/*
473. Matchsticks to Square

Remember the story of Little Match Girl? 
By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. 
You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

Your input will be several matchsticks the girl has, represented with their stick length. 
Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.

Example 1:
Input: [1,1,2,2,2]
Output: true

Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.


Example 2:
Input: [3,3,3,3,4]
Output: false

Explanation: You cannot find a way to form a square with all the matchsticks.

Note:
The length sum of the given matchsticks is in the range of 0 to 10^9.
The length of the given matchstick array will not exceed 15.
*/

//Rackspace
//Medium

public class Solution {
	//ACC: 67%
	//If without the sorting, 24%
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        long sum = 0;
        
        Arrays.sort(nums);
        reverse(nums);  //In Desc order
        
        for (int num:nums) sum += (long)num;
        
        if ((sum % 4) != 0) return false;
        long edge = sum/4;
        
        return dfs(nums, new long[4], 0, edge);
    }
    
    //Here sum[] is actually the sum of the four edges
    private boolean dfs(int[] nums, long[] sum, int startIdx, long edge) {
        if (startIdx == nums.length) {
            if (sum[0] == edge && sum[1] == edge && sum[2] == edge) {
                return true;
            }
            
            return false;
        }
        
        //Try to put the num at startIdx into each edge
        for (int i=0; i<4; i++) {
            if (sum[i] + nums[startIdx] > edge) continue;
            
            sum[i] += nums[startIdx];
            
            if (dfs(nums, sum, startIdx+1, edge)) return true;
            
            sum[i] -= nums[startIdx];
        }
        
        return false;
    }
    
    private void reverse(int[] nums) {
        int i = 0;
        int j = nums.length-1;
        int tmp;
        
        while (i < j) {
            tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
    }

}

