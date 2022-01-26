package Views;
import Models.MyGraph;
import Models.MyVertex;
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
    private String defaultColor = "rgb(0, 0, 0);";
    private String markedColor = "rgb(0, 100, 255);";

    public PanelGraph(String[] stations, Integer[][] matrix) {

        List<String> verticesNames = Arrays.asList(stations);
        structGraph = new MyGraph<>();
        structGraph.setVerticesByMatrix(verticesNames, matrix);

        setVertices(verticesNames);
        setEdges();

        uiGraph.setStrict(false);
        uiGraph.setAutoCreate(true);
        uiGraph.setAttribute("ui.stylesheet", "url(./ressources/graph_style.css);");

        Viewer viewer = new Viewer(uiGraph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        viewer.enableAutoLayout();
        View view = viewer.addDefaultView(false);
        setLayout(new BorderLayout());
        add((Component) view, BorderLayout.CENTER);
    }

    private void setEdges() {
        for (int i = 0; i < structGraph.getVertices().size(); i++) {
            MyVertex v = structGraph.getVertices().get(i);
            for (int j = 0; j < v.getAdjacentVertices().size(); j++) {
                MyVertex adj = (MyVertex) v.getAdjacentVertices().get(j);
                uiGraph.addEdge(v.getName() + "-" + adj.getName(), (String) v.getName(), (String) adj.getName(), true);
            }
        }
    }
    
    private void setVertices(List<String> verticesNames) {
        for(int i=0; i< verticesNames.size(); i++) {
            uiGraph.addNode(verticesNames.get(i));
            uiGraph.getNode(verticesNames.get(i)).setAttribute("ui.label", uiGraph.getNode(verticesNames.get(i)).getId());
        }
    }

    public void resetColors() {
        //reset color of edges
        for (int i =0; i< uiGraph.getEdgeCount(); i++) {
            uiGraph.getEdge(i).setAttribute("ui.style", "fill-color: " + defaultColor);
            //uiGraph.getEdge(i).removeAttribute("ui.style");
        }

        //reset color of nodes
        for (int i =0; i< uiGraph.getNodeCount(); i++) {
            uiGraph.getNode(i).setAttribute("ui.style", "text-color: " + defaultColor+ "fill-color: " + defaultColor);
            //uiGraph.getEdge(i).removeAttribute("ui.style");
        }
    }

    public void markEdge(String edgeName) {
        uiGraph.getEdge(edgeName).setAttribute("ui.style", "fill-color: " + markedColor);
    }

    public void markNode(String nodeName) {
        uiGraph.getNode(nodeName).setAttribute("ui.style", "text-color: " + markedColor +" fill-color: " + markedColor);
    }

}
