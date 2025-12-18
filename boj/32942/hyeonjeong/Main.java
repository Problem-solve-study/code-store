// 11544KB 68ms

import java.util.*;
import java.io.*;

/**
 * 그래프 이론?
 * 문제에서 정직하게 시키는 대로 했습니다.
 * 10 * 10이라 연결 리스트보다 2차원 맵이 더 편할 것 같아서 맵 사용했습니다.
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int a = next();
        int b = next();
        int c = next();

        boolean[][] map = new boolean[11][11];
        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {
                if (a * x + b * y != c) {
                    continue;
                }

                map[x][y] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            int count = 0;
            for (int j = 1; j <= 10; j++) {
                if (map[i][j]) {
                    if (count != 0) {
                        sb.append(' ');
                    }
                    count++;
                    sb.append(j);
                }
            }

            if (count == 0) {
                sb.append(0);
            }

            if (i != 10) {   
                sb.append('\n');
            }
        }

        System.out.println(sb);
    }

    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
