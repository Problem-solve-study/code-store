/**
 * 	25224KB	300ms
 * 백트래킹 되겠네? => 시초
 * DP 써야겠네? => 초깃값 안줘서 시초
 * 뭐... 그렇습니다.
 * 
 * 단순 DP코드이기 때문에 설명은 생략합니다.
 * 며칠 안풀었더니 감 떨어졌네요
 */

import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static int[][] DP;
    static int[] dr = {-1, 0, 1};
    static int[] dc = {1, 1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        DP = new int[R][C];
        for (int r=0; r<R; ++r) Arrays.fill(DP[r], -1);
        
        int sr = 0;
        int sc = 0;
        for (int r=0; r<R; ++r) {
        	String line = br.readLine();
        	for (int c=0; c<C; ++c) {
        		map[r][c] = line.charAt(c);
        		if (map[r][c]=='R') {
        			sr = r;
        			sc = c;
        		}
        	}
        }
        int res = bt(sr, sc);
        System.out.println(res<0? -1:res);
    }
    
    static int bt(int r, int c) {
    	if (DP[r][c]==-1) {
    		DP[r][c] = Integer.MIN_VALUE;
        	if (map[r][c]=='O') DP[r][c] = 0;
        	for (int d=0; d<3; ++d) {
        		int nr = r+dr[d];
        		int nc = c+dc[d];
        		if (isValid(nr, nc) && map[nr][nc]!='#') DP[r][c] = Math.max(DP[r][c], bt(nr, nc));
        	}
        	DP[r][c] += (map[r][c]=='C'? 1:0);
    	}
    	return DP[r][c];
    }
    
    static boolean isValid(int r, int c) {
    	return 0<=r&&r<R && 0<=c&&c<C;
    }
}
