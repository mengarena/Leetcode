package com.leet;

//Given an array with n objects colored red, white or blue, 
//sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
//
//Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
//
//Note:
//You are not suppose to use the library's sort function for this problem.
//
//click to show follow up.
//
//Follow up:
//A rather straight forward solution is a two-pass algorithm using counting sort.
//First, iterate the array counting number of 0's, 1's, and 2's, 
//then overwrite array with total number of 0's, then 1's and followed by 2's.
//
//Could you come up with an one-pass algorithm using only constant space?

public class SortColors {

	public SortColors() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int nums[] = {0, 2, 2};
		sortColors(nums);
		
		for (int i=0; i<nums.length; i++) System.out.print(nums[i] + ",");
	}
	
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        
        int n = nums.length;
        int nRedCnt = 0;
        int nBlueCnt = 0;
        int i;
        int nTmp;
        
        for (i=0; i<=n-1; i++) {
        	if (nums[i] == 0) {
        		if (nRedCnt != i) {
        			while (nums[nRedCnt] == 0 && nRedCnt < n-1) nRedCnt++;
        			nTmp = nums[nRedCnt];
        			nums[nRedCnt] = nums[i];
        			nums[i] = nTmp;
        			
        		} 
        		
        		nRedCnt = nRedCnt + 1;
        	} else if (nums[i] == 2) {
        		if (i < n-nBlueCnt-1) {
        			while (nums[n-nBlueCnt-1] == 2 && nBlueCnt < n-1-i) nBlueCnt++;
        			nTmp = nums[n-nBlueCnt-1];
        			nums[n-nBlueCnt-1] = nums[i];
        			

        			if (nTmp != 0) {
        				nums[i] = nTmp;
        			} else {
        				nums[i] = nums[nRedCnt];
        				nums[nRedCnt] = nTmp;
        				nRedCnt = nRedCnt + 1;
        			}
        		}
        		
        		nBlueCnt = nBlueCnt + 1;
        	}
        }
        
    }
	
}
