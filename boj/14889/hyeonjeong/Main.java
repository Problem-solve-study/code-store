// 19804KB, 348ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static int[][] scores;
    static boolean[] path;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        scores = new int[n][n];
        for (int i = 0; i < n; i++)
            scores[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        path = new boolean[n];
        path[0] = true;

        dfs(0, 1);

        System.out.println(min);
    }

    static void dfs(int prev, int depth) {
        if (depth == n / 2) {
            calculateScore();
            return;
        }

        for (int i = prev + 1; i < n; i++) {
            path[i] = true;
            dfs(i, depth + 1);
            path[i] = false;
        }
    }

    static void calculateScore() {
        int scoreGap = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (path[i] == path[j]) {
                    scoreGap += path[i] ? scores[i][j] : scores[i][j] * -1;
                    scoreGap += path[i] ? scores[j][i] : scores[j][i] * -1;
                }
            }
        }

        scoreGap = Math.abs(scoreGap);
        if (scoreGap < min) {
            min = scoreGap;
        }
    }
}