/**
 * 34600KB	352ms
 * [사고흐름]
 * 위상정렬 + DP 의 대표적인 문제같다고 생각했습니다.
 * 참고로 다음 문제도 이 문제와 동일한 DP를 사용합니다. https://www.acmicpc.net/problem/23244
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] DP, C;
    static List<List<Integer>> adj;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        DP = new int[N];
        C = new int[N];
        adj = new ArrayList<>();
        for (int i=0; i<N; ++i) adj.add(new ArrayList<>());
        for (int i=0; i<N; ++i) {
            C[i] = ni();
            int m = ni();
            for (int j=0; j<m; ++j) adj.get(ni()-1).add(i);
        }
        int res = 0;
        for (int i=0; i<N; ++i) res = Math.max(res, getDP(i));
        System.out.println(res);
    }

    static int getDP(int c) {
        if (DP[c] == 0) {
            for (int n: adj.get(c)) DP[c] = Math.max(DP[c], getDP(n));
            DP[c] += C[c];
        }
        return DP[c];
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
