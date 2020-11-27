/*

540. Single Element in a Sorted Array

Given a sorted array consisting of only integers where every element appears exactly twice 
except for one element which appears exactly once. 
Find this single element that appears only once.

Example 1:
Input: [1,1,2,3,3,4,4,8,8]
Output: 2

Example 2:
Input: [3,3,7,7,10,11,11]
Output: 10

Note: Your solution should run in O(log n) time and O(1) space.

Medium

Amazon, Google, Facebook, Apple
*/

class Solution {
public:

    // 39%
    // Strategy:  Treat every 2 element as a group
    // Here one group will be like a normal element in regular binary search
    int singleNonDuplicate(vector<int>& nums) {
        int n = nums.size();
        
        int i = 0, j = n/2;
        int candNum = nums[0];
        
        while (i <= j) {
            int mid = i + (j-i)/2;
            
            if (mid*2 == n-1 || nums[mid*2] != nums[mid*2+1]) {
                candNum = nums[mid*2];
                j = mid-1;
            } else {
                i = mid+1;    
            }
        }
        
        return candNum;
    }
};
