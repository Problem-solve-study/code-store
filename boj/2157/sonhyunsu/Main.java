import java.io.*;
import java.util.*;

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static List<List<int[]>> graph = new ArrayList<>();
    
	public static void main(String[] args) throws IOException {
	    int n = nextInt();
	    int m = nextInt();
	    int k = nextInt();
	    
	    int[][] dp = new int[n + 1][m + 1];
        graph.add(new ArrayList<>());
	    for (int i = 1; i <= n; i++) {
	        graph.add(new ArrayList<>());
	        for (int j = 1; j <= m; j++) {
	            dp[i][j] = (int) -1e9;
	        }
	    }
	    
	    for (int i = 0; i < k; i++) {
	        int a = nextInt();
	        int b = nextInt();
	        int c = nextInt();
	        if (a < b) {
	            graph.get(a).add(new int[]{b, c});    
	        }
	    }
	    
	    dp[1][1] = 0;
	    for (int i = 1; i < n; i++) {
            for (int[] next : graph.get(i)) {
                int ni = next[0];
                int c = next[1];
	            for (int j = 1; j < m; j++) {
	                dp[ni][j + 1] = Math.max(dp[ni][j + 1], dp[i][j] + c);
	            }
	        }
	    }
	    
	    int max = 0;
	    for (int i = 1; i <= m; i++) {
	        max = Math.max(max, dp[n][i]);
	    }
	    
	    System.out.print(max);
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}