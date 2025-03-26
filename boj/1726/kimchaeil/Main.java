//문제: BOJ 1726번 로봇
//메모리: 12992 KB
//시간: 76 ms

/*
 *  BFS
 */

import java.io.*;
import java.util.*;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		int m = nextInt();
		int n = nextInt();
		int size = m * n;
		boolean[] map = new boolean[size];
		boolean[] visited = new boolean[size << 2];
		int[][] d = { { 0, 0, 1, -1 }, { 1, -1, 0, 0 } };
		for (int i = 0; i < map.length; i++) {
			map[i] = nextInt() == 0;
		}

		int sy = nextInt() - 1;
		int sx = nextInt() - 1;
		int sd = nextInt() - 1;

		int ey = nextInt() - 1;
		int ex = nextInt() - 1;
		int ed = nextInt() - 1;

		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { sy, sx, sd, 0 });
		visited[sd * size + sy * n + sx] = true;
		int result = 0;
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			if (now[0] == ey && now[1] == ex && now[2] == ed) {
				result = now[3];
				break;
			}
			int y = now[0];
			int x = now[1];
			int dir = now[2];
			int depth = now[3];
			for (int i = 1; i < 4; i++) {
				int ny = y + d[0][dir] * i;
				int nx = x + d[1][dir] * i;
				int npos = ny * n + nx;
				if (nx < n && nx >= 0 && ny < m && ny >= 0) {
					if (!map[npos])
						break;
					if (!visited[dir * size + npos]) {
						queue.add(new int[] { ny, nx, dir, depth + 1 });
						visited[dir * size + npos] = true;
					}
				}
			}
			int pos = y * n + x;
			int nd = dir < 2 ? 2 : 0;
			if (!visited[nd * size + pos]) {
				queue.add(new int[] { y, x, nd, depth + 1 });
				visited[nd * size + pos] = true;
			}
			nd += 1;
			if (!visited[nd * size + pos]) {
				queue.add(new int[] { y, x, nd, depth + 1 });
				visited[nd * size + pos] = true;
			}
		}
		System.out.println(result);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
