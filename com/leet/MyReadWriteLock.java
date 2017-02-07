public class MyReadWriteLock {
    private Map<Thread, Integer> readThreads = new HashMap<Thread, Integer>();  //Thread, Count
    
    private int writeAccess = 0;
    private int writeRequest = 0;
    private Thread writeThread = null;
    
    
    //Idea:
    //0) Only one thread could have write lock; multi thread could have read lock
    //   * Remember the thread which has read lock, and write lock
    //1) If write lock is in use, read lock has to wait
    //2) If there are read lock in use, write lock has not wait
    //3) If write lock is in use, but the new requesting thread is the same as the thread which occupies the write lock, could grant the write access
    //4) Exception to 1) If write lock is in use, a read lock request comes, 
    //   if the requesting thread is the same as the thread occupies the write lock, grant read lock
    //5) If a reading thread(which has read lock) requests read lock again, grant read lock 
    //6) If current thread is not a write thread, and there is not write thread, and it is also not a read thread, 
    //   if there are write request, Don't grant read lock (the lock mechanism favors write over read)
    //7) If there is ONE read thread, if this only read thread also request write, could grant it write lock
    public synchronized void lockRead() throws InterruptedException {
		Thread callingThread = Thread.currentThread();
		
		while (!canGrantReadAccess(callingThread)) {
		    wait();
		}
		
		readThreads.put(callingThread, readThreads.get(callingThread)+1);
	}
	
	
	public synchronized void unlockRead() {
		Thread callingThread = Thread.currentThread();
		
		if (!isReader(callingThread)) return;
		
		int readCnt = readThreads.get(callingThread);
		
		if (readCnt == 1) {
		    readThreads.remove(callingThread);
		} else {
		    readThreads.put(callingThread, readCnt-1);
		}
		
		notifyAll();
	}
	
	public synchronized void lockWrite() throws InterruptedException {
		Thread callingThread = Thread.currentThread();
		writeRequest++;
		
		while (!canGrantWriteAccess(callingThread)) {
		    wait();
		}
		
		writeRequest--;
		
		writeAccess++;
		
		writeThread = callingThread;
		
	}
	
	public synchronized void unlockWrite() {
		Thread callingThread = Thread.currentThread();
		
		if (!isWriter(callingThread)) return;
		
		writeAccess--;
		if (writeAccess == 0) writeThread = null;
		notifyAll();
		
	}
    
    private boolean canGrantReadAccess(Thread callingThread) {
		if (isWriter(callingThread)) return true;
	    if (writeAccess > 0) return false;
	    if (isReader(callingThread)) return true;
	    if (writeRequest > 0) return false;
	    
	    return true;
	    
	}
	
	private boolean isReader(Thread callingThread) {
		return readThreads.containsKey(callingThread);
	}
	
	private boolean canGrantWriteAccess(Thread callingThread) {
		if (isOnlyReader(callingThread)) return true;
		if (readThreads.size() > 0) return false;
		if (writeThread == null) return true;
		if (!isWriter(callingThread)) return false;
		return true;
	}
	
	private boolean isWriter(Thread callingThread) {
		return callingThread == writeThread;
	}
    
}
