import java.io.IOException;
import java.util.Arrays;

/*
 * 자연수 N을 제곱수들의 합으로 나타낼 때 최소 항의 개수
 * N <= 100,000
 * 이전에 구한 값을 활용
 * dp[11] = (9) + dp[2], (4) + dp[7], (1) + dp[10]
 * 
 * sqrt(100,000) = 300. 
 */
public class BOJ1699_제곱수의합 {

	public static void main(String[] args) throws IOException {
		int N = readInt();
		int[] dp = new int[N + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			int root = (int) Math.sqrt(i);
			for (int j = 1; j <= root; j++) {
				dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
			}
		}
		System.out.println(dp[N]);
	}

	private static int readInt() throws IOException {
		int c;
		do {
			c = System.in.read();
		} while (c <= 32);
		int n = (c & 15);
		while ((c = System.in.read()) > 47) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return n;
	}
}
