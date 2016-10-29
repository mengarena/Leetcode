package com.leet;

import java.util.Arrays;
import java.util.Comparator;

//You have a number of envelopes with widths and heights given as a pair of integers (w, h). 
//One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
//
//What is the maximum number of envelopes can you Russian doll? (put one inside other)
//
//Example:
//	
//Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

//Google
//Hard
public class RussianDollEnvelopes {

	public RussianDollEnvelopes() {
		// TODO Auto-generated constructor stub
	}

	//ACC: 100%
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) return 0;
        int n = envelopes.length;
        
        //Sort:  Width asc;  if Width is the same, Height Desc
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return b[1] - a[1];
                } else {
                    return a[0] - b[0];
                }
            }
            
        });
        
        int[] dp = new int[n];

        int maxNum = 0;
        
        //Use the idea of the Question: Longest Increasing Subsequence  (dp remembers the index of current longest increasing subsequence)
        for (int[] env:envelopes) {
            int idx = Arrays.binarySearch(dp, 0, maxNum, env[1]);   //Return index of the search key (env[1]) if contained in the array; otherwise returns (-(insertion point) -1)
                                                                    //1st parameter: the array to be searched
                                                                    //2nd parameter: index of the first element to be searched (inclusive)
                                                                    //3rd parameter: index of the last element to be searched (exclusive)
                                                                    //4rd parameter: the value to be searched for
            if (idx < 0) idx = -(idx+1);   //becomes the insertion point
            
            dp[idx] = env[1];
            
            if (idx == maxNum) maxNum++;
        }
        
        return maxNum;
    }	
	
    
	//ACC: 16%
    public int maxEnvelopesA(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) return 0;
        int n = envelopes.length;
        
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
            
        });
        
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        int maxNum = 1;
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<i; j++) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            
            maxNum = Math.max(maxNum, dp[i]);
        }
        
        return maxNum;
    }
}
