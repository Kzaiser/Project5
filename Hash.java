public class Hash {
    private int size = 0, cap = 10, incPCT = 10;
    private float maxLDF = 0.8f;
    private int tbl[] = new int[cap];
    static int NOOBJ = -1;

    public Hash ( ) {
        for(int i = 0; i < cap; i++) tbl[i] = NOOBJ;
    }
    int hash(int key) { return key % cap;} // hash: return key's home address
        Hash add(int n) {
            if( size*1.0f/cap >= maxLDF) rehash();
            int home = hash(n);
            while(tbl[home] != NOOBJ) home = (home + 1) % cap;
            tbl[home] = n;
            size++;
            return this;
        }

        remove(int key) {
            int addr = find(key);
            if(tbl[addr] == NOOBJ) return;
        }

        void rehash() {

        }
}