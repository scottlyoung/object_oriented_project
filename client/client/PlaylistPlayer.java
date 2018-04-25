package client;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaylistPlayer extends JScrollPane {
    private JLabel label;
    private JTextField textField;
    private JButton button;

    public PlaylistPlayer(){

       this.setBounds(10, 101, 742, 276);

        JPanel borderlaoutpanel = new JPanel();
        this.setViewportView(borderlaoutpanel);
        borderlaoutpanel.setLayout(new BorderLayout(0, 0));

        JPanel mainPanel = new JPanel();
        borderlaoutpanel.add(mainPanel, BorderLayout.NORTH);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 1));
        mainPanel.setBackground(Color.gray);

        for(int i=0;i<3;i++) {
            JPanel rowPanel = new JPanel();
            rowPanel.setPreferredSize(new Dimension(300, 100));
            rowPanel.setBackground(Color.white);
            mainPanel.add(rowPanel);
            rowPanel.setLayout(new BorderLayout());
            JLabel label = new JLabel("xxx");
            rowPanel.add(label, BorderLayout.LINE_START);
            JButton button  = new JButton("Play");

            rowPanel.add(button,  BorderLayout.LINE_END);

            button.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(button.getText().compareToIgnoreCase("Play")==0){
                        button.setText("Stop");

                    }
                    else{
                        button.setText("Play");
                    }
                }
            });
        }
    }
}
