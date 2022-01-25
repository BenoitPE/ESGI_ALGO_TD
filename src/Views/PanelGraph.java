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
    public MultiGraph graph = new MultiGraph("Main Graph");
    public MyGraph<Integer> g;

    public PanelGraph(String[] stations, Integer[][] matrix) {

        List<String> verticesNames = Arrays.asList(stations);
        g = new MyGraph<>();
        g.setVerticesByMatrix(verticesNames, matrix);

        for(int i=0; i< verticesNames.size(); i++) {
            graph.addNode(verticesNames.get(i));
            graph.getNode(verticesNames.get(i)).setAttribute("ui.label", graph.getNode(verticesNames.get(i)).getId());
        }

        for (int i = 0; i < g.getVertices().size(); i++) {
            MyVertex v = g.getVertices().get(i);
            for (int j = 0; j < v.getAdjacentVertices().size(); j++) {
                MyVertex adj = (MyVertex) v.getAdjacentVertices().get(j);
                graph.addEdge(v.getName() + "-" + adj.getName(), (String) v.getName(), (String) adj.getName(), true);
            }
        }

        graph.setStrict(false);
        graph.setAutoCreate(true);
        graph.setAttribute("ui.stylesheet", "url(./ressources/graph_style.css);");

        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        viewer.enableAutoLayout();
        View view = viewer.addDefaultView(false);
        setLayout(new BorderLayout());
        add((Component) view, BorderLayout.CENTER);
    }

}
