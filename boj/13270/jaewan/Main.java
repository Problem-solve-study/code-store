package workspace;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int[] fibo = new int[20];
		fibo[0] = 1;
		fibo[1] = 2;
		for (int i = 2; i < 20; i++)
			fibo[i] = fibo[i - 1] + fibo[i - 2];
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp = new int[N + 1]; // dp[N] 구하면 정답
		dp[0] = 0;
		dp[1] = 0;
		int resMin = N % 2 == 0 ? N / 2 : (N + 1) / 2;
		for (int i = 2; i <= N; i++) { // dp[i] 를 계산.
			for (int j = 1; fibo[j] <= i; j++) dp[i] = Math.max(dp[i], dp[i-fibo[j]]+fibo[j-1]);
		}
		System.out.println(resMin + " " + dp[N]);
	}
}