package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given an index k, return the kth row of the Pascal's triangle.
//
//For example, given k = 3,
//Return [1,3,3,1].
//
//Note:
//Could you optimize your algorithm to use only O(k) extra space?

//Amazon
public class PascalTriangleII {

	public PascalTriangleII() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int nRow = 4;
		List<Integer> lstRow = getRow(nRow);
		
		System.out.println("Row " + nRow + " Is:");
		
		for (Integer nVal:lstRow) {
			System.out.print(nVal + ",");
		}
		
		System.out.println("");
	}
	
    public List<Integer> getRow(int rowIndex) {
        List<Integer> lstRow = new ArrayList<Integer>();
        if (rowIndex < 0) return lstRow;

        lstRow.add(1);
        
        if (rowIndex >= 1) {
        	lstRow.add(1); 
        	
        	//Each row has one more elements than previous row
        	for (int i=2; i<=rowIndex; i++) {
        		lstRow.add(i-1, lstRow.get(i-1-1) + lstRow.get(i-1)); //Insert a new
        		
        		for (int j=i-2; j>=1; j--) {
        			lstRow.set(j, lstRow.get(j) + lstRow.get(j-1));   //Update old
        		}
        		
        	}
        }
        
        return lstRow;
    }
	
	
}
