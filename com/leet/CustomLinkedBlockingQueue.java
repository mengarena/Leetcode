/*
Custom BlockingQueue
*/

import java.util.LinkedList;
import java.util.List;

public interface CustomBlockingQueue<E> {
     public void put(E item) throws InterruptedException;
     
     public E take() throws InterruptedException;
}

class CustomLinkedBlockingQueue<E> implements CustomBlockingQueue<E> {
    private Queue<E> queue;
    private int maxSize;
    
    public CustomLinkedBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = new LinkedList<E>();
    }
    
    
    public synchronized void put(E item) throws InterruptedException {
        if (queue.size() == maxSize) {
            this.wait();
        }
        
        queue.add(item);
        
        this.notifyAll();
    }
    
    public synchronized E take() throws InterruptedException {
        if (queue.size() == 0) {
            this.wait();
        }
        
        E ret = queue.remove(0);
        
        this.notifyAll();
        
        return ret;
    }
    
}



public class CustomLinkedBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        CustomLinkedBlockingQueue<Integer> cbq = new CustomLinkedBlockingQueue<Integer>(10);
        
        cbq.put(11);
        cbq.put(22);
        
        System.out.println("take()>" + cbq.take());
        System.out.println("take()>" + cbq.take());
        
    }
}

