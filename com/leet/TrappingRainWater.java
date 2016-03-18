package com.leet;

//Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
//
//For example, 
//Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
//
//
//The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. 
//In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

public class TrappingRainWater {

	public TrappingRainWater() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
//		int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
		int[] height = {2,1,1};
//		int[] height = {1,0,8};
//		int[] height = {5,4,1,2};
//		int[] height = {5,2,1,2,1,5};
		
		System.out.println("Trapped Rain = " + trap(height));
		
	}

	
    public int trap(int[] height) {
        if (height == null || height.length <= 2) return 0;
        int n = height.length;
        int i;
        int nTrappedRain = 0;
        int nLeftPos=0, nRightPos=0;
        int nBarHeight;
        
        while (nLeftPos < n-2) {
        	
        	while (nLeftPos < n-2 && height[nLeftPos] <= height[nLeftPos+1]) {
        		nLeftPos++;
        	}
        	
        	if (nLeftPos >= n-2) break;
        	
        	nRightPos = findNextPeak(height, nLeftPos+1, height[nLeftPos]);
        	
        	if (nRightPos == -1) break;
        	
        	nBarHeight = Math.min(height[nLeftPos], height[nRightPos]);
        	for (i=nLeftPos+1; i<nRightPos; i++) {
        		if (nBarHeight - height[i] > 0) nTrappedRain = nTrappedRain + nBarHeight - height[i];
        	}
        	
        	nLeftPos = nRightPos;
        }
        
        return nTrappedRain;
    }
	
	
    private int findNextPeak(int[] height, int nStartIdx, int nLeftHeight) {
    	int nRightPos = -1;
    	int n = height.length;
    	int i;
    	int nMaxHeight = -1;
    	
    	for (i=nStartIdx; i<n; i++) {
    		if (height[i] >= nLeftHeight) {
    			nRightPos = i;
    			break;
    		}
    		
    		if (height[i] > nMaxHeight) {
    			nMaxHeight = height[i];
    			nRightPos = i;
    		}
    	}
    	
    	return nRightPos;
    }
    
/*	
	//Strategy, find the peaks; there must be at least one gap between two consecutive peaks
	//Between two consecutive peaks, choose the smaller one as the bar for the trap
	//The rain trapped between the two peaks is the sum of the difference between bar and the numbers between the two peaks 
    public int trap(int[] height) {
        if (height == null || height.length <= 2) return 0;
        int n = height.length;
        //int i;
        int nTrappedRain = 0;
        int nLeftPos=0, nRightPos=0;
        //int nBarHeight;
        int nTrappedRainTmp = 0;
        
        while (nLeftPos < n-2) {
        	
        	while (nLeftPos < n-2 && height[nLeftPos] <= height[nLeftPos+1]) {
        		nLeftPos++;
        	}
        	
        	if (nLeftPos >= n-2) break;
        	
        	nTrappedRainTmp = 0;
    		nRightPos = nLeftPos+2;
    		if (nRightPos <= n-1) {
    			nTrappedRainTmp = height[nLeftPos] - height[nRightPos-1];
    		}
    		
    		while (nRightPos < n-1 && height[nRightPos] <= height[nRightPos+1]) {
    			nRightPos++;
    			nTrappedRainTmp = nTrappedRainTmp + height[nLeftPos] - height[nRightPos-1];
    		}
        	
    		if (height[nLeftPos] > height[nRightPos]) {
    			nTrappedRainTmp = nTrappedRainTmp - (height[nLeftPos] - height[nRightPos])*(nRightPos-nLeftPos-1);
    		}
    		
    		nTrappedRain = nTrappedRain + nTrappedRainTmp;
//        	nBarHeight = Math.min(height[nLeftPos], height[nRightPos]);
//        	for (i=nLeftPos+1; i<nRightPos; i++) {
//        		nTrappedRain = nTrappedRain + nBarHeight - height[i];
//        	}
        	
        	nLeftPos = nRightPos;
        }
        
        return nTrappedRain;
    }
*/	

/*    
    public int trap(int[] height) {
        if (height == null || height.length <= 2) return 0;
        int n = height.length;
        int i;
        int nTrappedRain = 0;
        int nLeftPos=0, nRightPos=0;
        int nBarHeight;
        
        while (nLeftPos < n-2) {
        	
        	while (nLeftPos < n-2 && height[nLeftPos] <= height[nLeftPos+1]) {
        		nLeftPos++;
        	}
        	
        	if (nLeftPos >= n-2) break;
        	
    		nRightPos = nLeftPos+2;
    		while (nRightPos < n-1 && height[nRightPos] <= height[nRightPos+1] && height[nRightPos] < height[nLeftPos]) {
    			nRightPos++;
    		}
        	
        	nBarHeight = Math.min(height[nLeftPos], height[nRightPos]);
        	for (i=nLeftPos+1; i<nRightPos; i++) {
        		if (nBarHeight - height[i] > 0) nTrappedRain = nTrappedRain + nBarHeight - height[i];
        	}
        	
        	nLeftPos = nRightPos;
        }
        
        return nTrappedRain;
    }
*/

}
