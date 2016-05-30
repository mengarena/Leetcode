package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given numRows, generate the first numRows of Pascal's triangle.
//
//For example, given numRows = 5,
//Return
//
//[
//     [1],
//    [1,1],
//   [1,2,1],
//  [1,3,3,1],
// [1,4,6,4,1]
//]

public class PascalTriangle {

	public PascalTriangle() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		List<List<Integer>> lstlstTri = generate(4);
		
		System.out.println("");
	}
	
	
	
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lstlstTri = new ArrayList<List<Integer>>();
        if (numRows <= 0) return lstlstTri;
        List<Integer> lstLevel = new ArrayList<Integer>();
        lstLevel.add(1);
        lstlstTri.add(lstLevel);
        
        if (numRows < 2) return lstlstTri;
        
        List<Integer> lstLevelPrev = new ArrayList<Integer>();
        lstLevelPrev.add(1);
        lstLevelPrev.add(1);
        lstlstTri.add(lstLevelPrev);
        
        for (int i=3; i<=numRows; i++) {
            List<Integer> lstLevelCur = new ArrayList<Integer>();
            lstLevelCur.add(1);
            for (int j=1; j<=lstLevelPrev.size()-1; j++) {
                lstLevelCur.add(lstLevelPrev.get(j-1) + lstLevelPrev.get(j));
            }
            lstLevelCur.add(1);
            
            lstlstTri.add(lstLevelCur);
            
            lstLevelPrev = lstLevelCur; //new ArrayList<Integer>(lstLevelCur);  (both works)
        }
        
        return lstlstTri;
        
    }
	
	
	
	//ACC
    public List<List<Integer>> generateA(int numRows) {
    	List<List<Integer>> lstlstTriangle = new ArrayList<List<Integer>>();
    	
    	if (numRows <= 0) return lstlstTriangle;
    	if (numRows >= 1) {
    		List<Integer> lstRow = new ArrayList<Integer>();
    		lstRow.add(1);
    		lstlstTriangle.add(lstRow);
    	}
    	
    	if (numRows >= 2) {
    		List<Integer> lstRow = new ArrayList<Integer>();
    		lstRow.add(1); lstRow.add(1);
    		lstlstTriangle.add(lstRow);
    	}
    	
    	for (int i=3; i<=numRows; i++) {
    		List<Integer> lstRow = new ArrayList<Integer>();
    		lstRow.add(1);
    		for (int j=1; j<=i-2; j++) {
    			lstRow.add(lstlstTriangle.get(i-2).get(j-1) + lstlstTriangle.get(i-2).get(j));
    		}
    		lstRow.add(1);
    		lstlstTriangle.add(lstRow);
    	}
    	
    	return lstlstTriangle;
    }
    
    
	
}
