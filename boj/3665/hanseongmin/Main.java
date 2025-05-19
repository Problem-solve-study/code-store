import java.io.*;
import java.util.*;

/*
 * 263440KB, 1512ms
 * 
 * 맨 처음엔 연결리스트 문제인줄 알았음
 * 근데 IMPOSSIBLE에 대한 고려를 해야하니 단순히 연결리스트로 접근해서는
 * 정해를 구할 수 없을 것 같았고 각 노드에 대한 선후관계가 명확하니
 * 위상정렬을 이용하면 되겠다 싶었음.
 * 
 * 먼저 입력된 순위를 기반으로 인엣지와 아웃엣지를 구성하고
 * 이후 상대 순위가 바뀐 간선에 대해 인엣지와 아웃엣지를 반전시킨다.
 * 
 * 이후 위상 정렬을 수행하고 
 * 위상 정렬 후 인엣지들이 모두 0이라면 (즉, 모든 정점이 선택됐다면)
 * 정렬 순서를 출력하고
 * 그게 아니라면 불가능한 경우라는 소리이므로 IMPOSSIBLE을 출력한다.
 * 
 * Set 배열써서 멤초나려나 싶었는데 다행히 멤초나지는 않았다.
 * 놀랍게도 ?가 있다는 사실을 문제를 다 풀고나서 깨달음
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		int T = nextInt();
		StringBuilder sb = new StringBuilder();
		while (T --> 0) {
			int N = nextInt();
			int[] arr = new int[N];
			HashSet<Integer>[] inEdges = new HashSet[N + 1];
			HashSet<Integer>[] outEdges = new HashSet[N + 1];
			for (int i = 1; i <= N; i++) {
				arr[i - 1] = nextInt();
				inEdges[i] = new HashSet<>();
				outEdges[i] = new HashSet<>();
			}
			
			//인엣지와 아웃엣지 입력받기
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					inEdges[arr[j]].add(arr[i]);
					outEdges[arr[i]].add(arr[j]);
				}
			}
			
			int M = nextInt();
			for (int i = 0; i < M; i++) {
				int a = nextInt();
				int b = nextInt();
				//상대 순위가 바뀌었다면 반전시킴
				if (inEdges[a].contains(b)) {
					inEdges[a].remove(b);
					inEdges[b].add(a);
					outEdges[b].remove(a);
					outEdges[a].add(b);
				} else {
					inEdges[b].remove(a);
					inEdges[a].add(b);
					outEdges[a].remove(b);
					outEdges[b].add(a);
				}
			}
			
			
			//위상 정렬 수행
			ArrayDeque<Integer> s = new ArrayDeque<>();
			for (int i = 1; i <= N; i++) {
				if (inEdges[i].isEmpty()) {
					s.add(i);
				}
			}
			
			StringBuilder curSb = new StringBuilder();
			while (!s.isEmpty()) {
				int cur = s.removeLast();
				curSb.append(cur).append(' ');
				for (int n : outEdges[cur]) {
					inEdges[n].remove(cur);
					if (inEdges[n].isEmpty()) {
						s.add(n);
					}
				}
			}
			
			//인엣지가 0이 아니라면 선택되지 않은 정점이 있다는 소리.
			//불가능한 경우이므로 이를 마킹
			boolean isImpossible = false;
			for (int i = 1; i <= N; i++) {
				if (!inEdges[i].isEmpty()) {
					isImpossible = true;
					break;
				}
			}
			
			if (isImpossible) sb.append("IMPOSSIBLE").append('\n');
			else sb.append(curSb).append('\n');
		}
		System.out.print(sb);
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}