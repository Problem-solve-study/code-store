import java.io.*;
import java.util.*;

public class Main {
    /**
     * 1억으로 나누는 거랑 경우의 수를 구하는 것을 보고 DP 문제라고 짐작함.
     * 테케가 1개 밖에 없어서 완탐 코드를 짜서 다른 케이스에서 수열이 어떻게 생겼는지를 확인함.
     * k=0 : 1 0 0 0 0
     * k=1 : 1 1 1 1 1
     * k=2 : 1 2 3 4 5
     * k=3 : 1 3 6 10 15
     * 
     * 이런식으로 출력되는것을 보고 dp[i][j] = dp[i-1][j] + dp[i][j-1] 인 걸 확인했는데
     * 어쩌피 2차원 dp 배열의 바로 직전 행만 확인하기 때문에 1차원으로 품.
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i=0; i<k; i++) {
            for (int j=1; j<=n; j++) {
                dp[j] = (dp[j] + dp[j-1]) % 1_000_000_000;
            }
        }
        System.out.print(dp[n]);
    }
}