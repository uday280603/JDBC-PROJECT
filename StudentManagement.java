import java.sql.*;
import  java.util.Scanner;
public class StudentManagement {
     static  final String url = "jdbc:mysql://localhost:3306/student_db";
     static  final String username = "root";
    static final String password = "Uday@8640";
    static Connection con;
     static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            con = DriverManager.getConnection(url,username,password);
             System.out.println("Connection successful");
            while (true){
                System.out.println("===STUDENT MANAGEMENT SYSTEM====");
                System.out.println("1]Add Student");
                System.out.println("2]View all Student");
                System.out.println("3]Update Student");
                System.out.println("4]Delete Student");
                System.out.println("5]Search Student");
                System.out.println("6]EXIT");
                System.out.println("Enter Your Choice");
                int Choice = scanner.nextInt();
                switch (Choice){
                    case 1:
                        addstudent();
                        break;
                    case 2:
                        viewstudent_inDB();
                        break;
                    case 3:
                        updateStudent();
                        break;
                    case 4:
                        deletestudent();
                        break;
                    case 5:
                        searchStudent();
                        break;
                    case 6:
                        con.close(); System.exit(0);
                    default:
                        System.out.println("Invalid Choice..!");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void addstudent() throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student Roll no : ");
        int roll = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Student Name : ");
        String name = sc.nextLine();
        System.out.println("Enter Student Course : ");
        String course = sc.nextLine();
        System.out.println("Enter Marks of Student : ");
        int marks = sc.nextInt();

        String query = "INSERT INTO student values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1,roll);
        ps.setString(2,name);
        ps.setString(3,course);
        ps.setInt(4,marks);
        int affectcoloum =  ps.executeUpdate();
        if(affectcoloum>0){
            System.out.println(affectcoloum+" row's are updated");
        }
        else {
            System.out.println("not update");
        }
        System.out.println("Student Added Into DataBase sucessfully..");

    }
    static void viewstudent_inDB() throws SQLException{
        String query = "SELECT * FROM student";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            System.out.println(rs.getInt("roll_no") + " | " + rs.getString("name") + " | " + rs.getString("course") + " | " + rs.getInt("marks"));
        }
    }
    static void updateStudent() throws SQLException{
        System.out.println("Enter roll no of student where to Update : ");
        int roll = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new name : ");
        String name = scanner.nextLine();
        System.out.println("Enter new course : ");
        String course  = scanner.nextLine();
        System.out.println("Enter new marks :");
        int marks = scanner.nextInt();

        String query = "UPDATE student SET name=?, course=?, marks=?,roll_no=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,name);
        ps.setString(2,course);
        ps.setInt(3,marks);
        ps.setInt(4,roll);
        int affectrows =ps.executeUpdate();
        if(affectrows>0){
            System.out.println(affectrows+" row's are updated successfully");
        }
        else {
            System.out.println("Updation failed..!");
        }

    }
    static void deletestudent() throws SQLException{
        System.out.println("Enter the roll number which you want to Delete : ");
        int roll = scanner.nextInt();
        scanner.nextLine();
        String query = "DELETE FROM student WHERE roll_no=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1,roll);
        int affectedrow=ps.executeUpdate();
        if(affectedrow>0){
            System.out.println(roll+" roll number data id deleted successfully..");
        }
        else {
            System.out.println("Data delection unsucessful..");
        }
    }
    static  void searchStudent()throws SQLException{
        System.out.println("Enter the roll number to search : ");
        int roll = scanner.nextInt();
        scanner.nextLine();
        String query = "SELECT * FROM student WHERE roll_no=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1,roll);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            System.out.println("Roll No: " + rs.getInt("roll_no"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Course: " + rs.getString("course"));
            System.out.println("Marks: " + rs.getInt("marks"));
        } else {
            System.out.println("Student not found.");
        }

    }
}
