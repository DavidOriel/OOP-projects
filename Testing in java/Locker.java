import oop.ex3.spaceship.*;
import java.util.Map;
import java.util.HashMap;


/**
 * A class for a locker, in that locker we can deposite items with certeain stricks rules.
 */
public class Locker   {

    public static LongTermStorage longTermStorage = new LongTermStorage();
    /**
     * The capacity of the storage, will be change when more items will be added.
     */

    /**
     * The maximum capacity of the long term storage, will be change when more items will be added.
     */

    int maxcapacity;

    int volume;
    /**
     * GLOBALS.
     */
    private int ADDED = 0;
    private int NOTADDED = -1;
    private int ADDEDTOSTORAGE = 1;
    private int COLLISION = -2;
    private double TWENTYPREC = 0.2;
    private double FIFTYPREC = 0.5;
    private int NOITEMS = 0;

    /**
     * A java map that represent the locker.
     */
    Map<String, Integer> locker = new HashMap<>();

    String NOROOM= "Error: Your request cannot be completed at this time." +
            " Problem: no room for n items of type type ";
    String STORAGE = "Warning: Action successful," +
            " but has caused items to be moved to storage";
    String NOTENOUGH = "Error: Your request cannot be completed at" +
            "this time. Problem: the locker does not contain n items of type type";
    String NEGATIVE = "Error: Your request cannot be completed at this time. Problem: cannot" +
            "remove a negative number of items of type type";
    String CULLISION = "Error: Your request cannot be completed at this time." +
            " Problem: the locker cannot contain items of type type, as it contains a contradicting item";

    /**
     * The constructor of the locker class.
     * @param capacity the capacity of the locker.
     */
    public Locker(int capacity) {
        this.maxcapacity = capacity;
        this.volume = 0;
    }

    /**
     * this method tries to add a given amount of items to the locker, in few cases some of the items could remove
     * to the long term storage.
     * We are using some variables in this method:
     * itemVolume - the volume capacity of the item that already in the locker.
     * itemCapacity - how much of that item is in the locker before the adding.
     * itemToAddVol - how much volume the items that we want to add takes.
     * @param item The item to add to the locker.locker
     * @param n how many of that items should be added to the locker.
     * @return -1 if there wasn't any place for n item, so nothing has been added. 0 if all the n items went to the
     * locker, and 1 if some of them went to the long term storage.
     */
    public int addItem(Item item, int n) {
        String itemName = item.getType();
        int itemCapacity = ADDED;
        if (check_collision(item)){return COLLISION;}
        if (n < ADDED){
            return NOTADDED; }
        if (n == NOITEMS){return ADDED;}
        if (locker.containsKey(itemName)) {
            itemCapacity = locker.get(itemName);}
            double itemVolume = itemCapacity * item.getVolume();
            double itemToAddVol = n * item.getVolume();
            if (itemToAddVol > getAvailableCapacity()){ // number of items to add are more then
                // the maximal capacity of the locker
                return no_room();}
            if (getAvailableCapacity() + longTermStorage.getAvailableCapacity() < itemToAddVol) { //no place at all
                return no_room();}
            if (itemVolume + itemToAddVol > FIFTYPREC * maxcapacity) {
                if (longTermStorage.getAvailableCapacity() < itemToAddVol - ( maxcapacity * TWENTYPREC - itemVolume)) {
                    return no_room(); }
                if (itemVolume > TWENTYPREC * maxcapacity) {
                    bellow_20_precent(item,n,itemVolume,itemCapacity);
                    return storage();}
                while (itemVolume +item.getVolume() <= maxcapacity * TWENTYPREC){
                    locker.put(itemName, itemCapacity+1);
                    n -= 1;
                    itemVolume += item.getVolume();
                    itemToAddVol -= item.getVolume();
                    itemCapacity += 1;
                    this.volume+= item.getVolume(); }
                longTermStorage.addItem(item, n);
                return storage(); }
            locker.put(itemName, itemCapacity + n);
            this.volume+= n*item.getVolume();
            return ADDED;
        }

    /**
     * This method will be called by the add item item method in case there are items in the locker that need to be
     * transfered to the long term storage, in case we want to add more then 50% of the capacity of an item to the
     * locker (including the items with the same kind that already in the locker) and there is more then 20% of the
     * capacity of the locker taken by that item.
     * @param item the item
     * @param n how many items we want to add to the locker in  total
     * @param itemVolume item that already in the locker volume
     * @param itemCapacity how much capacity the items already takes from the locker.
     */
        private void bellow_20_precent(Item item,int n, double itemVolume, int itemCapacity){
            String itemName = item.getType();
            while (itemVolume > TWENTYPREC * maxcapacity) {
                itemVolume -= item.getVolume();
                locker.put(itemName, itemCapacity - 1);
                n += 1;
                itemCapacity -= 1;
                this.volume-= item.getVolume(); }
            longTermStorage.addItem(item, n);
            }

    /**
     * in case no item has been added, we will call the NO ROOM announcement and return -1.
     * @return -1
     */
        private int no_room(){
         System.out.println(NOROOM);
         return NOTADDED; }

    /**
     * In case few items has been added to the locker and the long term locker as well, we will call
     * the STORAGE announcement and return 1
     * @return 1
     */
    private int storage(){
         System.out.println(STORAGE);
         return ADDEDTOSTORAGE;
     }
    /**
     * method for preventing from baseball bat and football to be in the same locker, the method will check if a given
     * item can be added to the locker without a problem.
     * @param item item to add to the locker
     * @return true if the item cannot be added, false if it can.
     */
    private boolean check_collision(Item item){
        if ("football".equals(item.getType()) && locker.containsKey("baseball bat")){
            if (getItemCount("baseball bat") > NOITEMS){
                System.out.println(CULLISION);
                return true;}}
        if ("baseball bat".equals(item.getType()) && locker.containsKey("football")){
            if (getItemCount("football")> NOITEMS ){
                System.out.println(CULLISION);
                return true;}}
        return false;}

    /**
     * method for removing certain amount of a specific item from the locker.
     * @param item the item we want to remove from the locker
     * @param n int that represent how many from that item we want to remove.
     * @return return 0 if succeeded, -1 if not.
     */
    public int removeItem(Item item, int n) {
        if (!locker.containsKey(item.getType()))
        {if (n>0){return NOTADDED;}
        return ADDED;
        }
        if (n< NOITEMS){
            System.out.println(NEGATIVE);
            return NOTADDED;}
        if (locker.get(item.getType()) == n ){
            locker.remove(item.getType());
            return ADDED; }
        if (n == NOITEMS){return ADDED;}
        if (locker.containsKey(item.getType())){
            if (locker.get(item.getType())< n){
                System.out.println(NOTENOUGH);
                return NOTADDED;}
            locker.put(item.getType(), locker.get(item.getType())- n);
            this.volume -= n*item.getVolume();
            return ADDED;}
        return NOTADDED;
    }

    /**
     * Get the total capacity of the locker.
     *
     * @return Number of the capacity.
     */
    public int getCapacity() {
        return maxcapacity;
    }

    /**
     * Returns the number of a specific item that apears in the locker.
     *
     * @param type The item name.
     * @return Int that represent the number.
     */
    public int getItemCount(String type) {
        if (locker.containsKey(type)){
            return locker.get(type);
        }
        return ADDED;
    }

    /**
     * Returns a map that represent the all the items and how many of them
     * are in the locker.
     *
     * @return A map that represent this value.
     */
    public Map<String, Integer> getInventory() {
        return locker;
    }

    /**
     * Method that returns how much free space left in the locker.
     *
     * @return Int that represent the free space.
     */
    public int getAvailableCapacity() {
        return this.maxcapacity - this.volume;
    }
}
