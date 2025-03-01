/*
 * N개의 도시 동쪽에서 서쪽
 * 제일 동쪽에 1번, 제일 서쪽에 N번 도시
 * 
 * M개 이하의 도시를 지나는 여행을 계획
 * 1번에서 시작해 N번에서 끝나야 함.
 * 
 * 계속 서쪽으로만, 기내식의 점수의 총 합이 최대가 되게.
 * 
 * 1 <= N <= 300, 2 <= M <= N, 1 <= K <= 100,000
 * 
 * 번호가 증가하는 방향으로만 이동한다.
 * 같은 (a, b) 쌍이면, 기내식의 맛 c가 더 높은 항로만 선택한다.
 * 
 * 인접 행렬을 사용해서, a, b, c 최댓값만 저장하자.
 * 
 * 그리고, dp[N][M] ,
 * dp[i][j] 는 i번째 도시에 총 j개의 도시 방문했을때 기내식 맛의 최댓값
 * dp[0][1] 부터 출발.
 * dp[i][j] = max(dp[0~i-1 중 i로 연결되어 있는 도시][j-1] + c, dp[i][j]) 
 * 결국 N개의 도시에 대해 N^2 탐색하니까 O(N^3) = 27,000,000
 * 
 * 인접 리스트 사용하면, taste[0~i-1][i] 연결되어 있는 항공로 중 최댓값 탐색 빠르게 가능한가?
 * 모르겠다. 
 * 
 * 혹시 도시 M 개를 방문 안해도 최댓값일 수도 있지 않을까?
 * 
 * 처음에 dp를 -1로 초기화 해야겠다.
 * 
 */

import java.io.IOException;
import java.util.Arrays;

public class BOJ2157_여행 {

	static int N, M;
	static int[][] taste, dp;

	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		taste = new int[N + 1][N + 1];
		dp = new int[N + 1][M + 1];
		for (int i = 0; i < N + 1; i++)
			Arrays.fill(dp[i], -1);

		int K = readInt(), a, b, c;
		for (int i = 0; i < K; i++) {
			a = readInt();
			b = readInt();
			c = readInt();
			if (a >= b)
				continue;
			taste[a][b] = Math.max(taste[a][b], c);
		}
		dp[1][1] = 0;

		for (int i = 2; i <= N; i++) {
			for (int j = 1; j < i; j++) {
				if (taste[j][i] > 0) { // j -> i 연결됨.
					for (int k = 2; k <= M; k++) {
						if (dp[j][k - 1] == -1) // dp[j][k-1]이 갈 수 없는 곳이면.
							continue;
						dp[i][k] = Math.max(dp[i][k], dp[j][k - 1] + taste[j][i]);
					}
				}
			}
		}

		int max = 0;
		for (int i = 2; i <= M; i++)
			max = Math.max(max, dp[N][i]); // 최댓값 찾기
		System.out.println(max);
	}

	private static int readInt() throws IOException {
		int c;
		do {
			c = System.in.read();
		} while (c <= 32);
		int n = c & 15;
		c = System.in.read();
		while (c > 47) {
			n = (n << 3) + (n << 1) + (c & 15);
			c = System.in.read();
		}
		return n;
	}

}
