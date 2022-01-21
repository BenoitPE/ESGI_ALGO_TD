import java.awt.*;
import javax.swing.*;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TestWindow
{
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Graph graph = new SingleGraph("Tutorial 1");

                Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
                View view = viewer.addDefaultView(false);
                graph.addNode("A");
                graph.addNode("B");
                graph.addNode("C");
                graph.addEdge("AB", "A", "B");
                graph.addEdge("BC", "B", "C");
                graph.addEdge("CA", "C", "A");

                graph.addAttribute("ui.quality");
                graph.addAttribute("ui.antialias");

                /*JFrame frame = new JFrame();
                frame.setLayout(new BorderLayout());
                //add(new JButton("test"), BorderLayout.NORTH);
                frame.add((Component) view, BorderLayout.CENTER);
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

                frame.setSize(320, 240);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);*/


                JPanel graphPanel = new JPanel();
                graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.LINE_AXIS));
                graphPanel.add((Component) view, BorderLayout.CENTER);

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



                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
                mainPanel.add(Box.createVerticalStrut(10));
                mainPanel.add(inputsPanel);
                mainPanel.add(graphPanel);

                JFrame frame = new JFrame("Foo");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(mainPanel);
                frame.pack();
                frame.setSize(800, 400);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}