/*

1063. Number of Valid Subarrays

Given an array A of integers, return the number of non-empty continuous subarrays that satisfy the following condition:

The leftmost element of the subarray is not larger than other elements in the subarray.


Example 1:
Input: [1,4,2,5,3]
Output: 11
Explanation: There are 11 valid subarrays: [1],[4],[2],[5],[3],[1,4],[2,5],[1,4,2],[2,5,3],[1,4,2,5],[1,4,2,5,3].

Example 2:
Input: [3,2,1]
Output: 3
Explanation: The 3 valid subarrays are: [3],[2],[1].

Example 3:
Input: [2,2,2]
Output: 6
Explanation: There are 6 valid subarrays: [2],[2],[2],[2,2],[2,2],[2,2,2].
 
Note:
1 <= A.length <= 50000
0 <= A[i] <= 100000

Hard

Hulu
*/


class Solution {
public:
    
    // 78%
    // The stack remembers: at a num, how many previous numbers exist, 
    // which are monotonously <= this num
    // These numbers (also including the number between them) forms the 
    // continuous subarrays satisfy the condition
    // If there are 3 numbers in stack after adding current number,
    // then we can get 3 these kinds of subarrays, 
    // the starting points of these subarrays are each of these numbers in the stack
    int validSubarrays(vector<int>& nums) {
        if (nums.size() == 0) return 0;
        stack<int> stk;
        
        int res = 0;
        
        for (auto num:nums) {
            while (!stk.empty() && stk.top() > num) {
                stk.pop();
            }
            
            stk.push(num);
            res += stk.size();
        }
        
        return res;
    }
};

