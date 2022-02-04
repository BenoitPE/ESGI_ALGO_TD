package Views;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelSide extends JPanel {
    public JComboBox cSelectAlgorithm;

    public JLabel lIsAccessible;
    public JLabel lSelection;
    public JLabel lAlgoName;
    public JLabel lAlgoRuntime;
    public JLabel lAlgoPathTitle;
    public JLabel lAlgoPath;
    public JLabel lAlgoLength;
    public JLabel lAlgoTotalVerticesTraveled;
    public JLabel lAlgoComplexity;
    public JLabel lAlgoDesc;

    public PanelSide() {
        Color bgColor = new Color(250, 250, 250);
        //bgColor = Color.blue;
        setBackground(bgColor);

        lIsAccessible = new JLabel("");
        lIsAccessible.setBorder(new EmptyBorder(30, 0, 0, 0));
        add(lIsAccessible);

        lSelection = new JLabel("");
        lSelection.setBorder(new EmptyBorder(10, 0, 0, 0));
        add(lSelection);

        String[] algorithms = {"Dijkstra", "Bellman-Ford", "Modified depth course"};
        cSelectAlgorithm = new JComboBox(algorithms);
        cSelectAlgorithm.setVisible(false);
        JPanel panelComboBox = new JPanel();
        panelComboBox.add(cSelectAlgorithm);
        panelComboBox.setOpaque(false);
        panelComboBox.setMaximumSize(new Dimension(10000, 30));
        add(panelComboBox);

        lAlgoName = new JLabel("");
        lAlgoName.setBorder(new EmptyBorder(10, 0, 0, 0));
        add(lAlgoName);

        lAlgoRuntime = new JLabel("");
        add(lAlgoRuntime);

        lAlgoLength = new JLabel("");
        add(lAlgoLength);

        lAlgoTotalVerticesTraveled = new JLabel("");
        add(lAlgoTotalVerticesTraveled);

        lAlgoComplexity = new JLabel("");
        add(lAlgoComplexity);

        lAlgoPathTitle = new JLabel("");
        lAlgoPathTitle.setBorder(new EmptyBorder(10, 0, 0, 0));
        add(lAlgoPathTitle);

        lAlgoPath = new JLabel("");
        add(lAlgoPath);

        lAlgoDesc = new JLabel("");
        lAlgoDesc.setBorder(new EmptyBorder(10, 0, 0, 0));
        add(lAlgoDesc);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Dimension dim = new Dimension(500, 1000);
        setMaximumSize(dim);
        setMinimumSize(dim);
        setPreferredSize(dim);

        setFonts();
    }

    public void setFonts() {
        Font fVerdana = new Font("Verdana", Font.PLAIN, 14);

        lIsAccessible.setFont(fVerdana);
        lSelection.setFont(fVerdana);
        lAlgoName.setFont(fVerdana);
        lAlgoRuntime.setFont(fVerdana);
        lAlgoLength.setFont(fVerdana);
        lAlgoPathTitle.setFont(fVerdana);
        lAlgoPath.setFont(fVerdana);
        lAlgoTotalVerticesTraveled.setFont(fVerdana);
        lAlgoComplexity.setFont(fVerdana);
        lAlgoDesc.setFont(fVerdana);
    }

    public void cleanInformations() {
        lSelection.setText("");
        lAlgoName.setText("");
        lAlgoRuntime.setText("");
        lAlgoPath.setText("");
        lAlgoPath.setText("");
        lAlgoLength.setText("");
        lAlgoTotalVerticesTraveled.setText("");
        lAlgoComplexity.setText("");
        lAlgoDesc.setText("");
        lAlgoPathTitle.setText("");
    }

    public void accessible(boolean access, String startingStation, String endingStation) {
        if (access) {
            cSelectAlgorithm.setVisible(true);
            lIsAccessible.setText("<html>Path exists <br>From " + startingStation + "<br>To " + endingStation + "!");
            lAlgoPathTitle.setText("Passage through: ");
            lSelection.setText("Please select an algorithm: ");
        } else {
            cleanInformations();
            cSelectAlgorithm.setVisible(false);
            lIsAccessible.setText("<html>There is no path <br>From " + startingStation + "<br>To " + endingStation);
        }
    }


}
