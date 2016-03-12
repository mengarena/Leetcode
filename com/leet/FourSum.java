package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
//Find all unique quadruplets in the array which gives the sum of target.
//
//Note:
//Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ¡Ü b ¡Ü c ¡Ü d)
//The solution set must not contain duplicate quadruplets.
//    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
//
//    A solution set is:
//    (-1,  0, 0, 1)
//    (-2, -1, 1, 2)
//    (-2,  0, 0, 2)

public class FourSum {

	public FourSum() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//int[] nums = {1, 0, -1, 0, -2, 2, 0, -2, 2};
		int[] nums = {-1,2,2,-5,0,-1,4};
		int target = 3;
		
		List<List<Integer>> lstlstFour = fourSum(nums, target);
		
		for (List<Integer> lstFour:lstlstFour) {
			System.out.print("(");
			for (Integer nVal:lstFour) {
				System.out.print(nVal + ",");
			}
			System.out.println(")");
		}
	}

	
	//Strategy:  borrow the same idea from ThreeSumClosest.java
    public List<List<Integer>> fourSum(int[] nums, int target) {
    	List<List<Integer>> lstlstFour = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) return lstlstFour;
        Arrays.sort(nums);
        int n = nums.length;
        int i, j;
        
        for (i=0; i<n-3;i++) {  //First element
        	if (nums[i] > target/4) break;
        	if (i > 0 && nums[i] == nums[i-1]) continue;   //At each postion, one value should only be used once to make sure the final set is unique
        	
        	for (j=i+1; j<n-2; j++) {
        		if (nums[j] > (target-nums[i])/3) break;
        		if (j > i+1 && nums[j] == nums[j-1]) continue;
        		
        		List<List<Integer>> lstlstTwoSum = twoSum(nums, j+1, target - nums[i] - nums[j]);
        		if (lstlstTwoSum.size() > 0) {
        			for (List<Integer> lstFourSum:lstlstTwoSum) {
        				lstFourSum.add(0, nums[j]);
        				lstFourSum.add(0, nums[i]);
        				lstlstFour.add(lstFourSum);
        			}
        		}
        	}
        }
        
        return lstlstFour;
    }

    
    private List<List<Integer>> twoSum(int[] nums, int nStartPos, int nTarget) {
    	List<List<Integer>> lstlstTwo = new ArrayList<List<Integer>>();
    	int n = nums.length;
    	int nLeft = nStartPos;
    	int nRight = n-1;
    	
    	while (nLeft < nRight) {
    		if (nums[nLeft] + nums[nRight] == nTarget) {
    			List<Integer> lstTwo = new ArrayList<Integer>();
    			lstTwo.add(nums[nLeft]);
    			lstTwo.add(nums[nRight]);
    			lstlstTwo.add(lstTwo);

    			while (nLeft+1 < n && nums[nLeft] == nums[nLeft+1]) nLeft++;

    			nLeft++;

    			while (nRight-1 >= nStartPos && nums[nRight] == nums[nRight-1]) nRight--;

    			nRight--;
    			
    		} else if (nums[nLeft] + nums[nRight] < nTarget) {
    			while (nLeft+1 < n && nums[nLeft] == nums[nLeft+1]) nLeft++;
    			nLeft++;
    		} else { //nums[nLeft] + nums[nRight] > nTarget
    			while (nRight-1 >= nStartPos && nums[nRight] == nums[nRight-1]) nRight--;
    			nRight--;
    		}
    	}
    	
    	return lstlstTwo;
    }
	
/*	Function is correct, but exceeded time limit
    public List<List<Integer>> fourSum(int[] nums, int target) {
    	List<List<Integer>> lstlstFour = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) return lstlstFour;
        Arrays.sort(nums);
        
        lstlstFour = fourSum(nums,0,4,target);
        
        return lstlstFour;
    }
	
    
    public List<List<Integer>> fourSum(int[] nums, int nStartPos, int nRemainedCnt, int target) {
    	List<List<Integer>> lstlstFour = new ArrayList<List<Integer>>();
    	int n = nums.length;
    	
    	if (nRemainedCnt == 0) return lstlstFour;
    	if (nStartPos + nRemainedCnt > n) return lstlstFour;
    	
    	for (int i=nStartPos; i<=n-nRemainedCnt; i++) {
    		if (nums[i] > target/nRemainedCnt) break;
    		
    		if (nRemainedCnt-1 > 0) {
    			List<List<Integer>> lstlstFourTmp = fourSum(nums, i+1, nRemainedCnt-1, target-nums[i]);
    			if (lstlstFourTmp.size() > 0) {
    				for (List<Integer> lstFourTmp:lstlstFourTmp) {
    					lstFourTmp.add(0, nums[i]);
    					if (nRemainedCnt == 4) {
    						if (!lstlstFour.contains(lstFourTmp)) lstlstFour.add(lstFourTmp);
    					} else {
    						lstlstFour.add(lstFourTmp);
    					}
    				}
    			}
    		} else {
    			if (nums[i] == target) {
    				List<Integer> lstFour = new ArrayList<Integer>();
    				lstFour.add(nums[i]);
    				lstlstFour.add(lstFour);
    			}
    		}
    		
    	}
    	
    	return lstlstFour;
    }
*/    
    
}
