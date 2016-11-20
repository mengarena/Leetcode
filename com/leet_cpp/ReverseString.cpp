//344. Reverse String
//
//Write a function that takes a string as input and returns the string reversed.
//
//Example:
//Given s = "hello", return "olleh".
//

//Easy
class Solution {
public:
    string reverseString(string s) {
        string sRet = "";
        int n = s.length();  //size() also works
        for (int i=n-1; i>=0; i--) {
            sRet.push_back(s[i]);   //sRet += s[i];
        }
        
        return sRet;
    }
    
    
    string reverseStringA(string s) {
        string sRet = "";
        int n = s.size();
        for (int i=0; i<n/2; i++) {
            swap(s[i], s[n-i-1]);
        }
        
        return s;
    }
    
};
