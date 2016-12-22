/*
462. Minimum Moves to Equal Array Elements II

Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, 
where a move is incrementing a selected element by 1 or decrementing a selected element by 1.

You may assume the array's length is at most 10,000.

Example:

Input:
[1,2,3]

Output:
2

Explanation:
Only two moves are needed (remember each move increments or decrements one element):

[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
*/

//Medium

public class Solution {
    public int minMoves2(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        Arrays.sort(nums);
        int count = 0;
        int i = 0, j = nums.length-1;
        
        //say the target value is x (which is between nums[i] and nums[j]), for each pair, the total move is nums[j] - nums[i]
        //If there is an extra number besides the pairs (i.e. the total number is odd), we could set the x at the value of the extra number
        while (i < j) {
            count += nums[j]-nums[i];
            i++;
            j--;
        }
        
        return count;
    }
}

