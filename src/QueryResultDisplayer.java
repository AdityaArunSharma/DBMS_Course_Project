import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class QueryResultDisplayer
{
    JFrame frame1;
    static JTable table;
    public QueryResultDisplayer(ResultSet rs) throws Exception
    {

        frame1 = new JFrame("Database Search Result");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnNumber = rsmd.getColumnCount();
        String columnNames[] = new String[columnNumber];
        for(int i=1;i<=columnNumber;i++)
        {
            columnNames[i-1] = rsmd.getColumnName(i);
        }
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        while (rs.next())
        {
            String chunks[] = new String[columnNumber];
            for(int i=1;i<=columnNumber;i++)
            {
                 String columnValue = rs.getString(i);
                 chunks[i-1] = columnValue;
            }
//            System.out.println(Arrays.toString(chunks));
            Object rowObjectArray[] = new Object[columnNumber];
            for(int x=0;x<columnNumber;x++)
            {
                rowObjectArray[x] = chunks[x];
            }
            model.addRow(rowObjectArray);
        }
        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(1000, 300);
    }
}
