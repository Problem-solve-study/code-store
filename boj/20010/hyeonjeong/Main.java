// 36528KB 904ms

import java.io.*;
import java.util.*;

// MST + 트리의 지름
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static List<List<int[]>> adjList = new ArrayList<>();
    static int[] ids;
    static boolean[] visited;
    static int maxNode;
    static int maxWeight;

    public static void main(String[] args) throws IOException {
        int v = nextInt();
        int e = nextInt();
        int[][] edges = new int[e][3];
        for (int i = 0; i < e; i++) {
            edges[i][0] = nextInt();
            edges[i][1] = nextInt();
            edges[i][2] = nextInt();
        }

        // 간선 비용 순 정렬
        Arrays.sort(edges, (e1, e2) -> e1[2] - e2[2]);

        // MST 구성
        ids = new int[v];
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
            ids[i] = i;
        }

        int sumOfWeight = 0;
        for (int[] edge : edges) {
            if (find(edge[0]) == find(edge[1])) {
                continue;
            }

            sumOfWeight += edge[2];
            adjList.get(edge[0]).add(new int[] { edge[1], edge[2] });
            adjList.get(edge[1]).add(new int[] { edge[0], edge[2] });
            union(edge[0], edge[1]);
        }

        // DFS 두 번으로 트리의 지름 구하기
        visited = new boolean[v];
        search(0, 0);
        int temp = maxNode;
        maxNode = 0;
        maxWeight = 0;
        visited = new boolean[v];
        search(temp, 0);

        // 답 출력
        System.out.println(sumOfWeight);
        System.out.println(maxWeight);
    }
    
    static void search(int parent, int weight) {
        visited[parent] = true;

        if (weight > maxWeight) {
            maxWeight = weight;
            maxNode = parent;
        }

        for (int[] child : adjList.get(parent)) {
            if (visited[child[0]]) {
                continue;
            }

            search(child[0], weight + child[1]);
        }
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        ids[rootX] = rootY;
    }

    static int find(int x) {
        if (x == ids[x]) {
            return x;
        }

        return ids[x] = find(ids[x]);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
