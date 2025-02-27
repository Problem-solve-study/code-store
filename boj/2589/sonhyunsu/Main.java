import java.io.*;
import java.util.*;

//처음에는 그래프 탐색 한 번으로 최단거리를 찾아야 하나 생각했는데
//N과 M이 50으로 매우 작아서 L인 지점마다 bfs를 돌려도 시간초과가 안 나겠다고 생각
//L인 지점이 출발점일 때 가장 먼 거리를 모두 구해보고 그 중 최댓값을 출력 
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer st = new StreamTokenizer(br);
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    static int n, m;
    static boolean[][] visited, map;
    static Queue<Data> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        map = new boolean[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = nextString();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) == 'L';
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //만약 L인 지점이라면
                if (map[i][j]) {

                    //방문 여부를 초기화 함
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < m; y++) {
                            visited[x][y] = false;
                        }
                    }

                    //현재 지점을 출발지점으로 했을 때의 가장 먼 거리를 찾아봄
                    int res = 0;
                    queue.add(new Data(i, j, 0));
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        Data item = queue.poll();
                        res = Math.max(res, item.val);

                        for (int d = 0; d < 4; d++) {
                            int nx = item.x + dx[d], ny = item.y + dy[d];
                            //범위 안에 있으면서, 땅이고, 아직 방문하지 않았다면
                            if (0 <= nx && nx < n && 0 <= ny && ny < m && map[nx][ny] && !visited[nx][ny]) {
                                visited[nx][ny] = true;
                                queue.add(new Data(nx, ny, item.val + 1));
                            }
                        }
                    }

                    //최댓값을 업데이트 함
                    max = Math.max(max, res);
                }
            }
        }

        System.out.print(max);
    }

    static class Data {
        int x;
        int y;
        int val;

        Data(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static String nextString() throws IOException {
        st.nextToken();
        return st.sval;
    }
}