/* 
 * 같은 소집단에 속한 사람끼리 모두 좋아하고,다른 소집단에 속한 사람들끼리 모두 싫어하면 집단이 안정되어 있다.
 * 0은 서로 좋아하고, 1이면 싫어하는 관계
 * 
 * 2 <= n <= 100
 * 
 * 각 소집단은 적어도 2명 이상으로 구성돼야 함.
 * 집단 내에서는 전부 좋아하는 관계여야 함.
 * 
 * 안정되지 않은 집단이면, 0을 출력
 * 안정된 집단이면, 소집단의 수를 출력하고, 다음 줄부터 각 소집단의 번호를 출력.
 * 번호는 오름차순으로.
 * 
 * Union-Find로 소집단을 파악하고, 각 집단의 노드들이 서로 전부 좋아하는 관계인지 판단.
 * 간선의 개수로 파악 가능. O(N)
 * 
 * Union-Find 는 시간복잡도가 얼마지? n이 100이니까 되겠지 뭐.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.LinkedList;

public class Main {
	static int N, componentCnt;
	static int[] parent, edgeCnt, groupNum;
	static int[][] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(br);
		N = nextInt(st);
		componentCnt = N;
		parent = new int[N + 1];
		edgeCnt = new int[N + 1];
		groupNum = new int[N + 1];
		for (int i = 1; i <= N; i++)
			parent[i] = i;

		adj = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				adj[i][j] = nextInt(st);
				if (adj[i][j] == 0 && i != j) {
					union(i, j);
					edgeCnt[i]++;
				}
			}
		}

		LinkedList<Integer>[] groups = new LinkedList[componentCnt + 1];
		for (int k = 1; k <= componentCnt; k++)
			groups[k] = new LinkedList<Integer>();

		int cnt = 1;
		for (int i = 1; i <= N; i++) {// 소그룹 만들기
			if (groupNum[parent[i]] == 0) { // 그룹넘버 안매겨졌으면
				groupNum[parent[i]] = cnt++;
			}
			groups[groupNum[parent[i]]].add(i);
			groupNum[i] = groupNum[parent[i]];
		}

		boolean isValid = true;
		loop: for (int k = 1; k <= componentCnt; k++) {
			if (groups[k].size() == 1) {
				isValid = false;
				break loop;
			}
			for (int cur : groups[k]) {
				if (edgeCnt[cur] < groups[k].size() - 1) {
					isValid = false;
					break loop;
				}
			}
		}

		if (isValid) {
			System.out.println(componentCnt);
			for (int k = 1; k <= componentCnt; k++) {
				for (int t : groups[k])
					System.out.printf("%d ", t);
				System.out.println();
			}
		} else {
			System.out.println(0);
		}
	}

	static void union(int u, int v) {
		int rootU = find(u);
		int rootV = find(v);
		if (rootU != rootV) {
			if (rootU < rootV) // 번호가 작은 걸로 합침.
				parent[rootV] = rootU;
			else
				parent[rootU] = rootV;
			componentCnt--;
		}
	}

	static int find(int x) {
		int root = x;
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		while (root != parent[root]) {
			stack.push(root);
			root = parent[root];
		}

		while (!stack.isEmpty()) // 경로 압축
			parent[stack.pop()] = root;
		return root;
	}

	private static int nextInt(StreamTokenizer st) throws IOException {
		st.nextToken();
		return (int) st.nval;
	}

}