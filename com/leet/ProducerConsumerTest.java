/*
Implement Producer/Consumer with BlockingQueue

There are four types of BlockingQueues:
a) ArrayBlockingQueue:  based on array, FIFO, constructor accepts an int to decide the size;
b) LinkedBlockingQueue:  based on linkedlist, FIFO, constructor could accept an int to decide size; if not given, the size is Integer.MAX_VALUE;
c) PriorityBlockingQueue:  baed on PriorityQueue, it is like LInkedBlockingQueue, but the order is decided by priorityqueue
d) SynchronousQueue: put and take must be done alternatively

*/

import java.util.concurrent.*;
import java.util.*;

class Producer implements Runnable {
    BlockingQueue<Integer> queue;
    private volatile boolean bStop;
    
    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
        bStop = false;
    }
    
    public void run() {
		Random rm = new Random();
        while (!bStop) {
            int item = rm.nextInt(100);
            try {
                queue.put(item);
                System.out.println(Thread.currentThread().getName() + " produce: " + item);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            
            }
        }
    }
    
    public void shutdown() {
        bStop = true;
    }
}


class Consumer implements Runnable {
    BlockingQueue<Integer> queue;
    private volatile boolean bStop;
    
    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
        bStop = false;
    }
    
    public void run() {
        while (!bStop) {
            try {
                int item = queue.take();
                System.out.println(Thread.currentThread().getName() + " consume: " + item);
                Thread.sleep(800);
            } catch (InterruptedException e) {
            
            }
        }
    }
    
    public void shutdown() {
        bStop = true;
    }
}


public class ProducerConsumerTest {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>(10);
        
        Producer pd = new Producer(blockingQueue);
        Consumer cm = new Consumer(blockingQueue);
        
        for (int i=1; i<=5; i++) {
            new Thread(pd, "Producer" + i).start();
        }
        
        for (int i=1; i<=5; i++) {
            new Thread(cm, "Consumer" + i).start();
        }
        
        try {
           Thread.sleep(15000);
 	    } catch (InterruptedException e) {
			
		}
		
        pd.shutdown();
        cm.shutdown();
        
    }
}


