package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given an array of non-negative integers, you are initially positioned at the first index of the array.
//
//Each element in the array represents your maximum jump length at that position.
//
//Your goal is to reach the last index in the minimum number of jumps.
//
//For example:
//Given array A = [2,3,1,1,4]
//
//The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
//
//Note:
//You can assume that you can always reach the last index.

public class JumpGameII {

	public JumpGameII() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		//int[] nums = {2,3,1,1,4};
		//int[] nums = {5,6,4,4,6,9, 4,4,7,4,4,8,2,6,8, 1,5,9,6,5,2,7,9, 7,9,6,9,4,1,6,8,8, 4,4,2,0,3,8,5};
//		int[] nums = {5,6,4,4,6,9, 4,4,7,4,4,8,2,6,8, 1,5,9,6,5,2,7,9, 7,9,6,9,4,1,6,8,8, 4};
		//int[] nums = {2,3,4, 1,1,1,3, 1,1,1, 1};
		int[] nums = {2,3,1};
		
		int steps = jump(nums);
		
		System.out.println(steps);
	}

	
	//ACC:  57%
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int i;
        int basePos = 0;
        int nStepCnt = 0;
        int reachable = 0;
        int maxJump = Integer.MIN_VALUE;
        int possibleJump = 0;
        int nextPosAdd = 0;
        
        while (reachable < n-1) {
        	maxJump = Integer.MIN_VALUE;
        	
        	for (i=1; i<=nums[basePos]; i++) {
        		if (basePos + i >= n-1) {
        			nextPosAdd = i;
        			break;
        		}
        		
        		//Decide, which is the best next jump (largest jump)
        		//The next jump is based on:  next possible step + the value at that step,  the larger the better
        		possibleJump = i + nums[basePos+i];  
        		if (maxJump < possibleJump) {
        			maxJump = possibleJump;
        			nextPosAdd = i;
        		}
        	}
        	
        	reachable = reachable + nextPosAdd;
        	basePos = basePos + nextPosAdd;
        	nStepCnt++;
        }
        
        return nStepCnt;
    }
	
    
    
    
	
/*		
	//Works, But exceeded time limit
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int i;
        int basePos = 0;
        int nMinStepCnt = Integer.MAX_VALUE;
                
        for (i=1; i<=nums[0]; i++) {
        	int nMinStep = getMinSteps(nums, n-1, basePos, i);
        	nMinStepCnt = Math.min(nMinStepCnt, nMinStep);
        }
        
        return nMinStepCnt;
    }
   
    
    private int getMinSteps(int[] nums, int target, int basePos, int step) {
    	if (basePos + step >= target) return 1;
    	int i;
    	int num = nums[basePos+step];
    	
    	if (num == 0) return Integer.MAX_VALUE;
    	
    	int nMinStepCnt = Integer.MAX_VALUE;
    	
    	for (i=1; i<=num; i++) {
    	    int nMinStep = getMinSteps(nums, target, basePos+step, i);
    	    nMinStepCnt = Math.min(nMinStepCnt, nMinStep);
    	}
    	
    	if (nMinStepCnt == Integer.MAX_VALUE) {
    		return nMinStepCnt;
    	}
    	
    	return nMinStepCnt+1;
    }
*/
        
}
