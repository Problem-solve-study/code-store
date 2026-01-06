// 11516KB 68ms

import java.util.*;
import java.io.*;

/**
 * DP
 * 
 * 1일차부터 아점저로 먹거나 n일차부터 저점아 순서로 먹는다 할 때,
 * 연달아 저녁약이거나 아침약이면 일차 순서를 바꿀 수 있음
 * 1. 1아 1점 1저 2아 2점 2저 = 1회
 * 2. 1아 1점 1저 2저 2점 2아 = 1저 <-> 2저 가능 -> 2회
 * 
 * dp[i][0] = i일차에 아침부터 먹은 경우 = i-1일차에 아침부터 먹은 경우 + i-1일차에 저녁부터 먹은 경우 * 2
 * dp[i][1] = i일차에 저녁부터 먹은 경우 = i-1일차에 아침부터 먹은 경우 * 2 + i-1일차에 저녁부터 먹은 경우
 */
class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[2][n];
        dp[0][0] = 1;
        dp[1][0] = 1;
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + dp[1][i - 1] * 2;
            dp[1][i] = dp[0][i - 1] * 2 + dp[1][i - 1]; 
        }

        System.out.println(dp[0][n - 1] + dp[1][n - 1]);
    }
}
