package Demo;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Processor {

	private int count;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();

	private void increment() {
		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}

	public void processOne() throws InterruptedException {
		try {
			lock.lock();// similar to synchronization of block of code, here
						// lock is taken
			System.out.println(" ProcessorOne Lock Obtained");
			System.out.println(" ProcessorOne will WAIT "); 
			cond.await();// give up the lock
			
			System.out.println("Test if code exec before processTwo unlocks");
			increment();
		} finally {
			lock.unlock();// // similar to synchronization of block of code,
							// here lock is released
		}
	}

	public void processTwo() throws InterruptedException {
		try {
			Thread.sleep(2000);
			System.out.println("ProcessorTwo Obtains the Lock");
			lock.lock();
			System.out.println(" Enter the Return key to release await using signal");
			new Scanner(System.in).nextLine();
			cond.signal();// here await comes out of waiting, *** but ensure lock is unlocked else
			// processOne will not be able to get lock

			increment();
		} finally {
			System.out.println("process Two Lock released");
			lock.unlock();// // similar to synchronization of block of code,
							// here lock is released
		}
	}

	public void totalCount() {
		System.out.println("Total count : " + count);
	}
}
