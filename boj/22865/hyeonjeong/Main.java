// 78780KB 1012ms

import java.io.*;
import java.util.*;

/**
 * 다익스트라로 땅을 탐색하면서, 가장 마지막에 방문하는 땅이 정답
 * Heap을 (거리가 짧은 순, 숫자가 큰 순)으로 정렬 -> 마지막에 방문하는 땅이 답
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();

        PriorityQueue<int[]> heap = new PriorityQueue<>((n1, n2) -> n1[1] != n2[1] ? n1[1] - n2[1] : n2[0] - n1[0]);
        for (int i = 0; i < 3; i++) {
            int friendHouse = nextInt() - 1;
            heap.add(new int[]{ friendHouse, 0 });
        }

        List<List<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        int l = nextInt();
        for (int i = 0; i < l; i++) {
            int u = nextInt() - 1;
            int v = nextInt() - 1;
            int cost = nextInt();
            adjList.get(u).add(new int[]{ v, cost });
            adjList.get(v).add(new int[]{ u, cost });
        }

        int last = -1;
        boolean[] visited = new boolean[n];
        while (!heap.isEmpty()) {
            int[] curr = heap.poll();

            if (visited[curr[0]]) {
                continue;
            }

            visited[curr[0]] = true;
            last = curr[0];
            for (int[] neighbor : adjList.get(curr[0])) {
                if (visited[neighbor[0]]) {
                    continue;
                }

                heap.add(new int[]{ neighbor[0], curr[1] + neighbor[1] });
            }
        }

        System.out.println(last + 1);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
