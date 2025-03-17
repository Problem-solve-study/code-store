/*
 * 매개 변수 탐색
 * 
 */

import java.io.IOException;

public class Main {
    static int N, M;
    static int[] table;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        table = new int[N];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            table[i] = readInt();
            min = Math.min(min, table[i]);
        }
        System.out.println(parametricSearch(1, (long) min * M));
    }

    static long parametricSearch(long left, long right) {
        long mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            long t = count(mid);
            // 조건 만족
            if (t >= M)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return left;
    }

    // T 초 동안 각 심사대에서 심사 가능한 사람 수 합
    static long count(long T) {
        long cnt = 0;
        for (int i = 0; i < N; i++)
            cnt += T / table[i];
        return cnt;
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
