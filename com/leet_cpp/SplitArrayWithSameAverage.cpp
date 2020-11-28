/*

805. Split Array With Same Average

In a given integer array A, we must move every element of A to either list B or list C. (B and C initially start empty.)

Return true if and only if after such a move, it is possible that the average value of B is equal to the average value of C, 
and B and C are both non-empty.

Example :
Input: 
[1,2,3,4,5,6,7,8]
Output: true
Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have the average of 4.5.

Note:
The length of A will be in the range [1, 30].
A[i] will be in the range of [0, 10000].

Hard

Uber
*/

class Solution {
public:
    // 30%
    // Strategy:
    // If we could divide A into two sub arrays, one's length could be less than or equal to the other
    // Here we focus on the shorter one (size up to m)
    // Let say there are k numbers in the shorter subarray,
    // a) so, Sum of K numbers / K = total sum /n  (here Sum of K numbers must be integer), so
    // b) totalsum*k % n == 0  (not many k exists for satisfying this condition)
    // In this solution, we use a vector of hashset to remember the sums of all combinations from up to k numbers
    // Here, in ith hashset (i.e. sums[i], it records all the sums formed by i numbers from A
    // At the end, from these possible sums, 
    // check whether there is one sum[i] satisified the condition (i.e.  mentioined above a), b))
    bool splitArraySameAverage(vector<int>& A) {
        int n = A.size(); 
        int m = n/2;
        int totalSum = accumulate(A.begin(), A.end(), 0);
        
        bool isPossible = false;
        for (int i=0; i<n && !isPossible; ++i) {
            if (totalSum*i % n == 0) isPossible = true;
        }
        
        if (isPossible == false) return false;
        
        vector<unordered_set<int>> sums(m+1); // sums[i] is all possible sums formed by i numbers
        
        sums[0].insert(0);
        
        for (auto num:A) {
            for (int i=m; i>=1; --i) {
                for (auto sum:sums[i-1]) {
                    sums[i].insert(sum + num);
                }
            }
        }
        
        // Check whether there is a case the averge of K number is same as the averge of the entire array A
        for (int i=1; i<=m; ++i) {
            if (totalSum*i % n == 0 && sums[i].find(totalSum*i/n) != sums[i].end()) return true;
        }
        
        return false;
    }
};
