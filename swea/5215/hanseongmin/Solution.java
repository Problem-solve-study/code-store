import java.io.*;
import java.util.*;

/*
 * 27648KB, 135ms
 */

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    static int n, l, res;
    static int[][] arr;
    
    public static void main(String[] args) throws Exception {
    	int T = nextInt();
    	StringBuilder sb = new StringBuilder();
    	for (int t = 1; t <= T; t++) {
    		res = 0;
    		n = nextInt();
    		l = nextInt();
    		arr = new int[n][2];
    		for (int i = 0; i < n; i++) {
    			arr[i][0] = nextInt();
    			arr[i][1] = nextInt();
    		}
    		
    		dfs(0, 0, 0);
    		sb.append(String.format("#%d %d\n", t, res));
    	}
    	
    	bw.write(sb.toString());
    	bw.flush();
    }
    
    static void dfs(int idx, int s, int c) {
    	//제한 칼로리 이상이면 탐색할 필요 없음
    	if (c > l) {
    		return;
    	}
    	
    	//맛 점수 갱신
    	res = Math.max(res, s);
    	for (int i = idx; i < n; i++) {
    		dfs(i + 1, s + arr[i][0], c + arr[i][1]);
    	}
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}