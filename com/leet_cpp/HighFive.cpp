/*

1086. High Five

Given a list of scores of different students, 
return the average score of each student's top five scores in the order of each student's id.

Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score.  
The average score is calculated using integer division.

Example 1:
Input: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
Output: [[1,87],[2,88]]

Explanation: 
The average of the student with id = 1 is 87.
The average of the student with id = 2 is 88.6. But with integer division their average converts to 88.
 

Note:
1 <= items.length <= 1000
items[i].length == 2
The IDs of the students is between 1 to 1000
The score of the students is between 1 to 100
For each student, there are at least 5 scores

Easy

Amazon, Goldman Sachs
*/


class Solution {
public:
    
    static bool itemComp(vector<int>& A, vector<int>& B) {
        if (A[0] == B[0]) {
            return A[1] > B[1];
        } else {
            return A[0] < B[0];
        }
    }
    
    // 63%
    vector<vector<int>> highFive(vector<vector<int>>& items) {
        vector<vector<int>> ans;
        int n = items.size();
        if (n == 0) return ans;
        
        sort(items.begin(), items.end(), itemComp);
        
        int curId = items[0][0];
        int total = items[0][1];
        int cnt = 1;
        
        for (int i = 1; i < n; ++i) {
            if (items[i][0] == curId) {
                if (cnt >= 5) {
                    continue;
                }
                
                cnt++;
                total += items[i][1];
            } else {
                ans.push_back(vector<int>{curId, total/cnt});
                curId = items[i][0];
                total = items[i][1];
                cnt = 1;
            }
        }
        
        ans.push_back(vector<int>{curId, total/cnt});
        
        return ans;
    }
};
