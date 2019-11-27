import java.util.HashMap;
import java.util.Map;
import java.sql.*;

public class Project {
    private int pNo;
    private String pName;
    private int dNo;
    private Map<Integer, Float> hours;

    public Project(String pName, int pNo, int dNo) {

        this.pName = pName;
        this.pNo = pNo;
        this.dNo = dNo;

        initializeHours();
    }

    public void initializeHours() {
        MakeConnection iconnect = new MakeConnection("deva.txt");
        Connection connection = iconnect.gConnection();

        ResultSet hoursSet = new QueryParser(connection)
                .getResult("SELECT Essn, Hours FROM WORKS_ON WHERE Pno = " + this.pNo);

        this.hours = new HashMap<>();

        try {
            while (hoursSet.next()) {
                this.hours.put(hoursSet.getInt("Essn"), hoursSet.getFloat("Hours"));
            }

        } catch (SQLException e) {
            System.out.println("Couldn't initilaize hours in Project");
            e.printStackTrace();
        }
    }
}