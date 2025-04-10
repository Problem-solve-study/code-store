import java.io.*;
import java.util.*;

/*
 * 26740KB, 308ms
 * 
 * 간단한 DP. 각 원소마다 나올 수 있는 경우의 수를 고려해서 적절히 DP 테이블을 갱신 후 출력
 * 
 * dp[i][j]: i번째 행의 j번째 스티커까지 보았을 때의 최댓값
 * i, j번째 스티커를 본다고 했을 때 나올 수 있는 경우의 수는
 * 
 * 1. 현재 스티커는 붙이지 않는 경우 (dp[i][j - 1]을 택함)
 * 2. 현재 스티커를 붙이되, i, j - 2번째 최댓값을 고려 (dp[i][j - 2] + arr[i][j])
 * 3. 현재 스티커를 붙이되, i^1(반대쪽 행), j - 1번째 최댓값을 고려 (dp[i^1][j - 1] + arr[i][j])
 * 
 * 따라서 위 세 개의 값에 max를 적용시키며 갱신
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		int T = nextInt();
		StringBuilder sb = new StringBuilder();
		while (T --> 0) {
			int n = nextInt();
			int[][] arr = new int[2][n + 1];
			for (int i = 0; i < 2; i++) {
				for (int j = 1; j <= n; j++) {
					arr[i][j] = nextInt();
				}
			}
			
			int[][] dp = new int[2][n + 1];
			dp[0][1] = arr[0][1]; dp[1][1] = arr[1][1];
			for (int i = 2; i <= n; i++) {
				dp[0][i] = Math.max(dp[0][i - 1], Math.max(dp[0][i - 2], dp[1][i - 1]) + arr[0][i]);
				dp[1][i] = Math.max(dp[1][i - 1], Math.max(dp[1][i - 2], dp[0][i - 1]) + arr[1][i]);
			}
			
			sb.append(Math.max(dp[0][n], dp[1][n])).append('\n');
		}
		System.out.print(sb);
	}

	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}
