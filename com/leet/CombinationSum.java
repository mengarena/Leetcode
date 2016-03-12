package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
//
//The same repeated number may be chosen from C unlimited number of times.
//
//Note:
//All numbers (including target) will be positive integers.
//Elements in a combination (a1, a2, ¡­ , ak) must be in non-descending order. (ie, a1 ¡Ü a2 ¡Ü ¡­ ¡Ü ak).
//The solution set must not contain duplicate combinations.
//For example, given candidate set 2,3,6,7 and target 7, 
//A solution set is: 
//[7] 
//[2, 2, 3] 
		
public class CombinationSum {

	public CombinationSum() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] candidates = {2};
		int target = 1;
		
		List<List<Integer>> lstlstCombSum = combinationSum(candidates, target);
		
		for (List<Integer> lstCombSum:lstlstCombSum) {
			for (Integer nVal:lstCombSum) {
				System.out.print(nVal+",");
			}
			System.out.println();
		}

	}
	
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	List<List<Integer>> lstlstCombSum = null; 
    	if (candidates == null || candidates.length == 0) return lstlstCombSum;
    	int n = candidates.length;
    	
    	Arrays.sort(candidates);
    	
    	lstlstCombSum = combinationSum(candidates, n, 0, target);
    	    	
    	if (lstlstCombSum == null) lstlstCombSum = new ArrayList<List<Integer>>();
    	
    	return lstlstCombSum;
    }
	
    
    public List<List<Integer>> combinationSum(int[] candidates, int n, int nStartIdx, int target) {
    	List<List<Integer>> lstlstCombSum = new ArrayList<List<Integer>>();
    	int i;
    	
    	if (target == 0) return lstlstCombSum;
    	if (target < 0) return null;
    	    	
    	for (i=nStartIdx; i<n; i++) {
    		List<List<Integer>> lstlstCombSumTmp = combinationSum(candidates, n, i, target-candidates[i]);
    		if (lstlstCombSumTmp != null) {
	    		if (lstlstCombSumTmp.size() > 0) {
	    			for (List<Integer> lstCombSumTmp:lstlstCombSumTmp) {
	    				lstCombSumTmp.add(0, candidates[i]);
	    				lstlstCombSum.add(lstCombSumTmp);
	    			}
	    		} else {
	    			List<Integer> lstCombSumTmp = new ArrayList<Integer>();
    				lstCombSumTmp.add(0, candidates[i]);
    				lstlstCombSum.add(lstCombSumTmp);
	    		}
    		} 
    	}
    	
    	if (lstlstCombSum.size() == 0) return null;
    	
    	return lstlstCombSum;
    }
    
}
