import java.util.ArrayList;

public class EmployeeCRUD {
    private ArrayList<Employee> employees = new ArrayList<>();

    // Create
    public void addEmployee(Employee e) {
        employees.add(e);
    }

    // Read
    public void viewEmployees() {
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    // Update
    public boolean updateEmployee(int id, String newName, String newDept) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                e.setName(newName);
                e.setDepartment(newDept);
                return true;
            }
        }
        return false;
    }

    // Delete
    public boolean deleteEmployee(int id) {
        return employees.removeIf(e -> e.getId() == id);
    }

    // Main to test
    public static void main(String[] args) {
        EmployeeCRUD crud = new EmployeeCRUD();

        crud.addEmployee(new Employee(1, "Alice", "HR"));
        crud.addEmployee(new Employee(2, "Bob", "IT"));

        System.out.println("All Employees:");
        crud.viewEmployees();

        crud.updateEmployee(2, "Bobby", "Finance");
        System.out.println("\nAfter Update:");
        crud.viewEmployees();

        crud.deleteEmployee(1);
        System.out.println("\nAfter Deletion:");
        crud.viewEmployees();
    }
}
