package twitter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ChangePwNotLoginModal extends JDialog {
	
	public ChangePwNotLoginModal(Window parent) {
		super(parent, "Forget Password", ModalityType.APPLICATION_MODAL);
		setSize(500, 400);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
	
	// Success Panel
		JPanel panelSuccessChange = new JPanel();
		panelSuccessChange.setBounds(0, 0, 495, 395);
		getContentPane().add(panelSuccessChange);
		panelSuccessChange.setLayout(null);
		
		// Label
		JLabel labelSuccess = new JLabel("Change password successfully!");
		labelSuccess.setFont(new Font("Arial", Font.BOLD, 18));
		labelSuccess.setBounds(100,150,400,50);
		
		panelSuccessChange.add(labelSuccess);
		
	// Change Password Panel
		JPanel panelChangePw = new JPanel();
		panelChangePw.setBounds(0, 0, 495, 395);
		getContentPane().add(panelChangePw);
		panelChangePw.setLayout(null);
		
		// ID
		JLabel labelId = new JLabel("ID  ");
		labelId.setFont(new Font("Arial", Font.BOLD, 18));
		labelId.setBounds(86,90,150,50);
		JTextField textFieldId = new JTextField(45);
		textFieldId.setBounds(236, 100, 170, 32);
		
		// Email
		JLabel labelEmail = new JLabel("Email  ");
		labelEmail.setFont(new Font("Arial", Font.BOLD, 18));
		labelEmail.setBounds(86,140,100,50);
		JTextField textFieldEmail = new JTextField(45);
		textFieldEmail.setBounds(236, 150, 170, 32);
		
		// New Password
		JLabel newPassword = new JLabel("New Password  ");
		newPassword.setFont(new Font("Arial", Font.BOLD, 18));
		newPassword.setBounds(86,190,150,50);
		JPasswordField textFieldnewPw = new JPasswordField(45);
		textFieldnewPw.setBounds(236, 200, 170, 32);
		
		// Change Password button
		JButton btnChangePw = new JButton("Change Password");
		btnChangePw.setFont(new Font("Arial", Font.BOLD, 18));
		btnChangePw.setBounds(140,270,200,40);
		
		panelSuccessChange.setVisible(false);
		
		// Press a Change Password Button
		btnChangePw.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {		
				String userId = textFieldId.getText();
				String userEmail = textFieldEmail.getText();
				String newUserPassword = new String (textFieldnewPw.getPassword());
				
				try {
					JdbcConnection JdbcConnection = new JdbcConnection();
					
					// userId 제대로 입력한 경우
					if (JdbcConnection.existUserID(userId)) {
						// email 잘못 입력한 경우
						if (!JdbcConnection.userConfirm(userId,userEmail)) {
							JOptionPane.showMessageDialog(null, "Wrong Email. Try again.", "Warning", JOptionPane.ERROR_MESSAGE);
						}
						else if (JdbcConnection.userConfirm(userId,userEmail)) {
							JdbcConnection.changeUserPassword(userId, newUserPassword);
							JOptionPane.showMessageDialog(null, "Password's changed!");
						}
					}
					// userId 잘못 입력한 경우
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
		
		panelChangePw.add(labelId);
		panelChangePw.add(textFieldId);
		panelChangePw.add(labelEmail);
		panelChangePw.add(textFieldEmail);
		panelChangePw.add(newPassword);
		panelChangePw.add(textFieldnewPw);
		panelChangePw.add(btnChangePw);
	}
	
}
