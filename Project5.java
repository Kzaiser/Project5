/*
    CMPS 3390: Java Advanced Programming
    Project 5: Hashtable
    Created by:
        Zachary Kaiser
        Guangjin Liu
        Lawrence Marquez
*/

public class Project5 {
    static String menu[] = {
        "+===================================================+",
        "|    CS 3390 Assignment:  Linked List Structures    |",
        "+===================================================+",
        "| G: Prompt for two integers, the capacity and the  |",
        "|   load factor of a hash table. Create a new hash  |",
        "|  table, with 20% as increment percentage, generate|",
        "|  (capacity * load factor) many mixed Member       |",
        "|          and add them to the hash table           |",
        "+---------------------------------------------------+",
        "| A: Instantiate a new member object, and the object|",
        "|  into the hash table. Display the newly added     |",
        "|  member, its home address, and current address.   |",
        "+---------------------------------------------------+",
        "| R: Ask for an ID or hash code of an object. Remove|",
        "|      the object whose ID matches the given ID.    |",
        "+---------------------------------------------------+",
        "| F: Ask for an ID or hash code of an object,       |",
        "|      display the object, ID, current address, and |",
        "|      home address.                                |",
        "+---------------------------------------------------+",
        "| C: Show contents of hash table in a tabular way.  |",
        "|      One object per line, the following columns   |",
        "|      have to be displayed: Object, Current Address|",
        "|      and the displacement from home               |",
        "|      address to current address                   |",
        "+---------------------------------------------------+",
        "| I/X: Remove object at I, and do not shift         |",
        "+---------------------------------------------------+",
        "| H/?: Show this menu.                              |",
        "+---------------------------------------------------+",
        "| T: Perform a successful search on each object in  |",
        "|      the hash table, and 'capacity' many          |",
        "|      unsuccessful searches, list the (1) average  |",
        "|      comparisons from all successful seraches, the|",
        "|      theoretic successful search complexity is:   |",
        "|      [(1 + 1/(1-a))/2], and the theoertical       |",
        "|      unsuccessful serach complexity is:           |",
        "|      [(1+1/(1-a)**2)/2], where a is loading factor|",
        "+===================================================+", 
        "| B: Display information on blocks formed by        |",
        "|      contiguous data or empty cells inside table. |",
        "|      For each block, display the type of block    |",
        "|      (either data or empty), the starting and     |",
        "|      ending addresses, size of block. At the end  |",
        "|      of block listing, show the total number of   |",
        "|      blocks, the maximum, minimum, and average    |",
        "|      block sizes for each type. Allow quit listing|",
        "|      the total, maximum, minimum, and average must|",
        "|      show the correct data even if the listing of |",
        "|      block ends.                                  |",
        "+===================================================+",         
        "| P: List the table parameters. This includes:      |",
        "|      the capacity, size, load factor, increment   |",
        "|      percentage, and the actual load facter.      |",
        "+===================================================+",         
        "| V: Verify whether all non-null elements in table  |",
        "|      are reachable or not.                        |",
        "+===================================================+",         
        "| Q: Exit the program.                              |",
        "+===================================================+" };

}