import java.io.*;

public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int n;
    static int[] d;
    static boolean[] visited;
    static int max;
    
	public static void main(String[] args) throws IOException {
	    n = nextInt();
	    
	    d = new int[n];
	    for (int i = 0; i < n; i++) {
	        d[i] = nextInt();
	    }
	    
	    visited = new boolean[n];
	    for (int i = 0; i < n; i++) {
	        visited[i] = true;
	        perm(1, i, 0);
	        visited[i] = false;
	    }
	    
	    System.out.print(max);
	}
	
	static void perm(int cnt, int prev, int sum) {
	    if (cnt == n) {
	        max = Math.max(max, sum);
	        return;
	    }
	    
	    for (int i = 0; i < n; i++) {
	        if (!visited[i]) {
	            visited[i] = true;
	            perm(cnt + 1, i, sum + Math.abs(d[prev] - d[i]));
	            visited[i] = false;
	        }
	    }
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}