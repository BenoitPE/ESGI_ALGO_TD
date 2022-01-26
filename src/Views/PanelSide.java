package Views;

import javax.swing.*;
import java.awt.*;

public class PanelSide extends JPanel {
    public JLabel labelTitle;
    public JLabel labelAccess;
    public JLabel labelPathTitle;
    public JLabel labelPath;
    public JLabel label;

    public PanelSide() {
        setLayout(new BorderLayout());
        setBackground(new Color(250,250,250));
        labelTitle = new JLabel("Please select a travel");
        labelAccess = new JLabel("This label tells if this travel is possible");
        labelPathTitle = new JLabel("");
        labelPath = new JLabel("");
        label =  new JLabel("Data and Informations");

        add(labelTitle);
        add(labelAccess);
        add(labelPathTitle);
        add(labelPath);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //label.setHorizontalAlignment(SwingConstants.CENTER);
        //label.setVerticalAlignment(SwingConstants.TOP);
        //add(label,BorderLayout.CENTER);
    }
}
