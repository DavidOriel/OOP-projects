/**
 * A superclass for implementations of hash-sets implementing the SimpleSet interface
 */
public abstract class SimpleHashSet extends java.lang.Object implements SimpleSet {


    /**
     * Describes the higer load factor of a newly created hash set.
     */
    protected static Float DEFAULT_HIGHER_CAPACITY = 0.75f;

    /**
     * Describes the lower load factor of a newly created hash set.
     */
    protected static Float DEFAULT_LOWER_CAPACITY = 0.25f;

    /**
     *  global for changing the capacity of the array when rehashing.
     */
    public double DOUBLE_SIZE = 2.0;

    public double HALF_SIZE = 0.5;

    /**
     * Describes the capacity of a newly created hash set.
     */
    protected static int INITIAL_CAPACITY = 16;
    protected Float lowerCapacity;
    protected Float higherCapacity;

    protected int capacity;

    /**
     * Constructs a new hash set with the default capacities
     */
    protected SimpleHashSet() {
        this.capacity = INITIAL_CAPACITY;
        this.lowerCapacity = DEFAULT_LOWER_CAPACITY;
        this.higherCapacity = DEFAULT_HIGHER_CAPACITY;
    }

    /**
     * @param upperLoadFactor the upper load factor before rehashing.
     * @param lowerLandFactor the lower load factor before rehashing.
     */
    protected SimpleHashSet(Float upperLoadFactor, Float lowerLandFactor) {
        this.higherCapacity = upperLoadFactor;
        this.lowerCapacity = lowerLandFactor;
        this.capacity = INITIAL_CAPACITY;

    }

    /**
     * @return The current capacity (number of cells) of the table.
     */
    public abstract int capacity();

    /**
     * Clamps hashing indices to fit within the current table capacity.
     *
     * @param index the index before clamping.
     * @return and index properly clamped.
     */
    protected int clamp(int index) {
        return index & (capacity() -1);
    }

    /**
     * @returnThe lower load factor of the table.
     */
    protected float getLowerLoadFactor() {
        return this.lowerCapacity;
    }

    /**
     * @return The higher load factor of the table.
     */
    protected float getUpperLoadFactor() {
        return this.higherCapacity;
    }

}
