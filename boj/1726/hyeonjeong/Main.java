// 	12396KB 80ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS
// visited 따로 쓰지 말고 에 숫자로 방문 확인
public class Main {
    static final int RIGHT = 0;
    static final int LEFT = 1;
    static final int DOWN = 2;
    static final int UP = 3;
    static final int I = 0;
    static final int J = 1;
    static final int DIR = 2;
    static final int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static int n;
    static int m;
    static int[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new int[n][m][4];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int a = Integer.parseInt(st.nextToken());
                visited[i][j][UP] = a;
                visited[i][j][DOWN] = a;
                visited[i][j][RIGHT] = a;
                visited[i][j][LEFT] = a;
            }
        }
        
        st = new StringTokenizer(br.readLine());
        int si = Integer.parseInt(st.nextToken()) - 1;
        int sj = Integer.parseInt(st.nextToken()) - 1;
        int sdir = Integer.parseInt(st.nextToken()) - 1;
        
        st = new StringTokenizer(br.readLine());
        int ei = Integer.parseInt(st.nextToken()) - 1;
        int ej = Integer.parseInt(st.nextToken()) - 1;
        int edir = Integer.parseInt(st.nextToken()) - 1;


        int level = 1;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{si, sj, sdir});
        visited[si][sj][sdir] = level++;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] node = queue.poll();

                if (node[I] == ei && node[J] == ej && node[DIR] == edir) {
                    System.out.println(level - 2);
                    return;
                }

                // 이동
                for (int length = 1; length < 4; length++) {
                    int ni = node[I] + deltas[node[DIR]][I] * length;
                    int nj = node[J] + deltas[node[DIR]][J] * length;

                    if (ni < 0 || ni >= n || nj < 0 || nj >= m) {
                        break;
                    }

                    // 이번 레벨의 탐색에서 방문한 적 있어도, 길이가 현재 경로가 더 길어서 최단일 수 있음
                    // -> 큐에 넣진 말고 탐색만 계속
                    if (visited[ni][nj][node[DIR]] == level) {
                        continue;
                    }

                    if (visited[ni][nj][node[DIR]] != 0) {
                        break;
                    }

                    queue.add(new int[]{ni, nj, node[DIR]});
                    visited[ni][nj][node[DIR]] = level;
                }

                // 회전
                for (int deltaDir = 0; deltaDir < 2; deltaDir++) {
                    int ndir = deltaDir + (node[DIR] < 2 ? 2 : 0);

                    // 회전은 늦게 방문하면 현재 경로가 최단일 수 없음
                    if (visited[node[I]][node[J]][ndir] != 0) {
                        continue;
                    }

                    queue.add(new int[]{node[I], node[J], ndir});
                    visited[node[I]][node[J]][ndir] = level;
                }
            }

            level++;
        }

        System.out.println(-1);
    }

    static void display() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf(
                    "[%d %d %d %d], ", 
                    visited[i][j][0],
                    visited[i][j][1],
                    visited[i][j][2],
                    visited[i][j][3]);
            }
            System.out.println();
        }
    }
}
