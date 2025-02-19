//310268KB 660ms
import java.io.*;
import java.util.*;
/*
* 처음엔 DFS로 접근했으나, N의 크기가 매우 컸기에 시간 초과로 실패. 이후 DP로 접근해서 Top-down 방식으로 풀이.
* DP 연습을 할 수 있었던 문제. 나중에 다시 참고해야 할듯.
*/

public class Main {
	static int[] T, P;
    static int N;
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = new int[N];
        P = new int[N];
		StringTokenizer st;
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());    
            T[n] = Integer.parseInt(st.nextToken());
		    P[n] = Integer.parseInt(st.nextToken());
        }
		
        long[] dp = new long[N+1];
        for(int i = N-1; i>=0; i--){
            if(i+T[i]>N){
                dp[i] = dp[i+1];
            }else{
                dp[i] = Math.max(P[i] + dp[i+T[i]], dp[i+1]);
            }
        }
		
		System.out.println(dp[0]);
	}  
}
