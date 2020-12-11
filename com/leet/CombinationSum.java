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
	
	
    //Time complexity: O(n!)
    //n is number of elements in candidates
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lstlstCombSum = new ArrayList<List<Integer>>();
        if (candidates == null || candidates.length == 0) return lstlstCombSum;

        Arrays.sort(candidates);

        combinationSumHelper(lstlstCombSum, new ArrayList<Integer>(), candidates, 0, target);
        return lstlstCombSum;
    }
    
    private void combinationSumHelper(List<List<Integer>> lstlstCombSum, List<Integer> lstComb, int[] candidates, int startIdx, int target) {
        if (target < 0) return;
        if (target == 0) {
            lstlstCombSum.add(new ArrayList<Integer>(lstComb));
            return;
        }
        
        for (int i=startIdx; i<candidates.length; i++) {
            lstComb.add(candidates[i]);
            
            combinationSumHelper(lstlstCombSum, lstComb, candidates, i, target-candidates[i]);
            
            lstComb.remove(lstComb.size()-1);
        }
        
    }	
	
	
	
	
	
	
	
    public List<List<Integer>> combinationSumA(int[] candidates, int target) {
    	List<List<Integer>> lstlstCombSum = null; 
    	if (candidates == null || candidates.length == 0) return lstlstCombSum;
    	int n = candidates.length;
    	
    	Arrays.sort(candidates);
    	
    	lstlstCombSum = combinationSumA(candidates, n, 0, target);
    	    	
    	if (lstlstCombSum == null) lstlstCombSum = new ArrayList<List<Integer>>();
    	
    	return lstlstCombSum;
    }
	
    
    public List<List<Integer>> combinationSumA(int[] candidates, int n, int nStartIdx, int target) {
    	List<List<Integer>> lstlstCombSum = new ArrayList<List<Integer>>();
    	int i;
    	
    	if (target == 0) return lstlstCombSum;
    	if (target < 0) return null;
    	    	
    	for (i=nStartIdx; i<n; i++) {
    		List<List<Integer>> lstlstCombSumTmp = combinationSumA(candidates, n, i, target-candidates[i]);  //Could use duplicate number, so still start from i, NOT i+1
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
