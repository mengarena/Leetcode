package com.leet;

import java.util.Arrays;

//Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), 
//prove that at least one duplicate number must exist. 
//Assume that there is only one duplicate number, find the duplicate one.
//
//Note:
//You must not modify the array (assume the array is read only).
//You must use only constant, O(1) extra space.
//Your runtime complexity should be less than O(n2).
//There is only one duplicate number in the array, but it could be repeated more than once.

//Bloomberg, Hedvig
//Hard
public class FindDuplicateNumber {

	public FindDuplicateNumber() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] nums = {};
		
		System.out.println("The duplicate number is: " + findDuplicate(nums));
	}
	
	//Refer to: http://keithschwarz.com/interesting/code/?dir=find-duplicate  (Floyd's loop detection)
	//
	//Say before entering the cycle, need to go x steps from the starting point (here n-1)
	//Say the meeting point in cycle has length y from the entry point
	//Say the length of the cycle is c and the remaining part of the cycle beyond y is z
	//So with the first do-while, slow goes: x + mc + y  (mc--means multiple cycles)
	//fast goes: 2x + 2mc + 2y = (x+y) + (x+y)  ==>  x + y = nc
	//Now for the second while, when slows goes x steps, x = nc - y  (when slow goes x steps, it reaches the entry point of the cycle)
	//i.e. we could think the fast goes nc-y steps meanwhile, 
	//because when slow starts from the starting point, fast is at position y from the entry point of the cycle
	//so after nc-y, it reaches the entry point of the cycle, so slow and fast meets there
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int slow = n;
        int fast = n;
        
        //Meet slow and fast within the loop (but the meeting point might not be the entry of the cycle
        do {
        	slow = nums[slow-1];
        	fast = nums[nums[fast-1]-1];
        } while (slow != fast);
        
        
        //Find the entry point
        slow = n;

        while (slow != fast) {
        	slow = nums[slow-1];
        	fast = nums[fast-1];
        }
        
        return slow;
    }
	
    
    
    
    public int findDuplicateA(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int i;
        int nDuplicateNum = 0;
        
        Arrays.sort(nums);
        for (i=1; i<n; i++) {
        	if (nums[i] == nums[i-1]) {
        		nDuplicateNum = nums[i];
        		break;
        	}
        }
        
        return nDuplicateNum;
    }	
}
