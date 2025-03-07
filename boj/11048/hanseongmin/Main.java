import java.io.*;
import java.util.*;

/*
 * 22636KB, 224ms
 * 
 * 기초 dp문제인듯. 문제 조건대로 슥슥 구현해주면 된다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    public static void main(String[] args) throws Exception {
    	int N = nextInt();
    	int M = nextInt();
    	int[][] map = new int[N + 1][M + 1];
    	for (int i = 1; i <= N; i++) {
    		for (int j = 1; j <= M; j++) {
    			map[i][j] = nextInt();
    		}
    	}
    	
    	int[][] dp = new int[N + 1][M + 1];
    	for (int i = 1; i <= N; i++) {
    		for (int j = 1; j <= M; j++) {
    			//현재 위치에서 이전 위치의 최댓값을 가져오고 현재 맵에 존재하는 사탕을 가져가기
    			dp[i][j] = Math.max(dp[i - 1][j], Math.max(dp[i][j - 1], dp[i - 1][j - 1])) + map[i][j];
    		}
    	}
    	
    	System.out.println(dp[N][M]);
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}