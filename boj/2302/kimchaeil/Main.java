//문제: BOJ 2302번 극장 좌석
//메모리: 11456 KB
//시간: 64 ms

/*
 * VIP석의 존재를 생각하지 않고 인접한 자리에 앉을 수 있다는 조건만 생각하고
 * n이 1일때 부터 구해보니 a0=1,a1=1인 피보나치 수열이었다
 * VIP석이 있다면 VIP석 기준으로 왼쪽, 오른쪽이 나뉘고 각각의 dp값을 곱해주면 된다
 * 만약, n이 5이고 VIP석이 3이라면 왼쪽 2좌석, 오른쪽 2좌석이므로
 * dp[2]*dp[2]를 하면된다
 * VIP석이 여러개이므로 이에 대한 처리만 해주면 된다.
 */

import java.io.*;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String args[]) throws Exception {
		int n = nextInt();

		int[] dp = new int[n + 1];
		dp[0] = dp[1] = 1;
		for (int i = 2; i <= n; i++)
			dp[i] = dp[i - 1] + dp[i - 2];

		int m = nextInt();
		int result = 1;
		int idx = 1;
		while (m-- > 0) {
			int vip = nextInt();
			result *= dp[vip - idx];
			idx = vip + 1;
		}
		result *= dp[n - idx + 1];

		System.out.println(result);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
