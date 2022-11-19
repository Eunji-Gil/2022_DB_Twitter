package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;


import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;

public class MyProfile extends JFrame implements ActionListener {
	static int MyIdx;
	static int userIdx;
	private JFrame frame;
	JButton editProfile;
	JButton following;
	JButton follower;
	JButton Tweets;
	JButton prev;
	JButton next;
	static String userID;
	static String username;
	static int followingCount;
	static int followerCount;
	static int count = 0;
	static int current_page = 1;
	static int max_page;
	static int PostCount;
	static int PostCeilCount;
	static String[] userEditInfo = new String[6];
	static String[][] postinfo;
	static int[] postCommentNum;
	static int[] postLikeNum;
	JLabel post1;
	JLabel post2;
	JLabel profileLable1;
	JLabel profileLable1_1;
	JPanel postPhoto1;
	JPanel postPhoto2;
	JButton viewPost2;
	JButton viewPost1;
	private JButton homeButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyProfile profile = new MyProfile();
					profile.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyProfile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			JdbcConnection JdbcConnection = new JdbcConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] profileInfo = JdbcConnection.profile(userIdx);
		String[] joinedSplit = profileInfo[5].split("-");
		System.out.println(username);
		username = profileInfo[1];
		System.out.println(username);

		userID = profileInfo[4];
		followingCount = Integer.parseInt(profileInfo[6]);
		followerCount = Integer.parseInt(profileInfo[7]);
		userEditInfo[0] = profileInfo[9];
		userEditInfo[1] = profileInfo[8];
		userEditInfo[2] = profileInfo[1];
		userEditInfo[3] = profileInfo[3];
		userEditInfo[4] = profileInfo[2];
		userEditInfo[5] = Integer.toString(userIdx);
		String[][] postinfo1 = JdbcConnection.postView(userIdx);
		int[] postCommentNum2 = JdbcConnection.postCommentNum(userIdx);
		int[] postLikeNum3 = JdbcConnection.postlikeCountNum(userIdx);
		PostCount = postinfo1.length;
		PostCeilCount = (int) ((Math.ceil(PostCount / 2.0))) * 2;
		postinfo = new String[PostCeilCount][3];
		max_page = PostCeilCount / 2;
		for (int i = 0; i < postinfo1.length; i++) {
			for (int j = 0; j < 3; j++) {
				if (postinfo1[i][j + 1] == null)
					postinfo[i][j] = " ";
				else
					postinfo[i][j] = postinfo1[i][j + 1];
			}
		}
		postCommentNum = new int[PostCeilCount];
		for (int i = 0; i < postCommentNum2.length; i++) {
			postCommentNum[i] = postCommentNum2[i];
		}
		postLikeNum = new int[PostCeilCount];
		for (int i = 0; i < postLikeNum3.length; i++) {
			postLikeNum[i] = postLikeNum3[i];
		}
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setForeground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(0, 0, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Profile");
		ImageIcon frameImg = new ImageIcon("src/image/Twitter.png");
		frame.setIconImage(frameImg.getImage());
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(209, 217, 221));
		panel_1.setBounds(200, 139, 200, 200);
		frame.getContentPane().add(panel_1);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(profilePhoto(profileInfo[8]));
		panel_1.add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(209, 217, 221));
		panel.setBounds(0, 0, 1000, 200);
		frame.getContentPane().add(panel);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setIcon(headPhoto(profileInfo[9]));
		panel.add(lblNewLabel);

		editProfile = new JButton("Edit profile");
		editProfile.addActionListener(this);
		editProfile.setBackground(new Color(255, 255, 255));
		editProfile.setBounds(700, 210, 123, 43);
		frame.getContentPane().add(editProfile);

		following = new JButton(profileInfo[6] + " Following");
		following.setBackground(new Color(255, 255, 255));
		following.setBounds(180, 468, 120, 23);
		following.addActionListener(this);
		frame.getContentPane().add(following);

		follower = new JButton(profileInfo[7] + " Follower");

		follower.setBackground(new Color(255, 255, 255));
		follower.setBounds(329, 468, 120, 23);
		follower.addActionListener(this);
		frame.getContentPane().add(follower);

		Tweets = new JButton("Tweets");
		Tweets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Tweets.setBackground(new Color(255, 255, 255));
		Tweets.setBounds(180, 501, 645, 23);
		frame.getContentPane().add(Tweets);

		JLabel bio = new JLabel(profileInfo[3]);
		bio.setBounds(180, 399, 291, 35);
		frame.getContentPane().add(bio);

		JLabel name = new JLabel(profileInfo[1]);
		name.setFont(new Font("굴림", Font.BOLD, 25));
		name.setBounds(180, 338, 300, 35);
		frame.getContentPane().add(name);

		JLabel userId = new JLabel("@" + profileInfo[4]);
		userId.setBounds(180, 374, 300, 24);
		frame.getContentPane().add(userId);

		JLabel location = new JLabel(profileInfo[2]);
		location.setBounds(180, 434, 149, 24);
		frame.getContentPane().add(location);

		JLabel joined = new JLabel(monthMapping(joinedSplit[1]) + " " + joinedSplit[0]);
		joined.setBounds(329, 434, 142, 23);
		frame.getContentPane().add(joined);

		postPhoto1 = new JPanel();
		postPhoto1.setBackground(new Color(209, 217, 221));
		postPhoto1.setBounds(185, 528, 90, 90);
		frame.getContentPane().add(postPhoto1);

		profileLable1 = new JLabel(postPhoto(postinfo[count][1]));
		postPhoto1.add(profileLable1);

		postPhoto2 = new JPanel();
		postPhoto2.setBackground(new Color(209, 217, 221));
		postPhoto2.setBounds(185, 627, 90, 90);
		frame.getContentPane().add(postPhoto2);

		profileLable1_1 = new JLabel(postPhoto(postinfo[count + 1][1]));
		postPhoto2.add(profileLable1_1);

		prev = new JButton("Prev");
		prev.setBackground(Color.WHITE);
		prev.setBounds(180, 721, 322, 40);
		prev.addActionListener(this);
		frame.getContentPane().add(prev);

		next = new JButton("Next");
		next.setBackground(Color.WHITE);
		next.setBounds(502, 721, 322, 40);
		next.addActionListener(this);
		frame.getContentPane().add(next);

		post1 = new JLabel(setLabel(postinfo[count], postCommentNum[count], postCommentNum[count]));
		post1.setBounds(280, 523, 445, 100);
		frame.getContentPane().add(post1);

		post2 = new JLabel(setLabel(postinfo[count + 1], postCommentNum[count + 1], postCommentNum[count + 1]));
		post2.setBounds(280, 622, 445, 100);
		frame.getContentPane().add(post2);

		viewPost1 = new JButton("viewPost");
		viewPost1.setBackground(new Color(255, 255, 255));
		viewPost1.setBounds(725, 524, 100, 100);
		frame.getContentPane().add(viewPost1);

		viewPost2 = new JButton("viewPost");
		viewPost2.setBackground(new Color(255, 255, 255));
		viewPost2.setBounds(725, 624, 100, 100);
		frame.getContentPane().add(viewPost2);

		homeButton = new JButton("<html>Home<br>button<html>");
		homeButton.setBounds(10, 210, 100, 100);
		homeButton.addActionListener(this);
		frame.getContentPane().add(homeButton);

	}

	public ImageIcon headPhoto(String str) {
		ImageIcon image = new ImageIcon(str);
		Image img = image.getImage();
		Image updateImg = img.getScaledInstance(1000, 200, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		return updateIcon;
	}

	public ImageIcon profilePhoto(String str) {
		ImageIcon image = new ImageIcon(str);
		Image img = image.getImage();
		Image updateImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		return updateIcon;
	}

	public ImageIcon postPhoto(String str) {
		ImageIcon image = new ImageIcon(str);
		Image img = image.getImage();
		Image updateImg = img.getScaledInstance(90, 100, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		return updateIcon;
	}

	public String monthMapping(String month) {
		switch (month) {
		case "1":
			return "January";
		case "2":
			return "February";
		case "3":
			return "March";
		case "4":
			return "April";
		case "5":
			return "May";
		case "6":
			return "June";
		case "7":
			return "July";
		case "8":
			return "August";
		case "9":
			return "September";
		case "10":
			return "October";
		case "11":
			return "November";
		case "12":
			return "December";
		default:
			return month;
		}

	}

	public MyProfile(int MyIdx) {
		this.MyIdx = MyIdx;
		this.userIdx = MyIdx;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bb = (JButton) e.getSource();
		if (bb.equals(editProfile)) {
			dispose();
			EditProfile nam = new EditProfile(userEditInfo);
			EditProfile le = new EditProfile(); // View Profile
			le.main(null);
		}
		if (bb.equals(following)) {
			dispose();
			Following nam = new Following(userIdx, username, userID, followingCount, MyIdx);
			Following le = new Following();
			le.main(null);
		}
		if (bb.equals(follower)) {
			dispose();
			Follower nam = new Follower(userIdx, username, userID, followerCount, MyIdx);
			Follower le = new Follower();
			le.main(null);
		}
		if (bb.equals(next) && max_page > current_page) {
			current_page += 1;
			count += 2;
			if (postinfo[count][1] != null) {
				profileLable1.setIcon(postPhoto(postinfo[count][1]));
			}
			if (postinfo[count + 1][1] != null) {
				profileLable1_1.setIcon(postPhoto(postinfo[count + 1][1]));
			}
			post1.setText(setLabel(postinfo[count], postCommentNum[count], postCommentNum[count]));
			post2.setText(setLabel(postinfo[count + 1], postCommentNum[count + 1], postCommentNum[count + 1]));

		}
		if (bb.equals(prev) && current_page > 1) {
			current_page -= 1;
			count -= 2;
			if (postinfo[count][1] != null) {
				profileLable1.setIcon(postPhoto(postinfo[count][1]));
			}
			if (postinfo[count + 1][1] != null) {
				profileLable1_1.setIcon(postPhoto(postinfo[count + 1][1]));
			}
			post1.setText(setLabel(postinfo[count], postCommentNum[count], postCommentNum[count]));
			post2.setText(setLabel(postinfo[count + 1], postCommentNum[count + 1], postCommentNum[count + 1]));

		}
		if (bb.equals(homeButton)) {
			//홈버튼으로 버튼 누르면 홈으로 연결되는 버튼입니다.
			}
		if(bb.equals(viewPost1)) {
			//
			//postinfo[count][2]가 post Idx입니다
		}
		if(bb.equals(viewPost2)) {
			//postinfo[count+1][2]가 postIdx입니다
		}
	}

	public static String setLabel(String[] userInfo, int postlikecount, int postcomment) {
		String html = "<html>";
		String ent = "<br>";
		String tab = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		if (userInfo[0] == null)
			userInfo[0] = "";
		if (userInfo[0] == null)
			userInfo[0] = "";
		String label = html + userInfo[0] + ent + ent + "Comment " + postcomment + tab + tab + tab + tab + tab
				+ " PostLike " + postlikecount + html;
		return label;
	}
}
