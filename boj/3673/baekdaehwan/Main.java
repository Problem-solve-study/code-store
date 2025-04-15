/**
 * 41840KB	276ms
 * 
 * [사고 흐름]
 * 처음에는 누적합 문제라고 생각했고, MOD*N 사이즈 배열 만들어서 풀려고 했습니다.
 * 1차원 배열로 할 수 있다는 힌트를 들어서 1차원으로 구현했습니다. 
 */

import java.io.*;
import java.util.*;

public class Main {
	static int N, MOD;
	static int[] DP;
	
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int tc = ni();
		while (tc-- > 0) {
			MOD = ni();
			N = ni();
			DP = new int[MOD];
			DP[0] = 1;
			
			int sum = 0;
			int res = 0;
			for (int i=0; i<N; ++i) {
				sum = (sum+ni()) % MOD;
				res += DP[sum%MOD];
				++DP[sum%MOD];
			}
			sb.append(res).append('\n');
		}
		System.out.print(sb);
    }
    
    static int ni() throws Exception {
    	st.nextToken();
    	return (int) st.nval;
    }
}
