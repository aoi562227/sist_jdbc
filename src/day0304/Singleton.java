package day0304;

public class Singleton {
	private static Singleton single;
	public Singleton() {
		
	}
	
	public static Singleton getInstance() {
		if (single == null) {
			single = new Singleton();
			
		}
		return single;
	}
}
