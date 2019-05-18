/*

697. Degree of an Array

Given a non-empty array of non-negative integers nums, 
the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, 
that has the same degree as nums.

Example 1:
Input: [1, 2, 2, 3, 1]
Output: 2
Explanation: 
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.
Example 2:
Input: [1,2,2,3,1,4,2]
Output: 6
Note:

nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.


Easy
*/

class Solution {
public:

    // 36%
    int findShortestSubArray(vector<int>& nums) {
        unordered_map<int, int> freqs;   // num, frequency
        unordered_map<int, pair<int, int>> poss;  // num, <first, last position>
        int maxFreq = 0;
        int minRange = INT_MAX;
        
        for (int i=0; i<nums.size(); ++i) {
            if (!freqs.count(nums[i])) {
                freqs[nums[i]] = 1;
                poss[nums[i]] = make_pair(i, i);
            } else {
                freqs[nums[i]]++;
                poss[nums[i]].second = i;
            }
            
            if (maxFreq <= freqs[nums[i]]) {
                maxFreq = freqs[nums[i]];
            }
        }
        
        for (auto num:nums) {
            if (maxFreq == freqs[num]) {
                minRange = min(minRange, poss[num].second - poss[num].first + 1); 
            }
        }
        
        return minRange;
    }
};
