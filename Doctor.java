package hospital.management.system;

// Imports DbUtils for displaying ResultSet in JTable
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

// Class to display doctor details in a table format
public class Doctor extends JFrame {

    Doctor() {
        // JTable to display database values in a tabular form
        JTable table = new JTable();
        table.setBackground(new Color(200,200,255));  // Setting background color

        try {
            // Connecting to database
            conn c = new conn();
            // SQL query to fetch all doctor records
            String q = "SELECT * FROM doctor";
            ResultSet rs = c.statement.executeQuery(q);

            // Setting the fetched data into the table using DbUtils
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e){
            e.printStackTrace();  // To handle exceptions gracefully
        }

        // Adding scroll pane to allow scrolling of table content
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBackground((new Color(200,200,255)));
        add(jsp);

        // JFrame settings
        setUndecorated(true);  // Removes window title bar and borders
        setSize(800,400);      // Sets window size
        setLocation(300,200);  // Sets window position on screen
        setVisible(true);      // Makes window visible
    }

    public static void main(String[] args) {
        new Doctor();  // Launching the Doctor window
    }
}