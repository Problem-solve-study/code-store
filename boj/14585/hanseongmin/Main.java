import java.io.*;
import java.util.*;

/*
 * 13072KB, 76ms
 * 
 * BFS가 제일 먼저 생각났고, 위쪽이랑 오른쪽만 가능하니까 DP도 가능할 것 같았다.
 * 일단 BFS로 먼저 구현했는데
 * 처음에 x, y 좌표 잘못 설정해서 1틀
 * 이후 BFS로 제대로 짰는데도 메모리 초과가 뜨길래 메모리 제한을 그제서야 보고 의도가 DP임을 알았다.
 * 
 * DP[i][j]: i, j 위치에서 먹을 수 있는 사탕의 최대 개수로 정의하고 적절히 테이블을 채워주면 된다.
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		int N = nextInt(); int M = nextInt();
		boolean[][] isExist = new boolean[301][301];
		for (int i = 0; i < N; i++) {
			int x = nextInt();
			int y = nextInt();
			isExist[y][x] = true;
		}
		
		int[][] dp = new int[301][301];
		for (int i = 0; i <= 300; i++) {
			for (int j = 0; j <= 300; j++) {
				if (i - 1 >= 0) {
					dp[i][j] = dp[i - 1][j];
				}
				if (j - 1 >= 0) {
					dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
				}
				
				//현재 위치에 사탕바구니가 있다면 dp 테이블 갱신
				if (isExist[i][j]) {
					//1초마다 사탕이 하나씩 줄어드니까 현재 시점에서 남아있는 사탕은 M - (i + j)다.
					int candy = Math.max(M - (i + j), 0);
					dp[i][j] += candy;
				}
			}
		}
		
		System.out.print(dp[300][300]);
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}