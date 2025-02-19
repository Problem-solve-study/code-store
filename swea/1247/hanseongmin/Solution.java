import java.io.*;
import java.util.*;

/*
27308KB, 1471ms
 
N이 작은 TSP.
N이 작아서 순열로도 풀린다.
 */

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    static int n, res;
    static int[] company;
    static int[] house;
    static int[][] arr;
    static boolean[] visited;
    
    public static void main(String[] args) throws Exception {
    	int T = nextInt();
    	StringBuilder sb = new StringBuilder();
    	for (int t = 1; t <= T; t++) {
    		n = nextInt();
    		arr = new int[n][2];
    		visited = new boolean[n];
    		res = Integer.MAX_VALUE;
    		company = new int[2];
    		house = new int[2];
    		
    		company[0] = nextInt(); company[1] = nextInt();
    		house[0] = nextInt(); house[1] = nextInt();
    		for (int i = 0; i < n; i++) {
    			arr[i][0] = nextInt();
    			arr[i][1] = nextInt();
    		}
    		
    		dfs(0, new int[n][2]);
    		sb.append(String.format("#%d %d\n", t, res));
    	}
    	
    	bw.write(sb.toString());
    	bw.flush();
    }
    
    static void dfs(int cnt, int[][] sel) {
    	if (cnt == n) {
    		//모든 경우를 뽑았으면 거리를 계산
    		int curDist = 0;
    		//회사에서 출발
    		int r1 = company[0];
    		int c1 = company[1];
    		
    		//뽑은 고객들의 집을 차례로 방문
    		for (int i = 0; i < n; i++) {
    			int r2 = sel[i][0];
    			int c2 = sel[i][1];
    			curDist += getDist(r1, c1, r2, c2);
    			r1 = r2;
    			c1 = c2;
    		}
    		
    		//마지막은 집으로 도착
    		curDist += getDist(r1, c1, house[0], house[1]);
    		res = Math.min(res, curDist);
    		return;
    	}
    	
    	//순열 뽑기
    	for (int i = 0; i < n; i++) {
    		if (!visited[i]) {
    			visited[i] = true;
    			sel[cnt][0] = arr[i][0];
    			sel[cnt][1] = arr[i][1];
    			dfs(cnt + 1, sel);
    			visited[i] = false;
    		}
    	}
    }
    
    static int getDist(int x1, int y1, int x2, int y2) {
    	//맨해튼 거리 계산
    	return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}