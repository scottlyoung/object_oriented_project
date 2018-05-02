package client;

import Server.Account;
import Server.Player;
import Server.Playlist;
import Server.Song;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PlaylistPlayer extends JScrollPane {
    private JLabel label;
    private JTextField textField;
    private JButton button;
    private List<Playlist> playlist;

    public PlaylistPlayer(Account currentUser){
        System.out.println(currentUser.listPlaylists().size());
        playlist = currentUser.getPlaylists();
       this.setBounds(10, 101, 742, 276);

        JPanel borderlaoutpanel = new JPanel();
        this.setViewportView(borderlaoutpanel);
        borderlaoutpanel.setLayout(new BorderLayout(0, 0));

        JPanel mainPanel = new JPanel();
        borderlaoutpanel.add(mainPanel, BorderLayout.NORTH);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 1));
        mainPanel.setBackground(Color.gray);

        for(int i=0;i<playlist.size();i++) {
            JPanel rowPanel = new JPanel();
            rowPanel.setPreferredSize(new Dimension(300, 100));
            rowPanel.setBackground(Color.white);
            mainPanel.add(rowPanel);
            rowPanel.setLayout(new BorderLayout());
            JLabel label = new JLabel("<html> Playlist: "+playlist.get(i).getName()+"<br>"+playlistToString(playlist.get(i))+"</br></html>");
            rowPanel.add(label, BorderLayout.LINE_START);
            JButton button  = new JButton("Play");

            rowPanel.add(button,  BorderLayout.LINE_END);
            Playlist temp_playlist = playlist.get(i);

            button.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(button.getText().compareToIgnoreCase("Play")==0){
                        Player.getPlayer().playPlaylist(temp_playlist);
                        button.setText("Stop");
                    }
                    else{
                        Player.getPlayer().pauseSong();
                        button.setText("Play");
                    }
                }
            });
        }
    }
    private String playlistToString(Playlist playlist){
        String str = "Songs: ";
        for(Song s : playlist.getSongs()){
            str= (str + s.getName() + ", ");
        }
        return  str.substring(0, str.length() - 2);
    }

}
