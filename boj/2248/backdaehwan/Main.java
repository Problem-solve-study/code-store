/**
 * 11524KB	68ms
 * 실수로 더미코드 넣어서 1틀..
 * 
 * [사고흐름]
 * 조합문제인가..? 규칙성이 있는거 같기도 하고 잘 모르겠네요
 * 좋은 문제는 아닌 것 같습니다.
 * 
 * [알고리즘 설명]
 * MSB부터 각 비트를 확인할 때, 
 * 하위 비트로 만들 수 있는 모든 경우의 수보다 남은 수가 많다면
 * 현재 비트를 1로 하였습니다.
 */

import java.io.*;

public class Main {
    static int N, L;
    static long I;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = (int) nl();
        L = (int) nl();
        I = nl();

        int rb = L;
        long ri = I;
        StringBuilder res = new StringBuilder();
        for (int n=N; n>0; --n) {
            long cnt = 0;
            for (int r=rb; r>=0; --r) cnt += comb(n-1, r);
            if (cnt < ri) {
                ri -= cnt;
                --rb;
                res.append(1);
            } else {
                res.append(0);
            }
        }
        System.out.println(res);
    }

    static long comb(int n, int r) {
        long p=1;
        for (int i=1;i<=r;++i) p = p*(n-i+1)/i;
        return p;
    }

    static long nl() throws Exception {
        st.nextToken();
        return (long) st.nval;
    }
}
