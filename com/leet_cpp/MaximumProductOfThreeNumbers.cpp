/*

628. Maximum Product of Three Numbers

Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:
Input: [1,2,3]
Output: 6
 
Example 2:
Input: [1,2,3,4]
Output: 24
 
Note:

The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.

Easy
*/


class Solution {
public:

    // 58%
    // Result could be one of the cases:
    // 1) 0 (all 0 or only <= 3 numbers exist, at least one of them is 0)
    // 2) product of 3 max negative numbers (i.e. no >=0 numbers exist)
    // 3) product of 2 min negative numbers * 1 max positive number
    // 4) product of 3 max positive numbers
    // 5) product of whatever numbers exist (total #number <= 3)
    int maximumProduct(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        if (n <= 2) {
            int sum = 1;
            for (auto num:nums) {
                sum *= num;
            }
            return sum;
        }
        
        return max(nums[0]*nums[1]*nums[n-1], nums[n-1]*nums[n-2]*nums[n-3]);
    }
};
