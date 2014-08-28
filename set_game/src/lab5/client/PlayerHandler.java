/**
 * Authors: Allison Zastrow, Jake Bruemmer, Kathleen Szabo
 * emails/IDS in cover-page
 * This class communicates directly with the client and adds runnables to the blocking queue
 */

package lab5.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import lab5.model.*;

public class PlayerHandler extends Thread {

	private Socket socket;
	private static GameController game;
	private int playerNum;
	public int score;
	public static int num = 0;
	private DataInputStream in;
	private DataOutputStream out;
	private boolean shouldDie;
	private SetGame model;
	public String name;


	public PlayerHandler(Socket s, GameController g, SetGame setGame) {
		this.socket = s;
		this.game = g;
		this.score = 0;
		this.playerNum = num;
		this.model = setGame;
		this.name = "Player " + num;
		++num;
	}

	/**
	 * Run method listens for information from the client then adds Runnables to the 
	 * BlockingQueue of the instance GameController. The Runnables that are added
	 * are executed by the GameController.
	 */
	public void run() {
		game.addPlayer(this);
		try {
			//
			// get the socket's input stream and make a DataInputStream out of it
			//
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());

			while (true) {				
				// See if game is over
				if (model.death()) {
					String winner = game.findWinner();
					out.writeByte('!');
					out.writeByte(42);
					game.tellClientNames(winner+" is the winner!");
					game.shutDown();
					//FIXME
				}
				
				// Initial check: ! is the first thing sent over in our protocol
				byte check = in.readByte();
				while (check != '!') {
					check = in.readByte();
				}
				// Read type of message
				byte nextCheck = in.readByte();
				System.out.println("Reading type check in playerhandler: " + nextCheck);
				
				// Display update type is 21
				if (nextCheck == 21) {
					System.out.println("Got a display update request from client");
					game.addRunnable(new Runnable() {
						public void run() {
							System.out.println("Running the display runnable");
							for (int i = 0; i<12; i++) {
								try {
									out.writeUTF(model.getDisplayed().get(i).getFileName());
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					});
				}
				
				// Check 3 cards as a set type is 23
				if (nextCheck == 23) {
					for (int i = 0; i < 3; i++) {									
						byte a = in.readByte();
						game.getSet()[i] = model.getDisplayed().get(a);
					}
					
					// Adds runnable to check if three cards are a set
					game.addRunnable(new Runnable() {
						public void run() {
							System.out.println(game.getSet()[0].toString());
							boolean isSet = model.isSet(game.getSet()[0], game.getSet()[1], game.getSet()[2]);
							if (isSet) {
								model.updateGame(game.getSet());
								score++;
								game.playerScores.put(name, new Integer(score));
								game.tellClients((byte)'!');
								game.tellClients((byte) 5);	
								game.tellClientNames(game.playerNameScore());
								
							}		

						}
					});
				}


				// Adds a player's name, updates the player names on the GUI
				if (nextCheck == 0) {
					final String newName = in.readUTF();
					final int place = game.names.indexOf(name);
					this.name = newName;
					System.out.println(place);
					game.addRunnable(new Runnable() {
						public void run() {						
							game.names.add(name);
							game.tellClients((byte)'!');
							game.tellClients((byte)1);
							game.tellClientNames(game.playerNames());
						}
					});
				}
			}

		}
		catch (Throwable t) {
			System.out.println("Noting exception for " + this);
		}
		finally {
			game.removePlayer(this);
		}

	}
	
	/**
	 * Sends a byte to the client to give server update. 
	 * @param update
	 */

	public void tellClient(byte update) {
		try {
			out.writeByte(update);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generate uniform messages
	 * @param s
	 */
	private void sendMessage(String s) {
		game.message("From Client " + this + " " + s);
	}

	public void die() {
		shouldDie = true;
	}
	
	/**
	 * Getter method for player number.
	 * @return
	 */
	public int getPlayerNum() {
		return playerNum;
	}	

	public String toString() {
		return "Player #" + playerNum;
	}

	/**
	 * Method that's similar to tellClient. Tell's a client about an update but method is modified
	 * to tell client about a string. 
	 * @param number
	 * @param name
	 */
	public void tellClientName(byte number, String name) {
		try {
			out.writeByte(number);
			out.writeUTF(name);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Getter method for DataOutputStream instance variable of the PlayerHandler.
	 * @return
	 */
	public DataOutputStream getDOS() {
		return this.out;
	}

}
