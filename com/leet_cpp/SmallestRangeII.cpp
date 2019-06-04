/*

910. Smallest Range II

Given an array A of integers, for each integer A[i] we need to choose either x = -K or x = K, and add x to A[i] (only once).

After this process, we have some array B.

Return the smallest possible difference between the maximum value of B and the minimum value of B.

Example 1:
Input: A = [1], K = 0
Output: 0
Explanation: B = [1]

Example 2:
Input: A = [0,10], K = 2
Output: 6
Explanation: B = [2,8]

Example 3:
Input: A = [1,3,6], K = 3
Output: 3
Explanation: B = [4,6,3]
 
Note:
1 <= A.length <= 10000
0 <= A[i] <= 10000
0 <= K <= 10000

Medium

Adobe
*/


class Solution {
public:
    
    // 98%
    // Strategy: After sorting, there is a position in the array
    // All the numbers before that position, should + K
    // All the numbers after that position, should -K,
    // Then could possibly reduce the difference between min and max
    // Say the position is i,
    // After the operation described, A[i]+K, A[i+1]-K
    // A[i]+K will be the max of the first segment; 
    // A[i+1]-K will be the smallest of the second segment
    // and A[0]+K will be the smallest of the first segment
    // A[n-1]-K will be the largest of the second segment
    // Then try every possible position to the candiate for positioin i
    int smallestRangeII(vector<int>& A, int K) {
        if (A.size() <= 1) return 0;

        int n = A.size();
        sort(A.begin(), A.end());
        int minV = A[0];
        int maxV = A[n-1];
        int ans = maxV - minV;
        
        int left = minV + K;
        int right = maxV - K;
        
        for (int i = 0; i<n-1; ++i) {
            minV = min(left, A[i+1] - K);
            maxV = max(right, A[i] + K);
            ans = min(ans, maxV - minV);
        }
        
        return ans;
    }
};
