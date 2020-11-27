/*

974. Subarray Sums Divisible by K

Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.

Example 1:
Input: A = [4,5,0,-2,-3,1], K = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by K = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 
Note:
1 <= A.length <= 30000
-10000 <= A[i] <= 10000
2 <= K <= 10000

Medium
*/

class Solution {
public:

    // 56%
    int subarraysDivByK(vector<int>& A, int K) {
        int n = A.size();
        int sum = 0;
        unordered_map<int, int> m;   // sum, count
        
        for (auto a:A) {
            sum = (sum + a) % K;
            if (sum < 0) sum += K;
            
            m[sum]++;
        }
        
        int cnt = 0;
        
        for (auto mm:m) {
            if (mm.first == 0) {
                cnt += mm.second*(mm.second+1)/2;   // 1,2,3.....,cnt
            } else {
                cnt += mm.second*(mm.second-1)/2;   // C(cnt, 2)
            }
        }
        
        return cnt;
    }
};
