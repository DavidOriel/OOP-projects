import java.util.LinkedList;

/**
 * the open hash set class
 */

public class OpenHashSet extends SimpleHashSet {
    /**
     * the size initialized as 0, there is no items.
     */
    int INITIAL_SIZE = 0;

    /**
     * wrapper object to wrap the linked list that will take place in each place of the array.
     */
    private Wrapper[] array;

    int size;

    /**
     * A default constructor.
     */
    public OpenHashSet() {
        super();
        this.array = new Wrapper[INITIAL_CAPACITY];
        this.size = INITIAL_SIZE;
    }

    /**
     * Constructs a new, empty table with the specified load factors, the capacity will be initialized as 16.
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        this.array = new Wrapper[INITIAL_CAPACITY];
        this.size = INITIAL_SIZE;
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * @param data Values to add to the set.
     */
    public OpenHashSet(java.lang.String[] data) {
        super();
        this.array = new Wrapper[INITIAL_CAPACITY];
        this.size = INITIAL_SIZE;
        for (String item : data) {
            add(item);
        }
    }

    /**
     * method for finding the hash code of a given value.
     * @param newValue finding the hash code of that value.
     * @return integer representing the hash code.
     */
    private int finedHash(String newValue) {
        int index = clamp(newValue.hashCode());
        return index;
    }

    /**
     * when the array has more items or less items then its load factor the array will be shrinked or
     */
    private void rehash(double sizing) {
        Wrapper[] array1 = this.array;
        this.capacity *= sizing;
        this.size = 0;
        this.array = new Wrapper[this.capacity];
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != null) {
                for (String item : array1[i].getLinkedList()) {
                    int index = finedHash(item);
                    if (this.array[index] == null) {
                        array[index] = new Wrapper();
                    }
                    this.size += 1;
                    this.array[index].add(item);
                }
            }
        }
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False if newValue already exists in the set
     */
    public boolean add(java.lang.String newValue) {
        if (this.size + 1 > this.capacity * getUpperLoadFactor()) {
            rehash(DOUBLE_SIZE);
        }
        int index = finedHash(newValue);
        if (this.array[index] == null) {
            array[index] = new Wrapper();
        }
        if (contains(newValue)) {
            return false;
        }
        this.size += 1;
        return this.array[index].add(newValue);
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */
    public boolean contains(java.lang.String searchVal) {
        int index = finedHash(searchVal);
        if (this.array[index] == null) {
            return false;
        }
        return this.array[index].contains(searchVal);

    }

    /** Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True if toDelete is found and deleted
     */
    public boolean delete(java.lang.String toDelete) {
        int index1 = finedHash(toDelete);
        if (this.array[index1] == null) {
            return false;
        }
        if (this.capacity <= 1) {
            this.size = INITIAL_SIZE;
            return this.array[index1].delete(toDelete);
        }
        if (this.size - 1 < this.capacity * getLowerLoadFactor()) {
            rehash(HALF_SIZE);
        }
        int index2 = finedHash(toDelete);
        if (array[index2] == null) {
            return false;
        }
        if (!contains(toDelete)) {
            return false;
        }
        this.size -= 1;
        return this.array[index2].delete(toDelete);
    }

    /**
     * checks the size of the set.
     * @return The number of elements currently in the set
     */
    public int size() {
        return this.size;
    }

    /**
     * checks the capacity of the hash set.
     * @return The current capacity (number of cells) of the table.
     */
    public int capacity() {
        return this.capacity;

    }

    /**
     * A nested Wrapper class inside the open hash class for wrapping.
     */
    public class Wrapper {
        /**
         * We will use a linked list data structure for deposit values with the same hash code.
         */
        LinkedList<String> linkedList;

        /**
         * construct a linked list
         */
        Wrapper() {
            this.linkedList = new LinkedList<String>();
        }

        /**
         * adding an element to the linked list.
         * @param newValue value to add.
         * @return true if added, false if not.
         */
        public boolean add(String newValue) {
            return linkedList.add(newValue);
        }

        /**
         * delete an element from the linked list.
         * @param newValue value to delete
         * @return true if the value has deleted false if not.
         */
        public boolean delete(String newValue) {
            return linkedList.remove(newValue);
        }

        /**
         * check if the linked list contains an element.
         * @param newValue the value to check.
         * @return true if the value is in the linked list false if not.
         */
        public boolean contains(String newValue) {
            return linkedList.contains(newValue);
        }


        /**
         * getter for the linked list
         * @return the linked list object.
         */
        public LinkedList<String> getLinkedList() {
            return linkedList;
        }

    }
}
