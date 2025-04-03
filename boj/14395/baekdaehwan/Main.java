/**
 * 	11784KB	68ms
 * 
 * [사고흐름]
 * 그냥 BFS 문제인데, 어느정도의 최적화가 가능해보였습니다.
 * t의 범위가 1 이상인데 (-)는 어떤 수던지 0이 되므로 (-)는 생략합니다.
 * (/)는 어떤 수던지 1이 되므로, 2^n꼴의 수가 t로 주어질 때만 필요합니다.
 * 2^n꼴의 수는 (+), (*)해도 2^n꼴이 나오기 때문입니다.
 * 
 * visited같은 경우, 2^n꼴 수와, s*2^n꼴 수만 방문되므로 희소배열이 됩니다.
 * 이 때문에 해시맵을 사용했습니다.
 * 
 * [알고리즘 설명]
 * 위 생각을 바탕으로 구현만 하면 됩니다.
 * 단, BFS 중 현재 값이 t값을 초과하는 경우 버려야 하며,
 * (*) 연산 중 int범위를 벗어날 수 있으니, long으로 타입으로 선언해야합니다.
 */
import java.io.*;
import java.util.*;

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        Set<Long> set = new HashSet<>();
        Queue<Node> q = new ArrayDeque<>();
        
        long s = ni();
        long t = ni();
        String res = "0";
        
        if (s != t) {
            res = "-1";
            set.add(s*s);
            set.add(s+s);
            q.add(new Node(s*s, new StringBuilder("*")));
            q.add(new Node(s+s, new StringBuilder("+")));
            if (hasOnlyOne(t)) {
                set.add(1L);
                q.add(new Node(1, new StringBuilder("/")));
            }
            while (!q.isEmpty()) {
            	Node c = q.poll();
            	
            	if (c.v == t) {
            		res = c.sb.toString();
            		break;
            	}
            	
            	if (c.v<t) {
            		long v1 = c.v*c.v;
            		long v2 = c.v+c.v;
            		if (!set.contains(v1)) {        			
            			set.add(v1);
            			q.add(new Node(v1, new StringBuilder(c.sb).append('*')));
            		}
            		if (!set.contains(v2)) {        			
            			set.add(v2);
            			q.add(new Node(v2, new StringBuilder(c.sb).append('+')));
            		}
            	}
            }
        }

        System.out.println(res);
    }

    /**
     * 값이 1인 비트가 단 하나만 존재하는지 검사하는 함수입니다.
     * n이 그러한 수일때 n-1은 n에서 1이었던 비트의 다음 비트부터 1으로 채워집니다.
     * 따라서 n&(n-1)은 0이 되어야 합니다.
     * 
     * ex) 1000(8), 0111(7)
     */
    static boolean hasOnlyOne(long n) {
        return (n&(n-1)) == 0;
    }
    
    static class Node {
    	long v;
    	StringBuilder sb;
		public Node(long v, StringBuilder sb) {
			this.v = v;
			this.sb = sb;
		}
    }
    
    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}