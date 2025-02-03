//메모리: 13464KB
//시간: 112ms
import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] room = new int[n][n];
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				room[i][j]=sc.nextInt();
		int[][][] dp = new int[3][n][n];
		for(int i=1;i<n&&room[0][i]==0;i++)
			dp[0][0][i]=1;
		for(int i=1;i<n;i++) {
			for(int j=1;j<n;j++) {
				if(room[i][j]==0) {
					dp[0][i][j]=dp[0][i][j-1]+dp[1][i][j-1];
					dp[2][i][j]=dp[2][i-1][j]+dp[1][i-1][j];
					if(room[i-1][j]==0&&room[i][j-1]==0) 
						dp[1][i][j]=dp[0][i-1][j-1]+dp[1][i-1][j-1]+dp[2][i-1][j-1];
				}
			}
		}
		System.out.println(dp[0][n-1][n-1]+dp[1][n-1][n-1]+dp[2][n-1][n-1]);
		sc.close();
	}
}
