import java.io.*;
import java.util.*;

/*
 * 14704KB, 124ms
 * 
 * BFS 시뮬레이션 문제
 * 문제 조건대로 구현하면 됨. 구현 실수해서 1틀
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int R, C;
	static class Data implements Comparable<Data> {
		int r;
		int c;
		int d;
		int cnt;
		
		public Data(int r, int c, int d, int cnt) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Data o) {
			int c1 = Integer.compare(r, o.r);
			if (c1 != 0) return c1;
			int c2 = Integer.compare(c, o.c);
			if (c2 != 0) return c2;
			return Integer.compare(d, o.d);
		}
	}
	
    public static void main(String[] args) throws Exception {
    	R = nextInt();
    	C = nextInt();
    	boolean[][] map = new boolean[R + 1][C + 1];
    	for (int i = 1; i <= R; i++) {
    		for (int j = 1; j <= C; j++) {
    			map[i][j] = nextInt() == 1;
    		}
    	}
    	
    	Data s = new Data(nextInt(), nextInt(), nextInt(), 0);
    	Data d = new Data(nextInt(), nextInt(), nextInt(), 0);
    	TreeSet<Data> v = new TreeSet<>();
    	Queue<Data> q = new ArrayDeque<>();
    	q.add(s);
    	v.add(s);
    	int res = 0;
    	while (!q.isEmpty()) {
    		Data cur = q.remove();
    		if (cur.compareTo(d) == 0) {
    			res = cur.cnt;
    			break;
    		}
    		
    		//명령 1
    		for (int i = 1; i <= 3; i++) {
    			int nr = cur.r;
    			int nc = cur.c;
    			if (cur.d == 1) {
    				nc += i;
    			} else if (cur.d == 2) {
    				nc -= i;
    			} else if (cur.d == 3) {
    				nr += i;
    			} else {
    				nr -= i;
    			}
    			
    			Data nextData = new Data(nr, nc, cur.d, cur.cnt + 1);
    			if (!bc(nr, nc)) break;
    			if (map[nr][nc]) break;
    			//1~3까지 가는 중간 결과가 v에 있더라도 일단 더 탐색해보긴 해야함 
    			if (v.contains(nextData)) continue;	

    			v.add(nextData);
    			q.add(nextData);
    		}
    		
    		//명령2 (왼쪽)
    		Data left = new Data(cur.r, cur.c, getLeftD(cur.d), cur.cnt + 1);
    		if (!v.contains(left)) {
    			v.add(left);
    			q.add(left);
    		}
    		
    		//명령2 (오른쪽)
    		Data right = new Data(cur.r, cur.c, getRightD(cur.d), cur.cnt + 1);
    		if (!v.contains(right)) {
    			v.add(right);
    			q.add(right);
    		}
    	}
    	System.out.print(res);
    }
    
    static int getLeftD(int cur) {
    	if (cur == 1) {
    		return 4;
    	} else if (cur == 2) {
    		return 3;
    	} else if (cur == 3) {
    		return 1;
    	} else {
    		return 2;
    	}
    }
    
    static int getRightD(int cur) {
    	if (cur == 1) {
    		return 3;
    	} else if (cur == 2) {
    		return 4;
    	} else if (cur == 3) {
    		return 2;
    	} else {
    		return 1;
    	}
    }
    
    static boolean bc(int r, int c) {
    	return (1 <= r && r <= R) && (1 <= c && c <= C);
    }
    
    static int nextInt() throws Exception {
    	st.nextToken();
    	return (int) st.nval;
    }
}