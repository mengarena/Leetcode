package com.leet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Given an array of integers, find out whether there are two distinct indices i and j in the array 
//such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.

public class ContainsDuplicateIII {

	public ContainsDuplicateIII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] nums = {1,3,1};
		int k = 1;
		int t = 1;
		System.out.println(containsNearbyAlmostDuplicate(nums, k, t));
	}

	
	
	//Strategy: put the elements into buckets
	//The width of the bucket is t+1
	//Check is there any two numbers with (j-i <= k) but they have same or neighboring buckets
	//Same or neighboring buckets means the difference between the two element might be <= t
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) return false;
        int n = nums.length;
        int i;
        Map<Long, Integer> mapBucket = new HashMap<Long, Integer>();   //Bucket Label, value of nums[i]
        long nBucketWidth = (long) t + 1;
        		
        for (i=0; i<n; i++) {
        	if (i > k) mapBucket.remove(getBucketLabel((long)nums[i-k-1], nBucketWidth));
        	long nLabel = getBucketLabel((long)nums[i], nBucketWidth);
        	if (mapBucket.containsKey(nLabel)) {
        		return true;
        	} else if (mapBucket.containsKey(nLabel-1) && Math.abs((long)nums[i] - mapBucket.get(nLabel-1)) <= t) {
        		return true;
        	} else if (mapBucket.containsKey(nLabel+1) && Math.abs((long)nums[i] - mapBucket.get(nLabel+1)) <= t) {
        		return true;
        	}
        	
        	mapBucket.put(nLabel, nums[i]);
        }
        
        return false;
    }
	

	private long getBucketLabel(long nNum, long nBucketWidth) {
		long nLabel = 0;
		
		if (nNum >= 0) {
			nLabel = nNum/nBucketWidth;
		} else {
			nLabel = (nNum+1)/nBucketWidth - 1;
		}
		
		return nLabel;
	}

    
/*
    //Works, but exceeded time limit
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0) return false;
        int n = nums.length;
        int i,j;
                
        for (i=0; i<n-1; i++) {
        	j = i+1;
        	while (j <= n-1 && j <= i+k) {
        		if (Math.abs(nums[j] - nums[i]) <= t) return true;
        		j++;
        	}
        }
        
        return false;
    }
*/	
	
}
