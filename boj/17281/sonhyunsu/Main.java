//제출번호: 89525710
//메모리:   19624 KB
//실행시간: 680 ms
import java.io.*;
import java.util.*;

public class Main {

    static int[][] hit = new int[9][50];
    static int[] order = new int[9];
    static boolean[] used = new boolean[9];
    static int n;
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                hit[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        perm(0);
        System.out.print(res);
    }

    public static void perm(int k) {
        if (k == 8) {
            int idx = 0;
            int score = 0;
            int out, player;
            boolean[] pos = new boolean[4];

            for (int i = 0; i < n; i++) {
                out = 0;
                pos[0] = pos[1] = pos[2] = pos[3] = false;
                while (out < 3) {
                    player = order[idx];
                    idx = (idx + 1) % 9;

                    if (hit[player][i] == 0) {
                        out++;
                        continue;
                    }

                    //점수 내기
                    pos[0] = true;
                    for (int j = 0; j < hit[player][i]; j++) {
                        if (pos[3 - j]) {
                            score++;
                            pos[3 - j] = false;
                        }
                    }

                    //플레이어 이동시키기기
                    for (int j = 3; j >= hit[player][i]; j--) {
                        pos[j] = pos[j - hit[player][i]];
                        pos[j - hit[player][i]] = false;
                    }
                }
            }

            res = Math.max(res, score);
            return;
        }

        //4번타자 빼고 배치하기
        int idx = k < 3 ? k : k + 1;
        for (int i = 1; i < 9; i++) {
            if (!used[i]) {
                used[i] = true;
                order[idx] = i;
                perm(k + 1);
                used[i] = false;
            }
        }
    } 
}