//문제: 1012번 유기농 배추
//메모리: 13504 KB
//시간: 96 ms

/*
 * BFS
 */

import java.util.*;
import java.io.*;

public class Main {
	static int m;
	static int n;
	static boolean[][] map; //배추가 있는지 없는지만 필요하므로 boolean
	static boolean[][] visited;
	static int[][] d = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; //4방 탐색

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			map = new boolean[m][n];
			visited = new boolean[m][n];
			int[] arr = new int[k]; //배추 위치
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = true;
				arr[i]=x*n+y; //배추 위치를 x*n+y로 표현 -> int값 하나로 표현 가능
			}
			int result = 0;
			for (int now : arr) { //배추들에 대해
				if (!visited[now / n][now % n]) { //방문하지 않았다면
					result++; 
					bfs(now); //BFS
				}
			}
			System.out.println(result);
		}
	}

	public static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start / n][start % n] = true;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			int x = now / n;
			int y = now % n;
			for (int dir = 0; dir < 4; dir++) {
				int nx = x + d[dir][0];
				int ny = y + d[dir][1];
				if (nx >= 0 && ny >= 0 && nx < m && ny < n && map[nx][ny] && !visited[nx][ny]) {
					queue.add(nx * n + ny);
					visited[nx][ny] = true;
				}
			}
		}
	}
}
//map배열 없이 해보기
