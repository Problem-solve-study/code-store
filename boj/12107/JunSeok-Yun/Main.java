import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		if (N == 1)
			System.out.println("B");
		else
			System.out.println("A");
		sc.close();
	}
	
}
