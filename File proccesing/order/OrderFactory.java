package filesprocessing.order;

import filesprocessing.Type1_Exceptions;

import java.util.Arrays;

public class OrderFactory {
    String str;
    Order order;
    String[] orderArray = new String[3];
    String ABS = "abs";
    String TYPE = "type";
    String SIZE = "size";
    String REVERSE = "REVERSE";
    String[] orderparts;

    /**
     * the constructor of the factory, were creating an legal filesprocessing.order array for checking if our
     * input is legal.
     */
    public OrderFactory() {
        orderArray[0] = ABS;
        orderArray[1] = TYPE;
        orderArray[2] = SIZE;
    }

    /**
     * findes the correctc filesprocessing.order from a given string
     * @param string the instruction string the tells which filesprocessing.order needs to be created
     * @return the filesprocessing.order that has created.
     * @throws Type1_Exceptions throws type one exception if the creation process failed.
     */
    public Order finedOrder(String string) throws Type1_Exceptions {
        this.str = string;
        this.orderparts = this.str.split("#");
        if (!(Arrays.asList(orderArray).contains(orderparts[0]))) {
            throw new Type1_Exceptions();
        }
        switch (orderparts[0]) {
            case "abs":
                order = new abs_Order();
                break;
            case "type":
                order = new Type_Order();
                break;
            case "size":
                order = new size_Order();
                break;

        }
        if (orderparts[orderparts.length - 1].equals(REVERSE)) {
            order = new Reverse_Order(order);
        }
        return order;
    }
}
