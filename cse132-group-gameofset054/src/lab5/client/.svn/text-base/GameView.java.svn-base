/**
 * Authors: Allison Zastrow, Jake Bruemmer, Kathleen Szabo
 * emails/IDS in cover-page
 * This class creates the GUI for our game and allows the User to select cards and submit them as a set
 */

package lab5.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JToggleButton;

import java.awt.Panel;
import java.awt.Canvas;

import javax.swing.JCheckBox;

import java.awt.Checkbox;
import java.awt.Label;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import lab5.model.SetMsgInputStream;
import lab5.model.SetMsgOutputStream;
import javax.swing.JTextField;

public class GameView extends JFrame {

	private JPanel contentPane;
	private Client client; 
	private SetMsgOutputStream out;
	private SetMsgInputStream in;
	//	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameView frame = new GameView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. Client is run from the constructor and this class can and receive
	 * messages from the DataStream through the Client instance variable. 
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws MalformedURLException 
	 */
	public GameView() throws UnknownHostException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		client = new Client();
		final JToggleButton[] buttons = new JToggleButton[12];

		JLabel lblTheGameOf = new JLabel("The Game of Set!");
		lblTheGameOf.setBounds(5, 5, 1200, 39);
		lblTheGameOf.setFont(new Font("BankGothic Md BT", Font.PLAIN, 30));
		lblTheGameOf.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTheGameOf);

		final JToggleButton one = new JToggleButton(new ImageIcon("Images\\Blank Card (3).jpg"));
		one.setBounds(300, 50, 120, 175);
		contentPane.add(one);
		buttons[0] = one;

		final JToggleButton two = new JToggleButton(new ImageIcon("Images\\Blank Card (3).jpg"));
		two.setBounds(444, 50, 120, 175);
		contentPane.add(two);
		buttons[1] = two;

		final JToggleButton three = new JToggleButton(new ImageIcon("Images\\Blank Card (3).jpg"));
		three.setBounds(588, 50, 120, 175);
		contentPane.add(three);
		buttons[2] = three;

		final JToggleButton four = new JToggleButton(new ImageIcon("Images\\Blank Card (3).jpg"));
		four.setBounds(732, 50, 120, 175);
		contentPane.add(four);
		buttons[3] = four;

		final JToggleButton five = new JToggleButton(new ImageIcon("Images\\Blank Card (3).jpg"));
		five.setBounds(300, 255, 120, 175);
		contentPane.add(five);
		buttons[4] = five;

		final JToggleButton six = new JToggleButton(new ImageIcon("Images\\Blank Card (3).jpg"));
		six.setBounds(444, 255, 120, 175);
		contentPane.add(six);
		buttons[5] = six;

		final JToggleButton seven = new JToggleButton(new ImageIcon("Images\\Blank Card (3).jpg"));
		seven.setBounds(588, 255, 120, 175);
		contentPane.add(seven);
		buttons[6] = seven;

		final JToggleButton eight = new JToggleButton(new ImageIcon("Images\\Blank Card (3).jpg"));
		eight.setBounds(732, 255, 120, 175);
		contentPane.add(eight);
		buttons[7] = eight;

		final JToggleButton nine = new JToggleButton(new ImageIcon("Images\\Blank Card (3).jpg"));
		nine.setBounds(300, 460, 120, 175);
		contentPane.add(nine);
		buttons[8] = nine;

		final JToggleButton ten = new JToggleButton(new ImageIcon("Images\\Blank Card (3).jpg"));
		ten.setBounds(444, 460, 120, 175);
		contentPane.add(ten);
		buttons[9] = ten;

		final JToggleButton eleven = new JToggleButton(new ImageIcon("Images\\Blank Card (3).jpg"));
		eleven.setBounds(588, 460, 120, 175);
		contentPane.add(eleven);
		buttons[10] = eleven;

		final JToggleButton twelve = new JToggleButton(new ImageIcon("Images\\Blank Card (3).jpg"));
		twelve.setBounds(732, 460, 120, 175);
		contentPane.add(twelve);
		buttons[11] = twelve;

		JLabel submitSet= new JLabel("<html><div style =\"text-align: center;\"> SET! </html>");
		submitSet.setBounds(112, 190, 75, 75);		
		contentPane.add(submitSet);

		JLabel players = new JLabel("Players");
		players.setBounds(924, 44, 252, 50);
		contentPane.add(players);

		final JTextArea names = new JTextArea();
		names.setText("");
		names.setBounds(924, 104, 252, 75);
		names.setEditable(false);
		contentPane.add(names);

		final JLabel scores = new JLabel("Scores");
		scores.setBounds(924, 189, 252, 50);
		contentPane.add(scores);

		final JTextArea numbers = new JTextArea();
		numbers.setBounds(924, 249, 252, 75);
		numbers.setText("");
		numbers.setEditable(false);
		contentPane.add(numbers);

		// STARTS RUNNING THE CLIENT
		// TAKES IN BUTTONS, NAMES, AND NUMBERS SO CLIENT HAS ACCESS TO USE THEM
		Thread t = new Thread(new Runnable() {
			public void run() {
				client.run(buttons, names, numbers);
			}
		});
		t.start();

		JButton set = new JButton(new ImageIcon(new URL("http://www.clipartbest.com/cliparts/dT6/e7A/dT6e7AAGc.png")));
		set.setBounds(75, 275, 150, 150);
		contentPane.add(set);

		JLabel lblWhatsYoName = new JLabel("Enter name below.");
		lblWhatsYoName.setBounds(956, 403, 136, 16);
		contentPane.add(lblWhatsYoName);

		final JTextField textField = new JTextField();
		textField.setBounds(952, 431, 114, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnThatsMyName = new JButton("That's My Name!");
		btnThatsMyName.setBounds(946, 460, 127, 26);
		contentPane.add(btnThatsMyName);
		
		final JLabel infoLabel = new JLabel("");
		infoLabel.setBounds(23, 87, 202, 16);
		contentPane.add(infoLabel);
		
		btnThatsMyName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


					try {
						client.getDOS().writeByte('!');
						client.getDOS().writeByte(0);
						client.getDOS().writeUTF(textField.getText());
						textField.setEditable(false);
					} catch (IOException e) {
						// FIXME Auto-generated catch block
						e.printStackTrace();
					}
			}
			
		});



		set.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// FIXME
				// Send message of set to client to check to see if it's a set

				// check the buttons to see if selected
				int count = 0;
				boolean[] checkArray = new boolean[12];
				for (int i = 0; i < 12; i++) {
					if (buttons[i].isSelected()) {
						count++;
						checkArray[i] = true;
					}
					else {
						checkArray[i] = false;
					}
				}
				if (count != 3) {
					infoLabel.setText("Wrong amount of cards!");
					for (int i = 0; i<12; i++) {
						if (buttons[i].isSelected()) {
							buttons[i].setSelected(false);
						}
					}
				}
				// If 3 selected, create an array with the indices 
				// of the three selected cards to send to the gamecontroller
				else {
					byte[] index = new byte[3];
					int count2 = 0;
					for (int i = 0; i < 12; i++) {
						if (checkArray[i]) {
							index[count2] = (byte) i;
							System.out.println(i);
							count2++;
						}
					}
					// Uses setMsgOutputStream to send the info to the game controller
					client.getOut().checkArray(index);

					for (int i = 0; i < 12; i++) {
						if (buttons[i].isSelected()) {
							buttons[i].setSelected(false);
						}
					}
				}
			}
		});

	}
}
