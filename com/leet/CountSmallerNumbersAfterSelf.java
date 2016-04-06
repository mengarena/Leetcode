package com.leet;

import java.util.ArrayList;
import java.util.List;

//You are given an integer array nums and you have to return a new counts array. 
//The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
//
//Example:
//
//Given nums = [5, 2, 6, 1]
//
//To the right of 5 there are 2 smaller elements (2 and 1).
//To the right of 2 there is only 1 smaller element (1).
//To the right of 6 there is 1 smaller element (1).
//To the right of 1 there is 0 smaller element.
//Return the array [2, 1, 1, 0].


//Google
public class CountSmallerNumbersAfterSelf {
	
	public class TreeNodeS {
		int val;
		TreeNodeS left;
		TreeNodeS right;
		int nCount;   //Number of node in left child tree (including itself)
		TreeNodeS(int x) { val = x; nCount = 1;}
	}
	
	public CountSmallerNumbersAfterSelf() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
//		int[] nums = {5,2,6,1};
		int[] nums = {5,2};

//		int[] nums = {-1,-1};

		List<Integer> lstCounts = countSmaller(nums);
		for (Integer nCount:lstCounts) System.out.print(nCount+",");
	}

	
	public List<Integer> countSmaller(int[] nums) {
		List<Integer> lstCounts = new ArrayList<Integer>();
		if (nums == null || nums.length == 0) return lstCounts;
		int n = nums.length;
		int i;
		int nSmallerCount;
		
		TreeNodeS root = new TreeNodeS(nums[n-1]);
		lstCounts.add(0);
		
		for (i=n-2; i>=0; i--) {
			nSmallerCount = insertTree(root, nums[i]);
			lstCounts.add(0, nSmallerCount);
		}
	
		
		return lstCounts;
	}
	
	
	private int insertTree(TreeNodeS root, int nNum) {
		int nCount = 0;
		
		while (true) {
			if (root.val >= nNum) {
				root.nCount++;
				
				if (root.left == null) {
					root.left = new TreeNodeS(nNum);
					break;
				} else {
					root = root.left;
				}
				
			} else if (root.val < nNum) {
				nCount = nCount + root.nCount;
				
				if (root.right == null) {
					root.right = new TreeNodeS(nNum);
					break;
				} else {
					root = root.right;
				}
			}
		}
		
		return nCount;
	}

/*	
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> lstCounts = new ArrayList<Integer>();
        List<Integer> lstSortedNums = new ArrayList<Integer>();
        
        if (nums == null || nums.length == 0) return lstCounts;
        int n = nums.length;
        int i;
        int nLeft, nRight, nMiddle;
        
        lstSortedNums.add(nums[n-1]);
        lstCounts.add(0);
        
        for (i=n-2; i>=0; i--) {
        	
        	nLeft = 0;
        	nRight = lstSortedNums.size();
        	
        	while (nLeft < nRight) {
        		nMiddle = (nLeft+nRight)/2;
        		if (lstSortedNums.get(nMiddle) >= nums[i]) {
        			nRight = nMiddle;
        		} else {
        			nLeft = nMiddle+1;        			
        		}
        	}
        	        	
       		lstCounts.add(0, nRight);
        	lstSortedNums.add(nRight, nums[i]);        		
        }
        
        return lstCounts;
    }
*/
	
}
