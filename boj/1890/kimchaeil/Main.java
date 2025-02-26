//문제: 1890번 점프
//메모리: 12576 KB
//시간: 68 ms

/*
 * 오른쪽과 아래쪽으로만 가는 경우 DP로 가능
 */

import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer st = new StreamTokenizer(br);

	public static void main(String[] args) throws IOException {
		int n = nextInt();
		long[][] dp = new long[n][n];
		dp[0][0] = 1L; // 시작점을 1로 초기화
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int jump = nextInt(); // 점프력 입력
				if (i == n - 1 && j == n - 1) // 도착지면 break;
					break;
				if (dp[i][j] == 0) // 현재 위치에 도달하는 경로가 없으면 continue(이 부분을 하던 안하던 큰 차이는 없음)
					continue;
				if (i + jump < n) // 아래쪽 점프
					dp[i + jump][j] += dp[i][j];
				if (j + jump < n) // 오른쪽 점프
					dp[i][j + jump] += dp[i][j];
			}
		}
		System.out.println(dp[n - 1][n - 1]);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}