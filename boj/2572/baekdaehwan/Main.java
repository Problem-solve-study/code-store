/**
 * 22600KB	624ms
 * 
 * [사고흐름]
 * M번 마을에 N번째로 방문하는 경우, 그 이후의 최대점수에 대한 경로는 동일하다는 생각이 들었습니다.
 * 따라서 DP로 접근했습니다.
 * 
 * [알고리즘 설명]
 * 일반적인 메모이제이션 DFS입니다.
 * 
 * DP의 정의는 다음과 같습니다.
 * DP[M][N] = M번째 마을에 N번째 순서로 방문 할 때, 그 이후에 발생하는 점수의 최댓값
 * DP[M][N] = 0 인 경우가 존재하니, 초기화는 꼭 다른값으로 설정하세요.
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static char[] card;
    static List<List<Edge>> adj;
    static int[][] DP;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        card = new char[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; ++i) card[i] = st.nextToken().charAt(0);

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        adj = new ArrayList<>();
        for (int i=0; i<M; ++i) adj.add(new ArrayList<>());
        for (int i=0; i<K; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            char c = st.nextToken().charAt(0);
            adj.get(a).add(new Edge(b, c));
            adj.get(b).add(new Edge(a, c));
        }
        DP = new int[M][N];
        for (int i=0; i<M; ++i) Arrays.fill(DP[i], -1);
        System.out.println(bt(0, 0));
    }

    static int bt(int cur, int cnt) {
        if (cnt == N) return 0;
        if (DP[cur][cnt] == -1) {
            DP[cur][cnt] = 0;
            for (Edge n : adj.get(cur)) {
                int score = bt(n.i, cnt+1) + (n.c==card[cnt]?10:0);
                DP[cur][cnt] = Math.max(DP[cur][cnt], score);
            }
        }
        return DP[cur][cnt];
    }

    static class Edge {
        int i;
        char c;
        public Edge(int i, char c) {
            this.i = i;
            this.c = c;
        }
    }
}