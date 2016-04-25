package com.leet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

//Linkedin
public class MaxPointsOnALine {
	
	 //Definition for a point.
	class Point {
	    int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
	}
	
	
	public MaxPointsOnALine() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC:  40%
	//
	//Strategy: from each point, to calcualte the slope with other points
	//Here the important thing is that NOT using double as the key in hashmap
	//Here the key is represented as two integers: x, y (NOT their ratio), GCD removed
	//For each <x, y>, accumulate its count
    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0) return 0;
        int n = points.length;
        if (n <= 2) return n;
        int i, j;
        Map<Integer, Map<Integer, Integer>> hmm = new HashMap<Integer, Map<Integer, Integer>>();  //x, y, count (the numbers of points in one line with <x,y>)
        int maxCnt = 0;
        
        //from each point, to calcualte the slope with other points
        for (i=0; i<n; i++) {
        	int overlapped = 0;
        	int tempMax = 0;
        	hmm.clear();   //avoid duplicate calculate for the same point also avoid calculate parallel lines together
        	
        	//The reason only needs to check i+1~n:
        	//Theoretically, needs to check 0~n, except i,
        	//but say to point A, B,  the slope AB and BA will be the same after divided by nGcd
        	//so say when i is 1, j is 3,  slope between points[1] points[3] is checked, later when i = 3, it does not need to check the slope between points[3] and points[1]
        	for (j=i+1; j<n; j++) {
        		if (points[i].x == points[j].x && points[i].y == points[j].y) {
        			overlapped++;
        			continue;
        		}
        		
        		int x = points[j].x - points[i].x;   //x difference
        		int y = points[j].y - points[i].y;   //y difference
        		int nGcd = gcd(x, y);
        		//All the vertical line (i.e. x = 0, y != 0) will be recorded as (x=0, y=1)
        		
        		if (nGcd != 0) {
        			x = x/nGcd;     //This makes sure ij, ji will be the same
        			y = y/nGcd;
        		}
        		
        		if (hmm.containsKey(x)) {
        			if (hmm.get(x).containsKey(y)) {
        				int count = hmm.get(x).get(y);
        				hmm.get(x).put(y, 1+count);  //Record number of points cross points[i]
        			} else {
        				hmm.get(x).put(y, 1);
        			}
        		} else {
        			Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
        			hm.put(y, 1);
        			hmm.put(x, hm);
        		}
        		
        		tempMax = Math.max(tempMax, hmm.get(x).get(y));
        	}
        	
        	maxCnt = Math.max(tempMax + overlapped + 1, maxCnt);   //overlapped: the points overlapped with points[i], 1: the points[i] itself
        	
        }
        
        return maxCnt;
    }
    
    
    private int gcd(int num1, int num2) {
    	if (num2 == 0) {
    		return num1;
    	}
    	
    	return gcd(num2, num1%num2);
    }

}
