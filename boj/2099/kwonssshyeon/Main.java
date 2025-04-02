
// 	70048KB	732ms
import java.io.*;
import java.util.*;

/**
 * 행렬의 곱하기는 result[i][j] += (a[i][c] * b[c][j]) 이다.
 * 따라서 곱했을때 좌표가 0이상이라는 것은 i->j로 c를 거쳐가는 경로가 최소 한개는 존재한다는 의미이다.
 * 
 * => k번 곱한다는 것은
 * i-> (a->b->c->...(k개)) ->j 의 경로가 존재한다는 의미이고, 이는 곧 거리 k 로 i -> j를 이동할 수 있다는
 * 의미이다.
 * 
 * 근데 문제에서 k가 백만이므로 실제로 k번을 곱하면 시간초과이다.
 * => 분할정복을 이용한 거듭제곱 계산
 * k가 짝수인 경우 A^k * A^K = A^(2k)
 * 홀수인 경우 A^k * A^(k-1) = A^(2k-1)
 * 을 이용해서 logk 시간 내로 k번의 곱셈을 계산한 결과를 낼 수 있다.
 */
public class Main {
    static int n, k, m;
    static final String death = "death\n", life = "life\n";
    static int[][] map;
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        n = nextInt();
        k = nextInt();
        m = nextInt();
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            map[i][nextInt() - 1] = 1;
            map[i][nextInt() - 1] = 1;
        }
        int[][] result = simulate();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            if (result[nextInt() - 1][nextInt() - 1] != 0)
                sb.append(death);
            else
                sb.append(life);
        }
        System.out.print(sb);
    }

    static int[][] multMatrix(int[][] a, int[][] b) {
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int c = 0; c < n; c++) {
                    result[i][j] += (a[i][c] * b[c][j]);
                }
            }
        }
        return result;
    }

    static int[][] simulate() {
        int[][] dst = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dst[i][j] = map[i][j];
            }
        }
        k--;
        while (k > 0) {
            if (k % 2 == 1) {
                dst = multMatrix(dst, map);
            }
            map = multMatrix(map, map);
            k /= 2;
        }
        return dst;
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}