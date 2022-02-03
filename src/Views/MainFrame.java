package Views;

import Models.Graph.*;

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
        
        Map<String, String[]> data = new LinkedHashMap<>();
        data.put("Annecy",               new String[]{"La Roche-sur-Foron"});
        data.put("Annemasse",            new String[]{"Genève", "La Roche-sur-Foron"});
        data.put("Bellegarde",           new String[]{"Meyrin", "Seyssel"});
        data.put("Coppet",               new String[]{"Les Tuileries", "Nyon"});
        data.put("Evian-les-Bains",      new String[]{"Thonon-les-Bains"});
        data.put("Genève",               new String[]{"Annemasse", "Les Tuileries", "Meyrin"});
        data.put("La Plaine",            new String[]{"Meyrin"});
        data.put("La Roche-sur-Foron",   new String[]{"Annecy", "Annemasse", "St Gervais-les-Bains", "Thonon-les-Bains"});
        data.put("Les Tuileries",        new String[]{"Coppet", "Genève"});
        data.put("Meyrin",               new String[]{"Bellegarde", "Genève"});
        data.put("Nyon",                 new String[]{});
        data.put("Seyssel",              new String[]{});
        data.put("St Gervais-les-Bains", new String[]{"Annecy", "La Roche-sur-Foron"});
        data.put("Thonon-les-Bains",     new String[]{"Bellegarde", "La Roche-sur-Foron"});

        init(data);
    }

    private void init(Map<String, String[]> data) {

        panelInputs = new PanelInputs(data);
        panelSide = new PanelSide();
        panelGraph = new PanelGraph(data);

        panelInputs.startingCombobox.addActionListener(onClickSearch());
        panelInputs.endingCombobox.addActionListener(onClickSearch());
        panelSide.cSelectAlgorithm.addActionListener(algoSelected());

        this.getContentPane().add(panelInputs, new GridBagConstraints(0, 0, 3, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.getContentPane().add(panelSide, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.getContentPane().add(panelGraph, new GridBagConstraints(1, 1, 2, 1, 2, 2, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    }


    private ActionListener onClickSearch() {
        return a -> {
            panelGraph.resetColors();

            String from = (String) panelInputs.startingCombobox.getSelectedItem();
            String to = (String) panelInputs.endingCombobox.getSelectedItem();

            List<MyVertex> invertedPath = new LinkedList<>();
            boolean pathExists = MyGraph.depthCoursePoint(
                    panelGraph.structGraph.getVertex(from),
                    panelGraph.structGraph.getVertex(to),
                    invertedPath,
                    new LinkedList<>());

            if(pathExists) {
                panelSide.accessible(true, from, to);
                switch (panelSide.cSelectAlgorithm.getSelectedItem().toString()) {
                    case "Dijkstra":
                        treatmentDijkstra(from, to);
                        break;
                    case "Bellman-Ford":
                        treatmentBellmanFord(from, to);
                        break;
                }
            } else {
                panelSide.accessible(false, from, to);
            }
        };
    }

    private ActionListener algoSelected() {
        return a -> {
            String from = (String) panelInputs.startingCombobox.getSelectedItem();
            String to = (String) panelInputs.endingCombobox.getSelectedItem();

            switch (panelSide.cSelectAlgorithm.getSelectedItem().toString()) {
                case "Dijkstra":
                    treatmentDijkstra(from, to);
                    break;
                case "Bellman-Ford":
                    treatmentBellmanFord(from, to);
                    break;
            }
        };
    }

    public void treatmentDijkstra(String from, String to) {
        MyPathResult dijkstraResult = panelGraph.structGraph.getShortestPathDijkstra(
                panelGraph.structGraph.getVertex(from),
                panelGraph.structGraph.getVertex(to));

        List<String> edgesName = MyPathResult.getEdgesName(dijkstraResult.getInvertedPath());
        for (int i=0; i< edgesName.size(); i++) {
            panelGraph.markEdge(edgesName.get(i));
        }

        String path = "<html><body>- ";
        for(int i = dijkstraResult.getInvertedPath().size() -1 ; i >= 0 ; i--) {
            String nodeName = dijkstraResult.getInvertedPath().get(i).getName().toString();
            if(nodeName == from || nodeName == to)
                panelGraph.markNodeExtremity(nodeName);
            else
                panelGraph.markNode(nodeName);
            path += nodeName + "<br>- ";
        }
        path = path.substring(0, path.length() -3);

        String desc = "<html>Details: Dijkstra's algorithm is one of the most famous shortest path algorithm</html>";
        panelSide.lAlgoDesc.setText(desc);

        panelSide.lAlgoName.setText("Dijkstra's algorithm: ");
        panelSide.lAlgoPath.setText(path);
        panelSide.lAlgoLength.setText("Shortest path length: " + (int) dijkstraResult.getLength() + " edge(s)");
        panelSide.lAlgoRuntime.setText("Runtime: " + dijkstraResult.getRuntime() + " nanoseconds");
    }

    public void treatmentBellmanFord(String from, String to) {
        MyPathResult dijkstraResult = panelGraph.structGraph.getShortestPathBellmanFord(
                panelGraph.structGraph.getVertex(from),
                panelGraph.structGraph.getVertex(to));

        List<String> edgesName = MyPathResult.getEdgesName(dijkstraResult.getInvertedPath());
        for (int i=0; i< edgesName.size(); i++) {
            panelGraph.markEdge(edgesName.get(i));
        }

        String path = "<html><body>- ";
        for(int i = dijkstraResult.getInvertedPath().size() -1 ; i >= 0 ; i--) {
            String nodeName = dijkstraResult.getInvertedPath().get(i).getName().toString();
            if(nodeName == from || nodeName == to)
                panelGraph.markNodeExtremity(nodeName);
            else
                panelGraph.markNode(nodeName);

            path += nodeName + "<br>- ";
        }
        path = path.substring(0, path.length() -3);

        panelSide.lAlgoName.setText("Bellman-Ford's algorithm: ");
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