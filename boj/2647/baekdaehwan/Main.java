/**
 * 	11732KB	68ms
 * 
 * [사고 흐름]
 * 최초시도, DP로 구간 최대 깊이만 구하고 그리디로 최소길이 찾기 => 당연히 틀림
 * DP로 구간 최소깊이 구하기 -> 각 구간을 1~2분할하여 DP로 최소 길이 찾는것이 가능하지 않을까?
 * 
 * [알고리즘 설명]
 * DP[l][r] = {구간 최대 깊이, 구간 최소 길이}로 정의함
 * M[l][r] = 구간 최소길이를 구했을 때 분할 지점 (분할 지점이 r과 동일하다면 분할되지 않은것임)
 * 
 * 만약 최상위 구간이 3개 존재하는 경우라도 2분할로 해결 가능하다.
 * 분할을 하는 경우 현재 좌측 구간 안예 존재하는 부분 DP[l+1][m-1]구간과 우측 구간 DP[m+1][r]구간을 분리하게 된다.
 * 우측 구간에서 또 2분할 하면 구간이 3개가 되므로 해결이 가능하다.
 * 
 * 위 내용을 바탕으로 구간 최소 길이와 구간 최대 깊이를 구한다.
 * 
 * 
 * 다행이도 최근에 임계경로 문제에서 역추적을 시도하였던게 도움되었던것 같다. M[l][r]은 구간 최소길이를 나눌 때 사용한 m을 저장해둔 배열이므로,
 * 해당 값을 사용해서 DP를 복기한다. 이 과정에서 앞선 수부터 출력되도록 만들면 풀린다.
 */

import java.io.*;

public class Main {
    static int N;
    static boolean[] isBlack;
    static int[][][] DP;
    static int[][] M;
    static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        isBlack = new boolean[N];
        DP = new int[N][N][2];
        M = new int[N][N];

        String str = br.readLine();
        for (int i=0; i<N; ++i) isBlack[i] = str.charAt(i) == '1';

        res.append(getDP(0, N-1)[1]).append('\n');
        logging(0, N-1);
        System.out.println(res);
    }

    public static void logging(int l, int r) {
        if (l > r) return;
        res.append(l+1).append(' ').append(M[l][r]+1).append('\n');
        logging(l+1, M[l][r]-1);
        logging(M[l][r]+1, r);
    }

    public static int[] getDP(int l, int r) {
        if (l > r) return new int[] {0, 0};
        if (DP[l][r][1] == 0) {
            DP[l][r][1] = Integer.MAX_VALUE;
            int cnt = 1;
            for (int m=l+1; m<=r; ++m) {
                if (isBlack[l] == isBlack[m]) ++cnt;
                else if (--cnt == 0) {
                    int[] left = getDP(l+1, m-1);
                    int[] right = getDP(m+1, r);

                    int total = m - l + ((left[0]+1)<<1) + left[1] + right[1];
                    if (DP[l][r][1] > total) {
                        DP[l][r][0] = Math.max(left[0]+1, right[0]);
                        DP[l][r][1] = total;
                        M[l][r] = m;
                    }
                }
            }
        }
        return DP[l][r];
    }
}