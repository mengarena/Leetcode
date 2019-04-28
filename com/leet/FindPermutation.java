/*
484. Find Permutation 

By now, you are given a secret signature consisting of character 'D' and 'I'. 
'D' represents a decreasing relationship between two numbers, 'I' represents an increasing relationship between two numbers. 
And our secret signature was constructed by a special integer array, 
which contains uniquely all the different number from 1 to n (n is the length of the secret signature plus 1). 
For example, the secret signature "DI" can be constructed by array [2,1,3] or [3,1,2], 
but won't be constructed by array [3,2,4] or [2,1,3,4], which are both illegal constructing special string that can't represent the "DI" secret signature.

On the other hand, now your job is to find the lexicographically smallest permutation of [1, 2, ... n] could refer to the given secret signature in the input.

Example 1:
Input: "I"
Output: [1,2]
Explanation: [1,2] is the only legal initial spectial string can construct secret signature "I", where the number 1 and 2 construct an increasing relationship.

Example 2:
Input: "DI"
Output: [2,1,3]
Explanation: Both [2,1,3] and [3,1,2] can construct the secret signature "DI", 
but since we want to find the one with the smallest lexicographical permutation, you need to output [2,1,3]

Note:

The input string will only contain the character 'D' and 'I'.
The length of input string is a positive integer and will not exceed 10,000
*/

//Medium
//Google

public class Solution {
	
	//Strategy:  List the candidate numbers in ascending order
	//Count the number of consecutive "D", remember how many "D" after the current position (including the current position)
	//For example, at a position is "D" and after it there are another two consecutive "D", for the current position, 
	//we should put the 3rd number (start from 0) at the current position to meet the condition of "DDD"
    public int[] findPermutation(String s) {
        if (s == null || s.length() == 0) return new int[0];
        int n = s.length();
        n++;
        int[] ans = new int[n];
        List<Integer> lstNum = new ArrayList<>();
        int i;
        int[] dAfter = new int[n];  //Number of consecutive 'D' since this position
        int dCount = 0;   //Number of consecutive 'D'
        
        //List the candidate numbers
        for (i=1; i<=n; i++) lstNum.add(i);
        
        //Count the number of consecutive "D" since the current position
        for (i=s.length()-1; i>=0; i--) {
            if (s.charAt(i) == 'D') {
                dCount++;
                dAfter[i] = dCount;
            } else {
                dCount = 0;
            }
        }
        
	//dAfter[i] decides which nth number to take from the remained ascending numbers
        //Set the numbers at each position
        for (i=0; i<n-1; i++) {
           ans[i] = lstNum.remove(dAfter[i]);  // From the remained numbers in lstNum, which one to take 
		                               //(the one to take is the dAfter[i] -th of the remained numbers)
        }
        
        //There is one remained (n numbers, n-1 "D"/"I"
        ans[n-1] = lstNum.remove(0);
        
        return ans;
    }
}
