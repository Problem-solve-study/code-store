// 11736KB	72ms
import java.io.*;
import java.util.*;
/*
 * Knapsack 알고리즘을 통한 dp 구현.
 * 냅색은 처음 해보는 거라 많이 헤멨음.
 * dp를 할 때 제일 고민이 많이 된 부분이 배열 선언을 어떤 기준으로 하는가인데 처음에는 N 값을 받아서 1차원으로 하려
 * 했으나 구현에 실패함. 정해는 hp 크기를 기준으로 배열로 받아서 dp를 구현하는 것. 이를 2차원 dp를 통해 구현함.
 */
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] hp = new int[N+1];
        int[] happy = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            hp[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            happy[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N+1][101];
        // 최대 hp가 0일 때의 경우의 수들
        for(int i=0; i<101; i++) dp[0][i] = 0;
        for(int i=1; i<=N; i++){
            // 아무한테도 인사 안한 경우의 수들
            dp[i][0] = 0;
            for(int j=1; j<=100; j++){
                //i 사람에게 인사 시 소모되는 hp보다 최대 hp가 클 때 이 사람에게 인사했을 때 기쁨 값과 이전 사람들에게만 했을 때의 값을 비교.
                if(hp[i]<j){
                    dp[i][j] = Math.max(dp[i-1][j-hp[i]]+happy[i], dp[i-1][j]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        System.out.println(dp[N][100]);
    }
}
