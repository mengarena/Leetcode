package com.leet;

//Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
//Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ¡Ü j), inclusive.
//
//Note:
//A naive algorithm of O(n2) is trivial. You MUST do better than that.
//
//Example:
//Given nums = [-2, 5, -1], lower = -2, upper = 2,
//Return 3.
//The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.


//Google
public class CountRangeSum {

	public CountRangeSum() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] nums = {1};
		int lower = -2;
		int upper = 2;
		
		System.out.println("#Range Sum = " + countRangeSum(nums, lower, upper));
	}
	
	
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0 || lower > upper) return 0;
        int n = nums.length;
        int i;
        int nRangeCnt = 0;
        long[] sums = new long[n+1];
        
        for (i=0; i<n; i++) {
        	sums[i+1] = sums[i] + nums[i];
        }
        
        nRangeCnt = mergeSort(sums, 0, n+1, lower, upper);
        
        return nRangeCnt;
    }
    
    private int mergeSort(long[] sums, int nStartPos, int nEndPos, int lower, int upper) {   //Not including nEndPos
    	int nRangeCnt = 0;
    	
    	if (nEndPos <= nStartPos+1) return 0;   //Here uses sum, so if nEndPos-1 = nStartPos, there is no element (of nums) between
    	int nMid = nStartPos + (nEndPos-nStartPos)/2;
    	
    	//Sort left half and right half
    	nRangeCnt = mergeSort(sums, nStartPos, nMid, lower, upper);
    	nRangeCnt += mergeSort(sums, nMid, nEndPos, lower, upper);
    	
    	//From this step below, the left half and right half has already been sorted, i.e. the sums in left and right half are in ascending order
    	
    	//At this moment, it is possible that some elements in right half < elements in left half. Through following steps, all the elements will be in order
    	//Since the count in left half and right half has already been calculated, we only need to calculate the count across the left and right half
    	long[] cache = new long[nEndPos-nStartPos];  //Used for sorting
    	int nUpBound = nMid;
    	int nLowBound = nMid;
    	int t = nMid;
    	int r = 0;
    	
    	for (int i=nStartPos; i<nMid; i++, r++) {   //Search through left half
    		while (nUpBound < nEndPos && sums[nUpBound] - sums[i] <= upper) nUpBound++;     //Search through right half, to find the first one which makes the difference > upper
    		while (nLowBound < nEndPos && sums[nLowBound] - sums[i] < lower) nLowBound++;   //Search through right half, to find the first one which makes the difference >= lower
    		
    		nRangeCnt += nUpBound-nLowBound;      //Then the sum difference between nUpBound and nLowBound to the sum[i]   
    		                                      //(corresponds to the sum of the element from nums[i] ~ (nums[nLowBound], nums[nUpBound]) meet the condition 
    		
    		//Sorted the elements in both left and right into cache
    		//The elements in right half, if they are smaller than the current one (i) in left half, they could be saved into cache first
    		while (t < nEndPos && sums[t] < sums[i]) cache[r++] = sums[t++];   //t is the index in the right half, it is monotically increasing during the for and while loop
    		
    		cache[r] = sums[i];
    	}
    	
    	//Copy sorted elements from cache into original sums, in this way, sorted the combination of left and right
    	System.arraycopy(cache, 0, sums, nStartPos, r);    //Copy sorted sums back (from cache to sums), r (count) elements are sorted
    	
    	return nRangeCnt;
    }
	
}
