//문제: BOJ 1535번 안녕
//메모리: 11524 KB
//시간: 64 ms

/*
 * 잃는 체력을 물건의 무게, 얻는 기쁨을 물건의 가치, 기본 체력 100을 가방의 용량으로 보면 배낭문제와 동일하다.
 * 배낭의 용량이 100인 배낭문제로 해결
 * 해결 방법으로는 dp를 사용
 * 
 * +) 잃는 체력의 최소값(min) 이전에는 아무 사람과도 인사를 할 수 없으므로 min부터 dp
 */

import java.io.*;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		int n = nextInt();
		int[][] people = new int[2][n + 1]; // 0: 잃는 체력(무게), 1: 얻는 기쁨(가치)

		int min = 101;
		for (int j = 1; j <= n; j++) {
			people[0][j] = nextInt();
			min = min>people[0][j]?people[0][j]:min; //잃는 체력의 최솟값 구하기
		}
		for (int j = 1; j <= n; j++)
			people[1][j] = nextInt();

		int[][] dp = new int[n + 1][100];
		for (int i = 1; i <= n; i++) { // 배낭 문제 dp
			for (int j = min; j < 100; j++) { //min부터 dp
				if (people[0][i] > j)
					dp[i][j] = dp[i - 1][j];
				else
					dp[i][j] = Math.max(dp[i - 1][j], people[1][i] + dp[i - 1][j - people[0][i]]);
			}
		}

		System.out.println(dp[n][99]);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
