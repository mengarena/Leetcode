package com.leet;

//Follow up for "Find Minimum in Rotated Sorted Array":
//What if duplicates are allowed?
//
//Would this affect the run-time complexity? How and why?
//Suppose a sorted array is rotated at some pivot unknown to you beforehand.
//
//(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
//
//Find the minimum element.
//
//The array may contain duplicates.

public class FindMinimumRotatedSortedArrayII {

	public FindMinimumRotatedSortedArrayII() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int[] nums = {4,5,6,7,0,1,2};
		System.out.println("The Min = " + findMin(nums));
	}
	
	
	//ACC:  Worst case O(n)
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
            } else {  //[Different from Find Minimum in Rotated Sorted Array]
            	      //Here nums[mid] == nums[j], we don't know the minimal is on mid's left or right, here just reduce the j once.  
            	      //If made a wrong move, later i will correct the moving direction
            	      //So: To the question: Would this affect the run-time complexity? How and why?  Yes, it will affect. Worst case becomes O(n)
                j--;
            }
        }
        
        return nums[j];
    }	
	
    
    //ACC
    public int findMinA(int[] nums) {
        int i;
        int nMin = nums[0];
        
        for (i=0; i<nums.length-1; i++) {
        	if (nums[i+1] < nums[i]) {
        		nMin = nums[i+1];
        		break;
        	}
        }
        
        return nMin;
    }
	
}
