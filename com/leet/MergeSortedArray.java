package com.leet;

//Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
//
//Note:
//You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. 
//The number of elements initialized in nums1 and nums2 are m and n respectively.

//Facebook, Bloomberg
public class MergeSortedArray {

	public MergeSortedArray() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int nums1[] = new int[10];
		nums1[0] = 3; nums1[1] = 4; nums1[2] = 5;  nums1[3] = 8;
		int nums2[] = {2, 4, 6, 7};
		
		merge(nums1, 4, nums2, 4);
		
		for (int i=0; i<8; i++) System.out.print(nums1[i] + ",");
		System.out.println("");
	}
	
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i;
    	if (nums2 == null || n == 0) return;
        
        if (nums1 == null) {
        	nums1 = nums2;
        	return;
        }

        if (m == 0) {
        	for (i=0; i<n; i++) nums1[i] = nums2[i];
        }

        int nTotalSize = m + n;
        
        //Merge, from end to the beginning
        while (m >=1 && n >=1) {
        	if (nums1[m-1] >= nums2[n-1]) {
        		nums1[nTotalSize-1] = nums1[m-1];
        		m = m-1;
        	} else {
        		nums1[nTotalSize-1] = nums2[n-1];
        		n = n-1;
        	}
        	nTotalSize = nTotalSize - 1;
        	if (m == 0 || n == 0) break;
        }
        
        if (n > 0) {
        	for (i=n-1; i>=0; i--) nums1[i] = nums2[i];
        }
    }
	
}
