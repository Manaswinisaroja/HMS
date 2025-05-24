package hospital.management.system;

// Import necessary Swing components and event handling classes
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class Department extends JFrame implements ActionListener {

    // Declare global components
    JButton backButton;
    JTable table;
    JTextField searchField;
    DefaultTableModel model;

    // Constructor to set up GUI
    Department() {
        // Create a panel to hold all components
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 840, 540);
        panel.setBackground(new Color(200, 200, 255)); // Light blue background color
        panel.setLayout(null); // Using absolute positioning
        add(panel);

        // Create heading label
        JLabel heading = new JLabel("Department Details");
        heading.setBounds(300, 10, 300, 40);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22)); // Set font
        panel.add(heading);

        // Create 'Search' label
        JLabel searchLabel = new JLabel("Search: ");
        searchLabel.setBounds(50, 60, 60, 25);
        searchLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(searchLabel);

        // Create text field for entering search queries
        searchField = new JTextField();
        searchField.setBounds(120, 60, 200, 25);
        panel.add(searchField);

        // Define column names and data for the table
        String[] columnNames = {"Department", "Contact Number"};
        String[][] data = {
                {"Surgical department", "1234567890"},
                {"Nursing department", "2468024680"},
                {"Paramedical department", "1357913579"},
                {"Emergency department", "9996663338"},
                {"General department", "9639639639"}
        };

        // Create table model and set initial data
        model = new DefaultTableModel(data, columnNames);

        // Create table using model
        table = new JTable(model) {
            // Override method to make table cells non-editable
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Customize table appearance
        table.setFont(new Font("Tahoma", Font.PLAIN, 14)); // Set font for table content
        table.setRowHeight(28); // Set height for each row
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14)); // Set font for header
        table.setBackground(Color.white); // Set background color

        // Implement zebra striping for table rows (alternating colors)
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                // Set different background color for even and odd rows
                if (row % 2 == 0) {
                    c.setBackground(Color.WHITE); // Even row
                } else {
                    c.setBackground(new Color(230, 230, 250)); // Odd row - light lavender
                }
                // Tooltip to show contact number on hovering
                setToolTipText("Contact: " + table.getValueAt(row, 1));
                return c;
            }
        });

        // Create a scroll pane to make the table scrollable
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 100, 740, 300); // Set position and size
        panel.add(scrollPane);

        // Create a 'Back' button
        backButton = new JButton("Back");
        backButton.setBounds(350, 430, 120, 30);
        backButton.setBackground(new Color(73, 65, 109)); // Dark purple color
        backButton.setForeground(Color.WHITE); // Text color white
        backButton.setFont(new Font("Tahoma", Font.BOLD, 14)); // Bold font
        backButton.setFocusPainted(false); // No focus border
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor on hover
        backButton.addActionListener(this); // Add action listener

        // Add mouse listener for hover effects
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(new Color(90, 80, 130)); // Change color on hover
            }
            public void mouseExited(MouseEvent e) {
                backButton.setBackground(new Color(73, 65, 109)); // Revert color when not hovering
            }
        });

        // Add Back button to panel
        panel.add(backButton);

        // Add a key listener to search field to filter table dynamically as user types
        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String query = searchField.getText().toLowerCase(); // Get typed text and convert to lowercase
                filterTable(query); // Call method to filter
            }
        });

        // Frame settings
        setUndecorated(true); // Remove title bar and borders
        setSize(850, 550); // Set frame size
        setLayout(null); // Absolute positioning
        setLocation(300, 250); // Set frame location
        setVisible(true); // Make frame visible
    }

    // Method to filter table based on search query
    private void filterTable(String query) {
        // Create a new table model for filtered results
        DefaultTableModel newModel = new DefaultTableModel(new String[]{"Department", "Contact Number"}, 0);

        // Loop through all rows and add matching rows to the new model
        for (int i = 0; i < model.getRowCount(); i++) {
            String dept = model.getValueAt(i, 0).toString().toLowerCase(); // Department name
            String number = model.getValueAt(i, 1).toString(); // Contact number
            if (dept.contains(query)) { // Check if department matches the query
                Vector<String> row = new Vector<>();
                row.add(dept);
                row.add(number);
                newModel.addRow(row);
            }
        }

        // Set the filtered model to the table
        table.setModel(newModel);
    }

    // Action performed when the Back button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false); // Close the current window
    }

    // Main method to launch the application
    public static void main(String[] args) {
        new Department(); // Create an instance of Department class
    }
}