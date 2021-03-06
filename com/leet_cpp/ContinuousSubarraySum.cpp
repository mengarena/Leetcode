/*

523. Continuous Subarray Sum

Given a list of non-negative numbers and a target integer k,
write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, 
that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.

Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 
Note:

The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.

Medium
*/

class Solution {
public:
    // 99%
    // Complexity: O(n)
    bool checkSubarraySum(vector<int>& nums, int k) {
        int n = nums.size();
        unordered_map<int, int> m;  // sum till this point mod k, position
        int sum = 0;
        
        m[0] = -1;
        for (int i=0; i<n; ++i) {
            sum += nums[i];
            if (k != 0) sum %= k;
            
            if (!m.count(sum)) {
                m[sum] = i;
            } else {
                if (i - m[sum] > 1) return true;   // from x1, x2, x3, x4, x5,   
                                                   // if till x2 and till x4 has the same mod value to K, 
                                                   // then the x3, x4 should be the multipler of k
                                                   // so here > 1
            }
        }
        
        return false;
    }
    

    // 28%
    // Complexity: O(n^2)
    bool checkSubarraySum(vector<int>& nums, int k) {
        int n = nums.size();
        if (n < 2) return false;
        if (k == 1) return true;
        
        vector<int> sums(n+1, 0);
        
        for (int i=1; i<=n; ++i) {
            sums[i] = sums[i-1] + nums[i-1];
        }
        
        for (int i=0; i<=n-2; ++i) {
            for (int j=i+2; j<=n; ++j) {
                if (k == 0 && sums[j]-sums[i] == 0) {
                    return true;
                }
                if ((k != 0) && ((sums[j]-sums[i]) % k == 0)) return true;
            }
        }
        
        return false;
    }
};
