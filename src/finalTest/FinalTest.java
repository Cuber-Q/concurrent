package finalTest;

import java.util.ArrayList;
/**
 * final定义的变量x，只限定了指向一个对象的引用不能再指向其他的对象(或变量)，
 * 而x指向的具体对象实例obj是可以改变的
 * 并且这个引用可以被赋值给其他变量
 * 就算x自身没有去修改自己指向的对象，一旦这种引用关系被传递给其他变量
 * 依然是可以改变对象obj的
 * 
 * @author Cuber_Q
 *
 */
public class FinalTest {
	public static void main(String[] args){
		final ArrayList<String> list = new ArrayList<String>();
		list.add("x");
		list.add("y");
		list.add("z");
		for(String s : list){
			System.out.println(s);
		}
		ArrayList<String> another = new ArrayList<String>();
		another = list;
		another.add("aaa");
		System.out.println("list------------");
		for(String s : list){
			System.out.println(s);
		}
		System.out.println("another------------");
		for(String s : list){
			System.out.println(s);
		}
		
	}
}
