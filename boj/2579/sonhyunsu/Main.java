import java.io.*;

public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();

        // dp[0] 계단 한 칸을 연속적으로 0번 이동 (2칸 이동)
        // dp[1] 계단 한 칸을 연속적으로 1번 이동
        // 한 칸을 연속으로 2번 이동하면 연속된 세 계단을 밟은 것이기 때문에 불가능
        int[][] dp = new int[2][n + 1];

        dp[0][1] = nextInt();
        for (int i = 2; i <= n; i++) {
            int x = nextInt();
            // 한 칸을 0번 이동했다는 얘기는 두 칸을 이동했다는 얘기와 같다.
            // 두 칸은 제한이 없기 때문에 두 칸 전의 최댓값을 고른다.
            dp[0][i] = Math.max(dp[0][i - 2], dp[1][i - 2]) + x;

            // 한 칸을 움직이려면 반드시 이전에 한 칸을 0번 이동해야 한다.
            dp[1][i] = dp[0][i - 1] + x;
        }

        System.out.print(Math.max(dp[0][n], dp[1][n]));
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}