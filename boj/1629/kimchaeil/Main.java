//문제: BOJ 1629번 곱셈
//메모리: 11432 KB
//시간: 64ms

/*
 * 분할 정복을 이용한 거듭제곱 + 모듈러 연산
 */

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int a = nextInt(), b = nextInt(), c = nextInt();
		System.out.println(myPow(a % c, b, c));
	}

	static long myPow(int a, int k, int p) {
		if (k == 1) return a;
		long x = myPow(a, k / 2, p);
		return (((x * x) % p) * ((k & 1) == 0 ? 1 : a)) % p;
	}

	static int nextInt() throws IOException {
		int c, n;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}
