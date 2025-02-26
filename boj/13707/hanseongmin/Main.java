import java.io.*;
import java.util.*;

/*
 * 109796KB, 240ms
 * 
 * 완탐을 돌리기엔 나오는 수가 너무 크다.
 * 완탐처럼 보이는데 시간초과가 날 것 같다 -> DP를 의심
 * 
 * dp[i][j]= i개를 선택해서 j를 만드는 경우의 수로 정의
 * 1개를 선택해서 j를 만드는 경우와 i개를 선택해서 0을 만드는 경우는 반드시 1이므로 초기화해주고
 * 이후에는 i - 1개를 선택해서 j를 만든 다음 0을 추가하는 경우와 i개를 선택해서 j - 1개를 만든 다음
 * 1을 더하는 경우의 수를 더해준다.
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);

    public static void main(String[] args) throws Exception {
    	final int MOD = 1_000_000_000;
    	int N = nextInt();
    	int K = nextInt();
    	int[][] dp = new int[K + 1][N + 1];
    	for (int i = 0; i <= N; i++) {
    		dp[1][i] = 1;
    	}
    	for (int i = 0; i <= K; i++) {
    		dp[i][0] = 1;
    	}
    	
    	for (int i = 2; i <= K; i++) {
    		for (int j = 1; j <= N; j++) {
    			dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
    		}
    	}
    	
    	bw.write(String.valueOf(dp[K][N]));
    	bw.flush();
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}