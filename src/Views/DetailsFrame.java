package Views;

import Models.Graph.Result;
import Models.Graph.Vertex;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
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
        frame = new JFrame("Details");

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);
        table.setRowHeight(30);

        tableModel.addColumn("",
                new Object[]{
                        "Complexity",
                        "Real complexity",
                        "Runtime",
                        "Number of vertices travelled",
                        "Path weight",
                        "Path",
                        "Details"
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
        frame.setSize(1200, 800);
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
                    r.getRealComplexity(),
                    (int) r.getRuntime() + " ns",
                    (int) r.getLength(),
                    r.getPathWeight(),
                    path.toString(),
                    r.getDetails()
            });
        }
        updateRowHeights();
    }

    // Update the display dynamically
    private void updateRowHeights() {
        for (int row = 0; row < table.getRowCount(); row++) {
            int rowHeight = table.getRowHeight();
            for (int column = 0; column < table.getColumnCount(); column++) {
                Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }
            table.setRowHeight(row, rowHeight);
        }
    }
}
