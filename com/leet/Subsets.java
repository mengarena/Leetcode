package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given a set of distinct integers, nums, return all possible subsets.
//
//Note:
//Elements in a subset must be in non-descending order.
//The solution set must not contain duplicate subsets.
//For example,
//If nums = [1,2,3], a solution is:
//
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//]

public class Subsets {

	public Subsets() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] nums = {4, 2,1,3};
				
		List<List<Integer>> lstlstSubsets = subsets(nums);
		for (List<Integer> lstSubset:lstlstSubsets) {
			for (Integer nVal:lstSubset) {
				System.out.print(nVal+",");
			}
			System.out.println();
		}
	}
	
	
    public List<List<Integer>> subsets(int[] nums) {
    	List<List<Integer>> lstlstSubsets = new ArrayList<List<Integer>>();
    	if (nums == null || nums.length == 0) return lstlstSubsets;
    	int n=nums.length;
    	int i;
    	
    	Arrays.sort(nums);
    	
    	//Subset size = 0;
    	List<Integer> lstTmp = new ArrayList<Integer>();
    	lstlstSubsets.add(lstTmp);
    	
    	for (i=1; i<=n-1; i++) {  //Subset size
    		List<List<Integer>> lstlstSubsetsTmp = subsets(nums, 0, i);
    		for (List<Integer> lstSubsetTmp:lstlstSubsetsTmp) lstlstSubsets.add(lstSubsetTmp);
    	}

    	//Subset size = n;
    	lstTmp = new ArrayList<Integer>();
    	for (i=0; i<n; i++) lstTmp.add(nums[i]);
    	lstlstSubsets.add(lstTmp);
    	
    	return lstlstSubsets;
    }
    
    
    public List<List<Integer>> subsets(int[] nums, int nStartIdx, int nSubsetSize) {
    	int n = nums.length;
    	int i;
    	List<List<Integer>> lstlstSubsets = new ArrayList<List<Integer>>();
    	List<Integer> lstTmp = new ArrayList<Integer>();
    	
    	if (nSubsetSize == 1) {
    		for (i=nStartIdx; i<=n-nSubsetSize; i++) {
    			lstTmp = new ArrayList<Integer>();
    			lstTmp.add(nums[i]);
    			lstlstSubsets.add(lstTmp);
    		}
    		
    		return lstlstSubsets;
    	}
    	
    	for (i=nStartIdx; i<=n-nSubsetSize; i++) {
    		List<List<Integer>> lstlstSubsetsTmp = subsets(nums, i+1, nSubsetSize-1);
    		for (List<Integer> lstTmpTmp:lstlstSubsetsTmp) {
    			lstTmpTmp.add(0, nums[i]);
    			lstlstSubsets.add(lstTmpTmp);
    		}
    	}
    	
    	return lstlstSubsets;
    }
}
