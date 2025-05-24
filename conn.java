package hospital.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class conn {

    public Connection conn;
    Connection connection;
    Statement statement;

    public conn() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management","root","Manaswini@37");
            statement = connection.createStatement();
            //Local host Noo. of mysql is 3306 by default  ; root is userId
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}