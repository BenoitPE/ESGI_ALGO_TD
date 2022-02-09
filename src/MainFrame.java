import Models.Graph.*;
import Views.DetailsFrame;
import Views.PanelGraph;
import Views.PanelInputs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class MainFrame extends JFrame {

    public double windowWidth = (double) 7 / 8 * Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public double windowHeight = (double) 9 / 16 * windowWidth;
    public Dimension windowDimension = new Dimension((int) windowWidth, (int) windowHeight);
    private final PanelInputs panelInputs;
    private final PanelGraph panelGraph;
    private List<Result> results;
    private boolean pathExists;


    public MainFrame() {
        super("PEGAZ - Final project");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*Map<String, String[]> data = new LinkedHashMap<>();
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
        data.put("Thonon-les-Bains", new String[]{"Bellegarde", "La Roche-sur-Foron"});*/
        Map<String, Map<String, Double>> map = new LinkedHashMap<>();
        map.put("Annecy", new HashMap<>() {{
            put("La Roche-sur-Foron", 33.0);
        }});
        map.put("Annemasse", new HashMap<>() {{
            put("Genève", 10.0);
            put("La Roche-sur-Foron", 19.0);
        }});
        map.put("Bellegarde", new HashMap<>() {{
            put("Meyrin", 32.0);
            put("Seyssel", 23.0);
        }});
        map.put("Coppet", new HashMap<>() {{
            put("Les Tuileries", 9.0);
            put("Nyon", 9.0);
        }});
        map.put("Evian-les-Bains", new HashMap<>() {{
            put("Thonon-les-Bains", 10.0);
        }});
        map.put("Genève", new HashMap<>() {{
            put("Annemasse", 10.0);
            put("Les Tuileries", 6.0);
            put("Meyrin", 7.0);
        }});
        map.put("La Plaine", new HashMap<>() {{
            put("Meyrin", 10.0);
        }});
        map.put("La Roche-sur-Foron", new HashMap<>() {{
            put("Annecy", 34.0);
            put("Annemasse", 22.0);
            put("St Gervais-les-Bains", 48.0);
            put("Thonon-les-Bains", 43.0);
        }});
        map.put("Les Tuileries", new HashMap<>() {{
            put("Coppet", 9.0);
            put("Genève", 7.0);
        }});
        map.put("Meyrin", new HashMap<>() {{
            put("Bellegarde", 32.0);
            put("Genève", 7.0);
        }});
        map.put("Nyon", new HashMap<>(){});
        map.put("Seyssel", new HashMap<>(){});
        map.put("St Gervais-les-Bains", new HashMap<>() {{
            put("Annecy", 87.0);
            put("La Roche-sur-Foron", 49.0);
        }});
        map.put("Thonon-les-Bains", new HashMap<>() {{
            put("Bellegarde", 86.0);
            put("La Roche-sur-Foron", 43.0);
        }});

        panelInputs = new PanelInputs(map);
        panelGraph = new PanelGraph(map);

        panelInputs.startingCombobox.addActionListener(verticesOnClick());
        panelInputs.endingCombobox.addActionListener(verticesOnClick());
        panelInputs.detailsButton.addActionListener(openDetails());

        this.setMinimumSize(windowDimension);
        this.setMaximumSize(windowDimension);
        this.setPreferredSize(windowDimension);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.getContentPane().setLayout(new GridBagLayout());
        this.pack();
        this.getContentPane().add(panelInputs, new GridBagConstraints(0, 0, 3, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.getContentPane().add(panelGraph, new GridBagConstraints(1, 1, 3, 1, 2, 2, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        runAlgo();
    }

    private ActionListener verticesOnClick() {
        return a -> runAlgo();
    }

    private ActionListener openDetails() {
        return a -> {
            DetailsFrame detailsFrame = new DetailsFrame(results);
            detailsFrame.accessible(pathExists);
        };
    }

    private void runAlgo() {
        panelGraph.resetColors();
        String from = (String) panelInputs.startingCombobox.getSelectedItem();
        String to = (String) panelInputs.endingCombobox.getSelectedItem();

        List<Vertex> invertedPath = new LinkedList<>();
        pathExists = Graph.modifiedDepthCourse(
                panelGraph.structGraph.getVertex(from),
                panelGraph.structGraph.getVertex(to),
                invertedPath,
                new LinkedList<>());

        if (pathExists) {
            results = new LinkedList<>();
            Result resultDisplay =  treatmentDijkstra(from, to);
            treatmentBellmanFord(from, to);
            treatmentModifiedDepthCourse(from, to);
        }
    }

    public Result treatmentDijkstra(String from, String to) {
        Result result = panelGraph.structGraph.getShortestPathDijkstra(
                panelGraph.structGraph.getVertex(from),
                panelGraph.structGraph.getVertex(to));

        List<String> edgesName = Result.getEdgesName(result.getInvertedPath());
        for (String s : edgesName) {
            panelGraph.markEdge(s);
        }

        for (int i = result.getInvertedPath().size() - 1; i >= 0; i--) {
            String name = result.getInvertedPath().get(i).getName();
            if (Objects.equals(name, from) || Objects.equals(name, to))
                panelGraph.markNodeExtremity(name);
            else
                panelGraph.markNode(name);
        }

        results.add(result);
        return result;
    }

    public void treatmentBellmanFord(String from, String to) {
        Result result = panelGraph.structGraph.getShortestPathBellmanFord(
                panelGraph.structGraph.getVertex(from),
                panelGraph.structGraph.getVertex(to));

        results.add(result);
    }

    public void treatmentModifiedDepthCourse(String from, String to) {
        /*List<Vertex> invertedPath = new LinkedList<>();
        Graph.modifiedDepthCourse(
                panelGraph.structGraph.getVertex(from),
                panelGraph.structGraph.getVertex(to),
                invertedPath,
                new LinkedList<>());*/

        Result result = panelGraph.structGraph.getPathModifiedDepthCourse(
                panelGraph.structGraph.getVertex(from),
                panelGraph.structGraph.getVertex(to));

        results.add(result);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        MainFrame mainFrame = new MainFrame();
    }
}