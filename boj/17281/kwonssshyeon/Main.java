// 16496KB	540ms
import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int base1, base2, base3, answer;
    static int[][] attacks;
    static boolean[] visited;
    static int[] order;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        attacks = new int[n][9];
        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<9; j++) {
                attacks[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        order = new int[9];
        visited = new boolean[9];
        order[3] = 0;
        visited[0] = true;
        dfs(0);
        System.out.print(answer);
    }


    static void dfs(int idx) {
        if (idx == 9) {
            int now = game(Arrays.copyOf(order, 9));
            answer = Math.max(answer, now);
            return;
        }
        if (idx == 3) {
            dfs(idx+1);
            return;
        }
        for (int i=1; i<9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[idx] = i;
                dfs(idx+1);
                visited[i] = false;
            }
        }
    }


    static int hit(int cnt) {
        int score = 0;
        for (int i=0; i<cnt; i++) {
            score += base3;
            base3 = base2;
            base2 = base1;
            base1 = (i == 0) ? 1 : 0;
        }
        return score;
    }


    static void clearBase() {
        base1=0;
        base2=0;
        base3=0;
    }


    static int game(int[] order) {
        int player = 0;
        int result = 0;
        for (int i=0; i<n; i++) {
            int outCount = 0;
            clearBase();
            while (outCount < 3) {
                if (attacks[i][order[player]] == 0) {
                    outCount++;
                }
                else {
                    result += hit(attacks[i][order[player]]);
                }
                player = (player + 1) % 9;
            }
        }
        return result;
    }
}