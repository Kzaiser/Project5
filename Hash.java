import java.util.*;
import java.util.Arrays;
import java.lang.reflect.Array;

@SuppressWarnings("unchecked")

public class Hash <T extends Comparable<T>> {
    protected Class<T> elementType;
    protected int size = 0, capacity = 10;
    protected float loadingFactor = 0.75f, increment = 0.2f;
    public T table[];

    // private float maxLDF = 0.8f;
    static int NOOBJ = -1;

    /**
     * Instantiate a hash table object.
     * @param capacity - total capacity of hash table.
     * @param loadingFactor - loading factor of the hash table. When
     * the loading factor (size/capacity) is reached, the capacity
     * will be increased by a given percentage.
     * @param increment - the percentage of increase of capacity when the
     * specified loading factor is reached.
     */

    public Hash(Class<T> type, int cap, float loadFact, float inc) {
        init(type, cap, loadFact, inc);
    }

    public Hash(Class<T> type, int cap, float loadFact) {
        init(type, cap, loadFact, 0.2f);
    }

    public Hash(Class<T> type, int cap) {
        init(type, cap, 0.75f, 0.2f);
    }

    public Hash(Class<T> type) {
        init(type, 10, 0.75f, 0.2f);
    }

    protected void init(Class<T> Type, int cap, float loadFact, float inc) {
        elementType = Type;
        capacity = cap;
        loadingFactor = loadFact;
        size = 0;
        increment = inc;
        table = (T[]) Array.newInstance(elementType, capacity);
    }

    // Hash <T> add(T obj) {}
    public Hash<T> add(T obj) {
        if (obj == null) {
            return null; // don't add if empty.
        }
        int home = hash(obj.hashCode() % capacity);
        while(table[home] != null) {
            home = (home + 1) % capacity;
        };
        table[home] = obj;
        size++;
        if( (float) size / capacity > loadingFactor) {
            rehash();
        }
        return this;
    }

    // public void display() {
    //     for(int i = 0; i < table.length; i++) {
    //         System.out.println(table[i]);
    //     }
    //     System.out.println("\n");
    // }

    // public T displaySingleValue(int loc) {
    //     return table[loc];
    // }

        // public Hash remove(int key) {
        //     int addr = find(key);
        //     if(tbl[addr] == NOOBJ) return this;
        // }
    public T remove(T obj) {
        return remove(obj.hashCode());
    }

    public T remove(int hashCode) {
        int location = find( hashCode );
        System.out.println("This is the location:" + location);
        if (location < 0) {
            return null; // nothing has been found, return null.
        }
        T o = table[location];
        table[location] = null;
        shift ( location ); // shift objects up if required.
        size --;
        return o; // return the object to display
    }

    int find ( T obj ) {
        // return find(obj.hashCode() );
        int location = hash(obj.hashCode()); // start at home location
        while (table[location] != null && table[location].compareTo(obj) != 0) {
            location = (location + 1) % capacity;
        }
        return table[location] == null ? -1 : location;
    }

    int find (int hCode) {
        int location = hash(hCode);
        // TO DO:

        // not working when using the second part of the while loop
        // while ( table[location] != null && table[location].hashCode() != hCode) {

        while(table[location] == null) {
            location = (location + 1) % capacity;
        }
        return table[location] == null ? -1 : location;
    }

    void shift( int empty ) {
        int current = empty, home;
        while ( true ) {
            current = (current + 1) % capacity;
            if( table[current] == null) {
                return;
            }
            
            if ( needToShift( empty, current) ) {
                table[empty] = table[current];
                table[current] = null;
                empty = current;
            }
        }
    }
    
    protected boolean needToShift( int empty, int current) {
        int home = hash( table[current].hashCode());
        if ( current > empty && ( home <= empty || home > current ) ||
            current < empty && home <= empty && home > current ) {
            return true;
        }
        return false;
    }

    // hash: return key's home address
    protected int hash(int hashCode) { 
        return hashCode % capacity;
    } 
    protected int hash(T obj) { return obj.hashCode() % capacity; }

    protected void rehash() {
        // create  temporary array
        // T tmp[] 
        // copy table into temporary
        //increase capacity
        //make table into new array of increased capacity; reset size
        // add objects into new bigger table
        capacity = (int) (capacity * ( 1 + increment));
        T t[] = table;
        table = ( T [] ) Array.newInstance(elementType, capacity);
        size = 0;
        for (int i = 0; i < t.length; i++ ) {
            add(t[i]);
        }
    }
    public int size() {
        return size;
    }
    public int capacity() {
        return capacity;
    }
    public float increment() {
        return increment;
    }
    public float loadingFactor() {
        return loadingFactor;
    }


    // distance function
    protected int displacement(int home, int location) {
        if (table[location] == null) {
            return -1;
        }
        // int home = hash(table[location].hashCode() );
        return home <= location ? location - home : capacity - home + location;

    }

    protected int displacement(int location) {
        T obj = table[location];
        int home = hash(obj);
        return displacement(home,location);
    }

    protected int getTotalDisplacement() {
        int count = 0;
        int i = 0;
        while (i < capacity) {
            if (table[i] != null) {
                count += displacement(i);
            }
            i++;
        }
        return count;
    }

    protected float averageUS(int cntUns) {
        int total = 0, loopCnt, k = cntUns;
        Member m = new Member();

        while ( k > 0) {
            loopCnt = find2( (T) m );
            if ( loopCnt > 0) {
                total += loopCnt;
                k--;
            }
            m.generate();
        }
        return (float) total/cntUns;
    }

    protected int find2( T obj ) {
        int loopCnt = 1;
        int location = hash(obj.hashCode());
        while(true) {
            if (table[location] == null) {
                return loopCnt;
            }
            if (table[location].compareTo( (T) obj) == 0 ) {
                return -1 * loopCnt;
            }
            loopCnt++;
            location = (location + 1) % capacity;
        }
    }
}
