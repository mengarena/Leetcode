/*

796. Rotate String

We are given two strings, A and B.

A shift on A consists of taking string A and moving the leftmost character to the rightmost position. 
For example, if A = 'abcde', then it will be 'bcdea' after one shift on A. 
Return True if and only if A can become B after some number of shifts on A.

Example 1:
Input: A = 'abcde', B = 'cdeab'
Output: true

Example 2:
Input: A = 'abcde', B = 'abced'
Output: false
Note:

A and B will have length at most 100.

Easy
*/

class Solution {
public:

    // 92%
    bool rotateString(string A, string B) {
        if (A.length() != B.length()) return false;
        if (A == B) return true;
        
        size_t firstPos = 0;
        
        while (true) {
            firstPos = B.find_first_of(A[0], firstPos);
            if (firstPos != string::npos) {
                string newB = B.substr(firstPos) + B.substr(0, firstPos);
                if (newB == A) return true;
                firstPos++;
            } else {
                return false;
            }
        }
        
        return false;
    }
};
