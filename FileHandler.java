// --== CS400 File Header Information ==--
// Name: Taylor Mehmen
// Email: tmehmen@wisc.edu
// Team: LD
// Role: Data Wrangler
// TA: Divyanshu
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * This class handles the IO of the program to a file "ApartmentList.txt"
 * The purpose of this is to hold memory of the listed apartments
 * When the program is not running
 * It holds a working list of apartments while it is active and will
 * write back to the file with update()
 *
 * <p>Bugs: (a list of bugs and other problems)
 *
 * @author Taylor Mehmen
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {
    private ArrayList<String> apartmentLines = new ArrayList<String>();
    String fileName;
    /**
     * (This constructor method opens the file and reads the apartments
     * line by line. It then puts them into a list stored within the class.
     * After reading it closes the file for safety.)
     *
     * @param (fileName) (the name of the file for IO)
     */

    public FileHandler(String fileName) {
        this.fileName=fileName;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while(line !=null){
                apartmentLines.add(line);
                //System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * (The getApartmentLines() method is used for whichever class makes this object
     * The class can then get a list of all apartments in the file)
     *
     * @return (apartmentLines - a list of all apartments in the list)
     */
    public ArrayList<String> getApartmentLines() {
        return apartmentLines;
    }

    /**
     * (The addToApartmentLines() method adds an apartment string to the working list)
     */
    public void addToApartmentLines(String line){
        apartmentLines.add(line);
    }


    /**
     * (The updateFile() method opens the file for writing
     * It then rewrites the file with all apartments currently in working list.
     *
     * @param (writer) (BufferedWriter object tp output to)
     */
    public void updateFile(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            for (int x = 0; x<apartmentLines.size(); x++){
                writer.write(apartmentLines.get(x));
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
