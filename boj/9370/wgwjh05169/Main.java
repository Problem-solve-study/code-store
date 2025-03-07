// 91008KB 636ms

// 다익스트라로 최단 경로를 탐색하면서, 경유로 g-h를 지나는지 확인

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static final int INDEX = 0;
    static final int WEIGHT = 1;

    static int graphSize;
    static int edgeSize;
    static int[][] graph;
    static int start;
    static int g;
    static int h;
    static int targetSize;
    static int[] targets;

    static int[] dist;
    static boolean[] isVia;     // 경유로 g-h를 지나는지

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            graphSize = Integer.parseInt(st.nextToken());
            edgeSize = Integer.parseInt(st.nextToken());
            targetSize = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken()) - 1;
            g = Integer.parseInt(st.nextToken()) - 1;
            h = Integer.parseInt(st.nextToken()) - 1;
            graph = new int[graphSize][graphSize];
            
            for (int e  = 0; e < edgeSize; e++) {
                st = new StringTokenizer(br.readLine());
                int i = Integer.parseInt(st.nextToken()) - 1;
                int j = Integer.parseInt(st.nextToken()) - 1;
                int w = Integer.parseInt(st.nextToken());
                graph[i][j] = w;
                graph[j][i] = w;
            }
            
            targets = new int[targetSize];
            for (int i = 0; i < targetSize; i++) {
                targets[i] = Integer.parseInt(br.readLine()) - 1;
            }
            Arrays.sort(targets);

            dijkstra();

            for (int target : targets) {
                if (isVia[target] == true) {
                    System.out.printf("%d ", target + 1);
                }
            }
            System.out.println();
        }
    }

    static void dijkstra() {
        // dist 초기화
        dist = new int[graphSize];
        for (int i = 0; i < graphSize; i++) {
            if (i != start) {
                dist[i] = Integer.MAX_VALUE;
            }
        }
        
        // pq, 경유로 체크 배열 초기화
        PriorityQueue<int[]> queue = new PriorityQueue<>((node1, node2) -> node1[WEIGHT] - node2[WEIGHT]);
        isVia = new boolean[graphSize];
        for (int i = 0; i < graphSize; i++) {
            if (i == start || graph[start][i] == 0) {
                continue;
            }

            dist[i] = graph[start][i];
            queue.add(new int[]{i, dist[i]});
            if (start == g && i == h || start == h && i == h) {
                isVia[i] = true;
            }
        }

        // dijkstra
        while (!queue.isEmpty()) {
            int[] currentNode = queue.poll();

            // 돌아가는 길은 무시
            // 같은 길이인 경우 경유 유무가 업데이트 될 수 있어 진행
            if (dist[currentNode[INDEX]] < currentNode[WEIGHT]) {
                continue;
            }

            for (int nextNode = 0; nextNode < graphSize; nextNode++) {
                if (nextNode == start || nextNode == currentNode[INDEX]) {
                    continue;
                }
                
                // current-next 간선이 없으면 무시
                if (graph[currentNode[INDEX]][nextNode] == 0) {
                    continue;
                }

                int newDist = dist[currentNode[INDEX]] + graph[currentNode[INDEX]][nextNode];
                if (newDist > dist[nextNode]) {
                    continue;
                }

                // 새로운 길이가 현재 최단 경로보다 짧을 때만 경로 변경
                // 경유 유무를 새로운 경로의 정보로 반영
                if (newDist < dist[nextNode]) {
                    isVia[nextNode] = isVia[currentNode[INDEX]];
                    dist[nextNode] = newDist;
                    queue.add(new int[]{nextNode, dist[nextNode]});
                }

                // 새로운 길이가 현재 최단 경로와 같거나 짧을 때, 경로 유무가 true인 길로 설정
                // current-next가 경유로거나, current가 경유해서 온 노드인 경우
                if (currentNode[INDEX] == g && nextNode == h || currentNode[INDEX] == h && nextNode == g || isVia[currentNode[INDEX]]) {
                    isVia[nextNode] = true;
                }
            }
        }
    }
}
