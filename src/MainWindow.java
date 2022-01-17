import org.graphstream.graph.*;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import java.util.*;

public class MainWindow {
    public static void main(String args[]) {
        //Data
        SingleGraph graph = new SingleGraph("ALGO - Trees and graphs");
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
        Graph<Integer> g = new Graph();
        g.setVerticesByMatrix(verticesNames, matrix);

        //View
        graph.setStrict(false);
        graph.setAutoCreate(true);
        System.setProperty("org.graphstream.ui", "swing");
        graph.setAttribute("ui.stylesheet", "url(graph_style.css);");
        graph.display();
        for (int i=0; i < g.getVertices().size(); i++) {
            Vertex v = g.getVertices().get(i);
            for (int j=0; j< v.getAdjacentVertices().size(); j++) {
                Vertex adj = (Vertex) v.getAdjacentVertices().get(j);
                graph.addEdge((String) v.getName() + (String) adj.getName(), (String) v.getName(), (String) adj.getName());


            }
        }

        for (Node node : graph) {
            node.setAttribute("ui.label", node.getId());
        }

        /*
        //Viewer viewer = graph.display();
        //View view = viewer.addDefaultView(false);

        JFrame f=new JFrame();//creating instance of JFrame

        JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height

        f.add(b);//adding button in JFrame
        //f.add((ViewPanel) viewer.addDefaultView(false), BorderLayout.CENTER);

        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
*/

    }
}
