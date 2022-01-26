package Views;

import Models.MyGraph;
import Models.MyVertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        /*String stations[] = {"Madrid", "Londres", "Tokyo", "Berlin", "Paris", "New-York", "Dubai"};
        Integer[][] matrix = {
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 0},
                {1, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 1, 1},
                {1, 0, 0, 0, 0, 1, 1},
                {1, 0, 0, 0, 1, 1, 0}
        };*/


        String stations[] = {"Madrid", "Londres", "Paris", "New-York"};
        Integer[][] matrix = {
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 0}
        };

        panelInputs = new PanelInputs(stations);
        panelSide = new PanelSide();
        panelGraph = new PanelGraph(stations, matrix);

        panelInputs.startingCombobox.addActionListener(onClickSearch());
        panelInputs.endingCombobox.addActionListener(onClickSearch());


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

            panelSide.labelTitle.setText("From " + startingStation + " to " + endingStation);
            if(pathExists && invertedPath.size() > 0) {
                panelSide.labelAccess.setText("Path exists from "+ startingStation + " to " + endingStation);

                String startingVertex;
                String endingVertex = endingStation;
                String textLabel = "<html><body>";
                panelGraph.markNode(endingVertex);

                for (int i=0; i< invertedPath.size(); i++) {
                    startingVertex = (String) invertedPath.get(i).getName();
                    panelGraph.markEdge(startingVertex + "-" + endingVertex);
                    panelGraph.markNode(startingVertex);
                    textLabel += startingVertex + "-" + endingVertex + "<br>";
                    endingVertex = (String) invertedPath.get(i).getName();
                }
                panelSide.labelPath.setText(textLabel);

            } else {
                panelSide.labelAccess.setText("There is no path from "+ startingStation + " to " + endingStation);
            }
        };
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