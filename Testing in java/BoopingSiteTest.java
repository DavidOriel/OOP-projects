import oop.ex3.searchengine.Hotel;

import static org.junit.Assert.*;

import org.junit.*;

import java.lang.Math.*;

public class BoopingSiteTest {


    private int POW = 2;
    private double LATITUDE1 = 26.76625542;
    private double LONGITUDE1 = 75.83593618;
    private double LATITUDE2 = 25.61165883;
    private double LONGITUDE2 = 85.1426855;


    BoopingSite boopingSite1 = new BoopingSite("hotels_tst1.txt");
    BoopingSite boopingSite2 = new BoopingSite("hotels_tst2.txt");
    /**
     * before every test we will create new objects to work on.
     */


    /**
     * test for the getHotelInCityByRating function, the test checks if the hotels are in the given city,
     * and if they are in order, the most starValue hotel will be first, also checks if two hotels with same
     * star value are orderet by alphabetic order.
     */
    @Test
    public void test_getHotelInCityByRating() {
        Hotel[] hotels = boopingSite1.getHotelsInCityByRating("Goa");
        for (int i = 0; i < hotels.length; i++) {
            assertEquals("", hotels[i].getCity(), "Goa");
        }
        for (int i = 0; i < hotels.length - 1; i++) {
            int hotel1_stars = hotels[i].getStarRating();
            int hotel2_stars = hotels[i + 1].getStarRating();
            assertTrue("", hotel1_stars >= hotel2_stars);
            if (hotel1_stars == hotel2_stars) {
                assertTrue(hotels[i].getPropertyName().compareTo(hotels[i + 1].getPropertyName()) >= 0);
            }
        }
    }

    /**
     * test if the first function and the third function returns empty array if the city
     * is not in the given data.
     */
    @Test
    public void test_for_city_not_in_data() {
        Hotel[] hotels1 = boopingSite1.getHotelsInCityByRating("Jerusalem");
        Hotel[] hotels2 = boopingSite1.getHotelsInCityByRating("Tel-Aviv");
        Hotel[] hotels0 = new Hotel[0];
        assertArrayEquals("", hotels0, hotels1);
        assertArrayEquals("", hotels0, hotels2);
    }

    /**
     * test for the second method, the test will check if the hotels in the array are ordered by the distance
     * from the given latitude and longitude. in case they are equal, the test will check if they ordered by
     * their POI.
     */
    @Test
    public void test_getHotelsByProximity() {
        Hotel[] hotels = boopingSite1.getHotelsByProximity(LATITUDE1, LONGITUDE1);
        for (int i = 0; i < hotels.length - 1; i++) {
            double distancei = euclidian_distance2(hotels[i], LATITUDE1, LONGITUDE1);
            double distancei1 = euclidian_distance2(hotels[i + 1], LATITUDE1, LONGITUDE1);
            assertTrue("", distancei <= distancei1);
            if (distancei == distancei1) {
                assertTrue(hotels[i].getNumPOI() >= hotels[i + 1].getNumPOI());
            }
        }
    }

    /**
     * test the getHotelsInCityByProximity method, first the test will check if all the hotels in the
     * array are in the given city, then the test will check if the hotels in the array
     * are ordered by the distance from the given latitude and longitude. in case they are equal,
     * the test will check if they ordered by their POI.
     */
    @Test
    public void test_getHotelsInCityByProximity() {
        Hotel[] hotels = boopingSite1.getHotelsInCityByRating("Delhi");
        for (int i = 0; i < hotels.length - 1; i++) {
            assertEquals("", hotels[i].getCity(), "Delhi");
        }
        for (int i = 0; i < hotels.length - 1; i++) {
            double distancei = euclidian_distance2(hotels[i], LATITUDE2, LONGITUDE2);
            double distancei1 = euclidian_distance2(hotels[i + 1], LATITUDE2, LONGITUDE2);
            assertTrue("", distancei <= distancei1);
            if (distancei == distancei1) {
                assertTrue(hotels[i].getNumPOI() >= hotels[i + 1].getNumPOI());
            }
        }
    }

    /**
     * test for checking correct input of the latitude and longitude,
     * with real data as input and no data input.
     */
    @Test
    public void test_for_wrong_latitude_longitude() {
        Hotel[] hotels = new Hotel[0];
        assertArrayEquals(hotels, boopingSite2.getHotelsByProximity(91, 0));
        assertArrayEquals(hotels, boopingSite2.getHotelsByProximity(90, 200));
        assertArrayEquals(hotels, boopingSite1.getHotelsInCityByProximity("Manali", 300, 50));
        assertArrayEquals(hotels, boopingSite1.getHotelsInCityByProximity("Manali", 40, 200));
    }

    @Test
    public void Special_tests() {
        Hotel[] hotels = new Hotel[0];
        assertArrayEquals("", hotels, boopingSite1.getHotelsInCityByRating(""));
    }

    /**
     * this method calculates the distance between a given altitude and longitute and a hotel.
     *
     * @param hotel1    the hotel object.
     * @param latitude  the latitude parameter .
     * @param longitude the longitude parameter
     * @return A double that represent the distance.
     */
    public double euclidian_distance2(Hotel hotel1, double latitude, double longitude) {
        double long1 = hotel1.getLongitude();
        double lat1 = hotel1.getLatitude();
        double long2 = longitude;
        double lat2 = latitude;
        return Math.sqrt(Math.pow(long1 - long2, POW) + Math.pow(lat1 - lat2, POW));
    }

}


