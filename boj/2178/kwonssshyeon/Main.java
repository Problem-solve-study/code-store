//12240KB	80ms
import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static char[][] map;
    static Queue<Node> queue = new LinkedList<>();
    static boolean[][] visited;
    static int[] dx = new int[] {1,0,0,-1};
    static int[] dy = new int[] {0,1,-1,0};
    static class Node {
        int y,x,cnt;
        Node (int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        for (int i=0; i<n; i++) {
            String line = br.readLine();
            for (int j=0; j<m; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        visited = new boolean[n][m];
        System.out.print(bfs());
    }


    static int bfs() {
        queue.add(new Node(0,0,1));
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.y == n-1 && node.x == m-1) {
                return node.cnt;
            }
            for (int i=0; i<4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];
                if (ny>=0 && ny<n && nx>=0 && nx<m && !visited[ny][nx] && map[ny][nx] == '1') {
                    queue.add(new Node(ny, nx, node.cnt+1));
                    visited[ny][nx] = true;
                }
            }
        }
        return 0;
    }
}