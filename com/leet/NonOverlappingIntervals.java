package com.leet;

import java.util.Arrays;

//Given a collection of intervals, find the minimum number of intervals 
//you need to remove to make the rest of the intervals non-overlapping.
//
//Note:
//You may assume the interval's end point is always bigger than its start point.
//Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
//
//Example 1:
//Input: [ [1,2], [2,3], [3,4], [1,3] ]
//
//Output: 1
//
//Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
//
//Example 2:
//Input: [ [1,2], [1,2], [1,2] ]
//
//Output: 2
//
//Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
//Example 3:
//Input: [ [1,2], [2,3] ]
//
//Output: 0
//
//Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

public class NonOverlappingIntervals {

	public NonOverlappingIntervals() {
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

	
	//ACC
	//Strategy:  sort based on the end point of each interval (to be ascending order)
	//For those have same end point, only one should be kept
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        int n = intervals.length;
        int nRmCnt = 0;
        int nCurEnd;
        
        //Arrays.sort(intervals, (a, b)->(a.end-b.end));   //Java 8
        
        nCurEnd = intervals[0].end;
        
        for (int i=1; i<n; i++) {
            if (intervals[i].start < nCurEnd) {
                nRmCnt++;
            } else {
                nCurEnd = intervals[i].end;
            }
        }
        
        return nRmCnt;
    }

}
