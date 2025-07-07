// 22676 KB, 184 ms
/*
 * 케이크를 배달할 수 있는 경우는 5가지가 있음.
 * 상, 하, 좌, 우, 그 위치
 * 
 * dp[i][j] : i번째 고객의 j 위치에 배달하는 최단 거리
 * 
 * 이전 좌표를 저장해서 맨해튼 거리로 계산함.
 * 
 * max를 얼마로 잡아야할까.
 * 0,0 이랑 (100,000, 100,000) 를 100,000번 왔다갔다 하면?
 * 
 * 200,000 x 100,000
 * 
 * 2e10
 */

import java.io.IOException;
import java.util.Arrays;

public class Main {
    static final long MAX = 100_000_000_000L;

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] dx = { 0, 0, -1, 1, 0 }, dy = { -1, 1, 0, 0, 0 };
        long[] prev = new long[7], cur = new long[7];

        prev[0] = readInt();
        prev[1] = readInt();
        prev[2] = prev[3] = prev[4] = prev[5] = MAX;

        for (int i = 0; i < N; i++) {
            Arrays.fill(cur, MAX);
            cur[0] = readInt();
            cur[1] = readInt();

            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    cur[j + 2] = Math.min(cur[j + 2],
                            prev[k + 2] + Math.abs((prev[0] + dy[k]) - (cur[0] + dy[j]))
                                    + Math.abs((prev[1] + dx[k]) - (cur[1] + dx[j])));
                }
            }

            prev = Arrays.copyOf(cur, 7);
        }

        long min = Long.MAX_VALUE;
        for (int i = 2; i < 7; i++)
            min = Math.min(min, prev[i]);
        System.out.println(min);
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}