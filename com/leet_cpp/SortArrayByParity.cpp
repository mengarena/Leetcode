/*

905. Sort Array By Parity

Given an array A of non-negative integers, return an array consisting of all the even elements of A, 
followed by all the odd elements of A.

You may return any answer array that satisfies this condition.

Example 1:
Input: [3,1,2,4]
Output: [2,4,3,1]
The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 
Note:
1 <= A.length <= 5000
0 <= A[i] <= 5000

Easy
*/

class Solution {
public:
    // 95%
    vector<int> sortArrayByParity(vector<int>& A) {
        int n = A.size();
        int i = 0;
        int j = n-1;
        int tmp;
        
        while (i < j) {
            if (A[i] % 2 == 1) {
                tmp = A[j];
                A[j] = A[i];
                A[i] = tmp;
                j--;
            } else {
                i++;
            }
        }
        
        return A;
    }
};
