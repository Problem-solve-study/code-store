//메모리: 13560 KB
//시간: 132 ms

import java.io.*;
import java.util.*;
/*
최단 거리 미로 탐색이므로 BFS 선택
*/
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		Queue<int[]> queue = new ArrayDeque<>(); //[0]은 좌표, [1]은 카운트
		//좌표 표현을 x와 y를 따로 표현하지 않고 y*m+x로 표현
		boolean[][] visited = new boolean[n][m];
		queue.add(new int[] { 0, 1 }); //시작지점도 카운트에 포함이므로 카운트를 1부터 시작
		visited[0][0] = true;
		int[][] d = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; //4방탐색
		while (!queue.isEmpty()) { //BFS
			int[] now = queue.poll();
			if (now[0] == n * m - 1) {
				System.out.println(now[1]);
				break;
			}
			int x = now[0] % m;
			int y = now[0] / m;
			for (int dir = 0; dir < 4; dir++) {
				int nx = x + d[dir][1];
				int ny = y + d[dir][0];
				if (nx >= 0 && ny >= 0 && nx < m && ny < n && map[ny][nx]==1 && !visited[ny][nx]) {
					queue.add(new int[] { ny * m + nx, now[1] + 1 });
					visited[ny][nx]=true;
				}
			}
		}
	}

}
