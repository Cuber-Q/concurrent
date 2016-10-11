package executor;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class Task implements Callable<String>{
	public static AtomicInteger cnt = new AtomicInteger(0);
	public static AtomicInteger cntTemp = new AtomicInteger(0);
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		/*
		 * 如果任务比较耗时，线程池会不断创建新的线程
		 * 不怎么耗时的话，会尽量让线程复用
		 */
		Thread.currentThread().sleep(1000);
//		for(int i=0;i<1000000000;i++){
//			
//		}
//		Thread.currentThread().interrupt();
		
		//test executor.shutdown()
		System.out.println( Thread.currentThread().getName()
				+"_____"+String.valueOf(cntTemp.incrementAndGet())
				+"_____"+ String.valueOf(System.currentTimeMillis())
				+"_____running");
//		Thread.currentThread().sleep(2000);
		return Thread.currentThread().getName()
				+"_____"+String.valueOf(cnt.incrementAndGet())
				+"_____"+ String.valueOf(System.currentTimeMillis());
	}
	
}
