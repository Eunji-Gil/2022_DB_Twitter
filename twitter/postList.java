import javax.swing.*;

public class postList extends JFrame {

    private JPanel postList;
    private JList list1;

    public static void main(String[] args) {
        postList post = new postList();
    }
    public void postList(){


        try {



            postList.add(new JScrollPane(table));
            frame.getContentPane().add(postList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }








    }
}
