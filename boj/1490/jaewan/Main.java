// 12868 KB, 88 ms

/*
 * 어떤 수 N, N으로 시작하면서, N의 0이 아닌 모든 자리수로 나누어 떨어지는 수 중 가장 작은 수
 * 
 * N <= 1,000,000,000
 * 
 * 13 : 132
 * 648 : 648
 * 566 : 56610
 * 1000000000 : 1000000000
 * 987654321 : 987654321360
 * 83 : 8304
 * 
 * 각 자리 숫자의 LCM을 구한다. (최소공배수)
 * 그 후, 전체 숫자에서 숫자를 붙여서, 최소공배수로 나누어 떨어지는 수 만들기.
 * 
 */

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int N;
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		int t = N, LCM = 1;
		while (t > 0) {
			int a = LCM, b = t % 10;
			if (b != 0) {
				int gcd = getGCD(a, b);
				LCM = a * b / gcd;
			}
			t /= 10;
		}

		long res = N;
		t = 1;
		while (true) {
			if ((LCM - res % LCM) % LCM < t) { // 나누어 떨어지기 위해 더해야 하는 수
				res += (LCM - res % LCM) % LCM;
				break;
			}
			res *= 10;
			t *= 10;
		}
		System.out.println(res);

	}

	static int getGCD(int a, int b) {
		int ta = a, tb = b, t = b;
		while (tb > 0) {
			t = tb;
			tb = ta % tb;
			ta = t;
		}
		return ta;
	}
}
