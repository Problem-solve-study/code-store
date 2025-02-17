// 11596 KB, 64 ms
/*
 * 원산지는 부모가 없으면서 자식이 있는 노드들
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int N;
	static boolean[] isCaught, isSupplied, visit, isSupplier;
	static Node[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		nodes = new Node[N];
		isCaught = new boolean[N];
		for (int i = 0; i < N; i++)
			nodes[i] = new Node(i);
		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int u = input[0].charAt(0) - 'A';
			int v = input[1].charAt(0) - 'A';
			nodes[u].child.add(v);
			nodes[v].parent.add(u);
		} // 입력받아 인접 리스트 생성
		input = br.readLine().split(" ");
		int K = Integer.parseInt(input[0]);
		for (int i = 1; i <= K; i++)
			isCaught[input[i].charAt(0) - 'A'] = true; // 'A' 는 0이다.

		isSupplied = new boolean[N + 1]; // 원산지와 연결 되어있는지 여부
		isSupplier = new boolean[N + 1]; // 원산지 인지 여부
		visit = new boolean[N + 1];
		for (int i = 0; i < N; i++) {
			if (nodes[i].parent.size() == 0 && nodes[i].child.size() > 0 && !isCaught[i]) { // 원산지면
				isSupplier[i] = true;
				bfs(i);
			}
		}
		int cnt = 0;
		for (int i = 1; i < N; i++)
			if (visit[i] && !isCaught[i] && !isSupplier[i])
				cnt++;
		System.out.println(cnt); // 원산지 제외 공급받는 공급책의 수
	}

	static void bfs(int n) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(n);
		visit[n] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int t : nodes[cur].child) { // 연결된 노드들
				if (visit[t] || isCaught[t]) // 이미 방문했거나, 잡혔으면 패스
					continue;
				q.offer(t);
				visit[t] = true;
			}
		}
	}

	static class Node {
		int n;

		LinkedList<Integer> parent;
		LinkedList<Integer> child;

		public Node(int n) {
			this.n = n;
			this.parent = new LinkedList<>();
			this.child = new LinkedList<>();
		}
	}
}
