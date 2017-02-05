package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
//
//For example,
//If n = 4 and k = 2, a solution is:
//
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//]

public class Combinations {

	public Combinations() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int n = 4;
		int k = 3;
		
		List<List<Integer>> lstlstComb = combine(n, k);
		
		for (List<Integer> lstComb:lstlstComb) {
			for (Integer nVal:lstComb) {
				System.out.print(nVal + ",");
			}
			System.out.println();
		}
		
	}
	
	
	//combine(n, k) = combine(n-1, k-1) + combine(n-1, k)
    //Basically, this solution follows the idea of the mathematical formula C(n,k)=C(n-1,k-1)+C(n-1,k).
    //Here C(n,k) is divided into two situations. 
    //Situation one, number n is selected, so we only need to select k-1 from n-1 next. 
    //Situation two, number n is not selected, and the rest job is selecting k from n-1.
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> lstlstComb = new ArrayList<List<Integer>>();
        if (k < 0 || k > n) return lstlstComb;
        
        if (k == 0) {
            lstlstComb.add(new ArrayList<Integer>());
            return lstlstComb;
        }
        
        lstlstComb = combine(n-1, k-1);
        for (List<Integer> lstComb:lstlstComb) {
            lstComb.add(n);
        }

        lstlstComb.addAll(combine(n-1, k));
        
        return lstlstComb;
    }	
	
	
	//Time complexity: O(C(n, k))
    public List<List<Integer>> combineA(int n, int k) {  //k numbers; 1~n
    	List<List<Integer>> lstlstComb = new ArrayList<List<Integer>>();
    	
    	if (k > n) return lstlstComb;
    
        lstlstComb = combineHelper(n, 1, k);
        
        return lstlstComb;
    }
    
    
    public List<List<Integer>> combineHelper(int n, int nStartPos, int k) {
        List<List<Integer>> lstlstComb = new ArrayList<List<Integer>>();
        
        if (k <= 0) return lstlstComb;
        
        for (int i=nStartPos; i<=n-k+1; i++) {   //Possible value for first element of the resultant k numbers
            List<List<Integer>> lstlstCombTmp = combineHelper(n, i+1, k-1);
            
            if (!lstlstCombTmp.isEmpty()) {
                for (List<Integer> lstCombTmp:lstlstCombTmp) {
                    lstCombTmp.add(0, i);
                    lstlstComb.add(lstCombTmp);
                }
            } else {
                List<Integer> lstCombTmp = new ArrayList<Integer>();
                lstCombTmp.add(i);
                lstlstComb.add(lstCombTmp);
            }
        }
        
        return lstlstComb;
        
    }
	
}
