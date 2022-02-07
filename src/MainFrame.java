import Models.Graph.*;
import Views.PanelGraph;
import Views.PanelInputs;
import Views.PanelSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class MainFrame extends JFrame {
    
    public double windowWidth = (double) 7 / 8 * Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public double windowHeight = (double) 9 / 16 * windowWidth;
    public Dimension windowDimension = new Dimension((int) windowWidth, (int) windowHeight);
    private PanelInputs panelInputs;
    private PanelSide panelSide;
    private PanelGraph panelGraph;


    public MainFrame() {
        super("PEGAZ - Final project");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(windowDimension);
        this.setMaximumSize(windowDimension);
        this.setPreferredSize(windowDimension);
        this.getContentPane().setLayout(new GridBagLayout());
        this.pack();

        Map<String, String[]> data = new LinkedHashMap<>();
        data.put("Annecy", new String[]{"La Roche-sur-Foron"});
        data.put("Annemasse", new String[]{"Genève", "La Roche-sur-Foron"});
        data.put("Bellegarde", new String[]{"Meyrin", "Seyssel"});
        data.put("Coppet", new String[]{"Les Tuileries", "Nyon"});
        data.put("Evian-les-Bains", new String[]{"Thonon-les-Bains"});
        data.put("Genève", new String[]{"Annemasse", "Les Tuileries", "Meyrin"});
        data.put("La Plaine", new String[]{"Meyrin"});
        data.put("La Roche-sur-Foron", new String[]{"Annecy", "Annemasse", "St Gervais-les-Bains", "Thonon-les-Bains"});
        data.put("Les Tuileries", new String[]{"Coppet", "Genève"});
        data.put("Meyrin", new String[]{"Bellegarde", "Genève"});
        data.put("Nyon", new String[]{});
        data.put("Seyssel", new String[]{});
        data.put("St Gervais-les-Bains", new String[]{"Annecy", "La Roche-sur-Foron"});
        data.put("Thonon-les-Bains", new String[]{"Bellegarde", "La Roche-sur-Foron"});

        init(data);
    }

    private void init(Map<String, String[]> data) {

        panelInputs = new PanelInputs(data);
        panelSide = new PanelSide();
        panelGraph = new PanelGraph(data);

        panelInputs.startingCombobox.addActionListener(verticesOnClick());
        panelInputs.endingCombobox.addActionListener(verticesOnClick());
        panelSide.cSelectAlgorithm.addActionListener(algoSelected());

        this.getContentPane().add(panelInputs, new GridBagConstraints(0, 0, 3, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.getContentPane().add(panelSide, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.getContentPane().add(panelGraph, new GridBagConstraints(1, 1, 2, 1, 2, 2, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        displaySidePanel();

    }

    private ActionListener verticesOnClick() {
        return a -> displaySidePanel();
    }

    private ActionListener algoSelected() {
        return a -> runChosenAlgo();
    }

    private void displaySidePanel() {
        panelGraph.resetColors();

        String from = (String) panelInputs.startingCombobox.getSelectedItem();
        String to = (String) panelInputs.endingCombobox.getSelectedItem();

        if (Objects.equals(from, to)) {
            panelSide.cleanInformations();
            panelSide.cSelectAlgorithm.setVisible(false);
            panelSide.lIsAccessible.setText("<html>The source and the<br>destination are the same");
        } else {
            List<Vertex> invertedPath = new LinkedList<>();
            boolean pathExists = Graph.depthCoursePoint(
                    panelGraph.structGraph.getVertex(from),
                    panelGraph.structGraph.getVertex(to),
                    invertedPath,
                    new LinkedList<>());

            if (pathExists) {
                panelSide.accessible(true, from, to);
                runChosenAlgo();
            } else {
                panelSide.accessible(false, from, to);
            }
        }
    }

    private void runChosenAlgo() {
        panelGraph.resetColors();
        String from = (String) panelInputs.startingCombobox.getSelectedItem();
        String to = (String) panelInputs.endingCombobox.getSelectedItem();

        switch (Objects.requireNonNull(panelSide.cSelectAlgorithm.getSelectedItem()).toString()) {
            case "Dijkstra" -> treatmentDijkstra(from, to);
            case "Bellman-Ford" -> treatmentBellmanFord(from, to);
            case "Modified depth course" -> treatmentModifiedDepthCourse(from, to);
            default -> System.err.println("Error: The algorithm '" + panelSide.cSelectAlgorithm.getSelectedItem().toString() + "' doesn't exist yet");
        }
    }

    public void treatmentDijkstra(String from, String to) {
        Result dijkstraResult = panelGraph.structGraph.getShortestPathDijkstra(
                panelGraph.structGraph.getVertex(from),
                panelGraph.structGraph.getVertex(to));

        List<String> edgesName = Result.getEdgesName(dijkstraResult.getInvertedPath());
        for (String s : edgesName) {
            panelGraph.markEdge(s);
        }

        StringBuilder path = new StringBuilder("<html><body>- ");
        for (int i = dijkstraResult.getInvertedPath().size() - 1; i >= 0; i--) {
            String name = dijkstraResult.getInvertedPath().get(i).getName();
            if (Objects.equals(name, from) || Objects.equals(name, to))
                panelGraph.markNodeExtremity(name);
            else
                panelGraph.markNode(name);
            path.append(name).append("<br>- ");
        }
        path = new StringBuilder(path.substring(0, path.length() - 3));

        String desc = "<html>Details: Dijkstra's algorithm is one of the most famous shortest path algorithm</html>";
        panelSide.lAlgoDesc.setText(desc);
        panelSide.lAlgoName.setText("Dijkstra's algorithm: ");
        panelSide.lAlgoPath.setText(path.toString());
        panelSide.lAlgoLength.setText("Shortest path length: " + (int) dijkstraResult.getLength() + " edge(s)");
        panelSide.lAlgoRuntime.setText("Runtime: " + dijkstraResult.getRuntime() + " nanoseconds");
    }

    public void treatmentBellmanFord(String from, String to) {
        Result dijkstraResult = panelGraph.structGraph.getShortestPathBellmanFord(
                panelGraph.structGraph.getVertex(from),
                panelGraph.structGraph.getVertex(to));

        List<String> edgesName = Result.getEdgesName(dijkstraResult.getInvertedPath());
        for (String s : edgesName) {
            panelGraph.markEdge(s);
        }

        StringBuilder path = new StringBuilder("<html><body>- ");
        for (int i = dijkstraResult.getInvertedPath().size() - 1; i >= 0; i--) {
            String name = dijkstraResult.getInvertedPath().get(i).getName();
            if (Objects.equals(name, from) || Objects.equals(name, to))
                panelGraph.markNodeExtremity(name);
            else
                panelGraph.markNode(name);

            path.append(name).append("<br>- ");
        }
        path = new StringBuilder(path.substring(0, path.length() - 3));

        String desc = "<html>Details: Bellman-Ford's algorithm is another algorithm</html>";
        panelSide.lAlgoDesc.setText(desc);
        panelSide.lAlgoName.setText("Bellman-Ford's algorithm: ");
        panelSide.lAlgoPath.setText(path.toString());
        panelSide.lAlgoLength.setText("Shortest path length: " + (int) dijkstraResult.getLength() + " edge(s)");
        panelSide.lAlgoRuntime.setText("Runtime: " + dijkstraResult.getRuntime() + " nanoseconds");
    }

    public void treatmentModifiedDepthCourse(String from, String to) {
        List<Vertex> invertedPath = new LinkedList<>();
        long startTimer = System.nanoTime();
        Graph.depthCoursePoint(
                panelGraph.structGraph.getVertex(from),
                panelGraph.structGraph.getVertex(to),
                invertedPath,
                new LinkedList<>());
        long runtime = System.nanoTime() - startTimer;

        //Mark edges
        if (invertedPath.size() >= 2) {
            StringBuilder edgeName = new StringBuilder(invertedPath.get(invertedPath.size() - 1).getName());
            for (int i = invertedPath.size() - 2; i >= 0; i--) {
                String v = invertedPath.get(i).getName();
                edgeName.append("-").append(v);
                panelGraph.markEdge(edgeName.toString());
                edgeName = new StringBuilder(v);
            }
        }

        //Mark nodes
        StringBuilder path = new StringBuilder("<html><body>- ");
        for (int i = invertedPath.size() - 1; i >= 0; i--) {
            String name = invertedPath.get(i).getName();
            if (Objects.equals(name, from) || Objects.equals(name, to))
                panelGraph.markNodeExtremity(name);
            else
                panelGraph.markNode(name);

            path.append(name).append("<br>- ");
        }
        path = new StringBuilder(path.substring(0, path.length() - 3));

        String desc = """
                <html>Details: Modified depth course's algorithm is NOT an algorithm that solves the shortest path problem.
                This algo executes a deep path from the starting vertex, if this algo manages to reach the destination vertex it returns the obtained path.
                Since the path found is not compared with the other possible paths, we cannot assert that the path obtained is the shortest path.
                It has been added to the list of implemented algorithms in order to show that finding a path has a much lower complexity than finding the shortest path</html>""";
        panelSide.lAlgoDesc.setText(desc);
        panelSide.lAlgoName.setText("Modified depth course's algorithm: ");
        panelSide.lAlgoPath.setText(path.toString());
        panelSide.lAlgoLength.setText("Shortest path length: " + (invertedPath.size() - 1) + " edge(s)");
        panelSide.lAlgoRuntime.setText("Runtime: " + (int) runtime + " nanoseconds");
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