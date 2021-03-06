/*
218. The Skyline Problem

A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. 
Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), 
write a program to output the skyline formed by these buildings collectively (Figure B).

 Buildings Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], 
where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. 
It is guaranteed that 0 <= Li, Ri <= INT_MAX, 0 < Hi <= INT_MAX, and Ri - Li > 0. 
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. 
A key point is the left endpoint of a horizontal line segment. 
Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, 
and always has zero height. 
Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:
The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. 
For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; 
the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]

Microsoft, Google, Facebook, Twitter, Yelp

Hard
*/

class Solution {
public:

    vector<pair<int, int>> getSkyline(vector<vector<int>>& buildings) {
        vector<pair<int, int>> vecSkyline;
        
        if (buildings.size() == 0 || buildings[0].size() == 0) return vecSkyline;
        
        int i;
        int n = buildings.size();
        vector<pair<int, int>> vecHeight; 
        
        for (i=0; i<n; i++) {
            vecHeight.push_back(make_pair(buildings[i][0], -buildings[i][2]));
            vecHeight.push_back(make_pair(buildings[i][1], buildings[i][2]));
        }
        
        sort(vecHeight.begin(), vecHeight.end());  //Small -> Large
        
        multiset<int> setHeight;  //Default order: Small -> Large

        setHeight.insert(0);   // Make sure when all removed, still has one (i.e. 0) remained. 
                               // This is for the last point in result
        
        int curHeight = 0;
        int prevHeight = 0;
        
        for (i=0; i<vecHeight.size(); i++) {
            pair<int, int> tmpPair = vecHeight[i];

            if (tmpPair.second < 0) {    //This height comes into play
                setHeight.insert(-tmpPair.second);
            } else {    // The effect of this height should be removed
                setHeight.erase(setHeight.find(tmpPair.second));
            }
            
            curHeight = *(setHeight.rbegin());  //Get current Largest height
            
            if (curHeight != prevHeight) {
                vecSkyline.push_back(pair<int, int>(tmpPair.first, curHeight));
                prevHeight = curHeight;
            }
        }
        
        return vecSkyline;
        
    }
};    
    
    
class Solution {
public:
    // x-axis value: ascending; 
    // if x-axis values are same, height ascending 
    //(since height could be negative, so actually larger positive height will come first)
    static bool mycmp(const vector<int>& A, const vector<int>& B) {
        if (A[0] == B[0]) {
            return A[1] < B[1]; 
        } else {
            return A[0] < B[0];
        }
    }
    
    vector<vector<int>> getSkyline(vector<vector<int>>& buildings) {
        vector<vector<int>> ans;
        multiset<int> heights;
        
        vector<vector<int>> mybuildings;
        for (auto building:buildings) {
            mybuildings.push_back({building[0], -building[2]});
            mybuildings.push_back({building[1], building[2]});
        }
        
        sort(mybuildings.begin(), mybuildings.end(), mycmp);
        
        heights.insert(0);
        
        int prevHeight = 0;
        int curHeight = 0;
        
        for (auto mybuild:mybuildings) {
            if (mybuild[1] < 0) {
                heights.insert(-mybuild[1]);
            } else {
                auto it = heights.lower_bound(mybuild[1]);
                heights.erase(it);
            }
            
            curHeight = *(heights.rbegin());
            
            if (prevHeight != curHeight) {
                ans.push_back({mybuild[0], curHeight});
            }
            
            prevHeight = curHeight;
        }
        
        return ans;
    }
};

