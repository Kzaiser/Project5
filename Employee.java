import java.util.Random;

public class Employee extends Member {
    static Random rnd = new Random();
    public int compareTo(Employee employee) {
        return ID - employee.ID;
    }
    public Employee () {
        generate();
    }

    public Employee(Employee employee) {
        super(employee);
        department = new String(employee.department);
        yearHired = employee.yearHired;
    }

    @Override 
    public void generate() {
        super.generate();
        //ID = rnd.nextInt(999999999 - 100000000 ) + 100000000;
        //firstName = Names.firstName[rnd.nextInt(180)];
        //lastName = Names.lastName[rnd.nextInt(180)];
        department = Names.department[rnd.nextInt(Names.department.length)];
        yearHired = rnd.nextInt(2020-1990) + 1990;
    }

    @Override
    public String toString() {
        return toString(false);
        // return String.format("%03d-%02d-%04d %10s %-10s %30s %30d", ID/1000000,
        // ID/10000 % 100, ID % 10000, firstName, lastName, department, yearHired);
    }
    @Override
    public String toString(boolean lab) {
        // return (lab ? "EMP" : "") + String.format("%03d-%02d-%04d %10s %-10s %10s %4d", ID/1000000,
        // ID/10000 % 100, ID % 10000, firstName, lastName, department, yearHired);
        return (lab ? "" : "") + super.toString(false) + String.format("%10s %4d", department, yearHired);
    }

    @Override
    public String htmlRow() {
        return "\t\t<TR>    " + htmlColumns() + "   </TR>";
    }

    @Override
    public String htmlColumns() {
        return super.htmlColumns() + String.format("<TD>%10s</TD> <TD>%4d</TD>", department, yearHired);
    }

    protected String department = null;
    int yearHired = 1990;

}

