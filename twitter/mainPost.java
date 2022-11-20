import javax.swing.*;
import java.awt.event.*;

public class mainPost extends JFrame {
    JFrame frame = new JFrame();
    private JPanel POST;
    private JButton TWEET;
    private JTextField contentTextField;
    private JButton twitter;
    private JTextField HashTag;
    private JTextField picture;
    private JButton ProFile;
    private JButton post_profile;
    private JButton Search;

    private JList post_list;
    private JPanel TREND;
    private JPanel MENU;
    private JButton HOMEButton;
    private JTextField everyoneCanReplyTextField;

    private JButton LOADButton;

    private JTextField searchText;
    private JButton trendBtn;
    private JPanel PostList;
    private JButton Expire;
    private JButton postView1;
    private JLabel name;

    private JButton proFile;
    private JPanel postList1;
    private JScrollPane sc;
    private JButton load;
    private JLabel email;
    private JLabel hash;
    private JLabel address;
    private JLabel content;
    private JButton like1;
    private JButton comment1;
    private JPanel POSTLI;
    private JPanel POSTLI2;



    private int i = 0, y = 50;

    public static void main(String[] args) {
        mainPost aa = new mainPost(1);
    }
    public mainPost(int userIdx) {
        isUndecorated();



        setContentPane(POST);
        setTitle("Twitter");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        TWEET.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent i) {
                String content = contentTextField.getText();
                String pic = picture.getText();
                String hash = HashTag.getText();


                try {
                    JdbcConnection jdbc = new JdbcConnection();
                    int postIdx = jdbc.uploadPost(userIdx, content);
                    jdbc.HashTag(postIdx, hash);
                    int photoIdx = jdbc.Photo(pic);
                    jdbc.PostPhoto(photoIdx, postIdx);

                    contentTextField.setText("");
                    picture.setText("");
                    HashTag.setText("");


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });

        try {
            JdbcConnection jdbcConnection = new JdbcConnection();
            String[][] data = jdbcConnection.postList();
            int[][] numData = jdbcConnection.postListLC();
            JPanel[] postList1 = new JPanel[data.length];
            int y = 20;
            for(int i = 0; i < data.length; i++) {
                name.setText(data[i][0]);
                email.setText(data[i][1]);
                postView1.setText("VIEW");
                content.setText(data[i][2]);
                address.setText(data[i][3]);
                hash.setText(data[i][4]);
                postList1[i].add(name);
                postList1[i].add(email);
                postList1[i].add(postView1);
                postList1[i].add(content);
                postList1[i].add(address);
                postList1[i].add(hash);
                postList1[i].setBounds(20,y,400,500);
                PostList.add(postList1[i]);
                y = y + 500;
            }
            sc = new JScrollPane(PostList);
            sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            int width = sc.getPreferredSize().width;
            int height = sc.getPreferredSize().height;
            sc.setBounds(100,20,width,height);

            load = new JButton();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        ProFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                POST.setVisible(false);
                GUI gui = new GUI();
            }
        });
        trendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                twitterTrendGUI trend = new twitterTrendGUI();
            }
        });
        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searCh = searchText.getText();
                twitterSearchGUI SCH = new twitterSearchGUI(searCh);
            }
        });
        trendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                twitterNotificationGUI No = new twitterNotificationGUI(userIdx);
            }
        });
        proFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI gui = new GUI();
            }
        });
    }

}