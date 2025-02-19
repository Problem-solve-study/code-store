import java.io.*;
import java.util.*;

/*
32896KB, 147ms

단순 구현 문제
 */

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[][] mat;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    
    public static void main(String[] args) throws Exception {
    	int T = nextInt();
    	for (int t = 1; t <= T; t++) {
    		n = nextInt();
    		mat = new int[n][n];
    		int r = 0;
    		int c = 0;
    		int d = 0;
    		int v = 1;
    		mat[r][c] = v;
    		while (v != n * n) {
    			//갈 수 있는 경로가 없으면 다음 방향으로
    			if (!boundaryCheck(r + dr[d], c + dc[d]) || 
    					mat[r + dr[d]][c + dc[d]] != 0) {
    				d = (d + 1) % 4;
    			} else {
    				//갈 수 있으면 값 할당
        			r += dr[d];
        			c += dc[d];
    				mat[r][c] = ++v;
    			}
    		}
    		
    		sb.append(String.format("#%d\n", t));
    		for (int i = 0; i < n; i++) {
    			Arrays.stream(mat[i]).forEach(e -> sb.append(e).append(" "));
    			sb.append('\n');
    		}
    	}
    	
    	bw.write(sb.toString());
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