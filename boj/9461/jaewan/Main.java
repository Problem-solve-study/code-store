//11436 KB, 64 ms
/*
 * f(x) = f(x - 1) + f(x - 5) (x >= 6)
 * 아, int 범위 넘는구나.
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int T = readInt();
        long[] fibo = new long[101];
        fibo[1] = 1;
        fibo[2] = 1;
        fibo[3] = 1;
        fibo[4] = 2;
        fibo[5] = 2;
        fibo[6] = 3;
        fibo[7] = 4;
        fibo[8] = 5;
        fibo[9] = 7;
        fibo[10] = 9;
        for (int i = 11; i < 101; i++)
            fibo[i] = fibo[i - 1] + fibo[i - 5];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++)
            sb.append(fibo[readInt()]).append('\n');

        System.out.print(sb);
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