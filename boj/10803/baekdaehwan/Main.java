/**
 * 19688KB	120ms
 * 최적화 개념으로 조건 추가하다가 AC났습니다... 
 * 증명은 못했고, 좋은 문제는 아닌거같네요
 */

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] DP;

	static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		N = ni();
		M = ni();
		int max = Math.max(N, M);
		int min = Math.min(N, M);
		DP = new int[max + 1][min + 1];

		System.out.println(getDP(N, M));
	}

	static int getDP(int n, int m) {
		if (n<m) {
			int tmp = m; m = n; n = tmp;
		}
		if (DP[n][m] == 0) {
			if (n%m == 0) {
				DP[n][m] = n/m;
			} else if ((double) n > m*2.25) {
				DP[n][m] = getDP(n-m, m) + 1;
			} else {
				DP[n][m] = Integer.MAX_VALUE;
				int tmp = (n>>1)+1;
				for (int s=1; s<tmp; ++s) {
					DP[n][m] = Math.min(DP[n][m], getDP(s, m)+getDP(n-s, m));
				}
				tmp = (m>>1)+1;
				for (int s=1; s<tmp; ++s) {
					DP[n][m] = Math.min(DP[n][m], getDP(n, s)+getDP(n, m-s));
				}
			}
		}
		return DP[n][m];
	}

	static int ni() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}
