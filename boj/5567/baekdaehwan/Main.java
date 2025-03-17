/**
 * 18308KB	140ms
 * 
 * [사고 흐름]
 * 특정 노드로부터 2 이내의 노드 개수를 찾는 문제라고 이해한 후, BFS 문제임을 알 수 있었다.
 * 확실한 방법이며, 시험 중 풀게 된 문제이기에 바로 구현을 시작했다.
 */

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static boolean[] visited;
	static List<List<Integer>> adj;
	static Queue<int[]> queue;
	
	static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		N = ni();
		M = ni();
		visited = new boolean[N];
		adj = new ArrayList<>();
		queue = new ArrayDeque<>();
		for (int i=0; i<N; ++i) adj.add(new ArrayList<>());
		for (int i=0; i<M; ++i) {
			int a = ni()-1;
			int b = ni()-1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		
		int cnt = -1;
		visited[0] = true;
		queue.add(new int[] {0, 0});
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			if (cur[1] > 2) continue;
			++cnt;
			for (int next: adj.get(cur[0])) {
				if (!visited[next]) {
					visited[next] = true;
					queue.add(new int[] {next, cur[1]+1});
				}
			}
		}
		System.out.println(cnt);
	}
	
	public static int ni() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}
