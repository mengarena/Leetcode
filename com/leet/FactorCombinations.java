package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Numbers can be regarded as product of its factors. For example,
//
//8 = 2 x 2 x 2;
//  = 2 x 4.
//Write a function that takes an integer n and return all possible combinations of its factors.
//
//Note: 
//Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
//You may assume that n is always positive.
//Factors should be greater than 1 and less than n.
//
//Examples: 
//input: 1
//output: 
//[]
//		
//input: 37
//output: 
//[]
//		
//input: 12
//output:
//[
//  [2, 6],
//  [2, 2, 3],
//  [3, 4]
//]
//		
//input: 32
//output:
//[
//  [2, 16],
//  [2, 2, 8],
//  [2, 2, 2, 4],
//  [2, 2, 2, 2, 2],
//  [2, 4, 4],
//  [4, 8]
//]

//Linkedin
public class FactorCombinations {

	public FactorCombinations() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int n = 6;
		
		List<List<Integer>> lstlstFactors = getFactors(6);
		
		System.out.println();
	}
		
	
	//AC: 63%
	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> lstlstFactors = new ArrayList<List<Integer>>();
		if (n < 4)
			return lstlstFactors;

		lstlstFactors = getFactorsHelper(n, 2);

		return lstlstFactors;
	}

	//Strategy:  get 2-tuple
	//And then further divide the elements in 2-tuple, and then 3-tuple...
	//Factor sequences:  2, x,x,x,x,x
	//                   3, x,x,x,x,x
	//                   4, x,x,x,x,x
	//                  sqrt(n), x,x,x,x,x
	private List<List<Integer>> getFactorsHelper(int n, int min) {
		List<List<Integer>> lstlstFactors = new ArrayList<List<Integer>>();

		int max = (int) Math.sqrt(n);
		
		for (int i = min; i <= max; i++) {
			if (n % i != 0)
				continue;

			lstlstFactors.add(new ArrayList<>(Arrays.asList(i, n / i)));

			List<List<Integer>> lstlstFactorsSmaller = getFactorsHelper(n / i,  i);   //Further factor n/i

			for (List<Integer> lstFactorsSmaller : lstlstFactorsSmaller)  lstFactorsSmaller.add(0, i);   //To be the factors of original n, i should be added

			lstlstFactors.addAll(lstlstFactorsSmaller);   //If lstlstFactorsSmaller is empty list, lstlstFactors does not change
		}

		return lstlstFactors;
	}	
	
	
	

	//Accepted:  61%
	//First, get all possible factors
	//Then merge two factors round by round (total factors go from n to n-1, to n-2... to 2)
    public List<List<Integer>> getFactorsA(int n) {
        List<List<Integer>> lstlstFactors = new ArrayList<List<Integer>>();
        if (n < 4) return lstlstFactors;
        List<Integer> lstFact = getFactor(n);
        
        if (lstFact.isEmpty() || lstFact.size() == 1) {
        	return lstlstFactors;
        }
        
        lstlstFactors.add(lstFact);
        int nSize = lstFact.size();
        
        while (nSize > 2) {
        	List<List<Integer>> lstlstFactorsFixed = getFactorsHelper(lstlstFactors, nSize-1);
        	
        	for (List<Integer> lstFactorsFixed:lstlstFactorsFixed) lstlstFactors.add(lstFactorsFixed); 
        	
        	nSize = lstlstFactorsFixed.get(0).size();
        }
        
        
        return lstlstFactors;
    }	
    
    
    private List<List<Integer>> getFactorsHelper(List<List<Integer>> lstlstFactors, int nLen) {
    	List<List<Integer>> lstlstFactorsFixed = new ArrayList<List<Integer>>();
    	int nCnt = lstlstFactors.size();
    	
    	for (List<Integer> lstFactor:lstlstFactors) {
    		if (lstFactor.size() == nLen+1) {
    			List<List<Integer>> lstlstFactorsFixedSmaller = getFactorSmaller(lstFactor);
    			
    			for (List<Integer> lstFactorsFixedSmaller:lstlstFactorsFixedSmaller) {
    				if (!lstlstFactorsFixed.contains(lstFactorsFixedSmaller)) lstlstFactorsFixed.add(lstFactorsFixedSmaller);
    			}
    			
    		}
    	}
    	
    	return lstlstFactorsFixed;
    }
    
    
    private List<List<Integer>> getFactorSmaller(List<Integer> lstFactor) {
    	List<List<Integer>> lstlstFactorsFixedSmaller = new ArrayList<List<Integer>>();
    	int n = lstFactor.size();
    	int nPrev = -1;
    	int nPrevJ = -1;
    	int i = 0;
    	int j = 0;
    	
    	while (i< n) {
    		
    		nPrevJ = -1;
    		j = i+1;
    		while (j < n) {
    			List<Integer> lstFactorSmaller = new ArrayList<Integer>();
    			
    			lstFactorSmaller.add(lstFactor.get(i)*lstFactor.get(j));
    			
    			for (int k=0; k<lstFactor.size(); k++) {
    				if (k != i && k != j) lstFactorSmaller.add(lstFactor.get(k));
    			}
    			
    			Collections.sort(lstFactorSmaller);
    			if (!lstlstFactorsFixedSmaller.contains(lstFactorSmaller)) lstlstFactorsFixedSmaller.add(lstFactorSmaller);
    			
    			while (j+1 < n && lstFactor.get(j+1) == nPrevJ) j = j+1;
    			j++;
    			
    			if (j < n) nPrevJ = lstFactor.get(j);
    		}
    		
    		while (i+1 < n && lstFactor.get(i+1) == nPrev) i = i+1;
    		i++;
    		if (i < n) nPrev = lstFactor.get(i);
    	}
    	
    	return lstlstFactorsFixedSmaller;
    }
    
    
    private List<Integer> getFactor(int n) {
    	List<Integer> lstFact = new ArrayList<Integer>();
    	int nMax = (int) Math.sqrt(n);
    	int nOrg = n;
    	int nFactor = 2;
    	
    	while (nFactor <= nMax && n > nFactor) {
    		if (n % nFactor == 0) {
    			lstFact.add(nFactor);
    			n = n/nFactor;
    		} else {
    			nFactor++;
    		}
    	}
    	
    	if (nOrg != n) lstFact.add(n);
    	
    	return lstFact;
    }
   
}
