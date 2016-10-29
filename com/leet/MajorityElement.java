package com.leet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//Given an array of size n, find the majority element. The majority element is the element that appears more than [ n/2 ] (low bound) times.
//
//You may assume that the array is non-empty and the majority element always exist in the array.

//Zenefits, Adobe
//Easy
public class MajorityElement {

	public MajorityElement() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
	}
	
	
	public int majorityElement(int[] nums) {
	    Arrays.sort(nums);
	    return nums[nums.length / 2];
	}
	
	public int majorityElementC(int[] nums) {
		int ret = 0;
		
		for (int i = 0; i < 32; i++) {
			int ones = 0;
			int zeros = 0;
			
			for (int j = 0; j < nums.length; j++) {
				if ((nums[j] & (1 << i)) != 0) {
				    ++ones;
				} else {
					++zeros;
				}
				
			}
			
			if (ones > zeros) {
				ret |= (1 << i);
			}
		}
		
		return ret;
	}
	
	
	//Moore¡¯s Voting Algorithm
    public int majorityElementB(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int maj = nums[0];
        int count = 1;
        
        for (int i=1; i<nums.length; i++) {
            if (count == 0) {
                maj = nums[i];
                count++;
                continue;
            }
            
            if (nums[i] == maj) {
                count++;
                if (count > nums.length/2) return maj;
            } else {
                count--;
            }
        }
        
        return maj;
    }
	
	
    public int majorityElementA(int[] nums) {
        int n = nums.length/2;
        int nCnt;
        if (nums.length == 1) return nums[0];
        
        Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();
        
        for (int i=0; i<nums.length; i++) {
        	if (numMap.containsKey(nums[i])) {
        		nCnt = numMap.get(nums[i]);
        		nCnt = nCnt + 1;
        		
        		if (nCnt > n) return nums[i];
        		numMap.put(nums[i], nCnt);
        	} else {
        		numMap.put(nums[i], 1);
        	}
        }
        
		return 0;    
    }	
}
