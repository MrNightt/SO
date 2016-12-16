package pt.europeia.uemail;

public abstract class Test {
	
	public static int alter(int x) {
		return x * 2;
	}

	public static void main (String [] args) {
		int x = 5;
		alter(x);
		System.out.println(x);


	}
	
}
