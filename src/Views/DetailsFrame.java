package Views;

import Models.Graph.Result;
import Models.Graph.Vertex;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DetailsFrame {
    JFrame frame;
    JTable table;
    JLabel label;
    JScrollPane sp;
    DefaultTableModel tableModel;

    public DetailsFrame(List<Result> results) {
        frame = new JFrame("Benoît PEGAZ - Comparison between the implemented algorithms");

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);
        table.setRowHeight(30);

        tableModel.addColumn("",
                new Object[]{
                        "Complexity (Worst-case)",
                        "Runtime",
                        "Path length",
                        "Number of vertices travelled",
                        "Path"
                });
        setTable(results);

        label = new JLabel("");
        label.setBorder(new EmptyBorder(30, 0, 0, 0));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(label);

        sp = new JScrollPane(table);
        panel.add(sp);

        frame.add(panel);
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void accessible(boolean accessible) {
        if (accessible) {
            sp.setVisible(true);
            label.setVisible(false);
            label.setText("");
        } else {
            sp.setVisible(false);
            label.setVisible(true);
            label.setText("There is no path between these two vertices");
        }
    }

    public void setTable(List<Result> results) {
        for (Result r : results) {
            StringBuilder path = new StringBuilder("<html>");
            for (Vertex v : r.getInvertedPath()) {
                path.append(v.getName()).append("<br>");
            }
            tableModel.addColumn(r.getName(), new Object[]{
                    r.getComplexity(),
                    (int) r.getRuntime() + " ns",
                    (int) r.getLength() + " kms",
                    r.getPathWeight() + " vertices",
                    path.toString()
            });
        }
        updateRowHeights();
    }

    // Update the display dynamically
    private void updateRowHeights() {
        for (int row = 0; row < table.getRowCount() ; row++) {
            int rowHeight = table.getRowHeight();
            for (int column = 0; column < table.getColumnCount(); column++) {
                Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }
            table.setRowHeight(row, rowHeight);
        }
    }
}
