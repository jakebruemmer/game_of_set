/**
 * Authors: Allison Zastrow, Jake Bruemmer, Kathleen Szabo
 * emails/IDS in cover-page
 * This class creates a card object
 */
package lab5.model;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Card {
	
	private byte[] info;
	private String fileName;
	
	String[] colors = {null, "Red", "Blue", "Green"};
	String[] shapes = {null, "Diamond", "Squiggles", "Ovals"};
	String[] shading = {null, "Filled", "Shaded", "Open"};
	
	public Card(byte number, byte color, byte shape, byte shade, String fileName) {
		byte[] info = {number, color, shape, shade};
		this.info = info;
		this.fileName = fileName;
	}
	
	public byte[] getInfo() {
		return info;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public String toString() {
		return "Number: " + info[0] + ". Color: " + colors[info[1]] + ". Shape: " + shapes[info[2]] + ". Shade: " + shading[info[3]]+".";
	}
	
	public static void main(String[] args) {
		Set<Card> cards = new HashSet<Card>();
		File[] images = new File("Images\\").listFiles();
		for (File file : images) {
			String name = "Images\\" + "\\" + file.getName();
			System.out.println(name);
			if (name.contains("(3)")) {
				byte number = 0; 
				byte color = 0; 
				byte shape = 0; 
				byte shade = 0;
				if (name.charAt(0) == '1') {
					number = 1;
				}
				if (name.charAt(0) == '2') {
					number = 2;
				}
				if (name.charAt(0) == '3') {
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
				System.out.println(name);
		}
 	}

}
}
