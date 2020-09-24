// --== CS400 File Header Information ==--
// Name: Taylor Mehmen
// Email: tmehmen@wisc.edu
// Team: LD
// Role: Data Wrangler
// TA: Divyanshu
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.ArrayList;

/**
 * (The DataHandler class is a middle point for the program.
 * It handles file IO, hashtable additions and calls
 * search algorithms for the front end to the back end.
 * It holds the working HashTableMap, the FileHandler(IO) and Backend(Searcher)
 * as well as the file name)
 *
 *
 * @author Taylor Mehmen
 */


public class DataHandler {
    private HashTableMap apartmentMap;
    private static String fileName = System.getProperty("user.dir")+"/src/ApartmentList.txt";
    private FileHandler apartmentFile;
    private BackEnd searcher;

    /**
     * (The DataHandler method is a constructor. It makes a Filehandler,
     * gets the aprtment list and parses it. Then it stores each apartment in a
     * private Hashtable. It also contructs a backEnd class used for searching and comparing
     * algorithms)
     *
     * @param (searcher) (backEnd object for searching)
     * @param (apartmentFile) (FileHandler IO object)
     * @param (apartmentMap) (Hashtable of apartments)
     */

    public DataHandler() {
        searcher = new BackEnd();
        apartmentFile = new FileHandler(fileName);
        ArrayList<String> apartmentLines = apartmentFile.getApartmentLines();
        this.apartmentMap= new HashTableMap(apartmentLines.size()*2);
        System.out.println(apartmentLines);
        for (String apt : apartmentLines){
            String[] aptInfo = apt.split("///");
            int numRooms = Integer.parseInt(aptInfo[2]);
            float price = Float.parseFloat(aptInfo[3]);
            Apartment newApartment =
                    new Apartment(aptInfo[0],aptInfo[1],numRooms,price,aptInfo[4]);
            apartmentMap.put(aptInfo[0],newApartment);
        }
    }

   /* public HashTableMap getApartmentMap(){
        return apartmentMap;
    }*/

    /**
     * (The ListApartment() method is used to add an apartment to the hashtable
     * and the .txt file for storage)
     *
     * @param (newApartment) (Apartment object to be added)
     */
    public void listApartment(Apartment newApartment){
        //add to .txt file
        String apartmentLine;
        String name = newApartment.getName();
        String address = newApartment.getAddress();
        String numRooms = Integer.toString(newApartment.getNumRooms());
        String price = Float.toString(newApartment.getPrice());
        String phoneNum = newApartment.getPhoneNum();
        apartmentLine = (name+"///"+address+"///"+numRooms+"///"+price+"///"+phoneNum);
        apartmentFile.addToApartmentLines(apartmentLine);
        apartmentFile.updateFile();
        //add to hashtable
        apartmentMap.put(name,newApartment);
    }

    /**
     * (searches the hashtable and returns information based on the apartment name)
     *
     * @param (name) (Apartment name to be searched)
     * @return (Apartment object with that name or null if not listed)
     */
    public Apartment searchByName(String name){
        Apartment returnApartment =
                searcher.searchByName(name,apartmentMap);
        return returnApartment;
    }

    /**
     * (searches the hashtable and returns information based on the location.)
     *
     * @param (address) (address to be searched for)
     * @return (ArrayList of Apartment objects with this address, null if none)
     */
    public ArrayList<Apartment> searchByLoc(String address){
        ArrayList<Apartment> returnApartments =
                searcher.searchByLoc(address,apartmentMap);
        return returnApartments;
    }

    /**
     * (Searches the hashtable and returns information based on number of rooms)
     *
     * @param (rooms) (The number of rooms being searched for)
     * @return (ArrayList of Apartment objects with this number of rooms, null if none)
     */
    public ArrayList<Apartment> searchByRoomNum(int rooms){
        ArrayList<Apartment> returnApartments =
                searcher.searchByRoomNum(rooms,apartmentMap);
        return returnApartments;
    }

    /**
     * (searches for apartments in a particular price range.)
     *
     * @param (lowPrice) (float of low price point)
     * @param (highPrice) (float of high price point)
     * @return (ArrayList of Apartment objects within this price range, null if none)
     */
    public ArrayList<Apartment> searchByPrice(float lowPrice, float highPrice){
        ArrayList<Apartment> returnApartments =
                searcher.searchByPrice(lowPrice, highPrice, apartmentMap);
        return returnApartments;
    }

    /**
     * (searches the hashtable and returns information based on the apartment name.)
     *
     * @param (compareApartments) (the list of apartment names to return for comparison)
     * @return (ArrayList of Apartment objects within these names,
     * null in every spot the name is not list)
     */
    public ArrayList<Apartment> compareTo(ArrayList<Apartment> compareApartments){
        ArrayList<Apartment> returnApartments =
                searcher.compareTo(compareApartments,apartmentMap);
        return returnApartments;
    }



}
