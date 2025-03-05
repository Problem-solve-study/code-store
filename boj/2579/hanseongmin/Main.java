import java.io.*;
import java.util.*;

/*
 * 11516KB, 64ms
 * 
 * DP의 느낌이 풀풀 나는 문제.
 * 연속된 3개의 계단을 밟으면 안되므로 DP 테이블을 2차원으로 구성
 * dp[i][j]: i번째 계단을 밟았을 때의 점수의 최댓값.
 * j가 0이면 직전 계단을 밟지 않은 경우
 * j가 1이면 직전 계단을 밟는 경우
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		int N = nextInt();
		int[] arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = nextInt();
		}
		
		int[][] dp = new int[301][2];
		dp[1][0] = arr[1]; dp[1][1] = arr[1]; 
		for (int i = 2; i <= N; i++) {
			//직전 계단을 밟지 않으므로 i - 2번째 계단의 최댓값 사용
			dp[i][0] = arr[i] + Math.max(dp[i - 2][0], dp[i - 2][1]);
			//직전 계단을 밟는 경우에는 i - 1번째 계단을 밟는 동시에
			//i - 1번째 계단이 직전 계단을 밟으면 안됨
			dp[i][1] = arr[i] + dp[i - 1][0];
		}
		
		System.out.println(Math.max(dp[N][0], dp[N][1]));
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}
