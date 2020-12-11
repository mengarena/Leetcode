package com.leet;

import java.util.ArrayList;
import java.util.List;

//Find all possible combinations of k numbers that add up to a number n, 
//given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
//
//Ensure that numbers within the set are sorted in ascending order.
//
//Example 1:
//Input: k = 3, n = 7
//Output:
//[[1,2,4]]
//
//Example 2:
//Input: k = 3, n = 9
//Output:
//[[1,2,6], [1,3,5], [2,3,4]]
		
public class CombinationSumIII {

	public CombinationSumIII() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int k = 2;
		int n = 18;
		
		List<List<Integer>> lstlstCombSum = combinationSum3(k, n);
		
		for (List<Integer> lstOneComSum:lstlstCombSum) {
			for (Integer nVal:lstOneComSum) {
				System.out.print(nVal + ",");
			}
			System.out.println();
		}
	}

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (k <= 0 || n <= 0 || k > n) return ret;
        
        combinationSum3Helper(ret, new ArrayList<Integer>(), 1, k, n);
        
        return ret;
    }
    
    private void combinationSum3Helper(List<List<Integer>> ret, List<Integer> lstComb, int startVal, int count, int remained) {
        if (remained < 0 || lstComb.size() > count) return;
        if (remained == 0 && lstComb.size() == count) {
            ret.add(new ArrayList<Integer>(lstComb));
            return;
        } 
        
        for (int i=startVal; i<=9; i++) {
            lstComb.add(i);
            combinationSum3Helper(ret, lstComb, i+1, count, remained-i);
            lstComb.remove(lstComb.size()-1);
        }
    }	
	
	
	
	
	///////////////
    public List<List<Integer>> combinationSum3A(int k, int n) {
    	List<List<Integer>> lstlstCombSum = new ArrayList<List<Integer>>();
    	
    	lstlstCombSum = getCandidateA(1, k, n);
    	
    	return lstlstCombSum;
    }
	
    
    public List<List<Integer>> getCandidateA(int nStartVal, int k, int n) {   // K #remained,  remained sum n
    	//Decide possible value for first value
    	int nMin; 
    	int nMax; 
    	int i;
    	List<List<Integer>> lstlstCombSum = new ArrayList<List<Integer>>();
        List<Integer> lstResult = new ArrayList<Integer>();
    	
    	if (nStartVal > n || k == 0) return lstlstCombSum;
    	
    	if (k == 1) {
    		if (n > 9) return lstlstCombSum;
    		lstResult.add(n);
    		lstlstCombSum.add(lstResult);
    		return lstlstCombSum;
    	}
    	
    	nMin = nStartVal;
    	nMax = (int) (n - (k-1)*k/2)/k;   //Max value occurs when the remaining numbers are: x (the maxvalue), x+1, x+2 ... x+k-1  (the sum should be n)
    	
    	for (i = nMin; i <= nMax; i++) {  //Possible value of the first
    		List<List<Integer>> lstlstCombSumTmp = new ArrayList<List<Integer>>();
    		lstlstCombSumTmp = getCandidateA(i+1, k-1, n-i);
    		
    		if (lstlstCombSumTmp.isEmpty()) continue;
    		
    		for (List<Integer> lstOneCombSum:lstlstCombSumTmp) {
    			lstOneCombSum.add(0, i);
    			lstlstCombSum.add(lstOneCombSum);
    		}
    		
    	}
    	
    	return lstlstCombSum;
    }
    
}
