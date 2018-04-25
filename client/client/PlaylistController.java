package client;

import Server.Item;
import Server.Playlist;
import Server.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// this class manages adding a song to a list
public class PlaylistController  extends JFrame {

    public PlaylistController(Song item){
       //TODO Call DBManager to get playlists for the current user
        List<Playlist> playlists = new ArrayList<Playlist>();
        playlists.add( new Playlist("Mood Booster"));
        playlists.add( new Playlist("Workout"));
        System.out.println(item.toString());
        this.setTitle("Add To Playlist");
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


        JPanel mainPanel = new JPanel();
        borderlaoutpanel.add(mainPanel, BorderLayout.NORTH);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 1));
        mainPanel.setBackground(Color.gray);

        for(int i=0;i<playlists.size();i++) {
            JPanel rowPanel = new JPanel();
            rowPanel.setPreferredSize(new Dimension(300,100));
            rowPanel.setBackground(Color.white);
            mainPanel.add(rowPanel);
            rowPanel.setLayout(new BorderLayout());
            JLabel label = new JLabel(playlists.get(i).getName());
            rowPanel.add(label, BorderLayout.LINE_START);
            JButton button = new JButton("Add to Playlist");
            rowPanel.add(button,  BorderLayout.LINE_END);
           int listId = i;

            button.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                        playlists.get(listId).addSong( item);

                }
            });
        }
    }
}
