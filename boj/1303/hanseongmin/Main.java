import java.io.*;
import java.util.*;

/*
 * 13312KB, 88ms
 * 
 * 그래프 탐색 돌리며 군집의 개수 세기
 */

public class Main {
	static int R, C, wSum, bSum;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static char[][] map;
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken()); R = Integer.parseInt(st.nextToken());
		map = new char[R][C]; v = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!v[i][j]) {
					bfs(i, j);
				}
			}
		}
		
		System.out.print(wSum + " " + bSum);
	}
	
	static void bfs(int sr, int sc) {
		int cnt = 0;
		Queue<Integer> q = new ArrayDeque<>();
		q.add(sr); q.add(sc); v[sr][sc] = true;
		while (!q.isEmpty()) {
			cnt++;
			int r = q.remove(); int c = q.remove();
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i]; int nc = c + dc[i];
				if (bc(nr, nc) && map[sr][sc] == map[nr][nc] && !v[nr][nc]) {
					q.add(nr); q.add(nc); v[nr][nc] = true;
				}
			}
		}
		
		//N명이 뭉쳐있을 때는 N*N의 위력을 낼 수 있음
		if (map[sr][sc] == 'W') wSum += (cnt * cnt);
		else bSum += (cnt * cnt);
	}
	
	static boolean bc(int r, int c) {
		return (0 <= r && r < R) && (0 <= c && c < C);
	}
}
