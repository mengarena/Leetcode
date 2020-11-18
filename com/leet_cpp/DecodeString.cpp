/*
394. Decode String   

Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. 
Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. 
For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

Google
*/

class Solution {
public:
    string decodeString(string s) {
        if (s.empty()) return s;
        stack<int> stkCount;
        stack<string> stkStr;
        string sCur = "";
        int count = 0;
        
        for (int i=0; i<s.length(); i++) {
            char c = s[i];
            if (c >= '0' && c<= '9') {
                count = count*10 + (int)(c-'0');
            } else if (c == '[') {
                stkCount.push(count);
                stkStr.push(sCur);
                count = 0;
                sCur = "";
            } else if (c == ']') {
                count = stkCount.top();
                stkCount.pop();
                string sRepeat = sCur;
                sCur = stkStr.top();
                stkStr.pop();
                for(; count>=1; count--) sCur = sCur + sRepeat;
            } else {
                sCur.push_back(c);
            }
        }
        
        return sCur;
    }
};

