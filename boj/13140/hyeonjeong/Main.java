// 12488KB 108ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 10P7 순열
public class Main {
    static final int SIZE = 10;
    static final int BOUND = 7;
    static final int D = 0;
    static final int E = 1;
    static final int H = 2;
    static final int L = 3;
    static final int O = 4;
    static final int R = 5;
    static final int W = 6;

    static int n;
    static int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    static boolean[] visited = new boolean[SIZE];
    static int[] path = new int[BOUND];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        boolean result = dfs(0);

        if (!result) {
            System.out.println("No Answer");
        }
    }

    static boolean dfs(int depth) {
        if (depth == 7) {
            int num1 = path[H] * 10000 + path[E] * 1000 + path[L] * 100 + path[L] * 10 + path[O];
            int num2 = path[W] * 10000 + path[O] * 1000 + path[R] * 100 + path[L] * 10 + path[D];
            
            if (num1 + num2 == n) {
                display();
                return true;
            }

            return false;
        }

        boolean result = false;
        int i = (depth == H || depth == W) ? 1 : 0;
        for (; i < SIZE; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            path[depth] = numbers[i];

            result = dfs(depth + 1);
            if (result) {
                return true;
            }

            path[depth] = 0;
            visited[i] = false;
        }

        return result;
    }

    static void display() {
        System.out.println(String.format("  %d%d%d%d%d", path[H], path[E], path[L], path[L], path[O]));
        System.out.println(String.format("+ %d%d%d%d%d", path[W], path[O], path[R], path[L], path[D]));
        System.out.println("-------");
        System.out.println(String.format("%7d", n));
    }
}
