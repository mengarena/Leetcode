/*
Question:

The schedule of several persons are:
A:  9am ~ 10am, 11am ~ 12pm
B:  8am ~ 9:30am, 12pm ~ 1pm
C:  10:30am ~ 12:00pm

Check whether it is possible to all them to have a meeting at a time slot, 
e.g 9am~10am is impossible;  1pm ~ 2pm is possible

Uber
*/

#include <iostream>
#include <queue>
#include <vector>

using namespace std;

struct Interval {
    double start;
    double end;
    Interval (double s, double e) {start = s; end = e;}
};

// Sort: descending by start; if tie, ascending by end
// When this sorting is used for the priority queue,  smallest start comes on top; if tie, larger end comes on top
struct IntervalCMP {
    bool operator()(const Interval& A, const Interval& B) {
        if (A.start != B.start) {
            return A.start > B.start;
        } else {
            return A.end < B.end;
        }
    }
};

bool isAvailable(priority_queue<Interval, vector<Interval>, IntervalCMP>& pq, Interval& expected) {
    if (pq.empty()) return true;
    
    Interval cur = pq.top();
    pq.pop();
    double prev_start = cur.start;
    //cout << prev_end << endl;
    if (expected.end <= prev_start) return true;
    double prev_end = cur.end;
    
    while (!pq.empty()) {
        cur = pq.top();
        pq.pop();
        if (cur.start > prev_end) {
            //cout << prev_end << "," << cur.start << endl;
            if (expected.start >= prev_end && expected.end <= cur.start) return true;
        }
        prev_end = max(prev_end, cur.end);
    }
    
    //cout << prev_end << endl;
    if (expected.start >= prev_end) return true;
    
    return false;
}


int main(int argc, const char * argv[]) {

    // Smallest start comes first; if starts are same, larger end comes first
    priority_queue<Interval, vector<Interval>, IntervalCMP> pq;
    pq.push(Interval(8,9));
    pq.push(Interval(11,12));
    pq.push(Interval(7,10));
    
    Interval expected(7,8);
    
    bool isPossible = isAvailable(pq, expected);
    if (isPossible) {
        cout << "Possible" << endl;
    } else {
        cout << "Impossible" << endl;
    }
    
    return 0;
}
