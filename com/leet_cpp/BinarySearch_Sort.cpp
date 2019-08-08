// Find the index of the target in nums
// If not found, return -1;
int FindIndex(vector<int>& nums, int target) {
    int left = 0, right = static_cast<int>(nums.size()-1);   // Note: n-1
    while (left <= right) {   // Note: <=
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) return mid;
        else if (nums[mid] < target) left = mid + 1;   // Note:  +1
        else right = mid-1;   // Note: -1
    }
    return -1;
}


// Find the position of the first element which is >= target
// It is also the position for inserting a new num in order
// If not found, return -1;
// Extension:  If find the last num which is < target,
//             if pos == -1, return n-1;  else return pos-1
int FindFirstLargeEqual(vector<int>& nums, int target) {
    int left = 0, right = static_cast<int>(nums.size()-1);   // Note: n-1
    int pos = -1;   // Note:  -1
    while (left <= right) {    // Note:  <=
        int mid = left + (right - left) / 2;
        if (nums[mid] < target) left = mid + 1;   // <, and Note: +1
        else {
            pos = mid;       // Note: remember
            right = mid-1;   // Note:  -1
        }
    }
    return pos;
}


// Find the postion of the first element which is > target
// If not found, return -1
// Extension:  If find the last num which is <= target,
//             if pos == -1, return n-1;  else return pos-1
int FindFirstLarge(vector<int>& nums, int target) {
    int left = 0, right = static_cast<int>(nums.size()-1);   // Note: n-1
    int pos = -1;   // Note:  -1
    while (left <= right) {    // Note:  <=
        int mid = left + (right - left) / 2;
        if (nums[mid] <= target) left = mid + 1;   // <=, and Note: +1
        else {
            pos = mid;       // Note: remember
            right = mid-1;   // Note:  -1
        }
    }
    return pos;
}


// A template function to implement bubble sort.
// We can use this for any data type that supports
// comparison operator < and swap works for it.
template <class T>
void bubbleSort(T a[], int n) {
 //   for (int i = 0; i < n - 1; i++)
 //       for (int j = n - 1; i < j; j--)
 //           if (a[j] < a[j - 1])
 //               swap(a[j], a[j - 1]);
    bool swapped;
    for (int i=0; i<n-1; ++i) {
        swapped = false;
        for (int j=0; j<n-1-i; ++j) {
            if (a[j] > a[j+1]) {
                swap(a[j], a[j+1]);
                swapped = true;
            }
        }
        if (!swapped) break;
    }
}


priority_queue<int> mypq;   // Default:  greatest on top
priority_queue<int, vector<int>, less<int>> mypq;    //Greatest on top
priority_queue<int, vector<int>, greater<int>> mypq;   // Smallest on top

std::deque<int> myvector;
std::vector<int> myvector;

myvector.push_back (100);
myvector.push_back (300);
myvector.push_back (200);
sort(myvector.begin(), myvector.end(), less<int>());     // Sort

int bbaa[] = {10, 50, 30, 40, 20};
sort(bbaa, bbaa+5, less<int>());                            // Sort

struct DT {
    int a;
    int b;
    DT(int a, int b) { this->a = a; this->b = b; }
};

bool mycomp(DT& A, DT& B) {
    return A.a > B.a;          // Descending order by X.a
}

vector<DT> dtv;
dtv.push_back(DT(5,3));
dtv.push_back(DT(4,2));
dtv.push_back(DT(6,3));
sort(dtv.begin(), dtv.end(), mycomp);          // Sort


struct DTComp {
    bool operator()(DT& A, DT& B) {
        return A.a > B.a;        // Descending order by X.a
    }
};

priority_queue<DT, vector<DT>, DTComp> ppqqs;

string sss = "mengrufeng";
unordered_set<char>  ssset(sss.begin(), sss.end());

struct classcomp {
    bool operator() (const int& lhs, const int& rhs) const {
        return lhs < rhs; 
    }
};

int myints1[]= {10,30,20,50,40};

std::set<int, classcomp> myset;                 // class as Compare
myset.insert(myints1, myints1 + sizeof(myints1)/sizeof(int));

bool fncomp (int lhs, int rhs) { 
    return lhs < rhs; 
}

bool(*fn_pt)(int,int) = fncomp;
std::set<int, bool(*)(int,int)> myset(fn_pt);  // function pointer as Compare


////////////////////////////////////////////////////////////////////////
// Template


#include <iostream>
#include <string>
#include <vector>

using namespace std;

template <typename S, typename T>
class MyNode {
 public:
    S name;
    T value;
    
    MyNode(S name, T value) {
        this->name = name;
        this->value = value;
    }
};

template <typename T>
class MyQueue {
 public:
    void add(T mynode);
    void printall();

 private:
    vector<T> myq;
};

template <typename T>
void MyQueue<T>::add(T mynode) {
    myq.push_back(mynode);
}

template <typename T>
void MyQueue<T>::printall() {
    for (auto node:myq) {
        cout << node.name << ", " << node.value << endl;
    }
}

int main(int argc, const char * argv[]) {
    MyQueue<MyNode<string, int>> mq;
    mq.add(MyNode<string, int>("meng", 12));
    mq.add(MyNode<string, int>("rufeng", 45));
    mq.add(MyNode<string, int>("jing", 67.5));

    mq.printall();
    
    return 0;
}
