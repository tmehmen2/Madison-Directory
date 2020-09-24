// --== CS400 File Header Information ==--
// Name: Taylor Mehmen
// Email: tmehmen@wisc.edu
// Team: LD
// TA: Divyanshu
// Lecturer:Gary Dahl
// Notes to Grader: HashTableMap.java

import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * HashTableMap.java implements all functionality of MapADT.java
 * This code makes an array of linkedlists folled with Array Objects
 *
 * @param (keyArray) array of linked lists to store ket,value pairs
 * @param (copyKeyArray) Used to point to main array when doubling
 * @param (capacity) Keeps track of initial capacity of array when created
 * @param (size) keeps track of the number of elemenmts in the array at all times
 * <p>Bugs: no known bugs
 *
 * @author Taylor Mehmen
 */

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType,ValueType>{
    private LinkedList<ArrayObject>[] keyArray;
    private LinkedList<ArrayObject>[] copyKeyArray;
    private int capacity;
    private int size;

    /**
     * This instantiates an array of size 10 and fills it with
     * empty linked lists
     *
     */

    public HashTableMap() {
        this.capacity = 10;
        keyArray= new LinkedList[capacity];
        for (int i=0;i<capacity;i++)
            keyArray[i] = new LinkedList<ArrayObject>();
        this.size=0;



    }

    /**
     * This instantiates an array of size (capacity) and fills it with
     * empty linked lists
     *
     */

    public HashTableMap(int capacity) {
        this.capacity=capacity;
        keyArray= new LinkedList[capacity];
        for (int i=0;i<capacity;i++)
            keyArray[i] = new LinkedList<ArrayObject>();
        this.size=0;
    }

    /**
     * The put method takes a key and a value an adds to the array
     * this is done through hashing and if an overlap occurs
     * it adds the key value pair to a linked list
     *
     * @param (toStore) (This is an ArrayObject that has a key and value)
     * @param (loadCapacity) (this represents the ratio of elemtns in the array
     *          to size - must not be over 80%)
     * @return (returns true if element is added, false if it is not)
     */

    public boolean put(KeyType key, ValueType value) {
        ArrayObject toStore = new ArrayObject(key,value);
        int placement = Math.abs(key.hashCode())%capacity;
        if (containsKey(key)) { //check for duplicate
            return false;
        }
        else{
            keyArray[placement].add(toStore); //add key to array
            size+=1; //increment size
            double loadCapacity = size/(float)capacity; //check load capacity
            if (loadCapacity>= 0.8){
                doubleHash(); //if greater double array size
            }

            return true;
        }

    }

    /**
     * The get method takes a ket and returns the value associated
     * If no key exists it throws the no such element exception
     *
     *
     * @param (placement) (after hashing this is the location of the key)
     * @param (found) (keeps track if key is found in the table)
     * @return (ValueType) value of key
     */

    public ValueType get(KeyType key) throws NoSuchElementException {
        int placement = Math.abs(key.hashCode())%capacity; //hash key
        int i = 0;
        boolean found = false;
        while (!found) { //check if key is found
            if (i>= keyArray[placement].size()){ //check to see if whole array is seached
                throw new NoSuchElementException(); //throw if not found
            }
            Object newKey=(keyArray[placement].get(i).getKey());
            if (newKey.equals(key)){ //check keys in table for match to search
                found = true;
                return (ValueType)(keyArray[placement].get(i).getValue()); //return value
            }
            i++;
        }
        throw new NoSuchElementException();
    }

    /**
     * Simple getter function that returns (size) # of elements
     * stored in the array
     *
     * @param (size) keeps track of the number of elemenmts in the array at all times
     * @return (int size) - number of elements in array
     */

    public int size() {
        return size;
    }

    /**
     * containsKey method returns boolean if the key is in the array
     * this checks where the key should be found through hashing
     * then iterates through the link list searching for a match
     *
     * @param (placement) (after hashing this is the location of the key)
     * @param (boolean returner) (saves value of whether key is found)
     * @return (returner) boolean of whether key is found
     */

    public boolean containsKey(KeyType key) {
        int placement = Math.abs(key.hashCode())%capacity; //find hash placement
        boolean returner = false;
        for (int i = 0; i < keyArray[placement].size(); i++) { //iterate through linked list
            Object newKey = keyArray[placement].get(i).getKey();
            if (newKey.equals(key)){ //check for match
                returner = true; //update whether found
            }
        }
        return returner;
    }

    /**
     * (This remove method removes a key,value pair from the array
     * it returns the value assoiated with the key to be removed
     * if no value is found it returns null
     *
     * @param (placement) (after hashing this is the location of the key)
     * @param (boolean found) (saves value of whether key is found)
     * @return (ValueType saved) the saved value to be returned
     */

    public ValueType remove(KeyType key) {
        int placement = Math.abs(key.hashCode())%capacity;
        boolean found = containsKey(key);
        ValueType saved = null;
        if (!found){
            saved = null; //if not found return null
        } else{
            for (int x=0;x<keyArray[placement].size();x++){
                Object newKey=(keyArray[placement].get(x).getKey());
                if (newKey.equals(key)){
                    saved = (ValueType)(keyArray[placement].get(x).getValue());
                    keyArray[placement].remove(x); //saves and removes object from array
                    size-=1; //decrement size
                    return saved;
                    //return saved; //return saved value
                }
            }
        }
        return saved;
    }

    /**
     * the clear method removes all key,value pairs from the array
     * it does this by storing new linked lists at each place in array
     *

     * @return (void)
     */

    public void clear() {
        for (int i=0;i<capacity;i++)
            keyArray[i] = new LinkedList<ArrayObject>(); //store new linkedList at each spot
        size = 0;
    }

    /**
     * The doubleHash method makes a copy of the original array
     * it then points the original array to a new one with double capacity
     * It then stores all values from the old array in their respective
     * placement through hashing
     *
     * @param (int copyCapacity) (a doubled version of the opriginal capacity)
     * @param (copyKeyArray) (a copied version of the original array)
     * @param (placement) (after hashing this is the location of the key)
     *
     * @return (void)
     */

    private void doubleHash() {
        int copyCapacity = 2 * capacity; //set copyCapacity
        copyKeyArray = keyArray; //copy original array
        keyArray= new LinkedList[copyCapacity];
        for (int i = 0; i < copyCapacity; i++) {
            keyArray[i] = new LinkedList<ArrayObject>();//set array as empty
        }
        for (int x = 0; x < capacity; x++) { //iterate through old array
            for (int y = 0; y < copyKeyArray[x].size(); y++) {
                KeyType copyKey = (KeyType) (copyKeyArray[x].get(y).getKey());
                ValueType copyValue = (ValueType) (copyKeyArray[x].get(y).getValue());
                int placement = Math.abs(copyKey.hashCode()) % copyCapacity;
                ArrayObject toStore = new ArrayObject(copyKey, copyValue);
                //store key,value pairs in new array
                keyArray[placement].add(toStore);
            }
        }
        capacity = copyCapacity; //change capacity original
    }


}


