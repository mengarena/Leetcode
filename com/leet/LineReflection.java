package com.leet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.
//
//Example 1:
//Given points = [[1,1],[-1,1]], return true.
//
//Example 2:
//Given points = [[1,1],[-1,-1]], return false.
//
//Follow up:
//Could you do better than O(n2)?
//
//Hint:
//
//Find the smallest and largest x-value for all points.
//If there is a line then it should be at y = (minX + maxX) / 2.
//For each point, make sure that it has a reflected point in the opposite side.


//Google
public class LineReflection {

	public LineReflection() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC: 70%
    public boolean isReflected(int[][] points) {
        if (points == null || points.length == 0 || points[0].length != 2) return true;
        int n = points.length;
        int i;
        int maxX = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        Set<Integer> hs = new HashSet<Integer>();
        
        for (i=0; i<n; i++) {
            maxX = Math.max(maxX, points[i][0]);
            minX = Math.min(minX, points[i][0]);
            
            hs.add(Arrays.hashCode(points[i]));   //calculate hash code based on the contents of the specified array. 
        }
        
        for (i=0; i<n; i++) {
            if (!hs.contains(Arrays.hashCode(new int[]{maxX-points[i][0]+minX, points[i][1]}))) return false;
        }
        
        return true;
    }
	
	
	//ACC: 37%
    public boolean isReflectedC(int[][] points) {
        if (points == null || points.length == 0 || points[0].length != 2) return true;
        int n = points.length;
        int i;
        int maxX = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        Set<String> hs = new HashSet<String>();
        String sReflected = "";
        
        for (i=0; i<n; i++) {
            maxX = Math.max(maxX, points[i][0]);
            minX = Math.min(minX, points[i][0]);
            
            hs.add(points[i][0] + "#" + points[i][1]);
        }
        
        for (i=0; i<n; i++) {
            sReflected = (maxX+minX-points[i][0]) + "#" + points[i][1];
            
            if (!hs.contains(sReflected)) return false;
        }
        
        return true;
    }	
	
	
    //ACC: 30%
    public boolean isReflectedB(int[][] points) {
        if (points == null || points.length == 0 || points[0].length != 2) return true;
        int n = points.length;
        int i;
        int maxX = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        Map<Integer, Map<Integer, Integer>> hmm = new HashMap<>();
        
        for (i=0; i<n; i++) {
            maxX = Math.max(maxX, points[i][0]);
            minX = Math.min(minX, points[i][0]);
            
            if (!hmm.containsKey(points[i][0])) {
                Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
                hm.put(points[i][1], 1);
                hmm.put(points[i][0], hm);
            } else {
                Map<Integer, Integer> hm = hmm.get(points[i][0]);
                hm.put(points[i][1],1);
                hmm.put(points[i][0], hm);
            }
        }
        
        for (i=0; i<n; i++) {
            if (!hmm.containsKey(maxX-points[i][0]+minX) || !hmm.get(maxX-points[i][0]+minX).containsKey(points[i][1])) return false;
        }
        
        return true;
    }
    
    
    
	//ACC: 28%
    public boolean isReflectedA(int[][] points) {
        if (points == null || points.length == 0 || points[0].length != 2) return true;
        int n = points.length;
        int i;
        int maxX = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        float mid = 0;
        int reflected = 0;  //Reflected X-axis point
        Map<Integer, Set<Integer>> hs = new HashMap<>();   //X, set of Y
        
        for (i=0; i<n; i++) {
            maxX = Math.max(maxX, points[i][0]);
            minX = Math.min(minX, points[i][0]);
            
            if (!hs.containsKey(points[i][0])) {
                Set<Integer> set = new HashSet<Integer>();
                set.add(points[i][1]);
                hs.put(points[i][0], set);
            } else {
                Set<Integer> set = hs.get(points[i][0]);
                set.add(points[i][1]);
                hs.put(points[i][0], set);
            }
        }
        
        mid = (float)(minX + (maxX-minX)/2.0);
        
        for (i=0; i<n; i++) {
            if (points[i][0] <= mid) {
                reflected = Math.round(mid + (mid - points[i][0]));
            } else {
                reflected = Math.round(mid - (points[i][0] - mid));
            }
            
            if (!hs.containsKey(reflected) || !hs.get(reflected).contains(points[i][1])) return false;
        }
        
        return true;
    }

}
