package com.leet;

//Follow up for "Remove Duplicates":
//What if duplicates are allowed at most twice?
//
//For example,
//Given sorted array nums = [1,1,1,2,2,3],
//
//Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. 
//It doesn't matter what you leave beyond the new length.

//Facebook
public class RemoveDuplicatesSortedArrayII {

	public RemoveDuplicatesSortedArrayII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] nums = {1,2,3};
		
		System.out.println("Remained Length = " + removeDuplicates(nums));
	}
	
	
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        int i;
        int nLen = 1;
        int nTmpCnt = 1;
        if (n <= 2) return n;
      
        for (i = 1; i < n; i++) {
        	if (nums[i] == nums[nLen-1]) {
        		if (nTmpCnt <= 1) {
        			nums[nLen] = nums[i];
        			nLen = nLen + 1;
        			nTmpCnt = nTmpCnt + 1;
        		} 
        	} else {
        		nTmpCnt = 1;
        		nums[nLen] = nums[i];
        		nLen = nLen + 1;
        	}
        }
        
        return nLen;
    }
	
	
}
