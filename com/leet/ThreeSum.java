package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
//		Find all unique triplets in the array which gives the sum of zero.
//
//Note:
//Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ¡Ü b ¡Ü c)
//The solution set must not contain duplicate triplets.
//    For example, given array S = {-1 0 1 2 -1 -4},
//
//    A solution set is:
//    (-1, 0, 1)
//    (-1, -1, 2)


//Facebook, Bloomberg
public class ThreeSum {

	public ThreeSum() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] nums = {-1, 0, 1, 2, -1, -4};
		
		List<List<Integer>> lstlst3sums = threeSum(nums);
		
		for (List<Integer> lst3sum:lstlst3sums) {
			System.out.print("(");
			for (Integer num:lst3sum) System.out.print(num + ",");
			System.out.println(")");			
		}
		
	}
	
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lstlst3sums = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return lstlst3sums;
        int n = nums.length;
        int i = 0;
        
        Arrays.sort(nums);
        
        while (i < n-2) {
        	List<List<Integer>> lstlst2Sum = twoSum(nums, i+1, 0-nums[i]);
        	
        	if (lstlst2Sum.size() > 0) {
	        	for (List<Integer> lst2Sum:lstlst2Sum) {
	        		lst2Sum.add(0, nums[i]);
	        		lstlst3sums.add(lst2Sum);
	        	}
	        	
	        	while (i < n-2 && nums[i] == nums[i+1]) i++;
	        	i++;
        	} else {
        		i++;
        	}
        }
        
        
        return lstlst3sums;
    }
	
    
    private List<List<Integer>> twoSum(int[] nums, int nStartPos, int nSum) {
    	int n = nums.length;
    	int nLeft = nStartPos;
    	int nRight = n-1;
    	List<List<Integer>> lstlst2Sum = new ArrayList<List<Integer>>();
    	
    	while (nLeft < nRight) {
    		if (nums[nLeft] + nums[nRight] == nSum) {
    			List<Integer> lst2Sum = new ArrayList<Integer>();
    			lst2Sum.add(nums[nLeft]);
    			lst2Sum.add(nums[nRight]);
    			lstlst2Sum.add(lst2Sum);
    			
    			while (nLeft < n-1 && nums[nLeft] == nums[nLeft+1]) nLeft++;
    			nLeft++;
    			while (nRight > 0 && nums[nRight] == nums[nRight-1]) nRight--;
    			nRight--;
    		} else if (nums[nLeft] + nums[nRight] < nSum) {
    			while (nLeft < n-1 && nums[nLeft] == nums[nLeft+1]) nLeft++;
    			nLeft++;
    		} else {
    			while (nRight > 0 && nums[nRight] == nums[nRight-1]) nRight--;
    			nRight--;
    		}
    	}
    	
    	return lstlst2Sum;
    }
    
}
