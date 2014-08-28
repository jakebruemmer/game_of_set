/**
 * Authors: Allison Zastrow, Jake Bruemmer, Kathleen Szabo
 * emails/IDS in cover-page
 * This class creates a client that communicates with a playerhandler
 */

package lab5.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import lab5.model.SetMsgInputStream;
import lab5.model.SetMsgOutputStream;

import org.hamcrest.core.Is;

/**
 *  Client
 *   Player program for game of Set.
 *
 *   Author: COMPLETE THIS
 *   Course: CSE 132
 *   Lab:    5
 */

public class Client {
	// Complete the Client class

	private Socket clientSocket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private SetMsgInputStream in;
	private SetMsgOutputStream out;
	private boolean setNotCalled, bruteForce;
	//	private PlayerHandler ph;

	public Client() throws UnknownHostException, IOException {
		this.clientSocket = new Socket("localhost", 10501);
		this.dis = new DataInputStream(clientSocket.getInputStream());
		this.dos = new DataOutputStream(clientSocket.getOutputStream());
		this.in = new SetMsgInputStream(dis);
		this.out = new SetMsgOutputStream(dos);
		setNotCalled = this.out.setnotcalled();
		this.bruteForce = false;
	}

	public void run(JToggleButton[] myButtons, JTextArea names, JTextArea numbers) {
		try {
			dos = new DataOutputStream(this.clientSocket.getOutputStream());
			dis = new DataInputStream(this.clientSocket.getInputStream());			
			out.askDisplayed();
			in.readDisplayed(myButtons);
			

			while (true) {
				System.out.println("LEWP");
				byte checkStart = dis.readByte();
				while (checkStart != '!') {
					checkStart = dis.readByte();
				}
				byte type = dis.readByte();
				if (type == 5) {
					byte rEad = dis.readByte();
					numbers.setText(dis.readUTF());
					out.askDisplayed();
					in.readDisplayed(myButtons);
				}
				if (type == 1) {
					System.out.println("Trying to read name!");
					byte needToRead = dis.readByte();					
					names.setText(dis.readUTF());
				}
				
				if (type == 42) {
					byte n2Read = dis.readByte();
					numbers.setText(dis.readUTF());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public DataOutputStream getDOS() {
		return dos;
	}

	public DataInputStream getDIS() {
		return dis;
	}

	public void updateBoard(JToggleButton[] myButtons) {
		try {

			dos.writeByte('!');				
			dos.writeByte(21);
			byte c = dis.readByte();
			for (int i = 0; i<12; i++) {
				String imageName = dis.readUTF();					
				myButtons[i].setIcon(new ImageIcon(imageName));
			}
		} catch (IOException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}



	}

	public void setBF() {
		this.bruteForce = true;
		System.out.println("SET BRUTEFORCE");
	}

	public boolean getBF() {
		return this.bruteForce;
	}
	
	public SetMsgOutputStream getOut() {
		return out;
	}

	/**
	 * Entry point for lab5 client.
	 * @param args unused
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	//	public static void main(String[] args) throws UnknownHostException, IOException {
	//		Client c = new Client();
	//		c.run();
	//	}

}
