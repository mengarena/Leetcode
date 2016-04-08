package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.leet.MeetingRoomsII.Interval;

//A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. 
//Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), 
//write a program to output the skyline formed by these buildings collectively (Figure B).
//
//Buildings  Skyline Contour
//
//The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], 
//where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. 
//It is guaranteed that 0 ¡Ü Li, Ri ¡Ü INT_MAX, 0 < Hi ¡Ü INT_MAX, and Ri - Li > 0. 
//You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
//
//For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
//
//The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. 
//A key point is the left endpoint of a horizontal line segment. 
//Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, 
//and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
//
//For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
//
//Notes:
//
//The number of buildings in any input list is guaranteed to be in the range [0, 10000].
//The input list is already sorted in ascending order by the left x position Li.
//The output list must be sorted by the x position.
//There must be no consecutive horizontal lines of equal height in the output skyline. 
//For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; 
//the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]

		
//Google, Microsoft
public class TheSkylineProblem {

	public TheSkylineProblem() {
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		//int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
		int[][] buildings = {{1,2,1}, {1,2,2}, {1,2,3}};
		
		List<int[]> lstSkyline = getSkyline(buildings);
		
		for (int[] sl:lstSkyline) {
			System.out.print("[" + sl[0] + "," + sl[1] + "]");
		}
		
		System.out.println();
	}


	//ACC: 54%
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> lstKeyPoints = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) return lstKeyPoints;
        int n = buildings.length;
        List<int[]> lstHeights = new ArrayList<>();
        int i;
        
        if (n == 1) {
        	lstKeyPoints.add(new int[]{buildings[0][0], buildings[0][2]});
        	lstKeyPoints.add(new int[]{buildings[0][1], 0});
        	return lstKeyPoints;
        }    
        
        for (i=0; i<n; i++) {
        	lstHeights.add(new int[]{buildings[i][0], -buildings[i][2]});   //Negative to represent start point
        	lstHeights.add(new int[]{buildings[i][1], buildings[i][2]});    //Positive to represent end point
        }
        
        //Sort the critical points based on x axis value (and the heights at these points)
        //Small x-axis value put in front; if x-axis values are the same, sort based on height (*Trick here)
        //Since for the starting point, height has been negated, so for starting points; if two segments have same starting points, 
        //the (positive) larger height (i.e. the smaller negated height) will be put in front and processed first
        //For end point, the smaller height will be put in front and processed first
        Collections.sort(lstHeights, new Comparator<int[]>() {
        	public int compare(int[] a, int[] b) {
        		if (a[0] != b[0]) {
        			return a[0] - b[0];
        		} else {
        			return a[1] - b[1];
        		}
        	}
        });

        
        //This PriorityQueue maintains the effect heights by this moment
        //Largest value is on top
        Queue<Integer> pq = new PriorityQueue<Integer>(11, new Comparator<Integer>() {
        	public int compare(Integer a, Integer b) {
        		return b-a;
        	}
        });
        
        
        pq.offer(0);   //When no building, height is 0
        int prevHeight = 0;
        int curHeight = 0;
        
        for (int[] h:lstHeights) {
        	if (h[1] < 0) {   //Starting point; its height comes into play
        		pq.offer(-h[1]);
        	} else {    //End point, its height becomes ineffective (out of effect)
        		pq.remove(h[1]);
        	}
        	
        	curHeight = pq.peek();
        	
        	if (curHeight != prevHeight) {
        		lstKeyPoints.add(new int[]{h[0], curHeight});   //h[0] is the x-axis value
        		prevHeight = curHeight;
        	}
        }
        
        return lstKeyPoints;
        
    }
	

    
    
/*	
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> lstKeyPoints = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) return lstKeyPoints;
        int n = buildings.length;
        List<int[]> lstStart = new ArrayList<>();
        List<int[]> lstEnd = new ArrayList<>();
        int i;
        
        if (n == 1) {
        	lstKeyPoints.add(new int[]{buildings[0][0], buildings[0][2]});
        	lstKeyPoints.add(new int[]{buildings[0][1], 0});
        	return lstKeyPoints;
        }        
	
        int nCurHeight = 0;
        int nCurEnd = 0;
        int nPrevStart = 0;
        
        for (i=0; i<n; i++) {
        	if (buildings[i][0] > nCurEnd || buildings[i][2] > nCurHeight) {
        		if (lstStart.isEmpty()) {
        			lstStart.add(new int[]{buildings[i][0], buildings[i][2]});
        			nPrevStart = buildings[i][0];
        		} else if (nPrevStart == buildings[i][0]) {
        			if (nCurHeight < buildings[i][2]) lstStart.set(lstStart.size()-1, new int[]{buildings[i][0], buildings[i][2]});
        		} else {
        			lstStart.add(new int[]{buildings[i][0], buildings[i][2]});
        			nPrevStart = buildings[i][0];
        		}
        		
        		nCurHeight = buildings[i][2];
        		nCurEnd = buildings[i][1];
        	} 
        }
        
        
        Arrays.sort(buildings, new Comparator<int[]>() {
        	public int compare(int[] a, int[] b) {
        		return a[1] - b[1];
        	}
        });        
        
        int nPrevHeight = 0;
        int nCurStart = 0;
        int nPrevEnd = 0;
        
        for (i=n-1; i>=0; i--) {
        	if (buildings[i][1] < nCurStart || buildings[i][2] > nPrevHeight) {
        		if (lstEnd.isEmpty()) {
        			lstEnd.add(0, new int[]{buildings[i][1], nPrevHeight});
        			nPrevEnd = buildings[i][1];
        		} else if (nPrevEnd == buildings[i][1]) {
        			if (nPrevHeight < lstEnd.get(0)[1]) lstEnd.set(0, new int[]{buildings[i][1], nPrevHeight});
        		} else {
        			lstEnd.add(0, new int[]{buildings[i][1], nPrevHeight});
        			nPrevEnd = buildings[i][1];        			
        		}
        		
        		nPrevHeight = buildings[i][2];
        		nCurStart = buildings[i][0];
        	}
        }
        
        
        //Merge
        while (!lstStart.isEmpty() || !lstEnd.isEmpty()) {
        	if (lstStart.isEmpty()) {
        		lstKeyPoints.add(lstEnd.remove(0));
        	} else if (lstEnd.isEmpty()) {
        		lstKeyPoints.add(lstStart.remove(0));
        	} else {
        		if (lstStart.get(0)[0] < lstEnd.get(0)[0]) {
        			lstKeyPoints.add(lstStart.remove(0));
        		} else {
        			lstKeyPoints.add(lstEnd.remove(0));
        		}
        	}
        }
        
        return lstKeyPoints;
        
    } 
*/	
    
    
    
    
/*
 
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> lstKeyPoints = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) return lstKeyPoints;
        int n = buildings.length;
        List<int[]> lstStart = new ArrayList<>();
        List<int[]> lstEnd = new ArrayList<>();
        int i;
        
        if (n == 1) {
        	lstKeyPoints.add(new int[]{buildings[0][0], buildings[0][2]});
        	lstKeyPoints.add(new int[]{buildings[0][1], 0});
        	return lstKeyPoints;
        }        
	
        int nCurHeight = 0;
        int nCurEnd = 0;
        int nPrevStart = 0;
        
        for (i=0; i<n; i++) {
        	if (buildings[i][0] > nCurEnd || buildings[i][2] > nCurHeight) {
        		if (lstStart.isEmpty()) {
        			lstStart.add(new int[]{buildings[i][0], buildings[i][2]});
        			nPrevStart = buildings[i][0];
        		} else if (nPrevStart == buildings[i][0]) {
        			if (nCurHeight < buildings[i][2]) lstStart.set(lstStart.size()-1, new int[]{buildings[i][0], buildings[i][2]});
        		} else {
        			lstStart.add(new int[]{buildings[i][0], buildings[i][2]});
        			nPrevStart = buildings[i][0];
        		}
        		
        		nCurHeight = buildings[i][2];
        		nCurEnd = buildings[i][1];
        	} 
        }
        
        Arrays.sort(buildings, new Comparator<int[]>() {
        	public int compare(int[] a, int[] b) {
        		return a[1] - b[1];
        	}
        });        
        
        nCurHeight = 0;
        int nCurStart = 0;
        int nPrevEnd = 0;
        
        for (i=n-1; i>=0; i--) {
        	if (buildings[i][1] <= nCurStart || buildings[i][2] > nCurHeight) {
        	    if (buildings[i][1] < nCurStart) nCurHeight = 0;
        	    
        	    if (buildings[i][2] <= nCurHeight) {
        	        nCurHeight = buildings[i][2];
        	        nCurStart = buildings[i][0];
        	        nPrevEnd = buildings[i][1];
        	        continue;
        	    }
        	    
        		if (lstEnd.isEmpty()) {
        			lstEnd.add(0, new int[]{buildings[i][1], nCurHeight});
        			nPrevEnd = buildings[i][1];
        			nCurStart = buildings[i][0];
        		} else if (nPrevEnd == buildings[i][1]) {
        		    if (nCurHeight < lstEnd.get(0)[1]) {
        		        lstEnd.set(0, new int[]{buildings[i][1], nCurHeight});
        		        nCurStart = Math.min(nCurStart, buildings[i][0]);
        		    }
        		} else {
        			lstEnd.add(0, new int[]{buildings[i][1], nCurHeight});
        			nPrevEnd = buildings[i][1];
        			nCurStart = Math.min(nCurStart, buildings[i][0]);
        		}
        		
        		nCurHeight = buildings[i][2];
        		
        	}
        }
        
        
        //Merge
        while (!lstStart.isEmpty() || !lstEnd.isEmpty()) {
        	if (lstStart.isEmpty()) {
        		lstKeyPoints.add(lstEnd.remove(0));
        	} else if (lstEnd.isEmpty()) {
        		lstKeyPoints.add(lstStart.remove(0));
        	} else {
        		if (lstStart.get(0)[0] < lstEnd.get(0)[0]) {
        			lstKeyPoints.add(lstStart.remove(0));
        		} else {
        			lstKeyPoints.add(lstEnd.remove(0));
        		}
        	}
        }
        
        return lstKeyPoints;
    }
 
 * */    
    
    
    
    
/*	
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> lstKeyPoints = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) return lstKeyPoints;
        int n = buildings.length;
        int i;
        int minX = buildings[0][0];
        int maxX = Integer.MIN_VALUE;
        
        if (n == 1) {
        	lstKeyPoints.add(new int[]{buildings[0][0], buildings[0][2]});
        	lstKeyPoints.add(new int[]{buildings[0][1], 0});
        	return lstKeyPoints;
        }
        
        for (i=0; i<n; i++) {
        	maxX = Math.max(maxX, buildings[i][1]);
        }
        
        int[] height = new int[maxX-minX+1+1];
        
        for (i=0; i<n; i++) {
        	for (int j=buildings[i][0]; j<=buildings[i][1]; j++) {
        		height[j-minX] = Math.max(height[j-minX], buildings[i][2]);
        	}
        }
        
        int prevHeight = 0;
        for (i=minX; i<=maxX+1; i++) {
        	if (height[i-minX] > prevHeight) {
        		lstKeyPoints.add(new int[]{i, height[i-minX]});
        	} else if (height[i-minX] < prevHeight) {
        		lstKeyPoints.add(new int[]{i-1, height[i-minX]});
        	}
        	
        	prevHeight = height[i-minX];
        }
        
        return lstKeyPoints;
    }
*/
}
