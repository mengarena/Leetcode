package com.leet;

import java.util.Arrays;

//There are a number of spherical balloons spread in two-dimensional space. 
//For each balloon, provided input is the start and end coordinates of the horizontal diameter. 
//Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice. 
//Start is always smaller than end. There will be at most 104 balloons.
//
//An arrow can be shot up exactly vertically from different points along the x-axis. 
//A balloon with xstart and xend bursts by an arrow shot at x if xstart ¡Ü x ¡Ü xend. 
//There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely. 
//The problem is to find the minimum number of arrows that must be shot to burst all balloons.
//
//Example:
//
//Input:
//[[10,16], [2,8], [1,6], [7,12]]
//
//Output:
//2
//
//Explanation:
//	
//One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and 
//another arrow at x = 11 (bursting the other two balloons).
//
//Microsoft
//Medium
public class MinimumNumberOfArrowsToBurstBalloons {

	public MinimumNumberOfArrowsToBurstBalloons() {
		// TODO Auto-generated constructor stub
	}

	//ACC
	//Stragegy: sort points, first in ascending order for end point of each balloon; 
	//Each time, shot at an End point
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0 || points[0].length < 2) return 0;
        int n = points.length;
        int i;
        int nArrowCnt = 0;
        int nArrowPos = Integer.MIN_VALUE;
        
        //Arrays.sort(points, (a, b)->(a[1]-b[1]));   //Java 8
        
        nArrowPos = points[0][1];
        nArrowCnt++;
        
        for (i=1; i<n; i++) {
            if (points[i][0] <= nArrowPos) {  //COuld be shot by previous arrow
                continue;  
            } else {
                nArrowPos = points[i][1];  //Need a new shot
                nArrowCnt++;
            }
        }
        
        return nArrowCnt;
    }

}
