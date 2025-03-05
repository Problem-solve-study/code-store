//문제: BOJ 2579번
//메모리: 11476 KB
//시간: 64 ms

/*
 * 처음을 제외하면 1칸 뛰기를 2번 연속불가능함
 * 배열 2개를 쓰는 dp
 */

import java.io.*;

public class Main {
	static StreamTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StreamTokenizer(br);

		int n = nextInt();
		if(n==1) { //계간이 1칸이면 해당 칸의 점수가 결과
			System.out.println(nextInt());
		}else {
			int[][] dp = new int[2][n];
			//dp[0] 한칸 점프로 오는 경우
			//dp[1] 두칸 점프로 오는 경우
			dp[0][0] = nextInt();
			dp[1][1] = nextInt();
			dp[0][1] = dp[0][0] + dp[1][1];
			int score;
			for (int i = 2; i < n; i++) {
				score = nextInt();
				dp[0][i] = dp[1][i - 1] + score;
				dp[1][i] = (dp[0][i-2]>dp[1][i-2]?dp[0][i-2]:dp[1][i-2]) + score;
			}
			System.out.println((dp[0][n-1]>dp[1][n-1]?dp[0][n-1]:dp[1][n-1]));			
		}
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}