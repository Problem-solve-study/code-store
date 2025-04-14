// 12096 KB, 80 ms
/*
 * 외판원 순회. 10! 백트래킹
 */

import java.io.IOException;

public class Main {
    static int N, min = Integer.MAX_VALUE;
    static boolean[] visit;
    static int[][] weight;

    public static void main(String[] args) throws IOException {
        N = readInt();
        visit = new boolean[N];
        weight = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                weight[i][j] = readInt();

        for (int i = 0; i < N; i++) {
            visit[i] = true;
            makePerm(0, i, i, 1);
            visit[i] = false;
        }

        System.out.println(min);
    }

    static void makePerm(int sum, int start, int prev, int cnt) {
        if (min <= sum)
            return;
        if (cnt == N) {
            if (weight[prev][start] == 0)
                return;
            sum += weight[prev][start];
            min = Math.min(min, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visit[i] || weight[prev][i] == 0)
                continue;
            visit[i] = true;
            makePerm(sum + weight[prev][i], start, i, cnt + 1);
            visit[i] = false;
        }
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}