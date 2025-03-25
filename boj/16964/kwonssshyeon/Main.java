// 72360KB	436ms
import java.io.*;
import java.util.*;

public class Main {
	static ArrayList<Integer>[] map;
	static boolean[] visited;
	static int[] target;
	static int answer = 1;
	static int n, idx;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		
		map = new ArrayList[n+1];
        for (int i=1; i<=n; i++) {
            map[i] = new ArrayList<>();
        }
		
		visited = new boolean[n + 1];
		target = new int[n];
		idx = 1;
		
		for(int i = 0 ; i < n - 1 ; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
            map[from].add(to);
            map[to].add(from);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ; ++i) target[i] = Integer.parseInt(st.nextToken());
		
		if(target[0] != 1) {
			System.out.println(0);
			return;
		}
		
		dfs(1);
		System.out.print(answer);
		
	}

	private static void dfs(int now) {
		if(visited[now]) return;
		visited[now] = true;
		
		HashSet<Integer> set = new HashSet<>();
		for(int next : map[now]) {
			if(visited[next]) continue;
			set.add(next);
		}
		
		if(set.size() == 0) return;
		
		if(set.contains(target[idx])) {
			dfs(target[idx++]);
		} else {
			answer = 0;
		}
	}
}