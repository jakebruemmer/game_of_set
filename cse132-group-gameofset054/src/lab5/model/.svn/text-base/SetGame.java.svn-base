/**
 * Authors: Allison Zastrow, Jake Bruemmer, Kathleen Szabo
 * emails/IDS in cover-page
 * This class creates a set game and controls 
 * methods like updating the displayed and checking if a set is valid
 */

package lab5.model;

import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import lab5.model.Player;

public class SetGame {
	private Set<Player> players;	// set of players in the game
	private int numPlayers; // number of players since game started
	private Vector<Card> played, unPlayed, displayed;
	private Card blankCard;
	private String winner;
	private boolean death;
	
	public SetGame() {
		death = false;
		players = new HashSet<Player>();
		numPlayers = 0;
		Vector<Card> cards = new Vector<Card>();
		File[] images = new File("Images\\").listFiles();
		for (File file : images) {
			String name = "Images\\" + "\\" + file.getName();			
			if (name.contains("(3)") && !name.contains("Blank")) {
				byte number = 0; 
				byte color = 0; 
				byte shape = 0; 
				byte shade = 0;
				if (name.charAt(8) == '1') {
					number = 1;
				}
				if (name.charAt(8) == '2') {
					number = 2;
				}
				if (name.charAt(8) == '3') {
					number = 3;
				}
				if (name.contains("Diamonds")) {
					shape = 1;
				}
				if (name.contains("Squiggles")) {
					shape = 2;
				}
				if (name.contains("Ovals")) {
					shape = 3;
				}
				if (name.contains("Red")) {
					color = 1;
				}
				if (name.contains("Blue")) {
					color = 2;
				}
				if (name.contains("Green")) {
					color = 3;
				}
				if (name.contains("Filled")) {
					shade = 1;
				}
				if (name.contains("Shaded")) {
					shade = 2;
				}
				if (name.contains("Open")) {
					shade = 3;
				}
				Card card = new Card(number, color, shape, shade, name);
				cards.add(card);
			//	System.out.println(card.toString());
			}
		}		
		cards.remove(new Card((byte) 0, (byte) 0,(byte) 0, (byte) 0, "Images\\BlankCard (3).jpg"));
		this.blankCard = new Card((byte) 0, (byte) 0,(byte) 0, (byte) 0, "Images\\BlankCard (3).jpg");
		this.unPlayed = cards;
		Vector<Card> placeholder = new Vector<Card>();
		int rand;
		Random generator = new Random();
		for (int i = 0; i < 12; i++) {
			rand = generator.nextInt(unPlayed.size() - 1);
			//int random = (int) (Math.random())*81;			
			placeholder.add(unPlayed.get(rand));
			unPlayed.remove(rand);
		}
		for (int i = 0; i < 12; i++) {
			for (int j = 1; j < 12; j++) {
				for (int k = 2; k < 12; k++) {
					if (isSet(placeholder.elementAt(i), placeholder.elementAt(j), 
							placeholder.elementAt(k))) this.displayed = placeholder;
				}
			}
		}
		
		this.played = new Vector<Card>();
	}
	
	/**
	 * Adds a player to the set of Players playing set.
	 * @param p player to be added to set of Players
	 */
	public synchronized void addPlayer(String player) {
		numPlayers++;
		Player p = new Player(player,numPlayers);
		if (players.contains(p)) {
			numPlayers--;
			return;
		}
		players.add(p);
		System.out.println("Added player " + player);
	}
	
	/**
	 * Accessor for set of players in game.
	 * @return set of players in game
	 */
	public synchronized Set<Player> getPlayers() {
		return players;
	}
	
	/**
	 * Getter method for unplayed cards.
	 * @return
	 */
	public Vector<Card> getUnPlayed() {
		return unPlayed;
	}
	
	/**
	 * Getter method for displayed cards. 
	 * @return
	 */
	public Vector<Card> getDisplayed() {
		return displayed;
	}
	
	public static void main(String[] args) {
		SetGame game = new SetGame();
	}
	
	/**
	 * Compares three supplied bytes to check if they're all the same or all different. 
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public boolean isMatched(byte a, byte b, byte c) {
		return ((a == b & b == c) || (a !=b && a!=c && c!=b));
	}
	
	/**
	 * Determines set validity of three supplied Cards by calling the isMatched() method
	 * on each byte of all three supplied Cards. 
	 * @param one
	 * @param two
	 * @param three
	 * @return
	 */
	public boolean isSet(Card one, Card two, Card three) {
		boolean isSet = isMatched(one.getInfo()[0], two.getInfo()[0], three.getInfo()[0]);
		isSet = isSet && isMatched(one.getInfo()[1], two.getInfo()[1], three.getInfo()[1]);
		isSet = isSet && isMatched(one.getInfo()[2], two.getInfo()[2], three.getInfo()[2]);
		isSet = isSet && isMatched(one.getInfo()[3], two.getInfo()[3], three.getInfo()[3]);
		return isSet;		
	}
	
	/**
	 * Updates the game and display board that is shown to all clients. This method also checks
	 * to see if the game is over, and if it is, reports the winner of the game to all of the 
	 * client's GUIs.
	 * @param cards
	 */
	public synchronized void updateGame(Card[] cards) {
		
		// See if Game is OVER
		boolean setLeft = false;
		int size = unPlayed.size();
		for (int i = 0; i < size ; i++) {
			for (int j = 1; j < size; j++) {
				for (int k = 2; k < size; k++) {
					if (isSet(unPlayed.elementAt(i), unPlayed.elementAt(j), unPlayed.elementAt(k))) setLeft = true;
				}
			}
		}
		
		if (!setLeft || size + displayed.size() < 3) {
			//Who Won?
			death = true;
		}
		
		
		for(int i = 0; i < 3; i++) {
			this.played.add(cards[i]);
			this.displayed.remove(cards[i]);
			System.out.println("Removed this card from displayed: " + cards[i].toString());
			System.out.println("Added this card to played: " + cards[i].toString());			
		}
				
		int rand;
		Random generator = new Random();
		for (int i = 0; i < 3; i++) {
			rand = generator.nextInt(unPlayed.size() - 1);
			this.displayed.add(unPlayed.get(rand));
			System.out.println("Added this card to displayed: " + unPlayed.get(rand).toString());
			unPlayed.remove(rand);
			System.out.println("Removed that card from unplayed");
		}
		
	
	}
	
	public boolean death() {
		return death;
	}
}
