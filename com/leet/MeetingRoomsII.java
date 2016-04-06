package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
//find the minimum number of conference rooms required.
//
//For example,
//Given [[0, 30],[5, 10],[15, 20]],
//return 2.

//Google, Facebook
public class MeetingRoomsII {
	public class Interval {
		 int start;
		 int end;
		 Interval() { start = 0; end = 0; }
		 Interval(int s, int e) { start = s; end = e; }
    }
	
	public MeetingRoomsII() {
		// TODO Auto-generated constructor stub
	}


    public void run() {
    	
    }
    
    
    //Accepted: Best performance
    //Strategy:  Check (compare) each meeting's start time with any meeting's end time
    //1) If at the moment a meeting wants to start, there is a currently going on meeting which could ends before it, the new meeting could use that meeting's room, don't need to open a new meeting room;
    //2) Otherwise, If at the moment a meeting wants to start, other ongoing meetings' end time is in the future, then this new meeting has to open a new meeting room
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null) return 0;
        int n = intervals.length;
        if (n <= 1) return n;
        int[] narrStart = new int[n];
        int[] narrEnd = new int[n];
        int i;
        
        for (i=0; i<n; i++) {
        	narrStart[i] = intervals[i].start;
        	narrEnd[i] = intervals[i].end;
        }
        
        Arrays.sort(narrStart);
        Arrays.sort(narrEnd);
        
        int nRoomCnt = 0;
        int nMeetingEndIdx = 0;
        
        //Check the situation at the moment each meeting wants to start
        for (i=0; i<n; i++) {
        	if (narrStart[i] < narrEnd[nMeetingEndIdx]) {    //Means, currently could-as-early-as-possible-to-finish meeting is still in the future, then has to open a new meeting room
        		nRoomCnt++;
        	} else {   
        		//Means, the starting time is behind the could-as-early-as-possible-to-finish meeting, the new meeting could use that meeting's room, don't need to open a new meeting room.
        		//And next time, we check on the next-finishing meeting's time
        		nMeetingEndIdx++;
        	}
        }
        
        return nRoomCnt;
    }
    
    //AC: The problem equals to finding the max number of overlapping 
    public int minMeetingRoomsA(Interval[] intervals) {
        if (intervals == null) return 0;
        int n = intervals.length;
        if (n <= 1) return n;
        int i,j;
        int nCount = 0;
        int nSize = 0;
        boolean bOverlapped = false;
        List<Integer> lstOverlapped = new ArrayList<Integer>();
        
        Arrays.sort(intervals, new Comparator<Interval>() {
        	public int compare(Interval a, Interval b) {
        		return a.start - b.start;
        	}
        });
        
        lstOverlapped.add(0);
        nCount = 1;
        i = 1;
        while (i<n) {
        	nSize = lstOverlapped.size();
        	bOverlapped = false;
        	for (j=nSize-1; j>=0; j--) {
        		if (intervals[i].start < intervals[lstOverlapped.get(j)].end) {
        			bOverlapped = true;
        		} else {
        			lstOverlapped.remove(j);
        		}
        	}
        	
        	if (bOverlapped == true) lstOverlapped.add(i);
        	
        	if (lstOverlapped.isEmpty()) lstOverlapped.add(i);
        	
        	nCount = Math.max(nCount, lstOverlapped.size());
        	i++;
        }
        
        return nCount;
    }   
}
