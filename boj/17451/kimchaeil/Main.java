//문제: boj 17451 평행 우주
//메모리: 13324 KB
//시간: 148 ms

/*
 * 처음에는 최소 속도를 행성 n의 요구 속도로 한다.
 * 
 * 최소 속도보다 현재 행성의 요구 속도가 크거나 같다면 속도를 줄이거나 유지하면 되기 때문에 현재 행성의 요구 속도를 최소 속도로 한다.
 * 최소 속도보다 현재 행성의 요구 속도가 작다면 최소속도보다 크거나 같은 (현재 행성의 요구 속도의 배수)를 최소 속도로 한다.
 * 
 * 이를 행성1까지 반복한다.
 * 
 * 처음에는 배수를 구하는 것을 덧셈 반복문으로 하였다가 시간초과가 났다.
 * 그 후 곱셈으로 바꾸었는데 오버플로우를 고려하지 않아서 틀렸다.
 * result를 long형으로 바꾸니 정답이었다.
 * 
 * 40번째 줄은 아래 if-else문과 같은 결과
 * 	if (velo >= result)
 * 		result = velo;
 * 	else {
 * 		long temp = result / velo * velo;
 * 		temp += result > temp ? velo : 0;
 * 		result = temp;
 * 	}
 */

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {

		int n = nextInt(), velo;
		int[] v = new int[n];
		for (int i = 0; i < n; i++)
			v[i] = nextInt();

		long result = 0;
		for (int i = n - 1; i >= 0; i--)
			result = (velo = v[i]) < result ? ((result / velo + (result % velo != 0 ? 1 : 0)) * velo) : velo;

		System.out.print(result);
	}

	static int nextInt() throws IOException {
		int n, c;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}
