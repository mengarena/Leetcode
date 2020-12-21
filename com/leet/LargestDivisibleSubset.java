package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Given a set of distinct positive integers, 
//find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
//
//If there are multiple solutions, return any subset is fine.
//
//Example 1:
//nums: [1,2,3]
//Result: [1,2] (of course, [1,3] will also be ok)
//
//Example 2:
//nums: [1,2,4,8]
//Result: [1,2,4,8]

//Google
public class LargestDivisibleSubset {

	public LargestDivisibleSubset() {
		// TODO Auto-generated constructor stub
	}

	
	
	//ACC: 97%
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> lstSubset = new ArrayList<>();
        if (nums == null || nums.length == 0) return lstSubset;        
        int n = nums.length;
        int i, j;
        int[] dp = new int[n];   //Size of valid subset by nums[i]
        
        Arrays.sort(nums);
        
        dp[0] = 1;
        
        for (i=1; i<n; i++) {
            for (j=i-1; j>=0; j--) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = dp[j] + 1;
                    break;
                }
            }
        }
        
        int maxLenIdx = 0;
        
        //Find the index of the largest element of the longest subset
        for (i=1; i<n; i++) {
            maxLenIdx = dp[i] > dp[maxLenIdx]? i:maxLenIdx;
        }
        
        int tmpNum = nums[maxLenIdx];
        
        //Find the subset
        for (i=maxLenIdx; i>=0; i--) {
            if (tmpNum % nums[i] == 0) {
                lstSubset.add(0, nums[i]);
                tmpNum = nums[i];   //important
            }
        }
        
        return lstSubset;
    }
	
	
	
	
	//Exceed Time Limit:  Test case:  [2,3,5,7,11,13,17,19,23,31,1000000007]
    public List<Integer> largestDivisibleSubsetA(int[] nums) {
        List<Integer> lstSubset = new ArrayList<>();
        if (nums == null || nums.length == 0) return lstSubset;
        if (nums.length == 1) {
            lstSubset.add(nums[0]);
            return lstSubset;
        }
        
        int n = nums.length;
        int i;
        int nMaxLen = 0;
        int baseNum = 0;
        int facted = 0;
        int newNum = 0;
        boolean bExist = false;
        Set<Integer> setNum = new HashSet<Integer>();
        
        Arrays.sort(nums);
        
        for (i=0; i<n; i++) setNum.add(nums[i]);
        
        for (i=0; i<n-1; i++) {
            List<Integer> lstSubTmp = new ArrayList<Integer>();
            baseNum = nums[i];
            
            lstSubTmp.add(baseNum);
            bExist = false;
            facted = 2; 
            newNum = baseNum*facted;
            
            while (newNum <= nums[n-1]) {
                if (setNum.contains(newNum)) {
                    lstSubTmp.add(newNum);
                    baseNum = newNum;
                    bExist = true;
                } else {
                    bExist = false;
                }
                
                if (bExist == true) {
                    facted = 2;
                } else {
                    facted++;
                }
                
                newNum = baseNum*facted;
            }
            
            if (nMaxLen < lstSubTmp.size()) {
                nMaxLen = Math.max(nMaxLen, lstSubTmp.size());
                lstSubset = new ArrayList<Integer>(lstSubTmp);
            }
        }
        
        return lstSubset;
    }
}
