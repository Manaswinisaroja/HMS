package hospital.management.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.*;

public class Room extends JFrame {
    JTable table;

    // DSA Structures
    ArrayList<RoomData> roomList = new ArrayList<>();
    Stack<RoomData> viewHistory = new Stack<>();
    Queue<RoomData> viewQueue = new LinkedList<>();
    HashMap<String, RoomData> roomMap = new HashMap<>();

    Room() {
        JPanel panel = new JPanel();
        panel.setBounds(5,5,890,590);
        panel.setBackground(new Color(200,200,255));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/room.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(600,200,200,200);
        panel.add(label);

        table = new JTable();
        table.setBounds(10,40,500,400);
        table.setBackground(new Color(200,200,255));
        panel.add(table);

        try {
            conn c = new conn();
            String q = "select * from Room";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

            while(resultSet.next()) {
                String roomNo = resultSet.getString("roomno");
                String availability = resultSet.getString("availability");
                String price = resultSet.getString("price");
                String bedType = resultSet.getString("bedtype");

                RoomData room = new RoomData(roomNo, availability, price, bedType);
                roomList.add(room);                // ArrayList
                viewQueue.offer(room);             // Queue
                roomMap.put(roomNo, room);         // HashMap
            }

            // Sort rooms by price (ascending)
            roomList.sort(Comparator.comparingInt(r -> Integer.parseInt(r.price)));

            // Binary search for Room No "101"
            RoomData result = binarySearchRoom("101");
            if (result != null) {
                System.out.println("Room Found: " + result);
                viewHistory.push(result); // Save viewed room to Stack
            } else {
                System.out.println("Room Not Found");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        JLabel label1 = new JLabel("Room No");
        label1.setBounds(12,15,80,15);
        label1.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label1);

        JLabel label2 = new JLabel("Availability");
        label2.setBounds(140,15,80,15);
        label2.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label2);

        JLabel label3 = new JLabel("Price");
        label3.setBounds(262,15,80,15);
        label3.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label3);

        JLabel label4 = new JLabel("Bed Type");
        label4.setBounds(390,15,80,15);
        label4.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label4);

        JButton back = new JButton("Back");
        back.setBounds(200,450,120,30);
        back.setBackground(new Color(73,65,109));
        back.setForeground(Color.white);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(900,600);
        setLayout(null);
        setLocation(300,230);
        setVisible(true);
    }

    // Binary Search on sorted roomList by roomNo
    private RoomData binarySearchRoom(String roomNo) {
        int low = 0, high = roomList.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            RoomData midRoom = roomList.get(mid);
            int cmp = midRoom.roomNo.compareTo(roomNo);
            if (cmp == 0) return midRoom;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }

    // Custom class to store room data
    class RoomData {
        String roomNo, availability, price, bedType;

        RoomData(String roomNo, String availability, String price, String bedType) {
            this.roomNo = roomNo;
            this.availability = availability;
            this.price = price;
            this.bedType = bedType;
        }

        public String toString() {
            return roomNo + " | " + availability + " | " + price + " | " + bedType;
        }
    }

    public static void main(String[] args) {
        new Room();
    }
}