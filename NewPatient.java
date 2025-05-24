package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class NewPatient extends JFrame implements ActionListener {

    // Dropdown to select ID type (Aadhar, Voter ID, etc.)
    JComboBox comboBox;

    // Text fields to input ID number, name, disease, deposit amount
    JTextField textFieldNumber, textName, textFieldDisease, textFieldDeposit;

    // Radio buttons for gender selection
    JRadioButton r1, r2;

    // Dropdown to select room number
    Choice c1;

    // Label to show current check-in date
    JLabel date;

    // Buttons to submit or go back
    JButton b1, b2;

    // Constructor to design the GUI
    NewPatient() {
        // Main panel setup
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 840, 540);
        panel.setBackground(new Color(200, 200, 255));
        panel.setLayout(null);
        add(panel);

        // Adding image
        ImageIcon imageicon = new ImageIcon(ClassLoader.getSystemResource("icon/patient.jpg"));
        Image image = imageicon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon imageicon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageicon1);
        label.setBounds(550, 150, 200, 200);
        panel.add(label);

        // Title
        JLabel labelName = new JLabel("NEW PATIENT FORM");
        labelName.setBounds(118, 11, 260, 53);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(labelName);

        // Label for ID Type
        JLabel labelID = new JLabel("ID : ");
        labelID.setBounds(35, 76, 200, 14);
        labelID.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelID.setForeground(new Color(73, 65, 109));
        panel.add(labelID);

        // Dropdown list for ID type
        comboBox = new JComboBox(new String[]{"Aadhar Card", "Voter Id", "Driving License"});
        comboBox.setBounds(271, 73, 150, 20);
        comboBox.setBackground(new Color(73, 65, 109));
        comboBox.setForeground(Color.white);
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(comboBox);

        // Input field for ID number
        JLabel labelNumber = new JLabel("Number : ");
        labelNumber.setBounds(35, 111, 200, 14);
        labelNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelNumber.setForeground(new Color(73, 65, 109));
        panel.add(labelNumber);

        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(271, 111, 150, 20);
        panel.add(textFieldNumber);

        // Input field for Name
        JLabel labelName1 = new JLabel("Name : ");
        labelName1.setBounds(35, 151, 200, 14);
        labelName1.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelName1.setForeground(new Color(73, 65, 109));
        panel.add(labelName1);

        textName = new JTextField();
        textName.setBounds(271, 151, 150, 20);
        panel.add(textName);

        // Gender radio buttons
        JLabel labelGender = new JLabel("Gender : ");
        labelGender.setBounds(35, 191, 200, 14);
        labelGender.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelGender.setForeground(new Color(73, 65, 109));
        panel.add(labelGender);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Tahoma", Font.BOLD, 14));
        r1.setForeground(new Color(73, 65, 109));
        r1.setBackground(new Color(200, 200, 255));
        r1.setBounds(271, 191, 80, 15);
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Tahoma", Font.BOLD, 14));
        r2.setForeground(new Color(73, 65, 109));
        r2.setBackground(new Color(200, 200, 255));
        r2.setBounds(350, 191, 80, 15);
        panel.add(r2);

        // Input field for Disease
        JLabel labelDisease = new JLabel("Disease : ");
        labelDisease.setBounds(35, 231, 200, 14);
        labelDisease.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelDisease.setForeground(new Color(73, 65, 109));
        panel.add(labelDisease);

        textFieldDisease = new JTextField();
        textFieldDisease.setBounds(271, 231, 150, 20);
        panel.add(textFieldDisease);

        // Room selection using dropdown (Choice)
        JLabel labelRoom = new JLabel("Room : ");
        labelRoom.setBounds(35, 274, 200, 14);
        labelRoom.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelRoom.setForeground(new Color(73, 65, 109));
        panel.add(labelRoom);

        c1 = new Choice();
        try {
            conn c = new conn(); // Custom connection class
            ResultSet resultSet = c.statement.executeQuery("select * from Room");
            while (resultSet.next()) {
                c1.add(resultSet.getString("Room_No")); // Adds available rooms to dropdown
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        c1.setBounds(271, 274, 150, 20);
        c1.setFont(new Font("Tahoma", Font.BOLD, 14));
        c1.setForeground(Color.white);
        c1.setBackground(new Color(73, 65, 109));
        panel.add(c1);

        // Date (Check-in Time)
        JLabel labelDate = new JLabel("Check-in Time : ");
        labelDate.setBounds(35, 316, 200, 14);
        labelDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelDate.setForeground(new Color(73, 65, 109));
        panel.add(labelDate);

        Date date1 = new Date();
        date = new JLabel("" + date1);
        date.setBounds(271, 316, 250, 15);
        date.setForeground(new Color(73, 65, 109));
        date.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(date);

        // Input field for Deposit
        JLabel labelDeposit = new JLabel("Deposit : ");
        labelDeposit.setBounds(35, 359, 200, 17);
        labelDeposit.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelDeposit.setForeground(new Color(73, 65, 109));
        panel.add(labelDeposit);

        textFieldDeposit = new JTextField();
        textFieldDeposit.setBounds(271, 359, 150, 20);
        panel.add(textFieldDeposit);

        // Button to add patient record
        b1 = new JButton("ADD");
        b1.setBounds(100, 430, 120, 30);
        b1.setForeground(Color.white);
        b1.setBackground(new Color(73, 65, 109));
        b1.addActionListener(this);
        panel.add(b1);

        // Button to go back
        b2 = new JButton("BACK");
        b2.setBounds(260, 430, 120, 30);
        b2.setForeground(Color.white);
        b2.setBackground(new Color(73, 65, 109));
        b2.addActionListener(this);
        panel.add(b2);

        // Frame settings
        setUndecorated(true);
        setSize(850, 550);
        setLayout(null);
        setLocation(300, 250);
        setVisible(true);
    }

    // Handles button actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) { // If "Add" button is clicked
            conn c = new conn();
            String radioBTN = null;

            // Get selected gender
            if (r1.isSelected()) {
                radioBTN = "Male";
            } else if (r2.isSelected()) {
                radioBTN = "Female";
            }

            // Fetch all input values
            String s1 = (String) comboBox.getSelectedItem();
            String s2 = textFieldNumber.getText();
            String s3 = textName.getText();
            String s4 = radioBTN;
            String s5 = textFieldDisease.getText();
            String s6 = c1.getSelectedItem();
            String s7 = date.getText();
            String s8 = textFieldDeposit.getText();

            try {
                // Insert patient record
                String q = "insert into patient_info values('" + s1 + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "','" + s7 + "','" + s8 + "')";
                // Update room status to Occupied
                String q1 = "update room set Availability = 'Occ' where Room_No = " + s6;

                c.statement.executeUpdate(q);
                c.statement.executeUpdate(q1);

                JOptionPane.showMessageDialog(null, "Added Successfully");
                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            // If "Back" is clicked
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewPatient();
    }
}
