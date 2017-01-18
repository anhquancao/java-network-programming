package Polymorphisme;

/*
 * C < B < A
 */
class Test {
	public static void main(String[] args) {
		A a1 = new A();
		A a2 = new B();
		B b = new B();
		C c = new C();
		D d = new D();
		
		// A et A
//		System.out.println(a1.f(b));

		// A et A
//		System.out.println(a1.f(c));

		// A et D
//		System.out.println(a1.f(d));		

		// B et A
//		System.out.println(a2.f(b));
	
		// B et A
//		System.out.println(a2.f(c));

		// A et D
//		System.out.println(a2.f(d));
		
//		B et B
//		System.out.println(b.f(b));

//		B et B
//		System.out.println(b.f(c));
		
//		A et D
//		System.out.println(b.f(d));
	}
}