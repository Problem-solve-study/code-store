//제출번호: 90177054
//메모리:   186624 KB
//실행시간: 1428 ms
import java.io.*;
import java.util.*;

//그래프 탐색을 이용해서 부모 정점을 기록하는 문제
//dfs든 bfs든 다음 탐색 점을 지정할 때 부모를 기록하면 된다.
public class Main {    

    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];
        parents = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        dfs(1, 0);
        for (int i = 2; i <= n; i++) {
            bw.append(String.format("%d%n", parents[i]));
        }

        bw.flush();
    }

    static void dfs(int node, int parent) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;
        parents[node] = parent;
        for (int nNode : graph.get(node)) {
            dfs(nNode, node);
        }
    }
}