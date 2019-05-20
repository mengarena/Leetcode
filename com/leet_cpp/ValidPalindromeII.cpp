/*

680. Valid Palindrome II

Given a non-empty string s, you may delete *at most* one character. 
Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True

Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.

Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.

Easy
*/


class Solution {
public:

    // 83%
    bool validPalindrome(string s) {
        int i = 0, j = s.length()-1;
        
        while (i<j) {
            if (s[i] != s[j]) {
                if (isPalindrome(s, i+1, j) || isPalindrome(s, i, j-1)) {  // Try deleting one of them
                    return true;
                } else {
                    return false;
                }
            }
            
            i++;
            j--;
        }
        return true;
    }
    
    bool isPalindrome(string& s, int l, int r) {
        while (l < r) {
            if (s[l] != s[r]) return false;
            l++;
            r--;
        }
        return true;
    }
};
