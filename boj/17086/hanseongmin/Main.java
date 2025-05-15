import java.io.*;
import java.util.*;

/*
 * 16092KB, 144ms
 * 
 * 그래프 탐색하면서 안전 거리가 더 작다면 안전 거리 갱신
 * 이후 거리 배열을 탐색하며 거리의 최댓값을 출력하면 된다.
 * 안전 거리를 계산할 때는 최솟값으로, 출력할 때는 최댓값을 탐색
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	static int N, M;
	static int[][] map, dist;
	static ArrayList<int[]> sharkPos = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		N = nextInt(); M = nextInt();
		map = new int[N][M];
		dist = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = nextInt();
				dist[i][j] = Integer.MAX_VALUE;
				if (map[i][j] == 1) {
					sharkPos.add(new int[] {i, j});
				}
			}
		}
		
        //BFS 수행
		Queue<Integer> q = new ArrayDeque<>();
		for (int[] shark : sharkPos) {
			q.clear();
			q.add(shark[0]); q.add(shark[1]); q.add(0);
			dist[shark[0]][shark[1]] = 0;
			
			while (!q.isEmpty()) {
				int r = q.remove(); int c = q.remove(); int cnt = q.remove();
				for (int i = 0; i < dr.length; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
                    //기록된 안전 거리가 현재 안전 거리보다 크다면 갱신함
					if (bc(nr, nc) && dist[nr][nc] > cnt + 1) {
						q.add(nr); q.add(nc); q.add(cnt + 1);
						dist[nr][nc] = cnt + 1;
					}
				}
			}
		}
		
        //출력할 때는 안전 거리의 최댓값 출력
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					res = Math.max(res, dist[i][j]);
				}
			}
		}
		
		System.out.print(res);
	}
	
	static boolean bc(int r, int c) {
		return (0 <= r && r < N) && (0 <= c && c < M);
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
	
}