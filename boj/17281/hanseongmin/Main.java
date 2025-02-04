import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] results;
    static boolean[] visited = new boolean[9];
    static int res = -1;
    static int playerIdx = 0;
    static final int OUT = 0;
    static final int HIT = 1;
    static final int TWO_HIT = 2;
    static final int THREE_HIT = 3;
    static final int HOMERUN = 4;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        results = new int[n][9];
        for (int i = 0; i < n; i++) {
            results[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        visited[0] = true;
        dfs(0, new int[9]);

        bw.write(String.valueOf(res));
        bw.flush();
    }

    public static void dfs(int cnt, int[] arr) {
        //선수 순서 뽑기
        if (cnt == 9) {
            res = Math.max(res, getRes(arr));
            return;
        }

        if (cnt == 3) {
            arr[cnt] = 0;
            dfs(cnt + 1, arr);
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[cnt] = i;
                dfs(cnt + 1, arr);
                visited[i] = false;
            }
        }
    }

    public static int getRes(int[] players) {
        //게임 시뮬레이션
        int score = 0;
        playerIdx = 0;
        for (int i = 0; i < n; i++) {
            int[] ground = new int[3];
            score += simulation(ground, results[i], players);
        }

        return score;
    }

    public static int simulation(int[] ground, int[] results, int[] players) {
        int score = 0;
        int outCnt = 0;
        
        while (true) {
            if (outCnt == 3) {
                break;
            }

            int cnt = results[players[playerIdx++ % 9]];
            if (cnt == HIT) {
                score += ground[2];
                ground[2] = ground[1];
                ground[1] = ground[0];
                ground[0] = 1;
            } else if (cnt == TWO_HIT) {
                score += ground[1] + ground[2];
                ground[2] = ground[0];
                ground[1] = 1;
                ground[0] = 0;
            } else if (cnt == THREE_HIT) {
                score += ground[0] + ground[1] + ground[2];
                ground[0] = ground[1] = 0;
                ground[2] = 1;
            } else if (cnt == HOMERUN) {
                score += 1 + ground[0] + ground[1] + ground[2];
                ground[0] = ground[1] = ground[2] = 0;
            } else {
                outCnt++;
            }
        }

        return score;
    }
}