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
		
	}
	
    public List<List<Integer>> generate(int numRows) {
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
