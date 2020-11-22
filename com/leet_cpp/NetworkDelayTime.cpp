/*

743. Network Delay Time

There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), 
where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? 
If it is impossible, return -1.

Example 1:
Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
Output: 2
 
Note:
N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.

Medium

Google, Amazon
*/


class Solution {
public:
    static bool mycomp(pair<int, int>& A, pair<int, int>& B) {
        return A.second < B.second;
    }

    // 23%
    int networkDelayTime(vector<vector<int>>& times, int N, int K) {
        vector<int> delays(N+1, INT_MAX);
        
        unordered_map<int, vector<pair<int, int>>> mytimes;
        for (auto time:times) {
            mytimes[time[0]].push_back(make_pair(time[1],time[2]));
        }
        
        // Sort the adjacent node in ascending order on distance
        // So later, some larger distance node might not to be processed, since it might has long distance from the parent node
        // than some sub (sub, sub) node of the parent node
        for (auto it=mytimes.begin(); it!=mytimes.end(); ++it) {
            sort(it->second.begin(), it->second.end(), mycomp);
        }
                                       
        propagate(mytimes, delays, K, 0);
        
        int totalTime = 0;
        for (int i=1; i<=N; ++i) {
            if (delays[i] == INT_MAX) return -1;
            totalTime = max(totalTime, delays[i]);
        }
        
        return totalTime;
    }
    
    // Check the propagation to node, the distanct to 'node' is 'dist'
    void propagate(unordered_map<int, vector<pair<int, int>>>& mytimes, vector<int>& delays, int node, int dist) {
        if (dist >= delays[node]) return;
        delays[node] = dist;
        
        if (mytimes.count(node) == 0) return;
        
        vector<pair<int, int>> adjs = mytimes[node];
        
        for (auto it:adjs) {
            int nextNode = it.first;
            int nextDist = it.second;
            propagate(mytimes, delays, nextNode, dist + nextDist);
        }
    }
};
