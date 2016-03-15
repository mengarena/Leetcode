package com.leet;

import java.util.ArrayList;
import java.util.List;

//There are N children standing in a line. Each child is assigned a rating value.
//
//You are giving candies to these children subjected to the following requirements:
//
//Each child must have at least one candy.
//Children with a higher rating get more candies than their neighbors.
//What is the minimum candies you must give?
		
public class Candy {

	public Candy() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//int ratings[] = new int[20000];
		//for (int i=0; i<20000; i++) ratings[i] = i+1;
		
		int ratings[] = {1,2,2};
		
		System.out.println(candy(ratings));
	}
	
	
	//Attention:  only when i > its neighbor(s), its value is larger than neighbor(s); if not >, it not necessarily to be equal.
	//[1,2,2]  the result should be 4 (not 5), i.e. the result should be 1,2,1
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        int n = ratings.length;
        if (n == 1) return 1;
        int nCandyCnt = n;
        int i;
        int[] narrCandyCnt = new int[n];

        for (i=1; i<n; i++) {
            if (ratings[i] > ratings[i-1]) narrCandyCnt[i] = narrCandyCnt[i-1] + 1;
        }

        for (i=n-2; i>=0; i--) {
            if (ratings[i] > ratings[i+1]) narrCandyCnt[i] = Math.max(narrCandyCnt[i], narrCandyCnt[i+1] + 1);
        }
        
        for (i=0; i<n; i++) nCandyCnt = nCandyCnt + narrCandyCnt[i];
        
        return nCandyCnt;
    }
	
	
    
	
	//Function correct, exceed time limit
    public int candyA(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        int n = ratings.length;
        if (n == 1) return 1;
        int nCandyCnt = n;
        int i;
        
        int[] narrCandyCnt = new int[n];
        
        distributeCandy(ratings, 0, n-1, narrCandyCnt);
        
        for (i=0; i<n; i++) nCandyCnt = nCandyCnt + narrCandyCnt[i];
        
        return nCandyCnt;
    }
	
    //Divide and Conquer
    private void distributeCandy(int[] ratings, int nStartPos, int nEndPos, int[] narrCandyCnt) {
    	int nMin = Integer.MAX_VALUE;
    	int nMinPos = 0;
    	
    	if (nEndPos <= nStartPos) {
    		return;
    	}
    	
    	for (int i=nStartPos; i<=nEndPos; i++) {
    		if (ratings[i] < nMin) {
    			nMin = ratings[i];
    			nMinPos = i;
    		}
    	}
    	
    	if (nMinPos-1 >= nStartPos) {
    		if (ratings[nMinPos-1] > ratings[nMinPos]) {
    			if (narrCandyCnt[nMinPos-1] <= narrCandyCnt[nMinPos]) narrCandyCnt[nMinPos-1] = narrCandyCnt[nMinPos] + 1;
    		} else if (ratings[nMinPos-1] == ratings[nMinPos]) {
    			if (narrCandyCnt[nMinPos-1] < narrCandyCnt[nMinPos]) narrCandyCnt[nMinPos-1] = narrCandyCnt[nMinPos];
    		}
    	}
    	
    	if (nMinPos+1 <= nEndPos) {
    		if (ratings[nMinPos+1] > ratings[nMinPos]) {
    			if (narrCandyCnt[nMinPos+1] <= narrCandyCnt[nMinPos]) narrCandyCnt[nMinPos+1] = narrCandyCnt[nMinPos] + 1;
    		} else if (ratings[nMinPos+1] == ratings[nMinPos]) {
    			if (narrCandyCnt[nMinPos+1] < narrCandyCnt[nMinPos]) narrCandyCnt[nMinPos+1] = narrCandyCnt[nMinPos] + 1;
    		}
    	}
    	
    	distributeCandy(ratings, nStartPos, nMinPos-1, narrCandyCnt);
    	distributeCandy(ratings, nMinPos+1, nEndPos, narrCandyCnt);
    }
}
