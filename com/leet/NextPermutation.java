package com.leet;

import java.util.Arrays;

//Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
//
//If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
//
//The replacement must be in-place, do not allocate extra memory.
//
//Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
//1,2,3 ¡ú 1,3,2
//3,2,1 ¡ú 1,2,3
//1,1,5 ¡ú 1,5,1

public class NextPermutation {

	public NextPermutation() {
		// TODO Auto-generated constructor stub
	}

	
	public void run() {
//		int[] nums = {7,8,6,9,8,7,2};
		int[] nums = {1,5,1};
		
		nextPermutation(nums);
		
		for (int i=0; i<nums.length; i++) System.out.print(nums[i] + ",");
	}
	
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int n = nums.length;
        int i,j;
        int nPos = -1;
        
        for (i=n-1; i>0; i--) {
        	if (nums[i-1] < nums[i]) {
        		nPos = i-1;
        		break;
        	}
        }
        
        if (nPos == -1) {
        	Arrays.sort(nums);
        	return;
        }
        
        int nMin = nums[nPos];
        int nChangePos = nPos;
        
        //Find the one which is smallest-larger than nums[nPos]
        for (i=nPos+1; i<n; i++) {
        	if (nums[i] > nums[nPos]) {
        		if (nChangePos == nPos) {
        			nChangePos = i;
        			nMin = nums[i];
        		} else {
        			if (nums[i] < nMin) {
        				nChangePos = i;
        				nMin = nums[i];
        			}
        		}
        	}
        }
        
        if (nChangePos == nPos) {
        	Arrays.sort(nums);
        	return;
        }
        
        //Exchange value to put the smallest-larger digit at position nPos
        int nTmp = nums[nChangePos];
        nums[nChangePos] = nums[nPos];
        nums[nPos] = nTmp;
        boolean bChangedPos = false;
        
        //Sort numbers from nPos+1 ~ n-1 in ascending order (bubble sort)
        for (i=nPos+1; i<n-1; i++) {
        	bChangedPos = false;
        	for (j=nPos+2; j<=n-i+nPos; j++) {
        		if (nums[j-1] > nums[j]) {
        			bChangedPos = true;
        			nTmp = nums[j-1];
        			nums[j-1] = nums[j];
        			nums[j] = nTmp;
        		}
        	}
        	
        	if (bChangedPos == false) break;
        }
        
    }	
	
}
