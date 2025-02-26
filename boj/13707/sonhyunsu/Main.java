//제출번호: 90575623
//메모리:   12360 KB
//실행시간: 312 ms
import java.io.*;

//N이 5000, K가 5000이므로 최대 O(NK) 알고리즘만 가능하다
//덧셈 순서를 바꾸면 다른 경우이기 때문에 0~N까지 한 번씩 더하고
//그 결과에 0~N까지 한 번씩 더하는 과정을 K만큼 하면 정답을 구할 수 있다.
//하지만 한 번씩 더하는 과정을 반복문으로 돌게 되면 O(N^2 * K)가 되면서 시간초과 날 수 있다.
//이 때 누적합을 이용해서 한 번씩 더하는 과정을 O(1)로 줄인다.
//sum[i]는 0~i까지 이용해서 i를 만드는 경우의 수를 담고 있다고 해석할 수 있다.
//(항상 0 <= k <= i인 임의의 k를 가지고 i - k에서 k를 더하면 i가 된다.)
//N이 넘어가는 경우의 수는 필요 없으므로 계산하지 않는다.
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer st = new StreamTokenizer(br);

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int k = nextInt();
        int divider = (int) 1e9;

        int[] dp = new int[n + 1];
        int[] sum = new int[n + 1];

        dp[0] = sum[0] = 1;
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + dp[i];
        }

        for (int i = 0; i < k; i++) {
            //0~N까지 한 번 더 써서 j를 만드는 경우는
            //이전의 [0, j]구간의 누적합이다.
            for (int j = 1; j <= n; j++) {
                dp[j] = sum[j]; //j를 만드는 경우의 수를 dp에 저장한다.
            }

            //다음 누적합을 계산한다.
            for (int j = 1; j <= n; j++) {
                sum[j] = (sum[j - 1] + dp[j]) % divider;
            }
        }

        System.out.print(dp[n]);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}

/* 불필요한 메모리를 제거하는 코드
//제출번호: 90575866
//메모리:   11716 KB
//실행시간: 296 ms
import java.io.*;

//위 코드를 보면 dp는 항상 sum 배열의 값만 가져오는 것을 볼 수 있다.
//그래서 dp의 위치를 sum으로 변경함으로써 dp 배열을 사용하지 않을 수 있다.
//해석이 쉬운 코드는 위쪽이기 때문에 위를 메인으로 남겨둠
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer st = new StreamTokenizer(br);

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int k = nextInt();
        int divider = (int) 1e9;

        int[] sum = new int[n + 1];

        sum[0] = 1;
        for (int i = 0; i < k; i++) {
            for (int j = 1; j <= n; j++) {
                sum[j] = (sum[j - 1] + sum[j]) % divider;
            }
        }

        System.out.print(sum[n]);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
 */