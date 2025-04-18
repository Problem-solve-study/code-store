import java.io.*;
import java.util.*;

/*
 * 17304KB, 104ms
 * 
 * 문제가 딱 봐도 DP스러워서 DP로 풀고있다가 값이 이상하게 나와서 어떻게 할까 
 * 곰곰히 생각해보니 냅색으로 하면 될 거 같아서 냅색으로 풀이.
 * 
 * 태그를 보니 브루트포스로도 뚫리는 것 같음.
 * 처음에 배낭 문제가 아닌 그냥 DP로 했다가 식 잘못짜서 2틀
 * 시간 차원을 초가 아니라 분으로 했다가 1틀
 * 이후 95퍼까지 갔다가 형 변환 잘못했다가 3틀
 */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long[] D = new long[N];
		for (int i = 0; i < N; i++) {
			D[i] = Long.parseLong(br.readLine());
		}
		
		long[][] boss = new long[K][2];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			boss[i][0] = Long.parseLong(st.nextToken());
			boss[i][1] = Long.parseLong(st.nextToken());
		}
		
		//3차원 냅색 시작
		//dp[i][j][k]: i번째 캐릭터가 보스를 j만큼 잡고 k초만큼 지났을 때 최대 메소
		long[][][] dp = new long[N][K + 1][901];
		for (int i = 0; i < N; i++) {
			for (int j = 1; j <= K; j++) {
				long p = boss[j - 1][0];
				long q = boss[j - 1][1];
				//보스를 잡는 시간을 구함
				long time = (long) Math.ceil((double) p / D[i]);
				
				for (int k = 0; k <= 900; k++) {
					//일단 이전 값을 취하고
					dp[i][j][k] = dp[i][j - 1][k];
					//갱신을 시도
					if (k - time >= 0) {
						dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j - 1][k - (int)time] + q);
					}
				}
			}
		}
		
		//M개의 캐릭터만 잡아야하므로 dp값이 최대인 캐릭터를 M개 골라야 함
		//맥스힙 사용
		PriorityQueue<Long> h = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < N; i++) {
			h.add(dp[i][K][900]);
		}
		
		long res = 0;
		for (int i = 0; i < M; i++) {
			res += h.remove();
		}
		
		System.out.print(res);
	}
}