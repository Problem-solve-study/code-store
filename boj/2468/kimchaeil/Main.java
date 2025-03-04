//문제: BOJ 2468번 안전영역
//메모리: 28196 KB
//시간: 228 ms

/*
 * 비가 얼마나 오냐에 따라 안전한 영역이 달라짐
 * 비 양에 따라 BFS
 * 최소 result값은 1이다 (비가 오지 않을때 ~ 비가 최소값보다 작을때)
 * 즉, result의 초기값을 1로 두고
 * [min,max) 범위 bfs
 */

import java.io.*;
import java.util.*;

public class Main {
	static StreamTokenizer st;
	static int n;
	static int[][] map;
	static boolean[][] visited;
	static int[][] d = { { 1, 0, -1, 0 }, { 0, 1, 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StreamTokenizer(br);

		n = nextInt();
		map = new int[n][n];
		int min = Integer.MAX_VALUE;
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) { //입력 받으면서 map의 최대, 최소 구하기
				map[i][j] = nextInt();
				min = min > map[i][j] ? map[i][j] : min;
				max = max < map[i][j] ? map[i][j] : max;
			}
		}
		int result = 1; // 최소 result 값은 1
		for (int k = min; k < max; k++) { //[min,max) 범위 BFS
			visited = new boolean[n][n]; //visited 초기화
			int cnt = 0; //영역 개수
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!visited[i][j] && map[i][j] > k) { //방문한 적 없으면서 안전한 곳이면
						cnt++; //영역 개수 증가
						bfs(i * n + j, k); //bfs
					}
				}
			}
			result = result < cnt ? cnt : result;
		}
		System.out.println(result);
	}

	static int bfs(int start, int k) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start / n][start % n] = true;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			int i = now / n;
			int j = now % n;
			for (int dir = 0; dir < 4; dir++) { //4방 탐색
				int ni = i + d[0][dir];
				int nj = j + d[1][dir];
				if (ni >= 0 && nj >= 0 && ni < n && nj < n && !visited[ni][nj] && map[ni][nj] > k) {
					queue.add(ni * n + nj);
					visited[ni][nj] = true;
				}
			}
		}
		return 0;
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
