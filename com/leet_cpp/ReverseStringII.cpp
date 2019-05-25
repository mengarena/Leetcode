/*

541. Reverse String II

Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from 
the start of the string. 
If there are less than k characters left, reverse all of them. 
If there are less than 2k but greater than or equal to k characters, 
then reverse the first k characters and left the other as original.

Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"

Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]

Easy
*/

class Solution {
public:

    // 92%
    string reverseStr(string s, int k) {
        int i=0, j=k-1, pos;
        int n = s.length();
        
        while (j < n) {
            pos = j;
            while (i < j) {
                char c = s[i];
                s[i] = s[j];
                s[j] = c;
                i++;
                j--;
            }
            
            i = pos + k + 1;
            j = i + k-1;
        }
        
        if (i < n) {
            j = n-1;
            
            while (i < j) {
                char c = s[i];
                s[i] = s[j];
                s[j] = c;
                i++;
                j--;
            }
        }
        
        return s;
    }
};
