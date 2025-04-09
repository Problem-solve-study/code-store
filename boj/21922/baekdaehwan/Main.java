/**
 * 34704KB	572ms
 * 
 * [사고흐름]
 * 그래프 탐색 문제같다고 생각했습니다. 
 * 방향 변화의 특징이 보여서 다음과 같이 생각했습니다.
 * 
 * (r, c)기준으로 설명하겠습니다.
 * 기존 방향이 (1, 0)고 현재 위치의 물건이 3(/)인 경우, 방향은 (0, -1)로 바뀌어야 합니다.
 * 
 * . . | . .
 * . . v . .
 * <-- / . . 
 * . . . . .
 * 
 * 아래는 기존방향 -> 물건3(/)에 의해 바뀐 방향을 나타냅니다.
 * (1, 0)   ->  (0, -1)
 * (0, 1)   ->  (-1, 0)
 * (-1, 0)  ->  (0, 1)
 * (0, -1)  ->  (1, 0)
 * 
 * 즉, 기존 방향을 (dr, dc)라고 할 때, 물건3(/)에 의해 변한 방향은 (-dc, -dr)라고 할 수 있습니다.
 * 같은 개념으로 각 물건에 따른 변화는 다음과 같습니다.
 * 
 * 1: (dr, -dc)
 * 2: (-dr, dc)
 * 3: (-dc, -dr)
 * 4: (dc, dr)
 * 
 * 이를 응용해서 탐색하면 됩니다.
 */

 import java.io.*;
import java.util.*;

public class Main {
    static int R, C, res;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Pos> q;
    static int[] biasRow = {1,-1,-1,1};
    static int[] biasCol = {-1,1,-1,1};

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        R = ni();
        C = ni();
        map = new int[R][C];
        visited = new boolean[R][C];
        q = new ArrayDeque<>();

        for (int r=0; r<R; ++r) {
        	for (int c=0; c<C; ++c) {
        		map[r][c] = ni()-1;
        		if (map[r][c] == 8) {
                	visited[r][c] = true;
                	++res;
                	q.add(new Pos(r, c, 1, 0));
                	q.add(new Pos(r, c, 0, 1));
                	q.add(new Pos(r, c, -1, 0));
                	q.add(new Pos(r, c, 0, -1));
        		}
        	}
        }
        
        while (!q.isEmpty()) {
        	Pos c = q.poll();
        	if (!isValid(c.r, c.c)) continue;
        	
        	if (!visited[c.r][c.c]) ++res;
        	visited[c.r][c.c] = true;
        	
        	c.r += c.dr;
        	c.c += c.dc;
        	if (!isValid(c.r, c.c)) continue;
        	
        	int curBias = map[c.r][c.c];
        	if (map[c.r][c.c] == -1) {
        		q.add(c);
        	} else if (map[c.r][c.c] < 2) {
        		c.dr *= biasRow[curBias];
        		c.dc *= biasCol[curBias];
        		q.add(c);
        	} else if (map[c.r][c.c] < 4) {
        		int tmp = c.dr; c.dr=c.dc; c.dc=tmp;
        		c.dr *= biasRow[curBias];
        		c.dc *= biasCol[curBias];
        		q.add(c);
        	}
        }
        System.out.println(res);
    }
    
    static class Pos {
    	int r, c, dr, dc;
		public Pos(int r, int c, int dr, int dc) {
			this.r = r;
			this.c = c;
			this.dr = dr;
			this.dc = dc;
		}
    }

    static boolean isValid(int r, int c) {
    	return 0<=r&&r<R && 0<=c&&c<C;
    }
    
    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}