import java.io.*;
import java.util.*;

//처음에 1차원 dp로 생각하고 dp[i]는 i번째까지 조약돌을 모두 사용했다고 가정한 뒤
//dp[i] = min( dp[i - 2] + (i -1 번째와 i번째 조약돌을 최대한 동시에 줍고 남은 조약돌이 있다면 +1), (dp[i-1] + 1) )
//라고 생각했는데 3 / 1 4 3 예제에서 불가능 한 것을 확인했음

//두 개씩 잡는 것을 연속으로 해도 되게끔 dp를 작성해야 했고
//그래서 2차원 dp로 [start, end]를 저장하는 공간을 만들면 될까 생각했음
//n이 2500으로 2차원으로 만들어도 2500*2500 = 6,250,000 정도의 공간과 시간을 가져서 시도해봄
public class Main {    

    static int dp[][] = new int[2501][2501]; //start, end

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        //조약돌을 저장함
        int[] d = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            d[i] = Integer.parseInt(st.nextToken());
        }

        //dp의 모든 공간을 10억으로 초기화
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (int) 1e9;
            }
        }

        //start와 end가 같은 곳은 한 곳의 조약돌을 줍는 행위이므로 1 작업량으로 가능
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;
        }

        //start를 i로 고정하고, end를 1씩 증가하면서 연속적으로 2곳씩 줍는 행위를 시도해봄
        for (int i = 1; i < n; i++) {
            int val = d[i];
            for (int j = i + 1; j <= n; j++) {
                val = d[j] - val;

                //만약 val이 음수면 앞의 조약돌을 기준으로
                //연속으로 줍는 행위가 불가능하다는 뜻이므로 더 이상 탐색 X
                if (val < 0) {
                    break;
                }

                //j가 end가 될 때 필요한 작업량을 계산해 [i, j]에 업데이트 함
                //val이 0이면 추가적인 작업이 필요없음
                dp[i][j] = j - i + (val == 0 ? 0 : 1);
            }
        }

        //start를 1로 고정하고, end를 2부터 n까지 1씩 증가하면서 dp를 업데이트 함.
        //아래 식에서 dp[1][j]가 최소임을 보장하기 위해서
        //2 부터 n 순서로 dp를 업데이트 해야 함
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                //dp[1][i] 는 기존의 dp[1][i]와 ([1, j]의 최솟값 + [j + 1][i]의 최솟값) 중 최솟값으로 업데이트
                dp[1][i] = Math.min(dp[1][i], dp[1][j] + dp[j + 1][i]);
            }
        }

        //[1, n] 구간의 값을 출력함
        System.out.print(dp[1][n]);
    }
}