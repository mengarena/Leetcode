/*

560. Subarray Sum Equals K

Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].


Medium
*/

class Solution {
public:

    // 76%
    int subarraySum(vector<int>& nums, int k) {
        int n = nums.size();
        int sum = 0;
        int count = 0;
        unordered_map<int, int> m;   // <sum, count>
        
        m[0] = 1;
        
        for (int i=0; i<n; ++i) {
            sum += nums[i];
            count += m[sum-k];
            m[sum]++;
        }
        
        return count;
    }


    // 10%
    int subarraySum(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> sums(n+1, 0);
        
        for (int i=1; i<=n; ++i) {
            sums[i] += sums[i-1] + nums[i-1];
        }
        
        int count = 0;
        
        for (int i=0; i<n; ++i) {
            for (int j=i+1; j<n+1; j++) {
                if (sums[j] - sums[i] == k) count++;
            }
        }
        
        return count;
    }
};
