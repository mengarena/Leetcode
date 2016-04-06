package com.leet;

//Given an unsorted array of integers, find the length of longest increasing subsequence.
//
//For example,
//Given [10, 9, 2, 5, 3, 7, 101, 18],
//The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. 
//Note that there may be more than one LIS combination, it is only necessary for you to return the length.
//
//Your algorithm should run in O(n2) complexity.
//
//Follow up: Could you improve it to O(n log n) time complexity?

//Microsoft
public class LongestIncreasingSubsequence {

	public LongestIncreasingSubsequence() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
		
		System.out.println(lengthOfLIS(nums));
	}
	
	
	//Process element by element, find the ranking of current element within all the elements from head to current
	//Each element will have a ranking, but for each ranking (say 3rd place), along with processing, the actual value might change
	//Use seqEnd to remember the raw index of the element in nums for each ranking (i.e. position)
	//And also remember the lisLen, if one element's ranking position is > lisLen, it means this element is larger than all elements before it
	//i.e. segEnd remembers the indexes of the last elements for each ranking between 1~lisLen
	public int lengthOfLIS(int[] nums) {
	    if (nums.length==0) return 0;
	    int len = nums.length;
	    int[] seqEnd = new int[len+1]; 
	    seqEnd[1] = 0;
	    int lisLen = 1;
	    for (int i=1;i<len;i++){
	        int pos = findPos(nums,seqEnd,lisLen,i);  //ranking position of a possible sub sequence till this ith element
	        seqEnd[pos] = i;   //Remember the raw index (of nums) of the last element of a sub-sequence 
	        if (pos>lisLen) lisLen = pos;
	    }

	    return lisLen;
	    
	}

	public int findPos(int[] nums, int[] seqEnd, int lisLen, int index){
	    int start = 1;
	    int end = lisLen;
	    while (start<=end){
	        int mid = (start+end)/2;

	        if (nums[index] == nums[seqEnd[mid]]){
	            return mid;
	        } else if (nums[index]>nums[seqEnd[mid]]){
	            start = mid+1;
	        } else end = mid-1;
	    }
	    return start;
	}

	

	/*
    public int lengthOfLIS(int[] nums) {
        int nLenLIS = 0;
        
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        
        int n = nums.length;
        int i, j;
        int[] narrLIS = new int[n];
        
        for (i=0; i<n; i++) {
        	narrLIS[i] = 1;
        	for (j=0; j<i; j++) {
        		if (nums[j] < nums[i]) narrLIS[i] = Math.max(narrLIS[i], narrLIS[j]+1) ;
        	}
        	
        	if (nLenLIS < narrLIS[i]) nLenLIS = narrLIS[i];
        }
        
        
        return nLenLIS;
    }

   */
}
