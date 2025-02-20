import java.io.*;
import java.util.*;

/*
36556KB, 136ms

BFS 기초 문제. 중복 체크만 해주면 적절히 통과하는듯
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    
    public static void main(String[] args) throws Exception {
    	//건물의 높이
    	int f = nextInt();
    	//출발 위치
    	int s = nextInt();
    	//목표 위치
    	int g = nextInt();
    	//위로 가는 길이
    	int u = nextInt();
    	//밑으로 가는 길이
    	int d = nextInt();
    	
    	String res = null;
    	boolean[] v = new boolean[f + 1];
    	Queue<int[]> q = new ArrayDeque<>();
    	q.add(new int[] {s, 0});
    	v[s] = true;
    	while (!q.isEmpty()) {
    		int[] cur = q.remove();
    		if (cur[0] == g) {
    			res = String.valueOf(cur[1]);
    			break;
    		}
    		
    		//다음으로 갈 층이 건물 층 수 범위 내에 있다면 진행
    		if (cur[0] + u <= f && !v[cur[0] + u]) {
    			q.add(new int[] {cur[0] + u, cur[1] + 1});
    			v[cur[0] + u] = true;
    		}
    		
    		if (cur[0] - d >= 1 && !v[cur[0] - d]) {
    			q.add(new int[] {cur[0] - d, cur[1] + 1});
    			v[cur[0] - d] = true;
    		}
    	}
    	
    	bw.write(res != null ? res : "use the stairs");
    	bw.flush();
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}