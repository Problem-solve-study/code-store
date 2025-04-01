//제출번호: 92355779
//메모리:   12692 KB
//실행시간: 88 ms
import java.io.*;

//그래프 탐색으로 뭉친 병사만 잘 세어주면 풀 수 있는 문제
//가로가 먼저 입력되는 것에 주의할 것
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static boolean[][] map, visited;
    static int n, m;
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        m = nextInt();
        n = nextInt();

        map = new boolean[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = nextString();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) == 'W';
            }
        }

        int white = 0, blue = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //아직 방문하지 않은 병사면 새로운 병사 집단임
                if (!visited[i][j]) {
                    int cnt = dfs(i, j);

                    //만약 아군 병사면 white에, 적군 병사면 blue에 저장
                    if (map[i][j]) {
                        white += cnt * cnt;
                    } else {
                        blue += cnt * cnt;
                    }
                }
            }
        }

        System.out.printf("%d %d", white, blue);
    }

    static int dfs(int x, int y) {
        int res = 1;
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            //맵 안에 있고, x,y랑 같은 병사면서 아직 방문하지 않았다면
            if (0 <= nx && nx < n && 0 <= ny && ny < m && map[x][y] == map[nx][ny] && !visited[nx][ny]) {
                res += dfs(nx, ny); //방문해서 병사 수를 셈
            }
        }

        return res;
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