import java.io.*;
import java.util.*;
/**
 * 11656KB	68ms
 * 매 초마다 두 개의 나무 중 하나에서 열매 떨어짐 (1, 2)
 * 최대 W번 움직인다고 할 때, 최대로 얻을 수 있는 자두 개수
 * dp[1,2(0,1)][움직인횟수] = 최대 자두 개수
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] plums = new int[T];
        int[][] dp = new int[2][W+1];
        for(int i = 0; i < T; i++){
            plums[i] = Integer.parseInt(br.readLine()) - 1;
        }
        //dp[1][0] = -1000; // 0에서 시작하므로, dp[1][0]은 도달 불가
        for(int i = 0; i < T; i++){
            if(plums[i] == 0){
                for(int c = W; c >= 1; c--){
                    dp[0][c] = Math.max(dp[0][c] + 1, dp[1][c-1] + 1);
                }
                dp[0][0] = dp[0][0] + 1;
            }
            else{
                for(int c = W; c >= 1; c--){
                    dp[1][c] = Math.max(dp[1][c] + 1, dp[0][c-1] +1);
                }
            }
        }
        System.out.println(Math.max(dp[0][W], dp[1][W]));
    }
}
