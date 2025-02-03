import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		int[][][] dp = new int[n + 1][n + 1][3];
		dp[1][2][0] = 1; // 시작 위치 설정

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (map[i][j] == 1)
					continue; // 벽인 경우 스킵

				if (j > 2)
					dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];

				if (i > 1)
					dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];

				if (i <= 1 || j <= 1 || map[i - 1][j] == 1 || map[i][j - 1] == 1)
					continue;
				dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
			}
		}

		System.out.println(Arrays.stream(dp[n][n]).sum());
	}
}