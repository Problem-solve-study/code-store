/*
 * 보물섬 육지(L), 바다(W)
 * 
 * 이웃한 육지로만 이동 가능, 이동하는데 한 시간 걸림.
 * 
 * 보물은 이동하는데 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀있다.
 * 
 * 보물이 묻혀 있는 두 곳 간의 최단 거리로 이동하는 시간 구해라.
 *
 * 최종단점. 임의의 점에서 BFS 한번, 종단점에서 BFS 한 번 . 두 번.
 * 
 * 가로, 세로 크기 50 이하.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BOJ2589_보물섬 {

	static int N, M;
	static int[] dy = { 0, 0, 1, -1 }, dx = { 1, -1, 0, 0 };
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		map = new int[N][M];
		ArrayList<int[]> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String inStr = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = inStr.charAt(j) == 'L' ? 1 : 0;
				if (map[i][j] == 1)
					list.add(new int[] { i, j });
			}
		}

		int max = 0;

		for (int[] cur : list) {
			int t = bfs(cur);
			max = Math.max(max, t);
		}

		System.out.println(max);
	}

	static int bfs(int[] start) {
		int max = 0;
		boolean[][] visit = new boolean[N][M];
		int[][] dist = new int[N][M];
		Queue<int[]> queue = new ArrayDeque<>();

		queue.offer(start);
		visit[start[0]][start[1]] = true;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			max = Math.max(max, dist[cur[0]][cur[1]]);

			for (int dir = 0; dir < 4; dir++) {
				int ty = cur[0] + dy[dir];
				int tx = cur[1] + dx[dir];
				if (ty < 0 || tx < 0 || ty >= N || tx >= M)
					continue;
				if (map[ty][tx] == 0 || visit[ty][tx])
					continue;
				visit[ty][tx] = true;
				dist[ty][tx] = dist[cur[0]][cur[1]] + 1;
				queue.offer(new int[] { ty, tx });
			}
		}

		return max;
	}
}
