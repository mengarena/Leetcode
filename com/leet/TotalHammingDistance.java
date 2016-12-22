/*
477. Total Hamming Distance

The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Now your job is to find the total Hamming distance between all pairs of the given numbers.

Example:
Input: 4, 14, 2

Output: 6

Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
showing the four bits relevant in this case). So the answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.

Note:
Elements of the given array are in the range of 0 to 10^9
Length of the array will not exceed 10^4.
*/

//Facebook
//Medium

public class Solution {
	
	//ACC
	//Strategy:  each integer 32-bit
	//For each position of 32-bit, count how many numbers have 1, how many have 0
	//So the hamming distance at each bit position will be ones*zeros
    public int totalHammingDistance(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        int n = nums.length;
        int totalHamDist = 0;
        int i, j;
        int ones = 0;
        
        for (i=0; i<32; i++) {
            ones = 0;
            
            for (j=0; j<n; j++) {
                if (((nums[j] >> i) & 1) == 1) ones++;
            }
            
            totalHamDist += ones*(n-ones);
        }
        
        return totalHamDist;
    }
}
