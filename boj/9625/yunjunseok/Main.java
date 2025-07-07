//11508ms 68KB
import java.io.*;

public class Main {
	static int K;
	static int[][] dp;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		dp = new int[K + 1][2];
		dp[0][0] = 1; // A의 개수
		dp[0][1] = 0; // B의 개수
		dp[1][0] = 0;
		dp[1][1] = 1;

		for (int i = 2; i <= K; i++){
			dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
			dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
		}
		System.out.println(dp[K][0] + " " + dp[K][1]);
	}
}