/*

1002. Find Common Characters

Given an array A of strings made only from lowercase letters, 
return a list of all characters that show up in all strings within the list (including duplicates).  
For example, if a character occurs 3 times in all strings but not 4 times, 
you need to include that character three times in the final answer.

You may return the answer in any order.

Example 1:

Input: ["bella","label","roller"]
Output: ["e","l","l"]

Example 2:

Input: ["cool","lock","cook"]
Output: ["c","o"]
 
Note:

1 <= A.length <= 100
1 <= A[i].length <= 100
A[i][j] is a lowercase letter

Easy
*/


class Solution {
public:

    // 47%
    // Use the first string as base, then check one by one to see how many common
    // characters remain
    vector<string> commonChars(vector<string>& A) {
        int n = A.size();
        vector<string> ans;
        
        if (n == 1) {
            for (auto c:A[0]) {
                ans.push_back(string(1, c));
            }
            return ans;
        }

        vector<int> charCnt(26, 0);

        for (auto c:A[0]) {
            charCnt[c-'a']++;
        }

        for (int i=1; i<n; ++i) {
            vector<int> tmp = charCnt;
            
            for (auto c:A[i]) {
                tmp[c-'a']--;
            }
            
            int zeroCnt = 0;
            for (int j=0; j<26; ++j) {
                if (tmp[j] == charCnt[j]) {
                    charCnt[j] = 0;
                } else if (charCnt[j] > 0 && tmp[j] < charCnt[j]) {
                    charCnt[j] = min(charCnt[j], charCnt[j] - tmp[j]);
                }
                
                if (charCnt[j] == 0) zeroCnt++;
            }
            
            if (zeroCnt == 26) break;
        }
        
        
        for (int i=0; i<26; ++i) {
            for (int j=1; j<=charCnt[i]; ++j) {
                ans.push_back(string(1,'a'+i));
            }
        }
        
        return ans;
    }
};
