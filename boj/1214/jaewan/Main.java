/*
 * P, Q 지폐 두 종류만 이용해 D원 물건 구매하기 위해 지불해야 하는 금액의 최솟값
 * dp? or dfs? 써서 만들어 가기엔 N 이 살짝 크다.
 * 1,000,000,000
 * 
 * P가 1이고 D 가 1,000,000,000 인 경우.
 * 
 * p를 i장, q를 j장
 * j = (D - p * i - 1) / Q + 1
 */

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		int D = readInt(), P = readInt(), Q = readInt(), min = Integer.MAX_VALUE;

		if (D % P == 0 || D % Q == 0) {
			System.out.println(D);
		}

		else {
			int bigger = Math.max(P, Q), smaller = Math.min(P, Q), j;
			for (int i = D / bigger + 1; i >= 0; i--) {
				j = D > bigger * i ? (D - bigger * i - 1) / smaller + 1 : 0;
				min = Math.min(min, i * bigger + j * smaller);
				if (min == D)
					break;
			}
			System.out.println(min);
		}
	}

	private static int readInt() throws IOException {
		int c;
		do {
			c = System.in.read();
		} while (c <= 32);
		int n = c & 15;
		c = System.in.read();
		while (c > 47) {
			n = (n << 3) + (n << 1) + (c & 15);
			c = System.in.read();
		}
		return n;
	}

}
