package com.leet;

import java.util.HashSet;
import java.util.Set;

//Given two arrays, write a function to compute their intersection.
//
//Example:
//Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
//
//Note:
//Each element in the result must be unique.
//The result can be in any order.


public class IntersectionOfTwoArrays {

	public IntersectionOfTwoArrays() {
		// TODO Auto-generated constructor stub
		
		
		
	}

	
	public void run() {
		int[] nums1 = {1,2,2,1};
		int[] nums2 = {2,2};
		
		int[] ret = intersection(nums1, nums2);
		
		for (int i=0; i<ret.length; i++) System.out.println(ret[i]);

	}
	
	//ACC
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return new int[0];
    	Set<Integer> setNum = new HashSet<Integer>();
    	Set<Integer> setComm = new HashSet<Integer>();
    	int n1 = nums1.length;
    	int n2 = nums2.length;
    	int i;
    	int commCnt = 0;
    	
    	if (n1 < n2) return intersection(nums2, nums1);
    	
    	for (i=0; i<n2; i++) {
    		if (!setNum.contains(nums2[i])) setNum.add(nums2[i]);
    	}
    	
    	for (i=0; i<n1; i++) {
    		if (setNum.contains(nums1[i]) && !setComm.contains(nums1[i])) setComm.add(nums1[i]); 
    	}
        
    	if (setComm.size() == 0) return new int[0];
    	
    	int[] ret = new int[setComm.size()];
    	
    	for (int val:setComm) ret[commCnt++] = val;
    	
    	return ret;
    }

}
