import java.io.*;
import java.util.*;

/*
 * 11596KB, 72ms
 * 
 * 수열을 구하는 문제이므로 DP라고 생각,
 * 그림을 살펴보면 점화식의 규칙을 쉽게 찾을 수 있다. 친절한 문제인듯
 * 100을 한 번 넣어보면 오버플로우가 발생하는 것을 알 수 있다. 테이블을 long으로 선언할 것
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		int T = nextInt();
		StringBuilder sb = new StringBuilder();
		while (T --> 0) {
			int N = nextInt();
			long[] dp = new long[101];
			dp[1] = dp[2] = dp[3] = 1;
			dp[4] = dp[5] = 2;
			for (int i = 6; i <= N; i++) {
				dp[i] = dp[i - 1] + dp[i - 5];
			}
			sb.append(dp[N]).append('\n');
		}
		System.out.print(sb);
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}