package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UploadController extends JFrame{

    private JTextField songTitle;
    private JTextField SongGenre;
    private JTextField songArtist;
    private JTextField filepath;
    private JButton browseButton;
    private JButton uploadButton;
    private JButton cancelButton;
    private JPanel panel;
    private JFileChooser fileChooser;
    public UploadController(){
        this.add(panel);
        this.setVisible(true);
        setBounds(100, 100, 578, 326);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        filepath.setForeground(Color.GRAY);
        filepath.setText("Audio File");
        filepath.setEnabled(false);
        // file picker
        fileChooser = new JFileChooser();
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });
        uploadButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                uploadButtonActionPerformed(evt);
            }
        });

    }
    private void browseButtonActionPerformed(ActionEvent evt) {
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
                filepath.setText(fileChooser.getSelectedFile().getAbsolutePath());
                filepath.setForeground(Color.BLACK);
        } else {
            System.out.println("File access cancelled by user.");
        }
    }
    private void uploadButtonActionPerformed(ActionEvent evt) {
        //TODO call DBManager to handle adding file to the DB
    }
}
