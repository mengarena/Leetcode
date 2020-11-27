#include <iostream>
#include <thread>
#include <condition_variable>
#include <mutex>
#include <queue>
#include <atomic>

/*
Difference between lock_guard vs unique_lock：

http://jakascorner.com/blog/2016/02/lock_guard-and-unique_lock.html
*/

using namespace std;

mutex m;
condition_variable cv;
queue<int> qu;
atomic_bool finished(false);

void producer(int n)
{
    for (int i=1; i<=n; i++)
    {
        lock_guard<mutex> lk(m);
        cout << "Pushing... " << i << endl;
        qu.push(i);
        cv.notify_all();
    }
    
    //lock_guard<mutex> lk(m);
    finished.store(true);
    cv.notify_all();
}

/*
The std::condition_variable::wait(...) unlocks the mutex and 
waits for the std::condition_variable.notify_one() member function call. 
Then, wait(...) reacquires the lock and proceeds.
*/

void consumer()
{
    unique_lock<mutex> lk(m);
    while (1) {
        cv.wait(lk, []{ return (finished.load() || !qu.empty()); });   // condition_variable::wait() requires unique_lock
        if (!qu.empty()) {
            while (!qu.empty()) {
                cout << "Consumer: " << qu.front() << endl;
                qu.pop();
            }
        }
        
        if (finished.load()) break;
    }
}

int main()
{
    thread tp(producer, 1000);
    thread tc(consumer);

    tp.join();
    tc.join();

    cout << "Done !" << endl;
}

//////////////////////////////////////////////////
#include <iostream>
#include <thread>
#include <condition_variable>
#include <mutex>
#include <queue>
#include <atomic>

using namespace std;

mutex m;
condition_variable cvP, cvC;
queue<int> qu;
atomic_bool finished(false);

void producer(int n)
{
    unique_lock<mutex> lk(m);
    
    for (int i=1; i<=n; i++)
    {
        cvP.wait(lk, []{ return qu.empty();});
     // cvP.wait(lk, []{ return qu.size() < 10;});
        qu.push(i);
        cout << "Producing ..." << i << endl;
        cvC.notify_all();
    }
    
    cvP.wait(lk, []{return qu.empty();});
    finished.store(true);
    cout << "Producer tells consumers to finished" << endl;
    cvC.notify_all();    
}

void consumer()
{
    unique_lock<mutex> lk(m);
    while (1) {
        cvC.wait(lk, []{ return (finished.load() || !qu.empty()); });
        if (!qu.empty()) {
            while (!qu.empty()) {
                cout << "Consumer: " << qu.front() << endl;
                qu.pop();
            }
            // Consumed all data, tell producer to produce data again
            cvP.notify_all();
        }
        
        if (finished.load()) break;
    }
    
    cout << "Consumer finshed" << endl;
}

int main()
{
    thread tp(producer, 100);
    thread tc(consumer);

    tp.join();
    tc.join();

    cout << "Done !" << endl;
}
