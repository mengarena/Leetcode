/*

1094. Car Pooling

You are driving a vehicle that has capacity empty seats initially available for passengers. 
The vehicle only drives east (ie. it cannot turn around and drive west.)

Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about 
the i-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.  
The locations are given as the number of kilometers due east from your vehicle's initial location.

Return true if and only if it is possible to pick up and drop off all passengers for all the given trips. 


Example 1:
Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false

Example 2:
Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true

Example 3:
Input: trips = [[2,1,5],[3,5,7]], capacity = 3
Output: true

Example 4:
Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
Output: true
 
Constraints:
trips.length <= 1000
trips[i].length == 3
1 <= trips[i][0] <= 100
0 <= trips[i][1] < trips[i][2] <= 1000
1 <= capacity <= 100000

Medium
*/


class Solution {
public:
    struct Node {
        int seats;
        int time;    // Should be localation, not time
        Node(int s, int t) {seats = s; time = t;}
    };
    
    static bool mycomp(const Node& A, const Node& B) {
        return A.time < B.time;
    }
    
    // Similar to the meeting room problem:
    // https://github.com/mengarena/Leetcode/blob/master/com/leet/MeetingRoomsII.java
    bool carPooling(vector<vector<int>>& trips, int capacity) {
        if (trips.size() == 0) return true;
        if (capacity <= 0) return false;
        
        vector<Node> starts;
        vector<Node> ends;
        for (auto trip:trips) {
            starts.push_back({trip[0], trip[1]});
            ends.push_back({trip[0], trip[2]});
            if (trip[0] > capacity) return false;
        }
        
        sort(starts.begin(), starts.end(), mycomp);
        sort(ends.begin(), ends.end(), mycomp);
        
        int endIdx = 0;
        int seatUsed = starts[0].seats;
        
        for (int i=1; i<starts.size(); ++i) {
            if (starts[i].time >= ends[endIdx].time) {
                while (starts[i].time >= ends[endIdx].time) {   // Here need to use loop to process all ending trips 
                    seatUsed -= ends[endIdx].seats;               
                    endIdx++;
                }
                seatUsed += starts[i].seats; 
            } else {
                seatUsed += starts[i].seats;
            }
            if (seatUsed > capacity) return false;
        }
        
        return true;
    }
};
