package com.leet;

import java.util.Arrays;
import java.util.Comparator;

//Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
//determine if a person could attend all meetings.
//
//For example,
//Given [[0, 30],[5, 10],[15, 20]],
//return false.

//Facebook
public class MeetingRooms {
	
	public class Interval {
		 int start;
		 int end;
		 Interval() { start = 0; end = 0; }
		 Interval(int s, int e) { start = s; end = e; }
    }
	
	public MeetingRooms() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		
	}
	
	    
    //AC: Better Performance. In Java 8, the comparator could be defined simply:
	//This is Andoird IDE, so it does not support Java 8 currently
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length <= 1) return true;
        int n = intervals.length;
        int i;
        
      //  Arrays.sort(intervals, (x, y)->x.start-y.start);    //Works in Java 8

        for (i=1; i<n; i++) {
            if (intervals[i].start < intervals[i-1].end) return false;
        }
        
        return true;
    }

    
    //AC: Better performance, defining a comparator and sort
    public boolean canAttendMeetingsB(Interval[] intervals) {
        if (intervals == null || intervals.length <= 1) return true;
        int n = intervals.length;
        int i;
        
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start-b.start;   //Smaller will be ordered in front
            }
            
        });

        for (i=1; i<n; i++) {
            if (intervals[i].start < intervals[i-1].end) return false;
        }
        
        return true;
    }

    
    
    //AC:  Naive solution, slow
    public boolean canAttendMeetingsA(Interval[] intervals) {
        if (intervals == null || intervals.length <= 1) return true;
        int n = intervals.length;
        int i, j;
        
        for (i=0; i<n-1; i++) {
            for (j=i+1; j<n; j++) {
                if (!(intervals[i].start >= intervals[j].end || intervals[i].end <= intervals[j].start)) return false;
            }
        }
        
        return true;
    }
 
}
