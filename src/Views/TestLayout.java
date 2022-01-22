package Views;

import javax.swing.*;
import java.awt.*;

public class TestLayout extends JFrame {

    public TestLayout() {
        super("ALGO - Trees and graphs");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }

    private void init() {
        this.getContentPane().setLayout(new GridBagLayout());

        String stations[] = {"Madrid", "Londres", "Tokyo", "Berlin", "Paris", "New-York", "Dubai"};

        PanelInputs panelInputs = new PanelInputs(stations);
        PanelSide panelSide = new PanelSide();
        PanelGraph panelGraph = new PanelGraph();

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

        TestLayout frame = new TestLayout();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        while (true) {
            (view).repaint();

        }
    }
}