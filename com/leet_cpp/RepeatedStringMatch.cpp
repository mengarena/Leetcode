/*

686. Repeated String Match

Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. 
If no such solution, return -1.

For example, with A = "abcd" and B = "cdabcdab".

Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; 
and B is not a substring of A repeated two times ("abcdabcd").

Note:
The length of A and B will be between 1 and 10000.

Easy
*/


class Solution {
public:

    // 79%
    int repeatedStringMatch(string A, string B) {
        int nA = A.length();
        int nB = B.length();
        
        if (nA >= nB) {
            if (A.find(B) != string::npos) return 1;
            
            string AA = A + A;
            if (AA.find(B) != string::npos) return 2;
        } else {
            int baseCnt = nB / nA + (nB % nA == 0 ? 0:1);
            string AA = "";
            for (int i=1; i<=baseCnt; ++i) {
                AA += A;
            }
            
            if (AA.find(B) != string::npos) return baseCnt;
            AA += A;
            if (AA.find(B) != string::npos) return baseCnt+1;
        }
        
        return -1;
    }
};
