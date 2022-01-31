package Views;

import Models.Graph.MyDijkstraVertex;
import Models.Graph.MyGraph;
import Models.Graph.MyPathResult;
import Models.Graph.MyVertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class MainFrame extends JFrame {
    public double screenSizeWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public double screenSizeHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public double windowWidth = (double) 7/8 * screenSizeWidth;
    public double windowHeight = (double) 9/16 * windowWidth;
    private PanelInputs panelInputs;
    private PanelSide panelSide;
    private PanelGraph panelGraph;


    public MainFrame() {
        super("ALGO - Arbres et graphes");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setMinimumSize(new Dimension((int) windowWidth, (int) windowHeight));
        this.setPreferredSize(new Dimension((int) windowWidth, (int) windowHeight));
        this.getContentPane().setLayout(new GridBagLayout());
        this.pack();

        init();

    }

    private void init() {
        /*
        String stations[] = {"Madrid", "Londres", "Paris", "New-York"};
        Integer[][] matrix = {
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 0}
        };
        */

        Map<String, String[]> graphValues = new LinkedHashMap<>();
        graphValues.put("Annecy",               new String[]{"La Roche-sur-Foron"});
        graphValues.put("Annemasse",            new String[]{"Genève", "La Roche-sur-Foron"});
        graphValues.put("Bellegarde",           new String[]{"Meyrin", "Seyssel"});
        graphValues.put("Coppet",               new String[]{"Les Tuileries", "Nyon"});
        graphValues.put("Evian-les-Bains",      new String[]{"Thonon-les-Bains"});
        graphValues.put("Genève",               new String[]{"Annemasse", "Les Tuileries", "Meyrin"});
        graphValues.put("La Plaine",            new String[]{"Meyrin"});
        graphValues.put("La Roche-sur-Foron",   new String[]{"Annecy", "Annemasse", "St Gervais-les-Bains", "Thonon-les-Bains"});
        graphValues.put("Les Tuileries",        new String[]{"Coppet", "Genève"});
        graphValues.put("Meyrin",               new String[]{"Bellegarde", "Genève"});
        graphValues.put("Nyon",                 new String[]{});
        graphValues.put("Seyssel",              new String[]{});
        graphValues.put("St Gervais-les-Bains", new String[]{"Annecy", "La Roche-sur-Foron"});
        graphValues.put("Thonon-les-Bains",     new String[]{"Bellegarde", "La Roche-sur-Foron"});

        MyGraph<Integer> myGraph = new MyGraph();
        myGraph.setVerticesByMap(graphValues);

        panelInputs = new PanelInputs(graphValues);
        panelSide = new PanelSide();
        panelGraph = new PanelGraph(graphValues);

        panelInputs.startingCombobox.addActionListener(onClickSearch());
        panelInputs.endingCombobox.addActionListener(onClickSearch());

        treatmentDijkstra(panelInputs.startingCombobox.getSelectedItem().toString(), panelInputs.endingCombobox.getSelectedItem().toString());

        this.getContentPane().add(panelInputs, new GridBagConstraints(0, 0, 3, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.getContentPane().add(panelSide, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.getContentPane().add(panelGraph, new GridBagConstraints(1, 1, 2, 1, 2, 2, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));


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

        String path = "<html><body>- ";
        for(int i = dijkstraResult.getInvertedPath().size() -1 ; i >= 0 ; i--) {
            String nodeName = dijkstraResult.getInvertedPath().get(i).getName().toString();
            if(nodeName == startingStation || nodeName == endingStation)
                panelGraph.markNodeExtremity(nodeName);
            else
                panelGraph.markNode(nodeName);

            path += nodeName + "<br>- ";
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
        System.setProperty("org.graphstream.ui.renderer",
                "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        MainFrame frame = new MainFrame();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}