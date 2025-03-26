import java.io.*;
import java.util.*;

public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int n = nextInt();
	    int[] d = new int[n + 1];
	    boolean[] visited = new boolean[n + 1];
	    
	    for (int i = 1; i <= n; i++) {
	        d[i] = nextInt();
	    }
	    
	    long lcm = 1;
	    for (int i = 1; i <= n; i++) {
	        if (!visited[i]) {
	            int cnt = 1;
	            visited[i] = true;
	            
	            int point = d[i];
	            while (point != i) {
	                visited[point] = true;
	                point = d[point];
	                cnt++;
	            }
	            
	            long g = gcd(lcm, cnt);
	            lcm = lcm / g * cnt;
	        }
	    }
	    
	    System.out.print(lcm);
	}
	
	static long gcd(long a, long b) {
	    long tmp;
	    
	    while (b != 0) {
	        tmp = a % b;
	        a = b;
	        b = tmp;
	    }
	    
	    return a;
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}