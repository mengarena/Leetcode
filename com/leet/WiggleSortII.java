package com.leet;

//Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
//
//Example:
//(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
//(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
//
//Note:
//You may assume all input has valid answer.
//
//Follow Up:
//Can you do it in O(n) time and/or in-place with O(1) extra space?

//Google
public class WiggleSortII {

	public WiggleSortII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//int[] nums = {3,3,3,2,2,2,3,2,1,1,2,1,2,3,3,3,1,2};
		//int[] nums = {2,2,5,4};
		int[] nums = {1, 3, 2, 2, 3, 1};
		//int[] nums = {1, 5, 1, 1, 6, 4};
		
		wiggleSort(nums);
		
		for (int i=0; i<nums.length; i++) System.out.print(nums[i] + ",");
		
		System.out.println();
	}
	
	
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int n = nums.length;
        
        int nMedian = findMedian(nums);
        int i = 0;
        int nn = n | 1;
        int nLargePos = 1;  //First position for putting large value
        int nMidPos = 1;
        int nSmallPos = (1+2*(n-1)) % nn;  //Last position for putting small value
        int nTmp;
        
        while (i < n) {
        	if (nums[nMidPos] > nMedian) {
        		nTmp = nums[nLargePos];
        		nums[nLargePos] = nums[nMidPos];
        		nums[nMidPos] = nTmp;
        		nMidPos = (nMidPos + 2) % nn;
        		nLargePos = (nLargePos + 2) % nn;
        	} else if (nums[nMidPos] < nMedian) {
        		nTmp = nums[nSmallPos];
        		nums[nSmallPos] = nums[nMidPos];
        		nums[nMidPos] = nTmp;
        		nSmallPos = (nSmallPos - 2 + nn) % nn;   //In this part, we don't know whether the value originally in nums[nSmallPos] is > nMedian or not, so don't increase nMidPos
        	} else {
        		nMidPos = (nMidPos + 2) % nn;
        	}
        	
        	i++;
        }
    }
	
    
    //Important algorithm
    private int findMedian(int[] nums) {
    	if (nums == null || nums.length == 0) return 0;
    	if (nums.length == 1) return nums[0];
    	int nMedth = (nums.length-1)/2+1;  //Median position
    	int nLeft = Integer.MIN_VALUE; 
    	int nRight = Integer.MAX_VALUE;
    	int nMedian = 0;
		int nSmallerCnt = 0, nLargerCnt = 0;
		int nCntDiff = 0;
    	
    	while (nLeft <= nRight) {
    		nMedian = (nLeft < 0 && nRight > 0) ? (nLeft + nRight) / 2 : nLeft + (nRight - nLeft) / 2;
    		nSmallerCnt = 0;
    		nLargerCnt = 0;
    		nCntDiff = 0;
    		
    		for (int i=0; i<nums.length; i++) {
    			if (nums[i] > nMedian) {
    				nLargerCnt++;
    				if (nLargerCnt > nMedth-1) {
    					nCntDiff = 1;
    					break;
    				}
    			} else if (nums[i] < nMedian) {
    				nSmallerCnt++;
    				if (nSmallerCnt > nMedth-1) {  //nums.length - nMedth also OK
    					nCntDiff = -1;
    					break;
    				}
    			}
    		}
    		    		
    		if (nCntDiff > 0) {
    			nLeft = nMedian + 1;
    		} else if (nCntDiff < 0) {
    			nRight = nMedian - 1;
    		} else {
    			break;
    		}

    	}
    	
    	return nMedian;
    }
}
