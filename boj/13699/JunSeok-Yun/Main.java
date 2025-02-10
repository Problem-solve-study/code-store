//17580KB, 176ms
import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long[] dp = new long[36];
		dp[0] = 1;
		for (int i = 0; i < N; i++){
			long res = 0;
			for (int j = 0; j <= i; j++){
				res += (dp[j] * dp[i - j]);
			}
			dp[i + 1] = res;
		}
		System.out.println(dp[N]);
		sc.close();
	}
}
