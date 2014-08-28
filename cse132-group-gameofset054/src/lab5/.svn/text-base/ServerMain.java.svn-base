/**
 * Authors: Allison Zastrow, Jake Bruemmer, Kathleen Szabo
 * emails/IDS in cover-page
 * This class starts the game by creating a game controller and running it
 */

package lab5;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Set;

import lab5.client.PlayerHandler;
import lab5.model.GameController;
import lab5.model.Player;
import lab5.model.SetGame;
import lab5.view.Logger;
import lab5.view.NetworkView;

public class ServerMain extends Thread {

	private int port;
	private static GameController game;
	private ServerSocket ss;
	final private SetGame model;
	final private Random rand;
	public static final int DELAY = 0; // nominal sleep delay (in ms)
	
	public ServerMain(int port, GameController g)  throws IOException {
		rand = new Random();
		model = new SetGame();
		this.port = port;
		ServerMain.game = g;
		ss = new ServerSocket(10501);
	}

	public void run() {
		
		//
		// simulate random activity
		//
		try {			
			while (true) {
				final Socket s;
				s = ss.accept();
				PlayerHandler ph = new PlayerHandler(s,game, model);
				ph.start();	
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * The sleep that is observed nominally.
	 * Change DELAY to speed things up or slow things down.
	 */
	public static void sleep() {
		sleep(DELAY);
	}
	
	/**
	 * Delay for an arbitrary amount of time.
	 * @param ms delay amount (in ms)
	 */
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		}
		catch (Exception e) {
			throw new Error("ServerMain.sleep: should not happen " + e);
		}
	}
	
	/**
	 * Accessor for SetGame model
	 * @return SetGame model
	 */
	public SetGame getSetGame() {
		return model;
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		// Creating a game controller also creates a server, so we just made a gamecontroller
		final GameController v = new GameController();
		v.start();	
	}

}
