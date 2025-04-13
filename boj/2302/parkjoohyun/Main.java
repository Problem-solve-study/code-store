//Memory : 14156KB Time : 104ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main{
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int n;
	static int[] vips;
	static int[] dp;
	static void input() throws IOException {
		n = nextInt();
		int v = nextInt();
		vips = new int[v];
		dp = new int[n+2];
		for(int i=0;i<v;i++) {
			vips[i] = nextInt();
		}
		
		dp[0] =1;
		dp[1] =1;
		
	}
	
	static int DP(int n) {
		if(dp[n] != 0) return dp[n];
		return dp[n] = DP(n-1) + DP(n-2);
	}
	
	static void solution() {
		if(vips.length == 0) {
			System.out.println(DP(n));
			return;
		}
		
		int res = DP(vips[0]- 1);
		int v = vips.length;
		for(int i =1;i<v;i++) {
			res *= DP(vips[i]-vips[i-1]-1);
		}
		res *= DP(n-vips[v-1]);
		
		System.out.println(res);
		}
	
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}
	
	static int nextInt() throws IOException {
		st.nextToken();
		return (int)st.nval;
	}
}