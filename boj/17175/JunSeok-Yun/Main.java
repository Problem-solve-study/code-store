// 17700KB ,176ms
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] dp = new long[51];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= n; i++){
			dp[i] = (1 + dp[i - 2] +  dp[i - 1]) % 1000000007;
		}
		System.out.println(dp[n]);
		sc.close();
	}
}
