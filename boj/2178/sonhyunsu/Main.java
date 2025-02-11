//제출번호: 89894105
//메모리:   12056 KB
//실행시간: 84 ms
import java.io.*;
import java.util.*;

//4방향 bfs 탐색하면 쉽게 풀 수 있음
//출력할 결과물은 이동 횟수가 아닌 지나가야 하는 최소 칸 수임을 주의하자
public class Main {

    static boolean[][] maze = new boolean[101][101];
    static int[][] d = new int[101][101];
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static Queue<Integer> queue = new ArrayDeque<>();
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                maze[i][j] = line.charAt(j) == '1';
            }
        }

        queue.add(0);
        d[0][0] = 1; //시작점도 칸이므로 1부터 시작
        while(!queue.isEmpty()) {
            int x = queue.peek() / 1000;
            int y = queue.poll() % 1000;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                //미로 범위 안이면서, 이동할 수 있고, 아직 방문하지 않았으면 업데이트한다.
                if (isInRange(nx, ny) && maze[nx][ny] && d[nx][ny] == 0) {
                    d[nx][ny] = d[x][y] + 1;
                    queue.add(nx * 1000 + ny);
                }
            }
        }

        //미로 범위를 0,0 ~ n-1,m-1로 설정했으므로 d[n-1][m-1]을 출력
        System.out.print(d[n - 1][m - 1]);
    }

    static boolean isInRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}