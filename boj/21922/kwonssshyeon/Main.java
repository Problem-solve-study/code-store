
// 317660KB	1004ms
import java.io.*;
import java.util.*;

/**
 * 
 */
public class Main {
    static int n, m, map[][];
    // dy, dx 시계 방향으로 배치
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { -1, 0, 1, 0 };
    static boolean[][] visited;
    static Queue<int[]> queue = new ArrayDeque<>();
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    addStartNode(i, j);
                }
            }
        }

        bfs();
    }

    static void addStartNode(int sy, int sx) {
        queue.add(new int[] { sy, sx, 0 });
        queue.add(new int[] { sy, sx, 1 });
        queue.add(new int[] { sy, sx, 2 });
        queue.add(new int[] { sy, sx, 3 });

        visited[sy][sx] = true;
        count++;
    }

    static void bfs() {
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int y = now[0], x = now[1], dir = now[2];

            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if (ny < 0 || ny >= n || nx < 0 || nx >= m)
                continue;

            switch (map[ny][nx]) {
                case 0:
                    queue.add(new int[] { ny, nx, dir });
                    if (!visited[ny][nx]) {
                        visited[ny][nx] = true;
                        count++;
                    }
                    break;
                case 1:
                    if (dx[dir] == 0) {
                        queue.add(new int[] { ny, nx, dir });
                    }
                    if (!visited[ny][nx]) {
                        visited[ny][nx] = true;
                        count++;
                    }
                    break;
                case 2:
                    if (dy[dir] == 0) {
                        queue.add(new int[] { ny, nx, dir });
                    }
                    if (!visited[ny][nx]) {
                        visited[ny][nx] = true;
                        count++;
                    }
                    break;
                case 3:
                    if (dx[dir] == 0)
                        queue.add(new int[] { ny, nx, (dir + 1) % 4 });
                    else
                        queue.add(new int[] { ny, nx, (dir + 3) % 4 });
                    if (!visited[ny][nx]) {
                        visited[ny][nx] = true;
                        count++;
                    }
                    break;
                case 4:
                    if (dy[dir] == 0)
                        queue.add(new int[] { ny, nx, (dir + 1) % 4 });
                    else
                        queue.add(new int[] { ny, nx, (dir + 3) % 4 });
                    if (!visited[ny][nx]) {
                        visited[ny][nx] = true;
                        count++;
                    }
                    break;
            }
        }
        System.out.println(count);
    }
}
