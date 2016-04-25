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
		int[] nums = {1,2,5,77,81};
		
		List<String> lstMissingRange = findMissingRanges(nums, 1, 79);
		
		System.out.println();
	}

	//From others
	public List<String> findMissingRangesB(int[] a, int lo, int hi) {
		List<String> res = new ArrayList<String>();

		// the next number we need to find
		int next = lo;

		for (int i = 0; i < a.length; i++) {
			// not within the range yet
			if (a[i] < next)
				continue;

			// continue to find the next one
			if (a[i] == next) {
				next++;
				continue;
			}

			// get the missing range string format
			res.add(getRange(next, a[i] - 1));

			// now we need to find the next number
			next = a[i] + 1;
		}

		// do a final check
		if (next <= hi)  res.add(getRange(next, hi));

		return res;
	}

	
	String getRange(int n1, int n2) {
		return (n1 == n2) ? String.valueOf(n1) : String.format("%d->%d", n1, n2);
	}	
	
		
	//From others
	public List<String> findMissingRangesA(int[] nums, int lower, int upper) {
	    List<String> ranges = new ArrayList<String>();
	    if (nums.length == 0) {  //Empty array misses the range lower->upper.
	        ranges.add(outputRange(lower, upper));
	        return ranges;
	    }
	    int prev;
	    if (nums[0] - lower > 0) {    //Handles lower boundary. Notice "inclusive".
	        ranges.add(outputRange(lower, nums[0] - 1));
	        prev = nums[0];
	    } else {
	        prev = lower;    //The solution has a little problem here. If comes here, the lower will not be inlucded in the result
	    }
	    
	    for (int cur : nums) {
	        if (cur - prev > 1) { 
	            ranges.add(outputRange(prev + 1, cur - 1)); //Misses range if distance > 1.
	        }
	        prev = cur;
	    }
	    
	    if (upper - prev > 0) {  //Handles the upper boundary.
	        ranges.add(outputRange(prev + 1, upper));
	    }

	    return ranges;
	}
	
	private String outputRange(int n, int m) {
	    return (n == m)?String.valueOf(n) : n + "->" + m;
	}

	
	
	
	//AC: 12% (majority)
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> lstMissingRange = new ArrayList<String>();
        if (upper < lower) return lstMissingRange;
        
        if (nums == null || nums.length == 0 || upper < nums[0] || lower > nums[nums.length-1]) {
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
        
        int n = nums.length;
        int nMin = 0;
        int nMax = 0;
        
        for (int i=1; i<n; i++) {
        	nMin = Math.max(nums[i-1], lower);
        	nMax = Math.min(nums[i], upper);
        	
        	if (nMin+2 == nMax) {
        		if (nMin == nums[i-1]) {  //case like 1,3;   2 is not there
        			if (nMax == nums[i]) {
        				lstMissingRange.add(Integer.toString(nMin + 1));
        			} else {
        				lstMissingRange.add(Integer.toString(nMin + 1) + "->" + nMax);
        			}
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
