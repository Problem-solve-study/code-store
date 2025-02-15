import java.io.*;
import java.util.*;

/*
59420KB, 2716ms

가능한 모든 전선의 조합을 확인해보는 방법으로 풀었다.
자세한 흐름은 아래 코드의 주석으로 설명
 */

public class Solution {
    static class Core {
        int r;
        int c;
        ArrayList<Integer> directions;

        Core(int r, int c) {
            this.r = r;
            this.c = c;
            directions = new ArrayList<>();
        }
    }

    static int n;
    static int connectedCore;
    static int sumOfLength;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static ArrayList<Core> cores;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());
            connectedCore = 0;
            sumOfLength = Integer.MAX_VALUE;
            cores = new ArrayList<>();
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        //가장자리에 위치한 코어는 고려할 필요 없음
                        if (i == 0 || i == n - 1 || j == 0 || j == n - 1) continue;
                        cores.add(new Core(i, j));
                    }
                }
            }

            directionUpdate();
            findAns(0, 0, 0);
            sb.append(String.format("#%d %d\n", t, sumOfLength == Integer.MAX_VALUE ? 0 : sumOfLength));
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static void directionUpdate() {
        //코어가 연결할 수 있는 전선을 미리 탐색
        for (Core core : cores) {
            for (int i = 0; i < dr.length; i++) {
                boolean connectable = true;
                for (int j = 1; j <= n; j++) {
                    int nextR = core.r + dr[i] * j;
                    int nextC = core.c + dc[i] * j;

                    if (!boundaryCheck(nextR, nextC))
                        break;
                    //다른 코어에 막혀서 해당 방향으로 전선을 연결하지 못하는 경우
                    if (map[nextR][nextC] != 0) {
                        connectable = false;
                        break;
                    }
                }

                if (connectable) {
                    core.directions.add(i);
                }
            }
        }
    }

    static void findAns(int selectedCore, int coreCnt, int sum) {
        if (selectedCore == cores.size()) {
            //연결된 코어의 개수가 더 많다면 전선 길이 갱신
            if (coreCnt > connectedCore) {
                sumOfLength = sum;
                connectedCore = coreCnt;
            } else if (coreCnt == connectedCore) {
                //코어의 개수가 같다면 짧은 전선 길이를 선택
                sumOfLength = Math.min(sumOfLength, sum);
            }
            return;
        }

        Core core = cores.get(selectedCore);
        if (core.directions.isEmpty()) {
            findAns(selectedCore + 1, coreCnt, sum);
        } else {
            for (int direction : core.directions) {
                //전선을 택할 수 있는지 판별
                int wireLength = selectedWire(core, direction);
                if (wireLength > 0) {
                    //전선을 택할 수 있다면 택하고 재귀
                    findAns(selectedCore + 1, coreCnt + 1, sum + wireLength);
                    rollback(core, direction);
                }
                //선택할 수 있다 하더라도 안하는 것이 최선일 수 있으므로 탐색
                findAns(selectedCore + 1, coreCnt, sum);
            }
        }
    }

    static int selectedWire(Core core, int direction) {
        //map에 전선을 놓으며 해당 방향으로 전선이 갈 수 없다면 -1, 갈 수 있다면 전선의 길이를 반환
        //먼저 전선을 선택할 수 있는지를 판별
        for (int i = 1; i <= n; i++) {
            int nextR = core.r + dr[direction] * i;
            int nextC = core.c + dc[direction] * i;

            if (boundaryCheck(nextR, nextC) && map[nextR][nextC] == 2) {
                return -1;
            }
        }

        int length = 0;
        for (int i = 1; i <= n; i++) {
            int nextR = core.r + dr[direction] * i;
            int nextC = core.c + dc[direction] * i;

            if (boundaryCheck(nextR, nextC)) {
                length++;
                map[nextR][nextC] = 2;
            } else {
                break;
            }
        }

        return length;
    }

    static void rollback(Core core, int direction) {
        //맵에 깔아둔 전선 회수
        for (int i = 1; i <= n; i++) {
            int nextR = core.r + dr[direction] * i;
            int nextC = core.c + dc[direction] * i;

            if (boundaryCheck(nextR, nextC)) {
                map[nextR][nextC] = 0;
            } else {
                break;
            }
        }
    }

    static boolean boundaryCheck(int r, int c) {
        return (0 <= r && r < n) && (0 <= c && c < n);
    }
}