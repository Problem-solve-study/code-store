// 11612 KB, 68 ms
/*
 * 세 종류의 파스타를 먹는 계획을 세운다.

 * 연속 3일 이상은 안된다.
 * 3 <= N <= 100, 1 <= K <= N
 * K 일에는 먹을 파스타를 미리 정해둠.
 * 
 * dp[i][j] i일에 j종류를 먹은 계획 수
 * dp[i][j] = dp[i-1][1]+dp[i-1][2]+dp[i-1][3] - dp[i-2][j] // 2일전부터 동일한 파스타 먹은 경우를 제외
 * 
 * 근데 i == K 일때는 미리 정해졌음. 동일하고, dp[k][정해진 종류] 제외하고는 0이다.
 * 
 * 1일차에 1 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static final int MOD = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // dp[날짜][파스타종류][연속횟수]
        int[][][] dp = new int[N + 1][4][3];
        int[] fix = new int[N + 1];

        // 고정된 파스타 입력 받기
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int pasta = Integer.parseInt(st.nextToken());
            fix[day] = pasta;
        }

        // 첫째 날 초기화
        if (fix[1] != 0) {
            dp[1][fix[1]][1] = 1;
        } else {
            dp[1][1][1] = dp[1][2][1] = dp[1][3][1] = 1;
        }

        // DP 계산
        for (int i = 2; i <= N; i++) {
            if (fix[i] != 0) {
                // i일에 먹을 파스타가 고정된 경우
                int p = fix[i];
                
                // 연속 1일차로 먹는 경우 (이전 날 다른 파스타)
                for (int prev = 1; prev <= 3; prev++) {
                    if (prev != p) {
                        // 이전 날의 연속 1일차, 2일차 모두 가능
                        dp[i][p][1] = (dp[i][p][1] + dp[i-1][prev][1] + dp[i-1][prev][2]) % MOD;
                    }
                }
                
                // 연속 2일차로 먹는 경우
                dp[i][p][2] = dp[i-1][p][1];
            } else {
                // 파스타를 자유롭게 선택할 수 있는 경우
                for (int j = 1; j <= 3; j++) {
                    // 연속 1일차로 먹는 경우
                    for (int prev = 1; prev <= 3; prev++) {
                        if (prev != j) {
                            dp[i][j][1] = (dp[i][j][1] + dp[i-1][prev][1] + dp[i-1][prev][2]) % MOD;
                        }
                    }
                    
                    // 연속 2일차로 먹는 경우
                    dp[i][j][2] = dp[i-1][j][1];
                }
            }
        }

        // 결과 계산: N일째 각 파스타의 연속 1일차, 2일차 경우의 합
        int result = 0;
        for (int j = 1; j <= 3; j++) {
            result = (result + dp[N][j][1] + dp[N][j][2]) % MOD;
        }
        
        System.out.println(result);
    }
}