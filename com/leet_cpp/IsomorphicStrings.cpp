/*
205. Isomorphic Strings

Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. 
No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.
*/

//LinkedIn
//Easy

class Solution {
public:
    bool isIsomorphic(string s, string t) {
        if ((s.size() == 0) && (t.size() == 0)) return true;
        if ((s.size() == 0) ^ (t.size() == 0)) return false;
        unordered_map<char, int> mpS;
        unordered_map<char, int> mpT;
        
        for (int i=0; i<s.length(); i++) {
            if (mpS.find(s[i]) != mpS.end()) {
                if (mpT.find(t[i]) == mpT.end()) return false;
                if (mpT[t[i]] != mpS[s[i]]) return false;
            } else if (mpT.find(t[i]) != mpT.end()) {
                return false;
            } else {
                mpS.insert(pair<char, int>(s[i], i));
                mpT.insert(pair<char, int>(t[i], i));
            }
        }
        
        return true;
    }
};

