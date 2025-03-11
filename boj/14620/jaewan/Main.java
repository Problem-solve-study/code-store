/*
 * 모든 조합 검사
 * 현재까지의 최솟값보다 커지면 가지치기 추가
 */

import java.io.IOException;

public class Main {
    static int N, min = Integer.MAX_VALUE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        N = readInt();
        map = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                map[i][j] = readInt();
        func(0, 0, new int[3], 0);
        System.out.println(min);
    }

    static void func(int idx, int cnt, int[] seedIdx, int sum) {
        int[] dy = { 0, 0, 0, -1, 1 }, dx = { 0, 1, -1, 0, 0 };
        // 가지치기
        if (sum >= min || idx > (N - 1) * N)
            return;
        // 기저조건
        if (cnt == 3) {
            min = Math.min(min, sum);
            return;
        }

        int curY = idx / N, curX = idx % N;
        // 씨앗 심는 경우
        if (isPossible(idx, cnt, seedIdx)) {
            int[] newSeed = seedIdx.clone();
            newSeed[cnt] = idx;
            int cost = 0;
            for (int dir = 0; dir < 5; dir++)
                cost += map[curY + dy[dir]][curX + dx[dir]];
            func(idx + 1, cnt + 1, newSeed, sum + cost);
        }

        // 씨앗 안 심는 경우
        func(idx + 1, cnt, seedIdx, sum);

    }

    // idx 위치에 씨앗 심기 가능 여부 판단
    static boolean isPossible(int idx, int cnt, int[] seedIdx) {
        int curY = idx / N, curX = idx % N;
        // 가장자리는 꽃 못 심음.
        if (curY == 0 || curX == 0 || curY == N - 1 || curX == N - 1)
            return false;

        // 심겨진 씨앗과 충돌 발생하는지 확인
        for (int i = 0; i < cnt; i++) {
            int y = seedIdx[i] / N, x = seedIdx[i] % N;
            if (Math.abs(curX - x) + Math.abs(curY - y) < 3)
                return false;
        }
        return true;
    }

    static int readInt() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        int n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}
