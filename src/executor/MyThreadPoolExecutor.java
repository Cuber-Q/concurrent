package executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolExecutor {
	
	public static void main(String[] args) throws Exception{
		ThreadPoolExecutor executor = new ThreadPoolExecutor
				(4						//corePoolSize 核心线程池大小
				, 20					//整个线程池最大规模
				, 20					//核心池【外】的【空闲线程】存活最大时间 
				, TimeUnit.SECONDS
				, new ArrayBlockingQueue<Runnable>(50) 	//工作阻塞队列，最大100
				, Executors.defaultThreadFactory()			
				, new ThreadPoolExecutor.DiscardPolicy());	//超出队列容量的任务的丢弃策略.丢弃任务，但是不抛出异常。
//				, new LinkedBlockingQueue<Runnable>());//排队队列，链式结构的阻塞队列,入列出列快，但是同时会产生Node对象
		
		ArrayList<Task> tasks = new ArrayList<>();
		for(int i=0;i<100;i++){
			tasks.add(new Task());
		}
		System.out.println("start executing..."+System.currentTimeMillis());
		
		//异步关闭线程池测试
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					System.out.println("prepare to shutdown executor...");
					Thread.currentThread().sleep(2 * 1000);
				
					/**
					 * shutdownNow()强制停止正在执行的任务线程
					 */
//					executor.shutdownNow();
					/**
					 * shutdown()会等待队列里的任务执行完毕
					 */
//					executor.shutdown();
					/**
					 * 
					 */
					System.out.println(executor.awaitTermination(1L, TimeUnit.SECONDS));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("executor has been shutdown....");
			}
		}).start();;
		
		List<Future<String>> futures = 
				executor.invokeAll(tasks);
		
		
		System.out.println("executing end....."+System.currentTimeMillis());
//		for(Future<String> future : futures){
//			System.out.println(future.get());
//		}
	}
}	
