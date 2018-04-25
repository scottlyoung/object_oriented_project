package client;

import Server.Artist;
import Server.Item;
import Server.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ArtistController extends JFrame {
    public ArtistController(Artist artist) {
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
            rowPanel.setLayout(new BorderLayout());
            JLabel label = new JLabel(songs.get(i).toString());
            rowPanel.add(label, BorderLayout.LINE_START);
            JButton button = new JButton("Add");

            rowPanel.add(button, BorderLayout.LINE_END);
            Song item = songs.get(i);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PlaylistController playlistController = new PlaylistController(item);
                    playlistController.setVisible(true);
                }
            });
        }
    }
}
