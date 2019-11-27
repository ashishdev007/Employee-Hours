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
                double work_hrs;

                try{
                    work_hrs = projectSet.getDouble("Hours");
                }
                catch(NullPointerException e){
                    work_hrs = 0.0;
                }

                this.projects.add(new Project(projectSet.getString("Pname"), projectSet.getInt("Pnumber"), work_hrs));
            }

        } catch (SQLException e) {
            System.out.println("Couldn't initilaize projects in Employee");
            e.printStackTrace();
        }
    }

    public double getTotalHrs(){
        double total = 0.0;

        for (Project project : projects) {
            total += project.getHours();
        }

        return total;
    }

    //Sort the employees by Lname

    public String getHoursBreakdown(){

        StringBuilder sb = new StringBuilder();

        String empName = this.fName + " " + this.lName;
        sb.append(String.format("\n\n%2s%s","", empName));

        for (Project project : projects) {
            sb.append(String.format("\n%5s%-30s%4.1f", "", project.getName(), project.getHours()));
        }

        double empHrs = this.getTotalHrs();

        if(empHrs != 0.0){

            String temp =  String.format("%.1f", empHrs);

            System.out.printf("\n%35s", "");
            for (int i = 0; i < temp.length(); i++) {
                System.out.print("-");
            }

            System.out.printf("\n%35s%4.1f", "", empHrs);  
        } 

        return sb.toString();
    }

    public int compareTo(Employee e){
        String thisName = this.lName.toUpperCase() + " " +this.fName.toUpperCase() + " " + this.mInit.toUpperCase();
        String thatName = e.lName.toUpperCase() + " " + e.fName.toUpperCase() + " " + e.mInit.toUpperCase();

        return thisName.compareTo(thatName);
    }


}