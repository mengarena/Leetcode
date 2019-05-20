/*

557. Reverse Words in a String III

Given a string, you need to reverse the order of characters in each word within 
a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"

Note: In the string, each word is separated by single space and there will not be any extra space in the string.

Easy
*/

class Solution {
public:

    // 32%
    string reverseWords(string s) {
        int i = -1;
        for (int j=0; j<s.length(); ++j) {
            if (s[j] == ' ') {
                if (i != -1) reverse(s.begin()+i, s.begin()+j);
                i = -1;
            } else {
                if (i == -1) i = j;
            }
        }
        
        if (i != -1) reverse(s.begin()+i, s.end());
        return s;
        
    }
};
