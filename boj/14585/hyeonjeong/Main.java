// 12404KB 76ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 위쪽과 오른쪽으로만 갈 수 있다 => 완탐
// map[i][j] = 왼쪽, 아래에서 오는 길 중 최대값
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int MAP_SIZE = 301;

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[MAP_SIZE][MAP_SIZE];
        for (int candy = 0; candy < n; candy++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            map[i][j] = 1;
        }

        for (int idx = 1; idx < MAP_SIZE; idx++) {
            if (map[idx][0] == 1) {
                map[idx][0] = Math.max(m - idx, 0);
            }
            if (map[0][idx] == 1) {
                map[0][idx] = Math.max(m - idx, 0);
            }
            map[idx][0] += map[idx - 1][0];
            map[0][idx] += map[0][idx - 1];
        }

        for (int i = 1; i < MAP_SIZE; i++) {
            for (int j = 1; j < MAP_SIZE; j++) {
                if (map[i][j] == 1) {
                    map[i][j] = Math.max(m - i - j, 0);
                }
                map[i][j] += Math.max(map[i - 1][j], map[i][j - 1]);
            }
        }

        System.out.println(map[MAP_SIZE - 1][MAP_SIZE - 1]);
    }
}
