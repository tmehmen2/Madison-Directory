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
    private HashTableMap<String,Apartment> nameApartmentMap;
    private HashTableMap<String,ArrayList<Apartment>> locApartmentMap;
    private HashTableMap<Integer,ArrayList<Apartment>> roomApartmentMap;
    private HashTableMap<Integer, ArrayList<Apartment>> priceApartmentMap;

    private static String fileName = System.getProperty("user.dir")+"/src/ApartmentList.txt";
    private FileHandler apartmentFile;
    private BackEnd searcher;

    /**
     * (The DataHandler method is a constructor. It makes a Filehandler,
     * gets the aprtment list and parses it. Then it stores each apartment in a
     * private Hashtable. It also contructs a backEnd class used for searching and comparing
     * algorithms)
     *
     * //@param (searcher) (backEnd object for searching)
     * //@param (apartmentFile) (FileHandler IO object)
     * //@param (apartmentMap) (Hashtable of apartments)
     */

    public DataHandler() {
        searcher = new BackEnd();
        apartmentFile = new FileHandler(fileName);
        ArrayList<String> apartmentLines = apartmentFile.getApartmentLines();
        this.nameApartmentMap= new HashTableMap(apartmentLines.size()*2);
        this.locApartmentMap= new HashTableMap(apartmentLines.size());
        this.roomApartmentMap= new HashTableMap(12);
        this.priceApartmentMap= new HashTableMap(12);
        //System.out.println(apartmentLines);
        ArrayList<Apartment>[] priceList = new ArrayList[12];
        ArrayList<ArrayList<Apartment>> locList = new ArrayList<ArrayList<Apartment>>();
        ArrayList<String> addressList = new ArrayList<>();
        ArrayList<Apartment>[] roomNumList = new ArrayList[12];
        for (String apt : apartmentLines){
            String[] aptInfo = apt.split("///");
            int numRooms = Integer.parseInt(aptInfo[2]);
            float price = Float.parseFloat(aptInfo[3]);
            String name = aptInfo[0];
            String address = aptInfo[1];
            String phoneNum = aptInfo[4];
            Apartment newApartment =
                    new Apartment(name,address,numRooms,price,phoneNum);
            nameApartmentMap.put(aptInfo[0],newApartment);
            //Do location list
            if (!addressList.contains(address)){
                addressList.add(address);
                locList.add(new ArrayList<Apartment>());
                locList.get(addressList.indexOf(address)).add(newApartment);
            }
            else
                locList.get(addressList.indexOf(address)).add(newApartment);


            //add to appropriate numRoom List
            if (roomNumList[numRooms] == null){
                roomNumList[numRooms]=new ArrayList<Apartment>();
                roomNumList[numRooms].add(newApartment);
            }
            else{
                roomNumList[numRooms].add(newApartment);
            }

            //add to appropriate priceList
            int priceHash = ((int)price/1000);
            //System.out.println(priceHash);
            if (priceList[priceHash] == null){
                priceList[priceHash]=new ArrayList<Apartment>();
                priceList[priceHash].add(newApartment);
            }
            else{
                priceList[priceHash].add(newApartment);
            }
        }
        // put lists in appropriate hashtables
        for (int z = 0; z<locList.size(); z++) {
            locApartmentMap.put(addressList.get(z), locList.get(z));
        }
        for (int x = 0; x<12; x++){
            if (roomNumList[x]!=null) {
                roomApartmentMap.put(x,roomNumList[x]);
            }
            if (priceList[x] != null) {
                priceApartmentMap.put(x,priceList[x]);
            }
        }

    }



    /**
     * (The ListApartment() method is used to add an apartment to the hashtable
     * and the .txt file for storage)
     *
     * @param (newApartment) (Apartment object to be added)
     */
    public void listApartment(Apartment newApartment){
        //add to .txt file
        boolean nameBool;

        String apartmentLine;
        String name = newApartment.getName();
        String address = newApartment.getAddress();
        String numRooms = Integer.toString(newApartment.getNumRooms());
        String price = Float.toString(newApartment.getPrice());
        String phoneNum = newApartment.getPhoneNum();
        apartmentLine = (name+"///"+address+"///"+numRooms+"///"+price+"///"+phoneNum);

        //add to running ArrayList
        //add to hashtable
        nameBool = nameApartmentMap.put(name,newApartment);
        //Do location list
        if (nameBool) {
            apartmentFile.addToApartmentLines(apartmentLine);
            apartmentFile.updateFile();
            if (!locApartmentMap.containsKey(address)) {
                ArrayList<Apartment> addressList = new ArrayList<Apartment>();
                addressList.add(newApartment);
                locApartmentMap.put(address, addressList);
            } else
                locApartmentMap.get(address).add(newApartment);


            //add to appropriate numRoom List
            if (!roomApartmentMap.containsKey(newApartment.getNumRooms())) {
                ArrayList<Apartment> roomNumList = new ArrayList<Apartment>();
                roomNumList.add(newApartment);
                roomApartmentMap.put(newApartment.getNumRooms(), roomNumList);
            } else {
                roomApartmentMap.get(newApartment.getNumRooms()).add(newApartment);
            }

            //add to appropriate priceList
            int priceHash = ((int) newApartment.getPrice() / 1000);
            if (!priceApartmentMap.containsKey(priceHash)) {
                System.out.println("here");
                ArrayList<Apartment> priceList = new ArrayList<Apartment>();
                priceList.add(newApartment);
                priceApartmentMap.put(priceHash, priceList);
            } else {
                priceApartmentMap.get(priceHash).add(newApartment);
            }
        }
        else{
            System.out.println("already Listed");
        }
    }




    /**
     * (searches the hashtable and returns information based on the apartment name)
     *
     * @param (name) (Apartment name to be searched)
     * @return (Apartment object with that name or null if not listed)
     */
    public Apartment searchByName(String name){
        Apartment returnApartment =
                searcher.searchByName(name,nameApartmentMap);
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
                searcher.searchByLoc(address,locApartmentMap);
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
                searcher.searchByRoomNum(rooms,roomApartmentMap);
        return returnApartments;
    }

    /**
     * (searches for apartments in a particular price range.)
     *
     * //@param (lowPrice) (float of low price point)
     * //@param (highPrice) (float of high price point)
     * @return (ArrayList of Apartment objects within this price range, null if none)
     */
    public ArrayList<Apartment> searchByPrice(int priceRange){
        ArrayList<Apartment> returnApartments =
                searcher.searchByPrice(priceRange, priceApartmentMap);
        return returnApartments;
    }

    /**
     * (searches the hashtable and returns information based on the apartment name.)
     *
     * @param (compareApartments) (the list of apartment names to return for comparison)
     * @return (ArrayList of Apartment objects within these names,
     * null in every spot the name is not list)
     */
    public ArrayList<Apartment> compareTo(ArrayList<String> compareApartments){
        ArrayList<Apartment> returnApartments =
                searcher.compareTo(compareApartments,nameApartmentMap);
        return returnApartments;
    }



}
