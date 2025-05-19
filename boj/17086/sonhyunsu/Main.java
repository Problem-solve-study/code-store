//제출번호: 94318204
//메모리:   12740 KB
//실행시간: 76 ms
import java.io.*;
import java.util.*;

//아기 상어 위치를 기준으로 bfs 돌리면 됨
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException { 
        int n = nextInt();
        int m = nextInt();

        int[][] map = new int[n][m];
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int num = nextInt();
                map[i][j] = num;
                //아기 상어가 있으면 위치를 큐에 넣음
                if (num == 1) {
                    q.add(new int[]{i, j});
                }
            }
        }

        int res = 0;
        int[] dx = {1, 0, -1, 0, 1, 1, -1, -1}, dy = {0, 1, 0, -1, 1, -1, 1, -1};
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int x = pos[0];
            int y = pos[1];

            //가장 멀리 있는 위치가 큐에서 가장 마지막에 뽑힘
            res = map[x][y];

            //8방 탐색
            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                //다음 위치가 맵 안에 있고, 아직 안전 거리를 계산하지 않은 위치면
                if (0 <= nx && nx < n && 0 <= ny && ny < m && map[nx][ny] == 0) {
                    map[nx][ny] = map[x][y] + 1; //안전 거리를 계산함
                    q.add(new int[]{nx, ny});
                }
            }
        }

        //1부터 시작했기 때문에 마지막에 1 빼줌
        System.out.print(res - 1);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}