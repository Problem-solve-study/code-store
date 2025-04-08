//제출번호: 92768139
//메모리:   40976 KB
//실행시간: 288 ms
import java.io.*;
import java.util.*;

//s를 루트로 하는 트리의 높이를 구한 다음
//실제로 전단지를 돌리는 탐색을 진행하면 풀 수 있음
public class Main {

	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static List<List<Integer>> graph;
	static int d;
	static int[] depths, leafDepths;

	public static void main(String[] args) throws IOException {
		int n = nextInt();
		int s = nextInt();
		d = nextInt();
		depths = new int[n + 1];
		leafDepths = new int[n + 1];

		graph = new ArrayList<>();
		for (int i = -1; i < n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 1; i < n; i++) {
			int a = nextInt();
			int b = nextInt();
			graph.get(a).add(b);
			graph.get(b).add(a);
		}

        //노드별 트리의 높이를 기록함
		dfs(s, 1);

        //실제로 오토바이를 움직여보고 움직인 거리를 출력함.
		System.out.print(run(s));
	}

	static int dfs(int node, int depth) {
		depths[node] = depth; //노드의 높이

		int leafDepth = depth;
		for (int nNode : graph.get(node)) {
            //아직 높이가 정해지지 않았다면
			if (depths[nNode] == 0) {
                //한 번 들어가보고 그곳에서 얻을 수 있는 최대 높이를 leafDepth에 업데이트
				leafDepth = Math.max(leafDepth, dfs(nNode, depth + 1));
			}
		}

		return leafDepths[node] = leafDepth; //현재 노드에서 가장 아래로 갈 수 있는 최대 높이
	}

	static int run(int node) {
		int dist = 0;

		for (int nNode : graph.get(node)) {
            //다음으로 가는 노드에 대해서
            //전단지를 뿌릴 수 없는 노드라면 직접 가야 함
			if (depths[nNode] > depths[node] && leafDepths[nNode] - depths[nNode] >= d) {
                //(다음 노드에서 오토바이가 움직인 거리) + 현재 노드에서 다음 노드로 왕복하는 거리(2)
				dist += run(nNode) + 2;
			}
		}

		return dist;
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}

/* DFS를 한 번만 사용해서 거리까지 구한 방법 + 입출력 최적화
//제출번호: 92769179
//메모리:   36196 KB
//실행시간: 196 ms
import java.io.*;
import java.util.*;

public class Main {

	static List<Integer>[] graph;
	static int d;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		int n = nextInt();
		int s = nextInt();
		d = nextInt();
		visited = new boolean[n + 1];

		graph = new List[n + 1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 1; i < n; i++) {
			int a = nextInt();
			int b = nextInt();
			graph[a].add(b);
			graph[b].add(a);
		}

		System.out.print(dfs(s, 1)[0]);
	}

	static int[] dfs(int node, int depth) {	
		visited[node] = true;

		int dist = 0;
		int leafDepth = depth;
		for (int nNode : graph[node]) {
			if (!visited[nNode]) {
                //item[0]: dist, item[1]: leafDepth
				int[] item = dfs(nNode, depth + 1);
                
                //다음 노드가 전단지를 뿌릴 수 없는 곳이면 직접 가야 함
				if (item[1] - depth > d) {
					dist += item[0] + 2; //item[0]에 다음 노드에서 오토바이가 움직인 거리가 저장되어 있음
				}

				leafDepth = leafDepth > item[1] ? leafDepth : item[1];
			}
		}

		return new int[] {dist, leafDepth};
	}

	static int nextInt() throws IOException {
        int n = System.in.read() & 15;
        int c = System.in.read();
        
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        
        return n;
	}
}
 */