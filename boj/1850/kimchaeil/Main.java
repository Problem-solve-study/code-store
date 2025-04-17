//문제: BOJ 1850 최대공약수
//메모리: 59280 KB
//시간: 200 ms

/*
 * 1로만 이루어진 수는 1로만 이루어진 수의 배수
 * 1로만 이루어진 수 n에 대해 n의 자리수를 k라고 하면
 * k의 약수 길이의 1로만 이루어진 수들의 집합이 n의 약수 집합
 * 즉, 두 1로만 이루어진 수 a와 b에 대해
 * a와 b의 길이의 최대 공약수 길이의 1로만 이루어진 수가
 * a와 b의 최대 공약수다.
 * 
 * 유클리드 호제법으로 푼다.
 */

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		long r1 = nextLong(), r2 = nextLong(), r;
		while (r2 != 0) {
			r = r1 % r2;
			r1 = r2;
			r2 = r;
		}
		while (r1-- > 0)
			sb.append('1');
		System.out.print(sb);
	}

	static long nextLong() throws IOException {
		long c, n;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}
