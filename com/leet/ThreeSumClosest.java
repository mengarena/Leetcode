package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
//Return the sum of the three integers. 
//You may assume that each input would have exactly one solution.
//
//For example, given array S = {-1 2 1 -4}, and target = 1.
//
//The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).


//Bloomberg
public class ThreeSumClosest {

	public ThreeSumClosest() {
		// TODO Auto-generated constructor stub
	}

	
						
	public void run() {
		//int[] nums = {-1,2,1,-4,3,-2,4,5,3,2,11,22,9,-5,-6,-7,-4,8,7,6,0};
//		int[] nums = {-12,-10,32,88,4,64,-57,-57,62,0,74,95,-23,10,-21,80,-74,36,-54,17,-97,-8,-86,43,95,-76,-18,-43,-43,31,-64,-96,-66,-42,-88,-44,-6,-2,16,-6,90,-45,52,48,-6,58,21,7,-18,73,-75,-90,-34,6,3,94,26,33,-92,73,-25,-67,16,-99,-90,-40,19,-78,-53,-36,28,82,33,66,-27,54,-34,-30,27,51,-32,-13,-52,37,-41,-95,68,56,23,57,25,-69,-65,43,-60,-41,-51,77,44,-6,-19,-87,-43,-54,97,82,-54,-13,82,43,-83,100,37,-34,-56,-65,-7,27,-25,-82,91,-76,46,-29,-78,69,-21,25,-10,71};
//		int[] nums = {70,-65,-24,-80,76,-46,-30,-100,-53,0,-81,-64,-51,-72,-73,-84,100,-38,-63,-46,-31,-96,35,-33,37,-29,-20,94,48,-18,6,63,-83,61,-50,-50,66,75,-6,-21,41,-62,75,66,87,9,85,64,71,44,98,-68,73,-97,-49,89,34,-51,38,-64,-32,83,-44,-15,36,1,20,29,-10,-37,-9,-49,-8,50,-82,-36,-20,63,30,-11,3,33,-82,-82,-20,51,78,71,65,-99,0,-58,53,-17,31,15,-7,-91,43,14,-23,91,97,-87,-81,39,85,-34,50,87,-46,81,83,8,9,20,19,-100,-37,-42,-2,31,-44,-91,83,40,-44,48,-58,-17,-45,-31,-80,-46,-14,67,35,-70,29,-4,-69,73,30,-72,43,87,59,84,-81,-88,-84,-78,17,96,39,-92,-76,19,51,-26,11,-13,63,41,93,5,-48,-26,63,44,53,42,-66,-100,17,-42,4,-65,-17,77,66,-51};
		int[] nums = {-16,-84,47,94,10,82,8,49,38,-5,31,-39,9,-30,49,-41,-57,18,66,-34,85,87,-54,-54,96,63,-59,-73,-7,-65,73,-87,62,-32,-49,69,13,-92,-57,92,38,45,21,-97,52,-51,99,-88,75,-20,-52,89,-10,-99,-92,52,9,92,46,58,-81,56,79,72,95,42,46,-44,-76,23,-98,63,-4,-2,-41,81,1,67,11,99,-96,85,36,31,36,45,-95,72,62,82,-33,-24,-31,-80,-96,14,-84,-100,94,-97,-16,-96,83,42,-40,46,17,-73,95,-23,96,-47,69,-30,-8,25,35,-1,-87,-27,-99,-66,-42,63,-91,-93,55,98,52,53,-21};

		int target = 143;
		
		System.out.println("Sum cloest to the target = " + threeSumClosest(nums, target));
	}

	
	public int threeSumClosest(int[] nums, int target) {
		int nMinGap = Integer.MAX_VALUE;
		int nGap = 0;
		if (nums == null || nums.length < 3) return nMinGap;
		int n = nums.length;
		int i;
		int nLeft, nRight;
		int nSum;
		int nClosestSum = Integer.MAX_VALUE;
		
		Arrays.sort(nums);
		
		for (i=0; i<=n-3; i++) {
			nLeft = i+1;
			nRight = n-1;
			
			while (nLeft < nRight) {
				nSum = nums[i] + nums[nLeft] + nums[nRight];
				nGap = nSum - target;
				if (Math.abs(nGap) < nMinGap) {
					nClosestSum = nSum;
					nMinGap = Math.abs(nGap);
				}
				
				if (nMinGap == 0) return nClosestSum;
				
				if (nGap > 0) {
					nRight = nRight - 1;
				} else {
					nLeft = nLeft + 1;
				}
				
			}
		}
		
		return nClosestSum;
	}
	
	
/* Works, but not efficient enough	
    public int threeSumClosest(int[] nums, int target) {
        int nSum = 0;
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int i;
        int nGap = Integer.MAX_VALUE;
        int nClosetSum = 0;
        int nPrevGap = Integer.MAX_VALUE;
        int nCurGap = Integer.MAX_VALUE;
        int nMean = (int) target/3;
        Arrays.sort(nums);
        
        if (n == 1) return nums[0];
        if (n == 2) return nums[0]+nums[1];
       
        nClosetSum = nums[0]+nums[1] + nums[2];
        
        for (i=0; i<=n-3; i++) {
        	if (nums[i] >= nMean) break;
        	List<Integer> lstTwoSum = twoSum(nums, i+1);
        	nPrevGap = Integer.MAX_VALUE;
        	for (Integer nTwoSum:lstTwoSum) {
	        	nSum = nums[i] + nTwoSum;
	        	nCurGap = Math.abs(nSum - target);
	        	if (nCurGap > nPrevGap) break;
	        	
	        	if (nCurGap < nGap) {
	        		nGap = Math.abs(nSum - target);
	        		nClosetSum = nSum;
	        	} 
	        	
	        	nPrevGap = nCurGap;
	        	
	        	if (nGap == 0) return target;
        	}
        }
        
        
        return nClosetSum;
    }
	
    
    public List<Integer> twoSum(int[] nums, int nStartPos) {
    	int n = nums.length;
    	int i,j;
    	List<Integer> lstTwoSum = new ArrayList<Integer>();
    	
    	for (i=nStartPos; i<=n-2; i++) {
    		for (j=i+1; j<=n-1; j++) {
    			lstTwoSum.add(nums[i]+nums[j]);
    		}
    	}
    	
    	return lstTwoSum;
    }
*/    
    
}
