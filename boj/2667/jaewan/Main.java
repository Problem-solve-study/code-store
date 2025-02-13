import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int N, cnt;
	static int[][] map;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int numDanji = 0;
		map = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String inStr = br.readLine();
			for (int j = 0; j < N; j++)
				map[i][j] = inStr.charAt(j) - '0';
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0 && !visit[i][j]) {
					map[i][j] = ++numDanji;
					visit[i][j] = true;
					cnt = 1;
					dfs(i, j, numDanji);
					pq.add(cnt);
				}
			}
		}
		System.out.println(numDanji);
		while(!pq.isEmpty()) System.out.println(pq.poll());
	}

	static void dfs(int i, int j, int num) {
		int[] dy = { 0, 0, 1, -1 }, dx = { 1, -1, 0, 0 };
		for (int dir = 0; dir < 4; dir++) {
			int ty = i + dy[dir];
			int tx = j + dx[dir];
			if (ty < 0 || tx < 0 || ty >= N || tx >= N)
				continue;
			if (map[ty][tx] == 1 && !visit[ty][tx]) {
				cnt++;
				map[ty][tx] = num;
				visit[ty][tx] = true;
				dfs(ty, tx, num);
			}
		}
	}
}
