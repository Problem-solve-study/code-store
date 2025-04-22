//문제: BOJ 15954 인형들
//메모리: 12192 KB
//시간: 168 ms

/*
 * 길이가 k 이상인 모든 범위에 대해 분산을 구해 최소값을 구하고
 * 분산의 최소값의 양의 제곱근 출력
 * 분산을 구할 때 해당 구간의 평균을 구해야 하는데
 * 이때 구간의 합을 구하는 것을 누적합으로 미리 계산
 */

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int N = nextInt(), K = nextInt();
		int[] arr = new int[N];
		int[] sum = new int[N];

		sum[0] = arr[0] = nextInt();
		for (int i = 1; i < N; i++)
			sum[i] = sum[i - 1] + (arr[i] = nextInt());

		double m, v, temp, min = Double.MAX_VALUE, len;
		for (int i = 0; i <= N - K; i++) {
			for (int j = i + K - 1; j < N; j++) {
				len = j - i + 1;
				m = (sum[j] - (i == 0 ? 0 : sum[i - 1])) / len;
				v = 0;
				for (int k = i; k <= j; k++) {
					temp = arr[k] - m;
					v += temp * temp;
				}
				v /= len;
				min = min > v ? v : min;
			}
		}
		System.out.println(Math.sqrt(min));
	}

	static int nextInt() throws IOException {
		int c, n;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}
