//문제: 2667번 단지번호붙이기
//메모리: 11796 KB
//시간: 68 ms

import java.util.*;
import java.io.*;

/*
 * 2차원 배열 4방 탐색 BFS
 */

public class Main {
	static boolean[][] isHouse; // 집이면 true, 아니면 false
	static boolean[][] visited; // 방문한 집인지
	static int[][] d = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // 4방탐색
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		isHouse = new boolean[n][n];
		visited = new boolean[n][n];

		for (int i = 0; i < n; i++) { // isHouse 입력
			String input = br.readLine();
			for (int j = 0; j < n; j++) {
				isHouse[i][j] = input.charAt(j) == '0' ? false : true;
			}
		}

		List<Integer> houseCount = new ArrayList<>(); // 단지별 집 개수를 저장할 곳
		int cnt = 0; // 단지 개수
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (isHouse[i][j] && !visited[i][j]) { // 방문하지 않은 집이면
					houseCount.add(bfs(i * n + j)); // BFS 돌리기
					cnt++;// 단지 개수 증가
				}
			}
		}

		houseCount.sort(null); // houseCount 오름차순 정렬
		bw.write(cnt + "\n"); // 단지 수 출력
		for (int res : houseCount) { // 단지별 집 개수 오름차순 출력
			bw.write(res + "\n");
		}
		bw.flush();
		bw.close();
	}

	public static int bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>(); // queue에 들어갈 형식은 (n*y+x)을 계산한 int 값
		queue.add(start);
		visited[start / n][start % n] = true;
		int cnt = 0;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			cnt++;
			int y = now / n;
			int x = now % n;
			for (int dir = 0; dir < 4; dir++) { // 4방 탐색
				int ny = y + d[dir][0];
				int nx = x + d[dir][1];
				if (ny >= 0 && nx >= 0 && ny < n && nx < n && !visited[ny][nx] && isHouse[ny][nx]) { // nx와 ny가 배열을 벗어나지
																										// 않고 방문하지 않은
																										// 집이면
					queue.add(ny * n + nx);
					visited[ny][nx] = true;
				}
			}
		}

		return cnt;
	}
}
