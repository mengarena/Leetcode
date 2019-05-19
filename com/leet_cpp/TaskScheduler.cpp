/*

621. Task Scheduler

Given a char array representing tasks CPU need to do. 
It contains capital letters A to Z where different letters represent different tasks. 
Tasks could be done without original order. Each task could be done in one interval. 
For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, 
there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 

Note:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].

Medium
*/

class Solution {
public:
    
    // 50%
    int leastInterval(vector<char>& tasks, int n) {
        vector<int> arrcnt(26, 0);
        vector<int> validPos(26, 0);
        int m = tasks.size();
        
        for (auto c:tasks) {
            arrcnt[c-'A']++;
        }
        
        int curPos = 0;
        int candIdx = -1;
        
        for (int i=0; i<m; ++i) {
            int neededPos = -1;
            
            // If not possible to put in curPos, neededPos will tell which should be the next position for current character
            candIdx = findCand(arrcnt, validPos, curPos, neededPos);
            
            arrcnt[candIdx]--;
            if (neededPos > curPos) {
                curPos = neededPos;
            }
            
            validPos[candIdx] = curPos + n + 1;
            curPos++;
            
        }
        
        return curPos;
    }
    
    
    int findCand(vector<int>& arrcnt, vector<int> &validPos, int curPos, int& needPos) {
        int maxCnt = 0;
        int candIdx = -1;
        
        // Check for all characters whose next valid position is <= curPos (i.e. could be put at position curPos)
        for (int i=0; i<arrcnt.size(); ++i) {
            if (arrcnt[i] == 0) continue;
            
            if (validPos[i] <= curPos && maxCnt < arrcnt[i]) {
                maxCnt = arrcnt[i];
                candIdx = i;
            }
        }
        
        if (candIdx != -1) return candIdx;
        
        int maxPostCnt = 0;
        int candPostIdx = -1;
        int minPostPos = INT_MAX;
        
        // If no character's current position is <= curPos, find the chacter, 
        // which has the smallest next position;
        // if two characters have same smallest next positions, the one has the largest remaining count should be selected.
        for (int i=0; i<arrcnt.size(); ++i) {
            if (arrcnt[i] == 0) continue;
            
            if (minPostPos > validPos[i]) {
                minPostPos = validPos[i];
                maxPostCnt = arrcnt[i];
                candPostIdx = i;
            } else if (minPostPos == validPos[i]) {
                if (maxPostCnt < arrcnt[i]) {
                    maxPostCnt = arrcnt[i];
                    candPostIdx = i;
                }
            }
        }
        
        needPos = minPostPos;
        
        return candPostIdx;
    }
 
};
