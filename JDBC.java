import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class JDBC{
  public static void main(String[] args) {

    Connection connection = null;
    Statement statement;

    try{
      
      MakeConnection con = new MakeConnection("deva.txt");
      connection = con.gConnection();
      
      List<String> names = new ArrayList<>();

      if(!connection.isClosed()){

        statement = connection.createStatement();

        String query = "Select CONCAT(Lname, ', ', Fname, ' ', Minit) AS Name FROM EMPLOYEE";

        ResultSet result = statement.executeQuery(query);

        while(result.next()){
          
          names.add(result.getString("Name"));

        }

        Collections.sort(names);

        for (String name : names) {
          System.out.println(name);
        }
      }
    }

    catch(Exception e){
      System.out.println("Exception: " + e);
    }

    finally{
      if(connection != null){
        con.cConnection(connection);
      }
    }

  }
}
