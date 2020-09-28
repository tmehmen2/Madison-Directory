// --== CS400 File Header Information ==--
// Name: Anush Ram Reddy Kethi Reddy
// Email: akethireddy@wisc.edu
// Team: LD
// Role: Backend Engineer
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>




import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;


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
    public Apartment searchByName(String name, HashTableMap<String,Apartment> apartmentMap){
        Apartment returnApartment;
        try {
            returnApartment = apartmentMap.get(name);
        }
        catch (NoSuchElementException e){
            returnApartment = null;
        }
        return returnApartment;
    }

    public ArrayList<Apartment> searchByLoc(String address, HashTableMap<String,ArrayList<Apartment>> apartmentMap){
        ArrayList<Apartment> returnApartments;
        try {
            returnApartments = apartmentMap.get(address);
        }
        catch (NoSuchElementException e){
            returnApartments = null;
        }

        return returnApartments;
    }

    public ArrayList<Apartment> searchByRoomNum(int rooms,HashTableMap<Integer,ArrayList<Apartment>> apartmentMap){
        ArrayList<Apartment> returnApartments;
        try {
            returnApartments = apartmentMap.get(rooms);
        }
        catch (NoSuchElementException e){
            returnApartments = null;
        }
        return returnApartments;
    }

    public ArrayList<Apartment> searchByPrice(int priceRange,HashTableMap<Integer, ArrayList<Apartment>> apartmentMap){
        ArrayList<Apartment> returnApartments;
        try {
            returnApartments = apartmentMap.get(priceRange);
        }
        catch (NoSuchElementException e){
                returnApartments = null;
            }
        return returnApartments;
    }

    public ArrayList<Apartment> compareTo(ArrayList<String> compareApartments,
                                          HashTableMap<String, Apartment> apartmentMap){
        ArrayList<Apartment> returnApartments = new ArrayList<Apartment>();
        for (String apt : compareApartments){
            try {
                returnApartments.add(apartmentMap.get(apt));
            }
            catch (NoSuchElementException e){
                returnApartments.add(null);
            }
        }
        return returnApartments;
    }
}
