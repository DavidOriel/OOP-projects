import oop.ex3.searchengine.Hotel;
import oop.ex3.searchengine.HotelDataset;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math.*;

/**
 * A class for a hotel booking site.
 */
public class BoopingSite {
    /**
     * The name of the file with the hotels data.
     */
    String name;

    /**
     * GLOBALS.
     */
    private int MAXLATITUDE = 90;
    private int MAXLONGITUDE = 180;

    /**
     * The constructor, here we will get the string that represent the file with the hotels data.
     *
     * @param name String of the name of the file that holds the data about the hotels.
     */
    public BoopingSite(String name) {
        this.name = name;
    }

    /**
     * This method returns an array of hotels located in the given city, sorted from the highest
     * star-rating to the lowest. Hotels that have the same rating will be organized according to
     * the alphabet order of the hotel’s (property) name. In case there are no hotels in the given city,
     * this method returns an empty array.
     *
     * @param city only the hotels in that city will be ordered in the returning array.
     * @return An array that hold the hotels near the given city with sorted with their star value.
     */
    public Hotel[] getHotelsInCityByRating(String city) {
        Hotel[] Hotels = HotelDataset.getHotels(this.name);
        ArrayList<Hotel> hotelList = create_array_list_by_city(Hotels, city);
        Collections.sort(hotelList, new SortByStarRate());
        Hotel[] hotels = new Hotel[hotelList.size()];
        hotels = hotelList.toArray(hotels);
        return hotels;
    }

    /**
     * Private function that adds the hotels that in the given city from the hotel database to an
     * ArrayList of hotels.
     *
     * @param hotels array of hotels.
     * @param city   only hotels from that city will be added.
     * @return an ArrayList of those hotels.
     */
    private ArrayList<Hotel> create_array_list_by_city(Hotel[] hotels, String city) {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        for (int i = 0; i < hotels.length; i++) {
            if (hotels[i].getCity().equals(city)) {
                hotelList.add(hotels[i]);
            }
        }
        return hotelList;
    }

    /**
     * This method
     * returns an array of hotels, sorted according to their (euclidean) distance from the given geographic
     * location, in ascending order. Hotels that are at the same distance from the given
     * location are organized according to the number of points-of-interest for which
     * they are close to (in a decreasing order).
     * In case of illegal input, this method returns an empty array.
     *
     * @param latitude
     * @param longitude
     * @return
     */
    public Hotel[] getHotelsByProximity(double latitude, double longitude) {
        if (Math.abs(latitude) > MAXLATITUDE || Math.abs(longitude) > MAXLONGITUDE) {
            Hotel[] hotels = new Hotel[0];
            return hotels;
        }
        ArrayList<Hotel> hotelList = new ArrayList<>();
        Collections.sort(hotelList, new SortByDistance(latitude, longitude));
        Hotel[] hotels = new Hotel[hotelList.size()];
        hotels = hotelList.toArray(hotels);
        return hotels;
    }

    /**
     * This method returns an array of hotels in the given city, sorted according to their
     * (euclidean) distance from the given geographic location,
     * in ascending order. Hotels that are at the same distance from the
     * given location are organized according to the number of points-of-interest
     * for which they are close to (in a decreasing order).
     * In case of illegal input, this method returns an empty array.
     *
     * @param city      The city of which the hotels need to be close to
     * @param latitude  the latitude parameter.
     * @param longitude the longitude parameter.
     * @return Array of hotels in the given city that appears in distance order.
     */
    public Hotel[] getHotelsInCityByProximity(String city, double latitude, double longitude) {
        if (Math.abs(latitude) > MAXLATITUDE || Math.abs(longitude) > MAXLONGITUDE) {
            Hotel[] hotels = new Hotel[0];
            return hotels;
        }
        Hotel[] Hotels = HotelDataset.getHotels(this.name);
        ArrayList<Hotel> hotelList = create_array_list_by_city(Hotels, city);
        Collections.sort(hotelList, new SortByDistance(latitude, longitude));
        Hotel[] hotels = new Hotel[hotelList.size()];
        hotels = hotelList.toArray(hotels);
        return hotels;
    }

}








