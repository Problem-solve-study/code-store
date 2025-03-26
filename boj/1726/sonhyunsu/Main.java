//제출번호: 92026863
//메모리:   13036 KB
//실행시간: 80 ms
import java.io.*;
import java.util.*;

//방향도 비용이 있는 bfs 문제
//이동 조건에 맞게 구현만 하면 됨
//문제에서 주는 동서남북 값과 맵에서 회전할 때 사용하는 인덱스가 다르므로 변환이 필요함
//연속으로 0이 있어야 최대 3칸까지 갈 수 있다는 점을 생각하면서 풀 것
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();
        
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = nextInt();
            }
        }

        int[] start = {nextInt() - 1, nextInt() - 1, convertDir(nextInt())};
        int[] end = {nextInt() - 1, nextInt() - 1, convertDir(nextInt())};
        int[][][] dp = new int[4][n][m];

        Queue<int[]> q = new ArrayDeque<>();

        //처음을 1로 두어 값을 visited 여부로 삼음
        dp[start[2]][start[0]][start[1]] = 1;
        q.add(start);
        while (!q.isEmpty()) {
            int[] item = q.poll();

            int x = item[0];
            int y = item[1];
            int dir = item[2];

            //반시계방향으로 90도 회전한 방향을 살펴봄
            int pDir = (dir + 3) % 4;
            //만약 아직 방문하지 않은 위치면 갱신
            if (dp[pDir][x][y] == 0) {
                dp[pDir][x][y] = dp[dir][x][y] + 1;
                q.add(new int[]{x, y, pDir});
            }

            //시계방향으로 90도 회전한 방향을 살펴봄
            int nDir = (dir + 1) % 4;
            //만약 아직 방문하지 않은 위치면 갱신
            if (dp[nDir][x][y] == 0) {
                dp[nDir][x][y] = dp[dir][x][y] + 1;
                q.add(new int[]{x, y, nDir});
            }

            //dir 방향으로 1칸부터 3칸까지 이동해봄
            for (int i = 1; i < 4; i++) {
                int nx = x + dx[dir] * i;
                int ny = y + dy[dir] * i;

                //만약 이동한 위치가 맵 안이면서
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    //이동할 수 있는 위치이고
                    if (map[nx][ny] == 0) {
                        //아직 방문하지 않았다면 갱신
                        if (dp[dir][nx][ny] == 0) {
                            dp[dir][nx][ny] = dp[dir][x][y] + 1;
                            q.add(new int[]{nx, ny, dir});
                        }
                    } else {
                        //만약 이동할 수 없는 위치면
                        //그 다음 칸은 이동해 볼 필요 없음
                        break;
                    }
                }
            }
        }

        //처음에 1부터 시작했기 때문에 -1 해줌
        System.out.print(dp[end[2]][end[0]][end[1]] - 1);
    }

    static int convertDir(int dir) {
        switch(dir) {
            case 1: return 0; //동
            case 2: return 2; //서
            case 3: return 1; //남
            case 4: return 3; //북
            default: return -1; //안 씀
        }
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}