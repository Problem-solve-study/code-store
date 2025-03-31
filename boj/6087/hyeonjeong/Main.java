// 12348KB 72ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// BFS
public class Main {
    static final int[][][] deltas = {
        {{0, 1}, {1, 0}, {-1, 0}},      // RIGHT
        {{0, -1}, {1, 0}, {-1, 0}},     // LEFT
        {{1, 0}, {0, 1}, {0, -1}},      // DOWN
        {{-1, 0}, {0, 1}, {0, -1}}      // UP
    };
    static final int WALL = -1;

    static final int I = 0;
    static final int J = 1;
    static final int MIRROR = 2;
    static final int DIRECTION = 3;

    static final int RIGHT = 0;
    static final int DOWN = 2;

    static int n;
    static int m;
    static int[][][] map;       // (i, j) 번째 칸의 {거울 개수, 방문 방향}
    static int[] start = null;
    static int[] end = null;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        m = Integer.parseInt(input[0]);
        n = Integer.parseInt(input[1]);
        map = new int[n][m][2];

        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                char token = line[j];

                if (token == '*') {
                    map[i][j][0] = WALL;
                    continue;
                }

                if (token == '.') {
                    map[i][j][0] = Integer.MAX_VALUE;
                    continue;
                }

                if (start == null) {
                    start = new int[2];
                    start[I] = i;
                    start[J] = j;
                    map[i][j][0] = Integer.MAX_VALUE;
                    continue;
                }

                end = new int[2];
                end[I] = i;
                end[J] = j;
                map[i][j][0] = Integer.MAX_VALUE;
            }
        }

        int result = bfs();
        System.out.println(result);
    }

    static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        init(queue);


        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            if (node[I] == end[I] && node[J] == end[J]) {
                continue;
            }

            for (int d = 0; d < deltas[node[DIRECTION]].length; d++) {
                int ni = node[I] + deltas[node[DIRECTION]][d][I];
                int nj = node[J] + deltas[node[DIRECTION]][d][J];
                
                if (ni < 0 || ni >= n || nj < 0 || nj >= m) {
                    continue;
                }

                if (map[ni][nj][0] == WALL) {
                    continue;
                }

                int nextDir = (node[DIRECTION] + d) % 4;
                if (d != 0 && (node[DIRECTION] == RIGHT || node[DIRECTION] == DOWN)) {
                    nextDir = (nextDir + 1) % 4;
                }

                // 어떤 방향이든 더 적은 거울로 온 적 있거나
                // 동-서, 남-북끼리 같은 개수의 거울로 온 적 있는 경우
                int nextMirror = node[MIRROR] + (d == 0 ? 0 : 1);
                if (map[ni][nj][0] < nextMirror || 
                    (map[ni][nj][0] == nextMirror && map[ni][nj][1] / 2 == nextDir / 2)) {
                    continue;
                }

                map[ni][nj][0] = nextMirror;
                map[ni][nj][1] = nextDir;
                int[] next = new int[]{ni, nj, nextMirror, nextDir};
                queue.add(next);
            }
        }
    
        return map[end[I]][end[J]][0] - 1;
    }

    // 시작 지점에서 동서남북으로 진행
    static void init(Queue<int[]> queue) {
        for (int dir = 0; dir < 4; dir++) {
            int ni = start[I] + deltas[dir][0][I];
            int nj = start[J] + deltas[dir][0][J];

            if (ni < 0 || ni >= n || nj < 0 || nj >= m) {
                continue;
            }

            if (map[ni][nj][0] == WALL) {
                continue;
            }

            map[ni][nj][0] = 1;
            map[ni][nj][1] = dir;
            queue.add(new int[]{ni, nj, 1, dir});
        }
    }
}
