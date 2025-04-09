import java.io.*;
import java.util.*;

/*
 * 12180KB, 76ms
 * 
 * 예전에 BFS로 풀었던적이 있길래 오늘 플로이드 워셜 배우는 김에 플로이드 워셜로 풀이
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws Exception {
		int N = nextInt(); int M = nextInt();
		
		//dp 테이블 초기화
		int[][] dp = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				if (i == j) dp[i][j] = 0;
				else dp[i][j] = 100_000_000;
			}
		}
		
		//간선을 테이블에 바로 반영, 길이는 항상 1
		for (int i = 0; i < M; i++) {
			int a = nextInt();
			int b = nextInt();
			dp[a][b] = 1;
			dp[b][a] = 1;
		}
		
		//플로이드 워셜
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (dp[i][j] > dp[i][k] + dp[k][j]) {
						dp[i][j] = dp[i][k] + dp[k][j];
					}
				}
			}
		}
		
		//다른 정점들까지 가는 거리의 합이 최소인 정점을 찾아냄
		int min = Integer.MAX_VALUE;
		int minIdx = 0;
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				sum += dp[i][j];
			}
			
			if (min > sum) {
				min = sum;
				minIdx = i;
			}
		}
		System.out.print(minIdx);
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}