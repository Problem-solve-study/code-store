import java.io.*;
import java.util.*;

public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static boolean[] visited;
    
	public static void main(String[] args) throws IOException {
	    int n = nextInt();
	    int m = nextInt();
	    int x = nextInt();
	    visited = new boolean[n + 1];
	    
        List<List<Integer>> greater = new ArrayList<>(), less = new ArrayList<>();
	    greater.add(new ArrayList<>());
	    less.add(new ArrayList<>());
	    for (int i = 0; i < n; i++) {
	        greater.add(new ArrayList<>());
	        less.add(new ArrayList<>());
	    }
	    
	    for (int i = 0; i < m; i++) {
	        int a = nextInt();
	        int b = nextInt();
	        
	        greater.get(b).add(a);
	        less.get(a).add(b);
	    }
	    
	    System.out.printf("%d %d", dfs(greater, x), n - dfs(less, x) + 1);
	}
	
	static int dfs(List<List<Integer>> graph, int node) {
	    int res = 1;
	    visited[node] = true;
	    
	    for (int nNode : graph.get(node)) {
	        if (!visited[nNode]) {
	            res += dfs(graph, nNode);
	        }
	    }
	    
	    return res;
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}