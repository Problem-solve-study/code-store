/**
 * 109752KB	240ms
 * 
 * 뒤집은 문자열이랑 lcs하면 되지 않나 싶었습니다.
 * 근데 lcs점화식을 잘못 써서 2틀...
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[] A, B;
    static int[][] lcs;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = br.readLine().toCharArray();
        B = new char[N];
        for (int i=0; i<N; ++i) B[N-i-1] = A[i];

        lcs = new int[N+1][N+1];
        for (int i=1; i<=N; ++i) {
            for (int j=1; j<=N; ++j) {
                if (A[i-1]==B[j-1]) lcs[i][j] = lcs[i-1][j-1] + 1;
                else lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
            }
        }
        System.out.println(N-lcs[N][N]);
    }
}
