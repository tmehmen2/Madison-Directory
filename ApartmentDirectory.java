import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ApartmentDirectory {
    public static void main(String[] args) {
        DataHandler dataHandler = new DataHandler();
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome");
        System.out.println("1. List or 2. Search");
        int choice1 = scanner.nextInt();
        if (choice1 == 1){
            scanner.nextLine();
            System.out.println("Name of apartment:");
            String name = scanner.nextLine();
            System.out.println("room number:");
            String num = scanner.nextLine();
            name = name+" "+num;
            System.out.println("Price per month:");
            float price = scanner.nextFloat();
            scanner.nextLine();
            System.out.println("Rooms available:");
            int numRooms = scanner.nextInt();
            scanner.nextLine();
            System.out.println("address:");
            String address = scanner.nextLine();
            System.out.println("Phone Number");
            String phoneNum = scanner.nextLine();
            Apartment newApartment = new Apartment(name, address, numRooms, price, phoneNum);
            dataHandler.listApartment(newApartment);
            System.out.println("Thanks");
        }
        else if (choice1 == 2){
            System.out.println("How would you like to Search?");
            System.out.println("1. name of apartment");
            System.out.println("2. location:");
            System.out.println("3. number of rooms");
            System.out.println("4. price");
            System.out.println("5. comparative");
            int choice2 = scanner.nextInt();
            scanner.nextLine();
            if (choice2 == 1){
                System.out.println("Name of apartment:");
                String name = scanner.nextLine();
                System.out.println("room number:");
                String num = scanner.nextLine();
                name = name+" "+num;
                Apartment apartment = dataHandler.searchByName(name);
                System.out.println(apartment.getName());
            }
            else if (choice2 == 2){
                System.out.println("address:");
                String address = scanner.nextLine();
                ArrayList<Apartment> apartmentList = dataHandler.searchByLoc(address);
                for (int x = 0; x < apartmentList.size(); x++){
                    System.out.println(apartmentList.get(x).getName());

                }
            }
            else if (choice2 == 3){
                System.out.println("Rooms available:");
                int numRooms = scanner.nextInt();
                ArrayList<Apartment> apartmentList = dataHandler.searchByRoomNum(numRooms);
                scanner.nextLine();
                for (int x = 0; x < apartmentList.size(); x++){
                    System.out.println(apartmentList.get(x).getName());

                }
            }
            else if (choice2 == 4){
                System.out.println("0. 0-999:");
                System.out.println("2. 2000-2999:");
                System.out.println("3. 3000-3999:");
                System.out.println("4. 4000-4999:");
                System.out.println("5. 5000-5999:");
                System.out.println("6. 6000-6999:");
                System.out.println("7. 7000-7999:");
                int priceHash = scanner.nextInt();
                scanner.nextLine();
                ArrayList<Apartment> apartmentList = dataHandler.searchByPrice(priceHash);
                for (int x = 0; x < apartmentList.size(); x++){
                    System.out.println(apartmentList.get(x).getName());
                }
            }
            else if (choice2 == 5){
                ArrayList<String> apartmentNames= new ArrayList<>();
                System.out.println("Enter all the names of apartments you want to compare");
                System.out.println("Hit Enter after each one and type 1 when done");
                boolean done = false;
                while (!done){
                    System.out.println("press 1 if done");
                    int choice3 = scanner.nextInt();
                    scanner.nextLine();
                    if (choice3 != 1) {
                        System.out.println("Name of apartment:");
                        String name = scanner.nextLine();
                        System.out.println("room number:");
                        String num = scanner.nextLine();
                        name = name + " " + num;
                        apartmentNames.add(name);
                    }
                    else{
                        done = true;
                        ArrayList<Apartment> apartmentList = dataHandler.compareTo(apartmentNames);
                        for (int x = 0; x < apartmentList.size(); x++){
                            System.out.println(apartmentList.get(x).getName());

                        }
                    }
                }

            }
            else{
                System.out.println("wrong input");
            }

        }
        else{
            System.out.println("wrong input");
        }


        // start with the main front end code here
        // to use any of the search functions just call the dataHandler methods
        //make sure to use the correct input and you will receive the right output
    }
}
