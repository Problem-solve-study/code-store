import java.io.*;
import java.util.*;

/*
 * 94616KB, 616ms
 * 
 * 문제에 주어진대로 다익스트라 돌리기
 * 평범하게 그래프로 주어지는게 아니라서 구현의 느낌도 있음
 * 
 * 맨 처음에 BFS로 풀다가 1틀
 * -> 아 이거 가중치가 달라서 다익스트라로 해야겠구나
 * -> 힙 안썼다가 시초
 * -> 이후 정답
 */

public class Main {
    static int R, C;
    static char[][] map;
    static int[][] dist;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        dist = new int[R][C];

        //입력받으면서 최초 위치 저장
        int sr = 0; int sc = 0;
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'T') {
                    sr = i;
                    sc = j;
                }
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        //최초 위치로부터 다익스트라 돌리기
        int ans = Integer.MAX_VALUE;
        PriorityQueue<int[]> h = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[2]));
        h.add(new int[] {sr, sc, 0}); dist[sr][sc] = 0;
        while (!h.isEmpty()) {
            int[] cur = h.remove();
            if (map[cur[0]][cur[1]] == 'E') {
                ans = Math.min(ans, cur[2]);
                continue;
            }
            if (dist[cur[0]][cur[1]] < cur[2]) continue;

            for (int i = 0; i < 4; i++) {
                int[] next = getNext(cur, i);
                int nr = next[0]; int nc = next[1];
                //절벽 바깥이거나, 구멍에 빠졌거나, 거리가 갱신된게 아니면 넘어감
                if (!bc(nr, nc) || map[nr][nc] == 'H' || dist[nr][nc] <= next[2]) continue;
                h.add(next); dist[nr][nc] = next[2];
            }
        }
        System.out.print(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static int[] getNext(int[] cur, int d) {
        //현재 위치에서 d방향으로 이동했을 때의 정보를 반환
        int r = cur[0]; int c = cur[1]; int time = cur[2];
        while (true) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            //다음 위치가 바위라면 다음으로 이동하지 않고 현재 위치를 반환
            if (bc(nr, nc) && map[nr][nc] == 'R') break;
            r = nr; c = nc;
            //미로 밖, 구멍으로 떨어졌거나, 출구에 도착했다면 이동한 위치를 반환
            if (!bc(nr, nc) || map[nr][nc] == 'H' || map[nr][nc] == 'E') break;

            if (map[r][c] != 'T') {
                time += Integer.parseInt(String.valueOf(map[r][c]));
            }
        }
        return new int[] {r, c, time};
    }

    static boolean bc(int r, int c) {
        return (0 <= r && r < R) && (0 <= c && c < C);
    }
}