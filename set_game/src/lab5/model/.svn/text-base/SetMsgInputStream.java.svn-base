/**
 * Authors: Allison Zastrow, Jake Bruemmer, Kathleen Szabo
 * emails/IDS in cover-page

 */

package lab5.model;

import java.io.DataInputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class SetMsgInputStream {

	private DataInputStream dis;

	public SetMsgInputStream(DataInputStream dis) {
		this.dis = dis;
	}
	
	/**
	 * Reads a message in UTF format from the DataInputStream instance variable. 
	 * @return
	 */
	public String readMessage() {
		try {
			return dis.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Reads a set of cards and returns the information of the cards in 
	 * String array. 
	 * @return
	 */
	public String[] readSet() {
		try {
			String[] cardsFromSet = new String[4];
			int excl = dis.readByte();
			if (excl == '!') {
				if (dis.readByte() == 4) {

				int check = dis.readByte();
				if (check == 15) {
					for(int i = 0; i < 4; i++) {
						cardsFromSet[i] = dis.readUTF();
					}
				}
			}
			
			}
			return cardsFromSet;
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Reads a String in UTF format from the DataInputStream. This method specifically looks for
	 * the hello message as stipulated from the protocol for lab 5. 
	 * @return
	 */
	public String readHello() {
		try {
			String name = ""; 
			int excl = dis.readByte();
			if(excl == '!') {
				int num = dis.readByte();
				if (num == 0) {
					if (dis.readByte() == 1) {
					name = dis.readUTF();
					}
				}
			}
			return name;
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}

	/**
	 * Reads information from DataInputStream about the cards to be displayed to clients and 
	 * then sets the JToggleButtons to the read Strings based on a file path. 
	 * @param myButtons
	 */
	public void readDisplayed(JToggleButton[] myButtons) {
		for (int i = 0; i<12; i++) {
			String imageName;
			try {
				imageName = dis.readUTF();
				myButtons[i].setIcon(new ImageIcon(imageName));
				System.out.println(imageName);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Reads a name in UTF format and returns a String. 
	 * @return
	 */
	public String readNames() {
		String names = "";
		try {
			byte read = dis.readByte();
			names = dis.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return names;
		
	}
	
	
	
	
	
}

