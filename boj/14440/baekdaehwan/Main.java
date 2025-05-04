/**
 * 	11800KB	68ms
 * [사고흐름]
 * 분할정복을 이용한 거듭제곱 문제임을 쉽게 유추할 수 있었습니다.
 * n이 0, 1이 될 수 있다는 사실을 두 번 틀리고 알았습니다...
 * 2자리를 모두 출력해야 한다는 것도 몰라서 한 번 더 틀렸습니다.
 */

import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 100;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        int[] m = {ni(), ni(), 1, 0};
        int a0 = ni();
        int a1 = ni();
        int n = ni();
        if (n == 0) {
            System.out.printf("%02d", a0);
        } else if (n == 1) {
            System.out.printf("%02d", a1);
        } else {
            m = pow(m, n-1);
            System.out.printf("%02d", (a1*m[0] + a0*m[1]) % MOD);
        }
    }

    static int[] pow(int[] m, int n) {
        int[] r = {1, 0, 0, 1};
        for (; n!=0; n>>=1) {
            if ((n&1) == 1) r = mul(r, m);
            m = mul(m, m);
        }
        return r;
    }

    static int[] mul(int[] a, int[] b) {
        return new int[] {
                (a[0]*b[0] + a[1]*b[2]) % MOD,
                (a[0]*b[1] + a[1]*b[3]) % MOD,
                (a[2]*b[0] + a[3]*b[2]) % MOD,
                (a[2]*b[1] + a[3]*b[3]) % MOD,
        };
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}