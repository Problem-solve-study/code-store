import java.io.*;
import java.util.*;
/*
 * 점화식을 찾는 과정이 매우 어려웠다.
 * dp[n][k] = dp[n-1][k]+dp[n][k-1].
 * 이를 찾고 나서부터는 dp 구현만 하면 되었다.
 * 모듈러연산을 for문 안에다가 안넣었던게 문제여서 이거 찾는데 많이 틀렸다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());;
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[][] dp = new int[N+1][K+1];
        for(int i=1; i<=N; i++){
            dp[i][1] = 1;
        }
        for(int i=1; i<=K; i++){
            dp[0][i] = 1;
        }

        for(int n=1; n<=N; n++){
            for(int k=2; k<=K; k++){
                dp[n][k] = (dp[n-1][k] + dp[n][k-1])% 1000000000;
            }
        }
        System.out.println(dp[N][K]);
    }
}
