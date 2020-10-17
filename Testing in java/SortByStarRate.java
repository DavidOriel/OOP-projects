import java.util.Comparator;
import oop.ex3.searchengine.Hotel;

/**
 * Comparator class for sorting hotels by their star rate, if two hotels has the same star rate they will
 * be ordered by their alphabetic names
 */
public class SortByStarRate implements Comparator<Hotel> {
    private int EQUAL = 0;
    /**
     * This method compares two hotels and decide which hotel will be first by the star value and alphabetic
     * value.
     * @param hotel1 the first hotel in the array.
     * @param hotel2 the second hotel in the array.
     * @return return negative
     */
    public int compare(Hotel hotel1, Hotel hotel2){
        int star1 = hotel1.getStarRating();
        int star2 = hotel2.getStarRating();
        int outcome = star2- star1;
        if (outcome == EQUAL){
            return hotel1.getPropertyName().compareTo(hotel2.getPropertyName());}
        return outcome;
        }
 }

