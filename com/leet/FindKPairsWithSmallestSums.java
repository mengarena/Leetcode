package com.leet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

//You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
//
//Define a pair (u,v) which consists of one element from the first array and one element from the second array.
//
//Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
//
//Example 1:
//Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3
//
//Return: [1,2],[1,4],[1,6]
//
//The first 3 pairs are returned from the sequence:
//[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
//		
//		
//Example 2:
//Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2
//
//Return: [1,1],[1,1]
//
//The first 2 pairs are returned from the sequence:
//[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
//		
//Example 3:
//Given nums1 = [1,2], nums2 = [3],  k = 3 
//
//Return: [1,3],[2,3]
//
//All possible pairs are returned from the sequence:
//[1,3],[2,3]


//Google, Uber
public class FindKPairsWithSmallestSums {

	public FindKPairsWithSmallestSums() {
		// TODO Auto-generated constructor stub
	}

	
    class Pair {
        int a;
        int b;
        
        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    
    //ACC: 45ms
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> lstPairs = new ArrayList<>();
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k <= 0) return lstPairs;
        int n1 = nums1.length;
        int n2 = nums2.length;
        int i,j;

        Queue<Pair> pq = new PriorityQueue<Pair>(k, new Comparator<Pair>(){
            public int compare(Pair X, Pair Y) {
                if (X.a - Y.a + X.b - Y.b == 0) {
                    return X.b - Y.b;
                } 
                
                return X.a - Y.a + X.b - Y.b;
            } 
            
        });
        
        for (i=0; i<n1; i++) {
            for (j=0; j<n2; j++) {
                pq.offer(new Pair(nums1[i], nums2[j]));
            }
        }
        
        int pqSize = pq.size();
        
        for (i=0; i<Math.min(k, pqSize); i++) {
            Pair tmp = pq.poll();
            lstPairs.add(new int[]{tmp.a, tmp.b});
        }
        
        return lstPairs;
    }

}
