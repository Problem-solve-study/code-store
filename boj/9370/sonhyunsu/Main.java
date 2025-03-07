
//제출번호: 90994682
//메모리:   31432 KB
//실행시간: 408	ms
import java.io.*;
import java.util.*;

//문제를 봤을 때, 최단 거리를 구해야 하니까 다익스트라 알고리즘을 떠올림
//특정 도로를 지나는 최단 경로인지 확인하는 방법을 떠올리는 게 쉽지 않았음
//다익스트라를 여러 번 쓰면 확정으로 할 수 있지만, 한 번만 돌려도 구할 수 있을 것 같았음
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PriorityQueue<int[]> pq = new PriorityQueue<>(
            (o1, o2) -> o1[0] == o2[0]
                    ? Integer.compare(o1[1], o2[1])
                    : Integer.compare(o1[0], o2[0]));

    public static void main(String[] args) throws IOException {
        int T = nextInt();

        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < 2001; i++) {
            graph.add(new ArrayList<>());
        }

        int[] dp = new int[2001];
        boolean[] isPassed = new boolean[2001];
        List<Integer> availableGoals = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int test = 0; test < T; test++) {
            int n = nextInt();
            int m = nextInt();
            int t = nextInt();

            int s = nextInt();
            int g = nextInt();
            int h = nextInt();

            for (int i = 1; i <= n; i++) {
                graph.get(i).clear();
                dp[i] = Integer.MAX_VALUE;
                isPassed[i] = false;
            }

            for (int i = 0; i < m; i++) {
                int a = nextInt();
                int b = nextInt();
                int d = nextInt();

                graph.get(a).add(new int[] { b, d });
                graph.get(b).add(new int[] { a, d });
            }

            // 시작점은 거리가 0임
            dp[s] = 0;
            pq.add(new int[] { 0, s });
            while (!pq.isEmpty()) {
                int[] item = pq.poll();

                int total = item[0];
                int node = item[1];

                // 종복된 점이 들어올 수 있는데 그걸 한 번 더 돌아가는 것을 방지하기 위해
                // 현재 거리가 정점에 기록된 최단 거리보다 크다면 최단 거리가 아니므로 패스
                if (dp[node] < total) {
                    continue;
                }

                for (int[] edge : graph.get(item[1])) {
                    int nNode = edge[0];
                    int weight = edge[1];

                    // 만약에 지금까지 계산되었던 최단거리보다 이번 정점을 지나는 게 더 최단거리라면
                    if (dp[nNode] > dp[node] + weight) {
                        dp[nNode] = dp[node] + weight; // 갱신
                        pq.add(new int[] { dp[nNode], nNode });

                        // 현재 정점이 이미 지났거나 이번 도로가 반드시 지나야 하는 도로면 isPassed에 기록
                        isPassed[nNode] = isPassed[node] || isTargetEdge(g, h, node, nNode);
                    } else if (dp[nNode] == dp[node] + weight) {
                        // 만약에 최단 거리가 같은데 경로가 다른 경우에는
                        // 이전에 계산되었던 도착 정점의 최단 거리에 그 도로가 추가 되어 있거나
                        // 이번 정점을 통해서 지나갈 수 있으면 isPassed에 기록
                        isPassed[nNode] = isPassed[nNode] || isPassed[node] || isTargetEdge(g, h, node, nNode);
                    }
                }
            }

            availableGoals.clear();
            for (int i = 0; i < t; i++) {
                int goal = nextInt();

                // 만약에 g-h 간선을 지나는 지나는 최단경로면 가능한 정점에 추가
                if (isPassed[goal]) {
                    availableGoals.add(goal);
                }
            }

            // 정렬 후 출력
            availableGoals.sort(Comparator.naturalOrder());
            for (int goal : availableGoals) {
                sb.append(goal).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    static boolean isTargetEdge(int g, int h, int node, int nNode) {
        return (g == node || g == nNode) && (h == node || h == nNode);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}