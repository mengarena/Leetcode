package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
//
//For example, given the following triangle
//[
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
//The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
//
//Note:
//Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.

public class Triangle {

	public Triangle() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		List<Integer> lstTmp = new ArrayList<Integer>();
		List<List<Integer>> lstlstTriangle = new ArrayList<List<Integer>>();
		int nMinSum = 0;
		
		lstTmp.add(2);
		lstlstTriangle.add(lstTmp);
		
		lstTmp = new ArrayList<Integer>();
		lstTmp.add(3); lstTmp.add(4);
		lstlstTriangle.add(lstTmp);
		
		lstTmp = new ArrayList<Integer>();
		lstTmp.add(6); lstTmp.add(5); lstTmp.add(7);
		lstlstTriangle.add(lstTmp);

		lstTmp = new ArrayList<Integer>();
		lstTmp.add(4); lstTmp.add(1); lstTmp.add(8); lstTmp.add(3);
		lstlstTriangle.add(lstTmp);

/*		
		lstTmp.add(-1);
		lstlstTriangle.add(lstTmp);
		
		lstTmp = new ArrayList<Integer>();
		lstTmp.add(3); lstTmp.add(2);
		lstlstTriangle.add(lstTmp);
		
		lstTmp = new ArrayList<Integer>();
		lstTmp.add(-3); lstTmp.add(1); lstTmp.add(-1);
		lstlstTriangle.add(lstTmp);
*/
		
		nMinSum = minimumTotal(lstlstTriangle);
		
		System.out.println("The min Sum = " + nMinSum);
	}
	
	
	public int minimumTotal(List<List<Integer>> triangle) {
		int[] total = new int[triangle.size()];  //triangle.size() = #row and also the #column of last row
		int l = triangle.size() - 1;
	 
		for (int i = 0; i < triangle.get(l).size(); i++) {
			total[i] = triangle.get(l).get(i);
		}
	 
		// iterate from last second row
		for (int i = triangle.size() - 2; i >= 0; i--) {
			for (int j = 0; j < triangle.get(i + 1).size() - 1; j++) {  //#element in Row i
				total[j] = triangle.get(i).get(j) + Math.min(total[j], total[j + 1]);    //Current + the min of the adjacent element in the row below
			}
		}
	 
		return total[0];
	}
	
	
	
	
    public int minimumTotal_full(List<List<Integer>> triangle) {
        int nMinSum = 0;
        int nLevel = 0;
        int nIdx = 0;
        int nSum = 0;
        int nIdxIdx = 0;
        List<Integer> lstCurrentIdx = new ArrayList<Integer>();
        List<Integer> lstCurrentSum = new ArrayList<Integer>();
        
        lstCurrentIdx.add(0);
        lstCurrentSum.add(triangle.get(0).get(0));
        
        for (nLevel = 1; nLevel < triangle.size(); nLevel++) {
        	List<Integer> lstRow = triangle.get(nLevel);
        	
        	List<Integer> lstNewCurrentIdx = new ArrayList<Integer>();
        	List<Integer> lstNewCurrentSum = new ArrayList<Integer>();
        	
        	//For each index in previous row, only the indexes = previous index or previous index + 1 are considered adjacent
        	for (int i=0; i<lstCurrentIdx.size(); i++) {
        		nIdx = lstCurrentIdx.get(i);
        		nSum = lstCurrentSum.get(i);
        		        		        		
        		nIdxIdx = lstNewCurrentIdx.indexOf(nIdx); 
        		if (nIdxIdx == -1) {
        			lstNewCurrentIdx.add(nIdx);
        			lstNewCurrentSum.add(nSum + lstRow.get(nIdx));
        		} else {
        			if (nSum + lstRow.get(nIdx) < lstNewCurrentSum.get(nIdxIdx)) {
        				lstNewCurrentSum.set(nIdxIdx, nSum + lstRow.get(nIdx));
        			}
        		}
        		
    			nIdxIdx = lstNewCurrentIdx.indexOf(nIdx + 1); 
    			if (nIdxIdx == -1) {
    				lstNewCurrentIdx.add(nIdx + 1);
    				lstNewCurrentSum.add(nSum + lstRow.get(nIdx + 1));
    			} else {
    				if (nSum + lstRow.get(nIdx + 1) < lstNewCurrentSum.get(nIdxIdx)) {
    					lstNewCurrentSum.set(nIdxIdx, nSum + lstRow.get(nIdx + 1));
    				}
    			}
        		  		
        	}
        	
    		lstCurrentIdx = lstNewCurrentIdx;
    		lstCurrentSum = lstNewCurrentSum;

        }
        
        nMinSum = lstCurrentSum.get(0);
        
        for (int j=1; j<lstCurrentSum.size(); j++) {
        	if (nMinSum > lstCurrentSum.get(j)) nMinSum = lstCurrentSum.get(j);
        }
        
        return nMinSum;
    }

    
    
    
    
    
    public int minimumTotal_original(List<List<Integer>> triangle) {
        int nMinSum = 0;
        int nLevel = 0;
        int nIdx = 0;
        int nSum = 0;
        int nIdxIdx = 0;
        List<Integer> lstCurrentIdx = new ArrayList<Integer>();
        List<Integer> lstCurrentSum = new ArrayList<Integer>();
        
        lstCurrentIdx.add(0);
        lstCurrentSum.add(triangle.get(0).get(0));
        
        for (nLevel = 1; nLevel < triangle.size(); nLevel++) {
        	List<Integer> lstRow = triangle.get(nLevel);
        	
        	List<Integer> lstNewCurrentIdx = new ArrayList<Integer>();
        	List<Integer> lstNewCurrentSum = new ArrayList<Integer>();
        	
        	for (int i=0; i<lstCurrentIdx.size(); i++) {
        		nIdx = lstCurrentIdx.get(i);
        		nSum = lstCurrentSum.get(i);
        		        		
        		if (nIdx - 1 >= 0) {
        			nIdxIdx = lstNewCurrentIdx.indexOf(nIdx - 1);
        			if (nIdxIdx == -1) {
        				lstNewCurrentIdx.add(nIdx - 1);
        				lstNewCurrentSum.add(nSum + lstRow.get(nIdx -1));
        			} else {
        				if (nSum + lstRow.get(nIdx - 1) < lstNewCurrentSum.get(nIdxIdx)) {
        					lstNewCurrentSum.set(nIdxIdx, nSum + lstRow.get(nIdx - 1));
        				}
        			}
        			
        		}
        		
        		nIdxIdx = lstNewCurrentIdx.indexOf(nIdx); 
        		if (nIdxIdx == -1) {
        			lstNewCurrentIdx.add(nIdx);
        			lstNewCurrentSum.add(nSum + lstRow.get(nIdx));
        		} else {
        			if (nSum + lstRow.get(nIdx) < lstNewCurrentSum.get(nIdxIdx)) {
        				lstNewCurrentSum.set(nIdxIdx, nSum + lstRow.get(nIdx));
        			}
        		}
        		
        		if (nIdx + 1 <= nLevel) {
        			nIdxIdx = lstNewCurrentIdx.indexOf(nIdx + 1); 
        			if (nIdxIdx == -1) {
        				lstNewCurrentIdx.add(nIdx + 1);
        				lstNewCurrentSum.add(nSum + lstRow.get(nIdx + 1));
        			} else {
        				if (nSum + lstRow.get(nIdx + 1) < lstNewCurrentSum.get(nIdxIdx)) {
        					lstNewCurrentSum.set(nIdxIdx, nSum + lstRow.get(nIdx + 1));
        				}
        			}
        		}
        		
        	        		
        	}
        	
    		lstCurrentIdx = lstNewCurrentIdx;
    		lstCurrentSum = lstNewCurrentSum;

        }
        
        nMinSum = lstCurrentSum.get(0);
        
        for (int j=1; j<lstCurrentSum.size(); j++) {
        	if (nMinSum > lstCurrentSum.get(j)) nMinSum = lstCurrentSum.get(j);
        }
        
        return nMinSum;
    }
    
	
}
