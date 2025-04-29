// 11628 KB, 64 ms
/*
 * gcd로 세 수를 나누고,
 * 연산을 잘 살펴보면 작은 수는 + 작은 수, 큰 수는 - 작은 수 이므로 전체 합은 동일
 * 먼저 전체 합이 3으로 나누어 떨어져야 함.
 * 
 * gcd로 각 수를 나누고,
 * 그리고 작은 수는 x 2 연산이다 보니, 만들어야 되는 수가 2의 거듭제곱 꼴이 아니면 못 만드는 듯..?
 * 이게 왜 됨..??
 */

import java.io.IOException;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws IOException {
        BigInteger A = BigInteger.valueOf(readInt());
        BigInteger B = BigInteger.valueOf(readInt());
        BigInteger C = BigInteger.valueOf(readInt());

        BigInteger gcd = A.gcd(B).gcd(C);

        A = A.divide(gcd);
        B = B.divide(gcd);
        C = C.divide(gcd);
        int a = A.intValue(), b = B.intValue(), c = C.intValue();
        System.out.println(func(a + b + c) ? 1 : 0);
    }

    static boolean func(int sum) {
        if (sum % 3 != 0)
            return false;
        sum /= 3;
        int bit = 1;
        while (bit <= sum) {
            if (bit == sum)
                return true;
            bit <<= 1;
        }
        return false;
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
