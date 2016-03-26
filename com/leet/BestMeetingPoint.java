package com.leet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//A group of two or more people wants to meet and minimize the total travel distance. 
//You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. 
//The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
//
//For example, given three people living at (0,0), (0,4), and (2,2):
//
//1 - 0 - 0 - 0 - 1
//|   |   |   |   |
//0 - 0 - 0 - 0 - 0
//|   |   |   |   |
//0 - 0 - 1 - 0 - 0
//The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
//
//Hint:
//
//Try to solve it in one dimension first. How can this solution apply to the two dimension case?

public class BestMeetingPoint {

	public BestMeetingPoint() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		
	}
	
	
	//Strategy: If there is an array, the minimal distance to all the element is the median point
	//Reason: choose any element, if there are more elements on one side than the other, we could always move towards the side which has more element to reduce the total distance
	//Say on one side there are m elements, the other side has n elements, and m > n,
	//If we move one unit towards m elements side, total distance change: -m + n, i.e. the total distance becomes small.
	//One one dimension is like this;
	//If there is another dimension, we should also choose the median point on the 2nd dimension
	//I.e. first find median point along x axis, then find median point along y axis
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        List<Integer> lstX = new ArrayList<Integer>();
        List<Integer> lstY = new ArrayList<Integer>();
        int nRow = grid.length;
        int nCol = grid[0].length;
        int i,j;
        int nMinDistX = 0;
        int nMinDistY = 0;
        
        for (i=0; i<nRow; i++) {
        	for (j=0; j<nCol; j++) {
        		if (grid[i][j] != 0) {
        			lstX.add(i);
        			lstY.add(j);
        		}
        	}
        }
        
        Collections.sort(lstX);
        Collections.sort(lstY);
        
        //Now find total distance based on median point on X and Y
        i=0; j=lstX.size()-1;
        
        //Here calcuate the total distance based on median
        //Say there is a median, m (even we don't know its actual position)
        //Before and after the median, we have equal number of elements
        //so corresponds to the median, for each pair elements on both sides, the distance is:  m-a + b-m = b-a
        while (i < j) {
        	nMinDistX = nMinDistX + lstX.get(j) - lstX.get(i);
        	nMinDistY = nMinDistY + lstY.get(j) - lstY.get(i);
        	i++;
        	j--;
        }
        
        return nMinDistX + nMinDistY;
        
    }	
}
