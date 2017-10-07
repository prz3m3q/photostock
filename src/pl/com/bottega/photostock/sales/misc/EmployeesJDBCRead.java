package pl.com.bottega.photostock.sales.misc;

import java.sql.*;
import java.util.Scanner;

public class EmployeesJDBCRead {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj imię:");
        String firstName = scanner.nextLine();
        System.out.println("Podaj nazwisko:");
        String lastName = scanner.nextLine();

        String sql = "select * from employees join salaries ON employees.emp_no = salaries.emp_no where first_name = ? and last_name = ? and now() < salaries.to_date";
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/employees?user=root&password=pass&useSSL=false");
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.printf("%d %s %s %s %s %s %s",
                resultSet.getLong("emp_no"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("birth_date"),
                resultSet.getString("gender"),
                resultSet.getString("hire_date"),
                resultSet.getString("salary")
            );
            System.out.println();
        }
    }
}
