package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class MainFrameModal extends JDialog {

	public MainFrameModal (Window parent) {
		super(parent, "Change Password", ModalityType.APPLICATION_MODAL);
		setSize(500, 400);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setBackground(Color.white);
		
		// Change Password (after login - 로그인 후 메인화면에 추가)
		JButton btnUpdatePwLogin = new JButton("Update Password");
		
		btnUpdatePwLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePwLoginModal changePwloginModal = new ChangePwLoginModal(parent);
				changePwloginModal.setVisible(true);
			}
		});
		
		btnUpdatePwLogin.setFont(new Font("Arial", Font.BOLD, 18));
		btnUpdatePwLogin.setBackground(new Color(0,172,238));
		btnUpdatePwLogin.setForeground(Color.white);
		btnUpdatePwLogin.setBounds(718, 390, 190, 52);
		add(btnUpdatePwLogin);
	}

}
