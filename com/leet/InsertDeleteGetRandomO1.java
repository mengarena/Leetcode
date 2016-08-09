package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Design a data structure that supports all following operations in average O(1) time.
//
//insert(val): Inserts an item val to the set if not already present.
//remove(val): Removes an item val from the set if present.
//getRandom: Returns a random element from current set of elements. 
//Each element must have the same probability of being returned.
//
//Example:
//
//// Init an empty set.
//RandomizedSet randomSet = new RandomizedSet();
//
//// Inserts 1 to the set. Returns true as 1 was inserted successfully.
//randomSet.insert(1);
//
//// Returns false as 2 does not exist in the set.
//randomSet.remove(2);
//
//// Inserts 2 to the set, returns true. Set now contains [1,2].
//randomSet.insert(2);
//
//// getRandom should return either 1 or 2 randomly.
//randomSet.getRandom();
//
//// Removes 1 from the set, returns true. Set now contains [2].
//randomSet.remove(1);
//
//// 2 was already in the set, so return false.
//randomSet.insert(2);
//
//// Since 1 is the only number in the set, getRandom always return 1.
//randomSet.getRandom();


//Google, Uber, Twitter, Yelp, Amazon, Pocket Gems
public class InsertDeleteGetRandomO1 {


    private List<Integer> lstNums = null;
    private Map<Integer, Integer> hmNumLoc = null;   //Value, Position in lstNums
    private java.util.Random mRd = new java.util.Random();
    
    /** Initialize your data structure here. */
    public InsertDeleteGetRandomO1() {     //In the question, name should be RandomizedSet
        lstNums = new ArrayList<Integer>();
        hmNumLoc = new HashMap<Integer, Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (hmNumLoc.containsKey(val)) return false;
        
        hmNumLoc.put(val, lstNums.size());
        
        lstNums.add(val);
        
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!hmNumLoc.containsKey(val)) return false;
        
        int loc = hmNumLoc.get(val);
        
        if (loc != lstNums.size() - 1) {
            //Adjust the last number's location (in lstNums) to the one being deleted
            int lastNum = lstNums.get(lstNums.size()-1);
            hmNumLoc.put(lastNum, loc);
            lstNums.set(loc, lastNum);
        }
        
        hmNumLoc.remove(val);
        lstNums.remove(lstNums.size()-1);
        
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return lstNums.get(mRd.nextInt(lstNums.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */