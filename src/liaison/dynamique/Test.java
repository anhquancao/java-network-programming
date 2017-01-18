package liaison.dynamique;

public class Test {
	public static void main(String[] args) {
		A a1 = new A();
		A a2 = new B();
		B b = new B();
		
		// A et A
		System.out.println(a1.f(a1));
		
		// A et A
		System.out.println(a1.f(a2));
		
		// B et A
		System.out.println(a2.f(a1));
		
		// B et A
		System.out.println(a2.f(a2));
		
		// B et B
		System.out.println(a2.f(b));
		
		// B et A
		System.out.println(b.f(a2));
	}
}
