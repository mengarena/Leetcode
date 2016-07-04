package com.leet;

import java.util.HashSet;
import java.util.Set;

//You are given two jugs with capacities x and y litres. 
//There is an infinite amount of water supply available. 
//You need to determine whether it is possible to measure exactly z litres using these two jugs.
//
//If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.
//
//Operations allowed:
//
//Fill any of the jugs completely with water.
//Empty any of the jugs.
//Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
//
//Example 1: (From the famous "Die Hard" example)
//
//Input: x = 3, y = 5, z = 4
//Output: True
//
//
//Example 2:
//
//Input: x = 2, y = 6, z = 5
//Output: False


//Microsoft
public class WaterJugProblem {

	public WaterJugProblem() {
		// TODO Auto-generated constructor stub
	}

	//ACC: 23%
	//Based on B¨¦zout's identity:    https://en.wikipedia.org/wiki/B%C3%A9zout%27s_identity
	//g = gcd(x, y)
	//check whether g*i = z
    public boolean canMeasureWater(int x, int y, int z) {
        if ((x % 2 == 0) && (y % 2 == 0) && (z % 2 == 1)) return false;
        if (x == z || y == z || x+y == z || z == 0) return true;
        if (x + y < z || x == y) return false;

        return ((z % gcd(x, y)) == 0);
    }
    
    
    private int gcd(int x, int y) {
        if (y == 0) return x;
        
        return gcd(y, x%y);
    }
    
    
    private int gcdA(int x, int y) {

        while (y != 0) {
            return gcd(y, x%y);
        }
        
        return x;
    }
    
    
    private int gcdB(int x, int y) {
        int tmp;
        
        while (y != 0) {
            tmp = x % y;
            x = y;
            y = tmp;
        }
        
        return x;
    }
}
