import java.sql.*;
import java.util.Scanner;

public class CRUD_Statement_Simple {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3307/college_db";
        String user = "root";
        String pass = "harshad1";

        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement st = conn.createStatement();

            while (true) {

                System.out.println("\n==============================");
                System.out.println(" SIMPLE CRUD USING STATEMENT ");
                System.out.println("==============================");
                System.out.println("1. Insert Data");
                System.out.println("2. View Data");
                System.out.println("3. Update Data (by ID)");
                System.out.println("4. Delete Data (by ID)");
                System.out.println("5. Exit");
                System.out.println("Enter choice: ");
                int ch = sc.nextInt();

                switch (ch) {

                    case 1:
                        // INSERT without id (AUTO_INCREMENT)
                        st.executeUpdate(
                            "INSERT INTO students(name, course, marks) VALUES ('harshad', 'bca', 90)"
                        );
                        System.out.println("Data Inserted Successfully!");
                        break;

                    case 2:
                        // VIEW data including id
                        ResultSet rs = st.executeQuery("SELECT * FROM students");

                        System.out.println("\nID\tName\tCourse\tMarks");
                        System.out.println("----------------------------------------");

                        while (rs.next()) {
                            System.out.println(
                                rs.getInt("id") + "\t" +
                                rs.getString("name") + "\t" +
                                rs.getString("course") + "\t" +
                                rs.getInt("marks")
                            );
                        }
                        break;

                    case 3:
                        // UPDATE using ID
                        System.out.println("Enter ID to update marks: ");
                        int uid = sc.nextInt();

                        st.executeUpdate(
                            "UPDATE students SET marks = 95 WHERE id = " + uid
                        );

                        System.out.println("Marks Updated Successfully!");
                        break;

                    case 4:
                        // DELETE using ID
                        System.out.println("Enter ID to delete: ");
                        int did = sc.nextInt();

                        st.executeUpdate(
                            "DELETE FROM students WHERE id = " + did
                        );

                        System.out.println("Data Deleted Successfully!");
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        conn.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
