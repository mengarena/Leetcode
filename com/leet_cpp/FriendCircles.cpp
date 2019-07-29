/*

547. Friend Circles

This question is very similar to:
https://github.com/mengarena/Leetcode/blob/master/com/leet_cpp/MostStonesRemovedWithSameRowColumn.cpp

There are N students in a class. Some of them are friends, while some are not. 
Their friendship is transitive in nature. 
For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. 
And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. 
If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. 
And you have to output the total number of friend circles among all the students.

Example 1:
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.

Example 2:
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.

Note:
N is in range [1,200].
M[i][i] = 1 for all students.
If M[i][j] = 1, then M[j][i] = 1.


Medium
*/


// Strategy:  To find how many islands exist.  Each island is a friend circle
// In an "island", the two points not necessary to be direct neighbors, 
// they could be on same row or col, but not directly neightbored.
class Solution {
public:

    // 82%
    // Attention:  There are only n students, so at most n groups
    // in the Matrix M, points at (x, x) is always 1 (i.e. a person is a friend to himself)
    int findCircleNum(vector<vector<int>>& M) {
        int n = M.size();
        int groups = n;
        vector<int> roots(n, 0);
        
        for (int i=0; i<n; ++i) roots[i] = i;
        
        for (int i=0; i<n; ++i) {
            for (int j=i+1; j<n; ++j) {  // Starts from i+1 to avoid checking both M[i][j], M[j][i] when i and j are same
                if (M[i][j]) {
                    int rooti = findRoot(i, roots);
                    int rootj = findRoot(j, roots);
                    if (rooti != rootj) {
                        roots[rooti] = rootj;
                        groups--;
                    }
                }
            }
        }
        
        return groups;
    }
    
    int findRoot(int x, vector<int>& roots) {
        if (x != roots[x]) roots[x] = findRoot(roots[x], roots);
        return roots[x];
    }
 
    int findRoot(int x, vector<int>& roots) {
	   	   while (x != roots[x]) {
				        roots[x] = roots[roots[x]];
				        x = roots[x];
			     }
			
			     return x;
    }
 
 

    // 7%
    int findCircleNum(vector<vector<int>>& M) {
        islands = 0;
        int m = M.size();
        int n = M[0].size();
        
        for (int i=0; i<m; ++i) {
            for (int j=0; j<n; ++j) {
                if (M[i][j]) {
                    unite(i, ~j);
                }
            }
        }
        
        return islands;
    }
    
    int findRoot(int x) {
        if (!roots.count(x)) {
            roots[x] = x;
            islands++;
        }
        if (x != roots[x]) roots[x] = findRoot(roots[x]);
        return roots[x];
    }
    
    void unite(int x, int y) {
        x = findRoot(x), y = findRoot(y);
        if (x != y) {
            roots[x] = y;
            islands--;
        }
    }
    
private:
    unordered_map<int, int> roots;   // <x, x's root>
    int islands;
};
