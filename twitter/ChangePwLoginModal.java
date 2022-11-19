package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ChangePwLoginModal extends JDialog {
	
	public ChangePwLoginModal(Window parent) {
		super(parent, "Change Password", ModalityType.APPLICATION_MODAL);
		setSize(500, 400);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setBackground(Color.white);
			
	// Change Password Panel
		JPanel panelChangePw = new JPanel();
		panelChangePw.setBounds(0, 0, 495, 395);
		getContentPane().add(panelChangePw);
		panelChangePw.setLayout(null);
		
		// Logo
        JLabel imgLabel = new JLabel();
		ImageIcon logo = new ImageIcon(LoginModal.class.getResource("./twitter_logo.png"));
		
		Image img = logo.getImage();
		Image updateImg = img.getScaledInstance(70, 55, Image.SCALE_SMOOTH);
		ImageIcon updateLogo = new ImageIcon(updateImg);
		
		imgLabel.setIcon(updateLogo);
		imgLabel.setBounds(212, 46, 70, 55);
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
		
        // Description Label
		JLabel updatePwLabel = new JLabel("Change your Password");
		updatePwLabel.setFont(new Font("Arial", Font.BOLD, 18));
		updatePwLabel.setForeground(Color.black);
		updatePwLabel.setBounds(135,122,350,50);
		
		// New Password
		JLabel newPassword = new JLabel("New Password  ");
		newPassword.setFont(new Font("Arial", Font.BOLD, 18));
		newPassword.setForeground(new Color(128,128,128));
		newPassword.setBounds(86,185,150,50);
		JPasswordField textFieldnewPw = new JPasswordField(45);
		textFieldnewPw.setBounds(236, 195, 180, 32);
		
		// Change Password button
		JButton btnChangePw = new JButton("Change Password");
		btnChangePw.setFont(new Font("Arial", Font.BOLD, 18));
		btnChangePw.setBackground(new Color(0,172,238));
		btnChangePw.setForeground(Color.white);
		btnChangePw.setBounds(140,260,200,40);
			
		// Press a Change Password Button
		btnChangePw.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {		
				String newUserPassword = new String (textFieldnewPw.getPassword());
				
				try {
					JdbcConnection JdbcConnection = new JdbcConnection();
				}			
				catch (Exception e){
					JOptionPane.showMessageDialog(null, "Wrong approach!");
		        }
				
			}			
		});
		
		panelChangePw.add(imgLabel);
		panelChangePw.add(updatePwLabel);
		panelChangePw.add(newPassword);
		panelChangePw.add(textFieldnewPw);
		panelChangePw.add(btnChangePw);
		panelChangePw.setBackground(Color.white);
	}
	
}
