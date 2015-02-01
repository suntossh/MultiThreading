package Demo;

public class App9 {

	public static void main(String[] args) {
		
	final Processor processor = new Processor();
	
	Thread t1 = new Thread(new Runnable() {
		public void run() {
			try {
				processor.producer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	});
	
	Thread t2 = new Thread(new Runnable() {
		public void run() {
			try {
				processor.consumer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	});
	
	
	t1.start();
	t2.start();
	
	try {
		t1.join(5000);
		t2.join(5000);
		
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	System.out.println("Returned after Join maxed out time specified, but this will leave my threads in infinite loop");
	}
}
