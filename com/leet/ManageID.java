/*
 Implement a class to allocate integer IDs and release them so that they are reusable 
 
 Implement two functions that assign/release unique id's from a pool. 
 Memory usage should be minimized and the assign/release should be fast, even under high contention. 
*/

//Use Bitset, use least memory
//Operation: allocateID:  O(n),  releaseID: O(1)

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;



class ManageID {
	
	class IdNode {
		int used;        //1: used, 0: not used
		IdNode left;
		IdNode right;
		IdNode parent;
		
		public IdNode() {
			left = null;
			right = null;
			parent = null;
			used = 0;
		}
	}	
	
	private BitSet bits;
	private int capacity;
	
	private Lock lock;
	private Condition notUsed;
	private Condition allUsed;
	
	private Semaphore mutex = new Semaphore(1);   //mutex.
	
	private Set<Integer> usedSet;
	private Set<Integer> availableSet;
	
	
	//BitSet internally uses a long[] array, the size is always a multiple of 64 bits
	//Bitset allocate 64*n bits (i.t. multiple of 64)
	//For example, if only ask for 10 bits, it actually gives 64 bits (size() = 64)
	//If ask for 65 bits, it actually gives 128 bits (size() = 128)
    public ManageID(int capacity) {
	    this.capacity = capacity;
	    bits = new BitSet(capacity);
	    
	    bits.set(2);
	    bits.set(3);
	    bits.set(4);
	    bits.set(5);

	    
	    System.out.println("Size=" + bits.size());
	    System.out.println("Length=" + bits.length());
	    if (bits.get(1)) {
		    System.out.println("TRUE");
		}
	    
	    
	    lock = new ReentrantLock();
	    notUsed = lock.newCondition();
	    allUsed = lock.newCondition();
	    
	    
	    usedSet = new HashSet<Integer>();
	    availableSet = new HashSet<Integer>();
	    
	    for (int i=1; i<=capacity; i++) {
		    availableSet.add(i);
		}
	}
	
	/////////////////////////////
	//lock, unlock works
	public int allocateID_HashSet() {
	    if (availableSet.isEmpty()) return -1;
	    
	    Iterator<Integer> it = availableSet.iterator();
	    int id = it.next();
	    
	    availableSet.remove(id);
	    usedSet.add(id);
	    
	    return id;
	}
	
	
	public void releaseID_HashSet(int id) {
		if (!usedSet.contains(id)) return;
		
	    usedSet.remove(id);
	    availableSet.add(id);
	}
	
	
	
	//////////////////////////////
	public int allocateID_HashSet_Mutex() {
		try {
			mutex.acquire();
		} catch (Exception e) {
		    return -1;
		}
		
	    if (availableSet.isEmpty()) return -1;
	    
	    Iterator<Integer> it = availableSet.iterator();
	    int id = it.next();
	    
	    availableSet.remove(id);
	    usedSet.add(id);
	    
	    mutex.release();
	    
	    return id;
	}
	
	
	public void releaseID_HashSet_Mutex(int id) {
		try {
			mutex.acquire();
		} catch (Exception e) {
		    return;
		}
			
		if (!usedSet.contains(id)) return;
		
	    usedSet.remove(id);
	    availableSet.add(id);
	    
	    mutex.release();
	}	
	
	
	
	///////////////////
	public int allocateID() {
	    for (int i=0; i<capacity; i++) {
		    if (bits.get(i) == false) {
			    bits.set(i);
			    return i+1;
			}
		}
		
		return -1;
	}
	
	public void releaseID(int id) {
		if (id > capacity || id <= 0) return;
		
		bits.clear(id-1);
	}
	
	
    ////////////////////////
	public int allocateID_synchronized() {
	    for (int i=0; i<capacity; i++) {
			
		    if (bits.get(i) == false) {
				
				synchronized (ManageID.class) {    //Just the way of Singleton did
					if (bits.get(i) == false) {
			            bits.set(i);
			            return i+1;
				    }
				}
				
			}
		}
		
		return -1;
	}
	
	
	public void releaseID_synchronized(int id) {
		if (id > capacity ||id <= 0) return;
		
		synchronized (ManageID.class) {
		    bits.clear(id-1);
	    }
	}
	
	
	///////////////////
	
	
	public int allocateID_Lock() {
	    for (int i=0; i<capacity; i++) {
		    if (bits.get(i) == false) {
				lock.lock();
				try {
			        bits.set(i);
			        return i+1;
			    } finally {
				    lock.unlock();
				}
			}
		}
		
		return -1;
	}
	
	
	public void releaseID_Lock(int id) {
		lock.lock();
		
        try {
		    bits.clear(id-1);
		} catch (Exception e) {
			;
		} finally {
			lock.unlock();
		}
	}
	
	
	///////////////////////////////
	public int allocateID_synchronized_Condition() {
		//while (All used) {
		//    wait();    --->noteifyAll();
		//}
		
	    for (int i=0; i<capacity; i++) {
			
		    if (bits.get(i) == false) {
				
				synchronized (ManageID.class) {    //Just the way of Singleton did
					if (bits.get(i) == false) {
			            bits.set(i);
			            return i+1;
				    }
				}
				
			}
		}
		
		return -1;
	}
	
	
	public void releaseID_synchronized_Condition(int id) {
		if (id > capacity ||id <= 0) return;
		
		synchronized (ManageID.class) {
		    bits.clear(id-1);
	    }
	}
	
	
	
    ////////////////////	
	public static void main(String[] args) {
	    ManageID mg = new ManageID(4);
	    
	    int id = mg.allocateID_HashSet();
	    
	    System.out.println("Allocated ID=" + id);
	    
	    id = mg.allocateID_HashSet();
	    System.out.println("Allocated ID=" + id);

	    id = mg.allocateID_HashSet();
	    System.out.println("Allocated ID=" + id);

	    id = mg.allocateID_HashSet();
	    System.out.println("Allocated ID=" + id);

	    id = mg.allocateID_HashSet();
	    System.out.println("Allocated ID=" + id);


        System.out.println("========Now release===========");	
        mg.releaseID_HashSet(4);
        mg.releaseID_HashSet(2);
        
 	    id = mg.allocateID_HashSet();
	    System.out.println("N Allocated ID=" + id);

 	    id = mg.allocateID_HashSet();
	    System.out.println("N Allocated ID=" + id);

 	    id = mg.allocateID_HashSet();
	    System.out.println("N Allocated ID=" + id);

           
	    return;
	    
	}
}

