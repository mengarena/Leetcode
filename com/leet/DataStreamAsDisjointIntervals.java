package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.
//
//For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
//
//[1, 1]
//[1, 1], [3, 3]
//[1, 1], [3, 3], [7, 7]
//[1, 3], [7, 7]
//[1, 3], [6, 7]
//		
//Follow up:
//What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?


//In the question, the class name is: SummaryRanges

//Easy
public class DataStreamAsDisjointIntervals {


    // Definition for an interval.
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
 

    //ACC:  74%
    private List<Interval> lstInterval = null;
    
    /** Initialize your data structure here. */
    public DataStreamAsDisjointIntervals() {
        lstInterval = new ArrayList<Interval>();
    }
    
    public void addNum(int val) {
        if (lstInterval.isEmpty()) {
            lstInterval.add(new Interval(val, val));
            return;
        } 
        
        int i=0, j = lstInterval.size()-1;
        int mid;
        Interval tmp = null;
        
        while (i<=j) {
            mid = (i+j)/2;
            tmp = lstInterval.get(mid);
            
            if (tmp.start <= val && tmp.end >= val) {
                return;
            } else if (tmp.start > val) {
                j = mid-1;   
            } else {
                i = mid+1;
            }
        }
        
        if (i==0) {
            if (val + 1 == lstInterval.get(0).start) {
                lstInterval.get(0).start = val;
            } else {
                lstInterval.add(0, new Interval(val, val));
            }
        } else if (i==lstInterval.size()) {
            if (lstInterval.get(lstInterval.size()-1).end + 1 == val) {
                lstInterval.get(lstInterval.size()-1).end = val;
            } else {
                lstInterval.add(new Interval(val, val));
            }
        } else {
            int before = i-1;
            int after = i;
            Interval intBefore = lstInterval.get(before);
            Interval intAfter = lstInterval.get(after);
            
            if (intBefore.end + 2 == intAfter.start) {
                lstInterval.get(before).end = intAfter.end;
                lstInterval.remove(i);
            } else if (intBefore.end + 1 == val) {
                lstInterval.get(before).end = val;
            } else if (val + 1 == intAfter.start) {
                lstInterval.get(after).start = val;
            } else {
                lstInterval.add(i, new Interval(val, val));
            }
        }
    }
    
    public List<Interval> getIntervals() {
        return lstInterval;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */
