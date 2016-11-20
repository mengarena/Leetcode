//77. Combinations
//
//Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
//
//For example,
//If n = 4 and k = 2, a solution is:
//
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//]
//

class Solution {
public:
    vector<vector<int>> combine(int n, int k) {
        vector<vector<int>> vecvec;
        
        if (k > n) return vecvec;
        
        vecvec = combineHelper(n, 1, k);
        
        return vecvec;
    }
    
    
    vector<vector<int>> combineHelper(int n, int nStartPos, int k) {
        vector<vector<int>> vecvec;
        
        if (k <= 0) return vecvec;
        
        for (int i=nStartPos; i<=n-k+1; i++) {
            vector<vector<int>> vecvecTmp = combineHelper(n, i+1, k-1);
            
            if (!vecvecTmp.empty()) {
                for (vector<vector<int>>::iterator it = vecvecTmp.begin(); it != vecvecTmp.end(); it++) {
                    (*it).insert(it->begin(), i);
                    vecvec.push_back(*it);
                }
            } else {
                vector<int> vecTmp;
                vecTmp.push_back(i);
                vecvec.push_back(vecTmp);
            }
        }
        
        return vecvec;
    }
};
