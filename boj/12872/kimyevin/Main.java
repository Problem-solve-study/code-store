import java.util.*;
import java.io.*;

/**
 * 	11556KB	60ms
 *  i개 노래로 된 플리에 j가지 노래를 넣는다면
 *  1) i-1개 플리에 j-1가지 노래 있을 때 새로운 노래 넣기
 *  2) i-1개 플리에 j가지 노래 있는데 (j-M)개 중 하나 고르기
 *  dp[0][0] = 1
 */
public class Main{
    static int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 노래 가짓 수
        int M = Integer.parseInt(st.nextToken()); // 같은 노래 사이 곡 개수
        int P = Integer.parseInt(st.nextToken()); // 플리 길이(곡 개수)

        long[][] dp = new long[P+1][N+1]; // 경우의 수 int값 초과
        dp[0][0] = 1; // 빈 플리

        for(int i = 1; i <= P; i++){
            for(int j = 1; j <= N; j++){
                long one = 0, two = 0;
                if(j > M){ // 2)번 경우
                    two = dp[i-1][j] * (j - M);
                }

                // 1)번 경우
                one = dp[i-1][j-1] * (N - j + 1);
                dp[i][j] = (one + two) % MOD;
            }
        }
        System.out.println(dp[P][N]);
    }
}