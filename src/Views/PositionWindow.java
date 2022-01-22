package Views;

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
                JPanel panelMain, panelInputs, panelContent, panelSide, panelGraph;
                panelMain = new JPanel();
                panelInputs = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
                panelContent = new JPanel();
                panelSide = new JPanel();
                panelGraph = new JPanel();


                panelContent.add(panelSide);
                panelContent.add(panelGraph);
                panelMain.add(panelInputs);
                panelMain.add(panelContent);


                panelGraph.setLayout(new BoxLayout(panelGraph, BoxLayout.Y_AXIS));


                String stations[] = {"Madrid", "Londres", "Tokyo", "Berlin", "Paris", "New-York", "Dubai"};
                JComboBox startingCombobox = new JComboBox(stations);
                JComboBox endingCombobox = new JComboBox(stations);

                JLabel l1 = new JLabel("From: ");
                JLabel l2 = new JLabel("To: ");

                panelInputs.add(l1);
                panelInputs.add(startingCombobox);
                panelInputs.add(l2);
                panelInputs.add(endingCombobox);

                panelInputs.add(new JButton("Calculer l'itinéraire"));
                panelInputs.setMaximumSize(new Dimension(100, 100));
                panelInputs.setPreferredSize(new Dimension(100, 100));

                panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.X_AXIS));
                //panelMain.add(Box.createVerticalStrut(10));
                panelMain.add(panelInputs);

                panelMain.add(panelGraph);

                JFrame frame = new JFrame("Foo");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(panelMain);
                frame.pack();
                frame.setSize(800, 400);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
