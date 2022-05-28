package main_pack;

import java.sql.*;
import javax.swing.JOptionPane;

public class AdminClass extends DdConnection{ //inheriting the DbConnection class to connect with the db using .connect method
    Connection myConn = null;
    
    public AdminClass() {
        myConn = DdConnection.connect(); //assings the connection to this variable in the constructor to use it inside the class
    }
    
    //Creating a method to search for a student
    public ResultSet search(int studentId){
        try{
            PreparedStatement Stmt = myConn.prepareStatement("Select * From student_list Where student_id = "+studentId+"");
            ResultSet rs = Stmt.executeQuery();
            return rs;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    //Creating a method to add a student
    public void addStudent(int studentId,String studentName,String faculty,String field,int year,String registered,String email,int mobile){
        
        try {
            PreparedStatement Stmt = myConn.prepareStatement("Insert into student_list" + "(student_id,student_name,faculty,study_field,year,registered,email,mobile)" + "Values(?,?,?,?,?,?,?,?)");
            Stmt.setInt(1, studentId); //assigning all the parameters of the method into the right place
            Stmt.setString(2, studentName);
            Stmt.setString(3, faculty);
            Stmt.setString(4, field);
            Stmt.setInt(5, year);
            Stmt.setString(6, registered);
            Stmt.setString(7, email);
            Stmt.setInt(8, mobile);
            Stmt.execute();//executing the statement
            JOptionPane.showMessageDialog(null, "Student added!");//shpwing the user a message
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Creating a method to update a student
    public void updateStudent (int studentId,String studentName,String faculty,String field,int year,String registered,String email,int mobile) {
        
        try {
           PreparedStatement Stmt = myConn.prepareStatement("Update student_list Set student_id = ?, student_name = ?, faculty = ?, study_field = ?, year = ?, registered = ?, email = ?, mobile =?  Where student_id = ?");
           Stmt.setInt(1, studentId);//assigning all the parameters of the method into the right place
           Stmt.setString(2, studentName);
           Stmt.setString(3, faculty);
           Stmt.setString(4, field);
           Stmt.setInt(5, year);
           Stmt.setString(6, registered);
           Stmt.setString(7, email);
           Stmt.setInt(8, mobile);
           Stmt.setInt(9, studentId);
           Stmt.execute();//executing the statement
           JOptionPane.showMessageDialog(null, "Student data updated!");//shpwing the user a message
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    //Creating a method to delete a student
    public void deleteStudent(int studentId) {
        
        try{
            PreparedStatement Stmt = myConn.prepareStatement("Delete from student_list Where student_id = "+studentId+"");
            Stmt.execute();
            JOptionPane.showMessageDialog(null, "Student deleted!");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    //Creating a method to increase the year for all students
    public void increaseYear() {
        
        try{
            PreparedStatement Stmt = myConn.prepareStatement("SELECT student_id, year FROM student_list");
            ResultSet rs = Stmt.executeQuery();//gets only the id and year of the students
            
            while (rs.next()) { //loops through each row in the table
                if (rs.getInt(2) > 2){ //checks if the year the student is in is greater than 2
                   PreparedStatement stmt = myConn.prepareStatement("Delete from student_list Where student_id = ?");
                   stmt.setInt(1, rs.getInt(1)); //sets the id in the statement
                   stmt.execute();
                }
                else {
                   PreparedStatement stmt = myConn.prepareStatement("Update student_list Set year = ? Where student_id = ?");
                   stmt.setInt(1, rs.getInt(2) + 1); //increments the year by one
                   stmt.setInt(2, rs.getInt(1));// sets the id in the statement
                   stmt.execute();
                }
            }
            JOptionPane.showMessageDialog(null, "Student's year increased!");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
