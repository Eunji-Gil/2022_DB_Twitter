package twitter;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;

public class twitterTrendGUI {

	private JFrame frmTrend;
	public String[] outputText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					twitterTrendGUI window = new twitterTrendGUI();
					window.frmTrend.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public twitterTrendGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		try
		{
		JdbcConnection JdbcConnection = new JdbcConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] trendResult=new String[10];
        trendResult=JdbcConnection.trendHash();
        outputText=JdbcConnection.trendHash();
		int[] trendNumResult=new int[10];
		for(int i=0;i<10; i++)
		{
			trendNumResult[i]=JdbcConnection.returnHashNumber(trendResult[i]);
		}
        
		
		frmTrend = new JFrame();
		frmTrend.getContentPane().setBackground(Color.WHITE);
		frmTrend.getContentPane().setLayout(null);
		frmTrend.setResizable(false);
		
		JTextPane txtpnTrendsForYou = new JTextPane();
		txtpnTrendsForYou.setForeground(new Color(0, 0, 0));
		txtpnTrendsForYou.setFont(new Font("Dubai", Font.BOLD, 25));
		txtpnTrendsForYou.setText("WHATS THE TREND?");
		txtpnTrendsForYou.setBounds(12, 10, 460, 37);
		frmTrend.getContentPane().add(txtpnTrendsForYou);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(209, 217, 221));
		panel.setBounds(12, 62, 460, 49);
		frmTrend.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("#"+trendResult[0]);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(12, 10, 217, 29);
		btnNewButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	new twitterSearchGUI(outputText[0]);
            }
        });
		panel.add(btnNewButton);
		
		JTextPane txtpn02 = new JTextPane();
		txtpn02.setFont(new Font("굴림", Font.PLAIN, 14));
		txtpn02.setText(": "+trendNumResult[0]+" Tweets");
		txtpn02.setBounds(268, 10, 180, 29);
		panel.add(txtpn02);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(209, 217, 221));
		panel_1.setBounds(12, 121, 460, 49);
		frmTrend.getContentPane().add(panel_1);
		
		JButton btnNewButton1 = new JButton("#"+trendResult[1]);
		btnNewButton1.setBackground(new Color(255, 255, 255));
		btnNewButton1.setBounds(12, 10, 217, 29);
btnNewButton1.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	new twitterSearchGUI(outputText[1]);
            }
        });
		panel_1.add(btnNewButton1);
		
		JTextPane txtpn02_1 = new JTextPane();
		txtpn02_1.setText(": "+trendNumResult[1]+" Tweets");
		txtpn02_1.setFont(new Font("굴림", Font.PLAIN, 14));
		txtpn02_1.setBounds(268, 10, 180, 29);
		panel_1.add(txtpn02_1);
		
		
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(209, 217, 221));
		panel_2.setBounds(12, 180, 460, 49);
		frmTrend.getContentPane().add(panel_2);
		
		JButton btnNewButton2 = new JButton("#"+trendResult[2]);
		btnNewButton2.setBackground(new Color(255, 255, 255));
		btnNewButton2.setBounds(12, 10, 217, 29);
btnNewButton2.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	new twitterSearchGUI(outputText[2]);
            }
        });
		panel_2.add(btnNewButton2);
		
		JTextPane txtpn02_2 = new JTextPane();
		txtpn02_2.setText(": "+trendNumResult[2]+" Tweets");
		txtpn02_2.setFont(new Font("굴림", Font.PLAIN, 14));
		txtpn02_2.setBounds(268, 10, 180, 29);
		panel_2.add(txtpn02_2);
		
		
		
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(209, 217, 221));
		panel_3.setBounds(12, 239, 460, 49);
		frmTrend.getContentPane().add(panel_3);
		
		JButton btnNewButton3 = new JButton("#"+trendResult[3]);
		btnNewButton3.setBackground(new Color(255, 255, 255));
		btnNewButton3.setBounds(12, 10, 217, 29);
btnNewButton3.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	new twitterSearchGUI(outputText[3]);
            }
        });
		panel_3.add(btnNewButton3);
		
		JTextPane txtpn02_3 = new JTextPane();
		txtpn02_3.setText(": "+trendNumResult[3]+" Tweets");
		txtpn02_3.setFont(new Font("굴림", Font.PLAIN, 14));
		txtpn02_3.setBounds(268, 10, 180, 29);
		panel_3.add(txtpn02_3);
		
		
		
		
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(209, 217, 221));
		panel_4.setBounds(12, 298, 460, 49);
		frmTrend.getContentPane().add(panel_4);
		
		JButton btnNewButton4 = new JButton("#"+trendResult[4]);
		btnNewButton4.setBackground(new Color(255, 255, 255));
		btnNewButton4.setBounds(12, 10, 217, 29);
		panel_4.add(btnNewButton4);
btnNewButton4.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	new twitterSearchGUI(outputText[4]);
            }
        });
		
		JTextPane txtpn02_4 = new JTextPane();
		txtpn02_4.setText(": "+trendNumResult[4]+" Tweets");
		txtpn02_4.setFont(new Font("굴림", Font.PLAIN, 14));
		txtpn02_4.setBounds(268, 10, 180, 29);
		panel_4.add(txtpn02_4);
		
		
		
		
		
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBackground(new Color(209, 217, 221));
		panel_5.setBounds(12, 357, 460, 49);
		frmTrend.getContentPane().add(panel_5);
		
		JButton btnNewButton5 = new JButton("#"+trendResult[5]);
		btnNewButton5.setBackground(new Color(255, 255, 255));
		btnNewButton5.setBounds(12, 10, 217, 29);
btnNewButton5.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	new twitterSearchGUI(outputText[5]);
            }
        });
		panel_5.add(btnNewButton5);
		
		
		JTextPane txtpn02_5 = new JTextPane();
		txtpn02_5.setText(": "+trendNumResult[5]+" Tweets");
		txtpn02_5.setFont(new Font("굴림", Font.PLAIN, 14));
		txtpn02_5.setBounds(268, 10, 180, 29);
		panel_5.add(txtpn02_5);
		
		
		
		
		
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBackground(new Color(209, 217, 221));
		panel_6.setBounds(12, 416, 460, 49);
		frmTrend.getContentPane().add(panel_6);
		
		JButton btnNewButton6 = new JButton("#"+trendResult[6]);
		btnNewButton6.setBackground(new Color(255, 255, 255));
		btnNewButton6.setBounds(12, 10, 217, 29);
btnNewButton6.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	new twitterSearchGUI(outputText[6]);
            }
        });
		panel_6.add(btnNewButton6);
		
		JTextPane txtpn02_6 = new JTextPane();
		txtpn02_6.setText(": "+trendNumResult[6]+" Tweets");
		txtpn02_6.setFont(new Font("굴림", Font.PLAIN, 14));
		txtpn02_6.setBounds(268, 10, 180, 29);
		panel_6.add(txtpn02_6);
		
		
		
		
		
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBackground(new Color(209, 217, 221));
		panel_7.setBounds(12, 475, 460, 49);
		frmTrend.getContentPane().add(panel_7);
		
		JButton btnNewButton7 = new JButton("#"+trendResult[7]);
		btnNewButton7.setBackground(new Color(255, 255, 255));
		btnNewButton7.setBounds(12, 10, 217, 29);
btnNewButton7.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	new twitterSearchGUI(outputText[7]);
            }
        });
		panel_7.add(btnNewButton7);
		
		JTextPane txtpn02_7 = new JTextPane();
		txtpn02_7.setText(": "+trendNumResult[7]+" Tweets");
		txtpn02_7.setFont(new Font("굴림", Font.PLAIN, 14));
		txtpn02_7.setBounds(268, 10, 180, 29);
		panel_7.add(txtpn02_7);
		
		
		
		
		
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBackground(new Color(209, 217, 221));
		panel_8.setBounds(12, 534, 460, 49);
		frmTrend.getContentPane().add(panel_8);
		
		JButton btnNewButton8 = new JButton("#"+trendResult[8]);
		btnNewButton8.setBackground(new Color(255, 255, 255));
		btnNewButton8.setBounds(12, 10, 217, 29);
btnNewButton8.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	new twitterSearchGUI(outputText[8]);
            }
        });
		panel_8.add(btnNewButton8);
		
		JTextPane txtpn02_8 = new JTextPane();
		txtpn02_8.setText(": "+trendNumResult[8]+" Tweets");
		txtpn02_8.setFont(new Font("굴림", Font.PLAIN, 14));
		txtpn02_8.setBounds(268, 10, 180, 29);
		panel_8.add(txtpn02_8);
		
		
		
		
		
		
		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setBackground(new Color(209, 217, 221));
		panel_9.setBounds(12, 593, 460, 49);
		frmTrend.getContentPane().add(panel_9);
		
		JButton btnNewButton9 = new JButton("#"+trendResult[9]);
		btnNewButton9.setBackground(new Color(255, 255, 255));
		btnNewButton9.setBounds(12, 10, 217, 29);
btnNewButton9.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	new twitterSearchGUI(outputText[9]);
            }
        });
		panel_9.add(btnNewButton9);
		
		JTextPane txtpn02_9 = new JTextPane();
		txtpn02_9.setText(": "+trendNumResult[9]+" Tweets");
		txtpn02_9.setFont(new Font("굴림", Font.PLAIN, 14));
		txtpn02_9.setBounds(268, 10, 180, 29);
		panel_9.add(txtpn02_9);
		
		
		
		
		frmTrend.setBackground(new Color(30, 144, 255));
		frmTrend.setTitle("TREND");
		frmTrend.setBounds(100, 100, 500, 700);
		frmTrend.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
