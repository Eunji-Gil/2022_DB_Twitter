package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JToggleButton;
import net.miginfocom.swing.MigLayout;
import temp.JdbcConnection;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class Follower extends JFrame implements ActionListener {
	static int userIdx;
	static int MyIdx;
	static String UserName;
	static String UserId;
	static int followingCount;
	static int count = 0;
	static int current_page = 0;
	static int max_page;
	static String string1;
	static String string2;
	static String string3;
	static String string4;
	static String string5;
	static int followingCeilCount;
	private JFrame frame;
	static String[][] Following;
	JButton profileButton;
	JButton userProfile5;
	JButton prev;
	JButton next;
	private JButton visitProfile1;
	private JButton visitProfile2;
	private JButton visitProfile3;
	private JButton visitProfile4;
	private JButton visitProfile5;
	private JLabel followingUser1;
	private JLabel followingUser2;
	private JLabel followingUser3;
	private JLabel followingUser4;
	private JLabel followingUser5;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel profileLable1;
	private JLabel profileLable2;
	private JLabel profileLable3;
	private JLabel profileLable4;
	private JLabel profileLable5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Follower window = new Follower();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public Follower(int userIdx, String userName, String userId, int followerCount,int MyIdx) {
		this.userIdx = userIdx;
		this.UserId = userId;
		this.UserName = userName;
		this.followingCount = (int) followerCount;
		this.followingCeilCount = (int) ((Math.ceil(followerCount / 5.0)) + 1) * 5;
		max_page = followingCount / 5;
		this.MyIdx = MyIdx;
	}

	public Follower() {
		try {
			JdbcConnection JdbcConnection = new JdbcConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[][] fol = JdbcConnection.viewFollower(userIdx);
		Following = new String[followingCeilCount][6];
		for (int i = 0; i < fol.length-1; i++) {
			for (int j = 0; j < 6; j++) {
				if (fol[i][j] == null)
					Following[i][j] = " ";
				else
					Following[i][j] = fol[i][j];
			}
		}
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(0, 0, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		profileButton = new JButton("←");
		profileButton.setBackground(new Color(255, 255, 255));
		profileButton.setFont(new Font("굴림", Font.BOLD, 20));
		profileButton.setBounds(0, 0, 126, 50);
		profileButton.addActionListener(this);
		frame.getContentPane().add(profileButton);

		JLabel name = new JLabel(UserName);
		name.setBounds(126, 0, 149, 29);
		frame.getContentPane().add(name);

		JLabel userId = new JLabel("@" + UserId);
		userId.setBounds(126, 29, 149, 21);
		frame.getContentPane().add(userId);

		JButton prev = new JButton("Prev");
		prev.setBackground(new Color(255, 255, 255));
		prev.setBounds(0, 680, 500, 90);
		frame.getContentPane().add(prev);

		JButton next = new JButton("Next");
		next.setBackground(new Color(255, 255, 255));
		next.setBounds(500, 680, 500, 90);
		frame.getContentPane().add(next);

		visitProfile1 = new JButton("VisitProfile");
		visitProfile1.setBackground(Color.WHITE);
		visitProfile1.setBounds(791, 130, 190, 110);
		visitProfile1.addActionListener(this);
		frame.getContentPane().add(visitProfile1);

		visitProfile2 = new JButton("VisitProfile");
		visitProfile2.setBackground(Color.WHITE);
		visitProfile2.setBounds(791, 240, 190, 110);
		visitProfile2.addActionListener(this);
		frame.getContentPane().add(visitProfile2);

		visitProfile3 = new JButton("VisitProfile");
		visitProfile3.setBackground(Color.WHITE);
		visitProfile3.setBounds(791, 350, 190, 110);
		visitProfile3.addActionListener(this);
		frame.getContentPane().add(visitProfile3);

		visitProfile4 = new JButton("VisitProfile");
		visitProfile4.setBackground(Color.WHITE);
		visitProfile4.setBounds(791, 460, 190, 110);
		visitProfile4.addActionListener(this);
		frame.getContentPane().add(visitProfile4);

		visitProfile5 = new JButton("VisitProfile");
		visitProfile5.setBackground(Color.WHITE);
		visitProfile5.setBounds(791, 570, 190, 110);
		visitProfile5.addActionListener(this);
		frame.getContentPane().add(visitProfile5);

		followingUser1 = new JLabel(setLabel(Following[count]));
		followingUser1.setBounds(110, 130, 681, 100);
		frame.getContentPane().add(followingUser1);

		followingUser2 = new JLabel(setLabel(Following[count + 1]));
		followingUser2.setBackground(new Color(0, 0, 0));
		followingUser2.setBounds(110, 240, 681, 100);
		frame.getContentPane().add(followingUser2);

		followingUser3 = new JLabel(setLabel(Following[count + 2]));
		followingUser3.setBounds(110, 350, 681, 100);
		frame.getContentPane().add(followingUser3);

		followingUser4 = new JLabel(setLabel(Following[count + 3]));
		followingUser4.setBounds(110, 460, 681, 100);
		frame.getContentPane().add(followingUser4);

		followingUser5 = new JLabel(setLabel(Following[count + 4]));
		followingUser5.setBounds(110, 570, 681, 100);
		frame.getContentPane().add(followingUser5);

		lblNewLabel = new JLabel("Follower");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 25));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 50, 989, 80);
		frame.getContentPane().add(lblNewLabel);

		panel = new JPanel();
		panel.setBackground(new Color(0, 128, 255));
		panel.setBounds(5, 135, 100, 100);
		frame.getContentPane().add(panel);

		profileLable1 = new JLabel();
		profileLable1.setIcon(profilePhoto(Following[count][5]));
		panel.add(profileLable1);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 255));
		panel_1.setBounds(5, 245, 100, 100);
		frame.getContentPane().add(panel_1);

		profileLable2 = new JLabel(profilePhoto(Following[count + 1][5]));
		panel_1.add(profileLable2);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 128, 255));
		panel_2.setBounds(5, 355, 100, 100);
		frame.getContentPane().add(panel_2);

		profileLable3 = new JLabel(profilePhoto(Following[count + 2][5]));
		panel_2.add(profileLable3);

		panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 128, 255));
		panel_3.setBounds(5, 465, 100, 100);
		frame.getContentPane().add(panel_3);

		profileLable4 = new JLabel(profilePhoto(Following[count + 3][5]));
		panel_3.add(profileLable4);

		panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 128, 255));
		panel_4.setBounds(5, 575, 100, 100);
		frame.getContentPane().add(panel_4);

		profileLable5 = new JLabel(profilePhoto(Following[count + 4][5]));
		panel_4.add(profileLable5);
	}

	public ImageIcon profilePhoto(String str) {
		ImageIcon image = new ImageIcon(str);
		Image img = image.getImage();
		Image updateImg = img.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		return updateIcon;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bb = (JButton) e.getSource();
		if (bb.equals(profileButton)) {
			dispose();
			MyProfile nam = new MyProfile(userIdx);
			MyProfile le = new MyProfile(); // View Profile
			le.main(null);
		}
		if (bb.equals(next) && max_page > current_page) {
			count += 5;
			profileLable1.setIcon(profilePhoto(Following[count][5]));
			profileLable2.setIcon(profilePhoto(Following[count + 1][5]));
			profileLable3.setIcon(profilePhoto(Following[count + 2][5]));
			profileLable4.setIcon(profilePhoto(Following[count + 3][5]));
			profileLable5.setIcon(profilePhoto(Following[count + 4][5]));
			followingUser1.setText(setLabel(Following[count]));
			followingUser2.setText(setLabel(Following[count + 1]));
			followingUser3.setText(setLabel(Following[count + 2]));
			followingUser4.setText(setLabel(Following[count + 3]));
			followingUser5.setText(setLabel(Following[count + 4]));
		}
		if (bb.equals(prev) && current_page > 0) {
			count -= 5;
			profileLable1.setIcon(profilePhoto(Following[count][5]));
			profileLable2.setIcon(profilePhoto(Following[count + 1][5]));
			profileLable3.setIcon(profilePhoto(Following[count + 2][5]));
			profileLable4.setIcon(profilePhoto(Following[count + 3][5]));
			profileLable5.setIcon(profilePhoto(Following[count + 4][5]));
			followingUser1.setText(setLabel(Following[count]));
			followingUser2.setText(setLabel(Following[count + 1]));
			followingUser3.setText(setLabel(Following[count + 2]));
			followingUser4.setText(setLabel(Following[count + 3]));
			followingUser5.setText(setLabel(Following[count + 4]));
		}
		if (bb.equals(visitProfile1)) {
			dispose();
			OtherProfile nam = new OtherProfile(Integer.parseInt(Following[count][4]), MyIdx);
			OtherProfile le = new OtherProfile(); // View Profile
			le.main(null);
		}
		if (bb.equals(visitProfile2)) {
			dispose();
			OtherProfile nam = new OtherProfile(Integer.parseInt(Following[count + 1][4]), MyIdx);
			OtherProfile le = new OtherProfile(); // View Profile
			le.main(null);
		}
		if (bb.equals(visitProfile3)) {
			dispose();
			OtherProfile nam = new OtherProfile(Integer.parseInt(Following[count + 2][4]), MyIdx);
			OtherProfile le = new OtherProfile(); // View Profile
			le.main(null);
		}
		if (bb.equals(visitProfile4)) {
			dispose();
			OtherProfile nam = new OtherProfile(Integer.parseInt(Following[count + 3][4]), MyIdx);
			OtherProfile le = new OtherProfile(); // View Profile
			le.main(null);
		}
		if (bb.equals(visitProfile5)) {
			dispose();
			OtherProfile nam = new OtherProfile(Integer.parseInt(Following[count + 4][4]), MyIdx);
			OtherProfile le = new OtherProfile(); // View Profile
			le.main(null);
		}
	}

	public static String setLabel(String[] userInfo) {
		String html = "<html>";
		String ent = "<br>";
		if (userInfo[1] == null)
			userInfo[1] = "";
		if (userInfo[2] == null)
			userInfo[2] = "";
		if (userInfo[3] == null)
			userInfo[3] = "";
		else
			userInfo[3] = "@" + userInfo[3];
		String label = html + userInfo[1] + ent + userInfo[3] + ent + userInfo[2] + html;
		return label;
	}
}
