package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame{
    private JPanel panel;
    private JTextField searchField;
    private JButton button1;

    public Home(){
        this.add(panel);
        this.setVisible(true);
        this.setSize(300,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO pass search terms to MusicPlayer.Server.Account.DBManager

                /**
                 * MusicPlayer.Server.Account.DBManager.getInstance().searchExact(searchField.getText())
                 */
                // For Now Let's assume that what we get from DBManger is a song
                showSearchResults();


            }
        });
    }
    private void  showSearchResults(){
        JListCustomRenderer results = new JListCustomRenderer();

        results.setVisible(true);
    }
}
