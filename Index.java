import java.sql.*;
import java.text.DecimalFormat;

public class Index{
    public static void main(String[] args) {
        
        MakeConnection iconnect = new MakeConnection("deva.txt");
        Connection connection = iconnect.gConnection();

        DecimalFormat df = new DecimalFormat("#.#");

        try{
            if(!connection.isClosed() && connection != null){
                ResultSet departments = new QueryParser(connection).getResult("SELECT Dname, Dnumber FROM DEPARTMENT");
    
                while (departments.next()){

                    String dName = departments.getString("Dname");
                    int dNumber = departments.getInt("Dnumber");
                    
                    System.out.print("\n" + dName);

                    ResultSet employees = new QueryParser(connection).getResult(("SELECT Fname, Lname, Ssn FROM EMPLOYEE JOIN DEPARTMENT ON Dno = Dnumber WHERE Dno = " + dNumber + " ORDER BY Fname, Lname"));
                    int noOfEmp = 0;
                    float totHrs = 0;

                    while (employees.next()){

                        noOfEmp++;

                        String empName = employees.getString("Fname") + " " + employees.getString("Lname");
                        int empSsn = employees.getInt("Ssn");
                        float empHrs = 0;
                
                        System.out.printf("\n\n%2s%s","", empName);

                        String works = "SELECT Pname, Hours FROM (EMPLOYEE JOIN WORKS_ON ON Ssn = Essn) JOIN PROJECT ON Pno = Pnumber WHERE Ssn = " + empSsn;

                        ResultSet contributions = new QueryParser(connection).getResult(works);

                        while(contributions.next()){
                            
                            String project_name = contributions.getString("Pname");
                            float work_hrs;

                            try{
                                work_hrs = contributions.getFloat("Hours");
                            }
                            catch(NullPointerException e){
                                work_hrs = 0;
                            }


                            totHrs += work_hrs;
                            empHrs += work_hrs;

                            System.out.printf("\n%5s%-30s%4.1f", "", project_name, work_hrs);
                        }

                        if(empHrs != 0.0){

                            String temp =  String.format("%.1f", empHrs);

                            System.out.printf("\n%35s", "");
                            for (int i = 0; i < temp.length(); i++) {
                                System.out.print("-");
                            }

                            System.out.printf("\n%35s%4.1f", "", empHrs);  
                        }                                            

                    }     
                    
                    System.out.printf("\n\n%-7s%d Employee", "Total: ", noOfEmp);
                    System.out.printf("\n%-7s%-4.1f Hours", "", totHrs);
                    
                    System.out.println("");
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
}