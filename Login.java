package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {    //J-Frame

    JTextField textField;   //Place for Username

    JPasswordField jPasswordField;   //Place for Password

    JButton b1,b2;


    Login(){

        //Username

        JLabel namelabel = new JLabel("Username");
        namelabel.setBounds(40,20,100,30);   //Determines the position in Frame at which Username is Present
        namelabel.setFont(new Font("Tahoma",Font.BOLD,16));
        namelabel.setForeground(Color.black);   //For Font Color
        add(namelabel);

        //Password

        JLabel psswd = new JLabel("Password");
        psswd.setBounds(40,70,100,30);
        psswd.setFont(new Font("Tahoma",Font.BOLD,16));
        psswd.setForeground(Color.black);          //For Text Color
        add(psswd);

        textField = new JTextField();
        textField.setBounds(150,20,150,30);
        textField.setFont(new Font("Tahoma",Font.PLAIN,15));
        textField.setBackground(new Color(255,248,232));
        add(textField);

        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(150,70,150,30);
        jPasswordField.setFont(new Font("Tahoma",Font.PLAIN,15));
        jPasswordField.setBackground((new Color(255,248,232)));
        add(jPasswordField);

        //Image

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource(""));   // icon/login.png We just got our image from Folder
        //Now we should scale it
        Image i1 = imageIcon.getImage().getScaledInstance(200,200, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(i1);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(320,-30,400,300);
        add(label);

        //Login Button

        b1 = new JButton("Login");   //The one in "" will appear on the button
        b1.setBounds(40,140,120,30);
        b1.setFont(new Font("serif",Font.BOLD,15));
        b1.setBackground(new Color(255, 248, 232));   //For Background Color
        b1.setForeground(Color.BLACK);   //For Text Color
        b1.addActionListener(this);    //When we click button addActionListener will be activated and moves to actionPerformed
        add(b1);

        //Sign Up Button

        b2 = new JButton("Cancel");   //The one in "" will appear on the button
        b2.setBounds(180,140,120,30);
        b2.setFont(new Font("serif",Font.BOLD,15));
        b2.setBackground(new Color(255, 248, 232));   //For Background Color
        b2.setForeground(Color.BLACK);   //For Text Color
        b2.addActionListener(this);
        add(b2);

        getContentPane().setBackground(new Color(200,200,255));   //For Background Color
        setSize(750,300);   //Setting Frame Size
        setLocation(400,270);   //To shift the window position
        //To set window to center give x=400 and y=270
        setLayout(null);   //By default Border Layout
        setVisible(true); //By default JFrame visibility is Hidden so we are resetting it
        //We shouldn't write anything below setVisible
        //Those written below setVisible are not visible

    }

    @Override
    public void actionPerformed(ActionEvent e){    //logic of Login

        if(e.getSource() == b1){
            try{
                conn c = new conn();
                String user = textField.getText();  //We are getting User  ID from User and saving it in user
                String Pass = jPasswordField.getText();
                //Query
                String q = "SELECT * FROM login where ID = '"+user+"' and pw = '"+Pass+"' ";
                ResultSet resultset = c.statement.executeQuery(q);  //Whether the entered details match with one that are stored or not is stored in resultset

                if(resultset.next()){
                    new Reception();
                    setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Inavlid");
                }

            }catch (Exception E){
                E.printStackTrace();
            }
        }
        else {
            System.exit(10);
        }

    }

    public static void main(String[] args){
        new Login(); //Calling Constructor
    }
}
