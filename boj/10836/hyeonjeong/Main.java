// 44760KB 1136ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 2차원 DP? 라기엔 700 * 700 * 백만이라 안 될 듯
 * 숫자가 감소하지 않는다고?? 그럼 고정일 것 같은데
 * 날짜별 성장 다 더해뒀다가 한 번에 ㄱㄱ
 */
public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        
        int m = nextInt();
        int n = nextInt();

        int[] grow = new int[2 * m - 1];
        for (int i = 0; i < n; i++) {
            int j = 0;
            for (int k = 0; k < 3; k++) {
                int count = nextInt();
                for (; count > 0; count--) {
                    grow[j++] += k;
                }
            }
        }

        int[][] map = new int[m][m];
        for (int i = 0; i < m - 1; i++) {
            int idx = m - 1 - i;
            for (int j = 0; j < m; j++) {
                map[idx][j] = grow[i] + 1;
            }
        }
        map[0][0] = grow[m - 1] + 1;
        for (int i = m; i < 2 * m- 1; i++) {
            int idx = i - m + 1;
            for (int j = 0; j < m; j++) {
                map[j][idx] = grow[i] + 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(map[i][j]).append(' ');
            }
            sb.append('\n');
        }

        sb.trimToSize();

        System.out.println(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();

        return (int) st.nval;
    }
}


