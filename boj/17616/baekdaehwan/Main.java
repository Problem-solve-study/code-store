/**
 *	260144KB	968ms
 *
 * [사고흐름]
 * 이거 A형때 나왔던 문제랑 동일한데?
 * 그래프 탐색 문제고, 1명에 대해서만 탐색하면 되므로, 시간복잡도는 N [(N-X) + X]이니까 고민없이 풀기 시작함.
 *
 * [알고리즘 설명]
 * dfs를 사용하였으며, 각 함수는 forward, reverse 방향으로 탐색했을 때, 몇 개의 노드를 탐색할 수 있는지를 반환하는 함수임.
 * 방법 특성상 메모이제이션을 사용할 수도, 사용할 필요도 없음.
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X;
    static boolean[] visitedF;
    static boolean[] visitedR;
    static List<List<Integer>> adjF;
    static List<List<Integer>> adjR;

    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        N = ni();
        M = ni();
        X = ni();

        adjF = new ArrayList<>();
        adjR = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjF.add(new ArrayList<>());
            adjR.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            int a = ni();
            int b = ni();
            adjF.get(a).add(b);
            adjR.get(b).add(a);
        }

        visitedF = new boolean[N+1];
        visitedR = new boolean[N+1];

        int lower = cntF(X);
        int higher = cntR(X);
        System.out.println(higher + " " + (N+1-lower));
    }

    public static int cntF(int c) {
        int cnt = 1;
        visitedF[c] = true;
        for (int n : adjF.get(c)) {
            if (!visitedF[n]) cnt += cntF(n);
        }
        return cnt;
    }

    public static int cntR(int c) {
        int cnt = 1;
        visitedR[c] = true;
        for (int n : adjR.get(c)) {
            if (!visitedR[n]) cnt += cntR(n);
        }
        return cnt;
    }

    public static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
