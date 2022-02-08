package Views;

import Models.Graph.Result;
import Models.Graph.Vertex;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DetailsFrame {
    // frame
    JFrame frame;
    JTable table;
    DefaultTableModel tableModel;

    // Constructor
    public DetailsFrame(List<Result> results)
    {
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
                "Path"
        });

        setTable(results);

        JScrollPane sp = new JScrollPane(table);
        frame.add(sp);
        frame.setSize(1200, 800);
        frame.setVisible(true);
    }

    public void setTable(List<Result> results) {
        for (Result r: results) {
            String path = "<html>";
            for (Vertex v : r.getInvertedPath()) {
                path += v.getName() + "<br>";
            }
            tableModel.addColumn(r.getName(), new Object[]{
                    r.getComplexity(),
                    r.getRealComplexity(),
                    r.getRuntime(),
                    (int) r.getLength(),
                    r.getPathWeight(),
                    path
            });
        }
        updateRowHeights();
    }

    private void updateRowHeights()
    {
        for (int row = 0; row < table.getRowCount(); row++)
        {
            int rowHeight = table.getRowHeight();

            for (int column = 0; column < table.getColumnCount(); column++)
            {
                Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }

            table.setRowHeight(row, rowHeight);
        }
    }
}
