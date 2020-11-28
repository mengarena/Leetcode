/*

862. Shortest Subarray with Sum at Least K

Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.

If there is no non-empty subarray with sum at least K, return -1.

Example 1:
Input: A = [1], K = 1
Output: 1

Example 2:
Input: A = [1,2], K = 4
Output: -1

Example 3:
Input: A = [2,-1,2], K = 3
Output: 3
 
Note:
1 <= A.length <= 50000
-10 ^ 5 <= A[i] <= 10 ^ 5
1 <= K <= 10 ^ 9

Hard

Goldman Sachs
*/

class Solution {
public:

    // 92%
    int shortestSubarray(vector<int>& A, int K) {
        int n = A.size();
        int ans = n+1;
        
        vector<int> sums(n+1, 0);  // Cumulative sum
        deque<int> dq;  // Keep the index of sums; in this deque, (from back-->front) sums[i] decreases
        
        for (int i=0; i<n; i++) sums[i+1] = sums[i] + A[i];
        
        for (int i=0; i<n+1; ++i) {
            // Continuously check the difference between sums[i] and 
            // the sums[x] which is indexed by the front() of the dq
            // the distance between i and dq.front() becomes smaller and smaller
            while (!dq.empty() && sums[i] - sums[dq.front()] >= K) {
                ans = min(ans, i-dq.front());
                dq.pop_front();
            }
            
            // This while make sure that the smaller value with larger index is saved
            // at the back of the deque
            // say if we don't have this while loop, the deque from back looks like:
            // 9(back) 8 7(front) say the corresponding sums are 88 (front) 99 111 (back)
            // here 88 < 99 and 88 < 111, so next time when a new sums[i] comes
            // since 88 has smaller value but larger index
            // It will more likely get a larger difference with next new sums[i],
            // and also gives smaller distance, 
            // so we can throw away the index for 99 111
            while (!dq.empty() && sums[i] <= sums[dq.back()]) dq.pop_back();
            
            dq.push_back(i);
        }
        
        return ans > n ? -1:ans;
    }
};

