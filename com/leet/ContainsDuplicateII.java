package com.leet;

import java.util.HashMap;
import java.util.Map;

//Given an array of integers and an integer k, 
//find out whether there are two distinct indices i and j in the array 
//such that nums[i] = nums[j] and the difference between i and j is at most k.


//Airbnb
public class ContainsDuplicateII {

	public ContainsDuplicateII() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
	}
	
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> mapElement = new HashMap<Integer, Integer>();  //Value, Position
        
        if (nums == null || nums.length <= 1) return false; 
        
        mapElement.put(nums[0], 0);
        
        for (int i=1; i<nums.length; i++) {
        	if (mapElement.containsKey(nums[i])) {
        		int nPrevIdx = mapElement.get(nums[i]);
        		if (i-nPrevIdx <= k) {
        			return true;
        		} else {
        			mapElement.put(nums[i], i);  //Replace existing value
        		}
        	} else {
        		mapElement.put(nums[i], i);
        	}
        }
        
        return false;
    }
	
}
