// 76696KB 700ms

import java.io.*;
import java.util.*;

/**
 * 좀비에게 점령 당한 도시에서 출발해 그래프를 탐색한다
 * 모든 도시에서 동시에 출발한다.
 * -> 여러 좀비 도시로부터 위험한 도시에서, 일부 다음 경로가 무시되지 않게 방지한다.
 * 
 * +
 * 
 * 최단 거리 탐색 (다익스트라)
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        final int CITY_COUNT = nextInt();
        final int EDGE_COUNT = nextInt();
        final int OVER_CITY_COUNT = nextInt();
        final int WARN_DIST = nextInt();
        final int SAFE_COST = nextInt();
        final int WARN_COST = nextInt();
        
        final int START = 0;
        final int DEPARTURE = CITY_COUNT - 1;

        // 그래프의 인접 리스트
        List<Set<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < CITY_COUNT; i++) {
            adjList.add(new HashSet<>());
        }

        // 도시의 상태 정보
        final int SAFE = 0;
        final int OVER = 1;
        final int WARN = 2;
        int[] cityState = new int[CITY_COUNT];

        // 최단 거리 파악에 사용할 최대 이동 거리
        final long MAX_DIST = CITY_COUNT * (long) WARN_COST;

        // 위험 도시 파악에 사용할 큐
        final int NODE = 0;
        final int DIST = 1;
        ArrayDeque<int[]> queue = new ArrayDeque<>();


        // 좀비에게 점령된 도시 입력
        for (int i = 0; i < OVER_CITY_COUNT; i++) {
            int city = nextInt() - 1;

            cityState[city] = OVER;
            queue.add(new int[]{ city, 0 });
        }

        // 그래프의 인접 리스트 입력
        for (int i = 0; i < EDGE_COUNT; i++) {
            int u = nextInt() - 1;
            int v = nextInt() - 1;
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }


        // 위험한 도시 마스킹
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            if (cityState[current[NODE]] == WARN) {
                continue;
            }

            if (cityState[current[NODE]] != OVER) {
                cityState[current[NODE]] = WARN;
            }
            if (current[DIST] == WARN_DIST) {
                continue;
            }

            for (int neighbor : adjList.get(current[NODE])) {
                queue.add(new int[]{ neighbor, current[DIST] + 1 });
            }
        }
        cityState[DEPARTURE] = SAFE;


        // 최단 경로 탐색
        long[] distTo = new long[CITY_COUNT];
        for (int i = 1; i < CITY_COUNT; i++) {
            distTo[i] = MAX_DIST;
        }

        PriorityQueue<Edge> heap = new PriorityQueue<>();
        heap.add(new Edge(START, 0));

        while (!heap.isEmpty()) {
            Edge current = heap.poll();

            if (distTo[current.node] < current.cost) {
                continue;
            }

            distTo[current.node] = current.cost;
            for (int neighbor : adjList.get(current.node)) {

                // 좀비가 점령한 도시는 갈 수 없음
                if (cityState[neighbor] == OVER) {
                    continue;
                }

                // 이웃의 갱신 여부 확인
                long newCost = distTo[current.node] + (cityState[neighbor] == WARN ? WARN_COST : SAFE_COST);
                if (distTo[neighbor] <= newCost) {
                    continue;
                }

                distTo[neighbor] = newCost;
                heap.add(new Edge(neighbor, newCost));
            }
        }

        System.out.println(distTo[DEPARTURE] - SAFE_COST);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static class Edge implements Comparable {
        int node;
        long cost;

        Edge(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Object o) {
            return Long.compare(this.cost, ((Edge) o).cost);
        }
    }
}
