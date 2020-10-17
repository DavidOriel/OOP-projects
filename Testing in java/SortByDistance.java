import java.util.Comparator;
import oop.ex3.searchengine.Hotel;
import java.lang.Math.*;

/**
 * Comparator class for sorting hotel array
 */
public class SortByDistance implements Comparator<Hotel>   {
    double latitude;
    double longitude;
    private int EQUAL =0;
    private int SWICH =1;
    private int NOACTION = -1;
    private int POWER = 2;
    /**
     * the constructor of the class, here we define the latitude and the longitude which we get
     * as parameters.
     * @param latitude latitude of the place we need to check the distance from.
     * @param longitude longitude of the place we need to check the distance from.
     */

    SortByDistance( double latitude, double longitude ){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * the compare method, were calculate the distance of the first hotel from the specific place
     * and the distance of the second hotel from the specific place and compare them.
     * @param hotel1 the first in turn hotel in the array .
     * @param hotel2 the second in turn hotel in the array.
     * @return
     */
    public int compare(Hotel hotel1, Hotel hotel2 ) {
        double distance1 = euclidian_distance2(hotel1, this.latitude, this.longitude);
        double distance2 = euclidian_distance2(hotel2, this.latitude, this.longitude);
        double outcome = distance2 - distance1;
        if (outcome == EQUAL) {return hotel2.getNumPOI()- hotel1.getNumPOI();}
        if (outcome > EQUAL){return NOACTION;}
        return SWICH;
        }

    /**
     * calculate the distance between a hotel an a given latitude and longitude.
     * @param hotel1 the hotel which we want to calculate the distance.
     * @param latitude latitude parameter.
     * @param longitude longitude parameter.
     * @return return a double representing the distance.
     */
    public double euclidian_distance2(Hotel hotel1, double latitude, double longitude) {
        double long1 = hotel1.getLongitude();
        double lat1 = hotel1.getLatitude();
        double long2 = longitude;
        double lat2 = latitude;
        return Math.sqrt(Math.pow(long1 - long2, POWER) + Math.pow(lat1 - lat2, POWER));

    }
}

