// 11504 KB, 448 ms
/*
 * ([n / 2] - 1) + ... + ([n / (n / 2)] - 1)
 * n이 2억까지니까 1억번 연산. O(N)
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int n = readInt(), t = n >> 1;
        long res = 0;

        for (int i = 2; i <= t; i++) {
            res += i * (n / i - 1);
            res %= 1_000_000;
        }
        System.out.println(res);
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