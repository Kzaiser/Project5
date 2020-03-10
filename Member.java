import java.util.Random;

public class Member implements Comparable<Member> {
    static Random rnd = new Random();
    public int compareTo(Member m) {
        return ID - m.ID;
    }
    public Member () {
        generate();
    }
    public Member(Member m) {
        ID = m.ID;
        firstName= new String(m.firstName);
        lastName = new String(m.lastName);
        // do this because you have to create a new string when using a copy constructor in this case.
    }
    public void generate() {
        ID = rnd.nextInt(999999999 - 100000000 ) + 100000000;
        firstName = Names.firstName[rnd.nextInt(180)];
        lastName = Names.lastName[rnd.nextInt(180)];
    }
    public String toString() {
        //return String.format("%03d-%02d-%04d %10s %-10s", ID/1000000,
        //ID/10000 % 100, ID % 10000, firstName, lastName);
        return toString(false);
    }
    
    public String toString(boolean lab) {
        return (lab ? "MEM" : "") + String.format("%03d-%02d-%04d %10s %-10s", ID/1000000,
        ID/10000 % 100, ID % 10000, firstName, lastName);
    }

    public String htmlRow() {
        return "/t/t<TR>    " + htmlColumns() + "   </TR>";
    }

    public String htmlColumns() {
        return String.format("<TD>%03d-%02d-%4d</TD> <TD>%10s</TD> <TD>%-10s</TD>", ID / 1000000, ID / 10000 % 100, ID % 10000, firstName,
        lastName);
    }

    protected String firstName = null, lastName = null;
    int ID = 0;
}

/* 
Test for Copy Constructor 

public Member makeCopy (Member m) {
    return new m.getClass(m)
}
You can not do this because the class has to be defined oringally.

public Member makeCopy (Member m) {
    return new m.getClass.getName(m);
}

This still does not work.

public makeCopy (Member m) {
    switch(m.getClass().getName) {
        case "Member":
            return new Member(m);
        case "Student":
            return new Student(m);
        case "Employee":
            return new Employee(m);
        etc.....
    }
}

This works because it will return a string.
*/