//제출번호:	92951801
//메모리:	13164 KB
//실행시간:	168 ms
import java.io.*;

//문제를 보자마자 냅색이 먼저 생각났음
//처음에 냅색을 이용해서 dp식을 구현했는데 식 자체가 틀려서 4틀
//원래는 웍 2개의 모든 조합을 기준으로도 냅색을 진행하려 했는데
//그러면 1000C2 * 10000 이므로 무조건 시간초과가 남
//이 때 크기가 4인 웍 1개와 크기가 1, 3인 웍 2개로 조리하는 횟수는 동일하니까
//이 부분을 커팅하면 되지 않을까 생각하고 구현함

//계산에 사용되는 웍 조합은 최대 n개이고, 냅색 for문은 O(N)으로 돌아가니까
//시간복잡도는 O(N^2)이지만 계수가 1/2임
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
		int n = nextInt();
		int m = nextInt();
		int[] dp = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			dp[i] = 100000;
		}

		int[] woks = new int[m];
		boolean[] used = new boolean[n + 1];
		for (int i = 0; i < m; i++) {
			woks[i] = nextInt();

			//모든 웍 2개의 조합에 대해서 계산해봄 
			for (int j = 0; j < i; j++) {
				int wok = woks[i] + woks[j];
				
				//2개의 조합이 n 이하이면서 아직 계산해보지 않은 값이면
				if (wok <= n && !used[wok]) {
					used[wok] = true;
					
					//냅색을 돌려봄
					for (int k = wok; k <= n; k++) {
						dp[k] = Math.min(dp[k], dp[k-wok] + 1);
					}
				}
			}

			//하나의 웍에 대해서 아직 계산해보지 않은 값이면
			int wok = woks[i];
			if (!used[wok]) {
                used[wok] = true;

				//냅색을 돌려봄
				for (int k = wok; k <= n; k++) {
					dp[k] = Math.min(dp[k], dp[k-wok] + 1);
				}
			}
		}

		//dp[n] 값이 10만이면 불가능하므로 -1 아니면 dp[n] 출력
		System.out.print(dp[n] == 100000 ? -1 : dp[n]);
	}

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}