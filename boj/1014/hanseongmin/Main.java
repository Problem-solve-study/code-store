import java.io.*;
import java.util.*;

/*
22044KB, 332ms

꽤 쉽게 풀었음. 개인적인 체감 난이도는 G2 정도인듯

맨 처음에는 전체 상황을 알고있어야 할 것같아서 10x10을 어떻게 DP의 상태값으로 넘기지 고민했는데
조금만 더 생각하니 굳이 전체 상태값을 알 필요없이 현재 행 기준 이전 행의 상태만 알고 있으면 된다는 사실을 알았음
이러면 최대 열의 값이 10이니까 비트마스킹으로 깔끔하게 넘겨줄 수 있음

그러면 dp 테이블을 행, 상태로 두고
dp[i][상태1]와 dp[i - 1][상태2]가 양립 가능할 때
dp[i][상태1] = dp[i - 1][상태2] + 상태1에서의 1 개수 로 점화식을 세우면 풀리겠다 싶어서 그대로 구현해서 AC
 */

public class Main {
    static int N, M;
    static int[][] dp;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            dp = new int[N + 1][1 << M];
            map = new char[N + 1][M];
            for (int i = 1; i <= N; i++) {
                map[i] = br.readLine().toCharArray();
            }

            for (int i = 1; i <= N; i++) {
                for (int curS = 0; curS < (1 << M); curS++) {
                    //현재 상태가 불가능한 경우라면 불가능하다는 것을 체크해두고 넘어감
                    if (!statusValidCheck(i, curS)) {
                        dp[i][curS] = -1;
                        continue;
                    }

                    for (int prevS = 0; prevS < (1 << M); prevS++) {
                        //이전 상태가 불가능한 경우라면 넘어감
                        if (dp[i - 1][prevS] == -1) continue;
                        //현재 상태와 이전 상태가 양립 가능하다면 dp값 갱신
                        if (isDeployable(prevS, curS)) {
                            dp[i][curS] = Math.max(dp[i][curS], dp[i - 1][prevS] + getOneCnt(curS));
                        }
                    }
                }
            }

            sb.append(Arrays.stream(dp[N]).max().orElse(0)).append('\n');
        }
        System.out.print(sb);
    }

    static boolean bitCheck(int n, int pos) {
        return (n & (1 << pos)) != 0;
    }

    //현재 상태가 유효한 상태인지 확인
    //x인데 앉았거나, 규칙에 맞지 않는 배치면 false
    static boolean statusValidCheck(int r, int s) {
        for (int i = 0; i < M; i++) {
            if (!bitCheck(s, i)) continue;

            //현재 위치에 학생이 배정됐는데 못앉는 자리라면 false
            if (map[r][M - i - 1] == 'x') {
                return false;
            }

            //자신의 오른쪽에 누군가가 앉아 있으면 안됨
            if (i != 0 && bitCheck(s, i - 1)) {
                return false;
            }
        }
        return true;
    }

    //두 상태가 양립 가능한지 확인
    static boolean isDeployable(int prevS, int curS) {
        for (int i = 0; i < M; i++) {
            if (!bitCheck(curS, i)) continue;
            if (i != 0 && bitCheck(prevS, i - 1)) {
                return false;
            }
            if (i != M - 1 && bitCheck(prevS, i + 1)) {
                return false;
            }
        }

        return true;
    }

    static int getOneCnt(int n) {
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            if (bitCheck(n, i)) cnt++;
        }
        return cnt;
    }
}
