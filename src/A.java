
public class A {
	private int val = 0;

	public static void affichePlus(int a) {
		a++;
		System.out.println(a);
	}

	public static void affichePlus(A a) {
		a.val++;
		System.out.println(a.val);
	}

	public static void main(String[] args) {
		A unObj = new A();
		A unAutreObj = new A();

		affichePlus(unObj.val);// 1
		affichePlus(unObj.val);// 1

		affichePlus(unObj);// 1
		affichePlus(unObj);// 2
		affichePlus(unAutreObj);// 1
		affichePlus(unAutreObj);// 2

		if (unObj == unAutreObj)
			System.out.println("Egales");
		else
			System.out.println("Differentes");
		// Differentes
	}

}
