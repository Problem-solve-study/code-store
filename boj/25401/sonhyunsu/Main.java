//제출번호: 93554644
//메모리:   20592 KB
//실행시간: 288 ms
import java.io.*;

//처음에 O(2,000,000 * N)인 풀이를 생각했는데 이건 확실히 시초날 거 같아서 바로 버렸음
//아슬아슬하게 O(N^3) 은 가능할 거 같아서 거기에 맞는 풀이를 생각함
//DP식을 짜긴 했는데, 기여 보니까 이렇게 푸는게 아니었음 ㅋㅋ...
//DP[s][e] : [s, e] 구간의 숫자를 사용했을 때 바꿔야 하는 최소 숫자 개수
//a[s][e] : [s, e] 구간의 등차수열의 첫째 항
//d[s][e] : [s, e] 구간의 공차
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int[] cards = new int[n];
        for (int i = 0; i < n; i++) {
            cards[i] = nextInt();
        }

        int[][] dp = new int[n][n];
        long[][] a = new long[n][n];
        long[][] d = new long[n][n];
		//dp 초기식 설정,  등차수열의 첫째항도 설정
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
            a[i][i] = cards[i];
        }

        for (int i = 1; i < n; i++) {
            for (int s = 0, e = s + i; e < n; s++, e++) {
                dp[s][e] = Integer.MAX_VALUE; //현재 필요한 개수를 최대로 설정

                int ns = s + 1;
                int pe = e - 1;

				//만약 cards[s]와 cards[e], 두 숫자를 가지고 등차수열을 만들 수 있다면
                if ((cards[e] - cards[s]) % i == 0) {
					//등차수열을 만들고
                    int delta = (cards[e] - cards[s]) / i;
                    int cnt = 0;
					//등차수열에 맞지 않는 숫자의 개수를 셈
                    for (int j = 0, num = cards[s]; j <= i; j++, num += delta) {
                        if (cards[s + j] != num) {
                            cnt++;
                        }
                    }

					//그 결과를 저장함
                    dp[s][e] = cnt;
                    a[s][e] = cards[s];
                    d[s][e] = delta;
                }

				//[ns, e] 구간의 등차수열에서 s가 포함될 수 있다면 0,
				//s가 포함되지 않는다면 숫자를 바꿔야 하므로 1을 추가로 더함
                int left = dp[ns][e] + (cards[s] != a[ns][e] - d[ns][e] ? 1 : 0);

				//현재 dp보다 작다면 업데이트
                if (left < dp[s][e]) {
                    dp[s][e] = left;
                    a[s][e] = a[ns][e] - d[ns][e];
                    d[s][e] = d[ns][e];
                }

				//[s, pe] 구간의 등차수열에서 e가 포함될 수 있다면 0,
				//e가 포함되지 않는다면 숫자를 바꿔야 하므로 1을 추가로 더함
                int right = dp[s][pe] + (cards[e] != a[s][pe] + d[s][pe] * i ? 1 : 0);

				//현재 dp보다 작다면 업데이트
                if (right < dp[s][e]) {
                    dp[s][e] = right;
                    a[s][e] = a[s][pe];
                    d[s][e] = d[s][pe];
                }
            }
        }

		//전체 구간의 dp 결과 출력
        System.out.print(dp[0][n-1]);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}