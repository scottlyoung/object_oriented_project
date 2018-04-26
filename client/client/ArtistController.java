package client;

import Server.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ArtistController extends JFrame {
    public ArtistController(Artist artist, Account currentUser) {
        List<Song> songs = artist.getSongs();
        this.setTitle(artist.getName());
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

        for (int i = 0; i < songs.size(); i++) {
            JPanel rowPanel = new JPanel();
            rowPanel.setPreferredSize(new Dimension(300, 100));
            rowPanel.setBackground(Color.white);
            mainPanel.add(rowPanel);
            rowPanel.setLayout(new FlowLayout());
            JLabel label = new JLabel(songs.get(i).toString());
            rowPanel.add(label);
            JButton button = new JButton("Add");
            JButton playButton = new JButton("Play");
            rowPanel.add(playButton);
            rowPanel.add(button);
            Song item = songs.get(i);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PlaylistController playlistController = new PlaylistController(item, currentUser);
                    playlistController.setVisible(true);
                    closeWindow();
                }
            });
            playButton.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(playButton.getText().compareToIgnoreCase("Play")==0){
                        Player.getPlayer().playSong(item);
                        playButton.setText("Stop");
                    }
                    else{
                        Player.getPlayer().pauseSong();
                        playButton.setText("Play");
                    }


                }
            });
        }
    }
    public void closeWindow(){
        this.setVisible(false);
    }
}
