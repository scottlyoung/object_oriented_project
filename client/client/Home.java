package client;

import Server.Artist;
import Server.DBManager;
import Server.Item;
import Server.Song;

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

    public Home(){
        this.getContentPane().add(panel,BorderLayout.NORTH);
        this.pack();
        this.setVisible(true);
        setBounds(100, 100, 778, 426);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(new PlaylistPlayer(),  BorderLayout.CENTER);
        button1.setSize(100,100);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Item> results = new DBManager().search(searchField.getText());
                if(!results.isEmpty()){
                    JOptionPane.showMessageDialog(null, "No Results Found");
                }
                else{
                    Artist artist = new Artist("X Ambassadors");
                    Song song1 = new Song("Renegades", "X Ambassadors", "Indie", "VHS", 195);
                    results.add(artist);
                    artist.addSong(song1);
                    results.add(song1);
                    results.add(new Song("Say you won't let go", "James Arthur", "Pop", "Back from the Edge", 195));
                    showSearchResults(results);
                }
            }
        });
    }
    private void  showSearchResults(List<Item> results){
        SearchResults searchResults = new SearchResults(results);
        searchResults.setVisible(true);
    }
}
