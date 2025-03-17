import java.io.*;
import java.util.*;

/*
 * 14460KB, 116ms
 * 
 * 그래프 탐색이 자신 기준으로 이루어지는게 직관적이라고 생각해서 BFS로 했다.
 * 추후에 생각해보니 DFS로 접근하면 좀 복잡해지는듯.
 * 친구의 친구까지만 카운팅해야하기 때문에 BFS를 수행할 때 depth를 같이 넘겨주는 식으로 구현
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		int N = nextInt();
		int M = nextInt();
		ArrayList<Integer>[] adj = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			int a = nextInt();
			int b = nextInt();
			adj[a].add(b);
			adj[b].add(a);
		}
		
		int cnt = -1;
		boolean[] v = new boolean[N + 1];
		Queue<Integer> q = new ArrayDeque<>();
		q.add(1);   //정점의 번호와 함께
		q.add(0);   //깊이도 같이 add
		v[1] = true;
		while (!q.isEmpty()) {
			int cur = q.remove();
			int depth = q.remove();
			if (depth > 2) continue;    //친구의 친구 이후로는 볼 필요가 없다.
			cnt++;
			
			for (int next : adj[cur]) {
				if (!v[next]) {
					v[next] = true;
					q.add(next);
					q.add(depth + 1);
				}
			}
		}
		
		System.out.print(cnt);
	}

	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}