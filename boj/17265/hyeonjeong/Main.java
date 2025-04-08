// 11744KB 72ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N이 5?
// DFS ㄱㄱ
public class Main {
    static int n;
    static char[][] map;

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                map[i][j] = line[j * 2];
            }
        }

        dfs(0, 0, map[0][0] - '0');

        System.out.printf("%d %d", max, min);
    }

    static void dfs(int i, int j, int result) {
        if (i == n - 1 && j == n - 1) {
            if (result < min) {
                min = result;
            }
            if (result > max) {
                max = result;
            }
            return;
        }

        if (j + 2 < n) {
            dfs(i, j + 2, calculate(result, map[i][j + 1], map[i][j + 2] - '0'));
        }

        if (j + 1 < n && i + 1 < n) {
            dfs(i + 1, j + 1, calculate(result, map[i][j + 1], map[i + 1][j + 1] - '0'));
            dfs(i + 1, j + 1, calculate(result, map[i + 1][j], map[i + 1][j + 1] - '0'));
        }

        if (i + 2 < n) {
            dfs(i + 2, j, calculate(result, map[i + 1][j], map[i + 2][j] - '0'));
        }
    }

    static int calculate(int op1, char command, int op2) {
        switch (command) {
            case '+':
                return op1 + op2;
            case '-':
                return op1 - op2;
            case '*':
                return op1 * op2;
        }
        return -1;
    } 
}
