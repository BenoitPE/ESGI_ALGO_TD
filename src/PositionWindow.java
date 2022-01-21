import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;

public class PositionWindow {

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JPanel graphPanel = new JPanel();
                graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.Y_AXIS));

                JPanel inputsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

                String stations[] = { "Madrid", "Londres", "Tokyo", "Berlin", "Paris", "New-York", "Dubai" };
                JComboBox startingCombobox = new JComboBox(stations);
                JComboBox endingCombobox = new JComboBox(stations);

                JLabel l1 = new JLabel("From: ");
                JLabel l2 = new JLabel("To: ");

                inputsPanel.add(l1);
                inputsPanel.add(startingCombobox);
                inputsPanel.add(l2);
                inputsPanel.add(endingCombobox);

                inputsPanel.add(new JButton("Calculer l'itinéraire"));
                inputsPanel.setMaximumSize(new Dimension(100, 100));
                inputsPanel.setPreferredSize(new Dimension(100, 100));

                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
                //mainPanel.add(Box.createVerticalStrut(10));
                mainPanel.add(inputsPanel);

                mainPanel.add(graphPanel);

                JFrame frame = new JFrame("Foo");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(mainPanel);
                frame.pack();
                frame.setSize(800, 400);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                System.out.println("frame.getWidth(): " + frame.getWidth() + ", frame.getHeight():" + frame.getHeight());
                System.out.println("inputsPanel.getWidth(): " + inputsPanel.getWidth() + ", inputsPanel.getHeight():" + inputsPanel.getHeight());
                System.out.println("graphPanel.getWidth(): " + graphPanel.getWidth() + ", graphPanel.getHeight():" + graphPanel.getHeight());

            }
        });
    }
}
