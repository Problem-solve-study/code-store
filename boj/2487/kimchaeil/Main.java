//문제: BOJ 2487번 섞기 수열
//메모리: 14520 KB
//시간: 100 ms

/*
 * 처음에는 구현으로 풀었으나 메모리 초과
 * 구현으로 풀면서 알게된 사실:
 * 궤적은 섞기를 진행하면서 생기는 사이클들의 길이들의 최소공배수다.
 * 최소공배수를 구하기위해 유클리디안 호제법으로 두 수의 최대공약수를 구한다.
 * 처음에는 현재까지 구한 값에 사이클의 길이를 곱하고 두 수의 최대공약수로 나누었는데
 * 곱한 후 나누면 곱한 결과가 오버플로우가 나서 틀렸다.
 * 그래서 나눈다음 곱해주니 정답 처리되었다.
 */

import java.io.*;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		int n = nextInt();

		int[] mix_arr = new int[n + 1];
		boolean[] visited = new boolean[n + 1]; //이미 확인한 사이클에 대해 중복 연산을 하지 않게 하기 위해
		for (int i = 1; i <= n; i++)
			mix_arr[i] = nextInt();

		int result = 1;

		for (int i = 1; i <= n; i++) {
			if (visited[i]) //이미 확인한 사이클이면 스킵
				continue;
			int cnt = 1;
			int temp = mix_arr[i];
			while (temp != i) { //사이클 길이 구하기
				visited[temp] = true;
				temp = mix_arr[temp];
				cnt++;
			}
			result = result/euclidean(result, cnt)*cnt;
		}
		System.out.println(result);
	}

	static int euclidean(int a, int b) { //유클리디안 호제법
		int temp = 1;
		while (temp != 0) {
			temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
