package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Given an array of integers, return indices of the two numbers such that they add up to a specific target.
//
//You may assume that each input would have exactly one solution.
//
//Example:
//Given nums = [2, 7, 11, 15], target = 9,
//
//Because nums[0] + nums[1] = 2 + 7 = 9,
//return [0, 1].
//		
//UPDATE (2016/2/13):
//The return format had been changed to zero-based indices. Please read the above updated description carefully.
	
//LinkedIn, Uber, Airbnb, Facebook, Amazon, Microsoft, Apple, Yahoo, Dropbox, Bloomberg, Yelp, Adobe
//Easy
public class TwoSum {

	public TwoSum() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int[] narrNums = {1, 2, 7, 11, 15, 8};
		int nTarget = 15;
		int[] narrIdx = twoSum(narrNums, nTarget);
		
		if (narrIdx[0] == 0) {
			System.out.println("Not found!");
		} else {
			System.out.println("Index[1] = " + narrIdx[0] + ", Index[2] = " + narrIdx[1]);
		}
	}
	
	//Accepted: Better O(n)
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return null;
        int n = nums.length;
        Map<Integer, Integer> mapNums = new HashMap<Integer, Integer>();  //Key, Position
        int[] narrIdx = new int[2];
        
        for (int i=0; i<n; i++) {
            if (mapNums.containsKey(target-nums[i])) {
                narrIdx[0] = mapNums.get(target-nums[i]);
                narrIdx[1] = i;
                break;
            }
            
            mapNums.put(nums[i], i);
        }
        
        return narrIdx;
    }
	
    
    //////////////////////////////
    //K-Sum
    int len = 0;
    public List<List<Integer>> fourSum(int[] nums, int target) {
        len = nums.length;
        Arrays.sort(nums);
        return kSum(nums, target, 4, 0);
    }
   private ArrayList<List<Integer>> kSum(int[] nums, int target, int k, int index) {
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if(index >= len) {
            return res;
        }
        if(k == 2) {
        	int i = index, j = len - 1;
        	while(i < j) {
        	    if(target - nums[i] == nums[j]) {
        	    	List<Integer> temp = new ArrayList<>();
                	temp.add(nums[i]);
                	temp.add(target-nums[i]);
                    res.add(temp);
                    while(i<j && nums[i]==nums[i+1]) i++;
                    while(i<j && nums[j-1]==nums[j]) j--;
                    i++;
                    j--;
        	    } else if (target - nums[i] > nums[j]) {
        	        i++;
        	    } else {
        	        j--;
        	    }
        	}
        } else{
            for (int i = index; i < len - k + 1; i++) {
                ArrayList<List<Integer>> temp = kSum(nums, target - nums[i], k-1, i+1);
                if(temp != null){
                    for (List<Integer> t : temp) {
                        t.add(0, nums[i]);
                    }
                    res.addAll(temp);
                }
                while (i < len-1 && nums[i] == nums[i+1]) {
                    i++;
                }
            }
        }
        return res;
    }
    
    
    
    
    
    
/*	Accepted: O(n^2)
    public int[] twoSum(int[] nums, int target) {
        int i,j;
        int nCnt = nums.length;
        int nExpected = 0;
        int nFirstIdx = 0;
        int nSecondIdx = 0;
        int[] narrIdx = new int[2];
                
        for (i = 0; i< nCnt-1; i++) {
        	nExpected = target - nums[i];
        	
        	nSecondIdx = 0;
        	for (j = i+1; j < nCnt; j++) {
        		if (nums[j] == nExpected) {
        			nFirstIdx = i+1;
        			nSecondIdx = j+1;
        			break;
        		}
        	}
        	
        	if (nSecondIdx > 0) break;
        }
        
        narrIdx[0] = nFirstIdx;
        narrIdx[1] = nSecondIdx;
        
        return narrIdx;
    }
*/	
}
