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
//Easy
public class RotateArray {

	public RotateArray() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int nums[] = {1,2,3};
		int k = 2;
		
		rotateB(nums, k);
		
		for (int i=0; i<nums.length; i++)
			System.out.print(nums[i] + ",");
		
	}
	
	public void rotate(int[] nums, int k) {
		if (nums == null || nums.length <= 1) return;
		
		int n = nums.length;
		k = k % n;
		
		reverse(nums, 0, n-1);
		reverse(nums, 0, k-1);
		reverse(nums, k, n-1);
	}
	
	
	public void reverse(int[] nums, int start, int end) {
		while (start < end) {
			int tmp = nums[start];
			nums[start] = nums[end];
			nums[end] = tmp;
			start++;
			end--;
		}
	}

	
	//Method 1
    public void rotateA(int[] nums, int k) {
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
	
	
    public void rotateB(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;
        int n = nums.length;
        int i;
        int tmp;
        
        k = k % n;
        
        for (i=1; i<=k; i++) {
            tmp = nums[n-i];
            nums[n-i] = nums[k-i];
            nums[k-i] = tmp;
        }
        
        for (i=n-k-1; i>=k; i--) {
            for (int j=1; j<=k; j++) {
                tmp = nums[i+j];
                nums[i+j] = nums[i+j-1];
                nums[i+j-1] = tmp;
            }
        }
    }
    
    
}
