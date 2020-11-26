/*

522. Longest Uncommon Subsequence II

Given a list of strings, you need to find the longest uncommon subsequence among them. 
The longest uncommon subsequence is defined as the longest subsequence of one of these strings and 
this subsequence should not be any subsequence of the other strings.

A subsequence is a sequence that can be derived from one sequence by 
deleting some characters without changing the order of the remaining elements. 
Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.

The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence. 
If the longest uncommon subsequence doesn't exist, return -1.

Example 1:
Input: "aba", "cdc", "eae"
Output: 3
Note:

All the given strings' lengths will not exceed 10.
The length of the given list will be in the range of [2, 50].

Medium
*/

class Solution {
public:

    // 97%
    int findLUSlength(vector<string>& strs) {
        int n = strs.size();
        if (n == 0) return -1;
        unordered_map<string, int> m;   // string, len
        int candMaxLen = 0;
        
        for (int i=0; i<n; ++i) {
            m[strs[i]]++;
            candMaxLen = max(candMaxLen, static_cast<int>(strs[i].size()));
        }
        
        vector<string> cands;
        unordered_set<string> noncands;
        int candMaxLen1 = -1;
        
        for (auto it = m.begin(); it!=m.end(); ++it) {
            string s = it->first;
            if (it->second > 1) {
                noncands.insert(s);
            } else { 
                if (s.size() == candMaxLen) return candMaxLen;
                candMaxLen1 = max(candMaxLen1, static_cast<int>(s.size()));
                cands.push_back(s);
            }
        }
        
        sort(cands.begin(), cands.end(), [](string s, string t){ return s.length() > t.length(); });
        
        for (auto cand:cands) {
            for (auto noncand:noncands) {
                if (cand.length() >= noncand.length()) continue;
                if (!isSubsequence(cand, noncand)) return cand.length();
            }
        }
        
        return -1;
    }
    
    bool isSubsequence(string& cand, string& noncand) {
        int i=0, j;
        
        for (j=0; j<noncand.length(); ++j) {
            if (i < cand.length() && cand[i] == noncand[j]) i++;
        }
        
        return (i == cand.length()) && (j == noncand.length());
    }
};
