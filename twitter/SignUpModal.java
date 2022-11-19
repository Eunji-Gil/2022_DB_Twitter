package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

// 회원가입 모달창 - 여기서 회원가입 실행
public class SignUpModal extends JDialog {
	
	public SignUpModal(Window parent) {
		super(parent, "Sign Up", ModalityType.APPLICATION_MODAL);
		setSize(500, 450);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setBackground(Color.white);
		
		// Select Panel Logo
		JLabel imgLabel = new JLabel();
		ImageIcon logo = new ImageIcon(LoginModal.class.getResource("./twitter_logo.png"));
		
		Image img = logo.getImage();
		Image updateImg = img.getScaledInstance(70, 55, Image.SCALE_SMOOTH);
		ImageIcon updateLogo = new ImageIcon(updateImg);
		
		imgLabel.setIcon(updateLogo);
		imgLabel.setBounds(212, 60, 70, 55);
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        
        // Sign up Email Panel Logo
        JLabel imgLabel2 = new JLabel();
		ImageIcon logo2 = new ImageIcon(LoginModal.class.getResource("./twitter_logo.png"));
		
		Image img2 = logo.getImage();
		Image updateImg2 = img.getScaledInstance(60, 45, Image.SCALE_SMOOTH);
		ImageIcon updateLogo2 = new ImageIcon(updateImg2);
		
		imgLabel2.setIcon(updateLogo2);
		imgLabel2.setBounds(212, 26, 60, 45);
        imgLabel2.setHorizontalAlignment(JLabel.CENTER);
        
        // Sign up Phone number Panel Logo
        JLabel imgLabel3 = new JLabel();
		ImageIcon logo3 = new ImageIcon(LoginModal.class.getResource("./twitter_logo.png"));
		
		Image img3 = logo.getImage();
		Image updateImg3 = img.getScaledInstance(60, 45, Image.SCALE_SMOOTH);
		ImageIcon updateLogo3 = new ImageIcon(updateImg2);
		
		imgLabel3.setIcon(updateLogo3);
		imgLabel3.setBounds(212, 26, 60, 45);
        imgLabel3.setHorizontalAlignment(JLabel.CENTER);
		
	///////////////////////////////////////////////////////////////////
	// Sign up by Email
		JPanel panelEmailSignUp = new JPanel();
		panelEmailSignUp.setBounds(0, 0, 495, 395);
		getContentPane().add(panelEmailSignUp);
		getContentPane().setBackground(Color.white);
		panelEmailSignUp.setLayout(null);
		
		// ID
		JLabel labelIdByEmail = new JLabel("ID  ");
		labelIdByEmail.setFont(new Font("Arial", Font.BOLD, 18));
		labelIdByEmail.setForeground(new Color(128,128,128));
		labelIdByEmail.setBounds(96,100,110,50);
		JTextField textFieldIdByEmail = new JTextField(45);
		textFieldIdByEmail.setBounds(206, 110, 190, 32);
		
		// Email
		JLabel labelEmail = new JLabel("Email  ");
		labelEmail.setFont(new Font("Arial", Font.BOLD, 18));
		labelEmail.setForeground(new Color(128,128,128));
		labelEmail.setBounds(96,150,100,50);
		JTextField textFieldEmail = new JTextField(45);
		textFieldEmail.setBounds(206, 160, 190, 32);
		
		// Name
		JLabel labelNameByEmail = new JLabel("Name  ");
		labelNameByEmail.setFont(new Font("Arial", Font.BOLD, 18));
		labelNameByEmail.setForeground(new Color(128,128,128));
		labelNameByEmail.setBounds(96,200,100,50);
		JTextField textFieldNameByEmail = new JTextField(45);
		textFieldNameByEmail.setBounds(206, 210, 190, 32);
		
		// Password
		JLabel labelPwByEmail = new JLabel("Password  ");
		labelPwByEmail.setFont(new Font("Arial", Font.BOLD, 18));
		labelPwByEmail.setForeground(new Color(128,128,128));
		labelPwByEmail.setBounds(96,250,100,50);
		JPasswordField pwFieldPwByEmail = new JPasswordField(45);
		pwFieldPwByEmail.setBounds(206, 260, 190, 32);
		
		// Sign up button
		JButton btnSignUpEmail = new JButton("Sign Up");
		
		btnSignUpEmail.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String userId = textFieldIdByEmail.getText();
				String userEmail = textFieldEmail.getText();
				String userName = textFieldNameByEmail.getText();
				String userPassword = new String (pwFieldPwByEmail.getPassword());
				
				try {
					JdbcConnection JdbcConnection = new JdbcConnection();
					
					if (!JdbcConnection.existUserID(userId)) {
						if (JdbcConnection.existEmail(userEmail)) {
							JOptionPane.showMessageDialog(null, "Email already exists.", "Warning", JOptionPane.ERROR_MESSAGE);
						}
						else {
							JdbcConnection.signUp(userId, userEmail, userPassword, userName);
							panelEmailSignUp.setVisible(false);
							JOptionPane.showMessageDialog(null, "Welcome to Twitter!");
						}
					}
					else if (JdbcConnection.existUserID(userId)) {
						JOptionPane.showMessageDialog(null, "ID already exists.", "Warning", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (Exception e) {
		               JOptionPane.showMessageDialog(null, "Wrong approach.");
		           }	
			}
		});
		
		btnSignUpEmail.setFont(new Font("Arial", Font.BOLD, 18));
		btnSignUpEmail.setBackground(new Color(0,172,238));
		btnSignUpEmail.setForeground(Color.white);
		btnSignUpEmail.setBounds(155,325,180,40);
		
		panelEmailSignUp.add(imgLabel2);
		panelEmailSignUp.add(labelIdByEmail);
		panelEmailSignUp.add(textFieldIdByEmail);
		panelEmailSignUp.add(labelEmail);
		panelEmailSignUp.add(textFieldEmail);
		panelEmailSignUp.add(labelNameByEmail);
		panelEmailSignUp.add(textFieldNameByEmail);
		panelEmailSignUp.add(labelPwByEmail);
		panelEmailSignUp.add(pwFieldPwByEmail);
		panelEmailSignUp.add(btnSignUpEmail);
		panelEmailSignUp.setBackground(Color.white);
		
	///////////////////////////////////////////////////////////////////
	// Sign up by Phone number
		JPanel panelPhoneSignUp = new JPanel();
		panelPhoneSignUp.setBounds(0, 0, 495, 395);
		getContentPane().add(panelPhoneSignUp);
		panelPhoneSignUp.setLayout(null);
		
		// ID
		JLabel labelIdByPhone = new JLabel("ID  ");
		labelIdByPhone.setFont(new Font("Arial", Font.BOLD, 18));
		labelIdByPhone.setForeground(new Color(128,128,128));
		labelIdByPhone.setBounds(96,100,100,50);
		JTextField textFieldIdByPhone = new JTextField(45);
		textFieldIdByPhone.setBounds(206, 110, 180, 32);
		
		// Phone number
		JLabel labelPhoneNumber = new JLabel("Number  ");
		labelPhoneNumber.setFont(new Font("Arial", Font.BOLD, 18));
		labelPhoneNumber.setForeground(new Color(128,128,128));
		labelPhoneNumber.setBounds(96,150,100,50);
		JTextField textFieldPhoneNumber = new JTextField(45);
		textFieldPhoneNumber.setBounds(206, 160, 180, 32);
		
		// Name
		JLabel labelNameByPhone = new JLabel("Name  ");
		labelNameByPhone.setFont(new Font("Arial", Font.BOLD, 18));
		labelNameByPhone.setForeground(new Color(128,128,128));
		labelNameByPhone.setBounds(96,200,100,50);
		JTextField textFieldNamePhone = new JTextField(45);
		textFieldNamePhone.setBounds(206, 210, 180, 32);
		
		// Password
		JLabel labelPwPhone = new JLabel("Password  ");
		labelPwPhone.setFont(new Font("Arial", Font.BOLD, 18));
		labelPwPhone.setForeground(new Color(128,128,128));
		labelPwPhone.setBounds(96,250,100,50);
		JPasswordField pwFieldPwPhone = new JPasswordField(45);
		pwFieldPwPhone.setBounds(206, 260, 180, 32);
		
		// Sign up button
		JButton btnSignUpPhone = new JButton("Sign Up");
		
		btnSignUpPhone.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String userId = textFieldIdByPhone.getText();
				String userPhoneNumber = textFieldPhoneNumber.getText();
				String userName = textFieldNamePhone.getText();
				String userPassword = new String (pwFieldPwPhone.getPassword());
				
				try {
					JdbcConnection JdbcConnection = new JdbcConnection();
					
					if (!JdbcConnection.existUserID(userId)) {
						if (!JdbcConnection.existPhoneNumber(userPhoneNumber)) {
								JdbcConnection.singUpPhoneNumber(userId, userPhoneNumber, userPassword, userName);
								panelPhoneSignUp.setVisible(false);
								JOptionPane.showMessageDialog(null, "Welcome to Twitter!");					
							}
						else {
							JOptionPane.showMessageDialog(null, "Phone number already exists.", "Warning", JOptionPane.ERROR_MESSAGE);
						}
					}
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
		btnSignUpPhone.setBackground(new Color(0,172,238));
		btnSignUpPhone.setForeground(Color.white);
		btnSignUpPhone.setBounds(155,320,180,40);
				
		panelPhoneSignUp.add(imgLabel3);
		panelPhoneSignUp.add(labelIdByPhone);
		panelPhoneSignUp.add(textFieldIdByPhone);
		panelPhoneSignUp.add(labelPhoneNumber);
		panelPhoneSignUp.add(textFieldPhoneNumber);
		panelPhoneSignUp.add(labelNameByPhone);
		panelPhoneSignUp.add(textFieldNamePhone);
		panelPhoneSignUp.add(labelPwPhone);
		panelPhoneSignUp.add(pwFieldPwPhone);
		panelPhoneSignUp.add(btnSignUpPhone);
		panelPhoneSignUp.setBackground(Color.white);
		
	///////////////////////////////////////////////////////////////////
	// Choose how to Sign up
		JPanel panelSelect = new JPanel();
		panelSelect.setBounds(0, 0, 495, 395);
		getContentPane().add(panelSelect);
		panelSelect.setLayout(null);
		panelSelect.setBackground(Color.white);
		
		// Label
		JLabel labelSelect = new JLabel("Choose how to Sign Up");
		labelSelect.setFont(new Font("Arial", Font.BOLD, 20));
		labelSelect.setForeground(new Color(128,128,128));
		labelSelect.setBounds(130,140,230,40);
		
		// Email Button
		JButton btnEmail = new JButton("Email");
		btnEmail.setFont(new Font("Arial", Font.BOLD, 20));
		btnEmail.setBackground(new Color(0,172,238));
		btnEmail.setForeground(Color.white);
		btnEmail.setBounds(145,205,195,50);
		
		// Phone number Button
		JButton btnPhoneNumber = new JButton("Phone Number");
		btnPhoneNumber.setFont(new Font("Arial", Font.BOLD, 20));
		btnPhoneNumber.setBackground(new Color(0,172,238));
		btnPhoneNumber.setForeground(Color.white);
		btnPhoneNumber.setBounds(145,275,195,50);
		
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
		
		panelSelect.add(imgLabel);
		panelSelect.add(labelSelect);
		panelSelect.add(btnEmail);
		panelSelect.add(btnPhoneNumber);
		
	}

}
