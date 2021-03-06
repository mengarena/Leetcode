package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//Design a data structure that supports all following operations in average O(1) time.
//
//Note: Duplicate elements are allowed.
//insert(val): Inserts an item val to the collection.
//remove(val): Removes an item val from the collection if present.
//getRandom: Returns a random element from current collection of elements. 
//The probability of each element being returned is linearly related to the number of same value the collection contains.
//
//Example:
//
//// Init an empty collection.
//RandomizedCollection collection = new RandomizedCollection();
//
//// Inserts 1 to the collection. Returns true as the collection did not contain 1.
//collection.insert(1);
//
//// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
//collection.insert(1);
//
//// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
//collection.insert(2);
//
//// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
//collection.getRandom();
//
//// Removes 1 from the collection, returns true. Collection now contains [1,2].
//collection.remove(1);
//
//// getRandom should return 1 and 2 both equally likely.
//collection.getRandom();


//Yelp
//Hard
public class InsertDeleteGetRandom_O1_DuplicatesAllowed {

    private List<Integer> lstNums = null;    // Record all the numbers
    private Map<Integer, Set<Integer>> hm = null;   // Number, set of its indexes in lstNums
    private java.util.Random mRd = new java.util.Random();
    
    /** Initialize your data structure here. */
    public InsertDeleteGetRandom_O1_DuplicatesAllowed() {   //In the question, name should be RandomizedCollection
        lstNums = new ArrayList<Integer>();   
        hm = new HashMap<Integer, Set<Integer>>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        Set<Integer> setPos = hm.get(val);
        
        if (setPos == null) {
            setPos = new HashSet<Integer>();
            setPos.add(lstNums.size());
            hm.put(val, setPos);
            lstNums.add(val);
            return true;
        } else {
            setPos.add(lstNums.size());
            //hm.put(val, setPos);
            lstNums.add(val);
            return false;
        }
        
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        Set<Integer> setPos = hm.get(val);
        
        if (setPos == null) return false;
        
        int loc = setPos.iterator().next();
        
        if (setPos.size() == 1) {
            hm.remove(val);
        } else {
            setPos.remove(loc);
        }
        
        // Use the last number in lstNums to fill in the position of the num being deleted
        if (loc != lstNums.size() - 1) {
            int lastNum = lstNums.get(lstNums.size()-1);
            
            Set<Integer> setPosLast = hm.get(lastNum);
            setPosLast.remove(lstNums.size()-1);
            setPosLast.add(loc);
            
            lstNums.set(loc, lastNum);
        }
        
        lstNums.remove(lstNums.size()-1);
        
        return true;
        
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return lstNums.get(mRd.nextInt(lstNums.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
