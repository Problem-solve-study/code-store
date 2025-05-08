/**
 * 13112KB	80ms
 * [사고흐름]
 * DP같다고 생각해서 바로 DP로 접근했습니다
 * 하지만 탑다운으로 하려다가 실수해서 중복 연산을 못잡는 바람에, 바텀업으로 다시 풀었습니다.
 */

import java.io.*;

public class Main {
    static int N, M;
    static int[][] DP;
    static boolean[][] map;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        M = ni();
        DP = new int[301][301];
        map = new boolean[301][301];
        for (int i=0; i<N; ++i) map[ni()][ni()] = true;
        for (int i=1; i<=300; ++i) {
            if (M<i) continue;
            DP[i][0] = DP[i-1][0] + (map[i][0]? M-i:0);
            DP[0][i] = DP[0][i-1] + (map[0][i]? M-i:0);
        }
        for (int r=1; r<=300; ++r) {
            for (int c=1; c<=300; ++c) {
                if (M<r+c) continue;
                DP[r][c] = Math.max(DP[r][c-1], DP[r-1][c]) + (map[r][c]? M-(r+c):0);
            }
        }
        int res = 0;
        for (int r = 0; r <= 300; r++) {
            for (int c = 0; c <= 300; c++) res = Math.max(res, DP[r][c]);
        }
        System.out.println(res);
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}