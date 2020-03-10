import java.util.Random;

public class Faculty extends Employee {
    static Random rnd = new Random();
    public int compareTo(Faculty faculty) {
        return ID - faculty.ID;
    }
    
    public Faculty () {
        generate();
    }

    public Faculty(Faculty faculty) {
        super(faculty);
        degree = new String(faculty.degree);
    }

    @Override
    public void generate() {
        super.generate();
        degree = Names.degree[rnd.nextInt(Names.degree.length)];
    }
    @Override
    public String toString() {
        return toString(false);
    }
    
    @Override
    public String toString(boolean lab) {
        return (lab ? "" : "") + super.toString(false) + String.format(" %10s", degree);
    }

    @Override
    public String htmlRow() {
        return "\t\t<TR>    " + htmlColumns() + "   </TR>";
    }

    @Override
    public String htmlColumns() {
        return super.htmlColumns() + String.format("<TD>%10s</TD>", degree);
    }

    protected String degree = null;
}
