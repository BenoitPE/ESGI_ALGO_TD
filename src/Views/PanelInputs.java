package Views;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class PanelInputs extends JPanel {
    public JComboBox startingCombobox;
    public JComboBox endingCombobox;
    public JButton detailsButton;

    public PanelInputs(Map<String, Map<String, Double>> values) {

        String[] stations = new String[values.size()];
        int i = 0;
        for (String from : values.keySet()) {
            stations[i] = from;
            i++;
        }

        JLabel labelFrom = new JLabel("From: ");
        JLabel labelTo = new JLabel("To: ");

        startingCombobox = new JComboBox(stations);
        endingCombobox = new JComboBox(stations);
        endingCombobox.setSelectedIndex(2);

        detailsButton = new JButton("Details");

        add(labelFrom);
        add(startingCombobox);
        add(labelTo);
        add(endingCombobox);
        add(detailsButton);

        setMinimumSize(new Dimension(500, 50));
        setMaximumSize(new Dimension(100000, 50));
        setPreferredSize(new Dimension(500, 50));

        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        setBackground(new Color(240, 240, 240));
    }

}
