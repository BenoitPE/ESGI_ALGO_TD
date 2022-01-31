package Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PanelSide extends JPanel {
    public JLabel lTitle;
    public JLabel lAccess;
    public JLabel lTempPathTitle;
    public JLabel lTempPath;
    
    /*
    Point A vers point B :
    Un chemin existe ? Oui/Non

    Si un chemin existe:
    Algo de Dijkstra (par exemple):
    Temps d'execution :....
    Chemin: ....
    Distance : .... (nombre de noeuds du chemin)
    Nombre total de noeuds parcourus: ....
    Complexité: O(...)
    */
    
    public JLabel lAlgoName;
    public JLabel lAlgoRuntime;
    public JLabel lAlgoPath;
    public JLabel lAlgoLength;
    public JLabel lAlgoTotalVerticesTraveled;
    public JLabel lAlgoComplexity;

    public PanelSide() {

        Font fVerdana = new Font("Verdana", Font.PLAIN, 14);
        setBackground(new Color(250,250,250));

        //Temporary
        lTitle = new JLabel("Please select a travel");
        lAccess = new JLabel("");
        lTempPathTitle = new JLabel("");
        lTempPath = new JLabel("");

        //1st algo
        lAlgoName = new JLabel("");
        lAlgoRuntime = new JLabel("");
        lAlgoPath = new JLabel("");
        lAlgoLength = new JLabel("");
        lAlgoTotalVerticesTraveled = new JLabel("");
        lAlgoComplexity = new JLabel("");

        //Set fonts to label
        lTitle.setFont(fVerdana);
        lAccess.setFont(fVerdana);
        lAlgoName.setFont(fVerdana);
        lAlgoRuntime.setFont(fVerdana);
        lAlgoPath.setFont(fVerdana);
        lAlgoLength.setFont(fVerdana);
        lAlgoTotalVerticesTraveled.setFont(fVerdana);
        lAlgoComplexity.setFont(fVerdana);

        //Temporary adding informations
        add(lTitle);
        add(lAccess);
        add(lTempPathTitle);
        add(lTempPath);

        //Adding 1st algo to the panel
        add(lAlgoName);
        add(lAlgoPath);
        add(lAlgoLength);
        add(lAlgoRuntime);
        //add(lAlgoTotalVerticesTraveled);
        //add(lAlgoComplexity);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //label.setHorizontalAlignment(SwingConstants.CENTER);
        //label.setVerticalAlignment(SwingConstants.TOP);
        //add(label,BorderLayout.CENTER);

    }
}
