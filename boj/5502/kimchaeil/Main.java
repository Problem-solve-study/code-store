//문제: BOJ 5502 팬린드롬
//메모리: 109944 KB
//시간: 252 ms

/*
 * 입력 문자열과 뒤집은 문자열의 최장 공통 부분수열의 길이를 구해주고 문자열의 길이와의 차를 출력
 */

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		char[] input = br.readLine().toCharArray();
		char[] reverse = new char[N];
		for (int i = 0; i < N; i++)
			reverse[i] = input[N - 1 - i];
		
		int[][] dp = new int[N + 1][N + 1];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				dp[i + 1][j + 1] = input[i] == reverse[j] ? dp[i][j] + 1 : Math.max(dp[i][j + 1], dp[i + 1][j]);
		
		System.out.println(N - dp[N][N]);
	}
}
