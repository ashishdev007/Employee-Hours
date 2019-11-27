import java.util.HashMap;
import java.util.Map;
import java.sql.*;

public class Project {
    private int pNo;
    private String pName;
    private float hours;

    public Project(String pName, int pNo, float hours) {

        this.pName = pName;
        this.pNo = pNo;
        this.hours = hours;
    }

    public String getName(){
        return this.pName;
    }

    public float hours(){
        return this.hours;
    }
}