package lab5.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lab5.client.Client;

/**
 * Authors: Allison Zastrow, Jake Bruemmer, Kathleen Szabo
 * emails/IDS in cover-page
 */

public class SetMsgOutputStream {
	
	private DataOutputStream dos;
	private boolean setNotCalled = true;
	
	public SetMsgOutputStream(DataOutputStream dos) {
		this.dos = dos;
	}
	
	/**
	 * Writes a String in UTF format to the DataOutputStream.
	 * @param a
	 */
	public void writeMessage(String a) {
		try {
			dos.writeUTF(a);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setNotCalled(String a) {
		if (a.equals("setCalled")) {
			setNotCalled = false;
			System.out.println("Changed setnotcalled to false");
		}
		else {setNotCalled = true;}
	}
	
	/**
	 * Getter method for the setNotCalled boolean instance variable. 
	 * @return
	 */
	public boolean setnotcalled() {
		return setNotCalled;
	}
	
	/**
	 * Sends a set call from a Client over the DataStream. The validity of the set that 
	 * has been called is checked by the server. 
	 * @param playerNum
	 * @param cardOne
	 * @param cardTwo
	 * @param cardThree
	 */
	public void setCall(byte playerNum, byte cardOne, byte cardTwo, byte cardThree) {
		try {
			dos.writeByte('!');
			dos.writeByte(15);
			dos.writeByte(3);
			dos.writeByte(playerNum);
			dos.writeByte(cardOne);
			dos.writeByte(cardTwo);
			dos.writeByte(cardThree);
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	/**
	 * Write "hello" to the DataStream in UTF format. 
	 * @param name
	 */
	public void sayHello(String name) {
		try {
			dos.writeByte('!');
			dos.writeByte(0);
			dos.writeByte(1);
			dos.writeUTF(name);
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	/**
	 * Asks the server to send information about the displayed Cards based on the protocol. 
	 */
	public void askDisplayed() {
		try {
			dos.writeByte('!');
			dos.writeByte(21);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends the index of the cards in the displayed set to be checked by the Server
	 * for set validity. 
	 * @param index
	 */
	public void checkArray(byte[] index) {
		try {
			dos.writeByte('!');
			dos.writeByte(23);
			dos.writeByte(index[0]);
			dos.writeByte(index[1]);
			dos.writeByte(index[2]);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Asks for the names of the players playing in the game. 
	 * @param name
	 */
	public void askNames(String name) {
		try {
			dos.writeByte('!');
			dos.writeByte(0);
			dos.writeUTF(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}

