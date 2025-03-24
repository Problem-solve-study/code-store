//제출번호: 91902197
//메모리:   54836 KB
//실행시간: 604 ms
import java.io.*;

//2가지 방법으로 풀 수 있을 듯함.
//첫 번째는 버튼을 가장 최소로 누르는 경로를 다익스트라로 뽑아가면서 도착점을 찾는 방법
//두 번째는 그래프의 간선이 최대 2개로 작기 때문에,
//  그리고 오른쪽, 아래 방향으로 가는 DAG이기 때문에 dp로 구할 수도 있음
//여기서는 두 번째 방법으로 구현 
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();

        int[][] d = new int[n + 1][n + 1];
        int[][] dp = new int[n + 1][n + 1];
        //if를 최대한 덜 사용하기 위해서 벽을 세움
        for (int i = 2; i <= n; i++) {
            d[i][0] = d[0][i] = -1000000;
        }
        //시작점은 어떤 높이들보다 높게 만듦
        d[0][1] = d[1][0] = 256;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                d[i][j] = nextInt();

                //i, j 기준으로 왼쪽, 위쪽에서 오기 위해서 필요한 버튼 클릭 수를 구함
                //왼쪽, 위쪽이 더 높다면 지금은 0번만 클릭하면 되기 때문에 Math.max()로 음수 값을 커팅함
                dp[i][j] = Math.min(dp[i - 1][j] + Math.max(0, d[i][j] - d[i - 1][j] + 1),
                                    dp[i][j - 1] + Math.max(0, d[i][j] - d[i][j - 1] + 1));
            }
        }

        System.out.print(dp[n][n]);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}