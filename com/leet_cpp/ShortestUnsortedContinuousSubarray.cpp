/*

581. Shortest Unsorted Continuous Subarray

Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, 
then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.

Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.

Easy
*/


class Solution {
public:
    
    // 25%
    // The candidate continuous subarray:
    // The first element should be larger than its next;  the last element should be smaller than its previous
    // Then get the max and min within the candidate subarray, to:
    // Compare from the original head until the "first element" with the minimal of the candidate subarray
    // Compare from the original end until the "last element" with the maximal of the candidate subarray
    // To find the real begin and end of the subarray
    int findUnsortedSubarray(vector<int>& nums) {
        int n = nums.size();
        int i = 0;
        int candStart = -1;
        
        while (i < n-1) {
            if (nums[i] <= nums[i+1]) {
                i++;
                continue;
            }
            
            candStart = i;
            break;
        }
        
        if (candStart == -1) return 0;
        
        i = n-1;
        int candEnd = -1;
        while (i > 0) {
            if (nums[i] >= nums[i-1]) {
                i--;
                continue;
            }
            
            candEnd = i;
            break;
        }
        
        int maxTmp = INT_MIN;
        int minTmp = INT_MAX;
        
        for (int i = candStart; i <= candEnd; ++i) {
            maxTmp = max(maxTmp, nums[i]);
            minTmp = min(minTmp, nums[i]);
        }
        
        candStart = 0;
        candEnd = n-1;
                
        while (candStart < n) {
            if (nums[candStart] > minTmp) break;
            candStart++;
        }
        
        while (candEnd > 0) {
            if (nums[candEnd] < maxTmp) break;
            candEnd--;
        }
        
        return candEnd - candStart + 1;
    }
};
