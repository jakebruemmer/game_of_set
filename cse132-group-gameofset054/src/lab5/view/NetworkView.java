package lab5.view;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import lab5.model.Card;
import lab5.model.SetGame;
import lab5.model.SetMsgInputStream;
import lab5.model.SetMsgOutputStream;

public class NetworkView implements Runnable {
	SetGame model;
	ServerSocket ss;
	private SetMsgInputStream in;
	private SetMsgOutputStream out;

	public NetworkView(SetGame model) throws IOException {
		this.model = model;
		ss = new ServerSocket(3989);
	}
 
	public void run() {
		
		System.out.println("In the networkview runnable");
		Socket s = null;
		try {
			s = ss.accept();
			System.out.println("accepted a socket");
			DataInputStream dis = new DataInputStream(s.getInputStream());
			System.out.println("Created a dis");
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			System.out.println("Created a dos");
			in = new SetMsgInputStream(dis);
			out = new SetMsgOutputStream(dos);
			model.addPlayer("Fred");
			while (true) {	
				
				System.out.println("In the while loop");
				byte check = dis.readByte();
				System.out.println("Reading first check in networkview: " + check);
				while (check != '!') {
					check = dis.readByte();
				}
				byte nextCheck = dis.readByte();
				System.out.println("Reading type check in networkview: " + nextCheck);
				if (nextCheck == 21) {
					System.out.println("Got a display update request from client");
					dos.writeByte(22);
					for (int i = 0; i<12; i++) {
						dos.writeUTF(model.getDisplayed().get(i).getFileName());
					}
					System.out.println("Sent display information to client");

				}
				if (nextCheck == 23) {
					System.out.println("Got a set of cards to check from client");
					boolean isSet = false;
					Card[] set = new Card[3];
					byte anotherCheck = dis.readByte();
					while (anotherCheck != 25) {
						anotherCheck = dis.readByte();
					}
					System.out.println("About to read cards to check");
					for (int i = 0; i < 3; i++) {
						set[i] = model.getDisplayed().get(dis.readByte());
					}
					isSet = model.isSet(set[0], set[1], set[2]);
					dos.writeByte(26);
					if (isSet) {
						System.out.println("Made a set!");
						dos.writeByte(1);
						System.out.println("About to call update Game method");
						model.updateGame(set);
					}
					else {
						System.out.println("Didn't make a set :(");
						dos.writeByte(0);
					}
				}				
			}
		} catch (EOFException e) {
			// graceful termination on EOF
		} catch (IOException e) {
			System.out.println("Remote connection reset");
		}
	}

	private void startMsg(DataOutputStream s, int type, int length) throws IOException {
		s.writeByte((int)'!');	//msg delimiter
		s.writeByte(type);		//msg type
		s.writeShort(length);	//payload length (in bytes)
	}
}
