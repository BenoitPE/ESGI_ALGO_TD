package Views;

import Models.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class PanelGraph extends JPanel {

    public SingleGraph graph = new SingleGraph("Test graph");


    public PanelGraph() {
        //setLayout(new BorderLayout());
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        //setBorder(BorderFactory.createEtchedBorder());
        /*JLabel label =  new JLabel("GRAPH");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label,BorderLayout.CENTER);*/

        /*
        Models.Graph graph = new SingleGraph("Tutorial 1");

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");

        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        View view = viewer.addDefaultView(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add((Component) view, BorderLayout.CENTER);
        */

        Integer[][] matrix = {
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 0},
                {1, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 1, 1},
                {1, 0, 0, 0, 0, 1, 1},
                {1, 0, 0, 0, 1, 1, 0}
        };
        List<String> verticesNames = Arrays.asList("Madrid", "Londres", "Tokyo", "Berlin", "Paris", "New-York", "Dubai");
        MyGraph<Integer> g = new MyGraph<>();
        g.setVerticesByMatrix(verticesNames, matrix);

        //View
        graph.setStrict(false);
        graph.setAutoCreate(true);
        //System.setProperty("org.graphstream.ui", "swing");
        graph.setAttribute("ui.stylesheet", "url(./ressources/graph_style.css);");
        graph.display();
        for (int i = 0; i < g.getVertices().size(); i++) {
            MyVertex v = g.getVertices().get(i);
            for (int j = 0; j < v.getAdjacentVertices().size(); j++) {
                MyVertex adj = (MyVertex) v.getAdjacentVertices().get(j);
                graph.addEdge((String) v.getName() + (String) adj.getName(), (String) v.getName(), (String) adj.getName());


            }
        }

        for (Node node : graph) {
            node.setAttribute("ui.label", node.getId());
        }

        //graph.addAttribute("ui.quality");
        //graph.addAttribute("ui.antialias");
        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        View view = viewer.addDefaultView(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add((Component) view, BorderLayout.CENTER);

    }

}
