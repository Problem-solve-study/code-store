// 292380KB	428ms
import java.io.*;
import java.util.*;

/**
 * 처음에 트리의 지름 찾는 것 처럼 컴포넌트마다 BFS 2번씩 해서 찾으려 했는데 틀림
 * 그래프는 트리가 아니어서 어떤 한 노드까지의 경로가 1개임을 보장할 수 없다.
 * LLL
 * LWL
 * LLL => 이런 경우 ..
 * 
 * 그래서 그냥 모든 정점에 대해 BFS를 돌림
 * 근데 visited 배열을 초기화하는게 오래 걸릴 것 같아서 (매번 50*50)
 * int 형으로 선언하고 한 턴의 탐색을 나타내는 marker 값이 같은지를 이용함.
 */
public class Main {
    static int n,m;
    static char[][] map;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] visited;
    static int answer;
    static Queue<int[]> queue = new LinkedList<>();

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
        int marker = 0;
        visited = new int[n][m];
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (map[i][j] == 'L') {
                    bfs(i, j, marker++);
                }
            }
        }
        System.out.print(answer);
    }
    

    static void bfs(int y, int x, int marker) {
        int cost = 0;
        queue.add(new int[] {y, x, cost});
        visited[y][x] = marker;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            y = node[0]; x = node[1]; cost = node[2];

            for (int i=0; i<4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny >= 0 && ny < n && nx >= 0 && nx < m) {
                    if (visited[ny][nx] != marker && map[ny][nx] == 'L') {
                        visited[ny][nx] = marker;
                        queue.add(new int[] {ny,nx, cost + 1});
                    }
                }
            }
        }
        answer = Math.max(answer, cost);
    }
}