package com.leet;

import java.util.HashMap;
import java.util.Map;

//Given n points in the plane that are all pairwise distinct, 
//a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k 
//(the order of the tuple matters).
//
//Find the number of boomerangs. 
//You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).
//
//Example:
//	
//Input:
//[[0,0],[1,0],[2,0]]
//
//Output:
//2
//
//Explanation:
//The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]

//Google
//Easy
public class NumberOfBoomerangs {

	public NumberOfBoomerangs() {
		// TODO Auto-generated constructor stub
	}

	//ACC
	//Strategy:  since order matters
	//From each point, calculate the distance to all other points
	//Group these distance, to form <distance, count>
	//For each distance, based on count, we could get P(count count-1) = count*(count-1) permutations
    public int numberOfBoomerangs(int[][] points) {
        if (points == null || points.length == 0 || points[0].length < 2) return 0;
        int n = points.length;
        int i, j;
        int dist;
        int count;
        int ans = 0;
        Map<Integer, Integer> hm = new HashMap<>(); //Distance, count
        
        for (i=0; i<n; i++) {
            hm.clear();
            
            for (j=0; j<n; j++) {
                if (i == j) continue;
                dist = (int) Math.pow((points[i][0] - points[j][0]), 2) + (int) Math.pow((points[i][1] - points[j][1]), 2);
                if (!hm.containsKey(dist)) {
                    hm.put(dist, 1);
                } else {
                    hm.put(dist, hm.get(dist)+1);
                }
            }
            
            Set<Integer> setKey = hm.keySet();
            for (int key:setKey) {
                count = hm.get(key);
            
                if (count <= 1) continue;
                
                ans += count*(count-1);
            }
        }
        
        return ans;
    }

}
