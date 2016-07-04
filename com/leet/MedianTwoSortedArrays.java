package com.leet;

import java.util.PriorityQueue;

//There are two sorted arrays nums1 and nums2 of size m and n respectively. 
//Find the median of the two sorted arrays. 
//The overall run time complexity should be O(log (m+n)).

//Google, Uber, Zenefits
public class MedianTwoSortedArrays {

	public MedianTwoSortedArrays() {
		// TODO Auto-generated constructor stub
	}

    public void run() {
    	int[] nums2 = {1,3,4,6,7,8,9};
    	int[] nums1 = {3,4,5,7,7,10,11,17};
    	
    	System.out.println(findMedianSortedArrays(nums1, nums2));
    }
    
    
    
    
    //ACC:  but very slow (3%)
    //Strategy:  Keep median+1 elements in a PriorityQueue, and later poll one or two elements to calculate the median
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if ((nums1 == null || nums1.length == 0) && (nums2 == null || nums2.length == 0)) return 0;
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 < n2) return findMedianSortedArrays(nums2, nums1);
        int nTotal = n1 + n2;
        int i;
        int nMedianCnt = nTotal/2 + 1;
        
        if (nums1 == null || nums1.length == 0) {
            if (n2 % 2 == 1) {
                return nums2[n2/2]*1.0;
            } else {
                return (nums2[n2/2-1] + nums2[n2/2])/2.0;
            }
        }
        
        if (nums2 == null || nums2.length == 0) {
            if (n1 % 2 == 1) {
                return nums1[n1/2]*1.0;
            } else {
                return (nums1[n1/2-1] + nums1[n1/2])/2.0;
            }
        }        
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        
        if (nMedianCnt > n1) {  //For example, both nums1, nums2 have 2 elements
            for (i=0; i<n1; i++) {
                pq.offer(nums1[i]);
            }
            
            pq.offer(nums2[0]);
            
            for (i=1; i<n2; i++) {
                if (pq.peek() < nums2[i]) {
                    pq.poll();
                    pq.offer(nums2[i]);
                }
            }
        } else {
            for (i=n1-nMedianCnt; i<n1; i++) {   //nums1 is sorted, only need to take the last MedianCnt elements
                pq.offer(nums1[i]);
            }
            
            for (i=0; i<n2; i++) {
                if (pq.peek() < nums2[i]) {
                    pq.poll();
                    pq.offer(nums2[i]);
                }
            }            
        }
        
        if (nTotal % 2 == 1) {
            return pq.poll()*1.0;
        } else {
            int median1 = pq.poll();
            int median2 = pq.poll();
            return (median1+median2)/2.0;
        }
    }

    
    
    
    
    
    
    //ACC: 43%
    public double findMedianSortedArraysA(int[] nums1, int[] nums2) {
        if ((nums1 == null || nums1.length == 0) && (nums2 == null || nums2.length == 0)) return 0;
        int n1 = 0;
        int n2 = 0;
        
        if (nums1 == null || nums1.length == 0) {
            n2 = nums2.length;
            if (n2 % 2 == 0) {
                return (nums2[(n2-1)/2] + nums2[n2/2])*1.0/2;
            } else {
                return nums2[n2/2];
            }
        }
        
        if (nums2 == null || nums2.length == 0) {
            n1 = nums1.length;
            if (n1 % 2 == 0) {
                return (nums1[(n1-1)/2] + nums1[n1/2])*1.0/2;
            } else {
                return nums1[n1/2];
            }
        }
        
        n1 = nums1.length;
        n2 = nums2.length;
        
        if (n1 < n2) return findMedianSortedArrays(nums2, nums1);
        
        int nMedian = 0;
        nMedian = findKth(nums1, n1, 0, nums2, n2, 0, (n1+n2+1)/2);
        
        if (((n1+n2) % 2) == 0) {  //If total number is even, median will be the average of two elements
            nMedian += findKth(nums1, n1, 0, nums2, n2, 0, (n1+n2+2)/2);
            return (nMedian*1.0)/2;
        }
        
        return nMedian*1.0;
    }
    
    
    //Find kth element from two arrays  (k starts from 1)
    private int findKth(int[] nums1, int n1, int start1, int[] nums2, int n2, int start2, int k) {
        if (start1 >= n1) return nums2[start2+k-1];
        if (start2 >= n2) return nums1[start1+k-1];
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);
        
        int mid1 = start1 + k/2 - 1;  //Try to get (k/2)th elements from nums1
        int mid2 = start2 + k/2 - 1;  //Try to get (k/2)th elements from nums2
        
        int nmidval1 = Integer.MAX_VALUE, nmidval2 = Integer.MAX_VALUE;
        if (mid1 < n1) nmidval1 = nums1[mid1];
        if (mid2 < n2) nmidval2 = nums2[mid2];
        
        if (nmidval1 < nmidval2) {  
        	//In this case, the prefix k/2 elements of nums1 could be ignored, the overall kth will be in nums1's right half and nums2
        	//i.e. if overall merge and sort nums1, nums2, the prefix k/2 of nums1 will be in the head (but prefix k/2 elments of nums2 is not guarenteed, 
        	//some of them might be larger than the elements in right half of nums1
        	//Next, we only need to find the k-k/2 th elements in remaining two arrays, i.e. right half of nums1 and (original) nums2
            return findKth(nums1, n1, mid1+1, nums2, n2, start2, k-k/2);   //k - k/2 is NOT equal to k/2
        } else {  //In this case, the prefix k/2 elements of nums2 could be ignored, the overall kth will be in nums2's right half and nums1
            return findKth(nums1, n1, start1, nums2, n2, mid2+1, k-k/2);
        }
    }
    
}
