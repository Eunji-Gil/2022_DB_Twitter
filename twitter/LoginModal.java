package twitter;

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
		
		// ID
		JLabel labelId = new JLabel("ID  ");
		labelId.setFont(new Font("Arial", Font.BOLD, 18));
		labelId.setBounds(96,90,100,50);
		JTextField textFieldId = new JTextField(45);
		textFieldId.setBounds(206, 100, 170, 32);
		
		// Password
		JLabel labelPw = new JLabel("Password  ");
		labelPw.setFont(new Font("Arial", Font.BOLD, 18));
		labelPw.setBounds(96,140,100,50);
		JPasswordField textFieldPw = new JPasswordField(45);
		textFieldPw.setBounds(206, 150, 170, 32);
		
		// Login Button
		JButton btnLogin = new JButton("Login");
		
		// 로그인 실행
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
					System.out.println(userIdx);
				 return(userIdx);
			}
			
		});
		
		btnLogin.setFont(new Font("Arial", Font.BOLD, 18));
		btnLogin.setBounds(176,220,120,40);
		
		// add to Dialog
		add(labelId);
		add(textFieldId);
		add(labelPw);
		add(textFieldPw);
		add(btnLogin);
	}

}