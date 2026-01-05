// 43336KB 520ms

import java.util.*;
import java.io.*;

/**
 * 다익스트라
 * 
 * 현재까지 읽은 생명의 값을 기준으로 탐색
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int[][] deltas = {{ 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }};

    static int n = 500;
    static int[][] map;
    static final int DANGER = 1;
    static final int DEATH = 2;

    public static void main(String[] args) throws IOException {
        n = 500;
        map = new int[n + 1][n + 1];
        boolean[][] visited = new boolean[n + 1][n + 1];

        initializeMap();

        // for (int i = 0; i < n; i++) {
        //     System.out.println(Arrays.toString(map[i]));
        // }

        PriorityQueue<int[]> heap = new PriorityQueue<>((n1, n2) -> n1[2] - n2[2]);
        heap.add(new int[]{ 0, 0, 0, 0 });
        while (!heap.isEmpty()) {
            int[] curr = heap.poll();

            if (visited[curr[0]][curr[1]]) {
                continue;
            }

            if (curr[0] == 500 && curr[1] == 500) {
                System.out.println(curr[2]);
                return;
            }

            // System.out.println(Arrays.toString(curr));
            
            visited[curr[0]][curr[1]] = true;
            for (int[] delta : deltas) {
                int ni = curr[0] + delta[0];
                int nj = curr[1] + delta[1];

                if (ni < 0 || ni > n || nj < 0 || nj > n || visited[ni][nj] || map[ni][nj] == DEATH) {
                    continue;
                }

                if (map[ni][nj] == DANGER) {
                    heap.add(new int[]{ ni, nj, curr[2] + 1, 1 });
                    continue;
                }

                heap.add(new int[]{ ni, nj, curr[2], 0 });
            }
        }

        System.out.println(-1);
    }

    static void initializeMap() throws IOException {
        int dangerCount = next();
        for (int i = 0; i < dangerCount; i++) {
            int x1 = next();
            int y1 = next();
            int x2 = next();
            int y2 = next();

            if (x1 > x2) {
                int temp = x1;
                x1 = x2;
                x2 = temp;
            }
            if (y1 > y2) {
                int temp = y1;
                y1 = y2;
                y2 = temp;
            }

            for (int di = x1; di <= x2; di++) {
                for (int dj = y1; dj <= y2; dj++) {
                    map[di][dj] = DANGER;
                }
            }
        }

        int deathCount = next();
        for (int i = 0; i < deathCount; i++) {
            int x1 = next();
            int y1 = next();
            int x2 = next();
            int y2 = next();

            if (x1 > x2) {
                int temp = x1;
                x1 = x2;
                x2 = temp;
            }
            if (y1 > y2) {
                int temp = y1;
                y1 = y2;
                y2 = temp;
            }

            for (int di = x1; di <= x2; di++) {
                for (int dj = y1; dj <= y2; dj++) {
                    map[di][dj] = DEATH;
                }
            }
        }
    }

    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
