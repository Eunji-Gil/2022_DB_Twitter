package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

// 로그인하지 않은 상태에서 비밀번호 바꾸는 모달 (Forget Password 버튼 누르면 실행)
public class ChangePwNotLoginModal extends JDialog {
	
	public ChangePwNotLoginModal(Window parent) {
		super(parent, "Forget Password", ModalityType.APPLICATION_MODAL);
		setSize(500, 400);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setBackground(Color.white);
		
//		Logo
        JLabel imgLabel = new JLabel();
		ImageIcon logo = new ImageIcon(LoginModal.class.getResource("./twitter_logo.png"));
		
		Image img = logo.getImage();
		Image updateImg = img.getScaledInstance(60, 45, Image.SCALE_SMOOTH);
		ImageIcon updateLogo = new ImageIcon(updateImg);
		
		imgLabel.setIcon(updateLogo);
		imgLabel.setBounds(212, 26, 60, 45);
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
		
	// Change Password Panel
		JPanel panelChangePw = new JPanel();
		panelChangePw.setBounds(0, 0, 495, 395);
		getContentPane().add(panelChangePw);
		panelChangePw.setLayout(null);
		
		// ID
		JLabel labelId = new JLabel("ID  ");
		labelId.setFont(new Font("Arial", Font.BOLD, 18));
		labelId.setForeground(new Color(128,128,128));
		labelId.setBounds(86,100,150,50);
		JTextField textFieldId = new JTextField(45);
		textFieldId.setBounds(236, 110, 180, 32);
		
		// Email
		JLabel labelEmail = new JLabel("Email  ");
		labelEmail.setFont(new Font("Arial", Font.BOLD, 18));
		labelEmail.setForeground(new Color(128,128,128));
		labelEmail.setBounds(86,150,100,50);
		JTextField textFieldEmail = new JTextField(45);
		textFieldEmail.setBounds(236, 160, 180, 32);
		
		// New Password
		JLabel newPassword = new JLabel("New Password  ");
		newPassword.setFont(new Font("Arial", Font.BOLD, 18));
		newPassword.setForeground(new Color(128,128,128));
		newPassword.setBounds(86,200,150,50);
		JPasswordField textFieldnewPw = new JPasswordField(45);
		textFieldnewPw.setBounds(236, 210, 180, 32);
		
		// Change Password button
		JButton btnChangePw = new JButton("Change Password");
		btnChangePw.setFont(new Font("Arial", Font.BOLD, 18));
		btnChangePw.setBackground(new Color(0,172,238));
		btnChangePw.setForeground(Color.white);
		btnChangePw.setBounds(140,275,200,40);
		
//		panelSuccessChange.setVisible(false);
		
		// Press a Change Password Button
		btnChangePw.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {		
				String userId = textFieldId.getText();
				String userEmail = textFieldEmail.getText();
				String newUserPassword = new String (textFieldnewPw.getPassword());
				
				try {
					JdbcConnection JdbcConnection = new JdbcConnection();
					
					if (JdbcConnection.existUserID(userId)) {
						if (!JdbcConnection.userConfirm(userId,userEmail)) {
							JOptionPane.showMessageDialog(null, "Wrong Email. Try again.", "Warning", JOptionPane.ERROR_MESSAGE);
						}
						else if (JdbcConnection.userConfirm(userId,userEmail)) {
							JdbcConnection.changeUserPassword(userId, newUserPassword);
							JOptionPane.showMessageDialog(null, "Password's changed!");
						}
					}
					else if (!JdbcConnection.existUserID(userId)) {
						JOptionPane.showMessageDialog(null, "Wrong Id. Try again.", "Warning", JOptionPane.ERROR_MESSAGE);
					}
				} 
				catch (Exception e){
					JOptionPane.showMessageDialog(null, "Wrong approach!");
		        }
				
//				panelSuccessChange.setVisible(true);	
//				panelChangePw.setVisible(false);
			}			
		});
		
		panelChangePw.add(imgLabel);
		panelChangePw.add(labelId);
		panelChangePw.add(textFieldId);
		panelChangePw.add(labelEmail);
		panelChangePw.add(textFieldEmail);
		panelChangePw.add(newPassword);
		panelChangePw.add(textFieldnewPw);
		panelChangePw.add(btnChangePw);
		panelChangePw.setBackground(Color.white);
	}
	
}
