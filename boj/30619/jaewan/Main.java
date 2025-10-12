/*
 * 곱이 최대가 되도록 집을 배정.
 * 오름차순으로 정렬해 배정하는 게 최대임.
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = readInt();

        StringBuilder sb = new StringBuilder();
        int M = readInt();
        while (M-- > 0) {
            int L = readInt(), R = readInt(), idx = L;

            for (int i = 0; i < N; i++) {
                if (L <= arr[i] && arr[i] <= R)
                    sb.append(idx++).append(' ');
                else
                    sb.append(arr[i]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
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