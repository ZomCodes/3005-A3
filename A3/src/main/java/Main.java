import java.sql.*;
import java.time.LocalDate;
import java.util.Date;


public class Main {

    public static void addStudent(String firstName, String lastName, String email, java.sql.Date enrollmentDate) {
        String url = "jdbc:postgresql://localhost:5432/University"; // My information to connect
        String user = "postgres";
        String password = "John10";

        try (Connection connection = DriverManager.getConnection(url, user, password)) { // connecting to database
            String sql = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)"; //script to insert

            try (PreparedStatement stmt = connection.prepareStatement(sql)) { //information for inserting
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, email);
                stmt.setDate(4, enrollmentDate);

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) { // checking
                    System.out.println("Student added successfully!");
                } else {
                    System.out.println("Error adding student.");
                }
            }
        } catch (SQLException e) { //error
            System.out.println(e);
        }
    }

    public static void getAllStudents(){
        String url = "jdbc:postgresql://localhost:5432/University";
        String user = "postgres";
        String password = "John10";

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM students"; //script for querying all students
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) { // setting temporary variables for formatting later
                int studentId = rs.getInt("student_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                Date enrollmentDate = rs.getDate("enrollment_date");

                System.out.println("Student ID: " + studentId + ", Name: " + firstName + " " + lastName +
                        ", Email: " + email + ", Enrollment Date: " + enrollmentDate); // formatting and printing
            }

        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void updateStudentEmail(int studentId, String newEmail) {
        String url = "jdbc:postgresql://localhost:5432/University";
        String user = "postgres";
        String password = "John10";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE students SET email = ? WHERE student_id = ?"; // script for updating

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, newEmail); // email
                stmt.setInt(2, studentId); // id

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) { //checking
                    System.out.println("Email updated successfully!");
                } else {
                    System.out.println("Error updating student. (Student ID may not exist)");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error updating student email: " + e.getMessage());
        }
    }

    public static void deleteStudent(int studentId) {
        String url = "jdbc:postgresql://localhost:5432/University";
        String user = "postgres";
        String password = "John10";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM students WHERE student_id = ?"; //script for deleting

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, studentId); //selecting

                int rowsAffected = stmt.executeUpdate(); // deleting

                if (rowsAffected > 0) { // checking
                    System.out.println("Student deleted successfully!");
                } else {
                    System.out.println("Error deleting student. (Student ID may not exist)");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
        }
    }


    public static void main(String[] args){

        // TESTS

        //getAllStudents();

        //getAllStudents();
        //java.sql.Date enrollmentDate = java.sql.Date.valueOf(LocalDate.of(2023, 9, 5));
        //addStudent("Lebron", "James", "lebron.james@example.com", enrollmentDate);
        //getAllStudents();

        //getAllStudents();
        //updateStudentEmail(,"lebron.james@gmail.com");
        //getAllStudents();

        getAllStudents();
        deleteStudent(11);
        getAllStudents();

    }
}


