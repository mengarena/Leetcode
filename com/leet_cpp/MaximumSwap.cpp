/*

670. Maximum Swap

Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. 
Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.

Example 2:
Input: 9973
Output: 9973
Explanation: No swap.

Note:
The given number is in the range [0, 108]

Medium
*/


class Solution {
public:
    
    // 86%
    // Strategy: Find where s[i] < s[i+1] happens, then from i+1, find the last maximal value (if there are multiple)
    // From the beginning, search to find the first one which is smaller then the maximal value found above and exchange
    int maximumSwap(int num) {
        string s = to_string(num);
        int diffPos = -1;
        
        int n = s.length();
        
        for (int i = 0; i<n-1; ++i) {
            if (s[i] < s[i+1]) {
                diffPos = i+1;
                break;
            }
        }
        
        if (diffPos == -1) return num;
        
        int maxPos = diffPos;
        char maxVal = s[maxPos];
        
        for (int i = diffPos+1; i < n; ++i) {
            if (s[i] >= maxVal) {  // Attention, here use >=, not >. e.g. 1993,  should exchange 2nd 9 with 1
                maxVal = s[i];
                maxPos = i;
            }
        }
        
        for (int i=0; i<diffPos; ++i) {
            if (s[i] < s[maxPos]) {
                char tmp = s[i];
                s[i] = s[maxPos];
                s[maxPos] = tmp;
                break;
            }
        }
        
        return stoi(s);
    }
};
