/*

Given a 1-d array candy crush, return the shortest array after removing all the continuous same numbers 
(the repeating number >= 3)

input: 1-d array [1, 3, 3, 3, 2, 2, 2, 3, 1]
return: [1, 1]

Time complexity should be better than O(n^2)

Google
*/


// Strategy:  
// 1) First, transform the original array into array of <val, count>
// 2) Try to remove every possible [continus >= 3 subarray], after removing, merge possible neighbor cells,
//    and then search >=3 subarray to remove ...  From all these, to find the remained array which has shortest length

#include <iostream>
#include <vector>

using namespace std;

void mergeIt(vector<pair<int, int>>& sdata) {
    for (int i=0; i<sdata.size()-1; ++i) {
        if (sdata[i].first == sdata[i+1].first) {
            sdata[i].second = sdata[i].second + sdata[i+1].second;
            sdata.erase(sdata.begin()+i+1);
            break;
        }
    }
}

int getLen(vector<pair<int, int>>& sdata)
{
    int cnt = 0;
    for (auto it:sdata) cnt += it.second;
    
    return cnt;
}

void simplifyIt(vector<pair<int, int>>& sdata) {
    for (int i= static_cast<int>(sdata.size())-1; i >= 0; --i) {
        if (sdata[i].second >= 3) {
            sdata.erase(sdata.begin()+i);
            mergeIt(sdata);
            simplifyIt(sdata);
        }
    }
}

vector<int> removeIt(vector<int>& data) {
    if (data.size() <= 2) return data;

    vector<pair<int, int>> sdata;
    
    int cnt = 1;
    int prevData = data[0];
    for (int i=1; i<data.size(); ++i) {
        if (data[i] == prevData) {
            cnt++;
        } else {
            sdata.push_back(make_pair(prevData, cnt));
            cnt = 1;
            prevData = data[i];
        }
    }
    
    sdata.push_back(make_pair(prevData, cnt));
    
    int shortest = getLen(sdata);
    vector<pair<int, int>> ret = sdata;
    
    for (int i= static_cast<int>(sdata.size())-1; i >= 0; --i) {
        vector<pair<int, int>> ssdata = sdata;
        if (ssdata[i].second >= 3) {
            ssdata.erase(ssdata.begin()+i);
            mergeIt(ssdata);
            simplifyIt(ssdata);
            int len1 = getLen(ssdata);
            if (shortest > len1) {
                shortest = len1;
                ret = ssdata;
            }
        }
    }
    
    vector<int> ans;
    for (auto it:ret) {
        for (int i = 0; i<it.second; ++i) {
            ans.push_back(it.first);
        }
    }
    
    return ans;
}


int main(int argc, const char * argv[]) {    
//    vector<int> data{1, 3, 3, 3, 2, 2, 2, 3, 1};
//    vector<int> data{1, 3, 3, 3, 2, 2, 2, 1, 3};
//    vector<int> data{1,1,2,2,2,1,1,1,2};
//    vector<int> data{1,2,2,2,1,1,1,2,2};
    vector<int> data{1, 4, 4, 4, 3, 3, 3, 2, 2, 2, 4, 3, 4, 1};

    vector<int> ans = removeIt(data);
    for (auto i:ans) {
        cout << i << ",";
    }
    
    cout << endl;
    
    return 0;
}
