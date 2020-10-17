import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * A class for testing the performance of few data structures including the open and the closed hash set.
 */
public class SimpleSetPerformanceAnalyzer {
    private final int MILI_SEC = 1000000;
    private final int LINKED_LIST_NOTATION = 7000;
    private final int NOTATIONS = 70000;
    private final int LINKED_LIST_INDEX = 2;
    private String text1 = "data1.txt";
    private String text2 = "data2.txt";
    private SimpleSet[] array;
    private LinkedList<String> linkedList;
    private TreeSet<String> treeSet;
    private HashSet<String> hashSet;
    private OpenHashSet openHashSet;
    private ClosedHashSet closedHashSet;
    private String[] data1 = Ex4Utils.file2array(text1);
    private String[] data2 = Ex4Utils.file2array(text2);
    private String[] namesArray;

    /**
     * creating an array that will be used for printing results.
     */
    private SimpleSetPerformanceAnalyzer() {
        namesArray = new String[5];
        namesArray[0] = "open hashSet";
        namesArray[1] = "closed hashSet";
        namesArray[2] = "linkedList";
        namesArray[3] = "treeSet";
        namesArray[4] = "hashSet";
    }

    /**
     * Before every test we will run that method for initializing new data structures and then we can
     * run tests on them.
     */
    private void beforeTest() {
        this.hashSet = new HashSet<String>();
        this.treeSet = new TreeSet<String>();
        this.linkedList = new LinkedList<String>();
        this.openHashSet = new OpenHashSet();
        this.closedHashSet = new ClosedHashSet();
        this.array = new SimpleSet[5];
        this.array[0] = this.openHashSet;
        this.array[1] = this.closedHashSet;
        this.array[2] = new CollectionFacadeSet(this.linkedList);
        this.array[3] = new CollectionFacadeSet(this.treeSet);
        this.array[4] = new CollectionFacadeSet(this.hashSet);

    }

    /**
     * Manually adding all the items from data 1 to the linked list, the hash tree and the hash set.
     */
    private void add_items_txt1() {
        beforeTest();
        for (int i = 0; i < this.array.length; i++) {
            for (String item : data1) {
                array[i].add(item);
            }
        }
    }

    /**
     * Manually adding all the items from data 2 to the linked list, the hash tree and the hash set.
     */
    private void add_items_txt2() {
        beforeTest();
        for (int i = 2; i < this.array.length; i++) {
            for (String item : data1) {
                array[i].add(item);
            }
        }
    }

    /**
     * find the iteretion for a specific data structure, if it is a linked list-
     * 7,000 and 70,000 for all the others
     * @param dataStructure the data structure to check.
     * @return integer 7,000 or 70,000.
     */
    private int findIterations(SimpleSet dataStructure) {
        if (dataStructure == this.array[LINKED_LIST_INDEX]) {
            return LINKED_LIST_NOTATION;
        } else {
            return NOTATIONS;
        }
    }

    /** the warm up function warms up the computer before the actual tests can run
     * @param dataStructure the data structure we are currently testing
     * @param string we are using the contains on that string
     * @param iterations the iterations needed for that specific data structure (if it is a linked list-
     * 7000 and 70000 for all the others
     */
    private void warmUp(SimpleSet dataStructure, String string, int iterations) {
        if (dataStructure == this.array[LINKED_LIST_INDEX]) {
            for (int i = 0; i < iterations; i++) {
                dataStructure.contains(string);
            }
        }
    }

    /**
     * First test for checking run time for adding Strings one by one from a given file to every
     * data structure.
     */
    private void test_add_txt1() {
        beforeTest();
        int index = 0;
        for (SimpleSet data_structure : this.array) {
            long timeBefore = System.nanoTime();
            for (String item : data1) {
                data_structure.add(item);
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println("test time for " + namesArray[index] + " is " +
                    difference / MILI_SEC + " ms");
            index += 1;
        }
    }

    /**
     * Second test for checking run time for adding Strings one by one from another given file to every
     * data structure.
     */
    private void test_add_txt2() {
        beforeTest();
        int index = 0;
        for (SimpleSet data_structure : this.array) {
            long timeBefore = System.nanoTime();
            for (String item : data2) {
                data_structure.add(item);
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println("test time for " + namesArray[index] + " is " +
                    difference / MILI_SEC + " ms");
            index += 1;
        }
    }

    /**
     * A test for checking the method "contains", by searching the String "hi" in every data structure.
     * the word "hi" has a different Hash code then the other words in the file.
     */
    private void test_contains(String string, int integer) {
        if (integer == 1){add_items_txt1();}
        if (integer == 2){add_items_txt2();}
        int index = 0;
        for (SimpleSet data_structure : this.array) {
            int iterations = findIterations(data_structure);
            warmUp(data_structure, string, iterations);
            long timeBefore = System.nanoTime();
            for (int i = 0; i<iterations; i++){
                data_structure.contains(string);
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println("test time for " + namesArray[index] + " is " +
                    difference/iterations + " ns");
            index += 1;
        }
    }

    /**
     * main method for running the tests. The user can choose which tests he would like to run
     * form the command line.
     *
     * @param args arguments from the user that given from the command line.
     */
    public static void main(String[] args) {
        if (args.length > 6) {
            System.out.println("to many arguments");
            System.exit(0);
        }
        /**
         * the tests will run threw parameters given by the user, ("a" for the first test ...
         * until f for the last tests.
         *
         */
        SimpleSetPerformanceAnalyzer tester = new SimpleSetPerformanceAnalyzer();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "a":
                    tester.test_add_txt1();
                    break;
                case "b":
                    tester.test_add_txt2();
                    break;
                case "c":
                    tester.test_contains("hi", 1);
                    break;
                case "d":
                    tester.test_contains("-13170890158", 1);
                    break;
                case "e":
                    tester.test_contains("23", 2);
                    break;
                case "f":
                    tester.test_contains("hi", 2);
                    break;
            }

        }
    }
}

