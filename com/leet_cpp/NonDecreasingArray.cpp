/*

665. Non-decreasing Array

Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.

We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

Example 1:
Input: [4,2,3]
Output: True
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.

Example 2:
Input: [4,2,1]
Output: False
Explanation: You can't get a non-decreasing array by modify at most one element.

Note: The n belongs to [1, 10,000].

Easy
*/


class Solution {
public:

    // 91%
    bool checkPossibility(vector<int>& nums) {
        int count = 0;
        int idx = -1;
        int n = nums.size();
        
        for (int i = 0; i<n-1; ++i) {
            if (nums[i] > nums[i+1]) {
                idx = i+1;
                count++;
            }
            if (count > 1) return false;    
        }
    
        if (idx == -1 || idx == 1 || idx == n-1) return true;  // for idx=1, make 0th number smaller;  for idx=n-1, make (n-1)th number larger
        if (idx > 0 && idx < n-1 && nums[idx-1] <= nums[idx+1]) return true;   // 1 3 2 4 (to change 2)
        if (idx >= 2 && idx < n-1 && nums[idx-2] <= nums[idx]) return true;     // 1 3 2 2 (to change 3)
        
        return false;
    }
};
