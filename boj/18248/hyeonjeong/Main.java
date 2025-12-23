// 16168KB 396ms

import java.util.*;
import java.io.*;

/**
 * i번째 사람이 들은 종을 j번째 사람이 못 들었으면, 이후에 i가 못 들은 종을 j가 들으면 안 됨
 * i번째 사람이 못 들은 종을 j번째 사람이 들었으면, 이후에 i가 들은 종을 j가 못 들으면 안 됨
 * 
 * i < n, j < n, bell < m 해서
 * i * j * m 해도 1억을 안 넘어서 완탐
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    public static void main(String[] args) throws IOException {
        final int n = next();
        final int m = next();

        boolean[][] heard = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int bell = 0; bell < m; bell++) {
                heard[i][bell] = next() == 1;
            }
        }

        boolean iIsCloser, iIsFar;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                iIsCloser = false;
                iIsFar = false;

                for (int bell = 0; bell < m; bell++) {
                    if (heard[i][bell] && !heard[j][bell]) {
                        iIsCloser = true;
                    }

                    if (!heard[i][bell] && heard[j][bell]) {
                        iIsFar = true;
                    }
                }

                if (iIsCloser && iIsFar) {
                    System.out.print("NO");
                    return;
                }
            }
        }

        System.out.print("YES");
    }

    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
