//	16788KB	132ms
import java.io.*;
import java.util.*;


public class Main {
	static int n,m;
	static ArrayList<Integer>[] map;
	static class Node {
		int num, cnt;
		Node(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
	static boolean[] visited;
	static int count;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		map = new ArrayList[n+1];
		for (int i=1; i<=n; i++) {
			map[i] = new ArrayList<>();
		}
		for (int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a].add(b);
			map[b].add(a);
		}
		visited = new boolean[n+1];
		bfs();
		System.out.print(count-1); // depth 2 이내의 노드 중 자기자신 제외;
	}
	
	static void bfs() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(1, 0));
		visited[1] = true;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			if (node.cnt > 2) {
				break;
			}
			count++;
			
			for (int next : map[node.num]) {
				if (!visited[next]) {
					visited[next] = true;
					queue.add(new Node(next, node.cnt + 1));
				}
			}
		}
	}
}