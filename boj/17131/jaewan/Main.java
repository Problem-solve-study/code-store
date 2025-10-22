/*
 * 만들 수 있느 V형 별자리의 개수.
 * (s, t, u) 가 있을 때, t 별 기준으로 좌상단 별 개수 x 우상단 별 개수 가 만들 수 있는 V형 별자리의 개수다.
 * 
 * y좌표 기준으로 내림차순 정렬하고, 세그먼트 트리로 좌측, 우측에 있는 별의 개수를 구해서 곱해서 구한다.
 * y 좌표가 같은 별의 경우는 제외해야 하므로, set 을 이용해 배치처리.
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {
    static int N;
    static long[] tree;
    static final int BASE = 400_001, MAX = 200_000, MIN = -200_000, GAP = 200_000, MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("testcase.txt"));
        N = readInt();
        Pos[] list = new Pos[N];

        for (int i = 0; i < N; i++)
            list[i] = new Pos(readInt(), readInt());
        Arrays.sort(list);

        tree = new long[BASE << 1];

        ArrayDeque<Integer> batch = new ArrayDeque<>();
        int cur = Integer.MAX_VALUE;
        long res = 0;
        for (int i = 0; i < N; i++) {

            if (cur != list[i].y) {
                while (!batch.isEmpty())
                    insert(batch.poll());
                cur = list[i].y;
            }

            res = (res + (sum(MIN, list[i].x - 1) * sum(list[i].x + 1, MAX))) % MOD;
            batch.add(list[i].x);
        }

        System.out.println(res);
    }

    public static long sum(int l, int r) {
        long t = 0;
        l += (BASE + GAP);
        r += (BASE + GAP);

        while (l <= r) {
            if ((l & 1) != 0)
                t += tree[l++];
            if ((r & 1) == 0)
                t += tree[r--];
            l >>= 1;
            r >>= 1;
        }
        return t;
    }

    public static void insert(int t) {
        t += (BASE + GAP);
        tree[t]++;
        while (t > 1)
            tree[t >>= 1]++;
    }

    static class Pos implements Comparable<Pos> {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Main.Pos o) {
            return o.y - y;
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
        boolean negative = c == 45;
        if (negative)
            c = read();
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;
    }
}