/*
 * 0은 00만 된다.
 * 00, 1 을 붙여서 만들 수 있는 모든 가짓수.
 * N <= 1,000,000 길이가 백만
 * 길이가 N인 2진 수열의 개수를 15746으로 나눈 나머지.
 * 
 * dp[i-1] 에서 1을 붙이는 경우, dp[i-2]에서 00을 붙이는 경우 합.
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp = new int[N + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= N; i++)
			dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
		System.out.println(dp[N]);
	}
}
