package com.leet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
//
//For example,
//Given [100, 4, 200, 1, 3, 2],
//The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
//
//Your algorithm should run in O(n) complexity.

public class LongestConsecutiveSequence {

	public LongestConsecutiveSequence() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		int[] nums = {1, 2, 100, 4, 200, 1, 3, 2};
		int[] nums = {3,2,5,6,7,9,10};
		
		System.out.println("Longest Consecutive sequence Len = " + longestConsecutive(nums));
	}
	
	//ACC: 45%
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;	
        int n = nums.length;
        int i;
        int maxLen = 1;
        int tmpLen = 0;
        Set<Integer> hs = new HashSet<Integer>();
        
        for (i=0; i<n; i++) hs.add(nums[i]);
        
        //Every time starts from the smallest value of a sequence
        for (i=0; i<n; i++) {
        	if (!hs.contains(nums[i]-1)) {
        		int seqVal = nums[i];
        		tmpLen = 1;
        		hs.remove(seqVal);
        		while (hs.contains(seqVal+1)) {
        			seqVal++;
        			hs.remove(seqVal);
        			tmpLen++;
        		}
        		
        		maxLen = Math.max(maxLen, tmpLen);
        	}
        }
        
        return maxLen;
    }
    
    
    //ACC: 19%
	//Strategy: for a sequence [1 2 3 4 5], both the border elements in the map is stored with the length of the whole sequence
    public int longestConsecutiveB(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        Map<Integer, Integer> hm = new HashMap<>();    //Num, Length of the sequence num is in
        int i = 0;
        int maxLen = 1;
        
        while (i < n) {
        	if (!hm.containsKey(nums[i])) {
        		int leftSeqLen = hm.containsKey(nums[i]-1) ? hm.get(nums[i]-1):0;
        		int rightSeqLen = hm.containsKey(nums[i]+1) ? hm.get(nums[i]+1):0;
        		int newLen = leftSeqLen + rightSeqLen + 1;
        		hm.put(nums[i], newLen);
        		
        		maxLen = Math.max(maxLen, newLen);
        				
        		hm.put(nums[i]-leftSeqLen, newLen);
        		hm.put(nums[i]+rightSeqLen, newLen);
        	}
        	
        	i++;
        }
    
        return maxLen;
    }
	
	
	//ACC: but wrong, the problem requires O(n)
    public int longestConsecutiveA(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int i;
        int nLen = 1;
        int nMaxLen = 1;
        
        Arrays.sort(nums);
        
        for (i=1; i<n; i++) {
        	if (nums[i] - nums[i-1] == 1) {
        		nLen++;
        	} else if (nums[i] - nums[i-1] > 1) {
        		nMaxLen = Math.max(nMaxLen, nLen);
        		nLen = 1;
        	}
        }
        
        nMaxLen = Math.max(nMaxLen, nLen);
        
        return nMaxLen;        
    }
	
}
