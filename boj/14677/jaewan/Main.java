/// 13820 KB, 68 ms
/*
 * BFS, (l, r) 구간별 먹을 수 있는 알약 먹기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        N *= 3;
        int[] pills = new int[N];
        String str = br.readLine();
        for (int i = 0; i < pills.length; i++) {
            switch (str.charAt(i)) {
                case 'L':
                    pills[i]++;
                    break;
                case 'D':
                    pills[i] += 2;
                    break;
            }
        }

        ArrayDeque<Data> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][N];
        visit[0][N - 1] = true;
        int max = 0;
        q.offer(new Data(0, N - 1, 0));
        while (!q.isEmpty()) {
            Data cur = q.poll();
            max = Math.max(max, cur.time);
            int nextType = cur.time % 3;
            // 모든 알약을 다 먹음
            if (cur.l == cur.r && pills[cur.l] == nextType) {
                max = N;
                break;
            }
            if (pills[cur.l] == nextType && !visit[cur.l + 1][cur.r]) {
                visit[cur.l + 1][cur.r] = true;
                q.offer(new Data(cur.l + 1, cur.r, cur.time + 1));
            }
            if (pills[cur.r] == nextType && !visit[cur.l][cur.r - 1]) {
                visit[cur.l][cur.r - 1] = true;
                q.offer(new Data(cur.l, cur.r - 1, cur.time + 1));
            }
        }
        System.out.println(max);
    }

    static class Data {
        int l, r, time;

        public Data(int l, int r, int time) {
            this.l = l;
            this.r = r;
            this.time = time;
        }

    }
}