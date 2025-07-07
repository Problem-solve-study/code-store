/**
 * 17932KB	152ms
 * 
 * 저는 바보입니다. 이 코드를 확인하지 말아주세요
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N, A, B;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        A = ni();
        B = ni();

        StringBuilder res = new StringBuilder();
        if (A+B-1 > N) {
            System.out.println(-1);
        } else if (A>=B) {
            N -= A+B-1;
            for (int i=0; i<N; ++i) res.append(1).append(' ');
            for (int i=1; i<=A; ++i) res.append(i).append(' ');
            for (int i=B-1; i>0; --i) res.append(i).append(' ');
            System.out.println(res);
        } else {
            N -= A+B-1;
            if (A==1) {
                for (int i=1; i<A; ++i) res.append(i).append(' ');
                res.append(B).append(' ');
                for (int i=0; i<N; ++i) res.append(1).append(' ');
                for (int i=B-1; i>0; --i) res.append(i).append(' ');
            } else {
                for (int i=0; i<N; ++i) res.append(1).append(' ');
                for (int i=1; i<A; ++i) res.append(i).append(' ');
                for (int i=B; i>0; --i) res.append(i).append(' ');
            }
            System.out.println(res);
        }
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}