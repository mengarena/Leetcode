package com.leet;

//Suppose a sorted array is rotated at some pivot unknown to you beforehand.
//
//(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
//
//Find the minimum element.
//
//You may assume no duplicate exists in the array.


//Microsoft
public class FindMinimumRotatedSortedArray {

	public FindMinimumRotatedSortedArray() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int nums[] = {8, 2, 4, 5, 6};
		
		System.out.println("Min value = " + findMin(nums));
	}
	

	//ACC:  O(logn)
    public int findMin(int[] nums) {
        int i = 0;
        int j = nums.length-1;
        int mid;
        
        while (i < j) {
            mid = i + (j-i)/2;
            
            if (nums[mid] > nums[j]) {
                i = mid+1;
            } else if (nums[mid] < nums[j]) {
                j = mid;
            }
        }
        
        return nums[j];
    }
	
	
    //ACC
	public int findMinA(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int nPos = 0;
        
        for (int i=1; i<n; i++) {
        	if (nums[i] - nums[i-1] < 0) {
        		nPos = i;
        		break;
        	}
        }
                
        return nums[nPos];
    }
	
}
