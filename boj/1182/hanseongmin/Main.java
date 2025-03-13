import java.io.*;
import java.util.*;

/*
 * 11516KB, 80ms
 * 
 * 기초적인 부분집합 문제인듯. 부분집합을 구해주면서 sum이 S와 같아지는지를 체크하면 된다.
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int N, S;
	static int[] arr;
	static int res = 0;
	
    public static void main(String[] args) throws Exception {
    	N = nextInt();
    	S = nextInt();
    	arr = new int[N];
    	for (int i = 0; i < N; i++) {
    		arr[i] = nextInt();
    	}
    	
    	dfs(0, 0);
    	System.out.println(res);
    }
    
    static void dfs(int idx, int sum) {
    	//크기가 양수라고 했으므로 공집합은 제외(idx != 0)
    	if (idx != 0 && sum == S) res++;
    	//부분집합 탐색
    	for (int i = idx; i < N; i++) {
    		dfs(i + 1, sum + arr[i]);
    	}
    }
    
    static int nextInt() throws Exception {
    	st.nextToken();
    	return (int) st.nval;
    }
}