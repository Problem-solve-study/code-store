// 11400 KB, 68 ms
/*
 * 한 번에 한 계단씩, 또는 두 계단씩
 * 연속 세개 모두 밟으면 안됨. 시작점은 포함 x
 * 마지막은 반드시 밟기.
 * 
 * dp[i][j] i번째 계단, 연속 j 개 밟아서 최댓값
 * 
 * dp[i][1] = 연속 1개째, 즉 두 계단 올라야 하므로
 * max(dp[i-2][1], dp[i-2][2]) + 현재 점수
 * 
 * dp[i][2] = 연속 2개째, dp[i-1][1] + 현재 점수
 */
import java.io.IOException;

public class BOJ2579_계단오르기 {

	public static void main(String[] args) throws IOException {
		int N = readInt();
		int[][] dp = new int[N + 2][3];
		for (int i = 2; i < N + 2; i++) {
			int score = readInt();
			dp[i][1] = Math.max(dp[i - 2][1], dp[i - 2][2]) + score;
			dp[i][2] = dp[i - 1][1] + score;
		}
		System.out.println(Math.max(dp[N + 1][1], dp[N + 1][2]));
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
