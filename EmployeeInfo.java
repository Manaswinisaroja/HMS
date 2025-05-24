package hospital.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

class Employee {
    String name;
    String id;
    String designation;

    Employee(String name, String id, String designation) {
        this.name = name;
        this.id = id;
        this.designation = designation;
    }
}

// Manual Linked List Node
class EmployeeNode {
    Employee employee;
    EmployeeNode next;

    EmployeeNode(Employee employee) {
        this.employee = employee;
        this.next = null;
    }
}

// Manual Linked List
class EmployeeLinkedList {
    EmployeeNode head;

    void add(Employee emp) {
        EmployeeNode newNode = new EmployeeNode(emp);
        if (head == null) {
            head = newNode;
        } else {
            EmployeeNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    EmployeeNode getHead() {
        return head;
    }
}

public class EmployeeInfo extends JFrame implements ActionListener {
    JButton backButton;
    JTable table;
    JTextField searchField;
    DefaultTableModel model;
    EmployeeLinkedList employeeList;

    EmployeeInfo() {
        // UI setup
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 840, 540);
        panel.setBackground(new Color(200, 200, 255));
        panel.setLayout(null);
        add(panel);

        JLabel heading = new JLabel("Employee Information");
        heading.setBounds(290, 10, 300, 40);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        panel.add(heading);

        JLabel searchLabel = new JLabel("Search: ");
        searchLabel.setBounds(50, 60, 60, 25);
        searchLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(120, 60, 200, 25);
        panel.add(searchField);

        // Table setup
        String[] columns = {"Employee Name", "Employee ID", "Designation"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(Color.WHITE);
                } else {
                    c.setBackground(new Color(230, 230, 250));
                }
                setToolTipText("ID: " + table.getValueAt(row, 1) + ", Role: " + table.getValueAt(row, 2));
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 100, 740, 300);
        panel.add(scrollPane);

        backButton = new JButton("Back");
        backButton.setBounds(350, 430, 120, 30);
        backButton.setBackground(new Color(73, 65, 109));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(this);

        backButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(new Color(90, 80, 130));
            }

            public void mouseExited(MouseEvent e) {
                backButton.setBackground(new Color(73, 65, 109));
            }
        });

        panel.add(backButton);

        // Manual Linked List of Employees
        employeeList = new EmployeeLinkedList();
        employeeList.add(new Employee("Dr. A. Sen", "E001", "Surgeon"));
        employeeList.add(new Employee("Nurse R. Kapoor", "E002", "Nurse"));
        employeeList.add(new Employee("Mr. Vivek", "E003", "Pharmacist"));
        employeeList.add(new Employee("Dr. R. Mehta", "E004", "General Physician"));
        employeeList.add(new Employee("Dr. Sneha", "E005", "Emergency Head"));
        employeeList.add(new Employee("Ms. Priya Nair", "E006", "Lab Technician"));
        employeeList.add(new Employee("Dr. K. Malhotra", "E007", "Cardiologist"));
        employeeList.add(new Employee("Mr. Arjun Patel", "E008", "Receptionist"));
        employeeList.add(new Employee("Dr. Neha Verma", "E009", "Dermatologist"));
        employeeList.add(new Employee("Nurse S. Bhatia", "E010", "Senior Nurse"));
        employeeList.add(new Employee("Dr. Rohan Das", "E011", "Orthopedic Surgeon"));
        employeeList.add(new Employee("Ms. Kavita Sharma", "E012", "Hospital Administrator"));
        employeeList.add(new Employee("Mr. Sunil Joshi", "E013", "Radiologist"));
        employeeList.add(new Employee("Dr. Pooja Iyer", "E014", "Pediatrician"));
        employeeList.add(new Employee("Mr. Manoj Khanna", "E015", "Billing Manager"));


        loadTable(employeeList.getHead());

        // Search logic
        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String query = searchField.getText().toLowerCase();
                filterTable(query);
            }
        });

        setUndecorated(true);
        setSize(850, 550);
        setLayout(null);
        setLocation(300, 250);
        setVisible(true);
    }

    private void loadTable(EmployeeNode head) {
        model.setRowCount(0); // Clear table
        EmployeeNode temp = head;
        while (temp != null) {
            Employee emp = temp.employee;
            model.addRow(new Object[]{emp.name, emp.id, emp.designation});
            temp = temp.next;
        }
    }

    private void filterTable(String query) {
        model.setRowCount(0);
        EmployeeNode temp = employeeList.getHead();
        while (temp != null) {
            Employee emp = temp.employee;
            if (emp.name.toLowerCase().contains(query)) {
                model.addRow(new Object[]{emp.name, emp.id, emp.designation});
            }
            temp = temp.next;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new EmployeeInfo();
    }
}
