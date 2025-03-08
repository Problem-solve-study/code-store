// 11940KB 88ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] numbers;
    static boolean[] visited;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        numbers = new int[n];
        visited = new boolean[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        dfs(-1, 0, 0);

        System.out.println(max);
    }

    static void dfs(int prev, int sum, int depth) {
        if (depth == n) {
            if (sum > max) {
                max = sum;
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            dfs(i, sum + (depth == 0 ? 0 : Math.abs(numbers[prev] - numbers[i])), depth + 1);
            visited[i] = false;
        }
    }
}
