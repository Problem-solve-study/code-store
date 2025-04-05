/**
 * 11388KB	832ms
 * 
 * [사고흐름]
 * 로그 덧셈말고는 다른 방법이 생각나지 않아, 부동소수 오차가 없는 테케만 있을 것이라 믿고 제출했습니다.
 * 
 * [알고리즘 설명]
 * log a + log b = log ab
 */

import java.io.*;
import java.util.*;

public class Main {
    static long N;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        for (int i=0; i<N; ++i) {
            int m = ni();
            double res = 1;
            for (int j=2; j<=m; ++j) res += Math.log10(j);
            System.out.println((int) res);
        }
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}