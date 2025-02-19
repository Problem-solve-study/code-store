/**
 * 11588KB	68ms
 * 
 * [사고흐름]
 * 비슷한 유형의 문제가 워낙 많아서, 
 * 처음부터 메모이제이션 쓰는 DP문제라고 생각이 들었음.
 * 종이에 각 경우의 수를 미리 써두고 풀이를 시작하면 비교적 쉽게 풀 수 있을듯함.
 * 
 * [알고리즘 설명]
 * <dfs 함수의 정의>
 * day일에 kind를 선택했을때 day일부터 시작하는 기간동안의 경우의 수(seq는 연속 선택 횟수), DP의 정의 또한 동일함.
 * 
 * 다음 번째를 호출하는 경우의 수가 다음과 같이 5가지이므로 그에 맞게 DFS
 * - 다음날짜의 선택이 확정되어 있음, 현재 선택과 동일함, 현재 2회 연속 선택    => 불가능한 경우로 0반환
 * - 다음날짜의 선택이 확정되어 있음, 현재 선택과 동일함                       => seq를 1늘리고 dfs
 * - 다음날짜의 선택이 확정되어 있음, 현재 선택과 다름                         => seq를 1로 초기화하여 dfs
 * - .
 * - .
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[][][] DP;
    static boolean[] isConfirmed;
    static int[] confirmed;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        DP = new int[N+1][3][3];
        confirmed = new int[N+1];
        isConfirmed = new boolean[N+1];
        for (int i=0; i<K; ++i) {
            st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int kind = Integer.parseInt(st.nextToken());
            confirmed[day] = kind-1;
            isConfirmed[day] = true;
        }
        System.out.println(dfs(0, 0, 0));
    }

    static int dfs(int day, int kind, int seq) {
        if (day == N) return 1;
        if (DP[day][kind][seq] == 0) {
            if (isConfirmed[day+1]) {
                int nkind = confirmed[day+1];
                if (kind == nkind) {
                    if (seq == 2) {
                        DP[day][kind][seq] = 0;
                    }
                    else {
                        DP[day][kind][seq] = dfs(day+1, nkind, seq+1);
                    }
                }
                else {
                    DP[day][kind][seq] += dfs(day+1, nkind, 1);
                }
            }
            else {
                for (int nkind=0; nkind<3; ++nkind) {
                    if (kind == nkind) {
                        if (seq == 2) continue;
                        else DP[day][kind][seq] += dfs(day+1, nkind, seq+1);
                    }
                    else {
                        DP[day][kind][seq] += dfs(day+1, nkind, 1);
                    }
                }
            }
        }
        return DP[day][kind][seq] % 10000;
    }
}
