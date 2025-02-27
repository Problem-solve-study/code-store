//문제: 2589번 보물섬
//메모리: 170052 KB
//시간: 372 ms

/*
 * 모든 육지에 대해 BFS
 * 평소 queue를 배열의 queue로 사용했는데
 * 다른 풀이도 익숙해지기 위해 이번에는 클래스를 만들어 queue 사용
 */

import java.io.*;
import java.util.*;

class Pos {
	int i, j, distance;

	public Pos(int i, int j, int distance) {
		this.i = i;
		this.j = j;
		this.distance = distance;
	}
}

public class Main {
	static StreamTokenizer st;
	static int r, c;
	static boolean[][] map;
	static boolean[][] visited;
	static int[][] d = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new boolean[r][c];

		for (int i = 0; i < r; i++) {
			String line = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = line.charAt(j) == 'W' ? false : true;
			}
		}

		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				if (map[i][j]) //육지면
					bfs(new Pos(i, j, 0)); //BFS

		System.out.println(answer);
	}

	static int bfs(Pos start) {
		visited = new boolean[r][c];
		Queue<Pos> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start.i][start.j] = true;

		while (!queue.isEmpty()) {
			Pos now = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int ni = now.i + d[dir][0];
				int nj = now.j + d[dir][1];
				if (ni >= 0 && nj >= 0 && ni < r && nj < c && !visited[ni][nj] && map[ni][nj]) { //범위 안이고 방문하지 않았고 육지라면
					visited[ni][nj]=true;
					queue.add(new Pos(ni,nj,now.distance+1));
					answer = Math.max(answer, now.distance+1);
				}
			}
		}
		return 0;
	}
}