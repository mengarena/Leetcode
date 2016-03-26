package com.leet;

import java.util.ArrayList;
import java.util.List;

//There is a fence with n posts, each post can be painted with one of the k colors.
//
//You have to paint all the posts such that no more than two adjacent fence posts have the same color.
//
//Return the total number of ways you can paint the fence.
//
//Note:
//n and k are non-negative integers.

public class PaintFence {

	public PaintFence() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		System.out.println(numWays(0, 2));
	}
	
	
	//Here shows how could be painted  (in this table, 1 means same color as yesterday, k-1 means have k-1 choice (for different color)
//	Post:     1              2                3                 4               5
//	-------------------------------------------------------------------------------                                                                         
//	          k              1                k-1               1               k-1
//	                                                            k-1             1
//	                                                                            k-1
//	                         k-1              1                 k-1             1
//	                                                                            k-1
//	                                          k-1               1               k-1
//	                                                            k-1             1
//	                                                                            k-1
	
	
	
    //AC
    //A good detailed explained is here: https://leetcode.com/discuss/56173/o-n-time-java-solution-o-1-space
    public int numWays(int n, int k) {
    	if (n == 0) return 0;
        if (n == 1) return k;
        if (k == 1 && n >= 3) return 0;
        
        //nDiffColorCnt:  store the #solution, where the last two days are different color
        //nSameColorCnt:  store the #solution, where the last two days are same color
        //Pay attention: nDiffColorCnt and nSameColorCnt are the total #solutions so far for the two situations (last two day same color, last two day different color), 
        //They are NOT simply the color choice on day i (which is at most k)
        //For example, #color = 5;  but by day i-1, there are 100 combinations of different color solutions (i.e. nDiffColorCnt = 100), 
        //now on day i, we want to put same color as day i-1, the total #solution for this same color painting will be nDiffColorCnt (its value by yesterday)
        int nDiffColorCnt = k*(k-1); //By 2nd post
        int nSameColorCnt = k;       //By 2nd post
        
        for (int i=2; i<n; i++) {
        	int nTmp = nSameColorCnt;
        	nSameColorCnt = nDiffColorCnt;   //Now come to day i, because for nSameColorCnt, previous two days are same color, if want to same color as yesterday, the only choice are nDiffColorCnt
        	nDiffColorCnt = (nTmp + nDiffColorCnt)*(k-1);  
        	//Here nTmp+nDiffColorCnt (i.e. the sum of nSameColorCnt + nDiffColorCnt by yesterday), now today i want to be different from them, so have *(k-1) total (different color) solution
        }
        
        return (nSameColorCnt + nDiffColorCnt);
    }
	
	//AC:  Deduced from drawing figures and calculate formula
    public int numWaysA(int n, int k) {
    	if (n == 0) return 0;
        if (n == 1) return k;
        if (k == 1 && n >= 3) return 0;
        int narrFib[] = new int[n+1];
        
        int i;
        
        narrFib[0] = 0;
        narrFib[1] = k;
        narrFib[2] = k*k;
        
        for (i=3; i<=n; i++) {
        	narrFib[i] = (narrFib[i-1] + narrFib[i-2])*(k-1);
        }
        
        return narrFib[n];
    }	
    
    
    
}
