// 51292KB 3580ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashSet;
import java.util.Set;

class Main {
    static int n;
    static Set<Integer>[] tree;
    static int[] dfs;
    static boolean[] visited;       // 노드를 나올 때 방문 처리 == 자식이 없는 노드

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        
        n = Integer.parseInt(br.readLine());
        
        tree = new HashSet[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new HashSet<>();
        }
        
        for (int i = 0; i < n - 1; i++) {
            st.nextToken();
            int u = (int) st.nval - 1;
            st.nextToken();
            int v = (int) st.nval - 1;

            tree[u].add(v);
            tree[v].add(u);
        }
        
        dfs = new int[n];
        for (int i = 0; i < n; i++) {
            st.nextToken();
            dfs[i] = (int) st.nval - 1;
        }

        if (dfs[0] != 0) {
            System.out.println(0);
            return;
        }

        visited = new boolean[n];
        if (checkDfs()) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    // 입력된 dfs 방문 순서를 i = 0, j = 1부터 확인
    // 1. 간선 (i, j)가 있으면 (j, j+1) 확인으로 넘어가기 (자식 탐색)
    // 2. 간선 (i, j)가 있으면 (i-1, j) 확인 (j가 leaf 노드라 나온 시나리오)
    // 2번 케이스에서 i-1부터 0까지 확인하는데, 도중에 j를 가지지 않아 부모로 넘어가야 하는데 다른 자식이 있으면 종료
    static boolean checkDfs() {
        int pi = 0;
        int ci = 1;

        if (tree[0].isEmpty()) {
            return false;
        }
        
        // parent는 항상 자식이 있는 노드
        while (pi >= 0) {
            int parent = dfs[pi];
            int child = dfs[ci];

            // 자식이 있는데 현재 방문해야 할 자식 노드와 연결되어 있지 않으면 종료
            if (!tree[parent].contains(child)) {
                return false;
            }

            // 간선 (parent, child) 제거
            tree[parent].remove(child);
            tree[child].remove(parent);
            if (tree[parent].isEmpty())
                visited[parent] = true;
            if (tree[child].isEmpty())
                visited[child] = true;

            // 다음 탐색 설정
            pi = ci;
            ci++;

            if (ci == n) {
                break;
            }

            // 다음 parent로 설정할, pi부터 0까지 중 아직 자식이 있는 첫번째 노드 탐색
            while (pi >= 0 && visited[dfs[pi]]) {
                pi--;
            }
        }

        return true;
    }

    // static void createTestcase() {
    //     for (int i = 2; i <= 100000; i++) {
    //         System.out.printf("%d %d\n", 1, i);
    //     }

    //     for (int i = 1; i <= 100000; i++) {
    //         System.out.printf("%d ", i);
    //     }
    // }
}
