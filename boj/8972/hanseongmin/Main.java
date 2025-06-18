import java.io.*;
import java.util.*;

/*
15644KB, 184ms

진짜 오랜만에 풀어보는거 같은 구현, 시뮬레이션 문제
구현, 시뮬도 오랜만에 푸니까 재밌네요.
 */

public class Main {
    static int R, C;
    static int[] dr = {1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dc = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static int[][] arduinoCnt;
    static Unit jongSoo;
    static Queue<Unit> arduinos = new ArrayDeque<>();

    static class Unit {
        int r, c;
        boolean isArduino;
        boolean isActive;

        public Unit(int r, int c, boolean isArduino) {
            this.r = r;
            this.c = c;
            this.isArduino = isArduino;
            isActive = true;
        }

        void move(int n) {
            r += dr[n];
            c += dc[n];

            for (Unit u : arduinos) {
                if (u.isArduino && u.r == r && u.c == c) {
                    isActive = false;
                }
            }
        }

        void move(Unit jongSoo) {
            int tr = r;
            int tc = c;
            int v = Math.abs(r - jongSoo.r) + Math.abs(c - jongSoo.c);

            for (int i = 0; i < 9; i++) {
                int curR = r + dr[i];
                int curC = c + dc[i];
                int curV = Math.abs(curR - jongSoo.r) + Math.abs(curC - jongSoo.c);
                if (curV < v) {
                    v = curV;
                    tr = curR;
                    tc = curC;
                }
            }

            arduinoCnt[r][c]--;
            r = tr;
            c = tc;
            arduinoCnt[r][c]++;

            if (r == jongSoo.r && c == jongSoo.c) {
                jongSoo.isActive = false;
            }

        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arduinoCnt = new int[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                char curChar = str.charAt(j);
                if (curChar == 'I') {
                    jongSoo = new Unit(i, j, false);
                } else if (curChar == 'R') {
                    arduinos.add(new Unit(i, j, true));
                    arduinoCnt[i][j]++;
                }
            }
        }

        String ans = "";
        char[] order = br.readLine().toCharArray();
        int moveCnt = 0;
        for (char o : order) {
            int curO = o - '0' - 1;

            //1. 먼저, 종수가 이동한다.
            jongSoo.move(curO); moveCnt++;
            //2. 종수가 미친 아두이노가 있는 칸으로 이동했다면 게임에서 진다.
            if (!jongSoo.isActive) break;
            //3. 미친 아두이노들이 이동한다.
            for (Unit u : arduinos) {
                u.move(jongSoo);
            }
            //4. 미친 아두이노가 종수의 아두이노가 있는 곳으로 움직였다면 진다.
            if (!jongSoo.isActive) break;
            //5. 미친 아두이노들이 같은 칸에 있으면 그 칸에 있는 아두이노들이 모두 파괴된다.
            for (Unit u : arduinos) {
                if (arduinoCnt[u.r][u.c] > 1) u.isActive = false;
            }

            int initSize = arduinos.size();
            for (int i = 0; i < initSize; i++) {
                Unit u = arduinos.remove();
                if (u.isActive) {
                    arduinos.add(u);
                } else {
                    arduinoCnt[u.r][u.c] = 0;
                }
            }
        }

        if (!jongSoo.isActive) {
            ans = String.format("kraj %d", moveCnt);
        } else {
            StringBuilder sb = new StringBuilder();
            char[][] map = new char[R][C];
            map[jongSoo.r][jongSoo.c] = 'I';
            for (Unit u : arduinos) {
                map[u.r][u.c] = 'R';
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] != 'I' && map[i][j] != 'R') map[i][j] = '.';
                }
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    sb.append(map[i][j]);
                }
                sb.append('\n');
            }
            ans = sb.toString();
        }

        System.out.print(ans);

    }
}
