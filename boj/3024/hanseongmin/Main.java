import java.io.*;

/*
11492KB, 68ms

예전에도 이런 틱택토 판별 문제가 한 번 나온 적이 있었는데 또 나왔다.
틱택토는 인접한 3칸까지만 보면 되므로 그냥 모든 좌표에 대해 인접한 칸을 보며 승리한 사람이 있는지를 확인한다.
 */

public class Main {
    static int N;
    static int[][][] d = {
            {{0, -1}, {0, 1}},
            {{-1, 0}, {1, 0}},
            {{1, -1}, {-1, 1}},
            {{-1, -1}, {1, 1}}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        String res = null;
        //모든 좌표에 대해
        for (int i = 0; i < N && res == null; i++) {
            for (int j = 0; j < N && res == null; j++) {
                for (int[][] arr : d) {
                    int i0 = i + arr[0][0]; int j0 = j + arr[0][1];
                    int i1 = i + arr[1][0]; int j1 = j + arr[1][1];
                    //.이 3개 연속으로 있는 경우는 누군가가 승리한 것이 아니므로 넘어감
                    if (!bc(i0, j0) || !bc(i1, j1) || map[i][j] == '.') continue;

                    //인접한 칸 모두가 같은 칸이라면 승리한 사람이 발생한 것.
                    if (map[i0][j0] == map[i][j] && map[i][j] == map[i1][j1]) {
                        res = String.valueOf(map[i][j]);
                        break;
                    }
                }
            }
        }

        System.out.print(res == null ? "ongoing" : res);
    }

    static boolean bc(int r, int c) {
        return (0 <= r && r < N) && (0 <= c && c < N);
    }
}
