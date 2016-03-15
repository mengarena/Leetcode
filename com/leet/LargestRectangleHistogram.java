package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, 
//find the area of largest rectangle in the histogram.
//
//Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
//
//The largest rectangle is shown in the shaded area, which has area = 10 unit.
//
//For example,
//Given heights = [2,1,5,6,2,3],
//return 10.
		
public class LargestRectangleHistogram {

	public LargestRectangleHistogram() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//int heights[] = {2,2,2,2,2};
		//int heights[] = {2,1,5,6,2,3};
		
		int heights[] = new int[30000];
		for (int i=0; i<30000; i++) heights[i] = 1;
		
		System.out.println(largestRectangleArea(heights));
	}

	
	//Accepted
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int n = heights.length;
        if (n == 1) return heights[0];
        int i;
        Stack<Integer> stkSmallerHeight = new Stack<Integer>();
        int nMaxArea = 0;
        int nCurVal = 0;        

        for (i=0; i<=n; i++) {
            int nRightSmallerIdx = i;
            
            if (i < n) {
                nCurVal = heights[i];
            } else {
                nCurVal = 0;
            }
            
            while (!stkSmallerHeight.isEmpty() && heights[stkSmallerHeight.peek()] > nCurVal) {
                int nHeight = heights[stkSmallerHeight.pop()];
                int nLeftSmallerIdx = 0;
                
                if (!stkSmallerHeight.isEmpty()) {
                    nLeftSmallerIdx = stkSmallerHeight.peek();
                } else {
                    nLeftSmallerIdx = -1;
                }
                
                nMaxArea = Math.max(nMaxArea, (nHeight * (nRightSmallerIdx-nLeftSmallerIdx-1)));
            }
            
            stkSmallerHeight.push(i);
        }
        
        return nMaxArea;
    }
	
	//Accepted
    public int largestRectangleAreaA(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int n = heights.length;
        if (n == 1) return heights[0];
        int i;
        Stack<Integer> stkSmallerHeight = new Stack<Integer>();
        int nMaxArea = 0;
        
        heights = Arrays.copyOf(heights, heights.length+1);  //Append a 0 at the end, which makes sure the following for loop could process all numbers
        
        for (i=0; i<=n; i++) {
            int nRightSmallerIdx = i;

            while (!stkSmallerHeight.isEmpty() && heights[stkSmallerHeight.peek()] > heights[i]) {
                int nHeight = heights[stkSmallerHeight.pop()];
                int nLeftSmallerIdx = 0;
                
                while (!stkSmallerHeight.isEmpty() && heights[stkSmallerHeight.peek()] == nHeight) stkSmallerHeight.pop();
                
                if (!stkSmallerHeight.isEmpty()) {
                    nLeftSmallerIdx = stkSmallerHeight.peek();
                } else {
                    nLeftSmallerIdx = -1;
                }
                
                nMaxArea = Math.max(nMaxArea, (nHeight * (nRightSmallerIdx-nLeftSmallerIdx-1)));
            }
            
            stkSmallerHeight.push(i);
        }
        
        return nMaxArea;
    }	
	
    
    
    
    
/*	
	//Works, but exceeded time limit
	//Find the minimal values and separate the array by the minimal values
    public int largestRectangleAreaA(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int n = heights.length;
        if (n == 1) return heights[0];
        int i;
        List<Integer> lstMinPos = new ArrayList<Integer>();
        int nMaxArea = 0;
        int nMin = Integer.MAX_VALUE;
        int nTmpArea = 0;
        int nMax = Integer.MIN_VALUE;
        
        for (i=0; i<n; i++) {
        	if (nMin > heights[i]) {
        		nMin = heights[i];
        		lstMinPos.clear();
        		lstMinPos.add(i);
        	} else if (nMin == heights[i]) {
        		lstMinPos.add(i);
        	}
        	
        	if (heights[i] > nMax) nMax = heights[i];
        }
        
        nMaxArea = nMin * n;
        
        if (nMax == nMin) return nMaxArea;
        
        int nStartPos = 0;
        
        for (Integer nEndPos:lstMinPos) {
        	nTmpArea = largestRectangleAreaHelper(heights, nStartPos, nEndPos-1, nMaxArea);
        	nMaxArea = Math.max(nTmpArea, nMaxArea);
        	nStartPos = nEndPos + 1;
        }
        
        nTmpArea = largestRectangleAreaHelper(heights, nStartPos, n-1, nMaxArea);
        nMaxArea = Math.max(nTmpArea, nMaxArea);
        
        return nMaxArea;
    }	
    
    
    private int largestRectangleAreaHelper(int[] heights, int nStartPos, int nEndPos, int nMaxArea) {
    	if (nStartPos > nEndPos) return 0;
    	if (nStartPos == nEndPos) return heights[nStartPos];
    	
    	int nMin = Integer.MAX_VALUE;
    	int nMax = Integer.MIN_VALUE;
    	
    	List<Integer> lstMinPos = new ArrayList<Integer>();
    	
    	for (int i = nStartPos; i<= nEndPos; i++) {
        	if (nMin > heights[i]) {
        		nMin = heights[i];
        		lstMinPos.clear();
        		lstMinPos.add(i);
        	} else if (nMin == heights[i]) {
        		lstMinPos.add(i);
        	}

        	if (heights[i] > nMax) nMax = heights[i];
    	}
    	
    	if (nMax * (nEndPos-nStartPos+1) <= nMaxArea) return 0;
    	
    	int nMyMaxArea = nMin*(nEndPos-nStartPos+1);
    	
    	nMyMaxArea = Math.max(nMyMaxArea, nMaxArea);
    	
        int nStart = nStartPos;
        int nTmpArea = 0;
        
        for (Integer nEnd:lstMinPos) {
        	nTmpArea = largestRectangleAreaHelper(heights, nStart, nEnd-1, nMyMaxArea);
        	nMyMaxArea = Math.max(nTmpArea, nMyMaxArea);
        	nStart = nEnd + 1;
        }
        
        nTmpArea = largestRectangleAreaHelper(heights, nStart, nEndPos, nMyMaxArea);
        nMyMaxArea = Math.max(nTmpArea, nMyMaxArea);
   	
        return nMyMaxArea;
    }
*/    
}
