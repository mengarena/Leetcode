package com.leet;

//Given an array of integers, every element appears three times except for one. Find that single one.
//
//Note:
//Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
		
public class SingleNumberII {

	public SingleNumberII() {
		// TODO Auto-generated constructor stub
	}

	
	public void run() {
		int[] nums = {3, 5, 3, 3};
		
		System.out.println("The Single Number is: " + singleNumber(nums));
	}
	
	//Add up, on each bit of the 32-bit result integer, some bit must occurs "1" mod 3 = 0.  The bit involves the single number will not have "1" mod 3 = 0;
    public int singleNumber(int[] nums) {
        int nSingleNum = 0;
        int narrBitOne[] = new int[32];
        int n = nums.length;
        int i, j;
        int nNum = 0;
        
        for (i=0; i<n; i++) {

        	for (j=0; j<32; j++) {   // 32-bit integer
        		nNum = (nums[i] >> j) & 1;
        		if (nNum == 1) narrBitOne[j]++;
        	}
        }
        
        for (i=0; i<32; i++) {
        	if ((narrBitOne[i] % 3) != 0) nSingleNum = nSingleNum + (1 << i);
        }
        
        return nSingleNum;
    }
	
}
