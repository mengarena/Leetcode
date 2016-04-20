package com.leet;

//Note: This is an extension of House Robber.
//
//After robbing those houses on that street, 
//the thief has found himself a new place for his thievery so that he will not get too much attention. 
//This time, all houses at this place are arranged in a circle. 
//That means the first house is the neighbor of the last one. 
//Meanwhile, the security system for these houses remain the same as for those in the previous street.
//
//Given a list of non-negative integers representing the amount of money of each house, 
//determine the maximum amount of money you can rob tonight without alerting the police.


//Microsoft
public class HouseRobberII {

	public HouseRobberII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] nums = {1,2,1,1};
		
		System.out.println("Max Amount = " + rob(nums));
	}
	
	//Correct
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        if (n == 1) return nums[0];
        int nDP0[] = new int[n];   //Possibly include first element but not last element
        int nDP1[] = new int[n];   //Possibly include last element but not first element
        int i;
        
        nDP0[0] = nums[0];
        
        if (n > 1) {
        	nDP0[1] = Math.max(nDP0[0], nums[1]);
        	
        	for (i=2; i<n-1; i++) {
        		nDP0[i] = Math.max(nDP0[i-1], nDP0[i-2]+nums[i]);
        	}
        }
        
        
        nDP1[0] = 0;
        if (n > 1) {
        	nDP1[1] = nums[1];
        	for (i=2; i<n; i++) {
        		nDP1[i] = Math.max(nDP1[i-1], nDP1[i-2]+nums[i]);
        	}
        }
        
        return Math.max(nDP0[n-2], nDP1[n-1]);  //Pay attention
    }

	
    /*  CORRECT
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        if (n == 1) return nums[0];
        int nDP0[] = new int[n];   //Possibly inlucde first element but not last element
        int nDP1[] = new int[n];   //Possibly include last element but not first element
        int i;
        
        nDP0[0] = nums[0];
        
        if (n > 1) {
        	nDP0[1] = Math.max(nDP0[0], nums[1]);
        	
        	for (i=2; i<n-1; i++) {
        		nDP0[i] = Math.max(nDP0[i-1], nDP0[i-2]+nums[i]);
        	}
        }
        
        
        nDP1[0] = nums[1];
        if (n > 2) {
        	nDP1[1] = Math.max(nDP1[0], nums[2]);
        	for (i=3; i<n; i++) {
        		nDP1[i-1] = Math.max(nDP1[i-2], nDP1[i-3]+nums[i]);
        	}
        }
        
        return Math.max(nDP0[n-2], nDP1[n-2]);
        
    } 
    */  
}
