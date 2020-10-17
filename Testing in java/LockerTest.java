import oop.ex3.spaceship.*;
import org.junit.*;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Class for testing the locker class.
 */
public class LockerTest {

    /**
     * GLOBALS.
     */
    private int NOTADDED = -1;
    private int ADDED = 0;
    private int ADDEDTOSTORAGE = 1;
    private int CAPACITY = 100;
    private int CAPACITY2 = 200;
    private int CAPACITY3 = 1000;
    private int CAPACITY4 = 2000;
    private int CAPACITY5 = 53;

    /**
     * LOCKERS.
     */
    private Locker locker = new Locker(CAPACITY);
    private Locker locker2 = new Locker(CAPACITY2);
    private Locker locker3 = new Locker(CAPACITY3);
    private Locker locker4 = new Locker(CAPACITY4);
    private Locker locker5 = new Locker(CAPACITY5);

    /**
     * ITEMS TO ADD.
     */
    static ItemFactory itemFactory = new ItemFactory();
    private Item item1 = itemFactory.createSingleItem("football");
    private Item item2 = itemFactory.createSingleItem("spores engine");
    private Item item3 = itemFactory.createSingleItem("baseball bat");
    private Item item4 = itemFactory.createSingleItem("helmet, size 1");

    /**
     * MAP.
     */
    Map<String, Integer> lockerMap = new HashMap<>();

    /**
     * Before every test we will reset all the lockers, their data and the long term storage.
     */

    @Before
    public void reset_map() {
        lockerMap.clear();
        Locker locker = new Locker(CAPACITY);
        Locker locker2 = new Locker(CAPACITY2);
        Locker locker3 = new Locker(CAPACITY3);
        Locker locker4 = new Locker(CAPACITY4);
        Locker locker5 = new Locker(CAPACITY5);
        //this is not in the API!!!!!!!!!!! BUT MUST BE RESET!!!
        locker.longTermStorage.resetInventory();
    }


    /**
     * test for the add item method.
     */
    @Test
    public void test_addItem1() {
        //test if item can add to locker if its bellow 50%
        assertEquals("problem in adding items ", ADDED, locker.addItem(item1, 4));
        // test if item can be added if it takes exactly 50%
        assertEquals("problem with adding exactly 50%", ADDED, locker.addItem(item2, 5));
        //test if an item is been added and the total amount of the items is over 50%
        assertEquals("item has been added and the amount is over 50%", ADDEDTOSTORAGE,
                locker.addItem(item2, 1));
        // test if we add more items then 50% of the locker, if some of the items removing to the long term
        assertEquals("checking if items removing to storage", 2,
                locker.getItemCount(item2.getType()));
        // test if there are items that takes exactly 20% of the locker capacity,
        // if we add more then 50% in total,
        //all the new items will be stored in the long term storage.
        assertEquals("problem with the transfer to longterm storage",
                ADDEDTOSTORAGE, locker.addItem(item2, 4));
        assertEquals("only 20% of the items should be left",
                2, locker.getItemCount(item2.getType()));
        //if we add
        assertEquals("adding more then the capacity to the storage", NOTADDED,
                locker.addItem(item1, 400));
    }

    /**
     * test for adding zero items to the locker.
     */
    @Test
    public void test_add_zero_item() {
        assertEquals("problem with adding zero items", ADDED, locker.addItem(item1, 0));
        assertEquals("should be zero", ADDED, locker.getItemCount(item1.getType()));
        assertEquals("available capacity should be equal to " +
                "max capacity", locker.maxcapacity, locker.getAvailableCapacity());
        System.out.println(locker.getInventory());
        System.out.println(lockerMap);
        assertEquals("the map after adding zero is not equal to empty map",
                lockerMap, locker.getInventory());
        assertEquals("the capacity should not be change", 100, locker.getCapacity());
        assertEquals("removing zero items cause problems", ADDED, locker.removeItem(item1, 0));
    }

    /**
     * test for the removing item method.
     */
    @Test
    public void test_remove_item() {
        locker.addItem(item1, 10);
        assertEquals("negative number should not be accepted",
                NOTADDED, locker.removeItem(item1, -1));
        assertEquals("removing items that do not" +
                " appears cause problem", NOTADDED, locker.removeItem(item2, 1));
        assertEquals("removing more then the capacity of the item"
                , NOTADDED, locker.removeItem(item1, 11));
        assertEquals("removing legal amount of items cause problems"
                , ADDED, locker.removeItem(item1, 9));
        assertEquals("problem with checking how much left from the item"
                , ADDEDTOSTORAGE, locker.getItemCount(item1.getType()));
    }

    /**
     * test for adding baseball and football to the same locker.
     */
    @Test
    public void baseball_and_football() {
        locker.addItem(item3, 0);
        assertEquals("adding football to locker with zero baseball bats cause problems"
                , ADDED, locker.addItem(item1, 1));
        assertEquals("the football item should be added",
                ADDEDTOSTORAGE, locker.getItemCount(item1.getType()));
        assertEquals("trying to add baseball bat to locker with football item"
                , -2, locker.addItem(item3, 1));
        assertEquals("the capacity of the baseball bat should be zero"
                , ADDED, locker.getItemCount(item3.getType()));
        assertEquals("the capacity of the football should be 1"
                , ADDEDTOSTORAGE, locker.getItemCount(item1.getType()));
    }

    /**
     * test for checking the long term storage after adding items to few lockers.
     */
    @Test
    public void long_term_test() {
        locker4.addItem(item2, 100);
        // locker2.addItem(item3, 10);
        //check if baseball bat and football can be together in the long term storage
        assertEquals("problem with adding football and baseball bat to the same longterm storage"
                , ADDED, locker.addItem(item1, 10));
        // check if more then the long term storage capacity can be added to that storage
        assertEquals("problem with adding more items then the capacity of the long term storage "
                , NOTADDED, locker4.addItem(item2, 50));
    }

    @Test
    public void test() {
        locker4.addItem(item2, 100);
        // check if more then the long term storage capacity can be added to that storage
        assertEquals("problem with adding more items then the capacity of the long term storage "
                , NOTADDED, locker4.addItem(item2, 50));
        //check if exactly 1000 items can be added to the long term storage
        assertEquals("problem with adding exactly 1000 item values to the long term storage"
                , ADDEDTOSTORAGE, locker4.addItem(item2, 40));
        //check if the storage is full and still items can be added from different locker
        assertEquals("the long term storage is full and yet item from different locker has been added"
                , NOTADDED, locker2.addItem(item3, 51));
    }

    /**
     * Testing a locker without a round number if were taking the 20% and the 50% of it.
     */
    @Test
    public void check_double_values() {
        locker5.addItem(item4, 5);
        //adding 4 more helmets, the capacity will be 27 but it can hold only 26.5 so only 3
        // of them should stay in the locker.
        assertEquals("locker holds more then 50%", ADDEDTOSTORAGE, locker5.addItem(item4, 4));
        //check if 6 of 9 items moved to the long term storage.
        assertEquals("items didnt moved to long term storage",
                982, locker.longTermStorage.getAvailableCapacity());
        // the 20% of the items should be stayed in the locker.
        assertEquals(" problem with the capacity", 3, locker5.getItemCount(item4.getType()));
    }

    /**
     * test for adding zero baseball bats to a locker with football, it should return -2 since were adding
     * baseball to a locker with football (even if the number is zero)
     */
    @Test
    public void baseball_and_football2() {
        locker.addItem(item1, 1);
        assertEquals("cannot add baseball to a locker with football, even if its a zero"
                , -2, locker.addItem(item3, 0));

    }

    /**
     * test if the number of items to add is negative.
     */
    @Test
    public void test_negative_number() {
        assertEquals("negative number should not be accepted",
                NOTADDED, locker.addItem(item1, -3));
    }

    @Test
    public void test_inventory() {

        locker3.addItem(item3, 5);
        lockerMap.put(item3.getType(), 5);
        assertEquals(lockerMap, locker3.getInventory());
        locker3.addItem(item1, 0);
        assertEquals(lockerMap, locker3.getInventory());
        locker3.addItem(item1, -1);
        assertEquals(lockerMap, locker3.getInventory());


    }
}


