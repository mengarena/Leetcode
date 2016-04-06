package com.leet;

import java.util.ArrayList;
import java.util.List;

//Find the contiguous subarray within an array (containing at least one number) which has the largest product.
//
//For example, given the array [2,3,-2,4],
//the contiguous subarray [2,3] has the largest product = 6.

//Linkedin
public class MaximumProductSubarray {

	public MaximumProductSubarray() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		int[] nums = {2,-1,2,4,1,3,-2,1,0,-4,2,3,1};
		int[] nums = {1,-2,1};
		
		System.out.println("Max Product = " + maxProduct(nums));
	}
	
	
	//Strategy:  Find 0s, use 0 to segment the original array
	//In each segment, find the total number of negative elements and the first/last negative elements
	//If in each segment, total number of negative elements are even, the max product is the product of the total segment
	//Otherwise, check the sub-segment which ends at the first negative and the sub-segment which starts at the last negative, 
	//compare them and then to divide the larger one from the total product of the segment
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        if (n == 1) return nums[0];
        int nMaxProduct = Integer.MIN_VALUE;
        int i;
        
        List<Integer> lstZeroPos = new ArrayList<Integer>();
        
        for (i = 0; i < n; i++) {
        	if (nums[i] == 0) lstZeroPos.add(i);
        }
        
        int nSegStartPos = 0;
        int nSegEndPos = n-1;
        
        for (Integer nZeroPos:lstZeroPos) {
        	nSegEndPos = nZeroPos-1;
        	nMaxProduct = Math.max(nMaxProduct, maxProductSeg(nums, nSegStartPos, nSegEndPos));
        	nSegStartPos = nZeroPos + 1;
        }
        
        nMaxProduct = Math.max(nMaxProduct, maxProductSeg(nums, nSegStartPos, n-1));
        if (nMaxProduct < 0 && lstZeroPos.size() > 0) nMaxProduct = 0;
        
        return nMaxProduct;
    }
	
    
    private int maxProductSeg(int[] nums, int nSegStartPos, int nSegEndPos) {
    	int nMaxProduct = Integer.MIN_VALUE;
    	if (nSegEndPos < nSegStartPos) return nMaxProduct;
    	if (nSegEndPos == nSegStartPos) return nums[nSegStartPos];
        int nFirstNegIdx = -1;
        int nLastNegIdx = -1;
        int nNegCount = 0;
        int i;
        int nTmpProduct = 1;
        
        for (i=nSegStartPos; i<=nSegEndPos; i++) {
        	if (nums[i] < 0) {
        		nNegCount++;
        		if (nFirstNegIdx == -1) nFirstNegIdx = i;
        		nLastNegIdx = i;
        	}
        	
        	nTmpProduct *= nums[i];
        }
    	
        if (nNegCount % 2 == 0) return nTmpProduct;
        
        int nProductHead = 1;
        int nProductTail = 1;
        
        for (i=nSegStartPos; i<=nFirstNegIdx; i++)  nProductHead *= nums[i];
        for (i=nLastNegIdx; i<=nSegEndPos; i++)  nProductTail *= nums[i];
        
        if (nProductHead > nProductTail) {
        	nMaxProduct = nTmpProduct/nProductHead;
        } else {
        	nMaxProduct = nTmpProduct/nProductTail;
        }
        
    	return nMaxProduct;
    }
    
}
