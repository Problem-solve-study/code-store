// 156668KB 1016ms

import java.io.*;
import java.util.*;

/**
 * 다익스트라
 * 
 * 정점 u에서 다음으로 갈 수 있는 정점들을 모두 큐에 넣고, 시간이 빠른 순으로 탐색하기
 * visited[]에 해당 정점에 도착했던 시간을 넣어, 다음 정점에 이미 더 빨리 도차한 적 있으면 무시
 *
 * 정답 최대가 n * m으로 int 범위를 넘어감에 주의
 */
public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int m;

    public static void main(String[] args) throws IOException {
        int n = next();
        m = next();

        List<List<int[]>> bridges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            bridges.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = next() - 1;
            int v = next() - 1;

            bridges.get(u).add(new int[]{ i, v });
            bridges.get(v).add(new int[]{ i, u });
        }

        PriorityQueue<Item> queue = new PriorityQueue<>();
        long[] visited = new long[n];
        for (int i = 0; i < n; i++) {
            visited[i] = Long.MAX_VALUE;
        }
        
        queue.add(new Item(0, -1, 0));
        visited[0] = -1;

        while (!queue.isEmpty()) {
            Item curr = queue.poll();

            for (int[] bridge : bridges.get(curr.node)) {
                Item next = curr.getNext(bridge[1], bridge[0]);

                long nextTime = (1L * next.cycleCount * m) + next.period;
                if (visited[next.node] <= nextTime) {
                    continue;
                }

                queue.add(next);
                visited[next.node] = nextTime;
            }
        }

        System.out.println(visited[n - 1] + 1);
    }

    static class Item implements Comparable<Item> {
        int node;
        int period;
        int cycleCount;

        Item(int node, int period, int cycleCount) {
            this.node = node;
            this.period = period;
            this.cycleCount = cycleCount;
        }

        Item getNext(int node, int period) {
            if (period > this.period) {
                return new Item(node, period, this.cycleCount);
            }
            return new Item(node, period, this.cycleCount + 1);
        }

        @Override
        public int compareTo(Item other) {
            long gap = (1L * this.cycleCount * m + this.period) - (1L * other.cycleCount * m + other.period);

            if (gap == 0) {
                return 0;
            }
            if (gap > 0) {
                return 1;
            }
            return -1;
        }

        @Override
        public String toString() {
            return String.format("Item=[node=%d, period=%d, cycle=%d, time=%d]", node, period, cycleCount, cycleCount * m + period);
        }
    }

    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
