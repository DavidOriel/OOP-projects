package filesprocessing;

import filesprocessing.filter.All_Filter;
import filesprocessing.filter.Filter;
import filesprocessing.filter.FilterFactory;
import filesprocessing.order.Order;
import filesprocessing.order.OrderFactory;
import filesprocessing.order.abs_Order;

import java.util.ArrayList;

public class Section {
    FilterFactory filterFactory = new FilterFactory();
    OrderFactory orderFactory = new OrderFactory();
    Filter filter;
    Order order;
    String filterString;
    String orderString;
    int line;
    boolean isError = false;
    ArrayList<String> errorString;
    String ERROR = "Warning in line ";

    /**
     * the constructor of that class, that class represent a single section for ordering a file package.
     *
     * @param filterString string represents the conditions which the specific filter method needs.
     * @param orderString  String represent the conditions which the specific filesprocessing.order method needs.
     * @param line         the current line of the file we are running on.
     */
    Section(String filterString, String orderString, int line) {
        this.filterString = filterString;
        this.orderString = orderString;
        this.line = line;
        errorString = new ArrayList<String>();
    }

    /**
     * create the filter object from the input.
     */
    private void createFilter() {
        try {
            filter = filterFactory.fined_filter_object(this.filterString);
        } catch (Type1_Exceptions e) {
            filter = new All_Filter();
            errorString.add(ERROR + line);

        }
    }

    /**
     * create an filesprocessing.order object from the input.
     */
    private void createOrder() {
        if (orderString.equals("")) {
            order = new abs_Order();
            this.line += 2;
        } else {
            try {
                order = orderFactory.finedOrder(this.orderString);
            } catch (Type1_Exceptions e) {
                order = new abs_Order();
                this.line += 2;
                errorString.add(ERROR + line);
            }
        }

    }

    /**
     * will be called from the manager to create filter and filesprocessing.order objects.
     */
    public void creator() {
        createFilter();
        createOrder();
    }

    /**
     * returns the filter object
     *
     * @return a filter object.
     */
    public Filter getFilter() {
        return filter;
    }

    /**
     * returns the filesprocessing.order object
     *
     * @return filesprocessing.order object.
     */
    public Order getOrder() {
        return order;
    }

}
