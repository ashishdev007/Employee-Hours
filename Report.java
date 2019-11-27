import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Report{

    private List<Department> departments;

    public Report(){
        departments = new ArrayList<>();
        initializeDepartments();
    }

    private void initializeDepartments(){
        MakeConnection iconnect = new MakeConnection("deva.txt");
        Connection connection = iconnect.gConnection();


        try{
            if(!connection.isClosed() && connection != null){
                ResultSet depts = new QueryParser(connection).getResult("SELECT Dname, Dnumber FROM DEPARTMENT ORDER BY Dnumber");
    
                while (depts.next()){
                    departments.add(new Department(depts.getString("Dname"), depts.getInt("Dnumber")));
                }

            } 
            else{
                System.out.println("Connection is Closed or it's null.");
            }
        }
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void generateReport(){
        for (Department department : departments) {
            System.out.println(department.getEmployeeHrs());
        }
    }
}