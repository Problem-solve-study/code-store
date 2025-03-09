// 22280KB 168ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// 경로 정보가 필요 없는 완탐이라 BFS 사용
public class Main {
    static final int I = 0;
    static final int J = 1;
    static final int LEFT = 0;
    static final int RIGHT = 1;
    static final char DANGEROUS = '0';

    static int n;
    static int k;
    static char[][] map;
    static boolean[][] visited;
    static int[][] deltas;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);
        deltas = new int[][]{{0, 1}, {0, -1}, {1, k}};
        visited = new boolean[2][n];

        map = new char[2][n];
        map[LEFT] = br.readLine().toCharArray();
        map[RIGHT] = br.readLine().toCharArray();

        System.out.println(bfs() ? 1 : 0);
    }

    static boolean bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{LEFT, 0});
        visited[LEFT][0] = true;

        int time = 0;
        while (!queue.isEmpty()) {
            int currentSize = queue.size();

            for (int s = 0; s < currentSize; s++) {
                int[] current = queue.poll();

                for (int[] delta : deltas) {
                    int i = (current[I] + delta[I]) % 2;
                    int j = current[J] + delta[J];

                    if (j < 0) {
                        continue;
                    }

                    if (j >= n) {
                        return true;
                    }

                    if (visited[i][j]) {
                        continue;
                    }

                    // 다음 턴에 사라질 칸
                    if (j <= time) {
                        continue;
                    }

                    if (map[i][j] == DANGEROUS) {
                        continue;
                    }

                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }

            time++;
        }

        return false;
    }
}
