/*
Given a matrix of M x N elements (M rows, N columns), 
return all elements of the matrix in diagonal order as shown in the below image.

Example:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

Output:  [1,2,4,7,5,3,6,8,9]

Explanation:

Note:
The total number of elements of the given matrix will not exceed 10,000.
*/

//Medium
//Google

class Solution {
public:
    vector<int> findDiagonalOrder(vector<vector<int>>& matrix) {
        int m = matrix.size();
        if (m == 0) return vector<int>();
        int n = matrix[0].size();
        
        vector<int> ans(m*n);
        
        int i=0;
        int j=0;
        int count = 0;
        bool up = true;
        
        while (count < m*n) {
            ans[count] = matrix[i][j];
            
            count++;
            
            if (up) {
                i--;
                j++;
                
                if (i < 0 && j <= n-1) {
                    i++;
                    up = !up;
                } else if (j > n-1) {
                    j--;
                    i++;
                    i++;
                    up = !up;
                }
                
            } else {
                i++;
                j--;
                
                if (j < 0 && i <= m-1) {
                    j++;
                    up = !up;
                } else if (i > m-1) {
                    i--;
                    j++;
                    j++;
                    up = !up;
                }
                
            }
            
        }
        
        return ans;
    }
};



