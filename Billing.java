package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;   // DSA: Dynamic array for storing patient data
import java.util.HashMap;    // DSA: Fast key-based room price lookup

public class Billing extends JFrame implements ActionListener {

    JTextField tfId;  // Input field for Patient ID
    JLabel nameLabel, depositLabel, priceLabel, totalLabel;  // Labels to display patient details and bill
    JButton check, back;  // Buttons for actions

    // DSA: Using ArrayList to dynamically store patient information
    ArrayList<String[]> patients = new ArrayList<>();  // List to store patient data (ID, Name, Deposit, Room Number)

    // DSA: Using HashMap for constant-time (O(1)) room price lookup
    HashMap<String, String> roomPrices = new HashMap<>();  // Map for storing room prices (Room Number -> Price)

    /**
     * Constructor - initializes the GUI and populates dummy data using DSA.
     */
    Billing() {
        setLayout(null);  // Absolute positioning for components

        // Initializing patient data in the ArrayList
        patients.add(new String[]{"P1001", "John Doe", "2000", "100"});
        patients.add(new String[]{"P1002", "Jane Smith", "1500", "101"});

        // Initializing room prices in the HashMap
        roomPrices.put("100", "500");
        roomPrices.put("101", "500");
        roomPrices.put("102", "800");

        // --- GUI Setup Below ---

        // Label for entering Patient ID
        JLabel lblId = new JLabel("Enter Patient ID:");
        lblId.setBounds(30, 30, 150, 30);
        add(lblId);

        // TextField for user input (Patient ID)
        tfId = new JTextField();
        tfId.setBounds(180, 30, 200, 30);
        add(tfId);

        // Button to check bill after entering Patient ID
        check = new JButton("Check Bill");
        check.setBounds(400, 30, 120, 30);
        check.addActionListener(this);  // Register the action listener
        add(check);

        // Labels to display patient information and bill details
        nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(30, 80, 300, 30);
        add(nameLabel);

        depositLabel = new JLabel("Deposit: ");
        depositLabel.setBounds(30, 120, 300, 30);
        add(depositLabel);

        priceLabel = new JLabel("Room Price: ");
        priceLabel.setBounds(30, 160, 300, 30);
        add(priceLabel);

        totalLabel = new JLabel("Total Bill: ");
        totalLabel.setBounds(30, 200, 300, 30);
        totalLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(totalLabel);

        // Button to go back to previous screen (if needed)
        back = new JButton("Back");
        back.setBounds(30, 250, 100, 30);
        back.addActionListener(this);  // Register the action listener
        add(back);

        // Window setup (size, location, and visibility)
        setSize(550, 350);
        setLocation(400, 250);
        setUndecorated(true);  // Removes the window border for a cleaner look
        setVisible(true);  // Make the frame visible
    }

    /**
     * Action handler for button clicks.
     * - Uses linear search to find the patient in the ArrayList.
     * - Uses HashMap for constant-time room price lookup.
     */
    public void actionPerformed(ActionEvent ae) {
        String id = tfId.getText();  // Get the input patient ID

        try {
            // If the 'Check Bill' button is clicked
            if (ae.getSource() == check) {
                boolean found = false;  // Flag to check if the patient is found
                String patientName = "", deposit = "", roomNo = "";

                // DSA: Performing linear search (O(n)) to find the patient
                // Iterate through all patients to find a match with the entered patient ID
                for (String[] patient : patients) {
                    if (patient[0].equals(id)) {
                        patientName = patient[1];  // Name of the patient
                        deposit = patient[2];      // Deposit amount of the patient
                        roomNo = patient[3];       // Room number assigned to the patient
                        found = true;              // Patient found, exit the loop
                        break;
                    }
                }

                // If patient is found, calculate the total bill
                if (found) {
                    // Display basic patient info
                    nameLabel.setText("Name: " + patientName);
                    depositLabel.setText("Deposit: " + deposit);

                    // DSA: Using HashMap to get room price in O(1)
                    // Retrieve the room price using the room number
                    String price = roomPrices.get(roomNo);
                    if (price != null) {
                        // Display room price and calculate the total bill
                        priceLabel.setText("Room Price: " + price);
                        int total = Integer.parseInt(deposit) + Integer.parseInt(price);  // Calculate the total
                        totalLabel.setText("Total Bill: " + total);
                    } else {
                        // If room not found in the HashMap
                        priceLabel.setText("Room Price: Not Found");
                        totalLabel.setText("Total Bill: Cannot calculate");
                    }

                } else {
                    // If patient ID is not found in the list
                    JOptionPane.showMessageDialog(null, "Patient not found!");
                }

            } else if (ae.getSource() == back) {
                setVisible(false);  // Close the billing window if back button is clicked
            }

        } catch (Exception e) {
            e.printStackTrace();  // Log any exception that occurs during execution
        }
    }

    /**
     * Main method - entry point of the program.
     */
    public static void main(String[] args) {
        new Billing();  // Launch the Billing system
    }
}