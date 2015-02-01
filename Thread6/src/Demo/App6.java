package Demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {
	private CountDownLatch latch;
	
	public Processor(CountDownLatch latch){
		this.latch = latch;
	}
	 
	public void run() {
		System.out.println("Thread Started");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}		
		System.out.println("Thead Finished");
		latch.countDown();
	}
	
}


public class App6 {

	public static void main(String[] args) {
		
		CountDownLatch latch = new CountDownLatch(3);
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		for(int i=0; i<3; i++){
			executorService.submit(new Processor(latch));
		}
		
		executorService.shutdown();
		System.out.println(" Threads have been spawned and made to work on Task/Processors");
		

		try {
			System.out.println("Dont Return untill all task is completed");
			executorService.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
		}
		System.out.println("All Task Completed");
		
		
//		latch.countDown();
//		latch.countDown();
//		latch.countDown();
		try {
			latch.await();
		} catch (InterruptedException e) {
		}
		System.out.println(" Control Returned after all threads have finished and made latch 0 ");
	}

}