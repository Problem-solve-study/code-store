import java.io.*;
import java.util.*;

public class Main {

	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws IOException {
		int n = nextInt();
		int m = nextInt();
		
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = -1 ; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i = 0; i < m; i++) {
			int a = nextInt();
			int b = nextInt();
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[n + 1];
		
		q.add(1);
		visited[1] = true;
		int depth = 0;
		int res = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			res += size;
			
			if (depth == 2) {
				break;
			}
			
			while (size-- > 0) {
				int user = q.poll();
				
				for (int friend : graph.get(user)) {
					if (!visited[friend]) {
						visited[friend] = true;
						q.add(friend);
					}
				}
			}
			
			depth++;
		}
		
		System.out.print(res - 1);
	}
	
	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}

}
