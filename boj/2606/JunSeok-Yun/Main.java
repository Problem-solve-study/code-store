//14328KB, 104ms
import java.io.*;
import java.util.*;
public class Main {
	static int N,M;
	static int cnt;
	static int[][] map;
	static boolean[] visited;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		
		for (int i = 0; i < M ;i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			map[start][end] = 1;
			map[end][start] = 1;
		}

		cnt = 0;
		bfs(1);
		System.out.println(cnt);
	}

	public static void bfs(int root){
		Queue<Integer> q = new LinkedList<>();
		q.offer(root);
		visited[root] = true;

		while (!q.isEmpty()){
			int curV = q.poll();
			for (int i = 1; i <= N; i++){
				if (!visited[i] && map[curV][i] == 1){
					q.offer(i);
					visited[i] = true;
					cnt++;
				}
			}
		}
	}
}
