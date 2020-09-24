// --== CS400 File Header Information ==--
// Name: Taylor Mehmen
// Email: tmehmen@wisc.edu
// Team: LD
// TA: Divyanshu
// Lecturer:Gary Dahl
// Notes to Grader: ArrayObject.java class is used to store key,value
//pairs as one object and to be able to get their values



/**
 * HashTableMap.java implements all functionality of MapADT.java
 * This code makes an array of linkedlists folled with Array Objects
 *
 * @param (KeyType key) the key of the object, type specified by user
 * @param (ValueType value) the value of the object, type specified by user
 * @param (capacity) Keeps track of initial capacity of array when created
 * @param (size) keeps track of the number of elemenmts in the array at all times
 * <p>Bugs: no known bugs
 *
 * @author Taylor Mehmen
 */

public class ArrayObject<KeyType,ValueType> {
    private KeyType key;
    private ValueType value;

    public ArrayObject(KeyType key, ValueType value) {
        this.key = key;
        this.value = value;
    }

    public KeyType getKey() {
        return key;
    }

    public ValueType getValue() {
        return value;
    }

}
