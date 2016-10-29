package com.leet;

//Given an unsorted integer array, find the first missing positive integer.
//
//For example,
//Given [1,2,0] return 3,
//and [3,4,-1,1] return 2.
//
//Your algorithm should run in O(n) time and uses constant space.

//Hard
public class FirstMissingPositive {

	public FirstMissingPositive() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int nums[] = {0};
		
		System.out.println("Missing = " + firstMissingPositive(nums));
	}
	
    //AC: Strategy:  each positive should be in its position, i.e. in an array (element <= 0  or > n are ignored). 
	//the positive number should be in the position of val - 1
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        int n = nums.length;
        int nMissing = 0;
        int i;
        
        for (i=0; i<n; i++) {
            if (nums[i] <= 0 || nums[i] > n) continue;
            
            int nVal = nums[i];
            int nTargetPos = nVal-1;
            int nCurPos = i;
            
            
            while (nCurPos != nTargetPos) {
                int nTmp = nums[nTargetPos];
                nums[nTargetPos] = nVal;
                
                if (nTmp <= 0 || nTmp > n) break;
                
                nCurPos = nTargetPos;
                nTargetPos = nTmp-1;
                nVal = nTmp;
            }
        }
       
        for (i=0; i<n; i++) {
            if (nums[i] != i+1) {
                nMissing = i+1;
                break;
            }
        }
        
        if (nMissing == 0) nMissing = n+1;
        
        return nMissing;
    }
	
    
	//AC:  Use buckets. Bucket size is 10, in each bucket, store: base + 1, base + 2, base + 3.....base + 9, base + 10
	//base 1~10 are combined into a number using bit (use 10 bits to remember whether each one occurs), if all the 10 is there, the result value should be 0x03FF
	//If not 0x03FF, some number is missing
    public int firstMissingPositiveA(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        int n = nums.length;
        int nMissing = 0;
        int nBucketSize = 10;
        int i;
        int nMax = Integer.MIN_VALUE;
        
        for (i=0; i<n; i++) nMax = Math.max(nMax, nums[i]);
        
        if (nMax <= 0) return 1;
        
        int nBucketCnt = (int)Math.ceil(nMax*1.0/nBucketSize);
        
        int[] narrBucket = new int[nBucketCnt];
        
        for (i=0; i<n; i++) {
        	if (nums[i] <= 0) continue;
        	int nIdx = (int)Math.ceil(nums[i]*1.0/nBucketSize)-1;
        	narrBucket[nIdx] = narrBucket[nIdx] | (1 << ((nums[i] + 9) % nBucketSize));
        }
        
        for (i=1; i<=nBucketCnt; i++) {
        	if (narrBucket[i-1] != 0x03FF) {
        		int nIdx = 1;
        		while (narrBucket[i-1] > 0) {
        			if ((narrBucket[i-1] % 2) == 0) {
        				nMissing = nBucketSize*(i-1) + nIdx;
        				break;
        			} else {
        				nIdx++;
        				narrBucket[i-1] = narrBucket[i-1] >> 1;
        			}
        		}
        		
        		if (nMissing == 0) nMissing = nBucketSize*(i-1) + nIdx;
        		
        		break;
        	}
        }
        
        //In case, the nums is perfectly 10 times (e.g. 1,2,3....100), the above for loop does not set the nMissing value
        if (nMissing == 0) nMissing = Math.max(1, nMax+1);
        
        return nMissing;
    }
	
}

