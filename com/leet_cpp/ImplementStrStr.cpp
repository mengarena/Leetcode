//28. Implement strStr()
//
//Implement strStr().
//
//Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
//
//Pocket Gems, Microsoft, Apple, Facebook

//Easy

class Solution {
public:
    
    
    int strStrK(string haystack, string needle) {
        for (int i=0; ; i++) {
            for (int j=0; ;j++) {
                if (j == needle.length()) return i;
                
                if (i+j == haystack.length()) return -1;
                
                if (needle[j] != haystack[i+j]) break;
            }
        }
    }
    
    
    
    vector<int> preProcessPattern(string ptrn) {
        vector<int> ans(ptrn.length()+1, 0);
        
        int i = 0, j = -1;
        int ptrnLen = ptrn.length();
        
        ans[i] = j;
        
        while (i < ptrnLen) {
            while (j >= 0 && ptrn[i] != ptrn[j]) {
                j = ans[j];
            }
            
            i++;
            j++;
            ans[i] = j;
        }
        
        return ans;
    }

    int strStr(string haystack, string needle) {
        if (needle.length() == 0) return 0;
        if (haystack.length() < needle.length()) return -1;
        int i = 0, j = 0;
        
        vector<int> b = preProcessPattern(needle);
        
        while (i < haystack.length()) {
            while (j >= 0 && haystack[i] != needle[j]) {
                j = b[j];
            }
            
            i++;
            j++;
            
            if (j == needle.length()) {
                return i - needle.length();
            }
        }
        
        return -1;
    }
};
