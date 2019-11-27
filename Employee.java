import java.sql.*;
import java.util.List;

public class Employee{
    private String fName;
    private String mInit;
    private String lName;
    private int ssn;
    private List<Project> projects;

    public Employee(String fName, String mInit, String lName, int ssn){
        this.fName = fName;
        this.mInit = mInit;
        this.lName = lName;
        this.ssn = ssn;    
        
        initializeProjects();
    }


    private void initializeProjects(){

        MakeConnection iconnect = new MakeConnection("deva.txt");
        Connection connection = iconnect.gConnection();

        ResultSet projectSet = new QueryParser(connection).getResult("SELECT * FROM WORKS_ON JOIN PROJECT ON Pnumber=Pno WHERE Essn=" + this.ssn);

        try {

            while (projectSet.next()) {
                this.projects.add(new Project(projectSet.getString("Pname"), projectSet.getInt("Pnumber"), projectSet.getFloat("Hours")));
            }

        } catch (SQLException e) {
            System.out.println("Couldn't initilaize projects in Employee");
            e.printStackTrace();
        }
    }

    public int compareTo(Employee e){
        String thisName = this.lName.toUpperCase() + " " +this.fName.toUpperCase() + " " + this.mInit.toUpperCase();
        String thatName = e.lName.toUpperCase() + " " + e.fName.toUpperCase() + " " + e.mInit.toUpperCase();

        return thisName.compareTo(thatName);
    }


}