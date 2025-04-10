//제출번호: 92894647
//메모리:   18072 KB
//실행시간: 300 ms
import java.io.*;

//dp식만 잘 만들면 풀 수 있음
public class Main {

	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		int[][] sticker = new int[2][100001];
		int[][] dp = new int[3][100001];
		
		int T = nextInt();
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int n = nextInt();

            //스티커를 입력 받음
			for (int r = 0; r < 2; r++) {
				for (int c = 0; c < n; c++) {
					sticker[r][c] = nextInt();
				}
			}

            //DP[0] : 위, 아래 스티커를 모두 선택하지 않았을 때 최댓값
            //DP[1] : 위 스티커를 선택했을 때 최댓값
            //DP[2] : 아래 스티커를 선택했을 때 최댓값
			dp[1][0] = sticker[0][0];
			dp[2][0] = sticker[1][0];
			for (int i = 1; i <= n; i++) {
                //i번째에서 아무것도 선택하지 않았을 때의 최댓값은
                //i-1번째에서 위나 아래 스티커를 하나 선택한 경우 중 최대임
				dp[0][i] = Math.max(dp[1][i-1], dp[2][i-1]);

                //i번째에서 위 스티커를 선택했을 때의 최댓값은
                //위 스티커를 선택할 수 있는
                //i-1번째에서 아무것도 선택하지 않은 경우와 아래 스티커를 선택하는 경우 중 최댓값에서
                //위쪽 스티커의 가치를 더한 값임
				dp[1][i] = Math.max(dp[0][i-1], dp[2][i-1]) + sticker[0][i];

                //i번쨰에서 아래 스티커를 선택했을 때의 최댓값은
                //위 스티커를 선택했을 때의 최댓값을 구하는 경우와
                //위아래만 반전일 뿐 구하는 방법은 동일하므로 설명 생략
				dp[2][i] = Math.max(dp[0][i-1], dp[1][i-1]) + sticker[1][i];
			}

            //n번째 스티커는 없지만(0 ~ n-1번째 스티커가 있음), 있다고 가정하고 dp식을 돌리면
            //dp[0][n]에는 dp[1][n-1], dp[2][n-1]의 최댓값이 들어가 있음
			sb.append(dp[0][n]).append('\n');
		}

		System.out.print(sb);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}