package threadLocal;
/**
 * threadLocal对象跟当前执行线程紧密相连
 * @author Cuber_Q
 *
 */
public class ThreadLocalUsage {
	public static void main(String[] args){
		Thread th = Thread.currentThread();
		ThreadLocal<Integer> cnt = new ThreadLocal<Integer>();
		cnt.set(12);
		System.out.println("main thread's cnt="+cnt.get());
		
		AnotherThread another = new AnotherThread(50);
		another.start();
		
	}
}
class AnotherThread extends Thread{
	ThreadLocal<Integer> cnt = new ThreadLocal<Integer>();
	public AnotherThread(int x){
		cnt.set(x);
		System.out.println("AnotherThread init thread is="+Thread.currentThread().getName());
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i=0;
		while(i<5){
			if(null==this.cnt.get())this.cnt.set(50);
			System.out.println("another thread's cnt="+this.cnt.get());
			this.cnt.set(this.cnt.get()+2);
			System.out.println("current thread="+Thread.currentThread().getName());
			i++;
		}
	}
	
}