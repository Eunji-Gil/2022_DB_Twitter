package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SignUpModal extends JDialog {
	
	public SignUpModal(Window parent) {
		super(parent, "Sign Up", ModalityType.APPLICATION_MODAL);
		setSize(500, 450);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
	///////////////////////////////////////////////////////////////////
	// Sign up by Email
		JPanel panelEmailSignUp = new JPanel();
		panelEmailSignUp.setBounds(0, 0, 495, 395);
		getContentPane().add(panelEmailSignUp);
		panelEmailSignUp.setLayout(null);
		
		// ID
		JLabel labelIdByEmail = new JLabel("ID  ");
		labelIdByEmail.setFont(new Font("Arial", Font.BOLD, 18));
		labelIdByEmail.setBounds(96,90,100,50);
		JTextField textFieldIdByEmail = new JTextField(45);
		textFieldIdByEmail.setBounds(206, 100, 170, 32);
		
		// Email
		JLabel labelEmail = new JLabel("Email  ");
		labelEmail.setFont(new Font("Arial", Font.BOLD, 18));
		labelEmail.setBounds(96,140,100,50);
		JTextField textFieldEmail = new JTextField(45);
		textFieldEmail.setBounds(206, 150, 170, 32);
		
		// Name
		JLabel labelNameByEmail = new JLabel("Name  ");
		labelNameByEmail.setFont(new Font("Arial", Font.BOLD, 18));
		labelNameByEmail.setBounds(96,190,100,50);
		JTextField textFieldNameByEmail = new JTextField(45);
		textFieldNameByEmail.setBounds(206, 200, 170, 32);
		
		// Password
		JLabel labelPwByEmail = new JLabel("Password  ");
		labelPwByEmail.setFont(new Font("Arial", Font.BOLD, 18));
		labelPwByEmail.setBounds(96,240,100,50);
		JPasswordField pwFieldPwByEmail = new JPasswordField(45);
		pwFieldPwByEmail.setBounds(206, 250, 170, 32);
		
		// Sign up button
		JButton btnSignUpEmail = new JButton("Sign Up");
		
		// Email ȸ������ ����
		btnSignUpEmail.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String userId = textFieldIdByEmail.getText();
				String userEmail = textFieldEmail.getText();
				String userName = textFieldNameByEmail.getText();
				String userPassword = new String (pwFieldPwByEmail.getPassword());
				
				try {
					JdbcConnection JdbcConnection = new JdbcConnection();
					
					// Id �ߺ����� �ʴ� ���
					if (!JdbcConnection.existUserID(userId)) {
						// Email �ߺ��Ǵ� ���
						if (JdbcConnection.existEmail(userEmail)) {
							JOptionPane.showMessageDialog(null, "Email already exists.", "Warning", JOptionPane.ERROR_MESSAGE);
						}
						// Email �ߺ����� �ʴ� ���
						else {
							JdbcConnection.signUp(userId, userEmail, userPassword, userName);
							JOptionPane.showMessageDialog(null, "Welcome to Twitter!");
						}
					}
					// Id �ߺ��Ǵ� ���
					else if (JdbcConnection.existUserID(userId)) {
						JOptionPane.showMessageDialog(null, "ID already exists.", "Warning", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (Exception e) {
		               JOptionPane.showMessageDialog(null, "Wrong approach.");
		           }	
			}
		});
		
		btnSignUpEmail.setFont(new Font("Arial", Font.BOLD, 18));
		btnSignUpEmail.setBounds(145,320,180,40);
		
		panelEmailSignUp.add(labelIdByEmail);
		panelEmailSignUp.add(textFieldIdByEmail);
		panelEmailSignUp.add(labelEmail);
		panelEmailSignUp.add(textFieldEmail);
		panelEmailSignUp.add(labelNameByEmail);
		panelEmailSignUp.add(textFieldNameByEmail);
		panelEmailSignUp.add(labelPwByEmail);
		panelEmailSignUp.add(pwFieldPwByEmail);
		panelEmailSignUp.add(btnSignUpEmail);
		
	///////////////////////////////////////////////////////////////////
	// Sign up by Phone number
		JPanel panelPhoneSignUp = new JPanel();
		panelPhoneSignUp.setBounds(0, 0, 495, 395);
		getContentPane().add(panelPhoneSignUp);
		panelPhoneSignUp.setLayout(null);
		
		// ID
		JLabel labelIdByPhone = new JLabel("ID  ");
		labelIdByPhone.setFont(new Font("Arial", Font.BOLD, 18));
		labelIdByPhone.setBounds(96,90,100,50);
		JTextField textFieldIdByPhone = new JTextField(45);
		textFieldIdByPhone.setBounds(206, 100, 170, 32);
		
		// Phone number
		JLabel labelPhoneNumber = new JLabel("Number  ");
		labelPhoneNumber.setFont(new Font("Arial", Font.BOLD, 18));
		labelPhoneNumber.setBounds(96,140,100,50);
		JTextField textFieldPhoneNumber = new JTextField(45);
		textFieldPhoneNumber.setBounds(206, 150, 170, 32);
		
		// Name
		JLabel labelNameByPhone = new JLabel("Name  ");
		labelNameByPhone.setFont(new Font("Arial", Font.BOLD, 18));
		labelNameByPhone.setBounds(96,190,100,50);
		JTextField textFieldNamePhone = new JTextField(45);
		textFieldNamePhone.setBounds(206, 200, 170, 32);
		
		// Password
		JLabel labelPwPhone = new JLabel("Password  ");
		labelPwPhone.setFont(new Font("Arial", Font.BOLD, 18));
		labelPwPhone.setBounds(96,240,100,50);
		JPasswordField pwFieldPwPhone = new JPasswordField(45);
		pwFieldPwPhone.setBounds(206, 250, 170, 32);
		
		// Sign up button
		JButton btnSignUpPhone = new JButton("Sign Up");
		
		// Phone number ȸ������ ����
		btnSignUpPhone.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String userId = textFieldIdByPhone.getText();
				String userPhoneNumber = textFieldPhoneNumber.getText();
				String userName = textFieldNamePhone.getText();
				String userPassword = new String (pwFieldPwPhone.getPassword());
				
				try {
					JdbcConnection JdbcConnection = new JdbcConnection();
					
					// Id �ߺ����� �ʴ� ���
					if (!JdbcConnection.existUserID(userId)) {
						// phone number �ߺ����� �ʴ� ���
						if (!JdbcConnection.existPhoneNumber(userPhoneNumber)) {
								JdbcConnection.singUpPhoneNumber(userId, userPhoneNumber, userPassword, userName);
								panelPhoneSignUp.setVisible(false);
								JOptionPane.showMessageDialog(null, "Welcome to Twitter!");					
							}
						// phone number �ߺ��Ǵ� ���
						else {
							JOptionPane.showMessageDialog(null, "Phone number already exists.", "Warning", JOptionPane.ERROR_MESSAGE);
						}
					}
					// Id �ߺ��Ǵ� ���
					else if (JdbcConnection.existUserID(userId)) {
						JOptionPane.showMessageDialog(null, "ID already exists.", "Warning", JOptionPane.ERROR_MESSAGE);
					}
				} 
				catch (Exception e) {
		               JOptionPane.showMessageDialog(null, "Try again.");
		           }	
			}
		});
		
		btnSignUpPhone.setFont(new Font("Arial", Font.BOLD, 18));
		btnSignUpPhone.setBounds(145,320,180,40);
				
		panelPhoneSignUp.add(labelIdByPhone);
		panelPhoneSignUp.add(textFieldIdByPhone);
		panelPhoneSignUp.add(labelPhoneNumber);
		panelPhoneSignUp.add(textFieldPhoneNumber);
		panelPhoneSignUp.add(labelNameByPhone);
		panelPhoneSignUp.add(textFieldNamePhone);
		panelPhoneSignUp.add(labelPwPhone);
		panelPhoneSignUp.add(pwFieldPwPhone);
		panelPhoneSignUp.add(btnSignUpPhone);
		
	///////////////////////////////////////////////////////////////////
	// Choose a method of Sign up
		JPanel panelSelect = new JPanel();
		panelSelect.setBounds(0, 0, 495, 395);
		getContentPane().add(panelSelect);
		panelSelect.setLayout(null);
		
		// Label
		JLabel labelSelect = new JLabel("Choose how to Sign Up");
		labelSelect.setFont(new Font("Arial", Font.BOLD, 20));
		labelSelect.setBounds(130,75,230,40);
		
		// Email Button
		JButton btnEmail = new JButton("Email");
		btnEmail.setFont(new Font("Arial", Font.BOLD, 18));
		btnEmail.setBounds(160,145,180,40);
		
		// Phone number Button
		JButton btnPhoneNumber = new JButton("Phone Number");
		btnPhoneNumber.setFont(new Font("Arial", Font.BOLD, 18));
		btnPhoneNumber.setBounds(160,205,180,40);
		
		panelEmailSignUp.setVisible(false);
		panelPhoneSignUp.setVisible(false);
		
		///////////////////////////////////////////////////////////////
		// Press a Email Button
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				panelEmailSignUp.setVisible(true);	
				panelSelect.setVisible(false);
			}
		});
		
		// Press a Phone Number Button
		btnPhoneNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				panelPhoneSignUp.setVisible(true);	
				panelSelect.setVisible(false);
			}
		});
		
		panelSelect.add(labelSelect);
		panelSelect.add(btnEmail);
		panelSelect.add(btnPhoneNumber);
		
	}

}
