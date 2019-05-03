package com.leet;

//Also called longest alternating subsequence

//The longest Zig-Zag subsequence problem is to find length of the longest subsequence of given sequence such that 
//all elements of this are alternating.
//If a sequence {x1, x2, .. xn} is alternating sequence then its element satisfy one of the following relation :
//
//  x1 < x2 > x3 < x4 > x5 < ¡­. xn or 
//  x1 > x2 < x3 > x4 < x5 > ¡­. xn 
//Examples:
//
//Input: arr[] = {1, 5, 4}
//Output: 3
//The whole arrays is of the form  x1 < x2 > x3 
//
//Input: arr[] = {1, 4, 5}
//Output: 2
//All subsequences of length 2 are either of the form 
//x1 < x2; or x1 > x2
//
//Input: arr[] = {10, 22, 9, 33, 49, 50, 31, 60}
//Output: 6
//The subsequences {10, 22, 9, 33, 31, 60} or
//{10, 22, 9, 49, 31, 60} or {10, 22, 9, 50, 31, 60}
//are longest Zig-Zag of length 6.
//We strongly recommend you to minimize your browser and try this yourself first.
//
//This problem is an extension of longest increasing subsequence problem, 
//but requires more thinking for finding optimal substructure property in this.
//
//We will solve this problem by dynamic Programming method, Let A is given array of length n of integers. 
//We define a 2D array Z[n][2] such that Z[i][0] contains longest Zig-Zag subsequence ending at index i and 
//last element is greater than its previous element and Z[i][1] contains longest Zig-Zag subsequence ending at index i 
//and last element is smaller than its previous element, 
//then we have following recurrence relation between them,
//
//Z[i][0] = Length of the longest Zig-Zag subsequence 
//          ending at index i and last element is greater
//          than its previous element
//Z[i][1] = Length of the longest Zig-Zag subsequence 
//          ending at index i and last element is smaller
//          than its previous element
//
//Recursive Formulation:
//   Z[i][0] = max (Z[i][0], Z[j][1] + 1); 
//             for all j < i and A[j] < A[i] 
//   Z[i][1] = max (Z[i][1], Z[j][0] + 1); 
//             for all j < i and A[j] > A[i]
//
//The first recurrence relation is based on the fact that, 
//If we are at position i and this element has to be bigger than its previous element then 
//for this sequence (upto i) to be bigger we will try to choose an element j ( < i) such that
//A[j] < A[i] i.e. A[j] can become A[i]¡¯s previous element and Z[j][1] + 1 is bigger than Z[i][0] then 
//we will update Z[i][0].
//
//Remember we have chosen Z[j][1] + 1 not Z[j][0] + 1 to satisfy alternate property 
//because in Z[j][0] last element is bigger than its previous one and A[i] is greater than A[j] 
//which will break the alternating property if we update. 
//So above fact derives first recurrence relation, similar argument can be made for second recurrence relation also.
//


//Google
public class LongestZigZagSubsequence {

	public LongestZigZagSubsequence() {
		// TODO Auto-generated constructor stub
	}

	int zzis(int arr[], int n)
	{
	    /*Z[i][0] = Length of the longest Zig-Zag subsequence
	          ending at index i and last element is greater
	          than its previous element
	     Z[i][1] = Length of the longest Zig-Zag subsequence
	          ending at index i and last element is smaller
	          than its previous element   */
	    int Z[][] = new int[n][2];
	 
	    /* Initialize all values to be 1  */
	    for (int i = 0; i < n; i++)
	        Z[i][0] = Z[i][1] = 1;
	 
	    int res = 1; // Initialize result
	 
	    /* Compute values in bottom up manner */
	    for (int i = 1; i < n; i++)
	    {
	        // Consider all elements as previous of arr[i]
	        for (int j = 0; j < i; j++)
	        {
	            // If arr[i] is greater, then check with Z[j][1]
	            if (arr[j] < arr[i] && Z[i][0] < Z[j][1] + 1)
	                Z[i][0] = Z[j][1] + 1;
	 
	            // If arr[i] is smaller, then check with Z[j][0]
	            if( arr[j] > arr[i] && Z[i][1] < Z[j][0] + 1)
	                Z[i][1] = Z[j][0] + 1;
	        }
	 
	        /* Pick maximum of both values at index i  */
	        if (res < Math.max(Z[i][0], Z[i][1]))
	            res = Math.max(Z[i][0], Z[i][1]);
	    }
	 
	    return res;
	}

}
