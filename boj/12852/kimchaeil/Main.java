//문제: BOJ 12852번 1로 만들기 2
//메모리: 20444 KB
//시간: 148 ms

/*
 * 문제에서 주어진 3가지 연산이 모두 값이 줄어드는 연산이므로 DP를 선택
 * 처음에는 n에서 1로 가는 DP로 구현하였다.
 * 3가지의 연산을 if문을 통해 따로 작성을 하니 중복 코드가 생겼다.
 * 그래서 divide배열({3,2,1})과 minus({0,0,1})배열을 이용해 for문으로 수정하였다.
 * 또한 모듈러 연산을 통해 나누어 떨어지는지에 대한 연산에 대한 오버헤드와 비효율적인 경로 추적때문에
 * 연산을 반대로 하여 1에서 n으로 가는 DP로 변경하였다.
 * divide배열과 minus배열의 이름을 가독성을 위해 time와 plus로 각각 변경하였다.
 */

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

		int[][] dp = new int[2][n + 1]; // dp[0]: 도달 최소 횟수, dp[1]: 최단 경로일때 이전 값
		for (int i = 2; i <= n; i++)
			dp[0][i] = Integer.MAX_VALUE;

		int[] times = { 3, 2, 1 };
		int[] plus = { 0, 0, 1 };
		int idx = 1;

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				idx = i * times[j] + plus[j]; // 다음 값 연산
				if (idx > n) // 다음 값이 n보다 크면 X
					continue;
				if (dp[0][idx] > dp[0][i] + 1) { // 다음 값에 더 빨리 갈수 있는 경로라면
					dp[0][idx] = dp[0][i] + 1; // 최소 횟수 초기화
					dp[1][idx] = i; // 다음 값에 갈때의 경로 초기화
				}
			}
		}
		sb.append(dp[0][n]).append('\n');

		while (idx != 0) { // 경로 추적, 위 반복문이 끝나면 index==n
			sb.append(idx).append(' ');
			idx = dp[1][idx];
		}

		System.out.println(sb);
	}
}