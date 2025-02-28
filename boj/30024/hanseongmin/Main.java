import java.io.*;
import java.util.*;

/*
 * 40012KB, 556ms
 * 
 * 수확할 수 있는 옥수수를 힙에 넣고 하나씩 빼기
 * 출력해야할게 옥수수의 좌표니까 옥수수의 좌표와 가치를 기준으로 클래스를 만들고 관리해야겠다.
 * -> String.format 사용 절대 금지. 메모리, 시간 차이 엄청 많이남
 */


public class Main {
	static StreamTokenizer st;
    static class Node implements Comparable<Node> {
    	int r;
    	int c;
    	int v;
    	
    	Node(int r, int c, int v) {
    		this.r = r;
    		this.c = c;
    		this.v = v;
    	}
    	
    	@Override
    	public int compareTo(Node o) {
    		return o.v - v;
    	}
    }
    
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StreamTokenizer(br);
        
    	int[] dr = {-1, 0, 1, 0};
    	int[] dc = {0, 1, 0, -1};
    	int n = nextInt();
    	int m = nextInt();
    	boolean[][] v = new boolean[n][m];
    	int[][] map = new int[n][m];
    	Queue<Node> h = new PriorityQueue<>();
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < m; j++) {
    			map[i][j] = nextInt();
    			if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
    				h.add(new Node(i, j, map[i][j]));
    				v[i][j] = true;
    			}
    		}
    	}
    	int k = nextInt();
    	
    	StringBuilder sb = new StringBuilder();
    	while (k --> 0) {
    		Node cur = h.remove();
    		sb.append(cur.r + 1).append(' ').append(cur.c + 1).append('\n');
    		
    		for (int i = 0; i < dr.length; i++) {
    			int nr = cur.r + dr[i];
    			int nc = cur.c + dc[i];
    			
    			if (boundaryCheck(nr, nc, n, m) && !v[nr][nc]) {
    				h.add(new Node(nr, nc, map[nr][nc]));
    				v[nr][nc] = true;
    			}
    		}
    	}
    	
    	System.out.println(sb.toString());
    }
    
    static boolean boundaryCheck(int r, int c, int n, int m) {
    	return (0 <= r && r < n) && (0 <= c && c < m);
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}