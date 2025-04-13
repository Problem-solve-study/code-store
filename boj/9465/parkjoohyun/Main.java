//Memory : 26096kb Time : 308ms

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main{
	
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int n, t;
	static int[][] scores;
	public static void main(String[] args) {
		t = nextInt();
		for(int i =0;i<t;i++) {
			input();
			dp();
		}
	}
	
	public static void dp() {
		int[][] dp = new int[2][n+1];
		dp[0][0] = scores[0][0];
		dp[1][0] = scores[1][0];
		dp[1][1] = dp[0][0] + scores[1][1];
		dp[0][1] = dp[1][0] + scores[0][1];
		

		for(int j = 2;j<n;j++) {
			for(int i=0;i<2;i++) {
				int value = Math.max(dp[(i+1)%2][j-1],dp[(i+1)%2][j-2]);
				dp[i][j] = value + scores[i][j];
			}
		}
		
		System.out.println(Math.max(dp[0][n-1], dp[1][n-1]));
	}
	
	public static void input() {
		n = nextInt();
		
		scores = new int[2][n+1];
		
		for(int i =0;i<2;i++) {
			for(int j =0;j<n;j++) {
				scores[i][j] = nextInt();
			}
		}
	}
	public static int nextInt() {
		try {
			st.nextToken();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return (int)st.nval;
	}
}