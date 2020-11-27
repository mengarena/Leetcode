/*

643. Maximum Average Subarray I

Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value.
And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
 

Note:
1 <= k <= n <= 30,000.
Elements of the given array will be in the range [-10,000, 10,000].

Easy
*/

class Solution {
public:
    
    // 46%
    double findMaxAverage(vector<int>& nums, int k) {
        double sum = 0;
        int removeIdx = 0;
        double maxAvg = DBL_MIN;
        int i;
        
        for (i= 0; i<k; ++i) sum += nums[i];
        
        maxAvg = sum*1.0/k;
        
        while (i < nums.size()) {
            sum -= nums[removeIdx++];
            sum += nums[i++];
            maxAvg = max(maxAvg, sum/k);
        }
        
        return maxAvg;
    }
};
