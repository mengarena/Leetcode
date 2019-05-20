/*

539. Minimum Time Difference

Given a list of 24-hour clock time points in "Hour:Minutes" format, 
find the minimum minutes difference between any two time points in the list.

Example 1:
Input: ["23:59","00:00"]
Output: 1

Note:
The number of time points in the given list is at least 2 and won't exceed 20000.
The input time is legal and ranges from 00:00 to 23:59.


Medium
*/

class Solution {
public:
    
    // 99%
    // Strategy: since within 24 hours, we only have 24*60 mins
    // Use bit (here use bool) to set the existence of each min
    // Then check the distance between consecutive mins
    int findMinDifference(vector<string>& timePoints) {
        int n = 24*60;
        vector<bool> mins(n, false);
        if (timePoints.size() <= 1) return 0;
        
        for (auto time:timePoints) {
            int idx = time2int(time);
            mins[idx] = true; 
        }
        
        int actCount = 0;
        for (int i=0; i<n; i++) {
            if (mins[i]) actCount++;
        }
        
        if (actCount < timePoints.size()) return 0;  // Duplicates exist
        
        int ans = INT_MAX;
        int prevIdx = -1, firstIdx = -1;
        int i = 0;
        
        while (i < n && mins[i] == false) i++;
        prevIdx = i;
        firstIdx = i;
        i++;
        
        while (i < n) {
            if (mins[i] == false) {
                i++;
                continue;
            }
            
            ans = min(ans, i-prevIdx);
            if (ans == 1) return ans;
            
            prevIdx = i;
            i++;
        }
        
        ans = min(ans, firstIdx+n-prevIdx);
        return ans;
    }
    
    // Convert time to the index in the 24*60 vector
    int time2int(string& time) {
        int idx = 0;
        int pos1 = time.find(':');
        idx += stoi(time.substr(0, pos1))*60;
        idx += stoi(time.substr(pos1+1));
        return idx;
    }
};
