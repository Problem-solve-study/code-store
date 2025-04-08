// 66668KB 484ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// DFS

// s를 루트로 하는 트리
// 각 경로별 최대 depth 저장 -> 최대 depth가 d 이하면 탐색 X
// 중복 어떻게 처리하지? -> 선택하는 간선 개수 = DFS 메소드 호출 횟수
public class Main {
    static int d;

    static List<List<Integer>> adjList;
    static boolean[] visited;       // 인접리스트 순회 시 부모 빼주기 위한 방문 체크
    static int[] depth;

    static int count = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken()) - 1;
        d = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        visited = new boolean[n];
        depth = new int[n];
        setTree(s);

        visited = new boolean[n];
        search(s);

        System.out.println((count - 1) * 2);
    }

    static void setTree(int parent) {
        visited[parent] = true;

        // 자식들의 최대 깊이
        int maxDepth = 0;
        for (int child : adjList.get(parent)) {
            // 이미 방문했으면 자식이 아니라 부모
            if (visited[child]) {
                continue;
            }

            setTree(child);

            if (depth[child] > maxDepth) {
                maxDepth = depth[child];
            }
        }

        depth[parent] = maxDepth + 1;
    }

    static void search(int parent) {
        count++;
        visited[parent] = true;

        for (int child : adjList.get(parent)) {
            // 이미 방문했으면 자식이 아니라 부모
            if (visited[child]) {
                continue;
            }

            // 자식의 최대 깊이가 d 이하면 무시
            if (depth[child] <= d) {
                continue;
            }

            search(child);
        }
    }
}
