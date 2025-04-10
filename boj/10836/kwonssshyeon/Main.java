
// 45856KB	640ms
import java.io.*;
import java.util.*;

/**
 * 누적합 ㅠㅠ
 */
public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int n, m, map[];

    public static void main(String[] args) throws IOException {
        m = nextInt();
        n = nextInt();
        map = new int[m << 1];
        map[0] = 1;
        for (int i = 0; i < n; i++) {
            int idx = 0;
            for (int j = 0; j < 3; j++) {
                idx += nextInt();
                map[idx] += 1;
            }
        }
        for (int i = 1; i < m << 1; i++) {
            map[i] += map[i - 1];
        }

        StringBuilder sb = new StringBuilder();
        for (int j = 1; j <= m; j++) {
            sb.append(map[m - j]).append(" ");
            for (int i = 0; i < m - 1; i++) {
                sb.append(map[m + i]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    // fast i/o
    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
