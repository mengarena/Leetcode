/*

859. Buddy Strings

Given two strings A and B of lowercase letters, 
return true if and only if we can swap two letters in A so that the result equals B.

Example 1:
Input: A = "ab", B = "ba"
Output: true

Example 2:
Input: A = "ab", B = "ab"
Output: false

Example 3:
Input: A = "aa", B = "aa"
Output: true

Example 4:
Input: A = "aaaaaaabc", B = "aaaaaaacb"
Output: true

Example 5:
Input: A = "", B = "aa"
Output: false
 
Note:
0 <= A.length <= 20000
0 <= B.length <= 20000
A and B consist only of lowercase letters.

Easy
*/


class Solution {
public:

    // 26%
    bool buddyStrings(string A, string B) {
        if (A.length() != B.length() || A.length() <= 1) return false;
        vector<int> diffPos;
        unordered_map<char, int> m;
        int maxCount = 0;
        
        for (int i=0; i<A.length(); ++i) {
            if (A[i] != B[i]) diffPos.push_back(i);
            m[A[i]]++;
            maxCount = max(maxCount, m[A[i]]);
        }
        
        if (A == B && maxCount >= 2) return true;
        
        if (diffPos.size() != 2) return false;
        
        if (A[diffPos[0]] == B[diffPos[1]] && A[diffPos[1]] == B[diffPos[0]]) return true;
        
        return false;
    }
};
