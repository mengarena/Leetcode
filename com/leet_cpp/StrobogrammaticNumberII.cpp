q/*
247. Strobogrammatic Number II

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].

Hint:

Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
*/

//Google
//Medium

class Solution {
public:
    vector<string> findStrobogrammaticHelper(int n, int currentLen) {
        vector<string> vec;
        
        if (currentLen == 0) {
            vec.push_back("");
            return vec;
        }
        
        if (currentLen == 1) {
            vec.push_back("0");
            vec.push_back("1");
            vec.push_back("8");
            return vec;
        }
        
        vector<string> vecTmp = findStrobogrammaticHelper(n, currentLen-2);
        
        for (int i=0; i<vecTmp.size(); i++) {
            if (currentLen != n) vec.push_back("0" + vecTmp[i] + "0");
            vec.push_back("1" + vecTmp[i] + "1");
            vec.push_back("8" + vecTmp[i] + "8");
            vec.push_back("6" + vecTmp[i] + "9");
            vec.push_back("9" + vecTmp[i] + "6");
        }
        
        return vec;
    }
    
    vector<string> findStrobogrammatic(int n) {
        return findStrobogrammaticHelper(n, n);
    }
};
