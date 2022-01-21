import org.graphstream.graph.*;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.*;
import java.util.List;

public class MainWindow {
    public static void main(String args[]) {
        JFrame frame = new JFrame("frame");

        // définir la disposition du frame
        frame.setLayout(new FlowLayout());

        // tableau de chaînes contenant des langages
        String s1[] = { "Java", "PHP", "Python", "C++", "Ruby" };

        // créer une case à cocher
        JComboBox combobox = new JComboBox(s1);
        // créer des étiquettes
        JLabel l1 = new JLabel("Quel est votre langage prefere? ");
        JLabel l2 = new JLabel("[Java]");

        // définir la couleur du texte
        l2.setForeground(Color.blue);

        // créer un nouveau panneau
        JPanel p = new JPanel();
        // ajouter combobox et labels au panneau
        p.add(l1);
        p.add(combobox);
        p.add(l2);
        // ajouter le panneau au frame
        frame.add(p);
        // définir la taille du frame
        frame.setSize(400, 200);
        frame.show();

        showGraph();
    }

    public static void showGraph() {
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
        for (int i = 0; i < g.getVertices().size(); i++) {
            Vertex v = g.getVertices().get(i);
            for (int j = 0; j < v.getAdjacentVertices().size(); j++) {
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
