// 40648KB 680ms

import java.io.*;
import java.util.*;

/**
 * MST (크루스칼)
 * 
 * 기존 방을 0~n+1번 노드로, 탈출구를 n번 노드로 설정
 * i번째 비상탈출구를 (i번 노드, n번 노드) 간선으로 취급
 * 0~n번 노드에 대한 MST 찾기
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int n;
    static int[] ids;
    
    public static void main(String[] args) throws IOException {
        n = nextInt();
        int m = nextInt();
        
        ids = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            ids[i] = i;
        }

        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int a = nextInt() - 1;
            int b = nextInt() - 1;
            int t = nextInt();
            edges.add(new int[]{a, b, t});
        }
        for (int i = 0; i < n; i++) {
            int t = nextInt();
            edges.add(new int[]{i, n, t});
        }

        // 워프와 탈출구를 시간 순으로 정렬
        Collections.sort(edges, (c1, c2) -> c1[2] - c2[2]);

        int time = 0;               // 간선 설치 비용
        int connectionCount = 0;    // 연결된 간선의 개수
        for (int[] edge : edges) {
            if (find(edge[0]) == find(edge[1])) {
                continue;
            }

            union(edge[0], edge[1]);
            time += edge[2];
            connectionCount++;
            
            // 탈출구를 포함한 모든 노드가 연결되면 종료
            if (connectionCount == n) {
                break;
            }
        }

        System.out.println(time);
    }

    static void union(int x, int y) {   
        int rootX = find(x);
        int rootY = find(y);

        ids[rootX] = rootY;
    }

    static int find(int x) {
        if (ids[x] == x) {
            return x;
        }

        return ids[x] = find(ids[x]);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
