/** 65768KB	504ms
 * 
 *  단순 그래프 탐색 문제
 *  그래프 탐색 중 부모를 정하는 것 이외에 특별한 연산이 필요하지 않으므로 BFS를 사용하였음
 *  N개의 줄을 출력해야 하기 때문에 StringBuilder사용
 */

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Integer>[] adj;
	static int[] parent;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		adj = new List[N+1];
		parent = new int[N+1];
		visited = new boolean[N+1];
		for (int i=0; i<=N; ++i) adj[i] = new ArrayList<>();
		for (int i=0; i<N-1; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			adj[n1].add(n2);
			adj[n2].add(n1);
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		q.add(1);
		visited[1] = true;
		while (!q.isEmpty()) {
			int idx = q.poll();
			for (int nidx: adj[idx]) {
				if (!visited[nidx]) {
					visited[nidx] = true;
					parent[nidx] = idx;
					q.add(nidx);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i=2; i<=N; ++i) sb.append(parent[i]).append('\n');
		System.out.println(sb);
	}
}
