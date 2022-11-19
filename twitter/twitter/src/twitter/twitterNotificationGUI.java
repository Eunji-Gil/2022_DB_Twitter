package twitter;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class twitterNotificationGUI {

	private JFrame frmNotification;
	static String input=null;
	/**
	 * Launch the application.
	 */
	
	public static int userIdx; //받아올 userIdx
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					twitterNotificationGUI window = new twitterNotificationGUI(userIdx);
					window.frmNotification.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public twitterNotificationGUI(int userIdx) {
		this.userIdx=userIdx;
		initialize(userIdx);
		frmNotification.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int userIdx) {
		
		try
		{
		JdbcConnection JdbcConnection = new JdbcConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[][][] printValue=JdbcConnection.notificationResult(userIdx); //결과값
		
		frmNotification = new JFrame();
		frmNotification.setTitle("NOTIFICATION");
		frmNotification.getContentPane().setBackground(new Color(0, 191, 255));
		frmNotification.getContentPane().setLayout(null);
		
		JTextPane txtpnNotification = new JTextPane();
		txtpnNotification.setForeground(new Color(255, 255, 255));
		txtpnNotification.setFont(new Font("Dubai", Font.BOLD, 25));
		txtpnNotification.setText("NOTIFICATION");
		txtpnNotification.setBounds(156, 20, 177, 37);
		txtpnNotification.setBackground(null);
		frmNotification.getContentPane().add(txtpnNotification);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 79, 460, 331);
		frmNotification.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("FOLLOW NOTIFICATION");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Microsoft JhengHei", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 10, 436, 25);
		panel.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 45, 436, 44);
		panel_2.setBackground(new Color(209, 217, 221));
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JTextPane txtpnAbc = new JTextPane();
		txtpnAbc.setText(printValue[0][0][0]);
		txtpnAbc.setBounds(12, 10, 194, 24);
		panel_2.add(txtpnAbc);
		
		JTextPane txtpnAbc_1 = new JTextPane();
		txtpnAbc_1.setText(printValue[0][1][0]);
		txtpnAbc_1.setBounds(230, 10, 194, 24);
		panel_2.add(txtpnAbc_1);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(209, 217, 221));
		panel_2_1.setBounds(12, 99, 436, 44);
		panel.add(panel_2_1);
		
		JTextPane txtpnAbc_2 = new JTextPane();
		txtpnAbc_2.setText(printValue[0][0][1]);
		txtpnAbc_2.setBounds(12, 10, 194, 24);
		panel_2_1.add(txtpnAbc_2);
		
		JTextPane txtpnAbc_1_1 = new JTextPane();
		txtpnAbc_1_1.setText(printValue[0][1][1]);
		txtpnAbc_1_1.setBounds(230, 10, 194, 24);
		panel_2_1.add(txtpnAbc_1_1);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setLayout(null);
		panel_2_2.setBackground(new Color(209, 217, 221));
		panel_2_2.setBounds(12, 153, 436, 44);
		panel.add(panel_2_2);
		
		JTextPane txtpnAbc_3 = new JTextPane();
		txtpnAbc_3.setText(printValue[0][0][2]);
		txtpnAbc_3.setBounds(12, 10, 194, 24);
		panel_2_2.add(txtpnAbc_3);
		
		JTextPane txtpnAbc_1_2 = new JTextPane();
		txtpnAbc_1_2.setText(printValue[0][1][2]);
		txtpnAbc_1_2.setBounds(230, 10, 194, 24);
		panel_2_2.add(txtpnAbc_1_2);
		
		JPanel panel_2_3 = new JPanel();
		panel_2_3.setLayout(null);
		panel_2_3.setBackground(new Color(209, 217, 221));
		panel_2_3.setBounds(12, 207, 436, 44);
		panel.add(panel_2_3);
		
		JTextPane txtpnAbc_4 = new JTextPane();
		txtpnAbc_4.setText(printValue[0][0][3]);
		txtpnAbc_4.setBounds(12, 10, 194, 24);
		panel_2_3.add(txtpnAbc_4);
		
		JTextPane txtpnAbc_1_3 = new JTextPane();
		txtpnAbc_1_3.setText(printValue[0][1][3]);
		txtpnAbc_1_3.setBounds(230, 10, 194, 24);
		panel_2_3.add(txtpnAbc_1_3);
		
		JPanel panel_2_4 = new JPanel();
		panel_2_4.setLayout(null);
		panel_2_4.setBackground(new Color(209, 217, 221));
		panel_2_4.setBounds(12, 261, 436, 44);
		panel.add(panel_2_4);
		
		JTextPane txtpnAbc_5 = new JTextPane();
		txtpnAbc_5.setText(printValue[0][0][4]);
		txtpnAbc_5.setBounds(12, 10, 194, 24);
		panel_2_4.add(txtpnAbc_5);
		
		JTextPane txtpnAbc_1_4 = new JTextPane();
		txtpnAbc_1_4.setText(printValue[0][1][4]);
		txtpnAbc_1_4.setBounds(230, 10, 194, 24);
		panel_2_4.add(txtpnAbc_1_4);
		
		JButton btnNewButton = new JButton("VIEW FOLLOW");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(304, 10, 144, 23);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(12, 420, 460, 331);
		frmNotification.getContentPane().add(panel_1);
		
		JLabel lblPostNotification = new JLabel("COMMENT NOTIFICATION");
		lblPostNotification.setFont(new Font("Microsoft JhengHei", Font.BOLD, 20));
		lblPostNotification.setBounds(12, 10, 436, 25);
		panel_1.add(lblPostNotification);
		
		JPanel panel_2_5 = new JPanel();
		panel_2_5.setLayout(null);
		panel_2_5.setBackground(new Color(209, 217, 221));
		panel_2_5.setBounds(12, 45, 436, 44);
		panel_1.add(panel_2_5);
		
		JTextPane txtpnAbc_6 = new JTextPane();
		txtpnAbc_6.setText(printValue[1][0][0]);
		txtpnAbc_6.setBounds(12, 10, 194, 24);
		panel_2_5.add(txtpnAbc_6);
		
		JTextPane txtpnAbc_1_5 = new JTextPane();
		txtpnAbc_1_5.setText(printValue[1][1][0]);
		txtpnAbc_1_5.setBounds(230, 10, 194, 24);
		panel_2_5.add(txtpnAbc_1_5);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setLayout(null);
		panel_2_1_1.setBackground(new Color(209, 217, 221));
		panel_2_1_1.setBounds(12, 99, 436, 44);
		panel_1.add(panel_2_1_1);
		
		JTextPane txtpnAbc_2_1 = new JTextPane();
		txtpnAbc_2_1.setText(printValue[1][0][1]);
		txtpnAbc_2_1.setBounds(12, 10, 194, 24);
		panel_2_1_1.add(txtpnAbc_2_1);
		
		JTextPane txtpnAbc_1_1_1 = new JTextPane();
		txtpnAbc_1_1_1.setText(printValue[1][1][1]);
		txtpnAbc_1_1_1.setBounds(230, 10, 194, 24);
		panel_2_1_1.add(txtpnAbc_1_1_1);
		
		JPanel panel_2_2_1 = new JPanel();
		panel_2_2_1.setLayout(null);
		panel_2_2_1.setBackground(new Color(209, 217, 221));
		panel_2_2_1.setBounds(12, 153, 436, 44);
		panel_1.add(panel_2_2_1);
		
		JTextPane txtpnAbc_3_1 = new JTextPane();
		txtpnAbc_3_1.setText(printValue[1][0][2]);
		txtpnAbc_3_1.setBounds(12, 10, 194, 24);
		panel_2_2_1.add(txtpnAbc_3_1);
		
		JTextPane txtpnAbc_1_2_1 = new JTextPane();
		txtpnAbc_1_2_1.setText(printValue[1][1][2]);
		txtpnAbc_1_2_1.setBounds(230, 10, 194, 24);
		panel_2_2_1.add(txtpnAbc_1_2_1);
		
		JPanel panel_2_3_1 = new JPanel();
		panel_2_3_1.setLayout(null);
		panel_2_3_1.setBackground(new Color(209, 217, 221));
		panel_2_3_1.setBounds(12, 207, 436, 44);
		panel_1.add(panel_2_3_1);
		
		JTextPane txtpnAbc_4_1 = new JTextPane();
		txtpnAbc_4_1.setText(printValue[1][0][3]);
		txtpnAbc_4_1.setBounds(12, 10, 194, 24);
		panel_2_3_1.add(txtpnAbc_4_1);
		
		JTextPane txtpnAbc_1_3_1 = new JTextPane();
		txtpnAbc_1_3_1.setText(printValue[1][1][3]);
		txtpnAbc_1_3_1.setBounds(230, 10, 194, 24);
		panel_2_3_1.add(txtpnAbc_1_3_1);
		
		JPanel panel_2_4_1 = new JPanel();
		panel_2_4_1.setLayout(null);
		panel_2_4_1.setBackground(new Color(209, 217, 221));
		panel_2_4_1.setBounds(12, 261, 436, 44);
		panel_1.add(panel_2_4_1);
		
		JTextPane txtpnAbc_5_1 = new JTextPane();
		txtpnAbc_5_1.setText(printValue[1][0][4]);
		txtpnAbc_5_1.setBounds(12, 10, 194, 24);
		panel_2_4_1.add(txtpnAbc_5_1);
		
		JTextPane txtpnAbc_1_4_1 = new JTextPane();
		txtpnAbc_1_4_1.setText(printValue[1][1][4]);
		txtpnAbc_1_4_1.setBounds(230, 10, 194, 24);
		panel_2_4_1.add(txtpnAbc_1_4_1);
		
		JButton btnViewPost = new JButton("VIEW POST");
		btnViewPost.setBackground(new Color(255, 255, 255));
		btnViewPost.setBounds(304, 10, 144, 23);
		panel_1.add(btnViewPost);
		
		
		
		
		
		frmNotification.setBounds(100, 100, 500, 800);
		frmNotification.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
