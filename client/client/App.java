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
        String username = usernameField.getText();
        String password = passwordField.getText();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO pass username & password to DBManger
                    goToHome();
            }
        });



        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO pass username & password to DBManger
                goToHome();
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


}
