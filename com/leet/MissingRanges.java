package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.
//
//For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
		
//Google
public class MissingRanges {

	public MissingRanges() {}
	
	public void run() {
		// TODO Auto-generated constructor stub
		int[] nums = {3};
		
		List<String> lstMissingRange = findMissingRanges(nums, 3, 4);
		
		System.out.println();
	}

	//AC: 12%
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> lstMissingRange = new ArrayList<String>();
        if (upper < lower) return lstMissingRange;
        
        if (nums == null || nums.length == 0) {
        	if (lower == upper) {
        		lstMissingRange.add(Integer.toString(lower));
        	} else {
        		lstMissingRange.add(lower + "->" + upper);
        	}   
        	
        	return lstMissingRange;
        }
        
        int n = nums.length;
        int nMin = 0;
        int nMax = 0;
        
        if (upper < nums[0] || lower > nums[n-1]) {
        	if (lower == upper) {
        		lstMissingRange.add(Integer.toString(lower));
        	} else {
        		lstMissingRange.add(lower + "->" + upper);
        	}
        	
        	return lstMissingRange;
        }
        
        if (lower < nums[0]) {
        	if (nums[0] - lower > 1) {
        		lstMissingRange.add(lower + "->" + (nums[0]-1));
        	} else {
        		lstMissingRange.add(Integer.toString(lower));
        	}
        } 
        
        for (int i=1; i<n; i++) {
        	nMin = Math.max(nums[i-1], lower);
        	nMax = Math.min(nums[i], upper);
        	
        	if (nMin+2 == nMax) {
        		if (nMin == nums[i-1]) {
        			lstMissingRange.add(Integer.toString(nMin + 1));
        		} else {
        			if (nMax == nums[i]) {
        				lstMissingRange.add(Integer.toString(nMin) + "->" + (nMax-1));
        			} else {
        				lstMissingRange.add(Integer.toString(nMin) + "->" + nMax);
        			}
        		}
        	} else if (nMax > nMin+2) {
        		if (nMin == nums[i-1]) {
        			if (nMax == nums[i]) {
        				lstMissingRange.add((nMin+1) + "->" + (nMax-1));
        			} else {
        				lstMissingRange.add((nMin+1) + "->" + nMax);
        			}
        		} else {
        			if (nMax == nums[i]) {
        				lstMissingRange.add(nMin + "->" + (nMax-1));
        			} else {
        				lstMissingRange.add(nMin + "->" + nMax);
        			}       			
        		}
        		
        	}
        }
        
        if (upper > nums[n-1]) {
        	if (upper - nums[n-1] > 1) {
        		lstMissingRange.add((nums[n-1] + 1) + "->" + upper);
        	} else if (upper - nums[n-1] == 1) {
        		lstMissingRange.add(Integer.toString(upper));
        	}
        }
        
        return lstMissingRange;
    }

}
