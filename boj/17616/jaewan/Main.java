// 51976 KB, 408 ms
/*
 * parent Node들에 대해 dfs 한번 하며 parent 수 카운트,
 * child Node들에 대해 dfs 한번 하며 child 수 카운트
 * 
 * parentCnt + 1이 최고 등수, N - childCnt 가 최저 등수
 */

import java.io.IOException;
import java.util.ArrayDeque;

public class Main {
	static int N, M, X, parentCnt, childCnt;
	static boolean[] visit;
	static Node[] graph;

	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		X = readInt();

		graph = new Node[N + 1];
		visit = new boolean[N + 1];
		for (int i = 1; i <= N; i++)
			graph[i] = new Node(i);

		int u, v;
		for (int i = 0; i < M; i++) {
			u = readInt();
			v = readInt();
			graph[u].child.add(v);
			graph[v].parent.add(u);
		}

		func();
	}

	static void func() {
		visit[X] = true;
		// 부모 노드 dfs
		parentDFS(X);
		// 자식 노드 dfs
		childDFS(X);
		System.out.printf("%d %d\n", parentCnt + 1, N - childCnt);
	}

	static void childDFS(int n) {
		for (int p : graph[n].child) {
			if (!visit[p]) {
				childCnt++;
				visit[p] = true;
				childDFS(p);
			}
		}
	}

	static void parentDFS(int n) {
		for (int p : graph[n].parent) {
			if (!visit[p]) {
				parentCnt++;
				visit[p] = true;
				parentDFS(p);
			}
		}
	}

	private static int readInt() throws IOException {
		int c;
		do {
			c = System.in.read();
		} while (c <= 32);
		int n = c & 15;
		c = System.in.read();
		while (c > 47) {
			n = (n << 3) + (n << 1) + (c & 15);
			c = System.in.read();
		}
		return n;
	}

	static class Node {
		int n;
		ArrayDeque<Integer> parent;
		ArrayDeque<Integer> child;

		public Node(int n) {
			this.n = n;
			parent = new ArrayDeque<>();
			child = new ArrayDeque<>();
		}

	}
}
