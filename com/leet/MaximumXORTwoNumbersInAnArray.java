package com.leet;

import java.util.HashSet;
import java.util.Set;

//Given a non-empty array of numbers, a0, a1, a2, ¡­ , an-1, where 0 ¡Ü ai < 231.
//
//Find the maximum result of ai XOR aj, where 0 ¡Ü i, j < n.
//
//Could you do this in O(n) runtime?
//
//Example:
//
//Input: [3, 10, 5, 25, 2, 8]
//
//Output: 28
//
//Explanation: The maximum result is 5 ^ 25 = 28.

//Google
public class MaximumXORTwoNumbersInAnArray {

	public MaximumXORTwoNumbersInAnArray() {
		// TODO Auto-generated constructor stub
	}

	//ACC
//	example: Given [14, 11, 7, 2], which in binary are [1110, 1011, 0111, 0010].
//	Since the MSB is 3, I'll start from i = 3 to make it simplify.
//
//	i = 3, set = {1000, 0000}, max = 1000
//	i = 2, set = {1100, 1000, 0100, 0000}, max = 1100
//	i = 1, set = {1110, 1010, 0110, 0010}, max = 1100
//	i = 0, set = {1110, 1011, 0111, 0010}, max = 1100	
	
    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        int i;
        int mask = 0;
        int max = 0;
        int tmp = 0;
        
        for (i=31; i>=0; i--) {
            mask = mask | (1 << i);
            
            Set<Integer> setPrefix = new HashSet<Integer>();
            
            for (int num:nums) {
                setPrefix.add(num & mask);  //Reserve left bits and ignore right bits
            }
            
            tmp = max | (1 << i);   //To check whether in each iteration, there are pair(s) whoes left bits can XOR to this value, 
                                    //if can, this will become the possible max (left bits) value of the final result
            
            for (int prefix:setPrefix) {
                if (setPrefix.contains(prefix ^ tmp)) {
                    max = tmp;
                    break;
                }    
            }
        }
        
        return max;
    }

}
