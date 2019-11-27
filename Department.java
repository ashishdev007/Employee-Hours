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

    public double getTotalHrs(){

        double total = 0.0;

        for (Employee employee : employees) {
            total += employee.getTotalHrs();
        }

        return total;
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

    public String getEmployeeHrs(){

        StringBuilder sb = new StringBuilder();

        sb.append("\n" + this.dname);

        for (Employee employee : employees) {
            sb.append(employee.getHoursBreakdown());
        }

        sb.append(String.format("\n\n%-7s%d Employee", "Total: ", this.employees.size()));
        sb.append(String.format("\n%-7s%-4.1f Hours", "", this.getTotalHrs()));

        return sb.toString();
        // printf("\n\n%2s%s","", empName);
    }

}