//문제: BOJ 27940번 가지 산사태
//메모리: 12240 KB
//시간: 284 ms

/*
 * 위에서 내린 비는 아래에도 누적됨
 * 총 내린 비의 양이 k를 초과하는 순간 어느 층에서 무너짐
 * 무너지는 층은 입력받은 층 중 최소값
 */

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int n = nextInt(), m = nextInt(), k = nextInt(), sum = 0;
		boolean result = false;
		for (int i = 1; i <= m; i++) {
			n = Math.min(n, nextInt());
			sum += nextInt();
			if (sum > k) {
				sb.append(i).append(' ').append(n);
				result = true;
				break;
			}
		}
		System.out.print(result ? sb : -1);
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
