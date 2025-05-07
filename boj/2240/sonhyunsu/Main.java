//제출번호: 93978297
//메모리:   12252 KB
//실행시간: 64 ms
import java.io.*;

//자두나무 번호, 이동 횟수, 시간 | 총 3개의 상태를 가지고 dp식을 만들 수 있음
//자세한 내용은 아래 주석 참고 
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int t = nextInt();
        int w = nextInt();
        int[][][] dp = new int[2][w + 1][t + 1];

        int plum = nextInt()-1;

        if (plum == 0) {
            //만약 1번 나무라면 움직이지 않고 자두를 얻을 수 있음
            dp[0][0][1] = 1;
        } else {
            //만약 2번 나무라면 움직여야 자두를 얻을 수 있음
            dp[1][1][1] = 1;
        }

        for (int i = 2; i <= t; i++) {
            plum = nextInt() - 1;
            int opposite = plum ^ 1; //반대편 나무 번호 계산

            dp[plum][0][i] = dp[plum][0][i-1] + 1; //자두가 떨어지는 나무는 움직이지 않고 얻을 수 있음
            dp[opposite][0][i] = dp[opposite][0][i-1]; //떨어지지 않는 나무는 변화 없음

            for (int j = 1; j <= w; j++) {
                //반대편 나무에서 j번 움직이고 i시간에 얻을 수 있는 최댓값은 i-1시간에 현재 나무에서 오거나, i-1시간에 움직이지 않는 경우 중 최댓값 
                dp[opposite][j][i] = Math.max(dp[plum][j-1][i-1], dp[opposite][j][i-1]);

                //현재 나무에서 j번 움직이고 i시간에 얻을 수 있는 최댓값은 i-1시간에 움직이지 않거나, i-1시간에 반대편 나무에서 오는 경우 중 최댓값
                //+ 1(떨어지는 자두를 받음)
                dp[plum][j][i] = Math.max(dp[plum][j][i-1], dp[opposite][j-1][i-1]) + 1;
            }
        }

        //t시간에 받을 수 있는 최댓값을 구하고 출력
        int res = 0;
        for (int i = 0; i <= w; i++) {
            res = Math.max(res, Math.max(dp[0][i][t], dp[1][i][t]));
        }

        System.out.print(res);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}