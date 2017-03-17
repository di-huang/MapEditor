package unit_testing.master;

import unit_testing.editor.Editor;
import unit_testing.map.Map;
import unit_testing.visualizer.Visualizer;

/**
 * 
 * @author dihuang
 *
 */
public class TestMaster {
	
	public static void main(String[] args) {
//		AFac fa = new AFac();
//		A a1 = fa.getA("A1");
//		A a2 = fa.getA("A2");
//		System.out.println(a1.getDes());
//		System.out.println(a2.getDes());
		
		Map M = new Map();
		Editor E = new Editor();
		Visualizer V = new Visualizer();
		Master Ma = new Master(M, E, V);
		Ma.start();
	}
	
}

class AFac{
	
	public A getA(String des){
		if(des == A1.des){
			return getA1();
		}else if(des == A2.des){
			return getA2();
		}
		return null;
	}
	
	public A getA1(){
		return new A1();
	}
	
	public A getA2(){
		return new A2();
	}
	
}

abstract class A{
	int i = 0;
	public abstract String getDes();
}

class A1 extends A{
	int i = 1;
	static String des = "A1";
	@Override
	public String getDes() {
		return des;
	}
}

class A2 extends A{
	int i = 2;
	static String des = "A2";
	@Override
	public String getDes() {
		return des;
	}
}
