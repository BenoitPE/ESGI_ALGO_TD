package Views;

import Models.Graph.MyDijkstraVertex;
import Models.Graph.MyGraph;
import Models.Graph.MyPathResult;
import Models.Graph.MyVertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MainFrame extends JFrame {

    private PanelInputs panelInputs;
    private PanelSide panelSide;
    private PanelGraph panelGraph;


    public MainFrame() {
        super("ALGO - Arbres et graphes");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }

    private void init() {
        this.getContentPane().setLayout(new GridBagLayout());

        String stations[] = {"Madrid", "Londres", "Tokyo", "Berlin", "Paris", "New-York", "Dubai"};
        Integer[][] matrix = {
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 0},
                {1, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 1, 1},
                {1, 0, 0, 0, 0, 1, 1},
                {1, 0, 0, 0, 1, 1, 0}
        };

        /*String stations[] = {"Madrid", "Londres", "Paris", "New-York"};
        Integer[][] matrix = {
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 0}
        };
*/
        panelInputs = new PanelInputs(stations);
        panelSide = new PanelSide();
        panelGraph = new PanelGraph(stations, matrix);

        panelInputs.startingCombobox.addActionListener(onClickSearch());
        panelInputs.endingCombobox.addActionListener(onClickSearch());

        treatmentDijkstra(panelInputs.startingCombobox.getSelectedItem().toString(), panelInputs.endingCombobox.getSelectedItem().toString());

        this.getContentPane().add(panelInputs, new GridBagConstraints(0, 0, 3, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.getContentPane().add(panelSide, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.getContentPane().add(panelGraph, new GridBagConstraints(1, 1, 2, 1, 2, 2, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        this.setPreferredSize(new Dimension(1200, 800));
        this.pack();
    }


    private ActionListener onClickSearch() {
        return a -> {
            panelGraph.resetColors();

            String startingStation = (String) panelInputs.startingCombobox.getSelectedItem();
            String endingStation = (String) panelInputs.endingCombobox.getSelectedItem();

            List<MyVertex> invertedPath = new LinkedList<>();
            boolean pathExists = MyGraph.depthCoursePoint(
                    panelGraph.structGraph.getVertex(startingStation),
                    panelGraph.structGraph.getVertex(endingStation),
                    invertedPath,
                    new LinkedList<>());

            if(pathExists) {
                treatmentDijkstra(startingStation, endingStation);

            } else {
                panelSide.lAccess.setText("There is no path from "+ startingStation + " to " + endingStation);
            }
        };
    }

    public void treatmentDijkstra(String startingStation, String endingStation) {
        panelSide.lTitle.setText("You search a path from " + startingStation + " to " + endingStation);
        panelSide.lAccess.setText("Path exists from "+ startingStation + " to " + endingStation);

        MyPathResult dijkstraResult = panelGraph.structGraph.getShortestPathDijkstra(
                panelGraph.structGraph.getVertex(startingStation),
                panelGraph.structGraph.getVertex(endingStation));

        List<String> edgesName = MyPathResult.getEdgesName(dijkstraResult.getInvertedPath());
        for (int i=0; i< edgesName.size(); i++) {
            panelGraph.markEdge(edgesName.get(i));
        }


        String path = "";
        for(int i = dijkstraResult.getInvertedPath().size() -1 ; i >= 0 ; i--) {
            String nodeName = dijkstraResult.getInvertedPath().get(i).getName().toString();
            panelGraph.markNode(nodeName);
            path += nodeName + " -> ";
        }
        path = path.substring(0, path.length() -3);

        panelSide.lAlgoName.setText("Dijkstra's algorithm: ");
        panelSide.lAlgoPath.setText(path);
        panelSide.lAlgoLength.setText("Shortest path length: " + (int) dijkstraResult.getLength() + " edge(s)");
        panelSide.lAlgoRuntime.setText("Runtime: " + dijkstraResult.getRuntime() + " nanoseconds");


    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        MainFrame frame = new MainFrame();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}