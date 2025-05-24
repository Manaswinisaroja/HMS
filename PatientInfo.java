package hospital.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;
import java.util.*;

public class PatientInfo extends JFrame {
    private JTable patientTable;
    private JScrollPane scrollPane;

    // ✅ Priority Queue to manage emergency patients based on urgency level
    private PriorityQueue<Patient> emergencyQueue;

    public PatientInfo() {
        // ✅ Frame configuration
        setTitle("Patient Information");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // ✅ Initialize the Priority Queue with custom comparator (higher priority first)
        emergencyQueue = new PriorityQueue<>(new Comparator<Patient>() {
            @Override
            public int compare(Patient p1, Patient p2) {
                // Compare based on priority, higher comes first
                return Integer.compare(p2.getPriority(), p1.getPriority());
            }
        });

        // ✅ Create main panel and set layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(200, 200, 255));  // Light blue background
        add(panel);

        // ✅ Create table model and add column names
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Type");
        model.addColumn("Number");
        model.addColumn("Name");
        model.addColumn("Gender");
        model.addColumn("Disease");
        model.addColumn("Room");
        model.addColumn("Check-in Time");
        model.addColumn("Deposit");

        // ✅ Create JTable and apply styling
        patientTable = new JTable(model);
        patientTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        patientTable.setForeground(new Color(73, 65, 109));
        patientTable.setBackground(new Color(255, 255, 255));
        scrollPane = new JScrollPane(patientTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // ✅ Customize table header styling
        JTableHeader header = patientTable.getTableHeader();
        header.setBackground(new Color(73, 65, 109));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Tahoma", Font.BOLD, 14));

        // ✅ Load patient data from the database into the table and emergency queue
        loadPatientData(model);

        // ✅ Create button panel and BACK button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(200, 200, 255));

        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(73, 65, 109));
        backButton.addActionListener(e -> setVisible(false));  // Close the frame
        buttonPanel.add(backButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
    }

    // ✅ Method to load data from the database and add to the JTable and priority queue
    private void loadPatientData(DefaultTableModel model) {
        try {
            conn c = new conn(); // Assuming a valid connection class is used
            String query = "SELECT * FROM patient_info";
            ResultSet resultSet = c.statement.executeQuery(query);

            // ✅ Loop through the result set and populate table + emergencyQueue
            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                String number = resultSet.getString("Number");
                String name = resultSet.getString("Name");
                String gender = resultSet.getString("Gender");
                String disease = resultSet.getString("Patient_Disease");
                String room = resultSet.getString("Room_Number");
                String time = resultSet.getString("Time");
                String deposit = resultSet.getString("Deposit");

                // Add row to the table
                Vector<String> row = new Vector<>();
                row.add(id);
                row.add(number);
                row.add(name);
                row.add(gender);
                row.add(disease);
                row.add(room);
                row.add(time);
                row.add(deposit);
                model.addRow(row);

                // Add to the emergency queue based on disease priority
                int priority = getPatientPriority(disease);
                emergencyQueue.add(new Patient(id, name, disease, priority));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading patient data: " + e.getMessage());
        }
    }

    // ✅ Method to assign priority based on the disease type
    private int getPatientPriority(String disease) {
        switch (disease.toLowerCase()) {
            case "heart attack":
                return 5; // Highest priority
            case "stroke":
                return 4;
            case "fever":
                return 2;
            case "cold":
                return 1; // Lowest priority
            default:
                return 3; // Medium/default priority
        }
    }

    // ✅ Method to display patients from the emergency queue (for debugging/demo)
    private void displayEmergencyPatients() {
        while (!emergencyQueue.isEmpty()) {
            Patient p = emergencyQueue.poll();
            System.out.println("Patient ID: " + p.getId() + ", Name: " + p.getName() + ", Priority: " + p.getPriority());
        }
    }

    // ✅ Main method to run the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PatientInfo frame = new PatientInfo();
            frame.setVisible(true);
        });
    }

    // ✅ Inner class to represent patient data and priority
    class Patient {
        private String id;
        private String name;
        private String disease;
        private int priority;

        public Patient(String id, String name, String disease, int priority) {
            this.id = id;
            this.name = name;
            this.disease = disease;
            this.priority = priority;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDisease() {
            return disease;
        }

        public int getPriority() {
            return priority;
        }
    }
}