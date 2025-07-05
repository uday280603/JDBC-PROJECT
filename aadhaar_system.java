import java.sql.*;
import java.util.Scanner;

public class aadhaar_system {
       static final String url = "jdbc:mysql://localhost:3306/aadhaar_system";
       static final   String username = "root";
       static final   String password = "Uday@8640";
       static Connection conn;
        static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        try{
             conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connection successful");


            while (true){
                System.out.println("==========================$$$AADHAAR CARD STORAGE SYSTEM$$$=======================");
                System.out.println("1]Add New Aadhaar Card");
                System.out.println("2]View All Aadhaar Card");
                System.out.println("3]Update Aadhaar Card");
                System.out.println("4]Delete Aadhaar Card");
                System.out.println("5]Search Aadhaar Card");
                System.out.println("6]Exit");
                System.out.println("Select Your Choice");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice){
                    case 1:
                        addAadhaar();
                        break;
                    case 2:
                        viewAllAadhaar();
                        break;
                    case 3:
                        UpdateAadhaar();
                        break;
                    case 4:
                        deleteAadhaar();
                        break;
                    case 5:
                        SearchAadhaar();
                        break;
                    case 6:
                        conn.close(); System.exit(0);

                    default:
                        System.out.println("Invalid Choice...!!");



                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    static public void addAadhaar() throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your Aadhaar Details");
        System.out.println("ENTER AADHAAR NUMBER : ");
        int no = sc.nextInt();
        sc.nextLine();
        System.out.println("ENTER AADHAAR NAME : ");
        String name = sc.nextLine();
        System.out.println("ENTER YOUR AADHAAR ADDRESS :");
        String address = sc.nextLine();
        System.out.println("ENTRE AADHAAR PINCODE :");
        int pincode = sc.nextInt();
        sc.nextLine();
        String query = "INSERT INTO aadhaar (aadhaar_no, aadhaar_name, aadhaar_address, pincode) VALUES (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1,no);
        ps.setString(2,name);
        ps.setString(3,address);
        ps.setInt(4,pincode);
        int affectcoloum = ps.executeUpdate();
        if(affectcoloum>0){
            System.out.println(affectcoloum+ " row's updated successfully..!");
        }
        else {
            System.out.println("Updation successfully");
        }
        System.out.println("Aadhaar card added..!");

    }
    static public  void viewAllAadhaar() throws SQLException {
        System.out.println(" HERE'S ALL AADHAAR CARD'S IN STORAGE UNIT");
        String query = "SELECT * FROM aadhaar";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(query);
        while (rs.next()) {
            System.out.println(rs.getInt("aadhaar_no") + " | " + rs.getString("aadhaar_name") + " | " + rs.getString("aadhaar_address") + " | " + rs.getInt("pincode"));
        }
    }
    static  public void UpdateAadhaar()throws SQLException{
        System.out.println("Enter Aadhaar Which you want  to Update :");
        int no = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter New Name :");
        String name = sc.nextLine();
        System.out.println("Enter New Address : ");
        String address = sc.nextLine();
        System.out.println("Enter new Pincode :");
        int pincode = sc.nextInt();
        sc.nextLine();
        String query = "UPDATE aadhaar set aadhaar_name=?, aadhaar_address=?, pincode=? WHERE aadhaar_no=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1,name);
        ps.setString(2,address);
        ps.setInt(3,pincode);
        ps.setInt(4,no);
         int rowaffect =ps.executeUpdate();
         if(rowaffect>0){
             System.out.println(rowaffect+" row update successfully..!");
         }
         else {
             System.out.println("Updation failed..!!");
         }
    }
    static public void deleteAadhaar() throws SQLException{
        System.out.println("Enter Aadhaar Number Which You Want to Delete From The Storage :");
        int no = sc.nextInt();
        sc.nextLine();
        String query = "DELETE FROM aadhaar where aadhaar_no=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1,no);
        int affectRows = ps.executeUpdate();
        if(affectRows>0){
            System.out.println(affectRows+" row's are deleted..");
        }
        else {
            System.out.println("Delection Failed..!");
        }

    }
    static public void SearchAadhaar() throws SQLException{
        System.out.println("Enter Aadhaar Number To See It Is Present In Data Or Not");
        int no = sc.nextInt();
        sc.nextLine();
        String query = "SELECT * FROM aadhaar WHERE aadhaar_no=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1,no);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            System.out.println("AADHAAR NUMBER: " + rs.getInt("aadhaar_no"));
            System.out.println("NAME: " + rs.getString("aadhaar_name"));
            System.out.println("ADDRESS: " + rs.getString("aadhaar_address"));
            System.out.println("PINCODE: " + rs.getInt("pincode"));
        } else {
            System.out.println("AADHAAR NOT FOUND..");
        }

    }
}
