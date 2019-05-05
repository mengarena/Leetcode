package com.leet;

import java.util.Arrays;

//Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
//
//Try to solve it in linear time/space.
//
//Return 0 if the array contains less than 2 elements.
//
//You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
//
//
//"difference" between two elements A and B means the absolute value of (A - B).
//
//"in its sorted form" means we need to get the maximum gap in the global sorted order, not the the given unsorted array order.
//
//For example, given a array [1, 7, 3, 3], the expected result it 4, not (7 - 1) = 6.
//
//We need to sort the the array into [1, 3, 3, 7], and get the maximum gap between the successive elements in this sorted array, 
//which is (7 - 3) = 4.

//Hard
public class MaximumGap {

	public MaximumGap() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//int[] nums = {1, 10000000};
		int[] nums = {1, 30, 35, 60, 82, 100};
		
		System.out.println(maximumGap_BucketSort(nums));
		
	}
	
	
	//ACC: 25%
    public int maximumGap_BucketSort(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int n = nums.length;
        int nMax = Integer.MIN_VALUE;
        int nMin = Integer.MAX_VALUE;
        
        for (int i:nums) {
            nMax = Math.max(nMax, i);
            nMin = Math.min(nMin, i);
        }
        
        if (nMin == nMax) return 0;
        
        int bucketSize = (int)Math.ceil((nMax-nMin)*1.0/(n-1));
        int[] narrMax = new int[n-1];
        int[] narrMin = new int[n-1];
        
        Arrays.fill(narrMax, Integer.MIN_VALUE);
        Arrays.fill(narrMin, Integer.MAX_VALUE);
        
        int idx = 0;
        
        for (int i:nums) {
            if (i == nMax || i == nMin) continue;
            idx = (i-nMin)/bucketSize;
            
            narrMax[idx] = Math.max(narrMax[idx], i);
            narrMin[idx] = Math.min(narrMin[idx], i);
        }
        
        int maxGap = Integer.MIN_VALUE;
        int previous = nMin;
        
        for (int i=0; i<n-1; i++) {
            if (narrMin[i] == Integer.MAX_VALUE && narrMax[i] == Integer.MIN_VALUE) continue;
            
            maxGap = Math.max(maxGap, narrMin[i] - previous);
            
            previous = narrMax[i];
        }
        
        maxGap = Math.max(maxGap, nMax - previous);
        
        return maxGap;
    }
	
	
	
	

    //ACC: 51%
    //Bucket Sort Time Complexity O(n+k),  Space Complexity: O(n)
    //Refer to: https://leetcode.com/discuss/18499/bucket-sort-java-solution-with-explanation-o-time-and-space
    public int maximumGap_BucketSortA(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int nMax = Integer.MIN_VALUE;
        int nMin = Integer.MAX_VALUE;
        int n = nums.length;
        int i;
        
        for (i=0; i<n; i++) {
            nMax = Math.max(nMax, nums[i]);
            nMin = Math.min(nMin, nums[i]);
        }
        
        if (nMin == nMax) return 0;
        
        int nBucketSize = (int)Math.ceil((nMax-nMin)*1.0/(n-1));
        
        int nBucketCount = (nMax - nMin) / nBucketSize + 1;
        
        int narrMin[] = new int[nBucketCount];   //The min value of each bucket
        int narrMax[] = new int[nBucketCount];   //The max value of each bucket
        
        Arrays.fill(narrMin, -1);
        Arrays.fill(narrMax, -1);
        
        int nIdx;
        
        for (i=0; i<n; i++) {
            nIdx = (nums[i] - nMin)/nBucketSize;  //Decide which bucket the element should go
            
            if (narrMin[nIdx] == -1 || nums[i] < narrMin[nIdx]) narrMin[nIdx] = nums[i];
            if (narrMax[nIdx] == -1 || nums[i] > narrMax[nIdx]) narrMax[nIdx] = nums[i];
        }
        
        int nPrevMax = narrMax[0];
        int nMaxGap = narrMax[0] - narrMin[0];
        
        //Also fine to check the gap within one bucket
        //According to the analysis in the reference URL, there are n-2 numbers put in n-1 bucket, 
        //so at least one bucket is empty, so the number around that bucket will generate large gap
        for (i=1; i<nBucketCount; i++) {
            if (narrMin[i] == -1) continue;
            nMaxGap = Math.max(nMaxGap, narrMin[i] - nPrevMax);
            nPrevMax = narrMax[i];
        }
        
        return nMaxGap;
    }
	
	
    //ACC: 79%
    //Solution: Radix Sort (based on each position digit, from the lowest to highest, round is decided by the maximal value)
    //Radix sort time complexity: O(nk), Space Complexity: O(n+k) --k is the number of digits in the maximal number
    //This sort guarantees (between two numbers): If all other digits are same, only one is different, 
    //the number with small digit will be put ahead of the number with larger digit
    //For example, for 3 numbers:   35, 27, 34
    //After round 1):     34, 35, 27    (i.e. sorted based on last digit)
    //After round 2):     27, 34, 35    (i.e. softed based on second last digit)
    public int maximumGap_RadixSort(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int nMax = Integer.MIN_VALUE;
        int n = nums.length;
        int i;
        
        for (i=0; i<n; i++) nMax = Math.max(nMax, nums[i]);
        
        int nRadix = 10;
        int nBase = 10;
        int nDivisor = 1;
        
        int aux[] = new int[n];
        
        //Sort the array  (Small --> Large)
        while (nMax / nDivisor > 0) {
            int[] narrCount = new int[nRadix];  //count of numbers in each position digit
            
	    // In 1st round of "while", this "for" process the last digit, i.e. sort the numbers based on last digit
            // In 2nd round, it will process and sort based on 10th position digit; and then 100th position digit
            for (i=0; i<n; i++) narrCount[(nums[i] % nBase)/nDivisor]++;
            
	    //This step make sure the next step aux could operate on globe index
            for (i=1; i<nRadix; i++) narrCount[i] = narrCount[i] + narrCount[i-1];   
	     
            //Put nums[i] to the new position based on this round of sorting
	    //i.e. based on the position digit of this round
            for (i=n-1; i>=0; i--) aux[--narrCount[(nums[i] % nBase)/nDivisor]] = nums[i];
            
            for (i=0; i<n; i++) nums[i] = aux[i];
            
            nBase = nBase*10;
            nDivisor = nDivisor*10;
        }
        
        int nMaxGap = 0;
        
        for (i=1; i<n; i++) nMaxGap = Math.max(nMaxGap, aux[i]-aux[i-1]);
        
        return nMaxGap;
    }
    
    
    //ACC: 72%
    public int maximumGap_RadixSortA(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int n = nums.length;
        int base = 10;
        int factor = 1;
        int i;
        int nMax = Integer.MIN_VALUE;
        int maxGap = Integer.MIN_VALUE;
        int[] aux = new int[n];
        
        for (i=0; i<n; i++) nMax = Math.max(nMax, nums[i]);
        
        while (nMax/factor > 0) {
            int[] narrRadixCnt  = new int[10];
            
            for (i=0; i<n; i++) narrRadixCnt[(nums[i] % base)/factor]++;
            
            for (i=1; i<=9; i++) narrRadixCnt[i] = narrRadixCnt[i] + narrRadixCnt[i-1];
            
            for (i=n-1; i>=0; i--) aux[--narrRadixCnt[(nums[i] % base)/factor]] = nums[i];
            
            for (i=0; i<n; i++) nums[i] = aux[i];
            
            base = base * 10;
            factor = factor * 10;
        }

        for (i=1; i<n; i++) maxGap = Math.max(maxGap, nums[i] - nums[i-1]);
        
        return maxGap;
    }
	
}
