/*

679. 24 Game

You have 4 cards each containing a number from 1 to 9. 
You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.

Example 1:
Input: [4, 1, 8, 7]
Output: True
Explanation: (8-4) * (7-1) = 24

Example 2:
Input: [1, 2, 1, 2]
Output: False

Note:
The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
Every operation done is between two numbers. In particular, we cannot use - as a unary operator. 
For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.

Hard

*/


class Solution {
public:

    // 58%
    bool judgePoint24(vector<int>& nums) {
        vector<double> mynums;
        for (auto num:nums) mynums.push_back(num);
        return judgePoint(mynums);
    }
    
    bool judgePoint(vector<double>& nums) {
        if (nums.size() == 0) return false;
        if (nums.size() == 1) return abs(nums[0] - 24) < 1e-6;
        
        int n = nums.size();
        
        for (int i=0; i<n; ++i) {
            for (int j=0; j<n; ++j) {
                if (i == j) continue;
                
                vector<double> nextNums;
                
                for (int k=0; k<n; ++k) {
                    if (k != i && k != j) nextNums.push_back(nums[k]);
                }
                
                for (int t=1; t<=4; ++t) {  // 4 situations between nums[i] and nums[j]
                    // + and * are commutive operator, only process one of the case
                    // i.e. for <2,3> and <3,2>, only need to process one of them
                    if ((t == 1 || t == 3) && (j > i)) continue;
                    
                    if (t == 1) nextNums.push_back(nums[i] + nums[j]);
                    if (t == 2) nextNums.push_back(nums[i] - nums[j]);
                    if (t == 3) nextNums.push_back(nums[i] * nums[j]);
                    if (t == 4) {
                        if (nums[j] != 0) {
                            nextNums.push_back(nums[i] / nums[j]);
                        } else {
                            continue;
                        }
                    }
                    
                    if (judgePoint(nextNums)) return true;
                    nextNums.erase(nextNums.begin() + nextNums.size()-1);
                    
                }
            }
        }
        
        return false;
    }
};
