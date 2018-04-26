package client;

import Server.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Home extends JFrame{
    private JPanel panel;
    private JTextField searchField;
    private JButton button1;
    private JPanel createPlaylistPanel;
    private JTextField newPlaylistTextField;
    private JButton createPlaylistButton;
    private PlaylistPlayer playlistPanel ;

    public Home(Account currentUser){
        this.getContentPane().add(panel,BorderLayout.NORTH);
        this.pack();
        this.setVisible(true);
        setBounds(100, 100, 778, 426);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /** Create a new playlist Panel **/
        createPlaylistPanel = new JPanel();
        createPlaylistPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel newPlaylistLabel = new JLabel("Enter Playlist Name");
        newPlaylistTextField = new JTextField(30);

        createPlaylistButton = new JButton("New Playlist");
        createPlaylistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPlaylist(newPlaylistTextField.getText(),currentUser);
            }
        });

        createPlaylistPanel.add(newPlaylistLabel);
        createPlaylistPanel.add(newPlaylistTextField);
        createPlaylistPanel.add(createPlaylistButton);

        this.getContentPane().add(createPlaylistPanel,  BorderLayout.SOUTH);


        playlistPanel = new PlaylistPlayer(currentUser);
        this.getContentPane().add(  playlistPanel,BorderLayout.CENTER);
        button1.setSize(100,100);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Item> results = DBManager.getInstance().search(searchField.getText());
                if(results.isEmpty()){
                    JOptionPane.showMessageDialog(null, "No Results Found");
                }
                else{
                    showSearchResults(results, currentUser);
                }
            }
        });
    }
    private void  showSearchResults(List<Item> results, Account currentUser){
        SearchResults searchResults = new SearchResults(results, currentUser);
        searchResults.setVisible(true);
    }
    private void createPlaylist(String title, Account currentUser){
        currentUser.addPlaylist(title);
        System.out.print(currentUser.getPlaylists().size());
        this.getContentPane().remove(playlistPanel );
        playlistPanel = new PlaylistPlayer(currentUser);
        this.getContentPane().add(playlistPanel,BorderLayout.CENTER);
        this.getContentPane().validate();
        this.getContentPane().repaint();
    }

}
