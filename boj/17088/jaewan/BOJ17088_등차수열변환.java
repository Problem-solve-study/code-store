/*
 * 수열을 등차수열로 변환하기.
 * 각각의 수에는 최대 한 번의 연산만 가능, -1, +1 
 * 
 * 수열의 크기 N <= 100,000. B <= 1,000,000,000
 * 등차수열로 변환시키기 위한 최소 연산 횟수 출력, 안되면 -1
 * 
 * 등차수열이면 유효한 공차가 하나 있을 것이다.
 * 초항. 공차. 정해야 하는데
 * 
 * 그럼 초항은 B[0] 이거나, B[0]-1, B[0]+1 3가지.
 * 정해진 공차에 대해서, 안되면 -1 출력하면 되잖아.
 * 공차는 첫번째 항, 두번째 항 차이에서 +-2 까지 가능하다.
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BOJ17088_등차수열변환 {
	static int N, cnt, res = Integer.MAX_VALUE;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(br);
		N = nextInt(st);
		arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = nextInt(st);

		if (N == 1)
			res = 0;
		else if (N != 1) {
			int t = arr[1] - arr[0];
			for (int d = t - 2; d <= t + 2; d++) {
				for (int a = arr[0] - 1; a <= arr[0] + 1; a++) {
					cnt = 0;
					if (func(a, d)) { // 초항 a부터 시작해서, 공차가 d인 등차수열로 변환이 가능한지 확인하고, 연산 횟수 count 하는 함수
						res = Math.min(res, cnt);
					}
				}
			}
		}
		System.out.println(res != Integer.MAX_VALUE ? res : -1);
	}

	private static int nextInt(StreamTokenizer st) throws IOException {
		st.nextToken();
		return (int) st.nval;
	}

	static boolean func(int a0, int d) {
		for (int i = 0; i < N; i++) {
			int expected = a0 + d * i; // 등차수열의 i번째 항
			int diff = Math.abs(arr[i] - expected);

			if (diff > 1)
				return false; // 변경 불가능

			if (diff == 1)
				cnt++; // 변경 필요
		}
		return true;
	}

}
