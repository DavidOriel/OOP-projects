import oop.ex3.spaceship.*;
import org.junit.*;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

/**
 * Test for the long term storage.
 */
public class LongTermTest {
    /**
     * Globals
     */
    private int ADDED = 0;
    private int FULL = 1000;
    private int NOTADDED = -1;

    /**
     * objects
     */
    private LongTermStorage longTermStorage = new LongTermStorage();
    static ItemFactory itemFactory = new ItemFactory();

    /**
     * Items.
     */
    private Item item1 = itemFactory.createSingleItem("football");
    private Item item2 = itemFactory.createSingleItem("helmet, size 3");
    private Item item3 = itemFactory.createSingleItem("baseball bat");
    private Map<String, Integer> longTermStorageMap = new HashMap<>();


    /**
     * the long term storage is cleared before every test.
     */
    @Before
    public void reset() {
        longTermStorage.resetInventory();
    }

    @Test
    public void test_addItem() {
        assertEquals("problem in adding items ", ADDED, longTermStorage.addItem(item1, 40));
        longTermStorage.addItem(item1, 400);
        assertEquals("adding more then the capacity to the storage", NOTADDED,
                longTermStorage.addItem(item1, 400));
        assertEquals(NOTADDED, longTermStorage.addItem(item1, -2));
        assertEquals(ADDED, longTermStorage.addItem(item1, 0));

    }

    /**
     * Test for the get available capacity of the long term storage.
     */
    @Test
    public void test_longTermStorage() {
        longTermStorage.addItem(item1, 40);
        assertEquals("problem in the availableCapacity of the storage",
                840, longTermStorage.getAvailableCapacity());
        longTermStorage.addItem(item2, 20);
        assertEquals("problem in the capacity after adding more then one kind of an item",
                FULL, longTermStorage.getCapacity());
        assertEquals("problem with the available capacity",
                740, longTermStorage.getAvailableCapacity());
    }

    /**
     * item count method test, we will try to add negative number of items and zero items.
     */
    @Test
    public void test_for_item_count() {
        longTermStorage.addItem(item2, 100);
        assertEquals("", 100, longTermStorage.getItemCount(item2.getType()));
        longTermStorage.addItem(item2, 0);
        assertEquals("problem with adding zero items",
                100, longTermStorage.getItemCount(item2.getType()));
        longTermStorage.addItem(item2, -5);
        assertEquals("problem with adding negative number",
                100, longTermStorage.getItemCount(item2.getType()));
        assertEquals("item count counts item that doesnt appear",
                0 , longTermStorage.getItemCount(item1.getType()));
    }

    /**
     * testing the reset method
     */
    @Test
    public void test_reset() {
        assertEquals("test reset empty inventory",longTermStorageMap, longTermStorage.getInventory());
        longTermStorage.addItem(item2, 30);
        assertEquals("", 850, longTermStorage.getAvailableCapacity());
        longTermStorage.resetInventory();
        assertEquals("test reset for available capacity",
                FULL, longTermStorage.getAvailableCapacity());
        assertEquals("test reset for item count",
                0, longTermStorage.getItemCount(item2.getType()));
    }

    /**
     * test for the inventory method. if adding zero items or negativ number and the maps are still equal.
     */
    @Test
    public void test_map() {
        longTermStorageMap.put(item2.getType(), 40);
        longTermStorage.addItem(item2, 40);
        assertEquals("problem with get inventory method"
                , longTermStorageMap, longTermStorage.getInventory());
        longTermStorage.addItem(item1, 0);
        assertEquals(longTermStorageMap, longTermStorage.getInventory());
        longTermStorage.addItem(item1, -2);
        assertEquals(longTermStorageMap, longTermStorage.getInventory());
    }

    /**
     * Test for checking if football item and baseball bat can be on the same long term locker.
     */
    @Test
    public void test_football_baseball(){
        longTermStorage.addItem(item1, 5);
        assertEquals("adding zero baseball", ADDED, longTermStorage.addItem(item3, 0));
        assertEquals("adding baseball", ADDED, longTermStorage.addItem(item3, 4));


    }
}

