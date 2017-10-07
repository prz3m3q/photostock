package pl.com.bottega.photostock.sales.misc;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class EmployeesJDBCWrite {

    private static final Date TO_DATE_MAX = Date.valueOf("9999-01-01");
    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employees (first_name, last_name, birth_date, gender, hire_date) VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT_SALARY_SQL = "INSERT INTO salaries (emp_no, salary, from_date, to_date) VALUES (?, ?, ?, ?)";
    private static final String INSERT_TITLE_SQL = "INSERT INTO titles (emp_no, title, from_date, to_date) VALUES (?, ?, ?, ?)";
    private static final String INSERT_DEPARTMENT_SQL = "INSERT INTO departments (dept_no, dept_name) VALUES (?, ?)";
    private static final String SELECT_DEPARTMENT_SQL = "SELECT dept_no FROM departments WHERE dept_name = ? LIMIT 1";
    private static final String INSERT_DEPARTMENT_EMPLOYEE_SQL = "INSERT INTO dept_emp (emp_no, dept_no, from_date, to_date) VALUES (?, ?, ?, ?)";
    private static final String NEXT_DEPARTMENT_ID_SQL = "SELECT CONCAT('d', LPAD(substr(dept_no, 2) + 1, 3, '0')) AS next_dept_id FROM departments ORDER BY dept_no DESC LIMIT 1;";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String firstName = getFirstName(scanner);
        String lastName = getLastName(scanner);
        LocalDate hireDate = LocalDate.now();
        String gender = getGender(scanner);
        LocalDate birthDate = getBirthDate(scanner);
        String departmentName = getDepartmentName(scanner);
        String title = getTitle(scanner);
        Integer salary = getSalary(scanner);

        Connection conn = getConnection();
        conn.setAutoCommit(false);

        Long employeeNumber = InsertEmployee(conn, firstName, lastName, hireDate, gender, birthDate);
        insertSalary(conn, employeeNumber, salary);
        insertTitle(conn, employeeNumber, title);
        insertDepartment(conn, employeeNumber, departmentName);

        conn.commit();
    }

    private static void insertDepartment(Connection conn, Long employeeNumber, String departmentName) throws SQLException {
        String deptNo = getOrCreateDepartment(conn, departmentName);
        PreparedStatement preparedStatement = conn.prepareStatement(INSERT_DEPARTMENT_EMPLOYEE_SQL);
        preparedStatement.setLong(1, employeeNumber);
        preparedStatement.setString(2, deptNo);
        preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
        preparedStatement.setDate(4, TO_DATE_MAX);
        preparedStatement.executeUpdate();
    }

    private static String getOrCreateDepartment(Connection conn, String departmentName) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(SELECT_DEPARTMENT_SQL);
        preparedStatement.setString(1, departmentName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("dept_no");
        }
        preparedStatement = conn.prepareStatement(INSERT_DEPARTMENT_SQL);
        String nextDeptNumber = getNextDepartmentNumber(conn);
        preparedStatement.setString(1, nextDeptNumber);
        preparedStatement.setString(2, departmentName);
        preparedStatement.executeUpdate();
        return nextDeptNumber;
    }

    public static String getNextDepartmentNumber(Connection conn) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(NEXT_DEPARTMENT_ID_SQL);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("next_dept_id");
    }

    private static void insertTitle(Connection conn, Long employeeNumber, String title) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(INSERT_TITLE_SQL);
        preparedStatement.setLong(1, employeeNumber);
        preparedStatement.setString(2, title);
        preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
        preparedStatement.setDate(4, TO_DATE_MAX);
        preparedStatement.executeUpdate();
    }

    private static void insertSalary(Connection conn, Long employeeNumber, Integer salary) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(INSERT_SALARY_SQL);
        preparedStatement.setLong(1, employeeNumber);
        preparedStatement.setInt(2, salary);
        preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
        preparedStatement.setDate(4, TO_DATE_MAX);
        preparedStatement.executeUpdate();
    }

    private static Long InsertEmployee(Connection conn, String firstName, String lastName, LocalDate hireDate, String gender, LocalDate birthDate) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(INSERT_EMPLOYEE_SQL, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setDate(3, Date.valueOf(birthDate));
        preparedStatement.setString(4, gender);
        preparedStatement.setDate(5, Date.valueOf(hireDate));
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        rs.next();
        return rs.getLong(1);
    }

    private static String getFirstName(Scanner scanner) {
        System.out.println("Podaj imię:");
        return scanner.nextLine();
    }

    private static String getLastName(Scanner scanner) {
        System.out.println("Podaj nazwisko:");
        return scanner.nextLine();
    }

    private static Integer getSalary(Scanner scanner) {
        System.out.println("Wpisz zarobki:");
        return scanner.nextInt();
    }

    private static String getTitle(Scanner scanner) {
        System.out.println("Podaj stanowisko:");
        return scanner.nextLine();
    }

    private static String getDepartmentName(Scanner scanner) {
        System.out.println("Podaj nazwę działu:");
        return scanner.nextLine();
    }

    private static LocalDate getBirthDate(Scanner scanner) {
        System.out.print("Podaj datę urodzenia:");
        return LocalDate.parse(scanner.nextLine());
    }

    private static String getGender(Scanner scanner) {
        System.out.println("Podaj płeć:");
        return scanner.nextLine();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/employees?user=root&password=pass&useSSL=false");
    }
}
