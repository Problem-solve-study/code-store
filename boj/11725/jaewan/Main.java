//94056 KB, 1576 ms
/*
 * 트리에서 연결된 주 노드가 주어진다.
 * 1과 다른 노드면, 1이 부모이다.
 * 부모는 하나니까, 이미 부모를 가진 놈이 있으면 나머지가 자식이다.
 * 만약 둘 다 연결되지 않은 경우엔, 나중에 처리한다.
 * 최악의 경우에 N^2 하면 시간초과 나는구나.
 * 
 * connectList를 노드마다 갖고 있으며 연결된 노드들을 추가한다.
 * 부모가 있는 녀석이면, 인접 노드들 모두 자식이다.
 * 부모가 생긴 녀석들을 queue에 넣어서 순차적으로 처리하면 O(N)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] parent = new int[N + 1];
		parent[1] = 1;

		Queue<Integer> list = new LinkedList<>();
		LinkedList<Integer>[] connectList = new LinkedList[N + 1];
		for (int i = 1; i <= N; i++)
			connectList[i] = new LinkedList<Integer>();
		for (int i = 1; i < N; i++) {
			String[] input = br.readLine().split(" ");
			int u = Integer.parseInt(input[0]);
			int v = Integer.parseInt(input[1]);
			connectList[u].add(v);
			connectList[v].add(u);
		}

		for (int t : connectList[1]) {
			parent[t] = 1;
			list.offer(t);
		}
		while (!list.isEmpty()) {
			int u = list.poll();
			for (int v : connectList[u])
				if (parent[v] == 0) {
					parent[v] = u;
					list.offer(v);
				}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= N; i++)
			sb.append(parent[i]).append('\n');
		System.out.println(sb.toString());
		
	}

	static class E {
		int u, v;

		public E(int u, int v) {
			this.u = u;
			this.v = v;
		}

	}
}
