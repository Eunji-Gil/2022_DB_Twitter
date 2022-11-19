package gui;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// 트위터 첫 실행화면 (여기서부터 시작해주세요)
public class twitterGUI {

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					twitterGUI window = new twitterGUI();
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
	public twitterGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Twitter");
		frame.setSize(WIDTH,HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.white);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 986, 763);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		// Logo
        JLabel imgLabel = new JLabel();
		ImageIcon logo = new ImageIcon(LoginModal.class.getResource("./twitter_logo.png"));
		
		Image img = logo.getImage();
		Image updateImg = img.getScaledInstance(50, 35, Image.SCALE_SMOOTH);
		ImageIcon updateLogo = new ImageIcon(updateImg);
		
		imgLabel.setIcon(updateLogo);
		imgLabel.setBounds(50, 28, 50, 35);
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(imgLabel);
        
        // Search
        JTextField search = new JTextField();
        search.setBounds(150, 30, 530, 35);
        panel.add(search);
        
        // Exploring
        JLabel explore = new JLabel("#Exploring");
        explore.setFont(new Font("Arial", Font.BOLD, 18));
        explore.setBounds(35, 110, 100, 20);
        panel.add(explore);
        
        // Welcome Twitter
        JLabel welcome = new JLabel("Welcome to Twitter!");
        explore.setFont(new Font("Arial", Font.BOLD, 18));
        explore.setBounds(35, 110, 100, 20);
        panel.add(explore);
		
		// Sign up
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpModal signUpModal = new SignUpModal(frame);
				signUpModal.setVisible(true);
			}
		});
		btnSignUp.setFont(new Font("Arial", Font.BOLD, 18));
		btnSignUp.setBackground(new Color(0,172,238));
		btnSignUp.setForeground(Color.white);
		btnSignUp.setBounds(718, 110, 190, 52);
		panel.add(btnSignUp);
		
		// Login
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginModal loginModal = new LoginModal(frame);
				loginModal.setVisible(true);
			}
		});
		btnLogin.setFont(new Font("Arial", Font.BOLD, 18));
		btnLogin.setBackground(new Color(0,172,238));
		btnLogin.setForeground(Color.white);
		btnLogin.setBounds(718, 180, 190, 52);
		panel.add(btnLogin);
		
		// Change Password (before login)
		JButton btnUpdatePassword = new JButton("Forget Password");
		btnUpdatePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePwNotLoginModal changePwNotloginModal = new ChangePwNotLoginModal(frame);
				changePwNotloginModal.setVisible(true);
			}
		});
		btnUpdatePassword.setFont(new Font("Arial", Font.BOLD, 18));
		btnUpdatePassword.setBackground(new Color(0,172,238));
		btnUpdatePassword.setForeground(Color.white);
		btnUpdatePassword.setBounds(718, 250, 190, 52);
		panel.add(btnUpdatePassword);
		
		// Exit Twitter
		JButton btnExit = new JButton("Exit Twitter");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Arial", Font.BOLD, 18));
		btnExit.setBackground(new Color(0,172,238));
		btnExit.setForeground(Color.white);
		btnExit.setBounds(718, 320, 190, 52);
		panel.add(btnExit);
	}

}
