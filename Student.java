import java.util.*;

public class Student extends Member {
    // Member m = new Member();
    // Member m = new Member();
    
    static Random rand = new Random();
    // public static void main(final String[] args) {
    //     System.out.println("yeet");
    //     // Student student = new Student();
    //     // for (int i = 0; i < 20; i++) {
    //     // student.generate();
    //     // }
    // }

    public int compareTo(Student student) {
        return ID - student.ID;
    }
    public Student() {
        generate();
    }

    public Student(Student student) {
        super(student);
        major = new String(student.major);
        GPA = student.GPA;
    }

    // public Student(String major, float GPA) {

    // }
    @Override
    public void generate() {
        // ID = rnd.nextInt(999999999 - 100000000 ) + 100000000;
        // firstName = Names.firstName[rnd.nextInt(180)];
        // lastName = Names.lastName[rnd.nextInt(180)];
        super.generate();
        GPA = rand.nextFloat() * (4.0f - 0.0f) + 0.0f;
        major = Names.department[rand.nextInt(Names.department.length)];
        // System.out.println(major);
    }
    @Override
    public String toString() {
        // return String.format("%03d-%02d-%4d %10s %-10s %.2f %10s", ID / 1000000, ID / 10000 % 100, ID % 10000,
        //         firstName, lastName, GPA, major);
        return toString(false);
    }
    @Override
    public String toString(boolean lab) {
        return (lab ? "" : "") + super.toString(false) + String.format("%.2f %10s",GPA, major);
    }
    @Override
    public String htmlRow() {
        return "\t\t<TR>    " + htmlColumns() + "   </TR>";
    }
    @Override
    public String htmlColumns() {
        return super.htmlColumns() + String.format("<TD>%10s</TD> <TD>%.2f</TD>",major, GPA);
    }


    /*
    public String htmlRow();
    public String htmlColumns();
    */

    protected String major = null;
    float GPA = 0.0f;
}
