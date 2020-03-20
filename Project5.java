/*
    CMPS 3390: Java Advanced Programming
    Project 5: Hashtable
    Created by:
        Zachary Kaiser
        Guangjin Liu
        Lawrence Marquez
    How to create a T type of array

*/

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;
// import java.util.Iterator;


public class Project5 {
    static int capacity;
    static float maxLDF;
    static boolean bool = false;
    static Scanner scanner = new Scanner(System.in);
    // static Hash<Member> hashTable = new Hash<Member>(Member.class);
    static Hash<Member> hashTable;
    static Member m = null;
    static Random rand = new Random();
    static String help[] = {
    "Enter a command: H/h/? to show Command Menu, or Q to Quit: "
    };
    static String menu[] = {
            "+===========================================================================================================================+",
            "|                                                Implementation of Hashtable                                                |",
            "|                                                        Assignment                                                         |",
            "+===========================================================================================================================+",
            "| Command  |                   Description                    | Command  |                   Description                    |",
            "+----------+--------------------------------------------------+----------+--------------------------------------------------+",
            "|   g G    |  Prompt for two integers, the capacity and the   |   t T    | Perform a successful search on each object in    |",
            "|          |  load factor of a hash table. Create a new hash  |show Time | the hash table, and 'capacity' many unsuccessful |",
            "|          |table, with 20% as increment percentage, generate |Complexity| searches, list the (1) average comparisons from  |",
            "|          |(capacity * load factor) many mixed Member objects|of Binary |all successful searches, the theoretic successful |",
            "|          |         and add them to the hash table.          | and Hash |search complexity [(1 + 1/(1-a))/2], and the      |",
            "+----------+--------------------------------------------------+  Search. |theoretical un-successful search time complexity  |",
            "|   a A    | Instantiate a new member object, and the object  |          |[(1+1/(1-a)**2)/2], where a is the loading factor.|",
            "|          |   into the hash table. Display the newly added   +----------+--------------------------------------------------+",
            "|          |  member, its home address and current address.   |   b B    |Display information on blocks formed by contiguous|",
            "+----------+--------------------------------------------------+          |data or empty cells inside table. For each block, |",
            "|   r R    | Ask for an ID or hash code of an object. Remove  |          |display the type of block (either data or empty), |",
            "|          |     the object whose ID matches the given ID.    |          |the starting and ending addresses, size of block. |",
            "+----------+--------------------------------------------------+          |At the end of block listing, show the total number|",
            "|   f F    |   Ask for an ID or hash code, of a object,       |          |   of blocks, the maximum, the minimum and the    |",
            "|          | display the object, ID current address and home  |          |  average block sizes, for each type. Allow quit  |",
            "|          |                     address.                     |          | listing the total, maximum, minimum and average  |",
            "+----------+--------------------------------------------------+          |must show the correct data even if the listing of |",
            "|   c C    |Show contents of hash table in a tabular way. one |          |                   block ends.                    |",
            "|          | object per line and ten objects per screen. for  +----------+--------------------------------------------------+",
            "|          |   each line, the following columns have to be    |   p P    |List the table parameters. The parameters include.|",
            "|          | displayed: Object, current address, home address |          |    the capacity, size, load factor, increment    |",
            "|          |  and the displacement from home address to its   |          |      percentage,and the actual load factor.      |",
            "|          |                 current address.                 +----------+--------------------------------------------------+",
            "+----------+--------------------------------------------------+   v V    |    Verify whether all non-null elements in table |",
            "|  i I x   |   Remove object at I, and do not shift.          |          |         are searchable or not.                   |",
            "+----------+--------------------------------------------------+----------+--------------------------------------------------+",
            "|  h H ?   |                 Show this menu.                  |   q Q    |                Exit the program.                 |",
            "+----------+--------------------------------------------------+----------+--------------------------------------------------+"
    };
    static String equalLine =     "+===================================================+";
    static String hyphenLine =    "+---------------------------------------------------+";
    static String tableTitle =    "|                Contents of Hash Table                |";
    static String columnLabels1 = "|         Object Value         |Current| Home  |Displac|";
    static String columnLabels2 = "|                              |Address|Address| ement |";
    static String spaceString =   "                                                            ";
    static String blocks[] = {
                                    "+===================================================+",
                                    "|       Information on Data and Blank Blocks        |",
                                    "+===================================================+",
                                    "| Block Type |  Starting  |   Ending   |    Size    |",
                                    "|            |  Address   |  Address   |            |",
                                    "+------------+------------+------------+------------+"
    };
    static String blockHyphenLine = "+------------+-------+---------+---------+----------+";
    static String parameterBlockTitle[] = {
                                    "+================================================================+",
                                    "|                  Parameters of The Hash Table                  |",
                                    "+================================================================+",
    };
    static String blockLabels[] = {
                                    "|  Capacity  |    Size    | Increment  | Specified  |Actual Load |",
                                    "|            |            |            |Load Factor |   Factor   | "
    };
    static String timeComplexityTable[] = {
                                    "+================================================================+",
                                    "|      Time Complexities of Practical & Theoretic Hashtable      |",
                                    "|          Search vs. Theoretic Binary Search Algorithm          |",
                                    "+================================================================+",
                                    "| Practical  | Practical  | Theoretic  | Theoretic  |Theoretical |",
                                    "| HashTable  | HashTable  | HashTable  | HashTable  |   Binary   |",
                                    "| Successful |Unsuccessful| Successful |Unsuccessful|   Search   |",
                                    "|   Search   |   Search   |   Search   |   Search   |            |",
                                    "+------------+------------+------------+------------+------------+"
    };
    static String timeComplexityLine = "+------------+------------+------------+------------+------------+";

    


    
    static void showHelpMenu() {
        for (int i = 0; i < menu.length; i++) {
                System.out.printf("\n%s", menu[i]);
        }
    }

    static char showAndGetMenuValue() {
        for(int i=0; i<help.length; i++) {
            System.out.printf("\n\t\t\t%s", help[i]);
        }

        return scanner.next().trim().charAt(0);
    }

    static void printStuff(String [] stuff) { 
        for (String s : stuff) {
            System.out.println(s);
        }
    }
    public static void main(String args[] ) {
        char choice = ' ';

        while(true) {
            choice = showAndGetMenuValue();

            switch(choice) {
                case 'H':
                case 'h':
                case '?':
                    showHelpMenu();
                    break;
                case 'G':
                case 'g':
                    generateAndStore();
                    break;
                case 'A':
                case 'a':
                    generateSingleAndStore();
                    break;
                case 'R':
                case 'r':
                    removeMemberWithID();
                    break;
                case 'F':
                case 'f':
                    displayMemberWithID();
                    break;
                case 'C':
                case 'c':
                    printStuff(blocks);
                    break;
                case 'I':
                case 'i':
                case 'X':
                case 'x':
                    break;
                case 'T':
                case 't':
                    break;
                case 'B':
                case 'b':
                    break;
                case 'P':
                case 'p':
                    break;
                case 'V':
                case 'v':
                    break;
                case 'Q':
                case 'q':
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nRun the program again with a different input!");
                    break;
            }
        }
    }

    /*
        "| G: Prompt for two integers, the capacity and the  |",
        "|   load factor of a hash table. Create a new hash  |",
        "|  table, with 20% as increment percentage, generate|",
        "|  (capacity * load factor) many mixed Member       |",
        "|          and add them to the hash table           |
    */
    static void generateAndStore() {
        System.out.printf("\n\t\t Input an integer and a float! (Capacity of Hash Table, Max Loading Factor)\n\t\t");
        try {
            capacity = scanner.nextInt();
            maxLDF = scanner.nextFloat();
        } catch (InputMismatchException e) {
            return;
        }
        
        // Initialize new hash table
        hashTable = new Hash<Member>(Member.class, capacity, maxLDF);
        // Generate (capacity * load factor) many mixed members and add them to the hashTable
        int capXmaxLDF = Math.round(capacity * maxLDF);
        
        for(int i = 0; i < capXmaxLDF; i++) {
            m = nextMember();
            hashTable.add(m);
            // System.out.println(m.toString(true));
        }
        // hashTable.display();


    }

    static Member nextMember() {
        switch(rand.nextInt(5)) {
            case 0:
                return new Member();
            case 1:
                return new Employee();
            case 2:
                return new Student();
            case 3:
                return new Faculty();
            case 4:
                return new Staff();
        }
        return new Member();
    }

    // "| A: Instantiate a new member object, and the object|",
    // "|  into the hash table. Display the newly added     |",
    // "|  member, its home address, and current address.   |",
    static void generateSingleAndStore() {
        // Init new random member object
        m = nextMember();
        // Add Object to hashTable
        hashTable.add(m);
        // hashTable.display();
    }

    // "+---------------------------------------------------+",
    // "| R: Ask for an ID or hash code of an object. Remove|",
    // "|      the object whose ID matches the given ID.    |",
    // "+---------------------------------------------------+",

    static void removeMemberWithID() {
        System.out.println("\n\t\t Enter a hash code/ID for member within hash table to remove.");
        // Retrieve Hash Code / Hash ID
        int hashID;
        hashTable.display();
        try {
            hashID = scanner.nextInt();
        } catch (Exception e) {
            return;
        }

        // Remove the object whose ID matches the given ID
        hashTable.remove(hashID);
        hashTable.display();
    }

    // "+---------------------------------------------------+",
    // "| F: Ask for an ID or hash code of an object,       |",
    // "|      display the object, ID, current address, and |",
    // "|      home address.                                |",
    // "+---------------------------------------------------+",
    static void displayMemberWithID() {
        System.out.println("\n\t\t Enter a hash code/ID for member within hash table to display.");
        int hashID;
        try {
            hashID = scanner.nextInt();
        } catch (Exception e) {
            return;
        }

        // Display the object, ID, current address, and home address.
        // System.out.println(m.toString(true));
        Member obj = hashTable.displaySingleValue(hashID);
        if (obj == null) {
            System.out.println("No value here!");
            return;
        }
        System.out.println(obj.toString(true));
    }

    public static String getTableParameteres() {
        int cap = hashTable.capacity();
        int size = hashTable.size();
        float maxLoadingFactor =  hashTable.loadingFactor();
        float increment = hashTable.increment();
        float currentLoadingFactor = 1f * size / cap;
                                
        return String.format("|   %5d      |   %5d |  %.2f   |  %.2f   |   %.1f   |", cap, size, maxLoadingFactor, increment, currentLoadingFactor);
    }
}