package client;

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



        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                System.out.println(username);
                //TODO pass username & password to DBManger
                if(username.compareToIgnoreCase("admin")==0) {
                    goToUploadView();
                }
                else{
                    goToHome();
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                System.out.println(username);
                // TODO pass username & password to DBManger
            }
        });

        this.add(panel);
        this.setVisible(true);
        this.setSize(300,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void goToHome(){
        Home home = new Home();
        this.setVisible(false);
        home.setVisible(true);
    }
    private void goToUploadView(){
        UploadController uploadController = new UploadController();
        this.setVisible(false);
        uploadController.setVisible(true);
    }


}
