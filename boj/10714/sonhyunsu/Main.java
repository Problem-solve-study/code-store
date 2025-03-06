
//제출번호: 90939981
//메모리:   44608 KB
//실행시간: 204 ms
import java.io.*;

//처음에 어떻게 접근해야 할 지 모르겠었음.
//그러다가 dp를 구간의 최댓값을 저장하는 방향으로 점화식을 만들 수 있지 않을까 생각했음.
//항상 인접한 케이크 조각을 선택해야 하기 때문에 구간을 줬을 때 선택할 수 있는 경우는 2가지 밖에 없음
//JOI 군은 두 가지 중 아무거나 선택할 수 있고, IOI 양은 항상 더 큰 케이크 조각을 선택하기 때문에
//그것을 점화식으로 삼아서 dp로 구현
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static long[][] dp; // [start][end], start ~ end 사이의 케이크 조각을 선택했을 때의 JOI 군의 최댓값 저장
    static int[] d;

    public static void main(String[] args) throws IOException {
        int n = nextInt();

        d = new int[n + 1];
        dp = new long[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            d[i] = nextInt();
            dp[i][i] = d[i]; // [i, i]는 항상 JOI군이 선택함
        }

        // 구간의 길이를 0부터 n - 1까지 늘려봄
        for (int i = 0; i < n; i++) {
            // 시작점을 0부터 n - 1까지 설정
            for (int s = 0; s < n; s++) {
                // 도착점은 항상 시작점+구간의 길이임
                // 원형이니까 나머지를 이용해서 실제 인덱스를 구해야 함
                int e = (s + i) % n;

                int ps = (s + n - 1) % n; // 시작점보다 한 칸 앞에 있는 인덱스를 계산
                int ne = (e + 1) % n; // 도착점보다 한 칸 뒤에 있는 인덱스를 계산

                // 구간의 길이가 짝수면 다음에 IOI 양이 선택함
                if (i % 2 == 0) {
                    // IOI 양은 항상 큰 케이크 조각만 선택하기 때문에 조건식을 이용해서 둘 중 하나만 계산되게 함
                    if (d[ps] < d[ne]) {
                        dp[s][ne] = Math.max(dp[s][ne], dp[s][e]);
                    } else {
                        dp[ps][e] = Math.max(dp[ps][e], dp[s][e]);
                    }
                } else {
                    // 구간의 길이가 홀수면 다음에 JOI 군이 선택함
                    // JOI 군은 둘 중 하나를 선택할 수 있기 때문에 둘 다 계산함
                    dp[s][ne] = Math.max(dp[s][ne], dp[s][e] + d[ne]);
                    dp[ps][e] = Math.max(dp[ps][e], dp[s][e] + d[ps]);
                }
            }
        }

        // 어떤 케이크 조각을 먼저 선택했는 지 모르기 때문에
        // 전체 구간의 길이가 n인 모든 구간을 탐색해서 최댓값을 계산함
        long max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i][(i + n - 1) % n]);
        }

        System.out.print(max);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}