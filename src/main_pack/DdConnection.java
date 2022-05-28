package main_pack;

import java.sql.*;
public class DdConnection {
    
public static Connection connect(){
         Connection myConn = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/university_db", "root", "1234");         
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return myConn;
    }
}
