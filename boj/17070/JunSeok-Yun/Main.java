//14272KB ,104ms
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int res = 0;
		StringTokenizer st;
		
		int [][]map = new int[N][N];
		int [][][]dp = new int[N][N][3]; // 가로 : 0, 세로 : 1, 대각선 : 2
		
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		dp[0][1][0] = 1; // 초기값 세팅, 파이프의 끝 위치가 (0,1)이고 가로인 상태
		for (int i = 0; i < N; i++){
			for (int j = 2; j < N; j++){
				if (j - 1 >= 0 && map[i][j] == 0)
					dp[i][j][0] = dp[i][j - 1][0] + dp[i][j-1][2];
				if (i - 1 >= 0 && map[i][j] == 0)
					dp[i][j][1] = dp[i-1][j][1] + dp[i - 1][j][2];
				if (i - 1 >= 0 && j - 1 >= 0 && map[i][j] == 0 && map[i-1][j] == 0 && map[i][j - 1] == 0)
					dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2]; 
			}
		}
		res = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
		System.out.println(res);
	}
}
