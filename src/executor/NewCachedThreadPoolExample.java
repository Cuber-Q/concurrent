package executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 使用 Executors 类的 newCachedThreadPool（） 
 * 方法创建的是无界线程池，可以进行线程自动回收。
 * 所谓的“无界线程池”就是池中存放［线程个数］是理论上的 
 * Integer.MAX_VALUE 最大值。
 * 
 * 可缓存的线程池，如果线程当前规模超过处理的需求，
 * 那么回收空闲线程（默认60s回收一次）， 当需求增加时，则可以添加新的线程，
 * @author Cuber_Q
 *
 */
public class NewCachedThreadPoolExample {
	private ExecutorService exService ;
	public NewCachedThreadPoolExample(){
		exService = Executors.newCachedThreadPool();
	}
	public static void main(String[] args) throws Exception{
		NewCachedThreadPoolExample example = 
				new NewCachedThreadPoolExample();
		
		example.exService.submit(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("run a task..."+System.currentTimeMillis());
			}
			
		});
		
		//测试线程的自动回收，的确是闲置1min后被销毁
		/**
		 * 关于 threadPooleExecutor 中构造参数keepAliveTime的定义
		 * when the number of threads is greater than the core, 
		 * this is the maximum time that excess idle threads will 
		 * wait for new tasks before terminating.
		 * 核心线程池(corePool)之外的闲置线程的最大存活时间。
		 */
		example.activeThreadsNum();
		example.service();
		example.activeThreadsNum();
		Thread.currentThread().sleep(60 * 1000);
		System.out.println("\r\n");
		example.activeThreadsNum();
		example.service();
		example.activeThreadsNum();
		
	}
	
	/**
	 * ThreadExecutor没有提供查看当前线程池内活跃线程数的接口，
	 * 通过获取jvm全部线程数，可以从侧面了解到线程池大概的存活线程数
	 */
	public void activeThreadsNum(){
		Map map=Thread.getAllStackTraces();
		System.out.println("activeThreadsNum____"+map.size());
	}
	
	public void service(){
		ArrayList<Task> tasks = new ArrayList<>();
		for(int i=0;i<100;i++){
			tasks.add(new Task());
		}
		
		try {
			List<Future<String>> futures = exService.invokeAll(tasks);
			
			for(Future<String> future : futures){
				System.out.println(future.get()
						+"___print time="+System.currentTimeMillis());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

