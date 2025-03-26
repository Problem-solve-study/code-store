// 12388KB	80ms
import java.io.*;
import java.util.*;

/**
 * 출발지부터 도착지까지의 최소 이동 횟수를 찾는 BFS 문제
 * 방향도 고려를 해야하므로 visited 배열을 3차원으로 선언하여 해당 방향으로 방문한 적이 있는지를 체크한다.
 */
public class Main {
    static int n,m,map[][];
    static boolean[][][] visited;
    static int sy,sx,sd;
    static int ty,tx,td;
    // 동 남 서 북
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    static class Node {
        int y, x, dir, cnt;
        Node(int y, int x, int dir, int cnt) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 입력은 (1,1) 부터 좌표가 시작되므로 y,x 에 -1씩 한다.
        // 90도씩 회전하므로 동서남북 -> 동남서북으로 순서를 바꿔 저장한다.
        st = new StringTokenizer(br.readLine());
        sy = Integer.parseInt(st.nextToken()) - 1;
        sx = Integer.parseInt(st.nextToken()) - 1;
        sd = Integer.parseInt(st.nextToken()) - 1;
        sd = (sd == 1) ? 2 : (sd == 2) ? 1 : sd;
        st = new StringTokenizer(br.readLine());
        ty = Integer.parseInt(st.nextToken()) - 1;
        tx = Integer.parseInt(st.nextToken()) - 1;
        td = Integer.parseInt(st.nextToken()) - 1;
        td = (td == 1) ? 2 : (td == 2) ? 1 : td;

        visited = new boolean[n][m][4];
        System.out.print(bfs());

    }

    static int bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(sy, sx, sd, 0));
        visited[sy][sx][sd] = true;
        
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (now.y == ty && now.x == tx && now.dir == td) {
                return now.cnt;
            }
            
            // 현재 방향 그대로 k 만큼 이동
            for (int k=1; k<=3; k++) {
                int ny = now.y + k * dy[now.dir];
                int nx = now.x + k * dx[now.dir];
                if (ny>=0 && ny<n && nx>=0 && nx<m && !visited[ny][nx][now.dir]) {
                    // k만큼 이동하는 경로 중 map이 1 인 경우, 그 다음칸으로 이동할 수 없으므로 바로 종료한다.
                    if (map[ny][nx] == 1) break; 
                    visited[ny][nx][now.dir] = true;
                    queue.add(new Node(ny, nx, now.dir, now.cnt + 1));
                }
            }

            // 90도 회전(시계방향)
            if (!visited[now.y][now.x][(now.dir + 1) % 4]) {
                visited[now.y][now.x][(now.dir + 1) % 4] = true;
                queue.add(new Node(now.y, now.x, (now.dir + 1) % 4, now.cnt + 1));
            }
            // 90도 회전(반시계방향)
            // 자바는 음수 나머지 연산에서 음수를 반환하므로 (now.dir - 1) % 4 가 아니라 (now.dir - 1 + 4) % 4
            if (!visited[now.y][now.x][(now.dir + 3) % 4]) {
                visited[now.y][now.x][(now.dir + 3) % 4] = true;
                queue.add(new Node(now.y, now.x, (now.dir + 3) % 4, now.cnt + 1));
            }
        }

        return 0;
    }
}
