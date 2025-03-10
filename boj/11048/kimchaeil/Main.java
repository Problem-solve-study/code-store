//문제: BOJ 11048번 이동하기
//메모리: 15900 KB
//시간: 192 ms

/*
 * 오른쪽,아래쪽,오른아래쪽으로 이동가능하니 DP로 접근
 * 이동하는 횟수는 정해져 있지 않다. 또한 사탕은 무조건 0 이상이다.
 * 그러므로 오른아래쪽으로 가는 것 보다 오른쪽->아래쪽 또는 아래쪽->오른쪽으로 가는 것이 항상 이득이다. 즉, 오른아래쪽으로 이동하는 것은 고려하지 않는다.
 * 오른쪽과 아래쪽으로만 이동하니 1차원 배열로 가능
 */

import java.io.*;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {

		int n = nextInt(), m = nextInt();
		int[] dp = new int[m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				dp[j] = nextInt() + (dp[j - 1] > dp[j] ? dp[j - 1] : dp[j]); // 입력 받은 값은 현재 위치의 사탕, dp[j]는 (i-1,j)에서의 최대값, dp[j-1]은 (i,j-1)에서의 최대값
			}
		}
		System.out.println(dp[m]);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
