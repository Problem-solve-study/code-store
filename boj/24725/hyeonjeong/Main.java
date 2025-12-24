// 12712KB 100ms

import java.util.*;
import java.io.*;

/**
 * 모든 좌표값에 대해 8방향 DFS로 풀었습니다.
 */
class Main {
    public static void main(String[] args) throws IOException {
        char[][] alphaSets = { { 'E', 'I'}, { 'N', 'S' }, { 'F', 'T' }, { 'P', 'J' }};
        int[][] deltas = {{ 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 }};
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }
        
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 모든 좌표값에 대해, 특정 방향으로 4칸 갈 수 있는지 탐색
                for (int di = 0; di < deltas.length; di++) {
                    for (int depth = 0; depth < alphaSets.length; depth++) {
                        int currI = i + deltas[di][0] * depth;
                        int currJ = j + deltas[di][1] * depth;
                        
                        if (currI < 0 || currI >= n || currJ < 0 || currJ >= m) {
                            break;
                        }
                        
                        if (alphaSets[depth][0] != (map[currI][currJ]) && alphaSets[depth][1] != map[currI][currJ]) {
                            break;
                        }

                        if (depth == alphaSets.length - 1) {
                            count++;
                        }
                    }
                }
            }
        }

        System.out.println(count);
    }
}
