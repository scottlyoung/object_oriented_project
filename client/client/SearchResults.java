package client;

import Server.Account;
import Server.Artist;
import Server.Item;
import Server.Song;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class SearchResults extends JFrame {
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
//
//        List<Item> results = new ArrayList<Item>();
//        Artist artist = new Artist("X Ambassadors");
//        Song song1 = new Song("Renegades", "X Ambassadors", "Indie", "VHS", 195);
//        results.add(artist);
//        artist.addSong(song1);
//        results.add(song1);
//        results.add(new Song("Say you won't let go", "James Arthur", "Pop", "Back from the Edge", 195));
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    SearchResults frame = new SearchResults(results);
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    /**
     * Create the frame.
     */
    public SearchResults(List<Item> items, Account currentUser) {
        this.setTitle("Search Results");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 778, 426);
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 101, 742, 276);
        //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollPane);

        JPanel borderlaoutpanel = new JPanel();
        scrollPane.setViewportView(borderlaoutpanel);
        borderlaoutpanel.setLayout(new BorderLayout(0, 0));

        borderlaoutpanel.add(new JLabel(String.format("Found %d results", items.size())));

        JPanel mainPanel = new JPanel();
        borderlaoutpanel.add(mainPanel, BorderLayout.NORTH);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 1));
        mainPanel.setBackground(Color.gray);

        for(int i=0;i<items.size();i++) {
            JPanel rowPanel = new JPanel();
            rowPanel.setPreferredSize(new Dimension(300,100));
            rowPanel.setBackground(Color.white);
            mainPanel.add(rowPanel);
            rowPanel.setLayout(new BorderLayout());
            JLabel label = new JLabel(items.get(i).toString());
            rowPanel.add(label, BorderLayout.LINE_START);
            JButton button;
            if(items.get(i) instanceof Song){
                button = new JButton("Add");
            }
            else {
                button = new JButton("View");
            }
            rowPanel.add(button,  BorderLayout.LINE_END);
            Item item = items.get(i);

            button.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (item instanceof Song){
                        PlaylistController playlistController = new PlaylistController((Song) item, currentUser);
                        playlistController.setVisible(true);
                    }
                    else {
                        ArtistController artistController = new ArtistController((Artist) item, currentUser);
                        artistController.setVisible(true);
                    }
                    closeWindow();
                }
            });
        }


    }
    public void closeWindow(){
        this.setVisible(false);
    }
}
