/**
 * Authors: Allison Zastrow, Jake Bruemmer, Kathleen Szabo
 * emails/IDS in cover-page
 * This class controls the game
 */

package lab5.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import lab5.BlockingQueue;
import lab5.client.PlayerHandler;
import lab5.ServerMain;

public class GameController extends Thread {
	
	BlockingQueue<Runnable> runqueue; /* thread-safe */
	Set<PlayerHandler> players;
	private Card[] set = new Card[3];
	public Vector<String> names;
	public HashMap<String, Integer> playerScores;
	
	public GameController() {
		this.names = new Vector<String>();
		players = new HashSet<PlayerHandler>();
		runqueue = new BlockingQueue<Runnable>(10);
		this.playerScores = new HashMap<String, Integer>();
	}

	public void addRunnable(Runnable r) {
		runqueue.enqueue(r);
		System.out.println("Added Runnable");
		this.setSet(set);
	}

	//
	// Even though the following methods are called
	//    from multiple threads concurrently,
	//    none of them needs synchronization.
	// Each one simply adds a Runnable to the queue,
	//    and since the queue is serviced one Runnable
	//    at a time, the work is not performed in a
	//    multithreaded manner
	//
	public void addPlayer(final PlayerHandler h) {	
		System.out.println("Adding player");
		addRunnable(
				new Runnable() {
					public void run() {
						players.add(h);
						message("Player joined: " + h);
						for (PlayerHandler p : players) {
						}
					}
				}
				);
	}

	public void removePlayer(final PlayerHandler h) {
		addRunnable(
				new Runnable() {
					public void run() {
						players.remove(h);
						message("Player left: " + h);
					}
				}
				);
	}

	public void killPlayer(final int num) {
		addRunnable(
				new Runnable() {
					public void run() {
						for (PlayerHandler p : players) {
							if (p.getPlayerNum() == num) 
								p.die();
						}
					}
				}
				);
	}

	// FIXME
	public void showPlayers() {
		addRunnable(
				new Runnable() {
					public void run () {
						for (PlayerHandler p : players) {
							System.out.println(p.toString());
						}
					}
				}
				);


	}

	public void tellClient(final PlayerHandler p, final byte update) {
		addRunnable(
				new Runnable() {
					public void run() {
						// FIXME
						p.tellClient(update);
					}
				}
				);		
	}
	
	public void tellClients(final byte update) {
		for (PlayerHandler p: players) {
			tellClient(p,update);
		}
	}
	
	public void tellClientName(final PlayerHandler p, final String name) {
		addRunnable(new Runnable() {
			public void run() {
				p.tellClientName((byte)p.getPlayerNum(), name);
			}
		});
	}
	
	public void tellClientNames(final String name) {
		for (PlayerHandler p : players) {
			tellClientName(p, name);
		}
	}

	public void shutDown() {
		addRunnable(
				new Runnable() {
					public void run() {
						System.out.println("GameController shutting down");
						System.exit(0);
					}
				}
				);
	}

	public void message(final String s) {
		addRunnable(
				new Runnable() {
					public void run() {
						System.out.println("message: " + s);
					}
				}
				);
	}

	public void run() {
		try {
			System.out.println("Making a servermain");
			new ServerMain(10501, this).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("In the game controller run method");
		while (true) {
			Runnable r = runqueue.dequeue();
			r.run();
		}
	}

	private static void snooze(int secs) {
		try {
			Thread.sleep(secs*1000);
		}
		catch(Throwable t) {
			throw new Error("Bad sleep: " + t);
		}
	}
	
	public BlockingQueue<Runnable> getBQ() {
		return this.runqueue;
	}

	public Card[] getSet() {
		return set;
	}

	public void setSet(Card[] set) {
		this.set = set;
	}
	
	public Set<PlayerHandler> getPH() {
		return this.players;
	}
	
	public Vector<String> getNames() {
		return this.names;
	}
	
	public String playerNames() {
		StringBuilder sb = new StringBuilder();
		for (String s : names) {
			if (!s.contains("Player")) {
				sb.append(s + "\n");
			}
				
		}
		return sb.toString();
	}
	
	public String playerNameScore() {
		String namesAndScores = " ";
		for (String s : playerScores.keySet()) {
			namesAndScores += s + ": " + playerScores.get(s).intValue() + "\n";
		}
		return namesAndScores;
	}
	
	
	public String findWinner() {
		String winner = "";
		int max = 0;
		for (String s : playerScores.keySet()) {
			if (playerScores.get(s) > max) {
				max = playerScores.get(s);
				winner = s;
			}
		}
		return winner;
	}
	
	
}
