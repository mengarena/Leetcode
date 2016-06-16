package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//Given an integer array of size n, find all elements that appear more than [ n/3 ] (lower bound) times. 
//The algorithm should run in linear time and in O(1) space.
//
//Hint:
//
//How many majority elements could it possibly have?
//Do you have a better hint? Suggest it!


//Zenefits
public class MajorityElementII {

	public MajorityElementII() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int[] nums = {3,2,3};
		
		List<Integer> lstMajors = majorityElement(nums);
		for (int nMajor:lstMajors) System.out.print(nMajor + ",");
	}
	
	
	//ACC: 43%
	//Boyer-Moore Majority Vote algorithm:
	//http://www.geeksforgeeks.org/majority-element/
    public List<Integer> majorityElement(int[] nums) {
    	List<Integer> lstMajors = new ArrayList<Integer>();
    	if (nums == null || nums.length == 0) return lstMajors;
    	int n = nums.length;
    	int i;
    	int nThreshold = n/3;
    	int candidate1 = 0, candidate2 = 1, count1 = 0, count2 = 0;
    	
    	//Find candidates
    	for (i=0; i<n; i++) {
    		if (nums[i] == candidate1) {
    			count1++;
    		} else if (nums[i] == candidate2) {
    		    count2++;	
            } else if (count1 == 0) {
            	candidate1 = nums[i];
            	count1 = 1;
            } else if (count2 == 0) {
            	candidate2 = nums[i];
            	count2 = 1;
            } else {
            	count1--;
            	count2--;
            }
    	}
    	
    	
    	count1 = 0; count2 = 0;
    	
    	//Confirm the majority
    	for (i=0; i<n; i++) {
    		if (nums[i] == candidate1) count1++;
    		if (nums[i] == candidate2) count2++;
    	}
    	
    	if (count1 > nThreshold) lstMajors.add(candidate1);
    	if (count2 > nThreshold) lstMajors.add(candidate2);

    	return lstMajors;
    }
    
    
    
    public List<Integer> majorityElementA(int[] nums) {
    	List<Integer> lstMajors = new ArrayList<Integer>();
    	if (nums == null || nums.length == 0) return lstMajors;
    	int n = nums.length;
    	int i;
    	int nThreshold = n/3;
    	int nCount = 0;
    	int nCurNum;
    	
    	Arrays.sort(nums);   //nlog(n), which is not linear
    	
    	nCurNum = nums[0];
    	
    	nCount++;
        
    	for (i=1; i<n; i++) {
    		if (nums[i] == nCurNum) {
    			nCount++;
    		} else {
    			
    			if (nCount > nThreshold) lstMajors.add(nCurNum);
    			
    			nCurNum = nums[i];
    			nCount = 1;
    		}
    	}
    	
    	if (nCount > nThreshold) lstMajors.add(nCurNum);
    	
    	return lstMajors;
    }

    
 /*   
    public List<Integer> majorityElement(int[] nums) {
    	List<Integer> lstMajors = new ArrayList<Integer>();
    	if (nums == null || nums.length == 0) return lstMajors;
    	int n = nums.length;
    	int i;
    	HashMap<Integer, Integer> hmCount = new HashMap<Integer, Integer>();
    	int nThreshold = n/3;
    	    	
    	for (i=0; i<n; i++) {
    		if (hmCount.containsKey(nums[i])) {
    			hmCount.put(nums[i],  hmCount.get(nums[i]) + 1);
    		} else {
    			hmCount.put(nums[i],  1);
    		}
    		
    		if (hmCount.get(nums[i]) > nThreshold && !lstMajors.contains(nums[i])) lstMajors.add(nums[i]);
    	}
    	
    	return lstMajors;
    }
*/    
    
}
