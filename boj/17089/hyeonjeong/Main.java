// 	261996KB 568ms

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// 투 포인터st BFS
class Main {
    static final int FIRST = 0;
    static final int SECOND = 1;
    static final int THIRD = 2;
    
    static int n;
    static List<Set<Integer>> adjList = new ArrayList<>();
    static boolean[] visited;
    static int minimum = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            adjList.add(new HashSet<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            bfs(i);
        }

        if (minimum == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(minimum);
    }
    
    static void bfs(int first) {
        Queue<int[]> queue = new ArrayDeque<>();

        // 서로 모두 연결된 모든 first - second - third 조합 넣어서 초기화
        for (int second : adjList.get(first)) {
            for (int third : adjList.get(second)) {
                if (first == third) {
                    continue;
                }

                if (!adjList.get(first).contains(third)) {
                    continue;
                }

                int friends = adjList.get(first).size() + adjList.get(second).size() + adjList.get(third).size() - 6;
                if (friends < minimum) {
                    minimum = friends;
                }
                
                queue.add(new int[]{first, second, third});
                visited[first] = true;
            }
        }

        // 세 친구가 없으면 진행 X
        if (queue.isEmpty()) {
            return;
        }

        // 매 노드에 대해 first 제거 & third의 친구 추가
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            if (visited[current[FIRST]]) {
                continue;
            }

            for (int nextThird : adjList.get(current[THIRD])) {
                if (nextThird == current[SECOND]) {
                    continue;
                }

                if (!adjList.get(current[SECOND]).contains(nextThird)) {
                    continue;
                }

                int friends = adjList.get(current[SECOND]).size() + adjList.get(current[THIRD]).size() + adjList.get(nextThird).size() - 6;
                if (friends < minimum) {
                    minimum = friends;
                }
                
                queue.add(new int[]{current[SECOND], current[THIRD], nextThird});
                visited[current[SECOND]] = true;
            }
        }

        return;
    }
}
