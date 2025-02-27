import java.io.*;
import java.util.*;

/*
 * 219780KB, 428ms
 * 
 * [사고 흐름]
 * 그래프 상에서 최단거리니까 BFS인 것 같고
 * 최단 거리 중에서 가장 긴 시간이 걸리는 시간을 뽑아야하니까
 * 모든 육지를 대상으로 BFS를 돌린 후 나오는 가장 긴 거리를 뽑아서 출력하면 되겠다. 
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
    	int[] dr = {-1, 0, 1, 0};
    	int[] dc = {0, 1, 0, -1};
    	int r = nextInt();
    	int c = nextInt();
    	
    	//육지의 위치 추출
    	ArrayList<int[]> lPos = new ArrayList<>();
    	char[][] map = new char[r][c];
    	for (int i = 0; i < r; i++) {
    		String line = nextToken();
    		for (int j = 0; j < c; j++) {
    			map[i][j] = line.charAt(j);
    			if (map[i][j] == 'L') {
    				lPos.add(new int[] {i, j});
    			}
    		}
    	}
    	
    	int res = 0;
    	for (int[] pos : lPos) {
    		int sR = pos[0];
    		int sC = pos[1];
    		
    		//모든 육지를 대상으로 BFS 시작
    		boolean[][] v = new boolean[r][c];
    		Queue<int[]> q = new ArrayDeque<>();
    		q.add(new int[] {sR, sC, 0});
    		v[sR][sC] = true;
    		
    		while (!q.isEmpty()) {
    			int[] cur = q.remove();
    			int curR = cur[0]; int curC = cur[1]; int curCnt = cur[2];
    			//res가 최댓값이 될 수 있도록 큐에서 하나씩 꺼내서 거리 갱신
    			res = Math.max(res, curCnt);
    			
    			for (int i = 0; i < dr.length; i++) {
    				int nR = curR + dr[i];
    				int nC = curC + dc[i];
    				
    				if (boundaryCheck(nR, nC, r, c) && !v[nR][nC] && map[nR][nC] == 'L') {
    					q.add(new int[] {nR, nC, curCnt + 1});
    					v[nR][nC] = true;
    				}
    			}
    		}
    	}
    	
    	System.out.println(res);
    }
    
    static boolean boundaryCheck(int r, int c, int rl, int cl) {
    	return (0 <= r && r < rl) && (0 <= c && c < cl);
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
    
    static String nextToken() throws Exception {
    	st.nextToken();
    	return st.sval;
    }
}