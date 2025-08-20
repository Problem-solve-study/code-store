/**
 * 	102340kb  760ms
 *  큐로 한시간동안 확산시킬 수 있는거 1번 확산 - 2번 확산
 *  visited 배열 사용해서 해당 시간내에 충돌되면 3번 바이러스로 변경
 */

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] v = new int[N][M];
        Deque<int[]> fst = new ArrayDeque<>();
        Deque<int[]> scd = new ArrayDeque<>();

        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};

        int first = 0;
        int second = 0;
        int third = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                v[i][j] = Integer.parseInt(st.nextToken());
                if (v[i][j] == 1) {
                    first++;
                    fst.offerLast(new int[]{i, j});
                } else if (v[i][j] == 2) {
                    second++;
                    scd.offerLast(new int[]{i, j});
                }
            }
        }

        boolean[][] visited = new boolean[N][M];

        // 두 바이러스 큐가 모두 빌 때까지 반복
        while (!fst.isEmpty() || !scd.isEmpty()) {

            // 1번 바이러스 확산
            int fstSize = fst.size();
            for (int i = 0; i < fstSize; i++) {
                int[] virus = fst.pollFirst();
                visited[virus[0]][virus[1]] = true;
                // 3번 바이러스는 X
                if (v[virus[0]][virus[1]] == 3) continue;

                for (int d = 0; d < 4; d++) {
                    int nr = virus[0] + dr[d];
                    int nc = virus[1] + dc[d];

                    // 감염 가능 마을X
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M || v[nr][nc] != 0 || visited[nr][nc]) continue;
                    
                    // 빈 공간이라면 1번 바이러스로 감염 & 다음 확산을 위해 큐에 추가
                    v[nr][nc] = 1;   
                    fst.offerLast(new int[]{nr, nc});
                    first++;
                }
            }

            // 2번 바이러스 확산
            int scdSize = scd.size();
            for (int i = 0; i < scdSize; i++) {
                int[] virus = scd.pollFirst();
                visited[virus[0]][virus[1]] = true;
                for (int d = 0; d < 4; d++) {
                    int nr = virus[0] + dr[d];
                    int nc = virus[1] + dc[d];

                    // 감염 가능 마을X
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M || v[nr][nc] == 2 || v[nr][nc] == 3 || v[nr][nc] == -1 || visited[nr][nc]) continue;
                    
                    // 빈 공간이라면 2번 바이러스로 감염 & 다음 확산을 위해 큐에 추가
                    if (v[nr][nc] == 0) {
                        v[nr][nc] = 2;
                        scd.offerLast(new int[]{nr, nc});
                        second++;
                    }
                    // 1번 바이러스를 만나면 3번(충돌) 바이러스로 변이
                    else if (v[nr][nc] == 1) {
                        v[nr][nc] = 3;
                        first--;
                        third++;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(first).append(' ').append(second).append(' ').append(third);
        System.out.println(sb);
    }
}