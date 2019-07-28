/*

896. Monotonic Array

An array is monotonic if it is either monotone increasing or monotone decreasing.

An array A is monotone increasing if for all i <= j, A[i] <= A[j].  
An array A is monotone decreasing if for all i <= j, A[i] >= A[j].

Return true if and only if the given array A is monotonic.

Example 1:

Input: [1,2,2,3]
Output: true
Example 2:

Input: [6,5,4,4]
Output: true
Example 3:

Input: [1,3,2]
Output: false
Example 4:

Input: [1,2,4,5]
Output: true
Example 5:

Input: [1,1,1]
Output: true
 
Note:

1 <= A.length <= 50000
-100000 <= A[i] <= 100000

Easy
*/


class Solution {
public:

    // 94%
    bool isMonotonic(vector<int>& A) {
        int n = A.size();
        if (n <= 2) return true;
        
        vector<int> AA;
        AA.push_back(A[0]);
        
        for (int i = 1; i<n; ++i) {
            if (A[i] != AA.back()) AA.push_back(A[i]);
        }
        
        int nn = AA.size();
        
        for (int i = 1; i<nn-1; ++i) {
            if ((AA[i-1]-AA[i]) * (AA[i] - AA[i+1]) < 0) return false;
        }
        return true;
    }
};
