package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import temp.JdbcConnection;
import javax.swing.JScrollPane;

public class EditProfile extends JFrame implements ActionListener {
	private JFrame frame;
	private JTextField HeadPhotoAdress;
	private JLabel EditProfilePhoto;
	private JTextField PhotoAdress;
	private JLabel EditProfileName;
	private JTextField name;
	private JLabel EditBio;
	private JTextField Bio;
	private JLabel EditProfileLocation;
	private JTextField Location;
	private static String[] userInfo;
	JButton profileButton;
	JButton saveButton;
	static int userIdx;
	/** 
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditProfile window = new EditProfile();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static String[] editProfileinfo;
	/**
	 * Create the application.
	 * 
	 * @param userEditInfo
	 */

	/**
	 * Initialize the contents of the frame.
	 */
	public EditProfile() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBackground(new Color(255, 255, 255));
		frame.setBounds(0, 0, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("EditProfile");
		ImageIcon frameImg = new ImageIcon("src/image/Twitter.png");
		frame.setIconImage(frameImg.getImage());
		saveButton = new JButton("Save");
		saveButton.setForeground(new Color(255, 255, 255));
		saveButton.setBackground(SystemColor.desktop);
		saveButton.setBounds(862, 0, 126, 50);
		saveButton.addActionListener(this);
		frame.getContentPane().add(saveButton);

		profileButton = new JButton("X");
		profileButton.setBackground(new Color(0, 0, 0));
		profileButton.setForeground(new Color(255, 255, 255));
		profileButton.setBounds(0, 0, 126, 50);
		profileButton.addActionListener(this);
		frame.getContentPane().add(profileButton);

		JLabel Title = new JLabel("          EditProfile");
		Title.setFont(new Font("굴림", Font.BOLD, 20));
		Title.setBounds(126, 0, 738, 50);
		frame.getContentPane().add(Title);

		HeadPhotoAdress = new JTextField(userInfo[0]);
		HeadPhotoAdress.setBounds(126, 110, 738, 70);
		frame.getContentPane().add(HeadPhotoAdress);
		HeadPhotoAdress.setColumns(10);

		JLabel EditProfileHeadPhoto = new JLabel("HeadPhoto");
		EditProfileHeadPhoto.setBounds(126, 80, 150, 30);
		frame.getContentPane().add(EditProfileHeadPhoto);

		EditProfilePhoto = new JLabel("Photo");
		EditProfilePhoto.setBounds(126, 190, 150, 30);
		frame.getContentPane().add(EditProfilePhoto);

		PhotoAdress = new JTextField(userInfo[1]);
		PhotoAdress.setColumns(10);
		PhotoAdress.setBounds(126, 220, 738, 70);
		frame.getContentPane().add(PhotoAdress);

		EditProfileName = new JLabel("Name");
		EditProfileName.setBounds(126, 300, 150, 30);
		frame.getContentPane().add(EditProfileName);

		name = new JTextField(userInfo[2]);
		name.setColumns(10);
		name.setBounds(126, 330, 738, 70);
		frame.getContentPane().add(name);

		EditBio = new JLabel("Bio");
		EditBio.setBounds(126, 410, 150, 30);
		frame.getContentPane().add(EditBio);

		Bio = new JTextField(userInfo[3]);
		Bio.setColumns(10);
		Bio.setBounds(126, 440, 738, 70);
		frame.getContentPane().add(Bio);

		EditProfileLocation = new JLabel("Location");
		EditProfileLocation.setBounds(126, 520, 150, 30);
		frame.getContentPane().add(EditProfileLocation);

		Location = new JTextField(userInfo[4]);
		Location.setColumns(10);
		Location.setBounds(126, 550, 738, 70);
		frame.getContentPane().add(Location);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(137, 91, 2, 2);
		frame.getContentPane().add(scrollPane);
	}

	public EditProfile(String[] userInfo) {
		this.userInfo = userInfo;
		userIdx = Integer.parseInt(userInfo[5]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bb=(JButton)e.getSource();
		if(bb.equals(profileButton)) {
			dispose();
			MyProfile nam =new MyProfile(userIdx);
			MyProfile le= new MyProfile();  //View Profile
			le.main(null);
		}
		if(bb.equals(saveButton)) {
			try {
				JdbcConnection JdbcConnection = new JdbcConnection();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JdbcConnection.editProfile(userIdx,PhotoAdress.getText(),HeadPhotoAdress.getText(),Bio.getText(),Location.getText(),name.getText());
		}
	}
}
