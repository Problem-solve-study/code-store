// 11556 KB, 68 ms
/*
 * 하천의 Strahler 순서 구하는 프로그램
 * 
 * 강의 근원인 노드의 순서는 1
 * 나머지는 들어오는 노드의 순서 중 가장 큰 값 i가 1개면 순서는 i, 2개 이상이면 i+1
 * 
 */
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;

public class BOJ9470_Strahler순서 {
	static int[] inDegree;
	static Node[] graph;

	public static void main(String[] args) throws IOException {
		int TC = readInt();
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= TC; tc++) {
			int K = readInt();
			int M = readInt();
			int P = readInt();
			inDegree = new int[M + 1];
			graph = new Node[M + 1];
			for (int i = 1; i <= M; i++)
				graph[i] = new Node(i);

			// 입력
			int a, b;
			for (int i = 0; i < P; i++) {
				a = readInt();
				b = readInt();
				graph[a].link.add(b);
				inDegree[b]++;
			}

			// 근원부터 시작하며 strahlerOrder 결정
			ArrayDeque<Integer> list = new ArrayDeque<>();
			for (int i = 1; i <= M; i++)
				if (inDegree[i] == 0)
					list.offer(i);

			int size = list.size();
			while (size-- > 0) {
				int cur = list.poll();
				graph[cur].strahlerOrder = 1;
				for (int next : graph[cur].link) {
					graph[next].strahlerOrderList.add(graph[cur].strahlerOrder);
					inDegree[next]--;
					if (inDegree[next] == 0)
						list.offer(next);
				}
			}

			// inDegree가 있던 노드들 strahlerOrder 결정
			// 들어오는 노드의 순서 중 가장 큰 값 i가 1개면 순서는 i, 2개 이상이면 i+1
			while (!list.isEmpty()) {
				int cur = list.poll();

				int max = graph[cur].strahlerOrderList.poll();
				if (graph[cur].strahlerOrderList.size() > 0 && graph[cur].strahlerOrderList.poll() == max)
					graph[cur].strahlerOrder = max + 1;
				else
					graph[cur].strahlerOrder = max;

				for (int next : graph[cur].link) {
					graph[next].strahlerOrderList.add(graph[cur].strahlerOrder);
					inDegree[next]--;
					if (inDegree[next] == 0)
						list.offer(next);
				}
			}

			sb.append(K).append(" ").append(graph[M].strahlerOrder).append('\n');
		}
		System.out.println(sb.toString());
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

	static public class Node {
		int n, strahlerOrder;
		ArrayDeque<Integer> link;
		PriorityQueue<Integer> strahlerOrderList;

		public Node(int n) {
			this.n = n;
			link = new ArrayDeque<>();
			strahlerOrderList = new PriorityQueue<>(Collections.reverseOrder());
		}
	}
}
