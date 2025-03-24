//문제: BOJ 9205번 맥주 마시면서 걸어가기
//메모리: 13284 KB
//시간: 92 ms

/*
 * 50미터마다 맥주를 1병씩 마셔야 하고 최대 20개의 맥주를 들고 다닐 수 있으니 최대 1000미터를 한 번에 이동할 수 있다.
 * 처음 입력 받을 때, 1000미터 이하의 거리에 있는 장소들을 양방향으로 연결한다.
 * 위 과정을 통해 만들어진 그래프를 집을 시작으로 BFS를 돌린다.
 * BFS들 돌렸을 때, 락 페스티벌을 방문하면 행복하게 페스티벌에 갈 수 있는 것이다.
 */

import java.io.*;
import java.util.*;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int test_case = nextInt();
		while (test_case-- > 0) {
			int n = nextInt();
			int[][] pos = new int[2][n + 2];
			List<List<Integer>> edges = new ArrayList<>();
			for (int i = 0; i < n + 2; i++)
				edges.add(new ArrayList<>());

			for (int i = 0; i < n + 2; i++) {
				pos[0][i] = nextInt();
				pos[1][i] = nextInt();
				for (int j = 0; j < i; j++) { // 지금까지 입력받은 좌표들에 대해
					if (Math.abs(pos[0][i] - pos[0][j]) + Math.abs(pos[1][i] - pos[1][j]) <= 1000) { //거리가 1000이하라면 연결
						edges.get(i).add(j);
						edges.get(j).add(i);
					}
				}
			}

			Queue<Integer> queue = new ArrayDeque<>();
			boolean[] visited = new boolean[n + 2];
			queue.add(0);
			visited[0] = true;
			while (!queue.isEmpty()) { //bfs
				int now = queue.poll();
				for (int next : edges.get(now)) {
					if (!visited[next]) {
						queue.add(next);
						visited[next] = true;
					}
				}
			}
			sb.append(visited[n + 1] ? "happy\n" : "sad\n"); //페스티벌을 방문했다면 happy
		}
		System.out.println(sb);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
