package twitter;

import java.awt.*;
import javax.swing.*;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 986, 763);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		// Sign up
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpModal signUpModal = new SignUpModal(frame);
				signUpModal.setVisible(true);
			}
		});
		btnSignUp.setFont(new Font("Arial", Font.BOLD, 18));
		btnSignUp.setBounds(718, 110, 185, 52);
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
		btnLogin.setBounds(718, 180, 185, 52);
		panel.add(btnLogin);
		
		// Update Password (비밀번호를 잊어버렸을 때, 비밀번호 변경)
		JButton btnUpdatePassword = new JButton("Forget Password");
		btnUpdatePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePwNotLoginModal changePwNotloginModal = new ChangePwNotLoginModal(frame);
				changePwNotloginModal.setVisible(true);
			}
		});
		btnUpdatePassword.setFont(new Font("Arial", Font.BOLD, 18));
		btnUpdatePassword.setBounds(718, 250, 185, 52);
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
		btnExit.setBounds(718, 320, 185, 52);
		panel.add(btnExit);
	}

}
