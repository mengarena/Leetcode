/*

1010. Pairs of Songs With Total Durations Divisible by 60

In a list of songs, the i-th song has a duration of time[i] seconds. 

Return the number of pairs of songs for which their total duration in seconds is divisible by 60.  
Formally, we want the number of indices i < j with (time[i] + time[j]) % 60 == 0.

Example 1:
Input: [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60

Example 2:
Input: [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 
Note:
1 <= time.length <= 60000
1 <= time[i] <= 500

Easy

Amazon, Dropbox, Adobe, Goldman Sachs
*/


class Solution {
public:

    // 44%
    int numPairsDivisibleBy60(vector<int>& time) {
        int n = time.size();
        if (n <= 1) return 0;
        
        for (int i = 0; i < n; ++i) {
            time[i] = time[i] % 60;
        }
        
        unordered_map<int, int> m;  // remained, cnt
        
        for (auto t:time) {
            m[t]++;
        }
        
        int totalCnt = 0;
        
        for (auto it=m.begin(); it!=m.end(); ++it) {
            int firstVal = it->first;
            if (firstVal > 30) continue;
            
            int firstCnt = it->second;
            if (firstVal == 30 || firstVal == 0) {
                totalCnt += firstCnt*(firstCnt-1)/2;
            } else {
                if (m.count(60-firstVal)) {
                    totalCnt += firstCnt * m[60-firstVal];
                }
            }
        }
        
        return totalCnt;
    }
};
