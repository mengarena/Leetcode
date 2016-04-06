package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//Design and implement a TwoSum class. It should support the following operations: add and find.
//
//add - Add the number to an internal data structure.
//find - Find if there exists any pair of numbers which sum is equal to the value.
//
//For example,
//add(1); add(3); add(5);
//find(4) -> true
//find(7) -> false

//Linkedin
public class TwoSumIIIDataStructureDesign {

	public TwoSumIIIDataStructureDesign() {
		// TODO Auto-generated constructor stub
	}

	
	//Accepted: 90%
	private Map<Integer, Integer> hmFreq = new HashMap<Integer, Integer>();
	private List<Integer> lstNumbers = new ArrayList<Integer>();
	
    // Add the number to an internal data structure.
	public void add(int number) {		
		if (hmFreq.containsKey(number)) {
			//hmFreq.put(number, hmFreq.get(number)+1);   //Similar performance
			hmFreq.put(number, 2);  //Don't need to same two many duplicate values, only two is enough
		} else {
			hmFreq.put(number, 1);
			lstNumbers.add(number);
		}
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {	    
        for (int i=0; i<lstNumbers.size(); i++) {
        	int num = lstNumbers.get(i);
        	int nOther = value - num;
        	
        	if (nOther == num && hmFreq.get(num) > 1) return true;
        	if (nOther != num && hmFreq.containsKey(nOther)) return true;
        }
	    
	    return false;
	}

	
	

	
	
	
	
	
	
	//AC: slow 3%
	Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
	
    // Add the number to an internal data structure.
	public void addA(int number) {
		if (hm.containsKey(number)) {
			hm.put(number, 2);   //Don't need to same two many duplicate values, only two is enough
		} else {
			hm.put(number, 1);
		}
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean findA(int value) {
	    Set<Integer> setKeys = hm.keySet();
	    
	    if (setKeys == null || setKeys.isEmpty()) return false;
	    
	    for (Integer nKey:setKeys) {
	    	int nOther = value - nKey;
	    	if (nOther == nKey && hm.get(nKey) > 1) return true;
	    	
	    	if (hm.containsKey(nOther) && nOther != nKey) return true;
	    }
	    
	    return false;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*	
	
	//Exceeded time limit
	Set<Integer> setRawNumbers = new HashSet<Integer>();
	Set<Integer> setSums = new HashSet<Integer>();	
	
    // Add the number to an internal data structure.
	public void addB(int number) {
	    if (setRawNumbers.contains(number)) return;
	    int nSum = 0;
	    
	    for (Integer num:setRawNumbers) {
	        nSum = num + number;
	        if (!setSums.contains(nSum)) setSums.add(nSum);
	    }
	    
	    setRawNumbers.add(number);
	}

	
    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean findB(int value) {
        return setSums.contains(value);
	}
	
	
	
	
	//Naive solution, Exceed time limit	
	Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
		
    // Add the number to an internal data structure.
	public void addA(int number) {
	    hm.put(number, hm.containsKey(number) ? hm.get(number)+1: 1);
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean findA(int value) {
	    Set<Integer> setKeys = hm.keySet();
	    
	    if (setKeys == null || setKeys.isEmpty()) return false;
	    
	    for (Integer nKey:setKeys) {
	    	int nOther = value - nKey;
	    	if (nOther == nKey) {
	    		if (hm.get(nKey) > 1) return true;
	    		return false;
	    	}
	    	
	    	if (hm.containsKey(value - nKey)) return true;
	    }
	    
	    return false;
	}
	
*/	
	// Your TwoSum object will be instantiated and called as such:
	// TwoSum twoSum = new TwoSum();
	// twoSum.add(number);
	// twoSum.find(value);
}
