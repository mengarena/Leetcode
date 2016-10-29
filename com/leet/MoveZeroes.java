package com.leet;

//Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
//
//For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
//
//Note:
//You must do this in-place without making a copy of the array.
//Minimize the total number of operations.


//Facebook, Bloomberg
//Easy
public class MoveZeroes {

	public MoveZeroes() {
		// TODO Auto-generated constructor stub
	}

    public void moveZeroes(int[] nums) {
 		int i;
		int nNonZeroCnt = 0;
		
		//Move non-zero number		
		for (i=0; i<nums.length; i++) {
			if (nums[i] != 0) {
				nums[nNonZeroCnt] = nums[i];
				nNonZeroCnt = nNonZeroCnt + 1;				
			}
		}
		
		//Fill 0 at the end
		for (i=0; i<nums.length-nNonZeroCnt; i++) {
			nums[nums.length-1-i] = 0;
		}

    }

}
