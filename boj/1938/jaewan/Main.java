/*
 * 기차 놀이로 처음위치에서 최종위치로 옮기는 프로그램
 * 4 <= N <= 50
 * > U, D, L, R, T 5가지.
 * 이동이 불가능하면 0 출력.
 * 
 * BFS, visit[type][y][x] 사용,
 * type == 0 은 가로, 1은 세로
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][][] visit;
    static ArrayDeque<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visit = new boolean[2][N][N];
        int startY = -1, startX = -1, startType = -1, endY = -1, endX = -1, endType = -1;
        for (int i = 0; i < N; i++) {
            String inStr = br.readLine();
            for (int j = 0; j < N; j++) {
                char t = inStr.charAt(j);
                switch (t) {
                    case 'B':
                        if (startY == i && startType == -1)
                            startType = 0;
                        if (startX == j && startType == -1)
                            startType = 1;
                        if (startY == -1) {
                            startY = i;
                            startX = j;
                        } else {
                            startY += i;
                            startX += j;
                        }
                        break;
                    case 'E':
                        if (endY == i && endType == -1)
                            endType = 0;
                        if (endX == j && endType == -1)
                            endType = 1;
                        if (endY == -1) {
                            endY = i;
                            endX = j;
                        } else {
                            endY += i;
                            endX += j;
                        }
                        break;
                    default:
                        map[i][j] = t - '0';
                }
            }
        }
        startY /= 3;
        startX /= 3;
        endY /= 3;
        endX /= 3;

        // 3차원 BFS
        int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
        visit[startType][startY][startX] = true;
        queue.offer(new int[] { startType, startY, startX });
        int res = 0;
        for (int time = 0;; time++) {
            int size = queue.size();
            if (size == 0 || res > 0)
                break;
            while (size-- > 0) {
                int[] cur = queue.poll();
                if (cur[0] == endType && cur[1] == endY && cur[2] == endX) {
                    res = time;
                    break;
                }

                // 같은 타입으로 4방 이동
                for (int i = 0; i < 4; i++) {
                    int ny = cur[1] + dy[i], nx = cur[2] + dx[i];
                    if (!isValid(cur[0], ny, nx))
                        continue;
                    visit[cur[0]][ny][nx] = true;
                    queue.offer(new int[] { cur[0], ny, nx });
                }

                // 회전하는 경우
                if (!isValid(2, cur[1], cur[2]))
                    continue;
                if (visit[cur[0] ^ 1][cur[1]][cur[2]])
                    continue;
                visit[cur[0] ^ 1][cur[1]][cur[2]] = true;
                queue.offer(new int[] { cur[0] ^ 1, cur[1], cur[2] });
            }
        }
        System.out.println(res);
    }

    // type=0은 가로, 1은 세로, 해당 위치로 이동이 가능한지
    static boolean isValid(int type, int y, int x) {
        // 가로
        if (type == 0) {
            if (y < 0 || y >= N || x - 1 < 0 || x + 1 >= N)
                return false;
            if (map[y][x - 1] == 1 || map[y][x] == 1 || map[y][x + 1] == 1)
                return false;
            return !visit[type][y][x];
        }
        // 세로
        else if (type == 1) {
            if (x < 0 || x >= N || y - 1 < 0 || y + 1 >= N)
                return false;
            if (map[y - 1][x] == 1 || map[y][x] == 1 || map[y + 1][x] == 1)
                return false;
            return !visit[type][y][x];
        }
        // 회전
        else {
            if (x - 1 < 0 || x + 1 >= N || y - 1 < 0 || y + 1 >= N)
                return false;
            if (map[y - 1][x] == 1 || map[y + 1][x] == 1 || map[y][x - 1] == 1 || map[y][x + 1] == 1
                    || map[y - 1][x - 1] == 1 || map[y - 1][x + 1] == 1 || map[y + 1][x - 1] == 1
                    || map[y + 1][x + 1] == 1)
                return false;
            return true;
        }
    }
}
