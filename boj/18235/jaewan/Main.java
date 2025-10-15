// 11440 KB, 64 ms
/*
 * 
 * 같은 일자에, 같은 방향으로 점프하면 '차'는 줄어들지 않음.
 * 반대 방향으로 점프하면, '차'는 (2 << d) 만큼 줄어들거나, 늘어남.
 * 
 * 줄어드는 거리는, 2의 제곱수 이므로, '차' 를 2의 제곱수의 합의 형태로 나타내는 문제임.
 * 예를 들어, '차' 가 12면, 1일차는 같은 방향, 2일차는 가까워지는 방향으로 움직여 4, 3일차는 가까워져 8 총 12만큼 가까워져야 함.
 * 따라서, 비트 연산 활용해 해당 일자에 같은 방향으로 움직일지, 멀어질 지 판단할 수 있음.
 * 
 * 그 전에, 차가 홀수면 절대 만날 수 없음.
 */

import java.io.IOException;

public class Main {
    static int N, res = 30;

    public static void main(String[] args) throws IOException {
        N = readInt();
        int A = readInt(), B = readInt();

        if ((A - B) % 2 == 1) {
            System.out.println(-1);
            return;
        }

        dfs(Math.min(A, B), Math.max(A, B), 0);
        System.out.println(res == 30 ? -1 : res);
    }

    static void dfs(int a, int b, int d) {
        if (a <= 0 || a > N || b <= 0 || b > N)
            return;

        if (a == b) {
            res = Math.min(res, d);
            return;
        }

        if ((Math.abs(a - b) & (2 << d)) != 0) {
            dfs(a + (1 << d), b - (1 << d), d + 1);
        } else {
            dfs(a + (1 << d), b + (1 << d), d + 1);
            dfs(a - (1 << d), b - (1 << d), d + 1);
        }
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