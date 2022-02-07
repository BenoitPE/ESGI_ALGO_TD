package Views;

import Models.Graph.Graph;
import Models.Graph.Vertex;
import org.graphstream.graph.implementations.MultiGraph;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

public class PanelGraph extends JPanel {
    public MultiGraph uiGraph = new MultiGraph("Graphstream's graph");
    public Graph structGraph;

    private final String markedNodeColor = "rgb(0, 100, 255);";

    public PanelGraph(Map<String, String[]> values) {

        structGraph = new Graph();
        structGraph.setVertices(values);

        String[] names = new String[values.size()];
        int i = 0;
        for (String name : values.keySet()) {
            names[i] = name;
            i++;
        }

        setVertices(names);
        setEdges();

        uiGraph.setStrict(false);
        uiGraph.setAutoCreate(true);
        uiGraph.setAttribute("ui.stylesheet", "url(style.css);");

        Viewer viewer = new Viewer(uiGraph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        viewer.enableAutoLayout();
        View view = viewer.addDefaultView(false);

        setLayout(new BorderLayout());
        Dimension dim = new Dimension(1200, 1200);
        setMinimumSize(dim);
        setMaximumSize(dim);
        setPreferredSize(dim);

        add((Component) view, BorderLayout.CENTER);
    }

    private void setEdges() {
        for (int i = 0; i < structGraph.getVertices().size(); i++) {
            Vertex v = structGraph.getVertices().get(i);
            for (int j = 0; j < v.getAdjacents().size(); j++) {
                Vertex adj = v.getAdjacents().get(j).getDest();
                uiGraph.addEdge(v.getName() + "-" + adj.getName(), v.getName(), adj.getName(), true);
            }
        }
    }

    private void setVertices(String[] verticesNames) {
        for (String verticesName : verticesNames) {
            uiGraph.addNode(verticesName);
            uiGraph.getNode(verticesName).setAttribute("ui.label", uiGraph.getNode(verticesName).getId());
        }
    }

    public void resetColors() {
        //reset color of edges
        for (int i = 0; i < uiGraph.getEdgeCount(); i++) {
            String defaultEdgeColor = "rgb(200,210,200);";
            uiGraph.getEdge(i).setAttribute("ui.style", "fill-color: " + defaultEdgeColor);
        }

        //reset color of nodes
        for (int i = 0; i < uiGraph.getNodeCount(); i++) {
            String defaultNodeColor = "#A3CB38;";
            uiGraph.getNode(i).setAttribute("ui.style", "text-color: black; fill-color: " + defaultNodeColor);
        }
    }

    public void markEdge(String edgeName) {
        uiGraph.getEdge(edgeName).setAttribute("ui.style", "fill-color: " + markedNodeColor);
    }

    public void markNode(String nodeName) {
        uiGraph.getNode(nodeName).setAttribute("ui.style", "text-color: black; fill-color: " + markedNodeColor);
    }

    public void markNodeExtremity(String nodeName) {
        String markedNodeExtremityColor = "#F79F1F;";
        uiGraph.getNode(nodeName).setAttribute("ui.style", "text-color: black; fill-color: " + markedNodeExtremityColor);
    }
}
