/*

532. K-diff Pairs in an Array

Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. 
Here a k-diff pair is defined as an integer pair (i, j), 
where i and j are both numbers in the array and their absolute difference is k.

Example 1:
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.

Example 2:
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).

Example 3:
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).

Note:
The pairs (i, j) and (j, i) count as the same pair.
The length of the array won't exceed 10,000.
All the integers in the given input belong to the range: [-1e7, 1e7].

Easy
*/


class Solution {
public:
    // 72%
    int findPairs(vector<int>& nums, int k) {
        if (k < 0) return 0;
        unordered_map<int, int> freqs;
        int count = 0;
        
        for (auto num:nums) {
            if (!freqs.count(num)) {
                freqs[num] = 1;
            } else {
                freqs[num]++;
            }
        }
        
        for (auto it=freqs.begin(); it!=freqs.end(); ++it) {
            int base = it->first;
            if (k == 0) {
                if (freqs[base] > 1) count++;
            } else {
                if (freqs.count(base+k)) count++;
            }
        }
        
        return count;
    }

    // 7 %
    int findPairs(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end(), less<int>());
        int count = 0;
        int n = nums.size();
        
        for (int i=0; i<n-1; ++i) {
            for (int j=i+1; j<n; ++j) {
                if (nums[j]-nums[i] == k) {
                    count++;
                    break;   // Unique, so break
                }
            }
            while (i+1<n-1 && nums[i+1] == nums[i]) i++;
        }
        
        return count;
    }
};
