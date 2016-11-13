package com.leet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. 
//The org sequence is a permutation of the integers from 1 to n, with 1 ¡Ü n ¡Ü 104. 
//Reconstruction means building a shortest common supersequence of the sequences in seqs 
//(i.e., a shortest sequence so that all sequences in seqs are subsequences of it). 
//Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
//
//Example 1:
//
//Input:
//org: [1,2,3], seqs: [[1,2],[1,3]]
//
//Output:
//false
//
//Explanation:
//[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
//
//
//Example 2:
//
//Input:
//org: [1,2,3], seqs: [[1,2]]
//
//Output:
//false
//
//Explanation:
//The reconstructed sequence can only be [1,2].
//
//
//Example 3:
//
//Input:
//org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]
//
//Output:
//true
//
//Explanation:
//The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
//
//
//Example 4:
//
//Input:
//org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
//
//Output:
//true

//Google
//Medium
public class SequenceReconstruction {

	public SequenceReconstruction() {
		// TODO Auto-generated constructor stub
	}

	//[1,4,2,5,3]
			//[[],[1,4],[5,3],[4,2,5,3],[]]
	
	public void run() {
		//int[] org = {1,2,3};
		//int[][] seqs = {{1,2}, {1,3}, {2,3}};

		int[] org = {1,4,2,5,3};
		int[][] seqs = {{},{1,4},{5,3},{4,2,5,3},{}};		
		boolean bRet = sequenceReconstruction(org, seqs);
		
		System.out.println("Result: " + bRet);
	}
	
	
	//ACC
	//Explain:
	//For org to be uniquely reconstructible from seqs we need to satisfy 2 conditions:

	//Every sequence in seqs should be a subsequence in org. This part is obvious.
	//Every 2 consecutive elements in org should be consecutive elements in some sequence from seqs. Why is that? 
	//Well, suppose condition 1 is satisfied. Then for 2 any consecutive elements x and y in org we have 2 options.
	//We have both xand y in some sequence from seqs. Then (as condition 1 is satisfied) they must be consequtive elements in this sequence.
	//There is no sequence in seqs that contains both x and y. 
	//In this case we cannot uniquely reconstruct org from seqs as sequence with x and y switched would also be a valid original sequence for seqs.
	//So this are 2 necessary criterions. It is pretty easy to see that this are also sufficient criterions for org to be uniquely reconstructible 
	//(there is only 1 way to reconstruct sequence when we know that condition 2 is satisfied).
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        if ((org == null || org.length == 0) ^ (seqs == null || seqs.length == 0)) return false;
        if ((org == null || org.length == 0) && (seqs == null || seqs.length == 0)) return true;
        
        int nOrg = org.length;
        int nSeq = seqs.length;
        int i;
        Set<Integer> setm = new HashSet<>();   //whether two consecutive elements (a, b) exists, here use HashCode to represent a pair
        Map<Integer, Integer> hm = new HashMap<>();  //Digit, Position
        
        for (i=0; i<nOrg; i++) {
            hm.put(org[i], i);
        } 
        
        for (i=0; i<nSeq; i++) {
            if (seqs[i] == null || seqs[i].length == 0) continue;
            for (int j=0; j<seqs[i].length; j++) {
                if (!hm.containsKey(seqs[i][j])) return false;    //org must contains elements in seqs
                
                //The consecutive order in org must be the same in seqs for each two numbers
                if (j > 0 && hm.get(seqs[i][j-1]) >= hm.get(seqs[i][j])) return false;
                
                if (j > 0) {
                    setm.add(Arrays.hashCode(new int[]{seqs[i][j-1], seqs[i][j]}));    //Consecutive number pair in seqs
                }
            }
        }
        
        for (i=1; i<nOrg; i++) {
            if (!setm.contains(Arrays.hashCode(new int[]{org[i-1], org[i]}))) return false;   //Each consecutive number pair in org must be existing in seqs
        }
        
        return true;
    }


}
