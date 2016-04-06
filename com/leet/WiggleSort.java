package com.leet;

//Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
//
//For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].

//Google
public class WiggleSort {

	public WiggleSort() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int nums[] = {9,6,1,4,6,1,3,5};
		wiggleSort(nums);
		System.out.println();
	}
	
	//Accepted: 71%
	//Actually, it could goes to round
	//First round, make sure order like (start from 0 position) (small1 big1) (small2 big2) (small big)  ---i.e. every two-element group be small big order (small2 might be larger than big1)
	//Second round, make sure order like (start from 1 position) (big small) (big small) (big small)
	//In this way, could make the order meet the requirement
	//The two round could be merged into one round as follows:
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        
        int n = nums.length;
        int i;
        int ntmp = 0;
        
        for (i=1; i<n; i++) {
            if (i % 2 == 1) {
                if (nums[i] < nums[i-1]) {
                    ntmp = nums[i];
                    nums[i] = nums[i-1];
                    nums[i-1] = ntmp;
                }
            } else {
                if (nums[i] > nums[i-1]) {
                    ntmp = nums[i];
                    nums[i] = nums[i-1];
                    nums[i-1] = ntmp;
                }
            }
        }
        
        return;
    }
	
	//Accpeted: 25%, 
	//Utilize heap sort to sort the elements in ascending order
	//Then from 2nd position to the end, swith the positions of two consecutive elements (i.e. 2nd vs. 3rd;   4th vs. 5th)
    public void wiggleSortA(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        
        int n = nums.length;
        int i;
        int ntmp = 0;
        
        //Construct max-heap
        for (i=n/2; i>=0; i--) {
            HeapAdjust(nums, i, n-1);
        }
        
        //Sort Ascending
        for (i=n-1; i>0; i--) {
            ntmp = nums[i];
            nums[i] = nums[0];
            nums[0] = ntmp;
            HeapAdjust(nums, 0, i-1);
        }
        
        //Wiggle
        for (i=1; i+1<n; i+=2) {
            ntmp = nums[i];
            nums[i] = nums[i+1];
            nums[i+1] = ntmp;
        }
        
        return;
    }
    
    
    //Max-heap
    //Index start from 0
    //Parent (i-1)/2
    //Left child: 2*i+1
    //Right child: 2*i+2
    private void HeapAdjust(int[] nums, int start, int end) {
        int ntmp = nums[start];
        
        for (int i=2*start+1; i<=end; i=i*2+1) {
            if (i < end && nums[i] < nums[i+1]) { //Compare left/right children
                i++;
            }
            
            if (ntmp > nums[i]) break;
            
            nums[start] = nums[i];
            nums[i] = ntmp;
            start = i;
        }
        
        nums[start] = ntmp;
    }

}
