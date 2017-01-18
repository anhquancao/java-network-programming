package champsdeclass;

public class Test {

	public static void main(String[] args) {
		// Member variables cannot be overridden like methods.
		// The i variables in classes
		// B are hiding (not overriding) the member variable number of the
		// superclass A.
		// By casting you can access the hidden member in the superclass.
		//
		B b = new B();
		// 2
		System.out.println(b.i); 
		
		// -2
		System.out.println(b.f());
		
		// B
		System.out.println(b.g());
		
		// B
		System.out.println(b.h());

		A a = b;
		// 0
		System.out.println(a.i);
		
		// -2
		System.out.println(a.f());
		
		// A
		System.out.println(a.g());
		
		// B
		System.out.println(a.h());

	}

}
