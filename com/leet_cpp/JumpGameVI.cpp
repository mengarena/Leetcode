/*

1696. Jump Game VI

You are given a 0-indexed integer array nums and an integer k.

You are initially standing at index 0. 
In one move, you can jump at most k steps forward without going outside the boundaries of the array. 
That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.

You want to reach the last index of the array (index n - 1). 
Your score is the sum of all nums[j] for each index j you visited in the array.

Return the maximum score you can get.

Example 1:
Input: nums = [1,-1,-2,4,-7,3], k = 2
Output: 7
Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.

Example 2:
Input: nums = [10,-5,-2,4,0,3], k = 3
Output: 17
Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.

Example 3:
Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
Output: 0
 
Constraints:
 1 <= nums.length, k <= 10^5
-10^4 <= nums[i] <= 10^4

Hint:
1. Let dp[i] be "the maximum score to reach the end starting at index i". 
The answer for dp[i] is nums[i] + min{dp[i+j]} for 1 <= j <= k. That gives an O(n*k) solution.

2. Instead of checking every j for every i, keep track of the smallest dp[i] values in a heap and calculate dp[i] from right to left. 
When the smallest value in the heap is out of bounds of the current index, remove it and keep checking.

Medium
*/

class Solution {
public:

    // 100%
    // O(n)
    int maxResult(vector<int>& nums, int k) {
        int n = nums.size();
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        
        deque<int> dq;  // for storing index corresponding to the sums accumulated from the end of the array.  The front() is index of the max sum
        long current = 0;
        
        // This solution use the original nums to record the sums (from right to left)
        for (int i=n-1; i>=0; --i) {
            current = nums[i] + (dq.empty()? 0:nums[dq.front()]);
            
            while (!dq.empty() && current > nums[dq.back()]) {
                dq.pop_back();
            }
            
            dq.push_back(i);
            
            // Remove the index which is out of bound from current index
            if (dq.front() >= i+k) {
                dq.pop_front();
            }
            
            nums[i] = current;
        }
        
        return current;
    }





    // TLE
    int maxResult(vector<int>& nums, int k) {
        int n = nums.size();
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        
        vector<int> sums(n, INT_MIN);
        
        sums[0] = nums[0];
        
        for (int i=0; i<n; ++i) {
            for (int j=1; j<=k; ++j) {
                if (i+j > n-1) continue;
                sums[i+j] = max(sums[i+j], sums[i] + nums[i+j]);
            }
        }
        
        return sums[n-1];
    }
};
