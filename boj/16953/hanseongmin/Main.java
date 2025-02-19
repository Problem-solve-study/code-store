import java.io.*;
import java.util.*;

/*
23540KB, 160ms

딱 보자마자 BFS라고 생각했다. B -> A로 간다고 생각하면 경우의 수를 하나로 줄일 수 있어서 그리디로도 풀리는 듯
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    
    public static void main(String[] args) throws Exception {
        long a = nextInt();
        long b = nextInt();
        
        long res = -1;
        HashSet<Long> visited = new HashSet<>();
        Queue<long[]> q = new ArrayDeque<>();
        q.add(new long[] {a, 1});
        visited.add(a);
        while (!q.isEmpty()) {
        	long[] cur = q.remove();
        	if (cur[0] == b) {
        		res = cur[1];
        		break;
        	}
        	
        	long n1 = cur[0] * 2;
        	long n2 = cur[0] * 10 + 1;
        	if (!visited.contains(n1) && n1 <= b) {
        		q.add(new long[] {n1, cur[1] + 1});
        		visited.add(n1);
        	}
        	
        	if (!visited.contains(n2) && n2 <= b) {
        		q.add(new long[] {n2, cur[1] + 1});
        		visited.add(n2);
        	}
        }
        
        bw.write(String.valueOf(res));
        bw.flush();
    }
    
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}