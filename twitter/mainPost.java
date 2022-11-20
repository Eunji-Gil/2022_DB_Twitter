import javax.swing.*;
import java.awt.event.*;

public class mainPost extends JFrame{
    JFrame frame = new JFrame();
    private  JPanel POST;
    private  JButton TWEET;
    private  JTextField contentTextField;
    private  JButton twitter;
    private  JTextField HashTag;
    private  JTextField picture;
    private  JButton ProFile;
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
    private JButton button1;
    private JButton like1;
    private JButton comment1;
    private JPanel POSTLI;
    private JPanel POSTLI2;
    private JPanel list3;
    private JLabel name1;
    private JLabel likeC1;
    private JButton button2;
    private JLabel email2;
    private JLabel hash;
    private JButton like2;
    private JButton comment2;
    private JLabel commnetC2;
    private JLabel likeC2;
    private JLabel name2;
    private JButton button3;
    private JLabel email;
    private JButton llike3;
    private JButton comment3;
    private JLabel commentC3;
    private JLabel likeC3;
    private JLabel name3;

    private int i = 0, y = 50;

    public static void main(String[] args) {
        mainPost aa = new mainPost(1);
    }

    public mainPost(int userIdx) {


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

        LOADButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JdbcConnection jdbc = new JdbcConnection();
                    String[][] data = jdbc.postList();
                    int[][] Count = jdbc.postListLC();
//                    String l = Integer.toString(Count[0][0]);
//                    String c = Integer.toString(Count[0][1]);
                    button1.setText("VIEW");
                    POSTLI.add(button1);
                    name1.setText(data[0][0]);
                    POSTLI.add(name1);
                    email.setText(data[0][1]);
                    POSTLI.add(email);
                    like1.setText("❤️");
                    POSTLI.add(like1);
                    comment1.setText("✏️");
                    POSTLI.add(comment1);
//                    likeC1.setText(l);
                    POSTLI.add(likeC1);
//                    commentC1.setText(c);
                    POSTLI.add(comment1);
                    button2.setText("VIEW");
                    POSTLI2.add(button2);
//                    String Li = Integer.toString(Count[1][0]);
//                    String CO = Integer.toString(Count[1][1]);
                    name2.setText(data[1][0]);
                    POSTLI2.add(name2);
                    email2.setText(data[1][1]);
                    POSTLI2.add(email2);
                    like2.setText("❤️");
                    POSTLI2.add(like2);
                    comment2.setText("✏️");
//                    POSTLI2.add(comment2);
//                    commnetC2.setText(CO);
                    POSTLI2.add(commnetC2);
//                    likeC2.setText(Li);
                    POSTLI2.add(likeC2);
                    button3.setText("VIEW");
                    POSTLI2.add(button3);
//                    String Lik = Integer.toString(Count[1][0]);
//                    String Com = Integer.toString(Count[1][1]);
                    name2.setText(data[2][0]);
                    list3.add(name2);
                    email2.setText(data[2][1]);
                    list3.add(email2);
                    llike3.setText("❤️");
                    list3.add(llike3);
                    comment3.setText("✏️");
                    list3.add(comment3);
//                    commentC3.setText(Com);
                    list3.add(commentC3);
//                    likeC3.setText(Lik);
                    list3.add(likeC3);

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }




            }
        });

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
    }
}