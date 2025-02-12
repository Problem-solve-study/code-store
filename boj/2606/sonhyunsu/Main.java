//제출번호: 89950597
//메모리:   11740 KB
//실행시간: 68 ms

import java.io.*;
import java.util.*;

//그래프 탐색 문제
//dfs든 bfs든 1번과 함께 있는 컴퓨터의 수만 잘 세어주면 된다.
//1번을 세지 않도록 주의
public class Main {

    static List<List<Integer>> graph = new ArrayList<>();
    static Queue<Integer> queue = new ArrayDeque<>();
    static boolean[] visited = new boolean[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        int res = 0;
        visited[1] = true;
        queue.add(1);
        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int nNode : graph.get(node)) {
                if (!visited[nNode]) {
                    visited[nNode] = true;
                    queue.add(nNode);
                    res++;
                }
            }
        }

        System.out.print(res);
    }
}