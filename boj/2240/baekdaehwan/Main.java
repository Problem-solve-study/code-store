/**
 * 	11620KB	64ms
 * [사고 흐름]
 * 1000*30 사이즈의 DP라고 생각했습니다.
 * 
 * [알고리즘 설명]
 * t를 현재 시간, w를 현재까지 움직인 횟수 라고 할 때, DP는 다음과 같이 정의됩니다.
 * DP[t][w] = t시간동안 w번 움직였을때 최대 자두 개수
 *          = Math.max(DP[t-1][w], DP[t-1][w-1]) + 현재 시간, 상태에서의 자두를 받을 수 있는가
 * 
 * 이는 t시간에 움직이지 않았거나, 움직인 경우에만 [t-1][?]에서 [t][w]가 되기때문입니다.
 * 그래서 이전 시간의 w, w-1만 확인하면 됩니다.
 */

import java.io.*;

public class Main {
	static int T, W;
	static boolean[] L;
	static int[][] DP;
	
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
    	T = ni();
    	W = ni();
    	L = new boolean[T];
    	DP = new int[T][W+1];
    	for (int t=0; t<T; ++t) L[t] = ni()==1;
    	DP[0][0] = L[0]? 1:0;
    	DP[0][1] = L[0]? 0:1;
    	for (int t=1; t<T; ++t) {
    		DP[t][0] = DP[t-1][0] + (L[t]? 1:0);
    		for (int w=1; w<=W; ++w) DP[t][w] = Math.max(DP[t-1][w], DP[t-1][w-1]) + (L[t]==((w&1)==0)? 1:0);
    	}
    	int res = 0;
    	for (int c: DP[T-1]) res = Math.max(res, c);
    	System.out.println(res);
    }
    
    static int ni() throws Exception {
    	st.nextToken();
    	return (int) st.nval;
    }
}