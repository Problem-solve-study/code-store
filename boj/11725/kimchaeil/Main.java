//문제: 11725번 트리의 부모 찾기
//메모리: 37444 KB
//시간: 344 ms

import java.util.*;
import java.io.*;

/*
 * 첫 입력 당시에는 누가 부모인지 모르기에 그래프처럼 연결하고
 * 시작하는 노드(루트 노드)를 문제에서 말하듯 1번으로 bfs 또는 dfs를 돌리면
 * 부모,자식 관계를 알 수 있다.
 * 아래는 bfs로 구현하였다.
 */

public class Main {
	static List<List<Integer>> link; // 입력 받았을 때, 두 노드의 연결을 저장
	static int[] parents; // 각 노드의 부모 노드의 번호를 저장
	static boolean[] visited; // 방문한 노드인지

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(br);
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine()); // 노드 수

		link = new ArrayList<>();
		parents = new int[n + 1];
		visited = new boolean[n + 1];

		for (int i = 0; i <= n; i++) {
			link.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < n - 1; i++) { // 처음에는 누가 부모인지 모르기에 양방향 그래프처럼 연결
			st.nextToken();
			int a = (int) st.nval;
			st.nextToken();
			int b = (int) st.nval;
			link.get(a).add(b);
			link.get(b).add(a);
		}
		bfs();
		for (int i = 2; i <= n; i++) {
			sb.append(parents[i]);
			sb.append('\n');
		}

		System.out.println(sb);
	}

	public static int bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(1); // 1이 무조건 루트 노드이므로 1부터 시작
		visited[1] = true;

		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int next : link.get(now)) {
				if (!visited[next]) { // 아직 방문하지 않은 next 노드들은 무조건 now의 자식 노드이다.
					parents[next] = now;
					visited[next] = true;
					queue.add(next);
				}
			}
		}

		return 0;
	}
}