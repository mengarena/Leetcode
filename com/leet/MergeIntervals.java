package com.leet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Given a collection of intervals, merge all overlapping intervals.
//
//For example,
//Given [1,3],[2,6],[8,10],[15,18],
//return [1,6],[8,10],[15,18].


//Google, Linkedin, Microsoft, Bloomberg
public class MergeIntervals {

	public MergeIntervals() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	//Definition for an interval.
	public class Interval {
		int start;
		int end;
		Interval() { start = 0; end = 0; }
		Interval(int s, int e) { start = s; end = e; }
	}
	
	
	//ACC:  87%
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> lstMerged = new ArrayList<Interval>();
        
        if (intervals == null || intervals.size() <= 1) return intervals;
        
        Collections.sort(intervals, new Comparator<Interval>() {
        	public int compare(Interval a, Interval b) {
        		return a.start - b.start;
        	}
        });
        
        int curStart = 0;
        int curEnd = 0;
        int i;
        
        curStart = intervals.get(0).start;
        curEnd = intervals.get(0).end;
        
        for (i=1; i<intervals.size(); i++) {
        	if (intervals.get(i).start > curEnd) {
        		lstMerged.add(new Interval(curStart, curEnd));
        		curStart = intervals.get(i).start;
        		curEnd = intervals.get(i).end;
        	} else {
        		if (intervals.get(i).end > curEnd) {
        			curEnd = intervals.get(i).end;
        		}
        	}
        }
        
        lstMerged.add(new Interval(curStart, curEnd));
        
        return lstMerged;
    }	
}
