import java.util.ArrayList;


/*BackEnd Developer: Mostly implement the search functionality and how they return
        searchByName(String name, HashTableMap map)
        searches the hashtable and returns information based on the apartment name
        Return: Apartment object with that name or null if not listed

        searchByLoc(String address, HashTableMap map)
        searches the hashtable and returns information based on the location
        Return: ArrayList of Apartment objects with this address, null if none

        searchByRoomNum(int rooms, HashTableMap map)
        Searches the hashtable and returns information based on number of rooms
        Return:ArrayList of Apartment objects with this number of rooms, null if none

        searchByPrice(lowPrice,highPrice, HashTableMap map)
        -searches for apartments in a particular price range.
        Return: ArrayList of Apartment objects within this price range, null if none

        compareTo([arrayList of Apartment Names(Strings)], HashTableMap map)
        Compares apartments on their price, number of rooms, amenities, and size.
        Return: ArrayList of Apartment objects within these names, null in every spot the name is not list
        This should return the same size list as the on entered*/

public class BackEnd {
    public Apartment searchByName(String name, HashTableMap apartmentMap){
        Apartment returnApartment = new Apartment("1","2",3,5,"5");

        return returnApartment;
    }

    public ArrayList<Apartment> searchByLoc(String address, HashTableMap apartmentMap){
        ArrayList<Apartment> returnApartments = new ArrayList<Apartment>();

        return returnApartments;
    }

    public ArrayList<Apartment> searchByRoomNum(int rooms, HashTableMap apartmentMap){
        ArrayList<Apartment> returnApartments = new ArrayList<Apartment>();
        return returnApartments;
    }

    public ArrayList<Apartment> searchByPrice(float lowPrice, float highPrice, HashTableMap apartmentMap){
        ArrayList<Apartment> returnApartments = new ArrayList<Apartment>();
        return returnApartments;
    }

    public ArrayList<Apartment> compareTo(ArrayList<Apartment> compareApartments, HashTableMap apartmentMap){
        ArrayList<Apartment> returnApartments = new ArrayList<Apartment>();
        return returnApartments;
    }
}
