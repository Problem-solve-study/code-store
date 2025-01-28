import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        String[] input = br.readLine().split(" ");
        int[] values = new int[3];
        
        for (int i = 0; i < N; i++) {
            values[i] = Integer.parseInt(input[i]);
        }
        
        int inA = values[0];
        int inB = N > 1 ? values[1] : 0;
        int inC = N > 2 ? values[2] : 0;
        
        // 여기서부터 기존의 DP 로직을 계속합니다.
        int[][][] dp = new int[inA + 1][inB + 1][inC + 1];

        for (int[][] plane : dp)
            for (int[] row : plane)
				Arrays.fill(row, Integer.MAX_VALUE);

        dp[0][0][0] = 0;

        for (int a = 0; a <= inA; a++) {
            for (int b = 0; b <= inB; b++) {
                for (int c = 0; c <= inC; c++) {
                    if (a == 0 && b == 0 && c == 0) continue;
                    dp[a][b][c] = Math.min(dp[a][b][c], getDP(Math.max(0, a-9), Math.max(0, b-3), Math.max(0, c-1)) + 1);
                    dp[a][b][c] = Math.min(dp[a][b][c], getDP(Math.max(0, a-9), Math.max(0, b-1), Math.max(0, c-3)) + 1);
                    dp[a][b][c] = Math.min(dp[a][b][c], getDP(Math.max(0, a-3), Math.max(0, b-9), Math.max(0, c-1)) + 1);
                    dp[a][b][c] = Math.min(dp[a][b][c], getDP(Math.max(0, a-3), Math.max(0, b-1), Math.max(0, c-9)) + 1);
                    dp[a][b][c] = Math.min(dp[a][b][c], getDP(Math.max(0, a-1), Math.max(0, b-9), Math.max(0, c-3)) + 1);
                    dp[a][b][c] = Math.min(dp[a][b][c], getDP(Math.max(0, a-1), Math.max(0, b-3), Math.max(0, c-9)) + 1);
                }
            }
        }

        System.out.println(dp[inA][inB][inC] == INF ? -1 : dp[inA][inB][inC]);
    }

    public static int getDP(int a, int b, int c) {
        return dp[a][b][c];
    }
}
