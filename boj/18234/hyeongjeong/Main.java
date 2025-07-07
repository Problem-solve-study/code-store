import java.io.*;
import java.util.*;

/**
 * p_i가 항상 w_i보다 큼 && T가 항상 N보다 큼
 * => p_i가 큰 순서대로 나중에 먹고, T-N 동안은 안 먹고 기다리는 게 항상 유리함
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int t = nextInt();
        int[][] carrots = new int[n][2];

        for (int i = 0; i < n; i++) {
            carrots[i][0] = nextInt();
            carrots[i][1] = nextInt();
        }

        Arrays.sort(carrots, (c1, c2) -> c1[1] == c2[1] ? c2[0] - c1[0] : c2[1] - c1[1]);

        Long answer = 0L;
        for (int i = 0; i < n; i++) {
            answer += carrots[i][1] * (t - i - 1L) + carrots[i][0];
        }

        System.out.print(answer);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
