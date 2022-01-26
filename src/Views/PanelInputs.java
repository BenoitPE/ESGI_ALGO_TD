package Views;

import javax.swing.*;
import java.awt.*;

public class PanelInputs extends JPanel {
    public JComboBox startingCombobox;
    public JComboBox endingCombobox;

    public PanelInputs(String[] stations) {

        JLabel labelFrom = new JLabel("From: ");
        JLabel labelTo = new JLabel("To: ");
        startingCombobox = new JComboBox(stations);
        endingCombobox = new JComboBox(stations);

        add(labelFrom);
        add(startingCombobox);
        add(labelTo);
        add(endingCombobox);

        setMinimumSize(new Dimension(500,50));
        setMaximumSize(new Dimension(100000,50));
        setPreferredSize(new Dimension(500,50));

        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        setBackground(new Color(240,240,240));
    }

}
