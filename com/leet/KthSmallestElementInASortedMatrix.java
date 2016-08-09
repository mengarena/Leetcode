package com.leet;

import java.util.PriorityQueue;


//Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
//
//Note that it is the kth smallest element in the sorted order, not the kth distinct element.
//
//Example:
//
//matrix = [
//   [ 1,  5,  9],
//   [10, 11, 13],
//   [12, 13, 15]
//],
//k = 8,
//
//return 13.
//		
//		
//Note: 
//You may assume k is always valid, 1 ¡Ü k ¡Ü n2.



//Google, Twitter
public class KthSmallestElementInASortedMatrix {

	public KthSmallestElementInASortedMatrix() {
		// TODO Auto-generated constructor stub
	}

	//ACC:
	//Could think about the current smallest values form a curve, the curve is pushed from the top-left corner to the bottom-right corner
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int i = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(k, (m1, m2)->(m1[0]-m2[0]));    //int[]: {element value, x, y}
        
        //For the initial curve
        for (i=0; i<n; i++) {
            pq.offer(new int[]{matrix[0][i], 0, i});
        }
        
        for (i=0; i<k-1; i++) {
            int[] tuple = pq.poll();
            
            if (tuple[1] == n-1) continue;
            
            //Based on current smallest value, push the curve (at this point) down by one step
            pq.offer(new int[]{matrix[tuple[1]+1][tuple[2]], tuple[1]+1, tuple[2]});
        }

        int[] retPair = pq.poll();
        return retPair[0];
    }

}
