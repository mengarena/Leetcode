/*

904. Fruit Into Baskets

In a row of trees, the i-th tree produces fruit with type tree[i].

You start at any tree of your choice, then repeatedly perform the following steps:

Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
Move to the next tree to the right of the current tree.  
If there is no tree to the right, stop.
Note that you do not have any choice after the initial choice of starting tree: 
you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.

You have two baskets, and each basket can carry any quantity of fruit, 
but you want each basket to only carry one type of fruit each.

What is the total amount of fruit you can collect with this procedure?

Example 1:

Input: [1,2,1]
Output: 3
Explanation: We can collect [1,2,1].
Example 2:

Input: [0,1,2,2]
Output: 3
Explanation: We can collect [1,2,2].
If we started at the first tree, we would only collect [0, 1].
Example 3:

Input: [1,2,3,2,2]
Output: 4
Explanation: We can collect [2,3,2,2].
If we started at the first tree, we would only collect [1, 2].
Example 4:

Input: [3,3,3,1,2,1,1,2,3,3,4]
Output: 5
Explanation: We can collect [1,2,1,1,2].
If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 

Note:

1 <= tree.length <= 40000
0 <= tree[i] < tree.length


Medium
*/


// Note: The wording is very bad in this question, 
//       Actually, it is asking: return the longest subarray's length which only contains 2 different elements


class Solution {
public:
    // 90%
    int totalFruit(vector<int>& tree) {
        if (tree.empty()) return 0;
        int first, second, prev;
        int firstPosS=-1, firstPosE=-1;
        int secondPosS = 1, secondPosE = -1;
        int maxLen = 0;
        int n = tree.size();
        
        first = tree[0];
        firstPosS = 0;
        firstPosE = 0;
        prev = first;
        
        for (int i=1; i<n; ++i) {
            if (tree[i] == first) {
                firstPosE = i;
                prev = first;
                continue;
            } 
        
            if (secondPosS == -1) {
                second = tree[i];
                secondPosS = i;
                secondPosE = i;
                prev = second;
                continue;
            }
            
            if (tree[i] == second) {
                secondPosE = i;
                prev = second;
                continue;
            }
            
            maxLen = max(maxLen, i-firstPosS);
            
            // not first, not second
            if (prev == first) {
                firstPosS = secondPosE + 1;
                secondPosS = i;
                secondPosE = i;
            } else {  // prev = second
                firstPosS = firstPosE + 1;
                firstPosE = secondPosE;
                first = second;
                secondPosS = i;
                secondPosE = i;
            }
            second = tree[i];
            prev = second;
        }
        
        if (firstPosS != -1) maxLen = max(maxLen, n-firstPosS);
        
        return maxLen;
    }
};
