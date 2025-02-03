//제출번호: 89480269
//메모리:   14240 KB
//실행시간: 104 ms
import java.io.*;
import java.util.*;

public class Main {

    static int[][][] dp = new int[3][18][18];
    static int[][] map = new int[18][18];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0][1] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    continue;
                }

                //최종 가로
                if (map[i][j+1] == 0) {
                    dp[0][i][j+1] += dp[0][i][j] + dp[2][i][j];
                }

                //최종 세로
                if (map[i+1][j] == 0) {
                    dp[1][i+1][j] += dp[1][i][j] + dp[2][i][j];
                }

                //최종 대각선
                if ((map[i+1][j] | map[i][j+1] | map[i+1][j+1]) == 0) {
                    dp[2][i+1][j+1] += dp[0][i][j] + dp[1][i][j] + dp[2][i][j];
                }
            }
        }

        System.out.print(dp[2][n][n]);
    }
}