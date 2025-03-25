//	14636KB	192ms
import java.io.*;
import java.util.*;

public class Main {
	static int n, map[][];
	static int[] dx = {1,0,0,-1};
	static int[] dy = {0,1,-1,0};
	static boolean[][] visited;
	static int answer;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		int max = 0;
		for (int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[i][j]);
			}
		}
		visited = new boolean[n][n];
		for (int limit=0; limit<=max; limit++) {
			clear();
			int count = 0;
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					if (!visited[i][j] && map[i][j] > limit) {
						dfs(i, j, limit);
						count++;
					}
				}
			}
			answer = Math.max(answer, count);
		}
		System.out.print(answer);
	}
	
	
	static void dfs(int y, int x, int limit) {
		visited[y][x] = true;
		
		for (int i=0; i<4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny>=0 && ny<n && nx>=0 && nx<n) {
				if (!visited[ny][nx] && map[ny][nx] > limit) {
					dfs(ny, nx, limit);
				}
			}
		}
	}
	
	static void clear() {
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				visited[i][j] = false;
			}
		}
	}
}
