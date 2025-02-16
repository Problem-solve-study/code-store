
/*
 * 1 <= N <= 1,000,000 <= M <= N*(N-1)/2
 * 그래프의 연결 요소의 개수. Union-Find 로 그룹 판단
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	static int N, M, componentCnt;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputNM = br.readLine().split(" ");
		N = Integer.parseInt(inputNM[0]);
		M = Integer.parseInt(inputNM[1]);
		parent = new int[N + 1];
		componentCnt = N; // 처음 컴포넌트의 개수는 N개
		for (int i = 1; i <= N; i++)
			parent[i] = i;

		for (int i = 0; i < M; i++) {
			String[] inputUV = br.readLine().split(" ");
			union(Integer.parseInt(inputUV[0]), Integer.parseInt(inputUV[1]));
		}

		System.out.println(componentCnt);
	}

	public static void union(int u, int v) {
		int rootU = find(u);
		int rootV = find(v);

		if (rootU != rootV) {
			parent[rootV] = rootU;
			componentCnt--; // 두 컴포넌트가 합쳐질 때 카운트 감소
		}
	}

	public static int find(int x) {
		int root = x;
		Deque<Integer> stack = new ArrayDeque<>();

		while (root != parent[root]) {
			stack.push(root);
			root = parent[root];
		}

		while (!stack.isEmpty()) // 경로 압축
			parent[stack.pop()] = root;

		return root;
	}
}