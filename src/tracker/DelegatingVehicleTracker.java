package tracker;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/**
 * 委派模式的线程安全类！
 * unmodifiableMap
 * @author Cuber_Q
 *
 */
public class DelegatingVehicleTracker {
	private final ConcurrentMap<String, Point> locations; 
	//委托模式，暴露map的读操作，写操作全部抛异常。。
	private final Map<String, Point> unmodifiableMap;
	
	public DelegatingVehicleTracker(Map<String, Point> points){
		locations = new ConcurrentHashMap<String,Point>(points);
		//This method allows modules to provide users 
		//with "read-only" access to internal maps.
		unmodifiableMap = Collections.unmodifiableMap(locations);
	}
	public Map<String, Point> getLocations(){
		return this.unmodifiableMap;
	}
	//返回 最新的值
	public Point getLocation(String id){
		return this.locations.get(id);
	}
	
	public void setLocation(String id, int x,int y){
		if(locations.replace(id, new Point(x,y)) == null){
			System.out.println("invalid vehicle name: "+id);
		}
	}
	
	public static void main(String[] args){
		Map<String, Point> points = new HashMap<String,Point>();
		points.put("bentz", new Point(0,0));
		DelegatingVehicleTracker tracker = 
				new DelegatingVehicleTracker(points);
		
		tracker.setLocation("bentz", 1, 2);
		System.out.println(tracker.getLocation("bentz"));
		Map<String, Point> locations = tracker.getLocations();
		System.out.println("------locations--------");
		for(String key : locations.keySet()){
			System.out.println(locations.get(key));
		}

		tracker.setLocation("bent", 1, 2);
		
	}
}
//因为属性都是不可变的，所以Point类是线程安全的
class Point{
	private final int x,y;
	public Point(int x,int y){
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString(){
		return "point:[x="+x+", y="+y+"]";
	}
}
