package filesprocessing.order;
import java.io.File;

/**
 * reverse class for filesprocessing.order classes, we want to get the opposite of the compare output
 */
public class Reverse_Order implements Order {
    Order order;

    /**
     * constructor for the class,
     * @param order the filesprocessing.order which need to be wrapped.
     */
    public Reverse_Order(Order order){
        this.order = order;
    }

    /**
     *  we want to get the opposite of the compare output for a specific filesprocessing.order.
     *  so we will just return the opposite of it.
     * @param filename1 file 1 to compare.
     * @param filename2 file 2 to compare.
     * @return return the opposite..
     */
    @Override
    public int compare(File filename1, File filename2) {
        return -(this.order.compare(filename1, filename2));
    }
}
