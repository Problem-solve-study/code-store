import java.io.*;

/*
그리디로 선택한다면 자신과 연결될 수 있는 요소들 중 가장 위에 있는 요소들을 선택하기.
1      100
100    101
101    102
102    103
103    5
이런 경우라면 그리디로 풀 수 없음. DP인거같다.

dp라면 왼쪽 오른쪽 두 개의 목초지가 있으니까 2차원 DP일거고
dp[i][j]를 i, j번째 목초지까지 이을 경우 횡단보드의 수라고 생각하니
i, j를 이을 수 있을 때 dp[i][j] = dp[i - 1][j - 1] + 1일텐데..
어? 이거 LCS 아닌가?

하고 풀어서 맞았다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int n = nextInt();
        int[] l = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            l[i] = nextInt();
        }

        int[] r = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            r[i] = nextInt();
        }

        int max = 0;
        //진짜 완벽하게 LCS의 점화식과 동일함
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int a = l[i];
                int b = r[j];
                if (Math.abs(a - b) <= 4) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    //이 부분 dp[i - 1][j - 1]로 택했다가 1틀함
                    //dp[i - 1][j - 1]보다 dp[i - 1][j], dp[i][j - 1]이 더 범위가 넓으므로
                    //둘 중 max 값을 선택해야지 맞는 답이 나옴
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        System.out.print(max);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
