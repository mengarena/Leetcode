package com.leet;

//Given a sorted array and a target value, return the index if the target is found. 
//If not, return the index where it would be if it were inserted in order.
//
//You may assume no duplicates in the array.
//
//Here are few examples.
//[1,3,5,6], 5 ¡ú 2
//[1,3,5,6], 2 ¡ú 1
//[1,3,5,6], 7 ¡ú 4
//[1,3,5,6], 0 ¡ú 0

public class SearchInsertPosition {

	public SearchInsertPosition() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int[] nums = {1,3,5,8, 10};
		
		int n = 9;
		
		System.out.println("Insert Position: " + searchInsert(nums, n));
	}

	
	//ACC
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int left = 0, right = n-1;
        int mid;
        
        while (left < right) {
            mid = (left + right)/2;
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        if (nums[left] == target) {
            return left;
        } else if (nums[left] > target) {
            return left;
        } else {
            return left+1;
        }
    }	
	
    
	//ACC
    public int searchInsertA(int[] nums, int target) {
        int nPos = 0;
        
        if (nums == null || nums.length == 0) return 0;
        if (nums[0] >= target) return 0;
        
        int n = nums.length;
        
        if (nums[n-1] == target) return n-1;
        if (nums[n-1] < target) return n;
        
        int nStartPos = 0;
        int nEndPos = n - 1;
        int nMiddlePos = 0;
        
        while (nStartPos <= nEndPos) {
        	nMiddlePos = (nStartPos + nEndPos)/2;
        	
        	if (nums[nMiddlePos] == target) {
        		nPos = nMiddlePos;
        		break;
        	} else if (nums[nMiddlePos] > target) {
        		nEndPos = nMiddlePos - 1;
        	} else {  //nums[nMiddlePos] < target
        		nStartPos = nMiddlePos + 1;
        	}
        	
        	if (nums[nStartPos] >= target) {
        		nPos = nStartPos;
        		break;
        	}

        	if (nums[nEndPos] < target) {
        		nPos = nEndPos + 1;
        		break;
        	}
       	
        }
                
        return nPos;
    }
	
}
