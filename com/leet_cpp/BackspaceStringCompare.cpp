/*

844. Backspace String Compare

Given two strings S and T, return if they are equal when both are typed into empty text editors. 
# means a backspace character.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
Note:

1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.
Follow up:

Can you solve it in O(N) time and O(1) space?


Easy
*/

class Solution {
public:
    // 96%
    bool backspaceCompare(string S, string T) {
        int idxS = -1;
        int idxT = -1;
        int i = 0;
        int j = 0;
        int nS = S.length();
        int nT = T.length();
        
        while (i < nS && !isalpha(S[i])) i++;
        
        for (; i < nS; ++i) {
            if (isalpha(S[i])) {
                idxS++;
                S[idxS] = S[i];
            } else if (S[i] == '#') {
                idxS = max(-1, idxS-1);
            }
        }
        
        while (j < nT && !isalpha(T[j])) j++;
        
        for (; j < nT; ++j) {
            if (isalpha(T[j])) {
                idxT++;
                T[idxT] = T[j];
            } else if (T[j] == '#') {
                idxT = max(-1, idxT-1);
            }
        }
        
        if (idxS != idxT ||
            (idxS >= 0 && S.substr(0, idxS+1).compare(T.substr(0, idxT+1)))) {
             return false;   
        }
        
        return true;
    }
};
