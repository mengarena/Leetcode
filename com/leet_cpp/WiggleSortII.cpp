/*

324. Wiggle Sort II

Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example 1:
Input: nums = [1, 5, 1, 1, 6, 4]
Output: One possible answer is [1, 4, 1, 5, 1, 6].

Example 2:
Input: nums = [1, 3, 2, 2, 3, 1]
Output: One possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?

Medium
*/


class Solution {
public:

/*
Accessing A(0) actually accesses nums[1].
Accessing A(1) actually accesses nums[3].
Accessing A(2) actually accesses nums[5].
Accessing A(3) actually accesses nums[7].
Accessing A(4) actually accesses nums[9].
Accessing A(5) actually accesses nums[0].
Accessing A(6) actually accesses nums[2].
Accessing A(7) actually accesses nums[4].
Accessing A(8) actually accesses nums[6].
Accessing A(9) actually accesses nums[8]. 
*/
    
    
    // 99%
    void wiggleSort(vector<int>& nums) {
        int n = nums.size();
        
        // This definition make sure when i > n/2, result is even, otherwise is odd
        // i.e. larger half values will be in even indexes
        #define A(i) nums[(1+2*i) % (n | 1)]
        
        auto mid = nums.begin()+n/2;
        // After this step, smaller values are at the left half (i.e. index < n/2)
        // larger values are at the right half (i.e. index > n/2)
        nth_element(nums.begin(), mid, nums.end());
        int midval = *mid;
        
        // Three way patition: https://en.wikipedia.org/wiki/Dutch_national_flag_problem#Pseudocode
        // In the wiki, sorting is ascending,
        // here below is descending
        // The bigger numbers normally in index 0~n/2 are placed in odd places
        // i.e.  A(0~n/2) which corresponding to nums[1,3,5,7...] will be put larger numbers
        int i=0, j=0, k=n-1;
        
        while (j <= k) {
            if (A(j) < midval) {
                swap(A(j), A(k--));
            } else if (A(j) > midval) {
                swap(A(i++), A(j++));
            } else {
                j++;
            }
        }
    }
};
