package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given an array of non-negative integers, you are initially positioned at the first index of the array.
//
//Each element in the array represents your *maximum* jump length at that position.
//
//Determine if you are able to reach the last index.
//
//For example:
//A = [2,3,1,1,4], return true.
//
//A = [3,2,1,0,4], return false.

//Microsoft
public class JumpGame {

	public JumpGame() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		//int[] nums = {3,2,1,0,4};
//		int[] nums = {2,3,1,1,4};
		int[] nums = new int[25000+3];
		int i;
		for (i=25000; i>=1; i--) {
			nums[25000-i] = i;
		}
		
		nums[25000] = 1;
		nums[25001] = 0;
		nums[25002] = 0;
		
		System.out.println("Can Jump = " + canJump(nums));
	}
	
	public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        int i;
        if (n == 1) return true;
        int nReachableFarestPos = 0;    //From position i, which is the farthest position it could go
        
        for (i=0; i<=n-1; i++) {
        	if (i > nReachableFarestPos || nReachableFarestPos >= n-1) break;
        	nReachableFarestPos = Math.max(nReachableFarestPos, i+nums[i]);
        }
        
        if (i > nReachableFarestPos) return false;
        
        return true;
	}
	
	
	
	
	/* Works, but time out
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        
        if (n == 1) return true;
        int i,j;
        int nLen;
        int nPos = 0;
        int nVal;
//        int nInsertPos;
        int nMaxVal;
        
        List<Integer> lstPos = new ArrayList<Integer>();
        
        if (nums[0] >= n-1) return true;
        
        for (i=1; i<=nums[0]; i++) {
        	lstPos.add(i);
        }
        
        while (lstPos.size() > 0) {
        	nLen = lstPos.size();
        	nMaxVal = 0;
        	
        	for (j=0; j<nLen; j++) {
            	nPos = lstPos.remove(0);
            	if (nPos >= n - 1) return true;
            	
            	nVal = nPos + nums[nPos];
            	
            	if (nVal >= n - 1) return true;
            	
            	nMaxVal = Math.max(nMaxVal, nVal);
        	}
        	
//        	for (j=0; j<nMaxVal; j++) {
//        		//nPos = lstPos.remove(0);   //Jump from this position
//        		if (nPos == n-1) {
//        			return true; 
//        		} else if (nPos < n-1) {
//        			nVal = nums[nPos];
//        		} else {
//        			break;
//        		}
//        	}	
        	
        	
    		for (i=nPos+1; i<=nMaxVal; i++) {
    			//if (i == n - 1) return true;
    			lstPos.add(i);
//    			if (nPos + i < n - 1) {
//    			nInsertPos = getInsertPos(lstPos, i);
//    			if (nInsertPos != -1) lstPos.add(nInsertPos, i);
 //   			}
    		}
        	
        }
        
        return false;
    }
*/
    
    public int getInsertPos(List<Integer> lstNum, int nNewNum) {
    	int nInsertPos = 0;
    	int n = lstNum.size();
    	
    	if (n == 0) return nInsertPos;
    	int i,j,nMiddle;
    	
    	i = 0; j = n - 1;
    	
    	if (nNewNum > lstNum.get(n-1)) return n;
    	
    	while (i <= j) {
    		nMiddle = (i+j)/2;
    		
    		if (lstNum.get(nMiddle) > nNewNum) {
    			j = nMiddle - 1;
    		} else if (lstNum.get(nMiddle) < nNewNum) {
    			i = nMiddle + 1;
    		} else {
    			return -1;
    		}
    	}
    	
    	return i;
    }
	
}
