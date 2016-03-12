package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given a collection of integers that might contain duplicates, nums, return all possible subsets.
//
//Note:
//Elements in a subset must be in non-descending order.
//The solution set must not contain duplicate subsets.
//For example,
//If nums = [1,2,2], a solution is:
//
//[
//  [2],
//  [1],
//  [1,2,2],
//  [2,2],
//  [1,2],
//  []
//]
		
public class SubsetsII {

	public SubsetsII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] nums = {1,2,2,2,3,3,4};
				
		List<List<Integer>> lstlstSubsets = subsetsWithDup(nums);
		for (List<Integer> lstSubset:lstlstSubsets) {
			for (Integer nVal:lstSubset) {
				System.out.print(nVal+",");
			}
			System.out.println();
		}
	}
	

	//Idea: Process numbers one by one  (refer to last solution on http://www.cnblogs.com/TenosDoIt/p/3451902.html)
    public List<List<Integer>> subsetsWithDup(int[] nums) {
    	List<List<Integer>> lstlstSubsets = new ArrayList<List<Integer>>();
    	if (nums == null || nums.length == 0) return lstlstSubsets;
    	int n=nums.length;
    	int i,j;
    	
    	Arrays.sort(nums);
    	
    	List<Integer> lstTmp = new ArrayList<Integer>();
    	lstlstSubsets.add(lstTmp);
    	
    	int nLastNum = nums[0];
    	int nProcessSubsetCnt = 1;
    	int nTotalLen;
    	
    	for (i=0; i<n; i++) {
    		if (nums[i] != nLastNum) {   //For a new number, it could be added into all existing subsets (including empty subset) to form new distinct subsets;
    			                         //If the number has been processed, only process last-time processed subsets
    			nLastNum = nums[i];
    			nProcessSubsetCnt = lstlstSubsets.size();  
    		}
    		
    		nTotalLen = lstlstSubsets.size();
    		for (j=nTotalLen-1; j>= nTotalLen-nProcessSubsetCnt; j--) {   //Need to process nProcessSubsetCnt existing subset
    			List<Integer> lstTmpTmp = new ArrayList<Integer>(lstlstSubsets.get(j));   //The get method *Return a reference*, NOT a value
    			lstTmpTmp.add(nums[i]);
    			lstlstSubsets.add(lstTmpTmp);
    		}
    	}

    	return lstlstSubsets;
    }
	
	/* Works, not efficient enough
    public List<List<Integer>> subsetsWithDup(int[] nums) {
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
    		for (List<Integer> lstSubsetTmp:lstlstSubsetsTmp) {
    			lstlstSubsets.add(lstSubsetTmp);
    		}
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
    			if (!lstlstSubsets.contains(lstTmp)) lstlstSubsets.add(lstTmp);
    		}
    		
    		return lstlstSubsets;
    	}
    	
    	for (i=nStartIdx; i<=n-nSubsetSize; i++) {
    		List<List<Integer>> lstlstSubsetsTmp = subsets(nums, i+1, nSubsetSize-1);
    		for (List<Integer> lstTmpTmp:lstlstSubsetsTmp) {
    			lstTmpTmp.add(0, nums[i]);
    			if (!lstlstSubsets.contains(lstTmpTmp)) lstlstSubsets.add(lstTmpTmp);
    		}
    	}
    	
    	return lstlstSubsets;
    }	
    
    */
    
}
