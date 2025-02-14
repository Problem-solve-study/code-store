import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] visit;
	static int[] dy = { 0, 0, -1, 1 }, dx = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int cnt = 0;
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			int K = Integer.parseInt(input[2]);
			map = new int[N][M];
			visit = new boolean[N][M];
			for (int i = 0; i < K; i++) {
				input = br.readLine().split(" ");
				map[Integer.parseInt(input[0])][Integer.parseInt(input[1])] = 1;
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1 && !visit[i][j]) {
						cnt++;
						bfs(i, j);
					}
				}
			}
			System.out.println(cnt);
		}
	}

	static void bfs(int y, int x) {
		Queue<int[]> queue = new LinkedList<>();
		visit[y][x] = true;
		queue.offer(new int[] { y, x });
		while (!queue.isEmpty()) {
			int[] pos = queue.poll();
			for (int i = 0; i < 4; i++) {
				int ty = pos[0] + dy[i];
				int tx = pos[1] + dx[i];
				if (ty < 0 || tx < 0 || ty >= N || tx >= M)
					continue;
				if (map[ty][tx] == 1 && !visit[ty][tx]) {
					visit[ty][tx] = true;
					queue.offer(new int[] { ty, tx });
				}
			}
		}
	}
}
