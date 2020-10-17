import java.util.HashMap;
import java.util.Map;
import oop.ex3.spaceship.*;

/**
 * Class for the long term storage, here we can deposite items to that storage.
 */
public class LongTermStorage  {

    /**
     * A java map that represent the long term storage.
     */
    Map<String , Integer > longtermstorage = new HashMap<>();

    /**
     * GLOBALSs
     */
    int MAXCAPACITY = 1000;
    private int ADDED = 0;
    private int NOTADDED =-1;
    private int NOITEMS = 0;

    /**
     * The capacity of the storage, will be change when more items will be added.
     */
    int volume;
    /**
     * The constructor of the long term storage.
     */
    public LongTermStorage(){
        this.volume = 0;
    }

    /**
     * Method for adding items to the long term storage, only items with the same
     * type can be added in a single call. The items will be added only if there is a
     * free space for all of them.
     * @param item the item object that we want to add.
     * @param n how many of that item to add to the storage
     * @return -1 if the storage dont have anough free space for all of the items.
     * 0 if those items had been added.
     */
    public int addItem(Item item, int n){
        if (n < NOITEMS){
            return NOTADDED; }
        if (n == NOITEMS){return ADDED;}
        String itemName = item.getType();
        int itemCapacity = 0;
        if (longtermstorage.containsKey(itemName)) {
            itemCapacity = longtermstorage.get(itemName);}
        int itemVolume = n * item.getVolume();
        if (itemVolume > getAvailableCapacity()) {
            return NOTADDED;}
        longtermstorage.put(itemName, itemCapacity + n);
        this.volume += n* item.getVolume();
        return ADDED;
        }

    /**
     * Reset the long term storage, after the reset, all the items that still in will be lost.
      */
    public void resetInventory(){
        longtermstorage.clear();
        this.volume =0;
     }

    /**
     *  Returns the number of a specific item that apears in the storage.
     * @param type The item name.
     * @return Int that represent the number.
     */
    public int getItemCount(String type){
        if (!longtermstorage.containsKey(type)){return ADDED;}
        return longtermstorage.get(type);
    }

    /**
     * Returns a map that represent the all the items and how many of them
     * are in the storage.
     * @return A map that represent this value.
     */
     public Map<String, Integer> getInventory(){
        return longtermstorage;
    }

    /**
     * Get the total capacity of the storage.
     * @return Number of the capacity.
     */
    public int getCapacity(){
        return MAXCAPACITY;
    }

    /**
     * Method that returns how much free space left in the storage.
     * @return Int that represent the free space.
     */
    public int getAvailableCapacity(){
        return MAXCAPACITY - this.volume;
    }



}
