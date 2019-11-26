import java.sql.*;
import java.util.ArrayList;

public class Department{

    private String dname;
    private int dnumber;
    private List<Employee> employees;

    public Department(String dname, int dnumber){
        this.dname = dname;
        this.dnumber = dnumber;
        initializeEmployees();
    }

    private void initializeEmployees(){

        MakeConnection iconnect = new MakeConnection("deva.txt");
        Connection connection = iconnect.gConnection();

        ResultSet employeesSet = new QueryParser(connection).getResult(("SELECT Fname, Lname, Ssn FROM EMPLOYEE JOIN DEPARTMENT ON Dno = Dnumber WHERE Dno = " + this.dnumber));

        employees = new ArrayList<>();

        while (employeesSet.next()){
            employees.add(new Employee());
        }
    }
}