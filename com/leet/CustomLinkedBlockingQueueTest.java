/*
Custom BlockingQueue
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




public class CustomLinkedBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        CustomLinkedBlockingQueue<Integer> cbq = new CustomLinkedBlockingQueue<Integer>(10);
        
        cbq.put(11);
        cbq.put(22);
        
        System.out.println("take()>" + cbq.take());
        System.out.println("take()>" + cbq.take());
        
        CustomLinkedBlockingQueue<String> cbqA = new CustomLinkedBlockingQueue<String>(10);
        
        cbqA.putX("ddd");
        cbqA.putX("tttg");
        cbqA.putX("hhhhh");
        
        System.out.println("take()>" + cbqA.takeX());
        System.out.println("take()>" + cbqA.takeX());
        System.out.println("take()>" + cbqA.takeX());        
    }
}

