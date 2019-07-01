/*

923. 3Sum With Multiplicity

Given an integer array A, and an integer target, 
return the number of tuples i, j, k  such that i < j < k and A[i] + A[j] + A[k] == target.

As the answer can be very large, return it modulo 10^9 + 7.

Example 1:

Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8
Output: 20
Explanation: 
Enumerating by the values (A[i], A[j], A[k]):
(1, 2, 5) occurs 8 times;
(1, 3, 4) occurs 8 times;
(2, 2, 4) occurs 2 times;
(2, 3, 3) occurs 2 times.

Example 2:

Input: A = [1,1,2,2,2,2], target = 5
Output: 12
Explanation: 
A[i] = 1, A[j] = A[k] = 2 occurs 12 times:
We choose one 1 from [1,1] in 2 ways,
and two 2s from [2,2,2,2] in 6 ways.

Note:

3 <= A.length <= 3000
0 <= A[i] <= 100
0 <= target <= 300

Medium
*/


class Solution {
public:
    
    // 80%
    // Strategy:  calculate the count of each number; later directly use the counts to calculate the number of combinations
    // Calcuation based on different scenarios of a, b, c 
    int threeSumMulti(vector<int>& A, int target) {
        unordered_map<int, int> m;
        
        for (auto num:A) m[num]++;
        
        long M = pow(10, 9) + 7;
        
        sort(A.begin(), A.end(), less<int>());
        int n = A.size();
        unsigned long long cnt = 0;
        
        for (int i=0; i<n-2;) {
            int a = A[i];
            unsigned long long cnta = m[a];
            
            for (int j=i+1; j<n-1;) {
                int b = A[j];
                unsigned long long cntb = m[b];
                int c = target-a-b;
                
                if (c < b) break;  // c must >= b, otherwise the case (i.e. a c b or c a b) must have already been covered, 
                                   // don't need to check again
                unsigned long long cntc = m[c];
                
                if (a == b) {
                    if (c == a) {
                        cnt += cnta*(cnta-1)*(cnta-2)/(3*2);    // C(Na, 3)
                    } else {
                        cnt += cnta*(cnta-1)/2*cntc;            // C(Na, 2) * Nc
                    }
                } else {
                    if (c == b) {
                        cnt += cnta*(cntb)*(cntb-1)/2;          // Na * C(Nb, 2)
                    } else {
                        cnt += cnta*cntb*cntc;                  // Na * Nb * Nc
                    }
                }
                
                j++;
                while (j < n-1 && A[j] == b) j++;  // Skip the same values of b
            }
            
            i++;
            while (i < n-2 && A[i] == a) i++;   // Skip the same values of a
        }
        
        return cnt % M;
    }
};
