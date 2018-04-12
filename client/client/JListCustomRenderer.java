package client;

import Server.*;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

    public class JListCustomRenderer extends JFrame {

        private int width = 350;
        private int height = 200;
        private JList<Item> listItem;


        public JListCustomRenderer() {
            // add main panel
            add(createMainPanel());
            // set display
            setTitle("Search Results");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(width, height);
            setLocationRelativeTo(null);
            setVisible(true);
        }

        private JPanel createMainPanel() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            // create list item and set to scrollpane and add to panel
            panel.add(new JScrollPane(listItem = createListItem()),
                    BorderLayout.CENTER);
            return panel;
        }

        private JList<Item> createListItem() {
            // create List model
            DefaultListModel<Item> model = new DefaultListModel<>();
            // add item to model
            model.addElement(new Song("Renegades", "X Ambassadors", "Indie", "VHS", 195));
            model.addElement(new Song("Say you won't let go", "James Arthur", "Pop", "Back from the Edge", 195));

            // create JList with model
            JList<Item> list = new JList<Item>(model);
            // set cell renderer
            list.setCellRenderer(new ItemRenderer());
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            return list;
        }

    }

