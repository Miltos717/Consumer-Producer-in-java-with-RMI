import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

//bufferSize max
public class BufferImplements extends UnicastRemoteObject implements Buffer {
	
	private static int size,front,back,counter;
	private static int[] contents;
	private static Lock lock = new ReentrantLock();
	private static boolean full,empty;
	private static Condition con = lock.newCondition();


	// we initialize an array and two pointers
	public BufferImplements()throws RemoteException {
		size=10000;
		contents = new int[size];
		front = 0;
		back=-1;
		counter=0;
		for(int i=0;i<contents.length;i++) {
			contents[i]=0;
		}
		
	}

	// this method takes an item from the array and increase by 1 the front index 
	// we use synchronized so the threads can access this method 1 by 1

	public synchronized int get() throws RemoteException {
		int data=1;
		while(counter==0) {
			try {
				con.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			data = contents[front];
			System.out.println("  Cons " + Thread.currentThread().getName() + " No "+ data + " Loc " + front + " Count = " + (counter-1));
                  front = (front + 1);
			counter--;
			return data;
		
	}


      // this method puts an item to the array and increase by 1 the back index 
	// we use synchronized so the threads can access this method 1 by 1
	// we use the lock.lock() so that only 1 thread at a time can go through this block
	// and increase the back index by 1 and put an item into the array
	// the command notifyAll, notify that the other threads that can get an item from the array
	// in case the counter was 0 in the get method
	public synchronized void put(int data) throws RemoteException {
		lock.lock();
		try {
			back=back+1;
			contents[back]=data;
			counter++;
			System.out.println("Prod " + Thread.currentThread().getName() + " No "+ data + " Loc " + back + " Count = " + counter);
			notifyAll();
		}
		finally {
			lock.unlock();
		}
	}
	


}
