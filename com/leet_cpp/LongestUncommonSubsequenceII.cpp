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
        int res = -1, j = 0, n = strs.size();
        for (int i = 0; i < n; ++i) {
            for (j = 0; j < n; ++j) {
                if (i == j) continue;
                if (isSubsequence(strs[i], strs[j])) break;
            }
            if (j == n) res = max(res, (int)strs[i].size());   // when j == n, means strs[i]'s isSubsequence() all get False
        }
        return res;
    }
    
    bool isSubsequence(string& cand, string& noncand) {
        int i = 0;
        for (char c : noncand) {
            if (c == cand[i]) ++i;
            if (i == cand.size()) break;
        } 
        return i == cand.size();
    }
};
