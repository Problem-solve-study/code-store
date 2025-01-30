import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static int[][] attack = {{9,3,1},{9,1,3},{3,9,1},{3,1,9},{1,3,9},{1,9,3}};
	public static int[][][]dp = new int[61][61][61];
	public static int minVal = Integer.MAX_VALUE;

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] scvArr = new int[3];
		
		for (int i = 0; i < N; i++)
			scvArr[i] = Integer.parseInt(st.nextToken());
		
		dfs(scvArr[0], scvArr[1], scvArr[2], 0);
		System.out.println(minVal);
	}

	public static void dfs(int s1, int s2, int s3, int cnt){
		if (minVal <= cnt)
			return ;
		if (dp[s1][s2][s3] != 0 && dp[s1][s2][s3] <= cnt)
			return ;

		dp[s1][s2][s3] = cnt;
		if (s1 == 0 && s2 == 0 && s3 == 0){
			minVal = Math.min(minVal, cnt);
			return ;
		}

		for (int i = 0; i < 6; i++){
			dfs(Math.max(s1 - attack[i][0], 0), Math.max(s2 - attack[i][1], 0), Math.max(s3 - attack[i][2], 0), cnt + 1);
		}
	}
}
