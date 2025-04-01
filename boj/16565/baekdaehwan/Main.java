/**
 * 	11508KB	68ms
 *
 * 	[사고흐름]
 * 	어쩌다 보니 파스칼의 삼각형 힌트를 얻고 시작했습니다
 *  포함 배제의 원리인지는 모르겠지만 아래처럼 써나가다 보니까 대충 어떤문제인지 알게되었습니다. 수학적 기반이 얕아서 그런지 어렵네요 :(
 *
 * 4: 13C1 * 48C0
 * 5: 13C1 * 48C1
 * 6: 13C1 * 48C2
 * 7: 13C1 * 48C3
 * 8: 13C1 * 48C4 - 13C2 * 44C0
 * . . .
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] C;
    static int MOD = 10007;

    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        N = ni();
        C = new int[53][53];
        for (int i=0; i<=52; i++) C[i][0] = 1;
        for (int i=1; i<=52; i++) C[i][1] = i;
        for (int i=1; i<=52; i++) C[i][i] = 1;
        for (int n=1; n<=52; n++) {
            for (int c=2; c<n; c++) C[n][c] = (C[n-1][c-1] + C[n-1][c]) % MOD;
        }

        if (N<4) System.out.println(0);
        else {
            int res = 0;
            for (int i=1; i<=N>>2; ++i) {
                if ((i&1) == 1) res += C[13][i] * C[52-(i<<2)][N-(i<<2)] % MOD;
                else res = (res - C[13][i] * C[52-(i<<2)][N-(i<<2)] + MOD) % MOD;
            }
            System.out.println((res+MOD) % MOD);
        }
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}