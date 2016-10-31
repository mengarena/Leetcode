/*
Find Right Interval

Given a set of intervals, for each of the interval i, 
check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, 
which can be called that j is on the "right" of i.

For any interval i, you need to store the minimum interval j's index, 
which means that the interval j has the minimum start point to build the "right" relationship for interval i. 
If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.

Note:
You may assume the interval's end point is always bigger than its start point.
You may assume none of these intervals have the same start point.

Example 1:
Input: [ [1,2] ]

Output: [-1]

Explanation: There is only one interval in the collection, so it outputs -1.

Example 2:
Input: [ [3,4], [2,3], [1,2] ]

Output: [-1, 0, 1]

Explanation: There is no satisfied "right" interval for [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point;
For [1,2], the interval [2,3] has minimum-"right" start point.

Example 3:
Input: [ [1,4], [2,3], [3,4] ]

Output: [-1, 2, -1]

Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point.
*/

/**
 * Definition for an interval.
 * struct Interval {
 *     int start;
 *     int end;
 *     Interval() : start(0), end(0) {}
 *     Interval(int s, int e) : start(s), end(e) {}
 * };
 */


//Medium


//ACC:

#include <vector>

using namespace std;

class Solution {
    
public:
    struct StartIdx {
        int start;
        int orgIdx;
        
        StartIdx(int st, int index): start(st), orgIdx(index) {}
        
        static bool sortStartIdx (StartIdx siA, StartIdx siB) {  //Should be static
            return siA.start < siB.start;    // order: small, large
        }
    };
    
    vector<int> findRightInterval(vector<Interval>& intervals) {
        vector<int> vRet;
        if (intervals.size() == 1) {
            vRet.push_back(-1);
            return vRet;
        }
        
        int n = intervals.size();
        vector<StartIdx> vSI;

        for (vector<int>::size_type i = 0; i != intervals.size(); ++i) {
            vSI.push_back(StartIdx(intervals[i].start, i));  //struct, no new
        }
        
        sort(vSI.begin(), vSI.end(), StartIdx::sortStartIdx);
        
        int target;
        int tmpIdx;
        
        for (vector<int>::size_type i = 0; i != intervals.size(); ++i) {
            target = intervals[i].end;
            
            tmpIdx = findMinLarger(vSI, target);
            
            vRet.push_back(tmpIdx);
        }
        
        return vRet;
    }
    
    int findMinLarger(vector<StartIdx>& vSI, int target) {
        int n = vSI.size();
        int i = 0;
        int j = n-1;
        int mid;
        
        while (i <= j) {
            mid = i + (j-i)/2;
            
            if (vSI[mid].start == target) {
                return vSI[mid].orgIdx;
            } else if (vSI[mid].start < target) {
                i = mid+1;
            } else {
                j = mid-1;
            }
        }
        
        if (i < n && vSI[i].start >= target) return vSI[i].orgIdx;
        
        return -1;
    }
};
