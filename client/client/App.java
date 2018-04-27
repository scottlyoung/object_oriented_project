package client;

import Server.Account;
import Server.DBManager;
import Server.Player;
import Server.Song;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame{
    private JPanel panel;
    private JButton loginButton;
    private JButton signupButton;
    private JTextField usernameField;
    private JTextField passwordField;
    public App(){
        SetupDBManager();


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                Account userTemp = DBManager.getInstance().getAccount(username);
                if (userTemp != null && userTemp.isPassValid(password)) {
                    if (userTemp.getIsAdmin()) {
                        goToUploadView();
                    } else {
                        goToHome(userTemp);
                    }
                }
                else {
                    System.out.print("Invalid Login\n");
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                if (DBManager.getInstance().addAccount(new Account(username, password))){
                    System.out.println("\nAccount Has Been Created, Try Logging In\n\n");
                }
                else{
                    System.out.println("\nAccount Name Already Exists, Can't Create Server.Account\n\n");
                }

            }
        });

        this.add(panel);
        this.setVisible(true);
        this.setSize(300,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void goToHome(Account currentUser){
        Home home = new Home(currentUser);
        this.setVisible(false);
        home.setVisible(true);
    }
    private void goToUploadView(){
        UploadController uploadController = new UploadController();
  
        uploadController.setVisible(true);
    }
    private void SetupDBManager(){
        DBManager db = DBManager.getInstance();

        // create a hard coded admin account (temporary untill proper admin account creation is implemented)
        Account accTemp = new Account("admin", "pass");
        accTemp.setIsAdmin(true);
        db.addAccount(accTemp);

        // create user account
        Account userTemp = new Account("user", "pass");
        userTemp.setIsAdmin(false);
        db.addAccount(userTemp);
        userTemp.addPlaylist("MyFav");


        Song a = new Song("Pain", "Three Days Grace", "Hard Rock", "One-X", 250);
        Song b = new Song("Riot", "Three Days Grace", "Hard Rock", "One-X", 250);
        Song c = new Song("Never Too Late", "Three Days Grace", "Hard Rock", "One-X", 250);
        Song d = new Song("Roundabout", "Yes", "Classic Rock", "Fragile (Deluxe Version)", 250);
        Song e = new Song("Lights", "Journey", "80's Rock", "Infinity", 250);
        Song f = new Song("Whos Crying Now", "Journey", "80's Rock", "Escape", 250);
        Song g = new Song("Smooth", "Santana", "Classic Rock", "Supernatural", 250);
        Song h = new Song("Who Are You", "The Who", "Classic Rock", "Who Are You", 250);
        Song i = new Song("Danger Zone", "Kenny Rogers", "Classic Rock", "Danger Zone", 250, "test_song.wav");
        db.addSong(a);
        db.addSong(b);
        db.addSong(c);
        db.addSong(d);
        db.addSong(e);
        db.addSong(f);
        db.addSong(g);
        db.addSong(h);
        db.addSong(i);

        userTemp.getPlaylist("MyFav").addSong(a);
        userTemp.getPlaylist("MyFav").addSong(b);
    }


}
