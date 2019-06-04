/*

907. Sum of Subarray Minimums

Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.

Since the answer may be large, return the answer modulo 10^9 + 7.

Example 1:

Input: [3,1,2,4]
Output: 17
Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.
 
Note:
1 <= A.length <= 30000
1 <= A[i] <= 30000

Medium

Amazon
*/



class Solution {
public:

    // 71%
    // Refer to: https://leetcode.com/problems/sum-of-subarray-minimums/discuss/178876/stack-solution-with-very-detailed-explanation-step-by-step
    // Here use a stack to remember the Previous Less Elmenent, and another stack to remember the Next Less Element
    // e.g. [3 7 8 4]
    // 3 has not PLE, 7 has PLE 3, 8 has PLE 7, 4 has PLE 3
    // 3 hs not NLE, 7 has NLE 4, 8 has NLE 8, 4 has no NLE
    // By knowing an element's PLE and NLE,
    // Say the distance between an element and it PLE and NLE is a and b
    // Total number of sub arrays with this element as its minimal value will be a*b, (so sum will be a*b*Value of this element)
    int sumSubarrayMins(vector<int>& A) {
        if (A.size() == 0) return 0;
        int n = A.size();
        vector<int> left(n, 0);  // Distance to previous less element
        vector<int> right(n, 0);  // Distance to next less element
        stack<pair<int, int>> stkPle, stkNle;  // <value, index>
        
        for (int i=0; i<n; ++i) {
            left[i] = i+1;
            right[i] = n-i;
        }
        
        // Get the Previous Less Element of each element
        for (int i=0; i<n; ++i) {
            while (!stkPle.empty() && stkPle.top().first > A[i]) {
                stkPle.pop();
            }
        
            left[i] = stkPle.empty() ? i+1:i-stkPle.top().second;
            stkPle.push(make_pair(A[i], i));
        }
        
        // Get the Next Less Element of each element
        for (int i=0; i<n; ++i) {
            while (!stkNle.empty() && stkNle.top().first > A[i]) {
                auto it = stkNle.top();
                stkNle.pop();
                right[it.second] = i-it.second;
            }
            
            stkNle.push(make_pair(A[i], i));
        }
        
        int NN = 1e9 + 7;
        int ans = 0;
        
        // left[i]*right[i]*A[i] is the sum with A[i] as minimal value of sub arraies
        for (int i=0; i<n; ++i) {
            ans = (ans + left[i]*right[i]*A[i]) % NN;
        }
        
        return ans;
    }

    // TLE (passed 99/100 test cases)
    // Strategy: Since it is required to calculate the sum of minimal values of sub array
    // We can use the minimal values of pivot
    // keep split the array by the minimal value, say (s, minIdx, e)
    // The result shuould be sum of minal of sumbarray of 
    // [s, minIdx-1] + [minIdx+1, e] + minVal * #number of subarrays contains the minValue
    int sumSubarrayMins(vector<int>& A) {
        if (A.size() == 0) return 0;
        return helper(A, 0, A.size()-1);
    }
    
    int helper(vector<int>& A, int s, int e) {
        int NN = 1000000007;
        if (s > e) return 0;
        if (s == e) return A[s];
        
        int minIdx = -1;
        int minVal = INT_MAX;
        
        for (int i=s; i<=e; ++i) {
            if (minVal > A[i]) {
                minIdx = i;
                minVal = A[i];
            }
        }
        
        long left = helper(A, s, minIdx-1);
        long right = helper(A, minIdx+1, e);
        long mid;
        if (minIdx == s) {
            mid = (e-minIdx+1)*minVal;
        } else if (minIdx == e) {
            mid = (e-s+1)*minVal;
        } else {
            mid = 1+ (minIdx-s) + (e-minIdx) + (minIdx-s)*(e-minIdx);
            mid = mid*minVal;
        }
        
        return (left + right + mid) % NN;
    }
};
