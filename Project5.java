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
    static String prompt;
    static Scanner scanner = new Scanner(System.in);
    // static Hash<Member> hashTable = new Hash<Member>(Member.class);
    static Hash<Member> hashTable = new Hash<Member>(Member.class);
    static Member m = new Member();
    static Random rand = new Random();
    static String noValueFound = "No value found here!";
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
    static String equalLine =     "+======================================================+";
    static String hyphenLine =    "+---------------------------------------------------+";
    static String tableTitle =  "|                Contents of Hash Table                |";
    static String tableColumns[] = {   
                                "+======================================================+",
                                "|         Object Value         |Current| Home  |Displac|",
                                "|                              |Address|Address| ement |",
                                "+------------------------------+-------+-------+-------+"
    };
    static String tableHyphenLine = "+------------------------------+-------+-------+-------+";
    static String spaceString =   "                                                            ";
    static String blocks[] = {
                                    "+===================================================+",
                                    "|       Information on Data and Blank Blocks        |",
                                    "+===================================================+",
                                    "| Block Type |  Starting  |   Ending   |    Size    |",
                                    "|            |  Address   |  Address   |            |",
                                    "+------------+------------+------------+------------+"
    };
    static String blockEqualLine = "+===================================================+";
    static String blockLabels = "| Block Type | Count | Maximum | Minimum | Avg Size |";
    static String blockHyphenLine = "+------------+-------+---------+---------+----------+ ";
    static String parameterTitle[] = {
                                    "+================================================================+",
                                    "|                  Parameters of The Hash Table                  |",
                                    "+================================================================+",
    };
    static String parameterLabels[] = {
                                    "|  Capacity  |    Size    | Increment  | Specified  |Actual Load |",
                                    "|            |            |            |Load Factor |   Factor   |",
                                    "+------------+------------+------------+------------+------------+"
    };
    static String parameterHyphenLine = "+------------+------------+------------+------------+------------+";
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

    static void printOtherStuff(String stuff) {
        System.out.println(stuff);
    }

    static void printNull(int pos) {
        System.out.printf("|%.30s| %-5d |%.7s|%.7s|\n%s\n",spaceString,pos,spaceString,spaceString, tableHyphenLine);
    }

    static void printMember(int pos) {
        if(pos < 0) {
            System.out.println(noValueFound);
        } else if (hashTable.table[pos] == null) {
            printNull(pos);
        } else {
            m = hashTable.table[pos];
            int home = hashTable.hash(m);
            int dist = hashTable.displacement(home, pos);
            System.out.printf("|%-30.30s| %-5d | %-5d | %-5d |\n%s\n", m, pos, home, dist, tableHyphenLine);
        }
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
                    addNewMember();
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
                    showHashTable();
                    break;
                case 'I':
                case 'i':
                case 'X':
                case 'x':
                    break;
                case 'T':
                case 't':
                    printTimeComplexityTable();
                    break;
                case 'B':
                case 'b':
                    dataBlocks();
                    break;
                case 'P':
                case 'p':
                    printTableParameters();
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
            System.out.printf("added %s\n", m);
            // System.out.println(m.toString(true));
        }
        // hashTable.display();
    }

    // "| A: Instantiate a new member object, and the object|",
    // "|  into the hash table. Display the newly added     |",
    // "|  member, its home address, and current address.   |",
    static void addNewMember() {
        // Init new random member object
        Member mem = nextMember();
        // Add Object to hashTable
        hashTable.add(mem);
        int pos = 0;
        while(hashTable.table[pos] != mem) {
            pos++;
        }
        displayNewMember(pos);
    }

    static void displayNewMember(int pos) {
        printStuff(tableColumns);
        printMember(pos);
        System.out.println(getTableParameters());
        printOtherStuff(hyphenLine);
    }


    // "+---------------------------------------------------+",
    // "| R: Ask for an ID or hash code of an object. Remove|",
    // "|      the object whose ID matches the given ID.    |",
    // "+---------------------------------------------------+",

    static void removeMemberWithID() {
        System.out.println("\n\t\t Enter a hash code/ID for member within hash table to remove.");
        // Retrieve Hash Code / Hash ID
        int hashID;
        // hashTable.display();
        try {
            hashID = scanner.nextInt();
        } catch (Exception e) {
            return;
        }
        System.out.printf("removed %s", hashTable.table[hashID]);
        // Remove the object whose ID matches the given ID
        hashTable.remove(hashID);
        // hashTable.display();
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
        displayNewMember(hashID);
    }

    // "+----------+--------------------------------------------------+",
    // "|   c C    |Show contents of hash table in a tabular way. one |",
    // "|          | object per line and ten objects per screen. for  |",
    // "|          |   each line, the following columns have to be    |",
    // "|          | displayed: Object, current address, home address |",
    // "|          |  and the displacement from home address to its   |",
    // "|          |                 current address.                 |",
    // "+----------+--------------------------------------------------+"

    static void showHashTable() {
        int cap = hashTable.capacity();
        int pos = 0;
        printHashTable();
        while (pos < cap) {
            printMember(pos);
            pos++;
            if(pos % 10 == 0 && pos < cap) {
                if(quit()) {
                    pos = Math.max(cap - 10, pos);
                    System.out.println("Quitting! \n");
                    while(pos < cap) {
                        printMember(pos);
                        pos++;
                    }
                } else {
                    printHashTable();
                }
            }
        }
        // printDisplacement();
        return;
    }

    static void printHashTable() {
        printOtherStuff(equalLine);
        printOtherStuff(tableTitle);
        System.out.println(getTableParameters());
        printStuff(tableColumns);
    }

    public static boolean quit() {
        System.out.println("Press C to continue, or Q to quit : ");
        prompt = scanner.next();
        switch (prompt) {
            case "Q": case "q": return true;
            case "C": case "c": return false;
            default: return quit();
        }
    }
    // +----------+--------------------------------------------------
    //    t T    | Perform a successful search on each of object in |
    // show Time | the hash table, and 'capacity' many unsuccessful |
    // Complexity| searches, list the (1) avareage comparions from  |
    // of Bin. & |all successful searchs, the theoretic susccessful |
    // Hash. Srch|search complexity [(1 + 1/(1-a))/2], and the      |
    // See exampl|theoretical un-successful search time complexity  |
    // |e below.  |[(1+1/(1-a)**2)/2], where a is the loading factor|
    // ----------+--------------------------------------------------+

    static void printTimeComplexityTable() {
        printStuff(timeComplexityTable);
        printOtherStuff(timeComplexityLine);
    }

    // +----------+--------------------------------------------------+",
    // |  b B     |Display information on blocks formed by contiguous|",
    // +          |data or empty cells inside table. For each block, |",
    // |          |display the type of block (either data or empty), |",
    // |          |the starting and ending addresses, size of block. |",
    // |          |At the end of block listing, show the total number|",
    // |          |   of blocks, the maximum, the minimum and the    |",
    // |          |  average block sizes, for each type. Allow quit  |",
    // |          | listing the total, maximum, minimum and average  |",
    // |          |must show the correct data even if the listing of |",
    // |          |                   block ends.                    |",
    // +----------+--------------------------------------------------+",

    static void dataBlocks() {
        printStuff(blocks);
        int first = 0;
        boolean block = hashTable.table[first] != null;
        String blockType;
        while(block == (hashTable.table[first] != null)) {
            first = (first + 1) % capacity; 
        }
        int dataBlocks = 0, dataSizes = 0, dataMax = 0, dataMin = hashTable.capacity();
        int emptyBlocks = 0, emptySizes = 0, emptyMax = 0, emptyMin = dataMin;
        int i = first;
        do {
            block = hashTable.table[i] != null;
            int start = i;
            int end = start;
            int size = 1;
            i = (i + 1) % hashTable.capacity();
            while(block == (hashTable.table[i] != null)) {
                end = (end + 1) % hashTable.capacity();
                i = (i + 1) % hashTable.capacity();
                size++;
            }

            if (block) {
                blockType = "Data";
                dataBlocks++;
                dataSizes += size;
                if(size > dataMax) {
                    dataMax = size;
                }
                if (size < dataMin) {
                    dataMin = size;
                }
            } else {
                blockType = "Empty";
                emptyBlocks++;
                emptySizes += size;
                if(size > emptyMax) {
                    emptyMax = size;
                }
                if(size < emptyMin) {
                    emptyMin = size;
                }
            }
            System.out.printf("|  %-5s     |    %-4d    |   %-4d     |    %-4d    |\n", blockType, start, end, size);
            if((dataBlocks + emptyBlocks) % 10 == 0) {
                if(quit()) {
                    break;
                }
            }
        } while (i != first);
        System.out.println(blockEqualLine);
        System.out.println(blockLabels);
        System.out.println(blockHyphenLine);
        System.out.printf("|   Data     | %-5d | %-5d   | %-5d   | %-5.2f    |\n", dataBlocks,dataMax, dataMin, 1f * dataSizes/dataBlocks);
        System.out.printf("|  Empty     | %-5d | %-5d   | %-5d   | %-5.2f    |\n", emptyBlocks, emptyMax, emptyMin, 1f * emptySizes/emptyBlocks);
        System.out.println(blockHyphenLine);
    }


    // "+----------+--------------------------------------------------+",
    // |   p P    |List the table parameters. The parameters include.|",
    // |          |    the capacity, size, load factor, increment    |",
    // |          |      percentage,and the actual load factor.      |",
    // "|         |                 current address.                 
    // +----------+--------------------------------------------------+",
    public static String getTableParameters() {
        int cap = hashTable.capacity();
        int size = hashTable.size();
        float maxLoadingFactor =  hashTable.loadingFactor();
        float increment = hashTable.increment();
        float currentLoadingFactor = 1f * size / cap;
                                
        return String.format("|Cap:%5d|Size:%5d|maxLDF:%.2f|IncP:%.2f|curLDF:%.1f |", cap, size, maxLoadingFactor, currentLoadingFactor, increment);
    }

    static void printTableParameters() {
        printStuff(parameterTitle);
        printStuff(parameterLabels);
        printOtherStuff(getTableParameters());
        printOtherStuff(parameterHyphenLine);
    }
}