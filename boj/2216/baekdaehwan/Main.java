/**
 * 	47132KB	152ms
 * [사고흐름]
 * DP라고 생각했습니다. 근데 LCS구한 다음 단순 연산으로 풀릴줄 알았으나, 실제로는 역추적없이 불가능해서 1틀했습니다 :(
 */

import java.io.*;

public class Main {
    static int A, B, C;
    static char[] S1, S2;
    static int[][] DP;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        A = ni();
        B = ni();
        C = ni();
        S1 = ns().toCharArray();
        S2 = ns().toCharArray();
        DP = new int[S1.length+1][S2.length+1];
        for (int r=0; r<=S1.length; ++r) DP[r][0] = r * B;
        for (int c=0; c<=S2.length; ++c) DP[0][c] = c * B;
        for (int r=1; r<=S1.length; ++r) {
            for (int c=1; c<=S2.length; ++c) {
                DP[r][c] = Math.max(DP[r-1][c], DP[r][c-1])+B;
                DP[r][c] = Math.max(DP[r][c], DP[r-1][c-1]+(S1[r-1]==S2[c-1]? A:C));
            }
        }
        System.out.println(DP[S1.length][S2.length]);
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
    static String ns() throws Exception {
        st.nextToken();
        return st.sval;
    }
}