/*
 *
 * Implement customized BlockingQueue:
 * Method: put, take
 * 
 * Two different thread-safe strategies:  synchronized, lock
 *  
 */

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;


class CustomLinkedBlockingQueue<E> {
    private Queue<E> queue;
    private int maxSize;
    
    private Lock lock;
    private Condition notFull;
    private Condition notEmpty;
    
    public CustomLinkedBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = new LinkedList<E>();
        
        lock = new ReentrantLock();
        notFull = this.lock.newCondition();
        notEmpty = this.lock.newCondition();
    }
    
    
    public synchronized void put(E item) throws InterruptedException {
        while (queue.size() == maxSize) {
            wait();
        }
        
        boolean bEmpty = false;
        
        if (queue.size() == 0) bEmpty = true;   //Before adding, the queue is empty, so there might be threads waiting on it to take items
        
        queue.add(item);
        
        if (bEmpty) notifyAll();
    }
    
    public synchronized E take() throws InterruptedException {
        while (queue.size() == 0) {
            wait();
        }
        
        boolean bFull = false;
        
        if (queue.size() == maxSize) bFull = true;  //Before removing, the queue is full, so there might be threads waiting on it to add new items
        
        E ret = queue.remove();
        
        if (bFull) notifyAll();
        
        return ret;
    }
    
    
    public void putX(E item) throws InterruptedException {
	    lock.lock();
	    
	    try {
			while (queue.size() == maxSize) {
			    notFull.await();
			}
			
			queue.add(item);
			
			notEmpty.signalAll();
			
		} finally {
		    lock.unlock();
		}
	}
	
	
	public E takeX() throws InterruptedException {
	    lock.lock();
	    
	    try {
			while (queue.size() == 0) {
			    notEmpty.await();
			}
			
			E ret = queue.remove();
			
			notFull.signalAll();
			
			return ret;
		} finally {
		    lock.unlock();
		}
	}
    
}


class Producer implements Runnable {
    CustomLinkedBlockingQueue<Integer> queue;
    private volatile boolean bStop;
    
    public Producer(CustomLinkedBlockingQueue<Integer> queue) {
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
    CustomLinkedBlockingQueue<Integer> queue;
    private volatile boolean bStop;
    
    public Consumer(CustomLinkedBlockingQueue<Integer> queue) {
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



class ProducerX implements Runnable {
    CustomLinkedBlockingQueue<Integer> queue;
    private volatile boolean bStop;
    
    public ProducerX(CustomLinkedBlockingQueue<Integer> queue) {
        this.queue = queue;
        bStop = false;
    }
    
    public void run() {
		Random rm = new Random();
        while (!bStop) {
            int item = rm.nextInt(1000);
            try {
                queue.putX(item);
                System.out.println(Thread.currentThread().getName() + " produceX: " + item);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            
            }
        }
    }
    
    public void shutdown() {
        bStop = true;
    }
}


class ConsumerX implements Runnable {
    CustomLinkedBlockingQueue<Integer> queue;
    private volatile boolean bStop;
    
    public ConsumerX(CustomLinkedBlockingQueue<Integer> queue) {
        this.queue = queue;
        bStop = false;
    }
    
    public void run() {
        while (!bStop) {
            try {
                int item = queue.takeX();
                System.out.println(Thread.currentThread().getName() + " consumeX: " + item);
                Thread.sleep(1800);
            } catch (InterruptedException e) {
            
            }
        }
    }
    
    public void shutdown() {
        bStop = true;
    }
}



public class ProducerConsumerTestX {
    public static void main(String[] args) {
        CustomLinkedBlockingQueue<Integer> blockingQueue = new CustomLinkedBlockingQueue<Integer>(10);
        
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
        
        System.out.println("---------------------------------------------");
        System.out.println("---------------------------------------------");
        CustomLinkedBlockingQueue<Integer> blockingQueueX = new CustomLinkedBlockingQueue<Integer>(8);
        
        ProducerX pdX = new ProducerX(blockingQueueX);
        ConsumerX cmX = new ConsumerX(blockingQueueX);
        
        for (int i=1; i<=4; i++) {
            new Thread(pdX, "ProducerX" + i).start();
        }
        
        for (int i=1; i<=4; i++) {
            new Thread(cmX, "ConsumerX" + i).start();
        }
        
        try {
           Thread.sleep(12000);
 	    } catch (InterruptedException e) {
			
		}
		
        pdX.shutdown();
        cmX.shutdown();
        
        System.out.println("------------------------------------------");
    }
}

