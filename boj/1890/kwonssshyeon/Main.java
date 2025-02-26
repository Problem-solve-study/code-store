// 11792KB	72ms
import java.io.*;
import java.util.*;

public class Main {
    static int n, map[][];
    static long[][] dp;
    /**
     * 메모이제이션하면서 백트래킹
     * 메모이제이션안하면 시간복잡도가 격자마다 (100*100) 갈 수 있는 방향 2개 => (100*100)^2
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new long[n][n];

        System.out.print(dfs(0,0));
    }

    static long dfs(int y, int x) {
        if (y == n-1 && x == n-1) return 1; // 종점 도착
        
        if (dp[y][x] != 0) return dp[y][x]; // 이미 왔던 곳이면 저장된 경우의 수 그대로 반환

        int jumpX = x + map[y][x];
        int jumpY = y + map[y][x];

        if (jumpX > x && jumpX < n) { // 오른쪽으로 가는 경우
            dp[y][x] += dfs(y, jumpX);
        }

        if (jumpY > y && jumpY < n) { // 왼쪽으로 가는 경우
            dp[y][x] += dfs(jumpY, x);
        }

        return dp[y][x];
    }
}