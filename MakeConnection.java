import java.sql.*;
import java.io.FileReader;
import java.util.Properties;

public class MakeConnection{

    private String driver, user, password, url;

    public MakeConnection(String fileName){
        try{
            FileReader reader = new FileReader(fileName);
        
            Properties p = new Properties();
            p.load(reader);
    
            driver = p.getProperty("db.driver");
            user = p.getProperty("db.user");
            password = p.getProperty("db.password");
            url = p.getProperty("db.url");
        }
        catch(Exception e){
            System.out.println("Exception: " + e);
        }
    }

    public Connection gConnection(){
        
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            return DriverManager.getConnection(url, user, password);
        }
        catch(Exception e){
            System.out.println("Exception: " + e);
            return null;
        }
    }

    public void cConnection(Connection con){
		try{
			if(con != null){
				con.close();
			}
		}catch(SQLException e){
			System.out.println("Error: " + e.getMessage());
		}
	}
}