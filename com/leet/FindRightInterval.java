package com.leet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Given a set of intervals, for each of the interval i, 
//check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, 
//which can be called that j is on the "right" of i.
//
//For any interval i, you need to store the minimum interval j's index, 
//which means that the interval j has the minimum start point to build the "right" relationship for interval i. 
//If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.
//
//Note:
//You may assume the interval's end point is always bigger than its start point.
//You may assume none of these intervals have the same start point.
//
//Example 1:
//Input: [ [1,2] ]
//
//Output: [-1]
//
//Explanation: There is only one interval in the collection, so it outputs -1.
//
//Example 2:
//Input: [ [3,4], [2,3], [1,2] ]
//
//Output: [-1, 0, 1]
//
//Explanation: There is no satisfied "right" interval for [3,4].
//For [2,3], the interval [3,4] has minimum-"right" start point;
//For [1,2], the interval [2,3] has minimum-"right" start point.
//
//Example 3:
//Input: [ [1,4], [2,3], [3,4] ]
//
//Output: [-1, 2, -1]
//
//Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
//For [2,3], the interval [3,4] has minimum-"right" start point.

//Medium
public class FindRightInterval {

	public FindRightInterval() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Definition for an interval.
	 */
	public class Interval {
	      int start;
	      int end;
	      Interval() { start = 0; end = 0; }
	      Interval(int s, int e) { start = s; end = e; }
	}
		
	
    class StartIdx implements Comparable<StartIdx> {
        int start;
        int orgIdx;
        StartIdx(int start, int orgIdx) {
            this.start = start;
            this.orgIdx = orgIdx;
        }
        
        public int compareTo(StartIdx other) {
            return this.start - other.start; //Order: Small, Large    
        }
    }
    
    public int[] findRightInterval(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return new int[0];
        if (intervals.length == 1) {
            int[] ret = new int[1];
            ret[0] = -1;
            return ret;
        }
        
        int n = intervals.length;
        int i;
        int[] ret = new int[n];
        int target;
        int nIdx;
        
        List<StartIdx> lstSI = new ArrayList<>();
        for (i=0; i<n; i++) {
            lstSI.add(new StartIdx(intervals[i].start, i));
        }
        
        Collections.sort(lstSI);
        
        for (i=0; i<n; i++) {
            target = intervals[i].end;
            nIdx = findMinLarger(lstSI, target);    
            ret[i] = nIdx;
        }
        
        return ret;
    }
    
    private int findMinLarger(List<StartIdx> lstSI, int target) {
        int i = 0;
        int j = lstSI.size()-1;
        int mid;
        int rightPos = -1;
        
        while (i <= j) {
            mid = i + (j-i)/2;
            
            if (lstSI.get(mid).start < target) {
                i = mid+1;
            } else {
                rightPos = mid;
                j = mid-1;
                
            }
        }
        
        if (rightPos != -1) return lstSI.get(rightPos).orgIdx;
        
        return -1;
    }
}
