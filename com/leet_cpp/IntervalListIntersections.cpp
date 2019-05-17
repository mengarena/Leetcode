/*
986. Interval List Intersections

Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  
The intersection of two closed intervals is a set of real numbers that is either empty, 
or can be represented as a closed interval.
For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

 

Example 1:



Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
 

Note:

0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
NOTE: input types have been changed on April 15, 2019. 
Please reset to default code definition to get new method signature.

Medium:
Uber
*/

class Solution {
public:
    // 43%
    // Complixity: O(m+n) --- m, n are sizes of A and B
    vector<vector<int>> intervalIntersection(vector<vector<int>>& A, vector<vector<int>>& B) {
        int nA = A.size();
        int nB = B.size();
        int i = 0;
        int j = 0;
        vector<vector<int>> ans;
        
        while (i < nA && j < nB) {            
            int left = max(A[i][0], B[j][0]);
            int right = min(A[i][1], B[j][1]);
            
            if (left <= right) {
                vector<int> seg;
                seg.push_back(left); 
                seg.push_back(right);
                ans.push_back(seg);
            }
            
            if (right == A[i][1]) i++;
            if (right == B[j][1]) j++;

        }
        
        return ans;
    }


    // 17%
    vector<vector<int>> intervalIntersection(vector<vector<int>>& A, vector<vector<int>>& B) {
        int nA = A.size();
        int nB = B.size();
        int i = 0;
        int j = 0;
        vector<vector<int>> ans;
        
        while (i < nA && j < nB) {
            if (A[i][1] < B[j][0]) {
                i++;
                continue;
            }
            
            if (B[j][1] < A[i][0]) {
                j++;
                continue;
            }
            
            int left = max(A[i][0], B[j][0]);
            int right = min(A[i][1], B[j][1]);
            
            vector<int> seg;
            seg.push_back(left); 
            seg.push_back(right);
            ans.push_back(seg);
            
            if (A[i][1] > B[j][1]) {
                j++;
            } else if (A[i][1] < B[j][1]) {
                i++;
            } else { // A[i][1] == B[j][1]
                i++;
                j++;
            }
        }
        
        return ans;
    }
};
