package com.leet;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

//Given an array of integers, find if the array contains any duplicates. 
//Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.


//Airbnb
public class ContainsDuplicate {

	public ContainsDuplicate() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
	}
	
    public boolean containsDuplicate(int[] nums) {
        Hashtable ht = new Hashtable();
        
        if (nums == null || nums.length == 0) return false;
        
        ht.put(nums[0], nums[0]);
                
        for (int i=1; i<nums.length; i++) {
        	if (ht.containsKey(nums[i])) return true;
        	
        	ht.put(nums[i], nums[i]);
        }
        
        return false;
    }

    
    //Works, but less efficient than the above one
    public boolean backup_containsDuplicate(int[] nums) {
        boolean bRet = false;
        
        List<Integer> lstDistinctNum = new ArrayList<Integer>();
        
        if (nums == null) return false;
        lstDistinctNum.add(nums[0]);
        
        for (int i=1; i<nums.length; i++) {
        	if (lstDistinctNum.indexOf(nums[i]) != -1) {
        		return true;
        	} else {
        		lstDistinctNum.add(nums[i]);
        	}
        }
        
        return bRet;
    }
   
    
}
