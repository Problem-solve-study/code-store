// 21692KB 304ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * dp[i][j] = dp[(i + 1) % 2][j - 1], dp[(i + 1) % 2][j - 2] 중 최대값
 */
public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int t = nextInt();
        for (; t > 0; t--) {
            int n = nextInt();

            int[][] scores = new int[2][n];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < n; j++) {
                    scores[i][j] = nextInt();
                }
            }

            // 크기 검사
            if (n == 1) {
                sb.append(Math.max(scores[0][0], scores[1][0])).append('\n');
                continue;
            }

            scores[0][1] += scores[1][0];
            scores[1][1] += scores[0][0];

            for (int j = 2; j < n; j++) {
                for (int i = 0; i < 2; i++) {
                    scores[i][j] += Math.max(scores[(i + 1) % 2][j - 1], scores[(i + 1) % 2][j - 2]);
                }
            }

            sb.append(Math.max(scores[0][n - 1], scores[1][n - 1])).append('\n');
        }

        System.out.println(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();

        return (int) st.nval;
    }
}


