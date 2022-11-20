

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class twitterSearchGUI {

    private JFrame frmSearch;
    private JTextField SearchText;
    private JPanel panel_1;
    private JButton btnEnter;
    private JPanel panel_2;
    private JButton btnNewButton;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel label;
    private JLabel lblNewLabel_4;
    private JPanel panel_3;
    private JButton btnNewButton_1;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private JLabel lblNewLabel_7;
    private JLabel lblNewLabel_8;
    private JLabel lblNewLabel_9;
    private JPanel panel_4;
    private JButton btnNewButton_2;
    private JLabel lblNewLabel_10;
    private JLabel lblNewLabel_11;
    private JLabel lblNewLabel_12;
    private JLabel lblNewLabel_13;
    private JLabel lblNewLabel_14;
    private JPanel panel_5;
    private JButton btnNewButton_3;
    private JLabel lblNewLabel_15;
    private JLabel lblNewLabel_16;
    private JLabel lblNewLabel_17;
    private JLabel lblNewLabel_18;
    private JLabel lblNewLabel_19;
    private JPanel panel_6;
    private JButton btnNewButton_4;
    private JLabel lblNewLabel_20;
    private JLabel lblNewLabel_21;
    private JLabel lblNewLabel_22;
    private JLabel lblNewLabel_23;
    private JLabel lblNewLabel_24;

    /**
     * Launch the application.
     */
    static String input=null;
    private JLabel lblNewLabel_25;
    private JLabel lblNewLabel_26;
    private JLabel lblNewLabel_27;
    private JLabel lblNewLabel_28;
    private JLabel lblNewLabel_29;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    twitterSearchGUI window = new twitterSearchGUI(input);
                    window.frmSearch.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * Create the application.
     */
    public twitterSearchGUI(String input) {
        this.input=input;
        initialize(input);
        frmSearch.setVisible(true);
        frmSearch.setLocationRelativeTo(null);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(String searchinput) {



        try
        {
            JdbcConnection JdbcConnection = new JdbcConnection();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        frmSearch = new JFrame();
        frmSearch.setResizable(false);
        frmSearch.getContentPane().setEnabled(false);
        frmSearch.setTitle("SEARCH");
        frmSearch.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\m2nuu\\Desktop\\2022-2 학교자료\\데이터베이스\\Twitter JDBC\\172px-트위터_로고_(2012).png"));
        frmSearch.getContentPane().setBackground(new Color(255, 255, 255));
        frmSearch.setBounds(100, 100, 1000, 800);
        frmSearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmSearch.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(new LineBorder(new Color(0, 191, 255), 3, true));
        panel.setBounds(262, 51, 460, 56);
        frmSearch.getContentPane().add(panel);
        panel.setLayout(null);

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setFont(new Font("굴림", Font.BOLD, 25));
        textPane.setText("🔎");
        textPane.setBounds(12, 10, 36, 36);
        panel.add(textPane);

        SearchText = new JTextField();
        SearchText.setText("");
        SearchText.setFont(new Font("한컴 고딕", Font.PLAIN, 25));
        SearchText.setBounds(49, 10, 300, 36);
        panel.add(SearchText);
        SearchText.setColumns(10);




        btnEnter = new JButton("ENTER");
        btnEnter.setBackground(Color.WHITE);
        btnEnter.setBounds(351, 10, 97, 36);
        panel.add(btnEnter);
        btnEnter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Search(getInput());
            }
        });

        SearchText.addActionListener(new ActionListener() {
            /**
             * Responds to pressing the enter key in the textfield by sending
             * the contents of the text field to the server.    Then clear
             * the text area in preparation for the next message.
             */
            public void actionPerformed(ActionEvent e) {
                Search(getInput());
            }
        });


        panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(209, 217, 221), 1, true));
        panel_1.setBackground(new Color(209, 217, 221));
        panel_1.setBounds(12, 117, 960, 634);
        frmSearch.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_2.setBounds(12, 10, 936, 115);;
        panel_1.add(panel_2);
        panel_2.setLayout(null);

        btnNewButton = new JButton("VIEW");
        btnNewButton.setBackground(new Color(209, 217, 221));
        btnNewButton.setBounds(829, 10, 95, 95);
        panel_2.add(btnNewButton);

        lblNewLabel = new JLabel("");
        lblNewLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 14));
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setBounds(119, 10, 595, 95);
        panel_2.add(lblNewLabel);


        lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(706, 10, 119, 55);
        panel_2.add(lblNewLabel_1);

        lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(706, 50, 119, 55);
        panel_2.add(lblNewLabel_2);

        lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setBounds(12, 10, 95, 95);
        panel_2.add(lblNewLabel_3);

        lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setFont(new Font("함초롬돋움", Font.PLAIN, 12));
        lblNewLabel_4.setForeground(Color.LIGHT_GRAY);
        lblNewLabel_4.setBounds(119, 90, 595, 15);
        panel_2.add(lblNewLabel_4);

        lblNewLabel_25 = new JLabel("");
        lblNewLabel_25.setFont(new Font("함초롬바탕", Font.BOLD, 14));
        lblNewLabel_25.setBounds(119, 10, 595, 15);
        panel_2.add(lblNewLabel_25);

        panel_3 = new JPanel();
        panel_3.setLayout(null);
        panel_3.setBackground(Color.WHITE);
        panel_3.setBounds(12, 135, 936, 115);
        panel_1.add(panel_3);

        btnNewButton_1 = new JButton("VIEW");
        btnNewButton_1.setBackground(new Color(209, 217, 221));
        btnNewButton_1.setBounds(829, 10, 95, 95);
        panel_3.add(btnNewButton_1);

        lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setFont(new Font("함초롬돋움", Font.PLAIN, 14));
        lblNewLabel_5.setBackground(Color.WHITE);
        lblNewLabel_5.setBounds(119, 10, 595, 95);
        panel_3.add(lblNewLabel_5);

        lblNewLabel_6 = new JLabel("");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_6.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
        lblNewLabel_6.setBounds(706, 10, 119, 55);
        panel_3.add(lblNewLabel_6);

        lblNewLabel_7 = new JLabel("");
        lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_7.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
        lblNewLabel_7.setBounds(706, 50, 119, 55);
        panel_3.add(lblNewLabel_7);

        lblNewLabel_8 = new JLabel("");
        lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_8.setBounds(12, 10, 95, 95);
        panel_3.add(lblNewLabel_8);

        lblNewLabel_9 = new JLabel("");
        lblNewLabel_9.setForeground(Color.LIGHT_GRAY);
        lblNewLabel_9.setFont(new Font("함초롬돋움", Font.PLAIN, 12));
        lblNewLabel_9.setBounds(119, 90, 595, 15);
        panel_3.add(lblNewLabel_9);

        lblNewLabel_26 = new JLabel("");
        lblNewLabel_26.setFont(new Font("함초롬바탕", Font.BOLD, 14));
        lblNewLabel_26.setBounds(119, 10, 595, 15);
        panel_3.add(lblNewLabel_26);

        panel_4 = new JPanel();
        panel_4.setLayout(null);
        panel_4.setBackground(Color.WHITE);
        panel_4.setBounds(12, 260, 936, 115);
        panel_1.add(panel_4);

        btnNewButton_2 = new JButton("VIEW");
        btnNewButton_2.setBackground(new Color(209, 217, 221));
        btnNewButton_2.setBounds(829, 10, 95, 95);
        panel_4.add(btnNewButton_2);

        lblNewLabel_10 = new JLabel("");
        lblNewLabel_10.setFont(new Font("함초롬돋움", Font.PLAIN, 14));
        lblNewLabel_10.setBackground(Color.WHITE);
        lblNewLabel_10.setBounds(119, 10, 595, 95);
        panel_4.add(lblNewLabel_10);

        lblNewLabel_11 = new JLabel("");
        lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_11.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
        lblNewLabel_11.setBounds(706, 10, 119, 55);
        panel_4.add(lblNewLabel_11);

        lblNewLabel_12 = new JLabel("");
        lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_12.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
        lblNewLabel_12.setBounds(706, 50, 119, 55);
        panel_4.add(lblNewLabel_12);

        lblNewLabel_13 = new JLabel("");
        lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_13.setBounds(12, 10, 95, 95);
        panel_4.add(lblNewLabel_13);

        lblNewLabel_14 = new JLabel("");
        lblNewLabel_14.setForeground(Color.LIGHT_GRAY);
        lblNewLabel_14.setFont(new Font("함초롬돋움", Font.PLAIN, 12));
        lblNewLabel_14.setBounds(119, 90, 595, 15);
        panel_4.add(lblNewLabel_14);

        lblNewLabel_27 = new JLabel("");
        lblNewLabel_27.setFont(new Font("함초롬바탕", Font.BOLD, 14));
        lblNewLabel_27.setBounds(119, 10, 595, 15);
        panel_4.add(lblNewLabel_27);

        panel_5 = new JPanel();
        panel_5.setLayout(null);
        panel_5.setBackground(Color.WHITE);
        panel_5.setBounds(12, 385, 936, 115);
        panel_1.add(panel_5);

        btnNewButton_3 = new JButton("VIEW");
        btnNewButton_3.setBackground(new Color(209, 217, 221));
        btnNewButton_3.setBounds(829, 10, 95, 95);
        panel_5.add(btnNewButton_3);

        lblNewLabel_15 = new JLabel("");
        lblNewLabel_15.setFont(new Font("함초롬돋움", Font.PLAIN, 14));
        lblNewLabel_15.setBackground(Color.WHITE);
        lblNewLabel_15.setBounds(119, 10, 595, 95);
        panel_5.add(lblNewLabel_15);

        lblNewLabel_16 = new JLabel("");
        lblNewLabel_16.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_16.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
        lblNewLabel_16.setBounds(706, 10, 119, 55);
        panel_5.add(lblNewLabel_16);

        lblNewLabel_17 = new JLabel("");
        lblNewLabel_17.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_17.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
        lblNewLabel_17.setBounds(706, 50, 119, 55);
        panel_5.add(lblNewLabel_17);

        lblNewLabel_18 = new JLabel("");
        lblNewLabel_18.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_18.setBounds(12, 10, 95, 95);
        panel_5.add(lblNewLabel_18);

        lblNewLabel_19 = new JLabel("");
        lblNewLabel_19.setForeground(Color.LIGHT_GRAY);
        lblNewLabel_19.setFont(new Font("함초롬돋움", Font.PLAIN, 12));
        lblNewLabel_19.setBounds(119, 90, 595, 15);
        panel_5.add(lblNewLabel_19);

        lblNewLabel_28 = new JLabel("");
        lblNewLabel_28.setFont(new Font("함초롬바탕", Font.BOLD, 14));
        lblNewLabel_28.setBounds(119, 10, 595, 15);
        panel_5.add(lblNewLabel_28);

        panel_6 = new JPanel();
        panel_6.setLayout(null);
        panel_6.setBackground(Color.WHITE);
        panel_6.setBounds(12, 510, 936, 115);
        panel_1.add(panel_6);

        btnNewButton_4 = new JButton("VIEW");
        btnNewButton_4.setBackground(new Color(209, 217, 221));
        btnNewButton_4.setBounds(829, 10, 95, 95);
        panel_6.add(btnNewButton_4);

        lblNewLabel_20 = new JLabel("");
        lblNewLabel_20.setFont(new Font("함초롬돋움", Font.PLAIN, 14));
        lblNewLabel_20.setBackground(Color.WHITE);
        lblNewLabel_20.setBounds(119, 10, 595, 95);
        panel_6.add(lblNewLabel_20);

        lblNewLabel_21 = new JLabel("");
        lblNewLabel_21.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_21.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
        lblNewLabel_21.setBounds(706, 10, 119, 55);
        panel_6.add(lblNewLabel_21);

        lblNewLabel_22 = new JLabel("");
        lblNewLabel_22.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_22.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
        lblNewLabel_22.setBounds(706, 50, 119, 55);
        panel_6.add(lblNewLabel_22);

        lblNewLabel_23 = new JLabel("");
        lblNewLabel_23.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_23.setBounds(12, 10, 95, 95);
        panel_6.add(lblNewLabel_23);

        lblNewLabel_24 = new JLabel("");
        lblNewLabel_24.setForeground(Color.LIGHT_GRAY);
        lblNewLabel_24.setFont(new Font("함초롬돋움", Font.PLAIN, 12));
        lblNewLabel_24.setBounds(119, 90, 595, 15);
        panel_6.add(lblNewLabel_24);

        lblNewLabel_29 = new JLabel("");
        lblNewLabel_29.setFont(new Font("함초롬바탕", Font.BOLD, 14));
        lblNewLabel_29.setBounds(119, 10, 595, 15);
        panel_6.add(lblNewLabel_29);


        if(searchinput!=null)
        {
            Search(searchinput);
        }
    }

    public String getInput()
    {
        String inputtext=SearchText.getText();
        return inputtext;
    }

    public void Search(String inputtext)
    {

        int[] tempidx=new int[100];
        tempidx=JdbcConnection.searchPostidx(inputtext);


        lblNewLabel.setText(JdbcConnection.returnPostContent(tempidx[0])[0][0]);
        lblNewLabel_1.setText("♥ "+JdbcConnection.returnPostContent(tempidx[0])[0][4]);
        lblNewLabel_2.setText("▶ "+JdbcConnection.returnPostContent(tempidx[0])[0][5]);
        lblNewLabel_3.setIcon(new ImageIcon(JdbcConnection.returnPostContent(tempidx[0])[0][1]));
        lblNewLabel_4.setText("# "+JdbcConnection.returnPostContent(tempidx[0])[0][3]);
        btnNewButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                /* (tempidx[0])[0][3] 라는 postidx를 가진 포스트로 간다~ */
            }
        });

        lblNewLabel_5.setText(JdbcConnection.returnPostContent(tempidx[1])[1][0]);
        lblNewLabel_6.setText("♥ "+JdbcConnection.returnPostContent(tempidx[1])[1][4]);
        lblNewLabel_7.setText("▶ "+JdbcConnection.returnPostContent(tempidx[1])[1][5]);
        lblNewLabel_8.setIcon(new ImageIcon(JdbcConnection.returnPostContent(tempidx[1])[1][1]));
        lblNewLabel_9.setText("# "+JdbcConnection.returnPostContent(tempidx[1])[1][3]);
        btnNewButton_1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                /* (tempidx[1])[1][3] 라는 postidx를 가진 포스트로 간다~ */
            }
        });

        lblNewLabel_10.setText(JdbcConnection.returnPostContent(tempidx[2])[2][0]);
        lblNewLabel_11.setText("♥ "+JdbcConnection.returnPostContent(tempidx[2])[2][4]);
        lblNewLabel_12.setText("▶ "+JdbcConnection.returnPostContent(tempidx[2])[2][5]);
        lblNewLabel_13.setIcon(new ImageIcon(JdbcConnection.returnPostContent(tempidx[2])[2][1]));
        lblNewLabel_14.setText("# "+JdbcConnection.returnPostContent(tempidx[2])[2][3]);
        btnNewButton_2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                /* (tempidx[1])[1][3] 라는 postidx를 가진 포스트로 간다~ */
            }
        });

        lblNewLabel_15.setText(JdbcConnection.returnPostContent(tempidx[3])[3][0]);
        lblNewLabel_16.setText("♥ "+JdbcConnection.returnPostContent(tempidx[3])[3][4]);
        lblNewLabel_17.setText("▶ "+JdbcConnection.returnPostContent(tempidx[3])[3][5]);
        lblNewLabel_18.setIcon(new ImageIcon(JdbcConnection.returnPostContent(tempidx[3])[3][1]));
        lblNewLabel_19.setText("# "+JdbcConnection.returnPostContent(tempidx[3])[3][3]);
        btnNewButton_3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                /* (tempidx[1])[1][3] 라는 postidx를 가진 포스트로 간다~ */
            }
        });

        lblNewLabel_20.setText(JdbcConnection.returnPostContent(tempidx[4])[4][0]);
        lblNewLabel_21.setText("♥  "+JdbcConnection.returnPostContent(tempidx[4])[4][4]);
        lblNewLabel_22.setText("▶  "+JdbcConnection.returnPostContent(tempidx[4])[4][5]);
        lblNewLabel_23.setIcon(new ImageIcon(JdbcConnection.returnPostContent(tempidx[4])[4][1]));
        lblNewLabel_24.setText("#  "+JdbcConnection.returnPostContent(tempidx[4])[4][3]);
        btnNewButton_4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                /* (tempidx[1])[1][3] 라는 postidx를 가진 포스트로 간다~ */
            }
        });

        lblNewLabel_25.setText(JdbcConnection.returnPostContent(tempidx[0])[0][7] + " @" + JdbcConnection.returnPostContent(tempidx[0])[0][6]);
        lblNewLabel_26.setText(JdbcConnection.returnPostContent(tempidx[1])[1][7] + " @" + JdbcConnection.returnPostContent(tempidx[1])[1][6]);
        lblNewLabel_27.setText(JdbcConnection.returnPostContent(tempidx[2])[2][7] + " @" + JdbcConnection.returnPostContent(tempidx[2])[2][6]);
        lblNewLabel_28.setText(JdbcConnection.returnPostContent(tempidx[3])[3][7] + " @" + JdbcConnection.returnPostContent(tempidx[3])[3][6]);
        lblNewLabel_29.setText(JdbcConnection.returnPostContent(tempidx[4])[4][7] + " @" + JdbcConnection.returnPostContent(tempidx[4])[4][6]);
        SearchText.setText("");
    }
}