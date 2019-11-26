import java.sql.*;

public class QueryParser{

    private Statement statement;

    public QueryParser(Connection connect){

        try{
            this.statement = connect.createStatement();
        }

        catch(SQLException e){
			System.out.println("Can't Create Statement in QueryParser.");
        }
    }

    public ResultSet getResult(String query){

        ResultSet result = null;

        try{            
            result = this.statement.executeQuery(query);
        }
        
        catch(SQLException e){
			System.out.println("Error: " + e.getMessage());
        }
        
        return result;
    }
    

}