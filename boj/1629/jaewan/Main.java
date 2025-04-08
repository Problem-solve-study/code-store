// 11436 KB, 68 ms
/*
 * A를 B번 곱한 수를 C로 나눈 나머지.
 * B가 2^31 - 1 으로 매우 크므로 곱하고 있으면 시간초과.
 * 거듭제곱을 사용해 log 만에 곱셈 수행해야 함.
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int A = readInt(), B = readInt(), C = readInt();

        System.out.println(pow(A, B, C));
    }

    static int pow(int A, int B, int C) {
        if (C == 1)
            return 0;
        long res = 1, temp = A % C;

        for (int p = 0; (1L << p) <= B; p++) {
            if ((B & (1 << p)) != 0)
                res = (res * temp) % C;
            temp = (temp * temp) % C;
        }
        return (int) res;
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