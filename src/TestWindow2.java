import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TestWindow2 extends JFrame {

    private JLabel labelDeparture = new JLabel("From: ");
    private JLabel labelArrival = new JLabel("To: ");
    private JTextField textDeparture = new JTextField(20);
    private JTextField fieldArrival = new JTextField(20);
    private JButton buttonLogin = new JButton("Search");

    public TestWindow2() {
        super("JPanel Demo Program");

        // create a new panel with GridBagLayout manager
        JPanel newPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(labelDeparture, constraints);

        constraints.gridx = 1;
        newPanel.add(textDeparture, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(labelArrival, constraints);

        constraints.gridx = 1;
        newPanel.add(fieldArrival, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonLogin, constraints);

        // set border for the panel
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Search Panel"));

        // add the panel to this frame
        add(newPanel);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // set look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestWindow2().setVisible(true);
            }
        });
    }
}