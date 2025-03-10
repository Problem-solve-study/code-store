//문제: BOJ 1699번 제곱수의 합
//메모리: 12544 KB
//시간: 128 ms

/*
 * 순조부로 안 풀리는 문제인거 같아서 dp라고 생각
 * 일단 dp[0]을 제외하고 모두 최대값으로 초기화한다.
 * 그 후, dp를 순차탐색한다. 이때 인덱스 값을 i라고 하자.
 * i보다 작은 제곱수의 집합을 S라고 하자.
 * i는 S에 포함된 원소 j에 대해 i-j로 부터 j를 더해서 도달한 값이라고 생각할 수 있다.
 * 즉, dp[i]는 S에 포함된 모든 원소 j에 대해 dp[i-j]+1의 최소값을 구하면 된다.
 */

import java.io.*;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		int n = nextInt();
		int sqrtN = (int) Math.sqrt(n) + 1;
		int[] squares = new int[sqrtN];
		int[] dp = new int[n + 1];
		for (int i = 1; i < sqrtN; i++)
			squares[i] = i * i;
		for (int i = 1; i < n + 1; i++)
			dp[i] = 100_001;

		for (int i = 1; i < n + 1; i++) {
			for (int j = 0; j < sqrtN && squares[j] <= i; j++) {
				dp[i] = Math.min(dp[i], dp[i - squares[j]] + 1);
			}
		}
		System.out.println(dp[n]);

	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
