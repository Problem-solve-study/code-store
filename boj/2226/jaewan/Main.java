// 12284 KB, 68 ms
/*
 * dp[i] = dp[i-1] + dp[i-2] + ... + dp[1] + (i % 2 == 0? 1 : 0)
 * dp[i + 2] = dp[i - 1] + 2 * dp[i - 2]
 * 
 * long 범위 벗어나서 BigDecimal 사용함.
 */

import java.io.IOException;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt();
        BigDecimal cur, prev, next = BigDecimal.valueOf(0), num = BigDecimal.valueOf(2);

        if (N == 1)
            System.out.println(0);
        else if (N == 2)
            System.out.println(1);
        else {
            prev = BigDecimal.valueOf(0);
            cur = BigDecimal.valueOf(1);
            for (int i = 3; i <= N; i++) {
                next = cur.add(prev.multiply(num));
                prev = cur;
                cur = next;
            }
            System.out.println(next.toString());
        }
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