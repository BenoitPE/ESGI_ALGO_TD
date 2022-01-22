package Views;

import javax.swing.*;
import java.awt.*;

public class PanelSide extends JPanel {
    public PanelSide() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder());
        JLabel label =  new JLabel("SIDE");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label,BorderLayout.CENTER);
    }
}
