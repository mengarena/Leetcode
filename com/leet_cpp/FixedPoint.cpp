/*

1064. Fixed Point

Given an array A of distinct integers sorted in ascending order, return the smallest index i that satisfies A[i] == i.
Return -1 if no such i exists.

Example 1:
Input: [-10,-5,0,3,7]
Output: 3
Explanation: 
For the given array, A[0] = -10, A[1] = -5, A[2] = 0, A[3] = 3, thus the output is 3.

Example 2:
Input: [0,2,5,8,17]
Output: 0
Explanation: 
A[0] = 0, thus the output is 0.

Example 3:
Input: [-10,-5,3,4,7,9]
Output: -1
Explanation: 
There is no such i that A[i] = i, thus the output is -1.
 
Note:
1 <= A.length < 10^4
-10^9 <= A[i] <= 10^9


Easy

Uber
*/


class Solution {
public:

    // 99%
    int fixedPoint(vector<int>& A) {
        int i = 0;
        int j = A.size()-1;
        int candIdx = -1;
        
        while (i <= j) {
            int mid = i + (j-i)/2;
            if (A[mid] == mid) {
                candIdx = mid;
                j = mid-1;
            } else if (A[mid] > mid) {
                j = mid-1;
            } else {
                i = mid+1;
            }
        }
        
        return candIdx;
    }
};
