package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//Suppose you have a random list of people standing in a queue. 
//Each person is described by a pair of integers (h, k), where h is the height of the person and 
//k is the number of people in front of this person who have a height greater than or equal to h. 
//Write an algorithm to reconstruct the queue.
//
//Note:
//The number of people is less than 1,100.
//
//Example
//
//Input:
//[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
//
//Output:
//[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

		
//Google		
public class QueueReconstructionByHeight {

	public QueueReconstructionByHeight() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int[][] qu = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
		
		int[][] ret = reconstructQueue(qu);
		
		System.out.println("");
	}
	
    //ACC: 92 ms
    //Strategy:
    //   Sort people,  if height are the same, k smaller is put in front (easy to understand, 
    //                 this way could satisify larger k really has more elements in front); 
    //                 if height are different, larger height is put in front 
    //                 (this way could satisify the k of smaller-height element when adding to the result list)
    public int[][] reconstructQueue(int[][] people) {
    	
        //Arrays.sort(people, (a, b)->(a[0]!=b[0]?Integer.compare(b[0], a[0]):Integer.compare(a[1], b[1])));   //Java 8
    	Arrays.sort(people, new Comparator<int[]>() {
    		public int compare(int[] a, int[] b) {
    			if (a[0] != b[0]) {
    				return Integer.compare(b[0], a[0]);
    			} else {
    				return Integer.compare(a[1], b[1]);
    			}
    		}
    	});
    	
    	//In the example, after sorting, qu = {{7, 0}, {7, 1}, {6, 1}, {5, 0}, {5, 2}, {4, 4}};
    	
        List<int[]> lstRet = new ArrayList<>();
        
        for (int[] ppl:people) lstRet.add(ppl[1], ppl);   //number of previous person as index for adding
        
        return lstRet.toArray(new int[people.length][]);
    }

}
