//문제: boj 13902 개업2
//메모리: 14204 KB
//시간: 192 ms

/*
 * 웍은 한번에 2개를 사용 할 수 있음 -> 왜냐하면 손은 두 개
 * 2개의 웍을 합쳐서 하나의 웍으로 생각
 * 합쳐서 나오는 웍의 크기가 중복 될 수 있으니 visited 배열 사용
 * 크기가 작은 웍부터 계산
 * dp[i]은 dp[i-웍의 크기]+1과 dp[i] 중 작은 값
 */

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int n = nextInt();
		int m = nextInt();
		int[] size = new int[m];
		boolean[] visited = new boolean[n + 1];
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			size[i] = nextInt();
			if (!visited[size[i]]) {
				visited[size[i]] = true;
				list.add(size[i]);
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = i + 1; j < m; j++) {
				int newSize = size[i] + size[j];
				if (newSize <= n && !visited[newSize]) {
					list.add(newSize);
					visited[newSize] = true;
				}
			}
		}
		list.sort(null);
		int[] dp = new int[n + 1];
		for (int i = 1; i <= n; i++)
			dp[i] = 10001;

		for (int now : list) {
			for (int i = now; i <= n; i++)
				dp[i] = Math.min(dp[i - now] + 1, dp[i]);
		}
		System.out.println(dp[n] == 10001 ? -1 : dp[n]);
	}

	static int nextInt() throws IOException {
		int n, c;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}
