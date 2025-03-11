import java.io.*;
import java.util.*;

/*
 * 14760KB, 112ms
 * 
 * 화단의 가장자리에는 반드시 꽃을 배치할 수 없으므로 가장자리를 제외한 곳만 본다면 최악의 경우에도 8 * 8이다.
 * 여기서 꽃을 3개만 배치하면 되므로 완탐하여 모든 경우를 다 보면 된다.
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int N;
	static boolean[][] v;
	static int[][] arr;
	static int res = Integer.MAX_VALUE;
	
    public static void main(String[] args) throws Exception {
    	N = nextInt();
    	arr = new int[N][N];
    	v = new boolean[N][N];
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			arr[i][j] = nextInt();
    		}
    	}
    	
    	dfs(0, 0);
    	System.out.println(res);
    }
    
    static void dfs(int cnt, int sum) {
    	if (cnt == 3) {
    		res = Math.min(res, sum);
    		return;
    	}
    	
    	for (int i = 1; i < N - 1; i++) {
    		for (int j = 1; j < N - 1; j++) {
    			if (check(i, j)) {
    				int value = select(i, j);
    				dfs(cnt + 1, sum + value);
    				unSelect(i, j);
    			}
    		}
    	}
    }
    
    static boolean check(int i, int j) {
    	return !v[i][j] && !v[i - 1][j] && !v[i][j + 1] && !v[i + 1][j] && !v[i][j - 1]; 
    }
    
    static int select(int i, int j) {
    	int res = 0;
    	v[i][j] = v[i - 1][j] = v[i][j + 1] = v[i + 1][j] = v[i][j - 1] = true;
    	res = arr[i][j] + arr[i - 1][j] + arr[i][j + 1] + arr[i + 1][j] + arr[i][j - 1];
    	return res;
    }
    
    static void unSelect(int i, int j) {
    	v[i][j] = v[i - 1][j] = v[i][j + 1] = v[i + 1][j] = v[i][j - 1] = false;
    }
    
    static int nextInt() throws Exception {
    	st.nextToken();
    	return (int) st.nval;
    }
}