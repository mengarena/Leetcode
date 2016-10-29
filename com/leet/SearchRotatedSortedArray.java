package com.leet;

//Suppose a sorted array is rotated at some pivot unknown to you beforehand.
//
//(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
//
//You are given a target value to search. If found in the array return its index, otherwise return -1.
//
//You may assume no duplicate exists in the array.

//Facebook, Uber, Linkedin, Bloomberg, Microsoft
//Hard
public class SearchRotatedSortedArray {

	public SearchRotatedSortedArray() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		int[] nums = {5,7,8,0,3,4};
//		int[] nums = {5,7};
//		int[] nums = {7,8,1,2,3,4,5,6};
		int[] nums = {6};

		int target = 6;
		
		System.out.println("Index of Target: " + search(nums, target));
	}
	
	
	//ACC:  O(logN)
	public int searchK(int A[], int target) {
		int n = A.length;
        int lo=0,hi=n-1;
        // find the index of the smallest value using binary search.
        // Loop will terminate since mid < hi, and lo or hi will shrink by at least 1.
        // Proof by contradiction that mid < hi: if mid==hi, then lo==hi and loop would have been terminated.
        while(lo<hi){
            int mid=(lo+hi)/2;
            if(A[mid]>A[hi]) lo=mid+1;
            else hi=mid;
        }
        // lo==hi is the index of the smallest value and also the number of places rotated.
        int rot=lo;
        
        lo=0;  
        hi=n-1;
        
        // The usual binary search and accounting for rotation.
        while (lo<=hi) {
            int mid=(lo+hi)/2;
            int realmid=(mid+rot)%n;
            if(A[realmid]==target)  return realmid;
            if(A[realmid]<target) lo=mid+1;
            else hi=mid-1;
        }
        return -1;
    }
	
	
	//ACC: O(logN)
	public int searchKK(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;
        
            if (nums[start] <= nums[mid]){
                 if (target < nums[mid] && target >= nums[start]) 
                    end = mid - 1;
                 else
                    start = mid + 1;
            } 
        
            if (nums[mid] <= nums[end]){
                if (target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                 else
                    end = mid - 1;
            }
        }
        return -1;
    }
	
	
	//ACC
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        int i=0, j=n-1;
        int mid;
        
        while (i < j) {
            mid = i + (j-i)/2;
            
            if (nums[mid] > nums[j]) {
                i = mid+1;
            } else if (nums[mid] < nums[j]) {
                j = mid;
            }
        }
        
        if (j > 0) {
            if (nums[0] <= target && target <= nums[j-1]) return searchPos(nums, 0, j-1, target);
            
            if (nums[j] <= target && target <= nums[n-1]) return searchPos(nums, j, n-1, target);
        } else {
            return searchPos(nums, 0, n-1, target);
        }
        
        return -1;
    }
    
    private int searchPos(int[] nums, int start, int end, int target) {
        if (start == end) {
            if (nums[start] == target) return start;
            return -1;
        }
        
        int mid;
        
        while (start <= end) {
            mid = start + (end-start)/2;
            
            if (nums[mid] == target) return mid;
            
            if (nums[mid] > target) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        
        return -1;
    }	
	
    
    
	
	//ACC
    public int searchA(int[] nums, int target) {
        int i,j;
        int nMiddle;
        int n;
        
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) {
        	if (nums[0] == target) {
        		return 0;
        	} else {
        		return -1;
        	}
        }
        
        n = nums.length;
        
        //Find the pivot
        i = 1; j = n-1;
        while (nums[i] > nums[j] || i < j) {
        	nMiddle = (i+j)/2;
        	
        	if (nums[nMiddle] > nums[0]) {
        		i = nMiddle + 1;
        	} else {
        		j = nMiddle - 1;
        	}
        }
        
        if (i < n -1 && nums[i] > nums[i+1]) {
        	nMiddle = i + 1;
        } else {
        	nMiddle = i;
        }
                
        if (target >= nums[nMiddle] && target <= nums[n-1]) {
        	i = nMiddle;
        	j = n - 1;
        } else {
        	i = 0;
        	j = nMiddle-1;
        }
        
        while (i<=j) {
        	nMiddle = (i+j)/2;
        	
        	if (nums[nMiddle] == target) {
        		return nMiddle;
        	} else if (nums[nMiddle] < target) {
        		i = nMiddle + 1;
        	} else {
        		j = nMiddle - 1;
        	}
        			
        }
        
        
        return -1;
    }
	
}
