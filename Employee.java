public class Employee{
    private String fName;
    private String mInit;
    private String lName;
    private int ssn;

    public Employee(String fName, String mInit, String lName, int ssn){
        this.fName = fName;
        this.mInit = mInit;
        this.lName = lName;
        this.ssn = ssn;        
    }

    public int compareTo(Employee e){
        String thisName = this.lName.toUpperCase() + " " +this.fName.toUpperCase() + " " + this.mInit.toUpperCase();
        String thatName = e.lName.toUpperCase() + " " + e.fName.toUpperCase() + " " + e.mInit.toUpperCase();

        return thisName.compareTo(thatName);
    }


}