package com.leet;

import java.util.*;

//Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. 
//Find the two elements that appear only once.
//
//For example:
//
//Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
//
//Note:
//The order of the result is not important. So in the above example, [5, 3] is also correct.
//Your algorithm should run in linear runtime complexity. 
//Could you implement it using only constant space complexity?
		
public class SingleNumberIII {

	public SingleNumberIII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int nums[] = {1,2,1,3,2,5};
		
		int[] newNums = singleNumber(nums);
		for (int i=0; i<newNums.length; i++) System.out.print(newNums[i] + ",");
	}

	
    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int n = nums.length;
        int i;
        int sum = 0;
        int ret = 0;
        int bitpos = 0;
        
        int[] arrDistinct = new int[2];
        
        for (i=0; i<n; i++) sum = sum ^ nums[i];
    
        bitpos = 1;
    
        for (i=0; i<32; i++) {
            ret = sum & bitpos;
            if (ret == bitpos) break;
            bitpos = bitpos*2;
        }
        
        arrDistinct[0] = 0;
        arrDistinct[1] = 0;
        
        for (i=0; i<n; i++) {
            if ((nums[i] & bitpos) == 0) {
                arrDistinct[0] = arrDistinct[0] ^ nums[i];
            } else {
                arrDistinct[1] = arrDistinct[1] ^ nums[i];
            }
        }
        
        return arrDistinct;
    }	
	
    public int[] singleNumberA(int[] nums) {
        int[] narrSingleNums= new int[2];
        
        if (nums == null || nums.length == 0) return null;
        
        int n = nums.length;

        int nSingeOneTwo = 0;
        for (int i=0; i<n; i++) {
        	nSingeOneTwo = nSingeOneTwo ^ nums[i];
        }
        
        //The two single occurrence numbers (a, b) must be different at least at one bit
        int nLastOneBit = nSingeOneTwo - (nSingeOneTwo & (nSingeOneTwo - 1));   //The last different bit (On this bit, one of (a, b) is 1, the other is 0)
        int a = 0, b = 0;
        
        //Based on the bit = 1 or 0, separate the numbers into two groups. 
        for (int i=0; i<n; i++) {
        	if ((nLastOneBit & nums[i]) == 0) {  //Or could == nLastOneBit
        		a = a ^ nums[i];   //Other normal elements occur twice each, will be cancelled
        	} else {
        		b = b ^ nums[i];
        	}
        }
        
        narrSingleNums[0] = a;
        narrSingleNums[1] = b;
        
        return narrSingleNums;
    }
	
/*    
	//Works, not very efficient
    public int[] singleNumber(int[] nums) {
        int[] nSingleNums= new int[2];
        
        if (nums == null || nums.length == 0) return null;
        
        int n = nums.length;
        Map<Integer, Integer> mapNums = new HashMap<Integer, Integer>();
        
        for (int i=0; i<n; i++) {
        	if (mapNums.containsKey(nums[i])) {
        		mapNums.remove(nums[i]);
        	} else {
        		mapNums.put(nums[i], nums[i]);
        	}
        }
        
        Set<Integer> setKey = mapNums.keySet();
        Iterator it = setKey.iterator();
        nSingleNums[0] = (int) it.next();
        nSingleNums[1] = (int) it.next();
        
        return nSingleNums;
    }
*/
    
}
