package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Given two arrays, write a function to compute their intersection.
//
//Example:
//Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
//
//Note:
//Each element in the result should appear as many times as it shows in both arrays.
//The result can be in any order.
//
//Follow up:
//What if the given array is already sorted? How would you optimize your algorithm?
//What if nums1's size is small compared to num2's size? Which algorithm is better?
//What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

public class IntersectionOfTwoArraysII {

	public IntersectionOfTwoArraysII() {
		// TODO Auto-generated constructor stub
	}

	
	public void run() {
		
		
	}
	
	
	//ACC
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return new int[0];
        int n1 = nums1.length;
        int n2 = nums2.length;
        int i=0, j=0;
        List<Integer> lstComm = new ArrayList<Integer>();
               
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        while (i < n1 && j < n2) {
        	if (nums1[i] == nums2[j]) {
        		lstComm.add(nums1[i]);
        		i++;
        		j++;
        	} else if (nums1[i] < nums2[j]) {
        		i++;
        	} else {
        		j++;
        	}
        }
        
        if (lstComm.size() == 0) return new int[0];
        
       
    	int[] ret = new int[lstComm.size()];
    	
    	i = 0;
    	
    	for (int val:lstComm) ret[i++] = val;
    	
    	return ret;
    }

}
