package com.leet;

//Assume you have an array of length n initialized with all 0's and are given k update operations.
//
//Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] 
//		(startIndex and endIndex inclusive) with inc.
//
//Return the modified array after all k operations were executed.
//
//Example:
//
//Given:
//
//    length = 5,
//    
//    updates = [
//        [1,  3,  2],
//        [2,  4,  3],
//        [0,  2, -2]
//    ]
//
//Output:
//
//    [-2, 0, 3, 5, 3]
//    		
//Explanation:
//
//Initial state:
//[ 0, 0, 0, 0, 0 ]
//
//After applying operation [1, 3, 2]:
//[ 0, 2, 2, 2, 0 ]
//
//After applying operation [2, 4, 3]:
//[ 0, 2, 5, 5, 3 ]
//
//After applying operation [0, 2, -2]:
//[-2, 0, 3, 5, 3 ]
//Hint:
//
//Thinking of using advanced data structures? You are thinking it too complicated.
//For each update operation, do you really need to update all elements between i and j?
//Update only the first and end element is sufficient.
//The optimal time complexity is O(k + n) and uses O(1) extra space.


//Google
public class RangeAddition {

	public RangeAddition() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC
	//For each update, [startIdx, endIdx, Inc],  on the original array, update as following:
	//narr[startIdx] += Inc   (The Inc starts to take effect)
	//narr[endIdx+1] -= Inc   (The Inc stops to take effect)
	//The elements between (startIdx, endIdx] is 0
	//
	// Then when calculate the sum, at each position, if meets a start idx, the effect is added;  if meets a end idx, the corresponding effect is removed
	// for those elements which are not start/end positions, it takes the value of the previous element
	//
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] narr = new int[length];
        if (updates == null || updates.length == 0 || updates[0].length != 3) return narr;
        int n = updates.length;
        int i;
        
        for (i=0; i<n; i++) {
            narr[updates[i][0]] += updates[i][2];
            
            if (updates[i][1] < length-1) {
                narr[updates[i][1]+1] -= updates[i][2];
            }
        }
        
        int sum = 0;
        
        for (i=0; i<length; i++) {
            sum += narr[i];
            narr[i] = sum;
        }
        
        return narr;
    }

}
