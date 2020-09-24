// --== CS400 File Header Information ==--
// Name: Taylor Mehmen
// Email: tmehmen@wisc.edu
// Team: LD
// Role: Data Wrangler
// TA: Divyanshu
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>


/**
 * This is a class to hold data for apartment objects that are being stored
 * in a hashtable. The main use is to hold private data
 * All methods are getters and setters
 *
 *
 * @author Taylor Mehmen
 */
public class Apartment {
    private String name; //ex. "Allen House 24
    private String address; //ex. "2130 University Ave."
    private int numRooms; //ex. 4
    private float price; // 575.00
    private String phoneNum; // "31934452345"

    public Apartment(String name, String address, int numRooms, float price, String phoneNum) {
        this.name = name;
        this.address = address;
        this.numRooms = numRooms;
        this.price = price;
        this.phoneNum = phoneNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public float getPrice() {
        return price;
    }

    public String getPhoneNum() {
        return phoneNum;
    }
}
