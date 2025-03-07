
//제출번호: 90992898
//메모리:   19636 KB
//실행시간: 228 ms
import java.io.*;

//쉽게 점화식을 만들어 풀 수 있는 DP 문제, 할 수 있는 행동을 기준으로 점화식을 만들면 됨
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();
        int[][] d = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 내 왼쪽, 위쪽, 왼위쪽 중 최댓값을 가져옴
                // 각각 왼쪽에서 현재, 위쪽에서 현재, 왼위쪽에서 현재로 오는 방법임
                d[i][j] = Math.max(Math.max(d[i - 1][j], d[i][j - 1]), d[i - 1][j - 1]) + nextInt();
            }
        }

        System.out.print(d[n][m]);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}