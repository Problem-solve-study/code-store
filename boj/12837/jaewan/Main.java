// 31480 KB, 204 ms
// int로 잡았다가 2틀.... 바보다
/*
 * p일에 수입/지출 내용 추가
 * p일부터 q일까지 잔고가 변화한 값을 출력
 * 
 * 살아온 날 N <= 1e6, 쿼리의 개수 Q <= 1e5
 * 
 * 1 p x : 생후 p일에 x를 추가
 * 2 p q : 생후 p일부터 q일까지 변화한 양을 출력
 * 
 * 전형적인 세그트리 문제.
 */

import java.io.IOException;

public class Main {
    static int N;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        N = readInt();
        int Q = readInt();
        tree = new long[N << 1];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int opt = readInt(), a = readInt(), b = readInt();
            switch (opt) {
                case 1:
                    update(a, b);
                    break;

                case 2:
                    sb.append(sum(a, b)).append('\n');
                    break;
            }
        }
        System.out.print(sb);
    }

    static void update(int p, int x) {
        p += N - 1;
        while (p > 0) {
            tree[p] += x;
            p >>= 1;
        }
    }

    static long sum(int p, int q) {
        long res = 0;
        p += N - 1;
        q += N - 1;
        while (p <= q) {
            if ((p & 1) == 1)
                res += tree[p++];
            if ((q & 1) == 0)
                res += tree[q--];
            p >>= 1;
            q >>= 1;
        }
        return res;
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        boolean negative = c == 45;
        if (negative)
            c = System.in.read();
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;
    }
}