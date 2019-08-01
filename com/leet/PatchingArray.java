package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given a sorted positive integer array nums and an integer n, 
//add/patch elements to the array such that any number in range [1, n] 
//inclusive can be formed by the sum of some elements in the array. 
//Return the minimum number of patches required.
//
//Example 1:
//nums = [1, 3], n = 6
//Return 1.
//
//Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
//Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
//Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
//So we only need 1 patch.
//
//Example 2:
//nums = [1, 5, 10], n = 20
//Return 2.
//The two patches can be [2, 4].
//
//Example 3:
//nums = [1, 2, 2], n = 5
//Return 0.

//Google
//Hard
public class PatchingArray {

	public PatchingArray() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] nums = {1,3};
		int n = 6; //Integer.MAX_VALUE;
		
		System.out.println("minPatches = " + minPatches(nums, n));
	}
	
	
	//ACC: 12%  (1 ms)
	public int minPatches(int[] nums, int n) {
	    int nLen = nums.length;
	    int nCumSum = 1;  //The up bound (exclusive) of current cumulative sum 
		              //(i.e. the sum of current numbers in nums[] plus patched); 
		              //here nCumSum means all numbers within [1, nCumSum) 
		              //could be expressed as sum with numbers in original nums[] and already patched numbers
	    int nNumIdx = 0;
	    int nPatchCnt = 0;
				
	    while (nNumIdx <= nLen-1 || nCumSum <= n) {	
		if (nNumIdx <= nLen-1) {
		    if (nums[nNumIdx] > nCumSum) { //i.e. nums[nNumIdx] larger than the number going to be patched
			nPatchCnt++;   //Path nCumSum as a new number
			if (n - nCumSum < nCumSum) break;
			nCumSum = nCumSum*2;    //Cumulative sum increase to nCumSum+nCumSum
		    } else {
			if (n - nCumSum < nums[nNumIdx]) break;
			nCumSum = nCumSum + nums[nNumIdx];  //Cumulative sum increase to nCumSum + nums[nNumIdx]
			nNumIdx++;
		    }

		} else {			
		    nPatchCnt++;   //Path nCumSum as a new number
		    if (n - nCumSum < nCumSum) break;
		    nCumSum = nCumSum*2;    //Cumulative sum increase to nCumSum+nCumSum (i.e. this round patch nCumSum)
		}
	   }
		
	   return nPatchCnt;
      }
	

	//ACC: 12%  (1 ms)
    public int minPatchesA(int[] nums, int n) {
        int cnt = nums.length;
        int patchCnt = 0;
        int cumSum = 0;
        int nextNum = 1;
        int patchedNum = 0;
        int idx = 0;
        
        while (cumSum < n && idx < cnt) {
            if (cumSum > nextNum - nums[idx]) {
                if (nextNum < nums[idx]) {
                    patchCnt++;
                    patchedNum = nextNum;
                    
                    if (cumSum >= n - nextNum) return patchCnt;
                    
                    cumSum = cumSum + nextNum;
                    nextNum = cumSum + 1;
                } else {
                    if (cumSum >= n - nums[idx]) return patchCnt;
                    cumSum = cumSum + nums[idx];
                    idx++;
                    nextNum = cumSum + 1;
                }
            } else if (cumSum == nextNum - nums[idx]) {
                cumSum = nextNum;
                idx++;
                nextNum = cumSum + 1;
            } else {
                if (cumSum >= n - nums[idx]) return patchCnt;
                cumSum = cumSum + nums[idx];
                idx++;
            }
            
        }
        
        if (cumSum >= n) return patchCnt;
        
        while (cumSum < n) {
            patchedNum = nextNum;
            patchCnt++;
            
            if (cumSum >= n - patchedNum) break;
            
            cumSum += patchedNum;
            nextNum = cumSum+1;
        }
        
        return patchCnt;
    }
	
	
	
/*	
	//Exceed memory limit
    public int minPatches(int[] nums, int n) {
        int[] narrSum = new int[n];
        int i;
        int nPatchCnt = 0;
        int nLen = nums.length;
        List<Integer> lstNums = new ArrayList<Integer>();
        
        for (i=0; i<n; i++) narrSum[i] = 0;
        
        List<Integer> lstSums = getSums(nums, n);
        
        for (Integer nPos:lstSums) narrSum[nPos-1] = 1;
        
        List<Integer> lstEmpty = new ArrayList<Integer>();
        
        for (i=0; i<n; i++) {
        	if (narrSum[i] == 0) lstEmpty.add(i+1);
        }
        
        for (i=0; i<nLen; i++) lstNums.add(nums[i]);
        
        patchArray(lstNums, narrSum, lstEmpty, n);
        
        nPatchCnt = lstNums.size() - nLen;
        
        return nPatchCnt;
    }
    
    
    private void patchArray(List<Integer> lstNums, int[] narrSum, List<Integer> lstEmpty, int n) {
    	if (lstEmpty == null || lstEmpty.size() == 0) return;
    	
    	int nEmpty = lstEmpty.remove(0);
    	int i;
    	
    	List<Integer> lstPos = new ArrayList<Integer>();
    	for (i=0; i<n; i++) {
    		if (narrSum[i] == 1) lstPos.add(i);
    	}
    	
    	for (Integer nPos:lstPos) {
    		if (nPos <= n - nEmpty) {
				narrSum[nPos+nEmpty-1] = 1;
				int nIdx = lstEmpty.indexOf(nPos + nEmpty+1);
				if (nIdx != -1) lstEmpty.remove(nIdx);    
    		}
    	}
    	    	
    	narrSum[nEmpty-1] = 1;
    	lstNums.add(nEmpty);
    	
    	patchArray(lstNums, narrSum, lstEmpty, n); 
    }
    
    
    
    private List<Integer> getSums(int[] nums, int n) {
    	int nLen = nums.length;
    	int i;
    	List<Integer> lstSums = new ArrayList<Integer>();
    	
    	for (i=1; i<=nLen; i++) {
    		List<Integer> lstSumsTmp = getSumsGivenCnt(nums, 0, n, i);
    		for (Integer nSumTmp:lstSumsTmp) lstSums.add(nSumTmp);
    	}
    	
    	return lstSums;
    }
	
    
    private List<Integer> getSumsGivenCnt(int[] nums, int nStartPos, int n, int nCnt) {
    	int nLen = nums.length;
    	int i;
    	List<Integer> lstSums = new ArrayList<Integer>();
    	
    	if (nStartPos+nCnt-1 > nLen-1) return lstSums;
    	
    	if (nCnt == 1) {
    		for (i=nStartPos; i<nLen; i++) {
    			if (nums[i] <=n) lstSums.add(nums[i]);
    		}
    		
    		return lstSums;
    	}
    	
    	for (i=nStartPos; i<=nLen-nCnt; i++) {
    		List<Integer> lstSumsTmp = getSumsGivenCnt(nums, i+1, n, nCnt-1);
    		
    		for (Integer nSum:lstSumsTmp) {
    			if (nSum <= n - nums[i]) lstSums.add(nSum+nums[i]);
    		}
    	}
    	
    	return lstSums;
    }
*/
    
    
    
///////////////////////////////////////////////////////////
/*
 
    public int minPatches(int[] nums, int n) {
        int[] narrSum = new int[n+1];
        int i;
        int nPatchCnt = 0;
        int nLen = nums.length;
        List<Integer> lstNums = new ArrayList<Integer>();
        
        for (i=0; i<=n; i++) narrSum[i] = 0;
        Arrays.sort(nums);
        
        List<Integer> lstSums = getSums(nums, n);
        
        for (Integer nPos:lstSums) narrSum[nPos] = 1;
        
        List<Integer> lstEmpty = new ArrayList<Integer>();
        
        for (i=1; i<=n; i++) {
        	if (narrSum[i] == 0) lstEmpty.add(i);
        }
        
        for (i=0; i<nLen; i++) lstNums.add(nums[i]);
        
        patchArray(lstNums, narrSum, lstEmpty, n);
        
        nPatchCnt = lstNums.size() - nLen;
        
        return nPatchCnt;
    }
    
    
    private void patchArray(List<Integer> lstNums, int[] narrSum, List<Integer> lstEmpty, int n) {
    	if (lstEmpty == null || lstEmpty.size() == 0) return;
    	
    	int nEmpty = lstEmpty.remove(0);
    	int i;
    	
    	List<Integer> lstPos = new ArrayList<Integer>();
    	for (i=1; i<=n; i++) {
    		if (narrSum[i] == 1) lstPos.add(i);
    	}
    	
    	for (Integer nPos:lstPos) {
    		if (nPos+nEmpty <= n) {
				narrSum[nPos+nEmpty] = 1;
				int nIdx = lstEmpty.indexOf(nPos + nEmpty);
				if (nIdx != -1) lstEmpty.remove(nIdx);    
    		}
    	}
    	    	
    	narrSum[nEmpty] = 1;
    	lstNums.add(nEmpty);
    	
    	patchArray(lstNums, narrSum, lstEmpty, n); 
    }
    
    
    
    private List<Integer> getSums(int[] nums, int n) {
    	int nLen = nums.length;
    	int i;
    	List<Integer> lstSums = new ArrayList<Integer>();
    	
    	for (i=1; i<=nLen; i++) {
    		List<Integer> lstSumsTmp = getSumsGivenCnt(nums, 0, n, i);
    		for (Integer nSumTmp:lstSumsTmp) lstSums.add(nSumTmp);
    	}
    	
    	return lstSums;
    }
	
    
    private List<Integer> getSumsGivenCnt(int[] nums, int nStartPos, int n, int nCnt) {
    	int nLen = nums.length;
    	int i;
    	List<Integer> lstSums = new ArrayList<Integer>();
    	
    	if (nStartPos+nCnt-1 > nLen-1) return lstSums;
    	
    	if (nCnt == 1) {
    		for (i=nStartPos; i<nLen; i++) {
    			if (nums[i] <=n) lstSums.add(nums[i]);
    		}
    		
    		return lstSums;
    	}
    	
    	for (i=nStartPos; i<=nLen-nCnt; i++) {
    		List<Integer> lstSumsTmp = getSumsGivenCnt(nums, i+1, n, nCnt-1);
    		
    		for (Integer nSum:lstSumsTmp) {
    			if (nSum+nums[i] <= n) lstSums.add(nSum+nums[i]);
    		}
    	}
    	
    	return lstSums;
    }
 
 
 *     
 */
    
    
}
