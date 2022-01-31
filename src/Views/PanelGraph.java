package Views;
import Models.Graph.MyGraph;
import Models.Graph.MyOrientedEdge;
import Models.Graph.MyVertex;
import org.graphstream.graph.implementations.MultiGraph;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

public class PanelGraph extends JPanel {
    public MultiGraph uiGraph = new MultiGraph("Main Graph");
    public MyGraph<Integer> structGraph;

    private String defaultNodeColor = "#A3CB38;";
    private String defaultEdgeColor = "rgb(200,210,200);";

    private String markedNodeColor = "rgb(0, 100, 255);";
    private String markedNodeExtremityColor = "#F79F1F;";
    private String markedEdgeColor = markedNodeColor;

    public PanelGraph(Map<String, String[]> values) {

        structGraph = new MyGraph<>();
        structGraph.setVerticesByMap(values);

        String[] verticesName = new String[values.size()];
        int i=0;
        for (String verticeName : values.keySet()) {
            verticesName[i] = verticeName;
            i++;
        }

        setVertices(verticesName);
        setEdges();

        uiGraph.setStrict(false);
        uiGraph.setAutoCreate(true);
        uiGraph.setAttribute("ui.stylesheet", "url(./ressources/graph_style.css);");

        Viewer viewer = new Viewer(uiGraph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        viewer.enableAutoLayout();
        View view = viewer.addDefaultView(false);
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(1200,1200));
        setMinimumSize(new Dimension(1200,1200));
        setPreferredSize(new Dimension(1200,1200));
        add((Component) view, BorderLayout.CENTER);
    }

    private void setEdges() {
        for (int i = 0; i < structGraph.getVertices().size(); i++) {
            MyVertex v = structGraph.getVertices().get(i);
            for (int j = 0; j < v.getAdjacentVertices().size(); j++) {
                MyVertex adj = ((MyOrientedEdge) v.getAdjacentVertices().get(j)).getDestination();
                uiGraph.addEdge(v.getName() + "-" + adj.getName(), (String) v.getName(), (String) adj.getName(), true);
            }
        }
    }
    
    private void setVertices(String[] verticesNames) {
        for(int i=0; i< verticesNames.length; i++) {
            uiGraph.addNode(verticesNames[i]);
            uiGraph.getNode(verticesNames[i]).setAttribute("ui.label", uiGraph.getNode(verticesNames[i]).getId());
        }
    }

    public void resetColors() {
        //reset color of edges
        for (int i =0; i< uiGraph.getEdgeCount(); i++) {
            uiGraph.getEdge(i).setAttribute("ui.style", "fill-color: " + defaultEdgeColor);
        }

        //reset color of nodes
        for (int i =0; i< uiGraph.getNodeCount(); i++) {
            uiGraph.getNode(i).setAttribute("ui.style", "text-color: black; fill-color: " + defaultNodeColor);
        }
    }

    public void markEdge(String edgeName) {
        uiGraph.getEdge(edgeName).setAttribute("ui.style", "fill-color: " + markedEdgeColor);
    }

    public void markNode(String nodeName) {
        uiGraph.getNode(nodeName).setAttribute("ui.style", "text-color: black; fill-color: " + markedNodeColor);
    }

    public void markNodeExtremity(String nodeName) {
        uiGraph.getNode(nodeName).setAttribute("ui.style", "text-color: black; fill-color: " + markedNodeExtremityColor);
    }
}
