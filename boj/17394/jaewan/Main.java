/*
 * A 이상 B 이하인 소수로 만들기
 * 1. 2로 나누기
 * 2. 3으로 나누기
 * 3. +1
 * 4. -1
 * 
 * A <= B <= 100,000 이므로, 먼저 100,000 이하인 소수를 구한다.
 * 그리고, N 에서, BFS로 A이상 B 이하인 소수가 되는 최소 연산 횟수 구하기.
 * 
 * O(N x T) 
 */

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {
    static boolean[] isPrime = new boolean[100_001];

    public static void main(String[] args) throws IOException {
        Arrays.fill(isPrime, true);
        for (int i = 2; i <= Math.sqrt(100_000); i++) {
            if (!isPrime[i])
                continue;
            for (int j = 2; i * j <= 100_000; j++)
                isPrime[i * j] = false;
        }

        int TC = readInt();
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < TC; tc++)
            sb.append(func(readInt(), readInt(), readInt())).append('\n');
        System.out.print(sb);
    }

    static int func(int N, int A, int B) {
        boolean flag = false;
        for (int i = A; i <= B; i++)
            if (isPrime[i])
                flag = true;
        if (!flag)
            return -1;

        boolean[] check = new boolean[1_000_001];

        ArrayDeque<int[]> queue = new ArrayDeque<>();

        check[N] = true;
        queue.offer(new int[] { N, 0 });
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (A <= cur[0] && cur[0] <= B && isPrime[cur[0]])
                return cur[1];

            int next = cur[0] / 3;
            if (!check[next]) {
                check[next] = true;
                queue.offer(new int[] { next, cur[1] + 1 });
            }

            next = cur[0] / 2;
            if (!check[next]) {
                check[next] = true;
                queue.offer(new int[] { next, cur[1] + 1 });
            }

            next = cur[0] + 1;
            if (next < 1_000_001 && !check[next]) {
                check[next] = true;
                queue.offer(new int[] { next, cur[1] + 1 });
            }

            next = cur[0] - 1;
            if (0 <= next && !check[next]) {
                check[next] = true;
                queue.offer(new int[] { next, cur[1] + 1 });
            }
        }
        return -1;
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