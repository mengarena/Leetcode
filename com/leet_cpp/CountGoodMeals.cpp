/*

1711. Count Good Meals

A good meal is a meal that contains exactly two different food items with a sum of deliciousness equal to a power of two.

You can pick any two different foods to make a good meal.

Given an array of integers deliciousness where deliciousness[i] is the deliciousness of the ith item of food, 
return the number of different good meals you can make from this list modulo 10^9 + 7.

Note that items with different indices are considered different even if they have the same deliciousness value.

Example 1:
Input: deliciousness = [1,3,5,7,9]
Output: 4
Explanation: The good meals are (1,3), (1,7), (3,5) and, (7,9).
Their respective sums are 4, 8, 8, and 16, all of which are powers of 2.

Example 2:
Input: deliciousness = [1,1,1,3,3,3,7]
Output: 15
Explanation: The good meals are (1,1) with 3 ways, (1,3) with 9 ways, and (1,7) with 3 ways.
 
Constraints:
1 <= deliciousness.length <= 10^5
0 <= deliciousness[i] <= 2^20

Medium
*/

class Solution {
public:
    bool isGoodMeal(long num) {
        if (num == 0) return 0;
        long times = (long)(log2(num));
        return (long)pow(2, (double)times) == num;
    }
    
    // faster than 92%
    int countPairs(vector<int>& deliciousness) {
        unordered_map<int, int> numCount;
        vector<int> nums;
        const unsigned int M = 1000000007;
        for (auto i: deliciousness) {
            numCount[i]++;
        }
        
        for (auto i: numCount) {
            nums.push_back(i.first);
        }
        
        long totalCnt = 0;
        vector<int> sums;
        for (int j=0; j<=21; ++j) {
            sums.push_back((int)pow(2, j));
        }
        
        for (int i=0; i<nums.size(); ++i) {
            int cnt = numCount[nums[i]];
            if (cnt > 1 && isGoodMeal((long)(nums[i]) + nums[i])) {
                totalCnt += (long)cnt*(long)(cnt-1)/2;
                totalCnt = totalCnt % M;
            }

            for (auto sum:sums) {
                int other = sum - nums[i];
                if (other > nums[i] && numCount.count(other)) {
                    totalCnt += (long)numCount[nums[i]]*numCount[other];
                totalCnt = totalCnt % M;
                }
            }
            
        }
        
        return (int)totalCnt;
    }
};
