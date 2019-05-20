/*

713. Subarray Product Less Than K

Your are given an array of positive integers nums.

Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

Example 1:
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.

Note:

0 < nums.length <= 50000.
0 < nums[i] < 1000.
0 <= k < 10^6.
*/


class Solution {
public:

    // 85%
    int numSubarrayProductLessThanK(vector<int>& nums, int k) {
        int n = nums.size();
        int cnt = 0;
        int i=0, j;
        int prod = 1;
        
        while (i<n && nums[i] >= k) i++;
        
        for (j=i; j<n; ++j) {
            prod *= nums[j];
            
            while (i<=j && prod >= k) prod /= nums[i++];
            
            cnt += j-i+1;   // j-i+1 is the number of valid subarray ending at position j
        }
            
        return cnt;
    }


    // TLE
    int numSubarrayProductLessThanK(vector<int>& nums, int k) {
        int n = nums.size();
        vector<vector<int>> prods(n, vector<int>(n, k+1));
        int cnt = 0;
        
        for (int i=0; i<n; ++i) {
            if (nums[i] < k) {
                cnt++;
                prods[i][i] = nums[i];
            }
        }
        
        for (int len=2; len<=n; ++len) {
            for (int i=0; i<=n-len; ++i) {
                int j = i+len-1;
                if (prods[i+1][j] < k && nums[i] < k && prods[i+1][j]*nums[i] < k) {
                    cnt++;
                    prods[i][j] = prods[i+1][j]*nums[i];
                } else if (prods[i][j-1] < k && nums[j] < k && prods[i][j-1]*nums[j] < k) {
                    cnt++;
                    prods[i][j] = prods[i][j-1]*nums[j];
                } else {
                    prods[i][j] = k+1;
                }               
            }
        }
                    
        return cnt;
    }
};
