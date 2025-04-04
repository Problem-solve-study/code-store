
// 15708 KB, 220 ms
/*
 * dp[i][j] : i번째 카드, j 번 마을에 방문한 경우
 * 카드 순서대로, 모든 간선을 순회하며, 양 끝의 dp 값을 업데이트.
 * O(N * K)
 */
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt();
        char[] seq = new char[N + 1];
        for (int i = 1; i <= N; i++)
            seq[i] = readChar();

        int M = readInt(), K = readInt();
        Edge[] allEdges = new Edge[K];
        for (int i = 0; i < K; i++)
            allEdges[i] = new Edge(readInt(), readInt(), readChar());

        int[][] dp = new int[N + 1][M + 1];
        for (int i = 0; i <= N; i++)
            Arrays.fill(dp[i], -9999999);

        dp[0][1] = 0;
        // N 개의 카드들 순서대로 진행
        for (int i = 1; i <= N; i++) {
            // 모든 엣지들에 대해 dp 상태 테이블 갱신
            for (int j = 0; j < K; j++) {
                dp[i][allEdges[j].u] = Math.max(dp[i][allEdges[j].u],
                        dp[i - 1][allEdges[j].v] + (allEdges[j].color == seq[i] ? 10 : 0));
                dp[i][allEdges[j].v] = Math.max(dp[i][allEdges[j].v],
                        dp[i - 1][allEdges[j].u] + (allEdges[j].color == seq[i] ? 10 : 0));
            }
        }

        int max = 0;
        for (int i = 1; i <= M; i++)
            max = Math.max(max, dp[N][i]);
        System.out.println(max);
    }

    static class Edge {
        int u, v;
        char color;

        public Edge(int u, int v, char color) {
            this.u = u;
            this.v = v;
            this.color = color;
        }
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    static char readChar() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        return (char) c;
    }
}

// 68560 KB, 580 ms
/*
 * 점수가 높아지기만 하므로 다익스트라 알고리즘의 그리디 특성 이용 및
 * DP의 상태 저장 방식을 혼용해 접근함.
 * 
 * score[i][j] : i번 마을에서 j번 카드 사용 시 최대 점수.
 * 더 높은 점수로 해당 상태에 접근했을 때만 갱신됨.
 * 
 * 카드의 수 N <= 1,000
 * 마을의 수 M <= 500
 * 길의 수 K <= 10,000
 * 
 * 두 마을을 잇는 길은 최대 1개.
 * 얻을 수 있는 최대 점수.
 * 
 * score[M][N] : M 번째 마을에 N 번째 순서에 방문해 얻을 수 있는 최대 점수 저장
 * score[1][0] = 0
 */

// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.PriorityQueue;

// public class Main {
// static Node[] graph;

// public static void main(String[] args) throws IOException {
// int N = readInt();
// char[] seq = new char[N + 1];
// for (int i = 1; i <= N; i++) {
// seq[i] = readChar();
// }
// int M = readInt(), K = readInt();
// graph = new Node[M + 1];
// for (int i = 1; i <= M; i++)
// graph[i] = new Node();
// for (int i = 0; i < K; i++) {
// int u = readInt(), v = readInt();
// char c = readChar();
// graph[u].link.add(new Edge(v, c));
// graph[v].link.add(new Edge(u, c));
// }

// int[][] score = new int[M + 1][N + 1];
// for (int i = 0; i <= M; i++)
// Arrays.fill(score[i], -1);

// int max = 0;
// score[1][0] = 0;
// PriorityQueue<Data> pq = new PriorityQueue<>();
// pq.offer(new Data(1, 0, score[1][0]));
// while (!pq.isEmpty()) {
// Data cur = pq.poll();

// if (cur.score > score[cur.n][cur.cnt])
// continue;

// if (cur.cnt == N) {
// max = Math.max(cur.score, max);
// continue;
// }

// int nextCnt = cur.cnt + 1;
// for (Edge next : graph[cur.n].link) {
// int nextScore = cur.score + (next.color == seq[nextCnt] ? 10 : 0);
// if (score[next.v][nextCnt] >= nextScore)
// continue;
// score[next.v][nextCnt] = nextScore;
// pq.offer(new Data(next.v, nextCnt, nextScore));
// }
// }

// System.out.println(max);
// }

// static class Edge {
// int v;
// char color;

// public Edge(int v, char color) {
// this.v = v;
// this.color = color;
// }
// }

// static class Node {
// ArrayList<Edge> link = new ArrayList<>();
// }

// static int readInt() throws IOException {
// int c;
// while ((c = System.in.read()) <= 32)
// ;
// int n = c & 15;
// while ((c = System.in.read()) > 47)
// n = (n << 3) + (n << 1) + (c & 15);
// return n;
// }

// static char readChar() throws IOException {
// int c;
// while ((c = System.in.read()) <= 32)
// ;
// return (char) c;
// }

// static class Data implements Comparable<Data> {
// int n, cnt, score;

// public Data(int n, int cnt, int score) {
// this.n = n;
// this.cnt = cnt;
// this.score = score;
// }

// @Override
// public int compareTo(Data d) {
// return d.score - score;
// }
// }
// }
