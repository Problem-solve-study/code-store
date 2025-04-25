//문제: BOJ 22871번 징검다리 건너기(large)
//메모리: 12164 KB
//시간: 140 ms

/*
 * 1~n-1까지 dp로 계산
 * 
 * 해당 위치로 바로 오는 경우, 중간지점을 경유하는 경우
 * 경유하는 경우, 경유지까지의 dp값과 경유지부터 해당 위치까지의 비용 중 큰 값을 계산
 */

public class Main {

	public static void main(String[] args) throws Exception {
		int n = nextInt();
		int[] arr = new int[n];
		long[] dp = new long[n];
		arr[0] = nextInt();
		for (int i = 1; i < n; i++) {
			arr[i] = nextInt();
			dp[i] = Long.MAX_VALUE;
			for (int j = 0; j < i; j++)
				dp[i] = Math.min(dp[i], Math.max(dp[j], (i - j) * (1L + Math.abs(arr[i] - arr[j]))));
		}
		System.out.print(dp[n-1]);
	}

	static int nextInt() throws Exception {
		int n, c;
		while ((c = System.in.read()) < 48);
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}
