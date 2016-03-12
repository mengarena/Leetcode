package com.leet;

//Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
//n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
//Find two lines, which together with x-axis forms a container, such that the container contains the most water.
//
//Note: You may not slant the container.

public class ContainerMostWater {

	public ContainerMostWater() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] height = {1,2,4,3};
		
		System.out.println("Max Area = " + maxArea(height));
	}
	
	//Initially, left is at 0, right is at the end; and then try to move the two boards towards the middle (until i >= j)
	//For the water between two boards, it is only decided by the two border boards (actually the lower one of the two border boards), the boards in-between does not matter (because only two vertical lines will exist)
	//When moving, try to move the left board to right;  try to move the right board to left
	//When moving left board to right, if meets a board lower than the current left, then for sure, the right board and the candidate left board will contain less water, so could ignore it;
	//If the new left board is higher than old left board, it might form a larger container with the right board, so it compare with the original container
    public int maxArea(int[] height) {
        if (height == null || height.length == 0 || height.length == 1) return 0;
        int i=0, j=height.length - 1;
        int nLeftHeight = height[0], nRightHeight = height[height.length - 1];
        int nMaxArea = 0;
        int nTmpArea = 0;
        
        while (i<j) {
        	nTmpArea = (int)(Math.min(nLeftHeight, nRightHeight))*(j-i);
        	
        	if (nTmpArea > nMaxArea) nMaxArea = nTmpArea;
        	
        	if (nLeftHeight < nRightHeight) {
        		while (height[i] <= nLeftHeight && i < j) i++;
        		
        		if (i < j) nLeftHeight = height[i];
        	} else {
        		while (height[j] <= nRightHeight && i < j) j--;
        		
        		if (i < j) nRightHeight = height[j];
        	}
        }
                
        return nMaxArea;
    }
	
}
