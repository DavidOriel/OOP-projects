/**
 * the closed hash set class
 */
public class ClosedHashSet extends SimpleHashSet {

    /**
     * the size of the data structure in real time.
     */
    public int size;
    /**
     * the array we will use as a data structure.
     */
    private String[] array;
    /**
     * The initial size, the array will be initialized with 0 items.
     */
    private int INITIAL_SIZE = 0;

    /**
     * Creating a special string what will be our "flag".
     * that flag indicates for us if we need to keep searching,
     * because maybe there will be items in the next modulo cycle for that spot,
     * or this place is "null" without that flag and there is no items to look for and we can stop here.
     */
    private String str = new String("deleted");

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity
     */
    public ClosedHashSet() {
        super();
        this.array = new String[INITIAL_CAPACITY];
        this.size = INITIAL_SIZE;
    }

    /**
     * Constructs a new, empty table with the specified load factors
     *
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        this.array = new String[INITIAL_CAPACITY];
        this.size = INITIAL_SIZE;
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one from the data
     * structure given as a parameter.
     * @param data Values to add to the set.
     */
    public ClosedHashSet(java.lang.String[] data) {
        this.array = new String[INITIAL_CAPACITY];
        this.size = INITIAL_SIZE;
        for (String item : data) {
            add(item);
        }
    }

    /**
     * Method for rehashing the elements in the array to a new bigger of smaller array.
     * @param sizing The size we want to shrink or to expand the array.
     */
    private void rehash(double sizing) {
        String[] array1 = this.array;
        this.capacity *= sizing;
        this.size = INITIAL_SIZE;
        this.array = new String[this.capacity];
        for (String item : array1) {
            if (item != null && item != str) {
                for (int i = 0; i < this.capacity; i++) {
                    int index = clamp((item.hashCode() + (i + i ^ 2) / 2) & (capacity() - 1));
                    if (this.array[index] == null) {
                        this.size += 1;
                        this.array[index] = item;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue New value to add to the set
     * @return False if newValue already exists in the set
     */
    public boolean add(java.lang.String newValue) {
        if (this.size + 1 > this.capacity * getUpperLoadFactor()) {
            rehash(DOUBLE_SIZE);
        }
        if (contains(newValue)) {
            return false;
        }
        for (int i = 0; i < this.capacity; i++) {
            int index = clamp((newValue.hashCode() + (i + i ^ 2) / 2) & (capacity() - 1));
            if (this.array[index] == null) {
                this.size += 1;
                this.array[index] = newValue;
                return true;
            }
            if (this.array[index] == str) {
                this.size += 1;
                this.array[index] = newValue;
                return true;
            }
        }
        return false;
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True if toDelete is found and deleted
     */
    public boolean delete(java.lang.String toDelete) {
        if (!contains(toDelete)) {
            return false;
        }
        if (capacity() == 1){
            array[0] = str;
            this.size -= 1;
            return true;
        }
        if (this.size - 1 < this.capacity * getLowerLoadFactor()) {
                rehash(HALF_SIZE);
        }
        for (int i = 0; i < this.capacity; i++) {
            int index = clamp((toDelete.hashCode() + (i + i ^ 2) / 2) & (capacity() - 1));
            if (this.array[index].equals(toDelete) && this.array[index] != str) {
                this.size -= 1;
                this.array[index] = str;
                return true;
            }
        }
        return false;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for.
     * @return True iff searchVal is found in the set.
     */
    public boolean contains(java.lang.String searchVal) {
        for (int i = 0; i < this.capacity; i++) {
            int index = clamp((searchVal.hashCode() + (i + i ^ 2) / 2) & (capacity() - 1));
            if (this.array[index] == null) {
                return false;
            }
            if (this.array[index].equals(searchVal)) {
                return true;
            }
        }
        return false;
    }

    /**
     * getter for the capacity of the array.
     *
     * @return The current capacity (number of cells) of the table.
     */
    public int capacity() {
        return this.capacity;
    }

    /**
     * getter for the size if the array (how many items)
     *
     * @return The number of elements currently in the set
     */
    public int size() {
        return this.size;

    }
}

