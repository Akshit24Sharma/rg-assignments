import java.sql.*;

public class EmployeeJDBC {

    private final String URL = "jdbc:mysql://localhost:3306/your_db";
    private final String USER = "root";
    private final String PASS = "your_password";

    // Create
    public void addEmployee(Employee e) throws SQLException {
        String sql = "INSERT INTO employees (id, name, department) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, e.getId());
            stmt.setString(2, e.getName());
            stmt.setString(3, e.getDepartment());
            stmt.executeUpdate();
        }
    }

    // Read
    public void viewEmployees() throws SQLException {
        String sql = "SELECT * FROM employees";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " +
                                   rs.getString("name") + " - " +
                                   rs.getString("department"));
            }
        }
    }

    // Update
    public void updateEmployee(int id, String name, String dept) throws SQLException {
        String sql = "UPDATE employees SET name = ?, department = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, dept);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }

    // Delete
    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Main to test
    public static void main(String[] args) throws SQLException {
        EmployeeJDBC db = new EmployeeJDBC();

        db.addEmployee(new Employee(1, "Alice", "HR"));
        db.addEmployee(new Employee(2, "Bob", "IT"));

        System.out.println("All Employees:");
        db.viewEmployees();

        db.updateEmployee(2, "Bobby", "Finance");
        System.out.println("\nAfter Update:");
        db.viewEmployees();

        db.deleteEmployee(1);
        System.out.println("\nAfter Deletion:");
        db.viewEmployees();
    }
}
