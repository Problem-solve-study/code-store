// 	13384KB 76ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static int n;
    static int k;
    static int[] numbers;
    static boolean[] visited;
    static Set<Integer> set = new HashSet<>();
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        visited = new boolean[n];
        dfs(0, 0);

        System.out.println(count);
    }

    static void dfs(int depth, int number) {
        if (depth == k) {
            if (set.add(number)) {
                count++;
            }

            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, number *  (int) Math.pow(10, (int) Math.log10(numbers[i]) + 1) + numbers[i]);
                visited[i] = false;
            }
        }
    }
}
