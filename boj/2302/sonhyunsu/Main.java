//제출번호: 92704999
//메모리:   11488 KB
//실행시간: 64 ms
import java.io.*;

//VIP석은 본인만 앉을 수 있다는 것을 보고 거기에 맞춰서 DP를 돌렸더니 맞음
public class Main {

	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		int n = nextInt();
		int m = nextInt();

		int[] dp = new int[n + 1];
		boolean[] isVip = new boolean[n + 1];
		for (int i = 0; i < m; i++) {
			isVip[nextInt()] = true;
		}
		
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
            //만약 본인이 VIP이거나, 한 칸 전이 VIP이면
            //본인 자리만 앉을 수 있음
			if (isVip[i] || isVip[i-1]) {
				dp[i] = dp[i-1];
			} else {
                //두 조건을 모두 만족하지 않으면
                //본인은 본인 자리에 앉거나, 한 칸 전 자리에 앉을 수 있음
                //(이 때 한 칸 전 고객은 본인의 자리에 앉음)
				dp[i] = dp[i-1] + dp[i-2];
			}
		}

		System.out.print(dp[n]);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}

