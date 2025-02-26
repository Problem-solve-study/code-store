import java.io.*;
import java.util.*;

/*
 * 12648KB, 72ms
 * 
 * BFS인가 싶었는데 경로의 개수를 보고 DP인가보다 싶었다.
 * dp[i][j]: i, j 위치일 때 가능한 경로의 개수라고 정의하고
 * i ~ 0, j ~ 0로 루프를 돌면서 현재 위치에서 점프할 수 있는 위치로의 값을 갱신하여 구할 수 있다. 
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    static int n;

    public static void main(String[] args) throws Exception {
    	n = nextInt();
    	int[][] map = new int[n][n];
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			map[i][j] = nextInt();
    		}
    	}
    	
    	//경로의 개수가 long 범위이므로 long으로 정의
    	long[][] dp = new long[n][n];
    	//맨 처음 위치의 경로는 1개
    	dp[0][0] = 1;
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			int jump = map[i][j];
    			if (jump == 0) continue;
    			//아래로 점프할 수 있다면 아래로 점프하고 해당 위치 값 갱신
    			if (boundaryCheck(i + jump, j)) {
    				dp[i + jump][j] += dp[i][j];
    			}
    			
    			//오른쪽으로 점프할 수 있다면 오른쪽으로 점프하고 해당 위치 값 갱신
    			if (boundaryCheck(i, j + jump)) {
    				dp[i][j + jump] += dp[i][j];
    			}
    		}
    	}
    	
    	bw.write(String.valueOf(dp[n - 1][n - 1]));
    	bw.flush();
    }
    
    static boolean boundaryCheck(int r, int c) {
    	return (0 <= r && r < n) && (0 <= c && c < n);
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}