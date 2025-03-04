import java.util.Scanner;

public class Main {

    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = { 1, 0, -1, 0 }, dy = { 0, 1, 0, -1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        map = new int[n][n];
        visited = new boolean[n][n];

        int limit = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                limit = Math.max(limit, map[i][j]);
            }
        }

        int res = 0;
        // 안전지수를 0부터 map 값의 최대까지 확인해본다.
        for (int i = 0; i <= limit; i++) {
            // 영역과 방문여부를 초기화한다.
            int areaCount = 0;
            resetVisited();

            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    // 알레르기 반응이 나타나지 않는 위치면서 아직 방문하지 않았다면
                    if (map[x][y] > i && !visited[x][y]) {
                        // 방문하고 영역의 개수를 1 증가한다.
                        dfs(x, y, i);
                        areaCount++;
                    }
                }
            }

            res = Math.max(res, areaCount);
        }

        System.out.print(res);

        sc.close();
    }

    static void dfs(int x, int y, int guideline) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // map 범위 안이면서 알레르기 반응이 나타나지 않고 아직 방문하지 않았다면 방문한다.
            if (isInRange(nx, ny) && map[nx][ny] > guideline && !visited[nx][ny]) {
                dfs(nx, ny, guideline);
            }
        }
    }

    static void resetVisited() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
            }
        }
    }

    static boolean isInRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

}
