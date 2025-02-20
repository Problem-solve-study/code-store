import java.io.*;
import java.util.*;

/*
 * 26752KB, 103ms
 * 
 * 2차원 누적합 + 브루트포스
 */

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    
    public static void main(String[] args) throws Exception {
    	int T = nextInt();
    	StringBuilder sb = new StringBuilder();
    	for (int t = 1; t <= T; t++) {
    		int n = nextInt();
    		int m = nextInt();
    		//배열 입력받는 동시에 누적합 배열 생성
    		int[][] map = new int[n + 1][n + 1];
    		for (int i = 1; i <= n; i++) {
    			for (int j = 1; j <= n; j++) {
    				map[i][j] = nextInt() + map[i - 1][j] + map[i][j - 1] - map[i - 1][j - 1];
    			}
    		}
    		
    		//모든 경우를 탐색하며 최댓값 탐색
    		//죽는 파리의 수는 누적합으로 O(1)에 계산
    		int max = 0;
    		for (int i = m; i <= n; i++) {
    			for (int j = m; j <= n; j++) {
    				int r1 = i - m;
    				int c1 = j - m;
    				int r2 = i;
    				int c2 = j;
    				max = Math.max(max, map[r2][c2] - map[r2][c1] - map[r1][c2] + map[r1][c1]);
    			}
    		}
    		
    		sb.append(String.format("#%d %d\n", t, max));
    	}
    	
    	bw.write(sb.toString());
    	bw.flush();
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}