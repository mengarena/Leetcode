package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
//
//You may assume that the intervals were initially sorted according to their start times.
//
//Example 1:
//Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
//
//Example 2:
//Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
//
//This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].


//Google, Linkedin, Facebook
//Hard
public class InsertInterval {

	public InsertInterval() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(0, 10));
		intervals.add(new Interval(14, 14));
		intervals.add(new Interval(15, 20));

		
		Interval newInterval = new Interval(11, 11);
		
		List<Interval> lstkk = insert(intervals, newInterval);
		
		System.out.println();
	}
	
	//Definition for an interval.
	public class Interval {
		int start;
		int end;
		Interval() { start = 0; end = 0; }
		Interval(int s, int e) { start = s; end = e; }
	}
	
	
	//ACC: 91%
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> lstNew = new ArrayList<Interval>();
        
        if (intervals == null || intervals.size() == 0) {
        	lstNew.add(newInterval);
        	return lstNew;
        }
        
        int n = intervals.size();
        int i;
        Interval tmpInterval = null;
        boolean newIntervalInserted = false;
        
        for (i=0; i<n; i++) {
        	tmpInterval = intervals.get(i);
        	
        	if (tmpInterval.end < newInterval.start) {  //Not overlap, before newInterval
        		lstNew.add(tmpInterval);
        		
        	} else if (tmpInterval.start > newInterval.end) {  //Not overlap, after newInterval
        		if (newIntervalInserted == false) {
        			lstNew.add(newInterval);
        			newIntervalInserted = true;
        		}
        		lstNew.add(tmpInterval);
        		
        	} else {  //Overlapped, merge overlapped intervals into newInterval
        		newInterval.start = Math.min(newInterval.start, tmpInterval.start);
        		newInterval.end = Math.max(newInterval.end, tmpInterval.end);
        	}
        }
	
        if (newIntervalInserted == false) {
        	lstNew.add(newInterval);
        }
	
        return lstNew;
    }
	
	
    
    
	//ACC: 91%
    public List<Interval> insertA(List<Interval> intervals, Interval newInterval) {
        List<Interval> lstNew = new ArrayList<Interval>();
        
        if (intervals == null || intervals.size() == 0) {
        	lstNew.add(newInterval);
        	return lstNew;
        }
        
        int n = intervals.size();
        int i;
        int curStart = intervals.get(0).start;
        int curEnd = intervals.get(0).end;
        boolean processed = false;   //To record whether newInterval has been processed or encountered
        
        //Process when newInterval is before all intervals or overlap with the first intervals
        if (newInterval.end < curStart) {
        	intervals.add(0, newInterval);
        	return intervals;
        } else if (newInterval.end <= curEnd) {
        	if (newInterval.start > curStart) {
        		return intervals;
        	} else {
        		intervals.set(0, new Interval(newInterval.start, curEnd));
        	}
        	return intervals;
        } else if (newInterval.start <= curEnd) {
        	curEnd = newInterval.end;
        	
        	if (curStart > newInterval.start) curStart = newInterval.start;
        	
        	processed = true;
        }
        
        for (i=1; i<n; i++) {
        	if (processed == true) {
        		if (intervals.get(i).start > curEnd) {
        			lstNew.add(new Interval(curStart, curEnd));
        			curStart = intervals.get(i).start;
        			curEnd = intervals.get(i).end;
        		} else {
        			//curEnd = intervals.get(i).end;
            		if (intervals.get(i).end > curEnd) {
            			curEnd = intervals.get(i).end;
            		}
        		}
        		
        		continue;
        	}
        	
        	if (newInterval.start > curEnd) {
        		lstNew.add(intervals.get(i-1));
        		curStart = intervals.get(i).start;
        		curEnd = intervals.get(i).end;
        		
        	} else if (newInterval.start < curStart) {
        		if (newInterval.end < curStart) {   
        			//newInterval does not overlap with any intervals, but is a separate interval in the gaps of intervals
        			lstNew.add(newInterval);
        			lstNew.add(new Interval(curStart, curEnd));
            		curStart = intervals.get(i).start;
            		curEnd = intervals.get(i).end;
        			processed = true;
        			continue;
        		}
        		
        		curStart = newInterval.start;
        		
        		if (newInterval.end > curEnd) {
        			curEnd = newInterval.end;
        		}
        		
                if (intervals.get(i).start > curEnd) {
        			lstNew.add(new Interval(curStart, curEnd));
        			curStart = intervals.get(i).start;
        			curEnd = intervals.get(i).end;               	
                } else {
                	if (intervals.get(i).end > curEnd) {
                		curEnd = intervals.get(i).end;
                	}
                }
                
        		processed = true;
        	} else if (newInterval.start <= curEnd) {
        		if (newInterval.end > curEnd) {
        			curEnd = newInterval.end;
        		}
        		
                if (intervals.get(i).start > curEnd) {
        			lstNew.add(new Interval(curStart, curEnd));
        			curStart = intervals.get(i).start;
        			curEnd = intervals.get(i).end;               	
                } else {
                	if (intervals.get(i).end > curEnd) {
                		curEnd = intervals.get(i).end;
                	}
                }
        		
        		processed = true;
        	}
        }
        
        //newInterval has not been processed, it could be behind the last intervals; or overlaps with the last interval
        if (processed == false) {
        	if (newInterval.start > curEnd) {   //newInterval is behind the last intervals
        		lstNew.add(new Interval(curStart, curEnd));
        		lstNew.add(newInterval);
        	} else if (newInterval.end < curStart) {  //newInterval does not overlap with last intervals, but in the gap before the last interval
        		lstNew.add(newInterval);
        		lstNew.add(new Interval(curStart, curEnd));		
        	} else {  //Overlap with last intervals
        		lstNew.add(new Interval(Math.min(curStart, newInterval.start), Math.max(curEnd, newInterval.end)));
        	}

        } else {
        	lstNew.add(new Interval(curStart, curEnd));
        }
        
        return lstNew;
    }

}
