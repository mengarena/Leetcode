package com.leet;

//Rotate an array of n elements to the right by k steps.
//
//For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
//
//Note:
//Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
//
//[show hint]
//
//Hint:
//Could you do it in-place with O(1) extra space?
//Related problem: Reverse Words in a String II

//Microsoft, Bloomberg
public class RotateArray {

	public RotateArray() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int nums[] = {1,2,3,4,5,6,7, 8};
		int k = 3;
		
		rotate(nums, k);
		
		for (int i=0; i<nums.length; i++)
			System.out.print(nums[i] + ",");
		
	}
	

    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length == 1) return;
        
        int n = nums.length;
        
        int nNewK = k % n;
        if (nNewK == 0) return;
        
        int narrExt[] = new int[nNewK];
        int i;
        
        for (i=0; i<nNewK; i++) narrExt[i] = nums[n-nNewK+i];
        for (i=n-nNewK-1; i>=0; i--) nums[i+nNewK] = nums[i];
        for (i=0; i<nNewK; i++) nums[i] = narrExt[i];
        
    }
	
	
}
