package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
//
//Each number in C may only be used once in the combination.
//
//Note:
//All numbers (including target) will be positive integers.
//Elements in a combination (a1, a2, ¡­ , ak) must be in non-descending order. (ie, a1 ¡Ü a2 ¡Ü ¡­ ¡Ü ak).
//The solution set must not contain duplicate combinations.
//For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
//A solution set is: 
//[1, 7] 
//[1, 2, 5] 
//[2, 6] 
//[1, 1, 6] 

public class CombinationSumII {

	public CombinationSumII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] candidates = {10,1,2,7,6,1,5};
		int target = 8;
		
		List<List<Integer>> lstlstCombSum = combinationSum2(candidates, target);
		
		for (List<Integer> lstCombSum:lstlstCombSum) {
			System.out.print("[");
			for (Integer nVal:lstCombSum) {
				System.out.print(nVal+",");
			}
			System.out.println("]");
		}
		
	}
	
		
	
	//Get combinations
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (candidates == null || candidates.length == 0) return ret;
        
        Arrays.sort(candidates);
        
        combinationSum2Helper(ret, new ArrayList<Integer>(), candidates, 0, target);
        
        return ret;
    }
    
    private void combinationSum2Helper(List<List<Integer>> ret, List<Integer> lstComb, int[] candidates, int startIdx, int target) {
        if (target < 0) return;
        if (target == 0) {
            ret.add(new ArrayList<Integer>(lstComb));
            return;
        }
        
        for (int i=startIdx; i<candidates.length; i++) {
            if (i > startIdx && candidates[i] == candidates[i-1]) continue;
            lstComb.add(candidates[i]);
            combinationSum2Helper(ret, lstComb, candidates, i+1, target-candidates[i]);
            lstComb.remove(lstComb.size()-1);
        }
    }	
	
	
	
	
	
    public List<List<Integer>> combinationSum2A(int[] candidates, int target) {
    	List<List<Integer>> lstlstCombSum = new ArrayList<List<Integer>>();
    	if (candidates == null || candidates.length == 0) return lstlstCombSum;
    	
    	Arrays.sort(candidates);
    	    	
    	lstlstCombSum = combinationSum2HelperA(candidates, 0, target);
    	
    	if (lstlstCombSum == null) {
    		lstlstCombSum = new ArrayList<List<Integer>>();
    	}
    	
    	return lstlstCombSum;
    }

    
    public List<List<Integer>> combinationSum2HelperA(int[] candidates, int nStartPos, int target) {
    	List<List<Integer>> lstlstCombSum = null; 
    	int n = candidates.length;
    	int i;
     	
    	if (target == 0) {
    		lstlstCombSum = new ArrayList<List<Integer>>();
    		return lstlstCombSum;
    	}
    	if (target < 0) return null;
    	    	
    	for (i=nStartPos; i<n;) {
    		List<List<Integer>> lstlstCombSumTmp = combinationSum2HelperA(candidates, i+1, target-candidates[i]);    //Not allow duplicate, so next start from i+1
    		if (lstlstCombSumTmp != null) {
    			if (lstlstCombSum == null) lstlstCombSum = new ArrayList<List<Integer>>();
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
    		
    		i++;
    		
    		//If the same value has been at this position of a combination, other same value should NOT be at this position again.
    		while (i<n && candidates[i] == candidates[i-1]) i++;
    	}
    	    	
    	return lstlstCombSum;
    }
    
}
