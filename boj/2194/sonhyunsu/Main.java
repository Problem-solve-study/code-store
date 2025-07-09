//제출번호: 96126558
//메모리:   17456 KB
//실행시간: 116 ms
import java.io.*;
import java.util.*;

//기본적인 BFS, 하지만 1x1이 아닌 AxB인게 다름.
//이동할 공간에 장애물이 있는지 빠르게 판단하기 위해서 누적합 사용
//유닛이 맵 밖을 넘어가면 안 되니까 좌상단의 좌표 범위를 잘 제한해야 함
public class Main {
    public static void main(String[] args) throws IOException {
        int n = nextInt(), m = nextInt(), a = nextInt(), b = nextInt(), k = nextInt();
        
        int[][] board = new int[n+1][m+1];
        for (int i = 0; i < k; i++) {
            int x = nextInt(), y = nextInt();
            board[x][y] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                board[i][j] += board[i-1][j] + board[i][j-1] - board[i-1][j-1];
            }
        }

        n-=a-1;
        m-=b-1;
        
        int[][] dp = new int[n+1][m+1];
        boolean[][] visited = new boolean[n+1][m+1];
        Queue<int[]> q = new ArrayDeque<>();

        int sx = nextInt(), sy = nextInt();
        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

        dp[sx][sy] = 1;
        visited[sx][sy] = true;
        q.add(new int[]{sx, sy});

        while (!q.isEmpty()) {
            int[] pos = q.poll();

            int x = pos[0], y = pos[1], next = dp[x][y] + 1;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (isInRange(nx, ny, n, m) && !visited[nx][ny] && !existsObstacle(board, nx, ny, a, b)) {
                    dp[nx][ny] = next;
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        System.out.print(dp[nextInt()][nextInt()] - 1);
    }
    
    static boolean isInRange(int x, int y, int n, int m) {
        return 0 < x && x <= n && 0 < y && y <= m;
    }

    static boolean existsObstacle(int[][] board, int x, int y, int a, int b) {
        int maxX = x+a-1, maxY = y+b-1, minX = x-1, minY = y-1;
        return board[maxX][maxY] - board[minX][maxY] - board[maxX][minY] + board[minX][minY] > 0; 
    }

    static int nextInt() throws IOException {
        int n = System.in.read() & 15, c;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}