package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

import gui.MyProfile;

public class LoginModal extends JDialog {
	
	public LoginModal(Window parent) {
		super(parent, "Login", ModalityType.APPLICATION_MODAL);
		setSize(500, 400);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setBackground(Color.white);
		
		// Logo
		JLabel imgLabel = new JLabel();
		ImageIcon logo = new ImageIcon(LoginModal.class.getResource("./twitter_logo.png"));
		
		Image img = logo.getImage();
		Image updateImg = img.getScaledInstance(70, 55, Image.SCALE_SMOOTH);
		ImageIcon updateLogo = new ImageIcon(updateImg);
		
		imgLabel.setIcon(updateLogo);
		imgLabel.setBounds(212, 40, 70, 55);
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
		
		// ID
		JLabel labelId = new JLabel("ID  ");
		labelId.setFont(new Font("Arial", Font.BOLD, 18));
		labelId.setForeground(new Color(128,128,128));
		labelId.setBounds(96,130,100,50);
		JTextField textFieldId = new JTextField(45);
		textFieldId.setBounds(206, 140, 190, 32);
		
		// Password
		JLabel labelPw = new JLabel("Password  ");
		labelPw.setFont(new Font("Arial", Font.BOLD, 18));
		labelPw.setForeground(new Color(128,128,128));
		labelPw.setBounds(96,180,100,50);
		JPasswordField textFieldPw = new JPasswordField(45);
		textFieldPw.setBounds(206, 190, 190, 32);
		
		// Login Button
		JButton btnLogin = new JButton("Login");
		
		// execute login
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				MyProfile mp = new MyProfile(login());
				mp.main(null);			
			}
			
			public int login() {
				 int userIdx = -1;
				 String userId = textFieldId.getText();
				 String userPassword = new String (textFieldPw.getPassword());
				 
				 try{
			            JdbcConnection JdbcConnection = new JdbcConnection();
			            
			            if (JdbcConnection.existUserID(userId)) {
			            	userIdx = JdbcConnection.login(userId, userPassword);
			            	JOptionPane.showMessageDialog(null, "Login Success!");
			            }

			            if(userIdx < 1) {
			            	JOptionPane.showMessageDialog(null, "Wrong input!");
			            } 

			        } catch (Exception e){
			        	JOptionPane.showMessageDialog(null, "Wrong input!");
			        }
				 return(userIdx);
			}
			
		});
		
		btnLogin.setFont(new Font("Arial", Font.BOLD, 18));
		btnLogin.setBackground(new Color(0,172,238));
		btnLogin.setForeground(Color.white);
		btnLogin.setBounds(176,265,120,40);
		
		// add to Dialog
		add(imgLabel);
		add(labelId);
		add(textFieldId);
		add(labelPw);
		add(textFieldPw);
		add(btnLogin);
	}

}