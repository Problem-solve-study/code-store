
//메모리: 33104 KB
//시간: 268 ms
import java.util.*;
import java.io.*;

public class Main {
	static int n, k;
	static int[] sum;
	static long result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		sum = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		Map<Integer, Long> hm = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken()); // A[n]까지의 누적합 구하기
			if (sum[i] == k) { // A[n]까지의 합이 k라면 result 증가
				result++;
			}
			result += hm.getOrDefault(sum[i] - k, (long) 0); // (A[n]까지의 합 - k)의 값을 가지는 A[1]부터의 부분합의 개수만큼 result 증가
			hm.put(sum[i], hm.getOrDefault(sum[i], (long) 0) + 1); // A[n]까지의 합의 값을 가지는 A[1]부터의 부분합의 개수 증가

		}
		System.out.println(result);
	}

}