package com.leet;

import java.util.ArrayList;
import java.util.List;


//Find the kth largest element in an unsorted array. 
//Note that it is the kth largest element in the sorted order, not the kth distinct element.
//
//For example,
//Given [3,2,1,5,6,4] and k = 2, return 5.
//
//Note: 
//You may assume k is always valid, 1 ¡Ü k ¡Ü array's length.


public class KthLargestElementArray {

	public KthLargestElementArray() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] nums = {3,2,1,5,6,2,4,3};
		int k = 5;
		
		System.out.println(k + "th largest element is: " + findKthLargest(nums, k));
	}
	

    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int i;
        int nLast = nums[0];
        int nInsertPos = 0;
        List<Integer> lstNumbers = new ArrayList<Integer>();  //numbers are stored in Desc order
        
        lstNumbers.add(nLast);
        
        for (i=1; i<n; i++) {
        	if (nums[i] <= nLast) {
        		if (lstNumbers.size() < k) {  //If lstNumbers already has k elements, and if a new element smaller than last one, don't need to put it
        			nInsertPos = getInsertPos(lstNumbers, nums[i]);
        			lstNumbers.add(nInsertPos, nums[i]);
        		}
        	} else {
    			nInsertPos = getInsertPos(lstNumbers, nums[i]);
    			lstNumbers.add(nInsertPos, nums[i]);        		
        	}
        	
        	nLast = lstNumbers.get(lstNumbers.size()-1);  //Last one is the minimal number in the list
        }
        
        
        return lstNumbers.get(k-1);
    }
	
    //Desc order
    public int getInsertPos(List<Integer> lstNumbers, int nNum) {
    	int i=0, j=lstNumbers.size()-1;
    	int nMiddle;
    	    	
    	while (i <= j) {
    		nMiddle = (i+j)/2;
    		
    		if (nNum > lstNumbers.get(nMiddle)) {
    			j = nMiddle - 1;
    		} else if (nNum < lstNumbers.get(nMiddle)) {
    			i = nMiddle + 1;
    		} else {
    			return nMiddle;
    		}
    		
    	}
    	
    	return i;
    }
    
}
