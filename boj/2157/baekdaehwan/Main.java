/**
 * 60088KB	448ms
 * <<서쪽에서 동쪽으로 이동하는 항로가 입력될 수도 있다!!!>>
 * 
 * [사고 흐름]
 * 이거 파스타랑 비슷한 DP 문제다. => DP[도시][방문번째] = 이 도시로부터 N번째 도시까지의 최대 기내식 점수.
 * 로 이차원 배열 선언해서 풀면 될 것 같다고 생각함.
 *
 * [알고리즘 설명]
 * DP의 정의는 위에서 말한 것과 같음
 * 
 * DP[도시][방문번째] = 이 도시로부터 N번째 도시까지 도달 가능한 최대 기내식 점수.
 * 이번에도 읽기 쉽게 탑다운으로 작성했으며, 인접 리스트는 {도시, 기내식 점수}로 이루어져있음.
 * 
 * 문제에서 N = 300, c = 10,000이라고 친절히 설명해주었기 때문에, 나올 수 있는 최댓값은 3,000,000임.
 * 따라서 M 초과를 무시하기 위해서, M을 초과하는 방문번째면 Integer.MIN_VALUE 를 반환했음.
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static List<List<int[]>> adj;
    static int[][] DP;
    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        N = ni();
        M = ni();
        K = ni();

        DP = new int[N+1][M+1];
        adj = new ArrayList<>();
        for (int i=0; i<N+1; i++) adj.add(new ArrayList<>());
        for (int i=0; i<K; i++) {
            int a = ni();
            int b = ni();
            int c = ni();
            if (a<b) adj.get(a).add(new int[] {b, c});
        }

        System.out.println(Math.max(get(1, 1), 0));
    }

    public static int get(int cur, int cnt) {
        if (cnt > M) return Integer.MIN_VALUE;
        if (cur == N) return 0;
        if (DP[cur][cnt] == 0) {
            int max = Integer.MIN_VALUE;
            for (int[] next: adj.get(cur)) {
                max = Math.max(max, get(next[0], cnt+1) + next[1]);
            }
            DP[cur][cnt] = max;
        }
        return DP[cur][cnt];
    }

    public static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}