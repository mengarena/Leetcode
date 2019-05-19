/*

947. Most Stones Removed with Same Row or Column

On a 2D plane, we place stones at some integer coordinate points.  
Each coordinate point may have at most one stone.

Now, a move consists of removing a stone that shares a column or row with another stone on the grid.

What is the largest possible number of moves we can make?

 

Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Example 2:

Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Example 3:

Input: stones = [[0,0]]
Output: 0
 

Note:

1 <= stones.length <= 1000
0 <= stones[i][j] < 10000


Medium - Hard
*/


class Solution {
public:
    // 94%
    /* 
      Analysis:
      The stones on the same row, same column could form an island (the stones not necessary to be direct neighbors)
      So the problem is to find the number of islands.
      After those removes, for each island, one stone could remain, so the possible #move = #stones - #island
      That's why use Union Find.
    
      Strategy: Treat row and column of each stone separately.
      a) The stones with same row could be unioned; the stone with same column could also be unioned;
      b) The row and column of the same stone could also be unioned.
    
      Based on the characteristics of Union Find, condition a) is naturally satisfied, 
      so we don't need to Union(rowx, rowx), or Union(coly, coly) because the two rowx (or two coly) are same,
      they are natually unioned.
      So we only need to union the row and column of each stone.
    */
    int removeStones(vector<vector<int>>& stones) {
        islands = 0;
        
        // Unite the row and col of each stone
        for (auto pos:stones) {
            unite(pos[0], ~pos[1]);   // here use "~" to differentiate row and col  
                                      //(otherwise, row and col might have same positive values)
        }

        return stones.size() - islands;
    }
    
    int findRoot(int x) {
        if (!roots.count(x)) {
            roots[x] = x;
            islands++;
        }
        
        // traverse until the final root, which will be the root of x
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
