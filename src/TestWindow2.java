import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class TestWindow2 extends JFrame {

    String stations[] = { "Madrid", "Londres", "Tokyo", "Berlin", "Paris", "New-York", "Dubai" };
    private JLabel labelDeparture = new JLabel("From: ");
    private JLabel labelArrival = new JLabel("To: ");
    JComboBox comboBoxDeparture = new JComboBox(stations);
    JComboBox comboBoxArrival = new JComboBox(stations);
    JLabel labelResult = new JLabel(" ");

    public TestWindow2() {
        super("Input Frame");

        // create a new panel with GridBagLayout manager
        JPanel inputPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        inputPanel.add(labelDeparture, constraints);

        constraints.gridx = 1;
        comboBoxDeparture.addActionListener(event -> actionPerformed(event));
        inputPanel.add(comboBoxDeparture, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        inputPanel.add(labelArrival, constraints);

        constraints.gridx = 1;
        comboBoxArrival.addActionListener(event -> actionPerformed(event));
        inputPanel.add(comboBoxArrival, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        inputPanel.add(labelResult, constraints);

        // set border for the panel
        inputPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Search"));

        // add the panel to this frame
        add(inputPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public void actionPerformed(ActionEvent e) {
        labelResult.setText(String.format("%s - %s",
                comboBoxDeparture.getSelectedItem().toString(),
                comboBoxArrival.getSelectedItem().toString()));

    }
}