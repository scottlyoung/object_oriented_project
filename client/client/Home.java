package client;

import javax.swing.*;

public class Home extends JFrame{
    private JPanel panel;
    private JTextField searchField;
    private JButton button1;

    public Home(){
        this.add(panel);
        this.setVisible(true);
        this.setSize(300,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
