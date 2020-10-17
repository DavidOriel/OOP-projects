/**
 * Wraps an underlying Collection and serves to both simplify its API and give it a common
 * type with the implemented SimpleHashSets.
 */
public class CollectionFacadeSet extends java.lang.Object implements SimpleSet {


    protected java.util.Collection<java.lang.String> collection;
    /**
     * Creates a new facade wrapping the specified collection.
     * @param collection The Collection to wrap
     */
    public CollectionFacadeSet(java.util.Collection<java.lang.String>  collection) {
        this.collection = collection;
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False if newValue already exists in the set
     */
    public boolean add(java.lang.String newValue){
        if (contains(newValue)){
            return false;}
        return collection.add(newValue);
    }

    /**
     * checks if the collection contains a given string.
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */
    public boolean contains(java.lang.String searchVal){
        return this.collection.contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True if toDelete is found and deleted
     */
    public boolean delete (java.lang.String toDelete){
    if (!contains(toDelete)){
        return false;}
    return this.collection.remove(toDelete);

    }

    /**
     * checks the size of the collection.
     * @return The number of elements currently in the set.
     */
    public int size(){
        return this.collection.size();

    }

}
