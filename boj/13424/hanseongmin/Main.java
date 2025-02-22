import java.io.*;
import java.util.*;

/*
25764KB, 444ms

다익스트라를 N번 돌려야하나? -> N이 작네? -> 플로이드 워셜 써야겠다 (구현이 훨씬 간단)
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);

    public static void main(String[] args) throws Exception {
        int T = nextInt();
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            //입력
            int n = nextInt();
            int m = nextInt();
            int[][] adj = new int[n + 1][n + 1];
            for (int i = 0; i < m; i++) {
                int a = nextInt();
                int b = nextInt();
                int c = nextInt();
                adj[a][b] = c;
                adj[b][a] = c;
            }

            int k = nextInt();
            ArrayList<Integer> friends = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                friends.add(nextInt());
            }

            //플로이드 워셜 초기화
            int[][] dist = new int[n + 1][n + 1];
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    if (a == b) dist[a][b] = 0;
                    else if (adj[a][b] != 0) dist[a][b] = adj[a][b];
                    else dist[a][b] = 10_000_000;
                }
            }

            //플로이드 워셜
            //경유할 정점
            for (int c = 1; c <= n; c++) {
                //출발할 정점
                for (int a = 1; a <= n; a++) {
                    //도착할 정점
                    for (int b = 1; b <= n; b++) {
                        //다른 정점을 경유한게 거리가 더 작으면 갱신
                        dist[a][b] = Math.min(dist[a][b], dist[a][c] + dist[c][b]);
                    }
                }
            }

            int res = 0;
            int sum = Integer.MAX_VALUE;
            //모든 방에 대하여 친구들이 가야할 거리의 최솟값을 구함
            for (int i = 1; i <= n; i++) {
                int next = i;
                int curSum = friends.stream().mapToInt(f -> dist[f][next]).sum();
                if (sum > curSum) {
                    res = i;
                    sum = curSum;
                }
            }
            sb.append(res).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
