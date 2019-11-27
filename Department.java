import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Department {

    private String dname;
    private int dnumber;
    private List<Employee> employees;

    public Department(String dname, int dnumber) {
        this.dname = dname;
        this.dnumber = dnumber;
        initializeEmployees();
        initializeEmployees();
    }

    private void initializeEmployees() {

        MakeConnection iconnect = new MakeConnection("deva.txt");
        Connection connection = iconnect.gConnection();

        ResultSet employeesSet = new QueryParser(connection)
                .getResult(("SELECT * FROM EMPLOYEE JOIN DEPARTMENT ON Dno = Dnumber WHERE Dno = " + this.dnumber));

        employees = new ArrayList<>();

        try {

            while (employeesSet.next()) {
                employees.add(new Employee(employeesSet.getString("Fname"), employeesSet.getString("Minit"), employeesSet.getString("Lname"), employeesSet.getInt("Ssn")));
            }

        } catch (SQLException e) {
            System.out.println("Couldn't initilaize employees in Department");
            e.printStackTrace();
        }

        iconnect.cConnection(connection);
    }

}