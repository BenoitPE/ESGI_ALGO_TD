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

        PanelInputs panelInputs = new PanelInputs(stations);
        PanelSide panelSide = new PanelSide();
        PanelGraph panelGraph = new PanelGraph(stations, matrix);

        ActionListener comboBoxListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(panelInputs.startingCombobox.getSelectedItem());
                System.out.println(panelInputs.endingCombobox.getSelectedItem());

                List<MyVertex> invertedPath = new LinkedList<>();
                System.out.println("----------------------");
                System.out.println(MyGraph.depthCoursePoint(
                        panelGraph.g.getVertex((String) panelInputs.startingCombobox.getSelectedItem()),
                        panelGraph.g.getVertex((String) panelInputs.endingCombobox.getSelectedItem()),
                        invertedPath,
                        new LinkedList<>()));
                System.out.print("Chemin inversé: ");
                invertedPath.forEach(v -> System.out.print(v.getName() + ", "));
                System.out.println();
            }
        };

        panelInputs.startingCombobox.addActionListener(comboBoxListener);
        panelInputs.endingCombobox.addActionListener(comboBoxListener);

        this.getContentPane().add(panelInputs, new GridBagConstraints(0, 0, 3, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        this.getContentPane().add(panelSide, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        this.getContentPane().add(panelGraph, new GridBagConstraints(1, 1, 2, 1, 2, 2, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        this.setPreferredSize(new Dimension(1600, 900));
        this.pack();
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