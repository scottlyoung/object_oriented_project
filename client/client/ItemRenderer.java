package client;
import server.Item;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class ItemRenderer extends JPanel implements ListCellRenderer<Item> {


    private JTextArea itemDetails = new JTextArea();
    private JButton button = new JButton("Add To Playlist");
    public ItemRenderer() {

        setLayout(new BorderLayout(5, 5));
        JPanel panelText = new JPanel(new GridLayout(0, 2));
        Border outsideBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray);
        Border insideBorder = BorderFactory.createEmptyBorder(3, 3, 3, 3);
        CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(outsideBorder, insideBorder);
        panelText.setBorder(compoundBorder);
        panelText.add(itemDetails);
        panelText.add(button, BorderLayout.EAST);
        add(panelText, BorderLayout.CENTER);

    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Item> list,
                                                  Item item, int index, boolean isSelected, boolean cellHasFocus) {
        itemDetails.setText(item.toString());
        itemDetails.setForeground(Color.black);
        itemDetails.setOpaque(false);
        if (isSelected) {

            button.setBackground(list.getSelectionBackground());
        }
        else {
            button.setBackground(list.getBackground());
        }

        return this;
    }
}
