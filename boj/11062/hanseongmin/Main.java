import java.io.*;

/*
326352KB, 748ms

예전에 풀었던 케이크 자르기 같은 유형이라서 DP인건 바로 알아냈는데 점화식 구하기가 까다로웠음
이런 류 DP에 내가 약한거같음

처음에는 turn을 넘겨줘서 근우 차례일 때 명우 차례일 때를 나눠서 고려하려 했는데 이러니까 생각하기 더 복잡한거 같고
무엇보다 turn 없이도 할 수 있을 것 같아 그 방식으로 고민하다보니 어찌저찌 점화식이 나옴

dp[i][j][k]: [i, j]만큼의 카드를 보았을 때
k가 0이면 해당 구간에서 내가 먼저 카드를 뽑았을 때 얻을 수 있는 점수,
1이면 해당 구간에서 상대방이 먼저 카드를 뽑았을 때 얻을 수 있는 점수
로 정의했다.

좀 헤맸는데 풀고나서 답 찾아보니 엄청 간단한 문제였어서 살짝 허무했음
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int N;
    static int[] arr, sum;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        int T = nextInt();
        StringBuilder sb = new StringBuilder();
        while (T --> 0) {
            N = nextInt();
            arr = new int[N];
            sum = new int[N + 1];
            dp = new int[N][N][2];
            for (int i = 0; i < N; i++) {
                arr[i] = nextInt();
                sum[i + 1] = sum[i] + arr[i];
            }

            getAns(0, N - 1);
            //[0, N - 1] 구간에서 근우가 먼저 뽑으므로 dp[0][N - 1][0]의 값을 취함
            sb.append(dp[0][N - 1][0]).append('\n');
        }
        System.out.print(sb);
    }

    static void getAns(int s, int e) {
        //이미 값은 구한적이 있으면 넘어감
        if (dp[s][e][0] != 0) return;
        if (s == e) {
            //내 차례라면 마지막 남은 카드 한 장을 가져갈 수 있음
            dp[s][e][0] = arr[s];
            //내 차례가 아니라면 못가져감
            dp[s][e][1] = 0;
            return;
        }

        //dp 테이블을 채우고
        getAns(s + 1, e);
        getAns(s, e - 1);
        //현재 구간에서 내가 먼저 뽑을 수 있다면 앞이나 뒷 카드 중 하나를 뽑고
        //남은 구간에서 상대방이 먼저 뽑는 경우의 수만큼 더해짐
        dp[s][e][0] = Math.max(arr[s] + dp[s + 1][e][1], arr[e] + dp[s][e - 1][1]);
        //현재 구간에서 상대방이 먼저 뽑는다면 현재 구간에서 얻을 수 있는 점수에서
        //상대방이 최선으로 얻는 점수를 뺀 것이 내가 얻을 수 있는 최대의 점수가 됨
        dp[s][e][1] = sum[e + 1] - sum[s] - dp[s][e][0];
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
