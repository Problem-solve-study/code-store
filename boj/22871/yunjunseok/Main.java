// 12788KB, 152ms
import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static long[] dp;
	static int[] arr;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < N; i++){
			dp[i] = Long.MAX_VALUE;
			for (int j = 0; j < i; j++){
				long K = (long) (i - j)*(1 + Math.abs(arr[i] - arr[j]));
				dp[i] = Math.min(dp[i], Math.max(dp[j], K));
			}
		}
		System.out.println(dp[N - 1]);
	}
}